package com.epoint.xmcg.assetinfo.action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.basic.controller.RightRelation;
import com.epoint.frame.service.organ.user.api.IUserService;
import com.epoint.xmcg.assetinfo.api.IAssetinfoService;
import com.epoint.xmcg.assetinfo.api.entity.Assetinfo;
import com.epoint.xmcg.projectbudgetinfo.api.IProjectbudgetinfoService;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;

/**
 * 采购基本信息表详情页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:52]
 */
@RightRelation(AssetinfoListAction.class)
@RestController("assetinfodetailaction")
@Scope("request")
public class AssetinfoDetailAction  extends BaseController
{
	@Autowired
    private IAssetinfoService service; 
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IProjectbudgetinfoService iProjectbudgetinfoService;
    
    /**
     * 采购基本信息表实体对象
     */
  	private Assetinfo dataBean=null;
  
    public void pageLoad() {
	   	String	guid = getRequestParameter("guid");
	   	dataBean = service.find(guid);
	   	//申请人员
	   	String applyUserName = iUserService.getUserNameByUserGuid(dataBean.getApplyuserguid());
	   	dataBean.setApplyuserguid(applyUserName);
	   	//预算名称
	   	Projectbudgetinfo projectbudgetinfo = iProjectbudgetinfoService.find(dataBean.getBudgetguid());
	   	String budgetname = projectbudgetinfo.getBudgetname();
	   	dataBean.setBudgetguid(budgetname);
		  if(dataBean==null){
		      dataBean=new Assetinfo();  
		  }
    }
   
	public Assetinfo getDataBean() {
		return dataBean;
	}
}