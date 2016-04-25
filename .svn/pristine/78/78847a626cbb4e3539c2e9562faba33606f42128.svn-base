package com.jeecms.extend.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.extend.dao.ContentCheckRecordDao;
import com.jeecms.extend.entity.ContentCheckRecord;
import com.jeecms.extend.manager.ContentCheckRecordMng;

public class ContentCheckRecordMngImpl implements ContentCheckRecordMng {

	@Override
	public ContentCheckRecord findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<ContentCheckRecord> findByContentId(Integer id) {
		return dao.findByContentId(id);
	}

	@Override
	public ContentCheckRecord save(ContentCheckRecord bean) {
		bean.init();
		return dao.save(bean);
	}

	@Override
	public int deleteByContentId(Integer contentId) {
		return dao.deleteByContentId(contentId);
	}

	private ContentCheckRecordDao dao;

	@Autowired
	public void setDao(ContentCheckRecordDao dao) {
		this.dao = dao;
	}

}
