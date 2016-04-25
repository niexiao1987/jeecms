package com.jeecms.extend.manager;

import java.util.List;

import com.jeecms.extend.entity.ContentCheckRecord;

public interface ContentCheckRecordMng {

	public ContentCheckRecord findById(Integer id);

	public List<ContentCheckRecord> findByContentId(Integer id);

	public ContentCheckRecord save(ContentCheckRecord bean);

	public int deleteByContentId(Integer contentId);
	
}
