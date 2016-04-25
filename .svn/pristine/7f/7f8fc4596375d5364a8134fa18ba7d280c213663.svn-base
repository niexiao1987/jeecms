package com.jeecms.extend.entity.base;

import java.io.Serializable;

public class BaseContentCheckRecord implements Serializable {
	
	private int hashCode = Integer.MIN_VALUE;
	
	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String checkOpinion;
	private java.lang.Byte checkStep;
	private java.lang.Boolean rejected;
	private java.util.Date checkDate;
	
	// many to one
	private com.jeecms.cms.entity.main.Content content;
	private com.jeecms.core.entity.CmsUser reviewer;
	
	
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion(java.lang.String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	public java.lang.Byte getCheckStep() {
		return checkStep;
	}

	public void setCheckStep(java.lang.Byte checkStep) {
		this.checkStep = checkStep;
	}

	public java.lang.Boolean getRejected() {
		return rejected;
	}

	public void setRejected(java.lang.Boolean rejected) {
		this.rejected = rejected;
	}

	public java.util.Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(java.util.Date checkDate) {
		this.checkDate = checkDate;
	}

	public com.jeecms.cms.entity.main.Content getContent() {
		return content;
	}

	public void setContent(com.jeecms.cms.entity.main.Content content) {
		this.content = content;
	}

	public com.jeecms.core.entity.CmsUser getReviewer() {
		return reviewer;
	}

	public void setReviewer(com.jeecms.core.entity.CmsUser reviewer) {
		this.reviewer = reviewer;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ContentCheck)) return false;
		else {
			com.jeecms.cms.entity.main.ContentCheck contentCheck = (com.jeecms.cms.entity.main.ContentCheck) obj;
			if (null == this.getId() || null == contentCheck.getId()) return false;
			else return (this.getId().equals(contentCheck.getId()));
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


	public String toString () {
		return super.toString();
	}
}
