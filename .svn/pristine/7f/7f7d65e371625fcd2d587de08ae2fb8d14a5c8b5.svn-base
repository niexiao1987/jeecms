package com.jeecms.extend.entity;

import java.util.Date;

import com.jeecms.cms.entity.main.ContentCheck;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.extend.entity.base.BaseContentCheckRecord;

public class ContentCheckRecord extends BaseContentCheckRecord {

	/**
	 * 克隆内容审核记录
	 * 
	 * @param contentCheck
	 * @return
	 * author:聂箫
	 * date：2016年1月5日
	 */
	public static ContentCheckRecord cloneContentCheck(ContentCheck contentCheck,CmsUser currUser){
		ContentCheckRecord bean = new ContentCheckRecord();
		bean.setCheckStep(contentCheck.getCheckStep());
		bean.setCheckOpinion(contentCheck.getCheckOpinion());
		bean.setRejected(contentCheck.getRejected());
		bean.setCheckDate(contentCheck.getCheckDate());
		bean.setContent(contentCheck.getContent());
		bean.setReviewer(currUser);
		return bean;
	}
	
	public void init() {
		byte zero = 0;
		if (getCheckStep() == null) {
			setCheckStep(zero);
		}
		if (getRejected() == null) {
			setRejected(false);
		}
		if(getCheckDate()==null){
			setCheckDate(new Date());
		}
	}
}
