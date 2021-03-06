package com.jeecms.extend.dao;

import java.util.List;

import com.jeecms.extend.entity.ContentCheckRecord;

public interface ContentCheckRecordDao {

	public ContentCheckRecord findById(Integer id);
	
	public List<ContentCheckRecord> findByContentId(Integer id);
	
	public ContentCheckRecord save(ContentCheckRecord bean);

	public int deleteByContentId(Integer contentId);
	
}
