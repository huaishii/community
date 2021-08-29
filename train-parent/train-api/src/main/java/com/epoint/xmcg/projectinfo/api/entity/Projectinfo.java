package com.epoint.xmcg.projectinfo.api.entity;

import java.util.Date;

import com.epoint.core.BaseEntity;
import com.epoint.core.annotation.Entity;
import com.epoint.core.annotation.EntityLength;

/**
 * 项目信息表实体
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:26]
 */
@Entity(table = "ProjectInfo", id = "rowguid")
public class Projectinfo extends BaseEntity implements Cloneable
{
    private static final long serialVersionUID = 1L;

	/**
	 * 流程实例标识
	 */
	@EntityLength(length = 50)
	public String getPviguid() {
		return super.get("pviguid");
	}

	public void setPviguid (String pviguid) {
		super.set("pviguid",pviguid);
	}

	/**
	 * 操作人所属单位guid
	 */
	@EntityLength(length = 50)
	public String getOperateuserbaseouguid() {
		return super.get("operateuserbaseouguid");
	}

	public void setOperateuserbaseouguid (String operateuserbaseouguid) {
		super.set("operateuserbaseouguid",operateuserbaseouguid);
	}

	/**
	 * 操作人所属部门guid
	 */
	@EntityLength(length = 50)
	public String getOperateuserouguid() {
		return super.get("operateuserouguid");
	}

	public void setOperateuserouguid (String operateuserouguid) {
		super.set("operateuserouguid",operateuserouguid);
	}

	/**
	 * 操作人guid
	 */
	@EntityLength(length = 50)
	public String getOperateuserguid() {
		return super.get("operateuserguid");
	}

	public void setOperateuserguid (String operateuserguid) {
		super.set("operateuserguid",operateuserguid);
	}

	/**
	 * 项目描述
	 */
	@EntityLength(length = 255)
	public String getDescriptions() {
		return super.get("descriptions");
	}

	public void setDescriptions (String descriptions) {
		super.set("descriptions",descriptions);
	}

	/**
	 * 所属辖区号
	 */
	@EntityLength(length = 50)
	public String getBelongxiaqucode() {
		return super.get("belongxiaqucode");
	}

	public void setBelongxiaqucode (String belongxiaqucode) {
		super.set("belongxiaqucode",belongxiaqucode);
	}

	/**
	 * 项目人员
	 */
	@EntityLength(length = 2000)
	public String getProjectuser() {
		return super.get("projectuser");
	}

	public void setProjectuser (String projectuser) {
		super.set("projectuser",projectuser);
	}

	/**
	 * 操作者名字
	 */
	@EntityLength(length = 50)
	public String getOperateusername() {
		return super.get("operateusername");
	}

	public void setOperateusername (String operateusername) {
		super.set("operateusername",operateusername);
	}

	/**
	 * 项目经理
	 */
	@EntityLength(length = 50)
	public String getProjectmanager() {
		return super.get("projectmanager");
	}

	public void setProjectmanager (String projectmanager) {
		super.set("projectmanager",projectmanager);
	}

	/**
	 * 操作日期
	 */
	public Date getOperatedate() {
		return super.getDate("operatedate");
	}

	public void setOperatedate (Date operatedate) {
		super.set("operatedate",operatedate);
	}

	/**
	 * 资金金额
	 */
	public Integer getProjectmoney() {
		return super.getInt("projectmoney");
	}

	public void setProjectmoney (Integer projectmoney) {
		super.set("projectmoney",projectmoney);
	}

	/**
	 * 序号
	 */
	public Integer getRow_id() {
		return super.getInt("row_id");
	}

	public void setRow_id (Integer row_id) {
		super.set("row_id",row_id);
	}

	/**
	 * 项目类别
	 */
	@EntityLength(length = 10)
	public String getProjecttype() {
		return super.get("projecttype");
	}

	public void setProjecttype (String projecttype) {
		super.set("projecttype",projecttype);
	}

	/**
	 * 年份标识
	 */
	@EntityLength(length = 4)
	public String getYearflag() {
		return super.get("yearflag");
	}

	public void setYearflag (String yearflag) {
		super.set("yearflag",yearflag);
	}

	/**
	 * 所属地区
	 */
	@EntityLength(length = 10)
	public String getBelongzone() {
		return super.get("belongzone");
	}

	public void setBelongzone (String belongzone) {
		super.set("belongzone",belongzone);
	}

	/**
	 * 默认主键字段
	 */
	@EntityLength(length = 50)
	public String getRowguid() {
		return super.get("rowguid");
	}

	public void setRowguid (String rowguid) {
		super.set("rowguid",rowguid);
	}

	/**
	 * 项目名称
	 */
	@EntityLength(length = 100)
	public String getProjectname() {
		return super.get("projectname");
	}

	public void setProjectname (String projectname) {
		super.set("projectname",projectname);
	}

}