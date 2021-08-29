package com.epoint.xmcg.assetinfo.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.basic.controller.RightRelation;
import com.epoint.xmcg.assetinfo.api.IAssetinfoService;
import com.epoint.xmcg.assetinfo.api.entity.Assetinfo;

/**
 * 采购基本信息表修改页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:52]
 */
@RightRelation(AssetinfoListAction.class)
@RestController("assetinfoeditaction")
@Scope("request")
public class AssetinfoEditAction  extends BaseController
{

	@Autowired
	private IAssetinfoService service;
    
    /**
     * 采购基本信息表实体对象
     */
  	private Assetinfo dataBean=null;
  
    

    public void pageLoad() {
       	String guid = getRequestParameter("guid");
       	dataBean = service.find(guid);
	   	if(dataBean==null)
	   	{
			dataBean=new Assetinfo();  
	   	}
    }

    /**
     * 保存修改
     * 
     */
	public void save() {
	    dataBean.setOperatedate(new Date());
	    service.update(dataBean);
	    addCallbackParam("msg", "修改成功！");
	}

	public Assetinfo getDataBean() {
		return dataBean;
	}

	public void setDataBean(Assetinfo dataBean) {
	    this.dataBean = dataBean;
	}
	      
	
}
