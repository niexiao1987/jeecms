package com.jeecms.extend.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.extend.dao.CmsDepartmentDao;
import com.jeecms.extend.entity.CmsDepartment;

@Repository
public class CmsDepartmentDaoImpl extends
		HibernateBaseDao<CmsDepartment, Integer> implements CmsDepartmentDao {

	@SuppressWarnings("unchecked")
	public List<CmsDepartment> getTopList() {
		Finder finder = getTopFinder();
		return find(finder);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CmsDepartment> getChildList(Integer parentId) {
		Finder finder = getChildFinder(parentId);
		return find(finder);
	}

	private Finder getTopFinder() {
		Finder finder = Finder.create("from CmsDepartment bean");
		finder.append(" where bean.parent.id is null");
		finder.append(" order by bean.priority asc,bean.id asc");
		return finder;
	}
	
	private Finder getChildFinder(Integer parentId) {
		Finder finder = Finder.create("from CmsDepartment bean");
		finder.append(" where bean.parent.id = :parentId");
		finder.setParam("parentId", parentId);
		finder.append(" order by bean.priority asc,bean.id asc");
		return finder;
	}

	@Override
	protected Class<CmsDepartment> getEntityClass() {
		return CmsDepartment.class;
	}

	@Override
	public CmsDepartment findById(Integer id) {
		CmsDepartment entity = get(id);
		return entity;
	}

	@Override
	public CmsDepartment save(CmsDepartment bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public CmsDepartment deleteById(Integer id) {
		CmsDepartment department = super.get(id);
		if (department != null) {
			getSession().delete(department);
		}
		return department;
	}

	@Override
	public List<CmsDepartment> getChushiList() {
		Finder finder = Finder.create("from CmsDepartment bean");
		finder.append(" where (bean.departmentExt.category=:chu");
		finder.setParam("chu", "chu");
		finder.append(" or bean.departmentExt.category=:shi");
		finder.setParam("shi", "shi");
		finder.append(")");
		return find(finder);
	}

	@Override
	public List<CmsDepartment> getXuyuanduiList() {
		Finder finder = Finder.create("from CmsDepartment bean");
		finder.append(" where bean.departmentExt.category=:xuyuandui");
		finder.setParam("xuyuandui", "dui");
		return find(finder);
	}
	/**
	 * 根据部门分类获取部门
	 */
	@Override
	public List<CmsDepartment> getListByCategory(String[] pattern) {
		Finder f = Finder.create("from CmsDepartment bean");
		if(pattern!=null&&pattern.length>0){
			f.append(" where (");
			for(int i = 0;i < pattern.length;i++){
				if(i==0){
					f.append(" bean.departmentExt.category =:pattern"+i);	
					f.setParam("pattern"+i, pattern[i]);
				}else{
					f.append(" or bean.departmentExt.category =:pattern"+i);
					f.setParam("pattern"+i, pattern[i]);
				}
				
			}
			f.append(" )");
		}
		return find(f);
	}
	
	@Override
	public long getListByCategorySum(String[] pattern) {
		Finder f = Finder.create("select  sum(bean.personCount)  FROM  CmsDepartment bean");
		if(pattern!=null&&pattern.length>0){
			f.append(" where (");
			for(int i = 0;i < pattern.length;i++){
				if(i==0){
					f.append(" bean.departmentExt.category =:pattern"+i);	
					f.setParam("pattern"+i, pattern[i]);
				}else{
					f.append(" or bean.departmentExt.category =:pattern"+i);
					f.setParam("pattern"+i, pattern[i]);
				}
				
			}
			f.append(" )");
		}
		return (long) find(f).iterator().next();
	}

}
