package com.epoint.xmcg.projectbudgetinfo.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.basic.controller.RightRelation;
import com.epoint.basic.faces.util.DataUtil;
import com.epoint.core.dto.model.SelectItem;
import com.epoint.frame.service.metadata.mis.util.CodeModalFactory;
import com.epoint.xmcg.projectbudgetinfo.api.IProjectbudgetinfoService;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;
import com.epoint.xmcg.projectinfo.api.IProjectinfoService;

/**
 * 项目预算信息表新增页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:42]
 */
@RightRelation(ProjectbudgetinfoListAction.class)
@RestController("projectbudgetinfoaddaction")
@Scope("request")
public class ProjectbudgetinfoAddAction extends BaseController
{
    @Autowired
    private IProjectbudgetinfoService service;
    @Autowired
    private IProjectinfoService iProjectinfoService;
    /**
     * 项目预算信息表实体对象
     */
    private Projectbudgetinfo dataBean = null;

    /**
     * 预算类型单选按钮组model
     */
    private List<SelectItem> budgettypeModel = null;

    private String projectGuid;

    public void pageLoad() {
        projectGuid = getRequestParameter("projectguid");
        dataBean = new Projectbudgetinfo();
        dataBean.setUsebudgetmoney(0);
        dataBean.setUpdatetime(new Date());
    }

    /**
     * 保存并关闭
     * 
     */
    public void add() {
        dataBean.setRowguid(UUID.randomUUID().toString());
        dataBean.setOperatedate(new Date());
        dataBean.setOperateusername(userSession.getDisplayName());
        dataBean.setProjectguid(projectGuid);
        // 已经添加的预算金额总额
        int monenyBefore = service.getMonenyBefore(dataBean.getProjectguid());
        // 将要添加的预算金额
        int moneny = dataBean.getBudgetmoney();
        // 项目的预算金额
        int budgetMoneny = iProjectinfoService.find(dataBean.getProjectguid()).getProjectmoney();
        if (monenyBefore + moneny > budgetMoneny) {
            addCallbackParam("msg", "超出项目的预算金额");
        }
        else {
            service.insert(dataBean);
            addCallbackParam("msg", "保存成功！");
        }
    }

    /**
     * 保存并新建
     * 
     */
    public void addNew() {
        add();
        dataBean = new Projectbudgetinfo();
        dataBean.setUsebudgetmoney(0);
        dataBean.setUpdatetime(new Date());
    }

    public Projectbudgetinfo getDataBean() {
        if (dataBean == null) {
            dataBean = new Projectbudgetinfo();
        }
        return dataBean;
    }

    public void setDataBean(Projectbudgetinfo dataBean) {
        this.dataBean = dataBean;
    }

    public List<SelectItem> getBudgettypeModel() {
        if (budgettypeModel == null) {
            budgettypeModel = DataUtil.convertMap2ComboBox(
                    (List<Map<String, String>>) CodeModalFactory.factory("单选按钮组", "预算类型", null, false));
        }
        return this.budgettypeModel;
    }

}
