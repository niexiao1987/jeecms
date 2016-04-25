package com.jeecms.cms.statistic;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;

public class Rank implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String PERCENTSIGN = "%";
	public static final double COEFFICIENT = 0.8;
	
	//部门投稿统计
	private Integer departmentId;
	private String departmentName;
	

	//用户通告统计
	private Integer userId;
	private String userName;
	//数量统计
	private Float count;
	private Long total;
	

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}
	
	public String getPercent() {
		return NumberFormat.getPercentInstance().format(
				count / (total == 0 ? 1.0 : total + 0.0));
	}

	public String getBarWidth() {
		return (int) ((Integer.parseInt(getPercent().replace(PERCENTSIGN, ""))) * COEFFICIENT)
				+ PERCENTSIGN;
	}

	@Override
	public String toString() {
		return "Rank [departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", userId=" + userId + ", userName="
				+ userName + ", count=" + count + ", total=" + total + "]";
	}





	
}
