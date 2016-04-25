package com.jeecms.extend.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.extend.dao.CmsDepartmentExtDao;
import com.jeecms.extend.entity.CmsDepartmentExt;

@Repository
public class CmsDepartmentExtDaoImpl extends
		HibernateBaseDao<CmsDepartmentExt, Integer> implements
		CmsDepartmentExtDao {

	public CmsDepartmentExt save(CmsDepartmentExt bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<CmsDepartmentExt> getEntityClass() {
		return CmsDepartmentExt.class;
	}

}
