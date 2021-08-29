package com.epoint.xmcg.projectinfo.action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.basic.controller.RightRelation;
import com.epoint.frame.service.metadata.code.api.ICodeItemsService;
import com.epoint.frame.service.organ.user.api.IUserService;
import com.epoint.xmcg.projectinfo.api.IProjectinfoService;
import com.epoint.xmcg.projectinfo.api.entity.Projectinfo;

/**
 * 项目信息表详情页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:26]
 */
@RightRelation(ProjectinfoListAction.class)
@RestController("projectinfodetailaction")
@Scope("request")
public class ProjectinfoDetailAction  extends BaseController
{
	@Autowired
    private IProjectinfoService service; 
	
	@Autowired
	private IUserService iUserService;
    
    /**
     * 项目信息表实体对象
     */
  	private Projectinfo dataBean=null;
  
    public void pageLoad() {
	   	String	guid = getRequestParameter("guid");
	   	dataBean = service.find(guid);
	   	
	    //项目经理名字 转换为文本
        String projectManagerName = iUserService.getUserNameByUserGuid(dataBean.getProjectmanager());
        
        //项目人员转换为文本
        String projectUserName = service.getAllUser(dataBean.getProjectuser());
        
        dataBean.setProjectmanager(projectManagerName);
        dataBean.setProjectuser(projectUserName);
	   	
		  if(dataBean==null){
		      dataBean=new Projectinfo();  
		  }
    }
   
	public Projectinfo getDataBean() {
		return dataBean;
	}
}