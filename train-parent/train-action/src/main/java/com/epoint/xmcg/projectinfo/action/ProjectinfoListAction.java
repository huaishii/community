package com.epoint.xmcg.projectinfo.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.basic.faces.export.ExportModel;
import com.epoint.basic.faces.util.DataUtil;
import com.epoint.core.dto.model.DataGridModel;
import com.epoint.core.dto.model.SelectItem;
import com.epoint.database.peisistence.crud.impl.model.PageData;
import com.epoint.frame.service.metadata.mis.util.CodeModalFactory;
import com.epoint.frame.service.metadata.mis.util.ListGenerator;
import com.epoint.frame.service.organ.user.api.IUserService;
import com.epoint.xmcg.projectinfo.api.IProjectinfoService;
import com.epoint.xmcg.projectinfo.api.entity.Projectinfo;

/**
 * 项目信息表list页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:26]
 */
@RestController("projectinfolistaction")
@Scope("request")
public class ProjectinfoListAction extends BaseController
{
    @Autowired
    private IProjectinfoService service;
    
    @Autowired
    private IUserService iUserService;

    /**
     * 项目信息表实体对象
     */
    private Projectinfo dataBean;

    /**
     * 表格控件model
     */
    private DataGridModel<Projectinfo> model;

    /**
     * 导出模型
     */
    private ExportModel exportModel;

    /**
     * 项目类别下拉列表model
     */
    private List<SelectItem> projecttypeModel = null;

    public void pageLoad() {
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

    public DataGridModel<Projectinfo> getDataGridData() {
        // 获得表格对象
        if (model == null) {
            model = new DataGridModel<Projectinfo>()
            {

                @Override
                public List<Projectinfo> fetchData(int first, int pageSize, String sortField, String sortOrder) {
                    // 获取where条件Map集合
                    Map<String, Object> conditionMap = ListGenerator.getSearchMap(getRequestContext().getComponents(),
                            sortField, sortOrder);
                    PageData<Projectinfo> pageData = service.paginatorList(conditionMap, first, pageSize);
                    this.setRowCount(pageData.getRowCount());
                    List<Projectinfo> list = pageData.getList();
                    for (Projectinfo projectinfo : list) {
                        //将项目经理，项目人员转换为文本
                        projectinfo.put("projectManagetName", iUserService.getUserNameByUserGuid(projectinfo.getProjectmanager()));
                        projectinfo.put("projectUserName", service.getAllUser(projectinfo.getProjectuser()));
                    }
                    return list;
                }

            };
        }
        return model;
    }

    public Projectinfo getDataBean() {
        if (dataBean == null) {
            dataBean = new Projectinfo();
        }
        return dataBean;
    }

    public void setDataBean(Projectinfo dataBean) {
        this.dataBean = dataBean;
    }

    public ExportModel getExportModel() {
        if (exportModel == null) {
            exportModel = new ExportModel("", "");
        }
        return exportModel;
    }

    public List<SelectItem> getProjecttypeModel() {
        if (projecttypeModel == null) {
            projecttypeModel = DataUtil.convertMap2ComboBox(
                    (List<Map<String, String>>) CodeModalFactory.factory("下拉列表", "项目类别", null, true));
        }
        return this.projecttypeModel;
    }

}
