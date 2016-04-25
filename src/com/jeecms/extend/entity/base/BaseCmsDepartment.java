package com.jeecms.extend.entity.base;

import java.io.Serializable;
import java.util.Set;

import com.jeecms.cms.entity.main.Content;

/**
 * 部门基类
 * 
 * @author 聂箫
 *
 */
public abstract class BaseCmsDepartment implements Serializable {

	// constructors
	public BaseCmsDepartment() {
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer lft;
	private java.lang.Integer rgt;
	private java.lang.Integer priority;
	private java.lang.Boolean display;
	//部门人数
	private java.lang.Integer personCount;
	// one to one
	private com.jeecms.extend.entity.CmsDepartmentExt departmentExt;

	// many to one
	private com.jeecms.extend.entity.CmsDepartment parent;
	

	// collections
	private java.util.Set<com.jeecms.extend.entity.CmsDepartment> child;
	private java.util.Set<com.jeecms.core.entity.CmsUser> users;
	
	private Set<Content> contents;
	
	
	
	

	public java.lang.Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(java.lang.Integer personCount) {
		this.personCount = personCount;
	}

	public Set<Content> getContents() {
		return contents;
	}

	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public java.lang.Integer getLft() {
		return lft;
	}

	public void setLft(java.lang.Integer lft) {
		this.lft = lft;
	}

	public java.lang.Integer getRgt() {
		return rgt;
	}

	public void setRgt(java.lang.Integer rgt) {
		this.rgt = rgt;
	}

	public java.lang.Integer getPriority() {
		return priority;
	}

	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}

	public java.lang.Boolean getDisplay() {
		return display;
	}

	public void setDisplay(java.lang.Boolean display) {
		this.display = display;
	}

	public com.jeecms.extend.entity.CmsDepartmentExt getDepartmentExt() {
		return departmentExt;
	}

	public void setDepartmentExt(
			com.jeecms.extend.entity.CmsDepartmentExt departmentExt) {
		this.departmentExt = departmentExt;
	}

	public com.jeecms.extend.entity.CmsDepartment getParent() {
		return parent;
	}

	public void setParent(com.jeecms.extend.entity.CmsDepartment parent) {
		this.parent = parent;
	}

	public java.util.Set<com.jeecms.extend.entity.CmsDepartment> getChild() {
		return child;
	}

	public void setChild(
			java.util.Set<com.jeecms.extend.entity.CmsDepartment> child) {
		this.child = child;
	}

	public java.util.Set<com.jeecms.core.entity.CmsUser> getUsers() {
		return users;
	}

	public void setUsers(java.util.Set<com.jeecms.core.entity.CmsUser> users) {
		this.users = users;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.jeecms.extend.entity.CmsDepartment))
			return false;
		else {
			com.jeecms.extend.entity.CmsDepartment department = (com.jeecms.extend.entity.CmsDepartment) obj;
			if (null == this.getId() || null == department.getId())
				return false;
			else
				return (this.getId().equals(department.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	@Override
	public String toString() {
		return "BaseCmsDepartment [hashCode=" + hashCode + ", id=" + id
				+ ", lft=" + lft + ", rgt=" + rgt + ", priority=" + priority
				+ ", display=" + display + ", departmentExt=" + departmentExt
				+ ", parent=" + parent + ", child=" + child + "]";
	}

}
