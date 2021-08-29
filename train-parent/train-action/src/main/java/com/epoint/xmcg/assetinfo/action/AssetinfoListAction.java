package com.epoint.xmcg.assetinfo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.basic.faces.export.ExportModel;
import com.epoint.basic.faces.tree.TreeFunction9;
import com.epoint.core.dto.base.TreeNode;
import com.epoint.core.dto.model.DataGridModel;
import com.epoint.core.dto.model.TreeModel;
import com.epoint.core.utils.sql.SqlConditionUtil;
import com.epoint.core.utils.string.StringUtil;
import com.epoint.database.peisistence.crud.impl.model.PageData;
import com.epoint.xmcg.assetinfo.api.IAssetinfoService;
import com.epoint.xmcg.assetinfo.api.entity.Assetinfo;
import com.epoint.xmcg.projectbudgetinfo.api.IProjectbudgetinfoService;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;
import com.epoint.xmcg.projectinfo.api.IProjectinfoService;
import com.epoint.xmcg.projectinfo.api.entity.Projectinfo;
import com.epoint.frame.service.metadata.mis.util.ListGenerator;
import com.epoint.workflow.service.common.custom.ProcessVersionInstance;
import com.epoint.workflow.service.common.entity.execute.WorkflowWorkItem;
import com.epoint.workflow.service.common.util.WorkflowKeyNames9;
import com.epoint.workflow.service.core.api.IWFInstanceAPI9;

/**
 * 采购基本信息表list页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:52]
 */
@RestController("assetinfolistaction")
@Scope("request")
public class AssetinfoListAction extends BaseController
{
    @Autowired
    private IAssetinfoService service;
    @Autowired
    private IProjectinfoService iProjectinfoService;
    @Autowired
    private IProjectbudgetinfoService iProjectbudgetinfoService;
    @Autowired
    private IWFInstanceAPI9 instanceAPI9;

    /**
     * 采购基本信息表实体对象
     */
    private Assetinfo dataBean;

    /**
     * 表格控件model
     */
    private DataGridModel<Assetinfo> model;

    /**
     * 导出模型
     */
    private ExportModel exportModel;

    /**
     * 树控件
     */
    private TreeModel treeModel;

    private String nodeInfo;

    public void pageLoad() {

    }

    public TreeModel geTreeModel() {
        if (treeModel == null) {
            treeModel = new TreeModel()
            {

                @Override
                public List<TreeNode> fetch(TreeNode treeNode) {
                    List<TreeNode> nodes = new ArrayList<>();
                    if (treeNode == null) {
                        // 构建根节点
                        TreeNode rNode = new TreeNode();
                        rNode.setId(TreeFunction9.F9ROOT);
                        rNode.setText("所有项目预算");
                        rNode.setExpanded(true);// 是否展开
                        rNode.getColumns().put("nodeType", "rootNode");
                        nodes.add(rNode);

                        // 构建父节点
                        List<Projectinfo> prjectInfoList = iProjectinfoService.findList(null);
                        TreeNode pNode = null;
                        for (Projectinfo projectinfo : prjectInfoList) {
                            pNode = new TreeNode();
                            pNode.setId(projectinfo.getRowguid());
                            pNode.setText(projectinfo.getProjectname());
                            pNode.setPid(rNode.getId());
                            pNode.getColumns().put("nodeType", "parentNode");

                            // 当前节点是否是叶子节点
                            List<Projectbudgetinfo> list = iProjectbudgetinfoService
                                    .getBudgetInfoByProjectGuid(projectinfo.getRowguid());
                            pNode.setLeaf(list.isEmpty());
//                            if (list.isEmpty()) {
//                                pNode.setLeaf(false);
//                            }
//                            else {
//                                pNode.setLeaf(true);
//                            }
                            nodes.add(pNode);
                        }
                    }
                    else {
                        // 构建子节点 预算信息
                        List<Projectbudgetinfo> list = iProjectbudgetinfoService
                                .getBudgetInfoByProjectGuid(treeNode.getId());
                        TreeNode sNode = null;
                        for (Projectbudgetinfo projectbudgetinfo : list) {
                            sNode = new TreeNode();
                            sNode.setId(projectbudgetinfo.getRowguid());
                            sNode.setText(projectbudgetinfo.getBudgetname());
                            sNode.setPid(treeNode.getId());
                            sNode.setLeaf(true);
                            sNode.getColumns().put("nodeType", "subNode");
                        }
                        nodes.add(sNode);
                    }
                    return nodes;
                }
            };
        }
        return treeModel;
    }

