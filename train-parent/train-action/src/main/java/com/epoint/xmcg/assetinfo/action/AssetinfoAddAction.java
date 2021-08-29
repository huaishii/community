package com.epoint.xmcg.assetinfo.action;

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
import com.epoint.xmcg.assetinfo.api.IAssetinfoService;
import com.epoint.xmcg.assetinfo.api.entity.Assetinfo;

/**
 * 采购基本信息表新增页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-24 16:30:53]
 */
@RightRelation(AssetinfoListAction.class)
@RestController("assetinfoaddaction")
@Scope("request")
public class AssetinfoAddAction extends BaseController
{
    @Autowired
    private IAssetinfoService service;
    /**
     * 采购基本信息表实体对象
     */
    private Assetinfo dataBean = null;

    /**
     * 采购类型单选按钮组model
     */
    private List<SelectItem> assettypeModel = null;

    public void pageLoad() {
        dataBean = new Assetinfo();
        dataBean.setApplyuserguid(userSession.getUserGuid());
        dataBean.setApplytime(new Date());
        addCallbackParam("applyUserName", userSession.getDisplayName());
    }

    /**
     * 保存并关闭
     * 
     */
    public void add() {
        dataBean.setRowguid(UUID.randomUUID().toString());
        dataBean.setOperatedate(new Date());
        dataBean.setOperateusername(userSession.getDisplayName());
        service.insert(dataBean);
        addCallbackParam("msg", "保存成功！");
        dataBean = null;
    }

    /**
     * 保存并新建
     * 
     */
    public void addNew() {
        add();
        dataBean = new Assetinfo();
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

    public List<SelectItem> getAssettypeModel() {
        if (assettypeModel == null) {
            assettypeModel = DataUtil.convertMap2ComboBox(
                    (List<Map<String, String>>) CodeModalFactory.factory("单选按钮组", "采购类型", null, false));
        }
        return this.assettypeModel;
    }

}
