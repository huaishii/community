package com.epoint.xmcg.assetinfo.api.entity;

import java.util.Date;

import com.epoint.core.BaseEntity;
import com.epoint.core.annotation.Entity;
import com.epoint.core.annotation.EntityLength;

/**
 * 采购基本信息表实体
 * 
 * @author Epoint
 * @version [版本号, 2021-08-25 14:42:13]
 */
@Entity(table = "AssetInfo", id = "rowguid")
public class Assetinfo extends BaseEntity implements Cloneable
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
	 * 申请状态
	 */
	public Integer getStatus() {
		return super.getInt("status");
	}

	public void setStatus (Integer status) {
		super.set("status",status);
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
	 * 申请说明
	 */
	@EntityLength(length = 255)
	public String getDescription() {
		return super.get("description");
	}

	public void setDescription (String description) {
		super.set("description",description);
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
	 * 物品总价
	 */
	public Integer getTotalmoney() {
		return super.getInt("totalmoney");
	}

	public void setTotalmoney (Integer totalmoney) {
		super.set("totalmoney",totalmoney);
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
	 * 采购类型
	 */
	@EntityLength(length = 10)
	public String getAssettype() {
		return super.get("assettype");
	}

	public void setAssettype (String assettype) {
		super.set("assettype",assettype);
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
	 * 到货时间
	 */
	public Date getArrivaltime() {
		return super.getDate("arrivaltime");
	}

	public void setArrivaltime (Date arrivaltime) {
		super.set("arrivaltime",arrivaltime);
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
	 * 申请时间
	 */
	public Date getApplytime() {
		return super.getDate("applytime");
	}

	public void setApplytime (Date applytime) {
		super.set("applytime",applytime);
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
	 * 预算标识
	 */
	@EntityLength(length = 50)
	public String getBudgetguid() {
		return super.get("budgetguid");
	}

	public void setBudgetguid (String budgetguid) {
		super.set("budgetguid",budgetguid);
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
	 * 申请人员
	 */
	@EntityLength(length = 50)
	public String getApplyuserguid() {
		return super.get("applyuserguid");
	}

	public void setApplyuserguid (String applyuserguid) {
		super.set("applyuserguid",applyuserguid);
	}

}