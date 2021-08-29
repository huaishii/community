package com.epoint.xmcg.projectbudgetinfo.action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.basic.controller.RightRelation;
import com.epoint.xmcg.projectbudgetinfo.api.IProjectbudgetinfoService;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;
import com.epoint.xmcg.projectinfo.api.IProjectinfoService;

/**
 * 项目预算信息表详情页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:42]
 */
@RightRelation(ProjectbudgetinfoListAction.class)
@RestController("projectbudgetinfodetailaction")
@Scope("request")
public class ProjectbudgetinfoDetailAction  extends BaseController
{
	@Autowired
    private IProjectbudgetinfoService service; 
	@Autowired
	private IProjectinfoService iProjectinfoService;
    
    /**
     * 项目预算信息表实体对象
     */
  	private Projectbudgetinfo dataBean=null;
  
    public void pageLoad() {
	   	String	guid = getRequestParameter("guid");
	   	dataBean = service.find(guid);
	    String projectName = iProjectinfoService.find(dataBean.getProjectguid()).getProjectname();
	    dataBean.setProjectguid(projectName);
		  if(dataBean==null){
		      dataBean=new Projectbudgetinfo();  
		  }
    }
   
	public Projectbudgetinfo getDataBean() {
		return dataBean;
	}
}