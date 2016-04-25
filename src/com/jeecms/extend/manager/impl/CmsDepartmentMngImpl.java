package com.jeecms.extend.manager.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.extend.dao.CmsDepartmentDao;
import com.jeecms.extend.entity.CmsDepartment;
import com.jeecms.extend.entity.CmsDepartmentExt;
import com.jeecms.extend.manager.CmsDepartmentExtMng;
import com.jeecms.extend.manager.CmsDepartmentMng;

@Service
@Transactional
public class CmsDepartmentMngImpl implements CmsDepartmentMng {

	@Override
	@Transactional(readOnly = true)
	public CmsDepartment findById(Integer id) {
		CmsDepartment entity = dao.findById(id);
		return entity;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CmsDepartment> getTopList() {
		return dao.getTopList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CmsDepartment> getChildList(Integer parentId) {
		return dao.getChildList(parentId);
	}

	public CmsDepartment save(CmsDepartment department, CmsDepartmentExt ext,Integer parentId) {
		if(parentId!=null){
			department.setParent(dao.findById(parentId));
		}
		department.init();
		dao.save(department);
		cmsDepartmentExtMng.save(ext, department);
		return department;
	}

	public CmsDepartment update(CmsDepartment department, CmsDepartmentExt ext,
			Integer parentId) {
		// 更新主表
		Updater<CmsDepartment> updater = new Updater<CmsDepartment>(department);
		department = dao.updateByUpdater(updater);
		// 更新父栏目
		CmsDepartment parent;
		if (parentId != null) {
			parent = findById(parentId);
		} else {
			parent = null;
		}
		department.setParent(parent);
		//更新扩展表
		cmsDepartmentExtMng.update(ext);
		return department;
	}

	private CmsDepartmentDao dao;
	private CmsDepartmentExtMng cmsDepartmentExtMng;

	@Autowired
	public void setDao(CmsDepartmentDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setCmsDepartmentExtMng(CmsDepartmentExtMng cmsDepartmentExtMng) {
		this.cmsDepartmentExtMng = cmsDepartmentExtMng;
	}

	@Override
	public CmsDepartment[] updatePriority(Integer[] ids, Integer[] priority) {
		int len = ids.length;
		CmsDepartment[] beans = new CmsDepartment[len];
		for (int i = 0; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	@Override
	public CmsDepartment[] deleteByIds(Integer[] ids) {
		CmsDepartment[] departments = new CmsDepartment[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			departments[i] = deleteById(ids[i]);
		}
		return departments;
	}

	private CmsDepartment deleteById(Integer id) {
		CmsDepartment department =dao.findById(id);
		department = dao.deleteById(id);
		return department;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CmsDepartment> getChushiList() {
		return dao.getChushiList();
	}

	@Override
	public List<CmsDepartment> getXuyuanduiList() {
		return dao.getXuyuanduiList();
	}

	@Override
	public List<CmsDepartment> getListByCategory(String pattern) {
		String[] patternArr = pattern.split(",");
		return dao.getListByCategory(patternArr);
	}
	
	@Override
	public long getListByCategorySum(String pattern) {
		String[] patternArr = pattern.split(",");
		return dao.getListByCategorySum(patternArr);
	}
	
	@Override
	public CmsDepartment[] updatePersonCount(Integer[] wids, Integer[] personCount) {
		int len = wids.length;
		CmsDepartment[] beans = new CmsDepartment[len];
		for (int i = 0; i < len; i++) {
			beans[i] = findById(wids[i]);
			beans[i].setPersonCount(personCount[i]);
		}
		return beans;
		
	}
}
