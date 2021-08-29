package com.epoint.xmcg.assetinfo.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.epoint.basic.faces.fileupload.FileUploadModel9;
import com.epoint.basic.faces.tree.DefaultFileUploadHandlerImpl9;
import com.epoint.basic.faces.util.DataUtil;
import com.epoint.core.dto.model.SelectItem;
import com.epoint.core.exception.paramcheck.LengthException;
import com.epoint.core.utils.convert.ConvertUtil;
import com.epoint.core.utils.date.EpointDateUtil;
import com.epoint.core.utils.string.StringUtil;
import com.epoint.frame.service.message.api.IMessagesCenterService;
import com.epoint.frame.service.metadata.mis.util.CodeModalFactory;
import com.epoint.frame.service.organ.user.api.IUserService;
import com.epoint.workflow.controller.execute.WorkflowBaseController;
import com.epoint.workflow.service.common.custom.ProcessVersionInstance;
import com.epoint.workflow.service.common.entity.config.WorkflowActivityOperation;
import com.epoint.workflow.service.common.entity.execute.WorkflowWorkItem;
import com.epoint.workflow.service.common.runtime.WorkflowResult9;
import com.epoint.workflow.service.common.util.WorkflowKeyNames9;
import com.epoint.workflow.service.core.api.IWFDefineAPI9;
import com.epoint.workflow.service.core.api.IWFEngineAPI9;
import com.epoint.workflow.service.core.api.IWFInitPageAPI9;
import com.epoint.workflow.service.core.api.IWFInstanceAPI9;
import com.epoint.workflow.service.tcc.api.IWFRMSAPI;
import com.epoint.xmcg.assetinfo.api.IAssetinfoService;
import com.epoint.xmcg.assetinfo.api.entity.Assetinfo;
import com.epoint.xmcg.projectbudgetinfo.api.IProjectbudgetinfoService;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;

/**
 * 采购基本信息表工作流页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:52]
 */
@RestController("assetinfoworkflowaction")
@Scope("request")
public class AssetinfoWorkFlowAction extends WorkflowBaseController
{
    private static Logger logger = LogManager.getLogger(AssetinfoWorkFlowAction.class);

    private WorkflowWorkItem item = null;

    /**
     * 获取流程版本实例操作API
     */
    @Autowired
    private IWFInstanceAPI9 wfInstanceAPI9;

    /**
     * 获取流程流转页面信息API
     */
    @Autowired
    private IWFInitPageAPI9 wfInitPageAPI9;

    /**
     * 获取工作流流程定义信息API
     */
    @Autowired
    private IWFDefineAPI9 wfDefineAPI9;

    @Autowired
    IWFEngineAPI9 wfEngineAPI9;

    private ProcessVersionInstance pvi = null;

    @Autowired
    private IWFRMSAPI wfRMSAPI;

    private Assetinfo dataBean = null;
    // 数据表名
    private String SQLTableName = "AssetInfo";

    private String rowguid;

    @Autowired
    private IAssetinfoService service;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProjectbudgetinfoService iProjectbudgetinfoService;
    @Autowired
    private IMessagesCenterService iMessagesCenterService;

    /**
     * 采购类型单选按钮组model
     */
    private List<SelectItem> assettypeModel = null;

    /**
     * 文件model
     */
    private FileUploadModel9 fileUploadModel;

