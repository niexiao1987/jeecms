package com.jeecms.cms.statistic;

import static com.jeecms.cms.statistic.CmsStatistic.SITEID;
import static com.jeecms.cms.statistic.CmsStatistic.ISREPLYED;
import static com.jeecms.cms.statistic.CmsStatistic.USERID;
import static com.jeecms.cms.statistic.CmsStatistic.CHANNELID;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.statistic.CmsStatistic.TimeRange;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateSimpleDao;

@Repository
public class CmsStatisticDaoImpl extends HibernateSimpleDao implements
		CmsStatisticDao {
	public long memberStatistic(TimeRange timeRange) {
		Finder f = createCacheableFinder("select count(*) from CmsUser bean where 1=1");
		if (timeRange != null) {
			f.append(" and bean.registerTime >= :begin");
			f.append(" and bean.registerTime < :end");
			f.setParam("begin", timeRange.getBegin());
			f.setParam("end", timeRange.getEnd());
		}
		return (Long) find(f).iterator().next();
	}

	public long contentStatistic(TimeRange timeRange,
			Map<String, Object> restrictions) {
		Finder f = createCacheableFinder("select count(*) from Content bean");
		Integer userId = (Integer) restrictions.get(USERID);
		Integer channelId = (Integer) restrictions.get(CHANNELID);
		if (channelId != null) {
			f.append(" join bean.channel channel,Channel parent");
			f.append(" where channel.lft between parent.lft and parent.rgt");
			f.append(" and channel.site.id=parent.site.id");
			f.append(" and parent.id=:parentId");
			f.setParam("parentId", channelId);
		} else {
			f.append(" where bean.site.id=:siteId").setParam("siteId",
					restrictions.get(SITEID));
		}
		if (timeRange != null) {
			f.append(" and bean.contentExt.releaseDate >= :begin");
			f.append(" and bean.contentExt.releaseDate < :end");
			f.setParam("begin", timeRange.getBegin());
			f.setParam("end", timeRange.getEnd());
		}
		if (userId != null) {
			f.append(" and bean.user.id=:userId").setParam("userId", userId);
		}
		return (Long) find(f).iterator().next();
	}

	public long commentStatistic(TimeRange timeRange,
			Map<String, Object> restrictions) {
		Finder f = createCacheableFinder("select count(*) from CmsComment bean where bean.site.id=:siteId");
		f.setParam("siteId", restrictions.get(SITEID));
		if (timeRange != null) {
			f.append(" and bean.createTime >= :begin");
			f.append(" and bean.createTime < :end");
			f.setParam("begin", timeRange.getBegin());
			f.setParam("end", timeRange.getEnd());
		}
		Boolean isReplyed = (Boolean) restrictions.get(ISREPLYED);
		if (isReplyed != null) {
			if (isReplyed) {
				f.append(" and bean.replayTime is not null");
			} else {
				f.append(" and bean.replayTime is null");
			}
		}
		return (Long) find(f).iterator().next();
	}

	public long guestbookStatistic(TimeRange timeRange,
			Map<String, Object> restrictions) {
		Finder f = createCacheableFinder("select count(*) from CmsGuestbook bean where bean.site.id=:siteId");
		f.setParam("siteId", restrictions.get(SITEID));
		if (timeRange != null) {
			f.append(" and bean.createTime >= :begin");
			f.append(" and bean.createTime < :end");
			f.setParam("begin", timeRange.getBegin());
			f.setParam("end", timeRange.getEnd());
		}
		Boolean isReplyed = (Boolean) restrictions.get(ISREPLYED);
		if (isReplyed != null) {
			if (isReplyed) {
				f.append(" and bean.replayTime is not null");
			} else {
				f.append(" and bean.replayTime is null");
			}
		}
		return (Long) find(f).iterator().next();
	}
	
	@Override
	public List<Object[]> statisticRank(Integer count,Date beginDate, Date endDate, Integer channelId,
			Integer siteId,Integer departmentId,String pattern,String contentStatus) {
		Finder f = createCacheableFinder("select bean.user.id, count(bean)  FROM Content bean   ");
		//如果是按照部门排名，先替换group by 表达式
		if("department".equals(pattern)){
			f=createCacheableFinder(f.getOrigHql().replace("bean.user.id", "p.id"));
			f.append(" join bean.department de,CmsDepartment p");
			if(channelId != null){
				f.append(" join bean.channel channel,Channel parent");
				f.append(" where channel.lft between parent.lft and parent.rgt");
				f.append(" and channel.site.id=parent.site.id");
				f.append(" and parent.id=:parentId");
				f.setParam("parentId", channelId);
				f.append(" and de.lft between p.lft and p.rgt");
			}else{
				f.append(" where de.lft between p.lft and p.rgt");
			}
			if(departmentId!=null){
				f.append(" and p.parent.id=:pid ");
				f.setParam("pid", departmentId);
			}else{
				f.append(" and p.parent.id is null");
			}
			
		}else if("user".equals(pattern)||StringUtils.isBlank(pattern)){
			if (channelId != null && departmentId != null) {
				f.append(" join bean.channel channel,Channel parent");
				f.append(" join bean.department de,CmsDepartment p");
				f.append(" where channel.lft between parent.lft and parent.rgt");
				f.append(" and channel.site.id=parent.site.id");
				f.append(" and parent.id=:parentId");
				f.setParam("parentId", channelId);
				f.append(" and de.lft between p.lft and p.rgt");
				f.append(" and p.id=:pid");
				f.setParam("pid", departmentId);
				
			}else if(channelId != null&&departmentId==null) {
				f.append(" join bean.channel channel,Channel parent");
				f.append(" where channel.lft between parent.lft and parent.rgt");
				f.append(" and channel.site.id=parent.site.id");
				f.append(" and parent.id=:parentId");
				f.setParam("parentId", channelId);
			}else if(departmentId != null&&channelId==null){
				f.append(" join bean.department de,CmsDepartment p");
				f.append(" where de.lft between p.lft and p.rgt");
				f.append(" and p.id=:pid");
				f.setParam("pid", departmentId);
			}else if (siteId != null) {
				f.append(" where bean.site.id=:siteId  ");
				f.setParam("siteId", siteId);
			} else {
				f.append(" where 1=1 ");
			}
		}
		if("checked".equals(contentStatus)){
			f.append(" and bean.status = 2 ");			
		}
		if(beginDate!=null){
			f.append(" and bean.contentExt.releaseDate >= :begin ");
			f.setParam("begin", beginDate);
		}
		if(endDate!=null){
			f.append(" and bean.contentExt.releaseDate < :end ");
			f.setParam("end", endDate);
		}
		if("user".equals(pattern)||StringUtils.isBlank(pattern)){
			f.append(" group by bean.user.id");
		}
		if("department".equals(pattern)){
				f.append(" group by p.id");
		}
		
		Query query = getSession().createQuery(f.getOrigHql());
		f.setParamsToQuery(query);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.list();
		//return find(f);
	}
	
	@Override
	public long statisticRankTotal(Date beginDate, Date endDate,
			Integer channelId, Integer siteId,Integer departmentId,String pattern,String contentStatus) {
		Finder f = Finder.create("select  count(bean)  FROM Content bean  ");
		if("department".equals(pattern)){
			f=createCacheableFinder(f.getOrigHql().replace("bean.user.id", "p.id"));
			f.append(" join bean.department de,CmsDepartment p");
			if(channelId != null){
				f.append(" join bean.channel channel,Channel parent");
				f.append(" where channel.lft between parent.lft and parent.rgt");
				f.append(" and channel.site.id=parent.site.id");
				f.append(" and parent.id=:parentId");
				f.setParam("parentId", channelId);
				f.append(" and de.lft between p.lft and p.rgt");
			}else{
				f.append(" where de.lft between p.lft and p.rgt");
			}
			if(departmentId!=null){
				f.append(" and p.parent.id=:pid ");
				f.setParam("pid", departmentId);
			}else{
				f.append(" and p.parent.id is null");
			}
			
			
		}else if("user".equals(pattern)||StringUtils.isBlank(pattern)){
			if (channelId != null && departmentId != null) {
				f.append(" join bean.channel channel,Channel parent");
				f.append(" join bean.department de,CmsDepartment p");
				f.append(" where channel.lft between parent.lft and parent.rgt");
				f.append(" and channel.site.id=parent.site.id");
				f.append(" and parent.id=:parentId");
				f.setParam("parentId", channelId);
				f.append(" and de.lft between p.lft and p.rgt");
				f.append(" and p.id=:pid");
				f.setParam("pid", departmentId);
				
			}else if(channelId != null&&departmentId==null) {
				f.append(" join bean.channel channel,Channel parent");
				f.append(" where channel.lft between parent.lft and parent.rgt");
				f.append(" and channel.site.id=parent.site.id");
				f.append(" and parent.id=:parentId");
				f.setParam("parentId", channelId);
			}else if(departmentId != null&&channelId==null){
				f.append(" join bean.department de,CmsDepartment p");
				f.append(" where de.lft between p.lft and p.rgt");
				f.append(" and p.id=:pid");
				f.setParam("pid", departmentId);
			}else if (siteId != null) {
				f.append(" where bean.site.id=:siteId  ");
				f.setParam("siteId", siteId);
			} else {
				f.append(" where 1=1 ");
			}
		}
		if("checked".equals(contentStatus)){
			f.append(" and bean.status = 2 ");			
		}
		if(beginDate!=null){
			f.append(" and bean.contentExt.releaseDate >= :begin ");
			f.setParam("begin", beginDate);
		}
		if(endDate!=null){
			f.append(" and bean.contentExt.releaseDate < :end ");
			f.setParam("end", endDate);
		}
		
		return (long) find(f).iterator().next();
	}
	
	@Override
	public List<Object[]> getThisDepartmentCount(Date beginDate, Date endDate,
			Integer channelId, Integer siteId, Integer departmentId,String contentStatus ) {
		Finder f = createCacheableFinder("select bean.department.id, count(bean)  FROM Content bean   ");
		if(channelId != null){
			f.append(" join bean.channel channel,Channel parent");
			f.append(" where channel.lft between parent.lft and parent.rgt");
			f.append(" and channel.site.id=parent.site.id");
			f.append(" and parent.id=:parentId");
			f.setParam("parentId", channelId);
			f.append(" and bean.department.id=:pid");
			f.setParam("pid", departmentId);
		}else{
			f.append(" where bean.department.id=:pid");
			f.setParam("pid", departmentId);
		}
		if("checked".equals(contentStatus)){
			f.append(" and bean.status = 2 ");			
		}
		if(beginDate!=null){
			f.append(" and bean.contentExt.releaseDate >= :begin ");
			f.setParam("begin", beginDate);
		}
		if(endDate!=null){
			f.append(" and bean.contentExt.releaseDate < :end ");
			f.setParam("end", endDate);
		}
		f.append(" group by bean.department.id");
		return find(f);
	}
	
	public long getThisDepartmentCountTotal(Date beginDate, Date endDate,
			Integer channelId, Integer siteId, Integer departmentId,String contentStatus) {
		Finder f = createCacheableFinder("select  count(bean)  FROM Content bean   ");
		if(channelId != null){
			f.append(" join bean.channel channel,Channel parent");
			f.append(" where channel.lft between parent.lft and parent.rgt");
			f.append(" and channel.site.id=parent.site.id");
			f.append(" and parent.id=:parentId");
			f.setParam("parentId", channelId);
			f.append(" and bean.department.id=:pid");
			f.setParam("pid", departmentId);
		}else{
			f.append(" where bean.department.id=:pid");
			f.setParam("pid", departmentId);
		}
		if("checked".equals(contentStatus)){
			f.append(" and bean.status = 2 ");			
		}
		if(beginDate!=null){
			f.append(" and bean.contentExt.releaseDate >= :begin ");
			f.setParam("begin", beginDate);
		}
		if(endDate!=null){
			f.append(" and bean.contentExt.releaseDate < :end ");
			f.setParam("end", endDate);
		}
		
		return (long) find(f).iterator().next();
	}
	
	@Override
	public List<Object[]> statisticRankByPattern(Integer viewCount,
			Date beginDate, Date endDate, Integer channelId, Integer siteId,
			String contentStatus, String[] pattern) {
		Finder f = createCacheableFinder("select p.id, count(bean)  FROM Content bean   ");
		f.append(" join bean.department de,CmsDepartment p");
		if (channelId != null) {
			f.append(" join bean.channel channel,Channel parent");
			f.append(" where channel.lft between parent.lft and parent.rgt");
			f.append(" and channel.site.id=parent.site.id");
			f.append(" and parent.id=:parentId");
			f.setParam("parentId", channelId);
			f.append(" and de.lft between p.lft and p.rgt");
		} else {
			f.append(" where de.lft between p.lft and p.rgt");
		}
		if(pattern!=null&&pattern.length>0){
			f.append(" and (");
			for(int i = 0;i < pattern.length;i++){
				if(i==0){
					f.append(" p.departmentExt.category =:pattern"+i);	
					f.setParam("pattern"+i, pattern[i]);
				}else{
					f.append(" or p.departmentExt.category =:pattern"+i);
					f.setParam("pattern"+i, pattern[i]);
				}
				
			}
			f.append(" )");
		}
		if("checked".equals(contentStatus)){
			f.append(" and bean.status = 2 ");			
		}
		if(beginDate!=null){
			f.append(" and bean.contentExt.releaseDate >= :begin ");
			f.setParam("begin", beginDate);
		}
		if(endDate!=null){
			f.append(" and bean.contentExt.releaseDate < :end ");
			f.setParam("end", endDate);
		}
		//选择可以计入投稿统计的栏目
		f.append("and bean.channel.canRank = :canRank");
		f.setParam("canRank", true);
		
		f.append(" group by p.id");	
		Query query = getSession().createQuery(f.getOrigHql());
		f.setParamsToQuery(query);
		query.setFirstResult(0);
		query.setMaxResults(viewCount);
		return query.list();
	}

	@Override
	public long statisticRankTotalByPattern(Date beginDate, Date endDate,
			Integer channelId, Integer siteId, String contentStatus,
			String[] pattern) {
		Finder f = createCacheableFinder("select  count(bean)  FROM Content bean   ");
		f.append(" join bean.department de,CmsDepartment p");
		if (channelId != null) {
			f.append(" join bean.channel channel,Channel parent");
			f.append(" where channel.lft between parent.lft and parent.rgt");
			f.append(" and channel.site.id=parent.site.id");
			f.append(" and parent.id=:parentId");
			f.setParam("parentId", channelId);
			f.append(" and de.lft between p.lft and p.rgt");
		} else {
			f.append(" where de.lft between p.lft and p.rgt");
		}
		if(pattern!=null&&pattern.length>0){
			f.append(" and (");
			for(int i = 0;i < pattern.length;i++){
				if(i==0){
					f.append(" p.departmentExt.category =:pattern"+i);	
					f.setParam("pattern"+i, pattern[i]);
				}else{
					f.append(" or p.departmentExt.category =:pattern"+i);
					f.setParam("pattern"+i, pattern[i]);
				}
				
			}
			f.append(" )");
		}
		if("checked".equals(contentStatus)){
			f.append(" and bean.status = 2 ");			
		}
		if(beginDate!=null){
			f.append(" and bean.contentExt.releaseDate >= :begin ");
			f.setParam("begin", beginDate);
		}
		if(endDate!=null){
			f.append(" and bean.contentExt.releaseDate < :end ");
			f.setParam("end", endDate);
		}
		//选择可以计入投稿统计的栏目
		f.append("and bean.channel.canRank = :canRank");
		f.setParam("canRank", true);
		return (long) find(f).iterator().next();
	}
	
	
	private Finder createCacheableFinder(String hql) {
		Finder finder = Finder.create(hql);
		finder.setCacheable(true);
		return finder;
	}

	





	



}