package com.epoint.xmcg.projectbudgetinfo.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

/**
 * 项目预算信息表修改页面对应的后台
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:42]
 */
@RightRelation(ProjectbudgetinfoListAction.class)
@RestController("projectbudgetinfoeditaction")
@Scope("request")
public class ProjectbudgetinfoEditAction  extends BaseController
{

	@Autowired
	private IProjectbudgetinfoService service;
    
    /**
     * 项目预算信息表实体对象
     */
  	private Projectbudgetinfo dataBean=null;
  
     /**
  * 预算类型单选按钮组model
  */
 private List<SelectItem>  budgettypeModel=null;


    public void pageLoad() {
       	String guid = getRequestParameter("guid");
       	dataBean = service.find(guid);
	   	if(dataBean==null)
	   	{
			dataBean=new Projectbudgetinfo();  
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

	public Projectbudgetinfo getDataBean() {
		return dataBean;
	}

	public void setDataBean(Projectbudgetinfo dataBean) {
	    this.dataBean = dataBean;
	}
	      
	  public  List<SelectItem> getBudgettypeModel(){if(budgettypeModel==null){budgettypeModel=DataUtil.convertMap2ComboBox((List<Map<String, String>>) CodeModalFactory.factory("单选按钮组","预算类型",null,false));
} return this.budgettypeModel;}

}