    @Override
    public void pageLoad() {
        String workitemGuid = getRequestParameter("WorkItemGuid");
        String processVersionInstanceGuid = getRequestParameter("ProcessVersionInstanceGuid");
        pvi = wfInstanceAPI9.getProcessVersionInstance(processVersionInstanceGuid);
        if (StringUtil.isNotBlank(workitemGuid)) {
            item = wfInstanceAPI9.getWorkItem(pvi, workitemGuid);
        }

        rowguid = getRequestParameter("guid");
        // 初始化mis表
        if (StringUtil.isBlank(rowguid)) {
            rowguid = wfInstanceAPI9.getContextItemValue(pvi, SQLTableName);
        }
        if (StringUtil.isNotBlank(rowguid)) {
            // 初次后再加载
            dataBean = service.find(rowguid);
            if (StringUtil.isNotBlank(dataBean.getApplyuserguid())) {
                addCallbackParam("applyUserName", iUserService.getUserNameByUserGuid(dataBean.getApplyuserguid()));
            }
            Projectbudgetinfo projectbudgetinfo = iProjectbudgetinfoService.find(dataBean.getBudgetguid());
            if (StringUtil.isNotBlank(projectbudgetinfo)) {
                addCallbackParam("budgetname", projectbudgetinfo.getBudgetname());
            }
        }
        else {
            // 初次加载
            dataBean = new Assetinfo();
            rowguid = getViewData("rowguid");
            if (StringUtil.isBlank(rowguid)) {
                rowguid = UUID.randomUUID().toString();
                addViewData("rowguid", rowguid);
            }
            dataBean.setApplyuserguid(userSession.getUserGuid());
            dataBean.setApplytime(new Date());
            dataBean.setPviguid(processVersionInstanceGuid);
            addCallbackParam("applyUserName", userSession.getDisplayName());
        }

        // 需要设置字段权限时设置
        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("activityGuid", item.getActivityGuid());
            jsonobject.put("issingleform", true);
            jsonobject.put("processversioninstanceguid", processVersionInstanceGuid);
            addCallbackParam("accessRight", wfInitPageAPI9.init_getTablePropertyControl(jsonobject.toJSONString()));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存并关闭
     * 
     */
    public void submit() {
        if (save()) {
            wfInstanceAPI9.singlematerialSubmit(pvi, SQLTableName, rowguid, userSession.getUserGuid(), true);
        }
    }

    /**
     * 保存并新建
     * 
     */
    public void saveForm() {
        save();
    }

    private boolean save() {
        if ("申请".equals(item.getActivityName())) {
            // 如果不满足业务逻辑返回false
            // 到货时间不能早于当前时间
            if (EpointDateUtil.compareDateOnDay(dataBean.getArrivaltime(), dataBean.getApplytime()) <= 0) {
                addCallbackParam("msg", "到货时间不能早于当前时间");
                return false;
            }
            // 物品总价不能超过剩余金额
            Projectbudgetinfo projectbudgetinfo = iProjectbudgetinfoService.find(dataBean.getBudgetguid());
            if (StringUtil.isNotBlank(projectbudgetinfo)) {
                if (dataBean.getTotalmoney() > (projectbudgetinfo.getBudgetmoney()
                        - projectbudgetinfo.getUsebudgetmoney())) {
                    addCallbackParam("msg", "物品总价不能超过剩余预算金额");
                    return false;
                }
            }
        }

        try {
            if (StringUtil.isBlank(dataBean.getRowguid())) {
                dataBean.setRowguid(rowguid);
                dataBean.setOperatedate(new Date());
                dataBean.setOperateusername(userSession.getDisplayName());
                dataBean.setStatus(10);
                service.insert(dataBean);
            }
            else {
                rowguid = dataBean.getRowguid();
                dataBean.setOperatedate(new Date());
                service.update(dataBean);
            }
        }
        catch (LengthException e) {
            logger.info(e);
        }
        wfInstanceAPI9.singlematerialSubmit(pvi, SQLTableName, rowguid, userSession.getUserGuid(), false);

        if (WorkflowKeyNames9.FIRST_WORKFLOW_WORKITEM.equals(item.getWorkItemType())
                && WorkflowKeyNames9.ActivityInstanceStatus_Inactive == item.getStatus()) {
            String title = "【待提交】" + userSession.getDisplayName() + "采购申请流程";
            iMessagesCenterService.updateMessageTitleAndShow(item.getWaitHandleGuid(), title,
                    userSession.getUserGuid());
            // 修改当前工作项名称
            item.setWorkItemName(title);
            List<WorkflowWorkItem> wList = new ArrayList<>();
            wList.add(item);
            wfInstanceAPI9.updateWorkFlowWorkItem(wList);
        }

        // 如果满足业务逻辑返回true,msg为空
        addCallbackParam("msg", "");
        return true;
    }

    public void setDataBean(Assetinfo dataBean) {
        this.dataBean = dataBean;
    }

    public Assetinfo getDataBean() {
        return dataBean;
    }

    /**
     * 系统方法，勿删 获取按钮打开页面或者默认处理
     * 
     * @param operationGuid
     *            操作按钮guid
     * @param transitionGuid
     *            变迁guid
     * @return
     */
    public String getPageUrlOfOperate(String data) {
        // String returndata = EncodeUtil.decode(data);
        JSONObject jsonobject = JSONObject.parseObject(data);
        jsonobject.put("userguid", userSession.getUserGuid());
        jsonobject.put("ishandleendactivity", true);
        if (StringUtil.isNotBlank(getRequestParameter("batchHandleGuid"))) {
            String otherurlparam = "batchHandleGuid=" + getRequestParameter("batchHandleGuid");
            jsonobject.put("otherurlparam", otherurlparam);
        }
        String operationGuid = jsonobject.get("operationguid").toString();

        // 获取操作处理页面或者默认处理
        JSONObject returnobject = JSONObject
                .parseObject(wfInitPageAPI9.init_getPageUrlOfOperate(jsonobject.toString()));
        WorkflowActivityOperation operation = (StringUtil.isNotBlank(operationGuid))
                ? wfDefineAPI9.getActOperation(pvi, operationGuid)
                : new WorkflowActivityOperation();
        if (returnobject.containsKey("isdefoperation")) {
            boolean isdefault = ConvertUtil.convertStringToBoolean(returnobject.get("isdefoperation").toString());
            if (isdefault) {
                // 需要默认操作前确认提示返回returnobject.toString()即可
                jsonobject.put("operationname", operation.getOperationName());
                jsonobject.put("isdefoperation", isdefault);
                return jsonobject.toString();
                // 如果不需要提示直接处理
                // return getoperate(data);
            }
        }
        returnobject.put("operationtype", operation.getOperationType());
        return returnobject.toString();
    }

    /**
     * 系统方法，勿删 操作处理页面返回json后回调
     * 
     * @param data
     *            json数据
     * @return
     */
    public String getoperate(String data) {
        // data = EncodeUtil.decode(data);
        JSONObject jsonobject = JSONObject.parseObject(data);
        jsonobject.put("userguid", userSession.getUserGuid());
        jsonobject.put("displayname", userSession.getDisplayName());
        String json = jsonobject.toString();
        JSONObject returnjsonobject = new JSONObject();
        returnjsonobject.put("message", "");
        try {
            // 调用IWFRMSAPI.operateTCC包裹分布式事务，如果存在操作前后事件请个性化主业务服务IWFRMSAPI
            // String resultString = wfRMSAPI.operateTCC(json);
            // WorkflowResult9 result = JsonUtil.jsonToObject(resultString,
            // WorkflowResult9.class);
            WorkflowResult9 result = wfEngineAPI9.operate(json);
            // 非服务化部署，本地事务情况可以编写操作后事件
            // 服务化架构部署的此处不允许编写操作后事件
            if (StringUtil.isNotBlank(result.getCurrentActIstance().getActivity().getNote())) {
                // getActivityDispName
                switch (result.getCurrentActIstance().getActivity().getNote()) {
                    case "审核":
                        // getOperationName()
                        switch (result.getOperation().getNote()) {
                            case "送下一步":
                                // 操作后事件
                                break;
                        }
                        break;
                }
            }
        }
        catch (RuntimeException e) {
            String message = e.getMessage();
            if (StringUtil.isNotBlank(e.getMessage()) && e.getMessage().indexOf("：") > -1) {
                message = e.getMessage().split("：")[1];
            }
            returnjsonobject.put("message", message);
        }
        return returnjsonobject.toString();
    }

    public List<SelectItem> getAssettypeModel() {
        if (assettypeModel == null) {
            assettypeModel = DataUtil.convertMap2ComboBox(
                    (List<Map<String, String>>) CodeModalFactory.factory("单选按钮组", "采购类型", null, false));
        }
        return this.assettypeModel;
    }

    // 文件上传
    public FileUploadModel9 getFileUploadModel() {
        if (fileUploadModel == null) {
            // DefaultFileUploadHandlerImpl9具体详情可以去查基础api
            // DefaultFileUploadHandlerImpl9参数为：clientGuid，clientTag，clientInfo，AttachHandler9，attachHandler，userGuid，userName

            // clientGuid一般是地址中获取到的，此处只做参考使用
            fileUploadModel = new FileUploadModel9(new DefaultFileUploadHandlerImpl9(rowguid, null, null, null,
                    userSession.getUserGuid(), userSession.getDisplayName()));
        }

        // 该属性设置他为只读，不能被删除
        // fileUploadModel1.setReadOnly("true");
        return fileUploadModel;
    }
}
