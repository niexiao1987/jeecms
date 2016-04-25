package com.jeecms.extend.entity.base;

import java.io.Serializable;

/**
 * 部门扩展基类
 * @author 聂箫
 *
 */
public abstract class BaseCmsDepartmentExt implements Serializable {
	// constructors
	public BaseCmsDepartmentExt () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsDepartmentExt (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}
	
	protected void initialize () {}
	
	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.String description;
	//新增字段，机构类别  
	private String category;
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// one to one
	private com.jeecms.extend.entity.CmsDepartment department;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public com.jeecms.extend.entity.CmsDepartment getDepartment() {
		return department;
	}

	public void setDepartment(com.jeecms.extend.entity.CmsDepartment department) {
		this.department = department;
	}
	
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.extend.entity.CmsDepartmentExt)) return false;
		else {
			com.jeecms.extend.entity.CmsDepartmentExt departmentExt = (com.jeecms.extend.entity.CmsDepartmentExt) obj;
			if (null == this.getId() || null == departmentExt.getId()) return false;
			else return (this.getId().equals(departmentExt.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	@Override
	public String toString() {
		return "BaseCmsDepartmentExt [hashCode=" + hashCode + ", id=" + id
				+ ", name=" + name + ", description=" + description
				+ ", department=" + department + "]";
	}

}
