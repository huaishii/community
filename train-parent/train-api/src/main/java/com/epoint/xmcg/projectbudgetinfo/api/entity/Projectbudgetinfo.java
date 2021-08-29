package com.epoint.xmcg.projectbudgetinfo.api.entity;

import java.util.Date;

import com.epoint.core.BaseEntity;
import com.epoint.core.annotation.Entity;
import com.epoint.core.annotation.EntityLength;

/**
 * 项目预算信息表实体
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:42]
 */
@Entity(table = "ProjectBudgetInfo", id = "rowguid")
public class Projectbudgetinfo extends BaseEntity implements Cloneable
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
	 * 填写时间
	 */
	public Date getUpdatetime() {
		return super.getDate("updatetime");
	}

	public void setUpdatetime (Date updatetime) {
		super.set("updatetime",updatetime);
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
	 * 预算已使用额
	 */
	public Integer getUsebudgetmoney() {
		return super.getInt("usebudgetmoney");
	}

	public void setUsebudgetmoney (Integer usebudgetmoney) {
		super.set("usebudgetmoney",usebudgetmoney);
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
	 * 预算金额
	 */
	public Integer getBudgetmoney() {
		return super.getInt("budgetmoney");
	}

	public void setBudgetmoney (Integer budgetmoney) {
		super.set("budgetmoney",budgetmoney);
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
	 * 项目标识
	 */
	@EntityLength(length = 50)
	public String getProjectguid() {
		return super.get("projectguid");
	}

	public void setProjectguid (String projectguid) {
		super.set("projectguid",projectguid);
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
	 * 预算类型
	 */
	@EntityLength(length = 10)
	public String getBudgettype() {
		return super.get("budgettype");
	}

	public void setBudgettype (String budgettype) {
		super.set("budgettype",budgettype);
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
	 * 预算名称
	 */
	@EntityLength(length = 100)
	public String getBudgetname() {
		return super.get("budgetname");
	}

	public void setBudgetname (String budgetname) {
		super.set("budgetname",budgetname);
	}

}