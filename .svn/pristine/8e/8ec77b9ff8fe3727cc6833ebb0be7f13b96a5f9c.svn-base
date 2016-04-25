package com.jeecms.extend.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.extend.dao.CmsDepartmentExtDao;
import com.jeecms.extend.entity.CmsDepartment;
import com.jeecms.extend.entity.CmsDepartmentExt;
import com.jeecms.extend.manager.CmsDepartmentExtMng;

@Service
@Transactional
public class CmsDepartmentExtMngImpl implements CmsDepartmentExtMng {

	public CmsDepartmentExt save(CmsDepartmentExt ext,
			CmsDepartment department) {
		department.setDepartmentExt(ext);
		ext.setDepartment(department);
		ext.init();
		ext = dao.save(ext);
		return ext;
	}
	
	
	private CmsDepartmentExtDao dao;

	@Autowired
	public void setDao(CmsDepartmentExtDao dao) {
		this.dao = dao;
	}

	@Override
	public CmsDepartmentExt update(CmsDepartmentExt ext) {
		Updater<CmsDepartmentExt> updater = new Updater<CmsDepartmentExt>(ext);
		CmsDepartmentExt entity = dao.updateByUpdater(updater);
		return entity;
	}

}
