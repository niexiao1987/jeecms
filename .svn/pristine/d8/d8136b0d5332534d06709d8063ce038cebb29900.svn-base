package com.jeecms.cms.statistic.rankview;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
@Repository
public class RankViewDaoImpl extends HibernateBaseDao<RankView, Integer> implements RankViewDao {
	@Override
	public RankView findById(Integer id) {
		return  get(id);
	}

	@Override
	protected Class<RankView> getEntityClass() {
		return RankView.class;
	}
	
	public RankView save(RankView rankView){
		rankView.setStatus(false);
		getSession().save(rankView);
		return rankView;
	}
	
	public Pagination getPageByQuery(Integer siteId, CmsUser user,
			 int cpn, int pageSize,String inputTile,String inputStatus,String inputPattern) {
		String hql = "FROM RankView bean WHERE 1=1 ";
		Finder finder = Finder.create(hql);
		if(siteId>0){
			finder.append(" and bean.site.id = :siteId ");
			finder.setParam("siteId", siteId);
		}
		if(!"admin".equals(user.getUsername())){
			finder.append(" and bean.user.id = :userId");
			finder.setParam("userId", user.getId());
		}
		if(!StringUtils.isBlank(inputTile)){
			finder.append(" and bean.title like :title ");
			finder.setParam("title", "%"+inputTile+"%");
		}
		if(!"all".equals(inputStatus)){
			if("true".equals(inputStatus)){
				finder.append(" and bean.status=:status ");
				finder.setParam("status", true);
			}
			if("false".equals(inputStatus)){
				finder.append(" and bean.status=:status ");
				finder.setParam("status", false);
			}
			
		}
		if(!StringUtils.isBlank(inputPattern)){
			finder.append(" and bean.pattern=:pattern");
			finder.setParam("pattern", inputPattern);
		}
		
		finder.append(" order by bean.createTime desc ");
		
		
		Pagination p = find(finder, cpn, pageSize);
		return p;
		
	}
	
	

	@Override
	public void update(RankView rankView) {
		getSession().saveOrUpdate(rankView);
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}

	@Override
	public void delete(RankView rankView) {
		getSession().delete(rankView);
		
	}
	/**
	 * 获得开启的排名展示列表
	 */
	@Override
	public List<RankView> getStartList() {
		Finder finder = Finder.create("FROM RankView bean where 1=1 ");
		finder.append(" and bean.status = :status");
		finder.setParam("status", true);
		finder.append(" order by bean.pattern");
		return find(finder);
	}
	/**
	 * 获取已经开启的个数，如果为0则表示可以开启
	 */
	@Override
	public Long getCountByStart(String pattern) {
		Finder finder = Finder.create("select count(bean) from RankView bean where 1=1 ");
		if(pattern!=null){
			finder.append(" and bean.pattern=:pattern");
			finder.setParam("pattern", pattern);
		}
		finder.append(" and bean.status = :status ");
		finder.setParam("status", true);
		
		return (Long) find(finder).iterator().next();
	}
	/**
	 * 根据pattern获取已经开启的排名展示
	 */
	@Override
	public RankView getStart(String pattern) {
		//先判断是否有开启的,未开启返回null
		if(getCountByStart(pattern).intValue()<1){
			return null;
		}
		Finder finder = Finder.create(" FROM RankView bean where bean.status = :status ");
		finder.setParam("status", true);
		if(pattern!=null){
			finder.append(" and bean.pattern = :pattern ");
			finder.setParam("pattern", pattern);
		}
		return (RankView) find(finder).get(0);
	}
	

	

	
	
}
