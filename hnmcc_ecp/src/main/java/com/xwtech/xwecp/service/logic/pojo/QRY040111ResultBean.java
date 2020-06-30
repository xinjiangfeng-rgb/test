package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
/**
 * 流量不清零查询
 * 
 * @author wangjiajia
 * 2015-11-11
 */
public class QRY040111ResultBean extends BaseServiceInvocationResult
{

	/**
	 * 科目类别
	 */
	private String item_name;
	/**
	 * 科目Id
	 */
	private String item_id;
	/**
	 * 下个月是否可用
	 */
	private String can_use_next_month;
	/**
	 * 结转流量总量
	 */
	private String free_res;
	/**
	 * 结转流量已使用量
	 */
	private String free_res_used;
	/**
	 * 结转流量剩余流量
	 */
	private String free_res_remain;
	
	
	/**
	 * 流量单位
	 */
	private String unit_des;
	
	/**
	 * 套餐名称
	 */
	private String prod_name;

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getCan_use_next_month() {
		return can_use_next_month;
	}

	public void setCan_use_next_month(String can_use_next_month) {
		this.can_use_next_month = can_use_next_month;
	}

	public String getFree_res() {
		return free_res;
	}

	public void setFree_res(String free_res) {
		this.free_res = free_res;
	}

	public String getFree_res_used() {
		return free_res_used;
	}

	public void setFree_res_used(String free_res_used) {
		this.free_res_used = free_res_used;
	}

	public String getFree_res_remain() {
		return free_res_remain;
	}

	public void setFree_res_remain(String free_res_remain) {
		this.free_res_remain = free_res_remain;
	}

	public String getUnit_des() {
		return unit_des;
	}

	public void setUnit_des(String unit_des) {
		this.unit_des = unit_des;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	@Override
	public String toString() {
		return "QRY040111Result [item_name=" + item_name + ", item_id="
				+ item_id + ", can_use_next_month=" + can_use_next_month
				+ ", free_res=" + free_res + ", free_res_used=" + free_res_used
				+ ", free_res_remain=" + free_res_remain + ", unit_des="
				+ unit_des + ", prod_name=" + prod_name + "]";
	}
	
	
}