package com.jeecms.extend.dao.impl;

import java.util.List;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.extend.dao.ContentCheckRecordDao;
import com.jeecms.extend.entity.CmsDepartment;
import com.jeecms.extend.entity.ContentCheckRecord;

public class ContentCheckRecordDaoImpl extends
		HibernateBaseDao<ContentCheckRecord, Integer> implements
		ContentCheckRecordDao {

	@Override
	public ContentCheckRecord findById(Integer id) {
		ContentCheckRecord entity = get(id);
		return entity;
	}

	@Override
	public List<ContentCheckRecord> findByContentId(Integer id) {
		Finder finder = Finder.create("from ContentCheckRecord bean");
		finder.append(" where bean.content.id = :contentId");
		finder.setParam("contentId", id);
		finder.append(" order by bean.checkDate desc");
		return find(finder);
	}

	@Override
	public ContentCheckRecord save(ContentCheckRecord bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public int deleteByContentId(Integer contentId) {
		String hql = "delete from ContentCheckRecord bean where bean.content.id=:contentId";
		return getSession().createQuery(hql).setParameter("contentId",
				contentId).executeUpdate();
	}

	@Override
	protected Class<ContentCheckRecord> getEntityClass() {
		return ContentCheckRecord.class;
	}

}