    /**
     * 删除选定
     * 
     */
    public void deleteSelect() {
        List<String> select = getDataGridData().getSelectKeys();
        for (String sel : select) {
            service.deleteByGuid(sel);
        }
        addCallbackParam("msg", "成功删除！");
    }

    public DataGridModel<Assetinfo> getDataGridData() {
        // 获得表格对象
        if (model == null) {
            model = new DataGridModel<Assetinfo>()
            {

                @Override
                public List<Assetinfo> fetchData(int first, int pageSize, String sortField, String sortOrder) {
                    // 获取where条件Map集合
                    Map<String, Object> conditionMap = ListGenerator.getSearchMap(getRequestContext().getComponents(),
                            sortField, sortOrder);
                    if (StringUtil.isNotBlank(nodeInfo)) {
                        String[] str = nodeInfo.split(";");
                        String nodeId = str[0];
                        String nodeType = str[1];
                        SqlConditionUtil sqlConditionUtil = new SqlConditionUtil();

                        // 点选节点是子节点
                        if ("subNode".equals(nodeType)) {
                            sqlConditionUtil.eq("budgetguid", nodeId);
                        }
                        // 点选节点是parentNode
                        if ("parentNode".equals(nodeType)) {
                            List<Projectbudgetinfo> list = iProjectbudgetinfoService.getBudgetInfoByProjectGuid(nodeId);
                            List<String> budgetList = new ArrayList<>();
                            for (Projectbudgetinfo projectbudgetinfo : list) {
                                budgetList.add(projectbudgetinfo.getRowguid());
                            }
                            sqlConditionUtil.in("budgetguid", "'" + StringUtil.join(budgetList, "','") + "'");
                        }

                        conditionMap.putAll(sqlConditionUtil.getMap());
                    }

                    PageData<Assetinfo> pageData = service.paginatorList(conditionMap, first, pageSize);
                    this.setRowCount(pageData.getRowCount());
                    List<Assetinfo> list = pageData.getList();
                    for (Assetinfo assetinfo : list) {
                        String applyUserName = assetinfo.getOperateusername();
                        assetinfo.put("applyUserName", applyUserName);

                        // 标题、处理地址
                        String title = applyUserName + "的采购详情";
                        String url = "";
                        if (StringUtil.isNotBlank(assetinfo.getPviguid())) {
                            ProcessVersionInstance pvi = instanceAPI9.getProcessVersionInstance(assetinfo.getPviguid());
                            ArrayList<Integer> status = new ArrayList<>();
                            status.add(WorkflowKeyNames9.WorkItemStatus_Active);
                            List<WorkflowWorkItem> wList = instanceAPI9.getWorkItemListByPVIGuidAndStatus(pvi, status);
                            for (WorkflowWorkItem workItem : wList) {
                                if (userSession.getUserGuid().equalsIgnoreCase(workItem.getOperatorForDisplayGuid())) {
                                    title = workItem.getWorkItemName();
                                    url = workItem.getHandleUrl();

                                }
                            }
                        }
                        assetinfo.put("title", title);
                        assetinfo.put("url", url);

                    }
                    return list;
                }

            };
        }
        return model;
    }

    public Assetinfo getDataBean() {
        if (dataBean == null) {
            dataBean = new Assetinfo();
        }
        return dataBean;
    }

    public void setDataBean(Assetinfo dataBean) {
        this.dataBean = dataBean;
    }

    public ExportModel getExportModel() {
        if (exportModel == null) {
            exportModel = new ExportModel("", "");
        }
        return exportModel;
    }

    public String getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(String nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

}
