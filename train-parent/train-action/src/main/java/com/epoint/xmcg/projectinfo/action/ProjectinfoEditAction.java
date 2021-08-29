package com.epoint.xmcg.projectinfo.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.drew.lang.StringUtil;
import com.epoint.basic.controller.BaseController;
import com.epoint.basic.controller.RightRelation;
import com.epoint.basic.faces.util.DataUtil;
import com.epoint.core.dto.model.SelectItem;
import com.epoint.frame.service.metadata.code.api.ICodeItemsService;
import com.epoint.frame.service.metadata.mis.util.CodeModalFactory;
import com.epoint.frame.service.organ.user.api.IUserService;
import com.epoint.xmcg.projectinfo.api.IProjectinfoService;
import com.epoint.xmcg.projectinfo.api.entity.Projectinfo;

/**
 * 项目信息表修改页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:26]
 */
@RightRelation(ProjectinfoListAction.class)
@RestController("projectinfoeditaction")
@Scope("request")
public class ProjectinfoEditAction extends BaseController
{

    @Autowired
    private IProjectinfoService service;
    
    @Autowired
    private IUserService iUserService;
    
    @Autowired
    private ICodeItemsService iCodeItemsService;

    /**
     * 项目信息表实体对象
     */
    private Projectinfo dataBean = null;

    /**
     * 所属地区下拉列表model
     */
    private List<SelectItem> belongzoneModel = null;
    /**
     * 项目类别下拉列表model
     */
    private List<SelectItem> projecttypeModel = null;

    public void pageLoad() {
        String guid = getRequestParameter("guid");
        dataBean = service.find(guid);
        
        //项目经理名字 转换为文本
        String projectManagerName = iUserService.getUserNameByUserGuid(dataBean.getProjectmanager());
        addCallbackParam("projectManagerName", projectManagerName);
        
        //项目人员转换为文本
        String projectUserName = service.getAllUser(dataBean.getProjectuser());
        addCallbackParam("projectUserName", projectUserName);
        
        //所属地区转换为文本
        String belongzoneText = iCodeItemsService.getItemTextByCodeName("所属地区", dataBean.getBelongzone());
        addCallbackParam("belongzoneText", belongzoneText);
        
        if (dataBean == null) {
            dataBean = new Projectinfo();
        }
    }

    /**
     * 保存修改
     * 
     */
    public void save() {
        dataBean.setOperatedate(new Date());
        
        //修改页重名验证
        String projectNameOld = service.find(dataBean.getRowguid()).getProjectname();
        String projectNameNew = dataBean.getProjectname();
        int count = service.checkExist(projectNameOld,projectNameNew);
        
        if (count > 0) {
            addCallbackParam("msg", "项目名称重复！");
            addCallbackParam("sameName", true);
        } else {
            service.update(dataBean);
            addCallbackParam("msg", "修改成功！");
        }
    }

    public Projectinfo getDataBean() {
        return dataBean;
    }

    public void setDataBean(Projectinfo dataBean) {
        this.dataBean = dataBean;
    }

    public List<SelectItem> getBelongzoneModel() {
        if (belongzoneModel == null) {
            belongzoneModel = DataUtil.convertMap2ComboBox(
                    (List<Map<String, String>>) CodeModalFactory.factory("下拉列表", "所属地区", null, false));
        }
        return this.belongzoneModel;
    }

    public List<SelectItem> getProjecttypeModel() {
        if (projecttypeModel == null) {
            projecttypeModel = DataUtil.convertMap2ComboBox(
                    (List<Map<String, String>>) CodeModalFactory.factory("下拉列表", "项目类别", null, false));
        }
        return this.projecttypeModel;
    }

}
