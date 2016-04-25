package com.jeecms.cms.statistic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.statistic.CmsStatistic.TimeRange;

public interface CmsStatisticDao {
	public long memberStatistic(TimeRange timeRange);

	public long contentStatistic(TimeRange timeRange,
			Map<String, Object> restrictions);

	public long commentStatistic(TimeRange timeRange,
			Map<String, Object> restrictions);

	public long guestbookStatistic(TimeRange timeRange,
			Map<String, Object> restrictions);

	public List<Object[]> statisticRank(Integer count, Date beginDate, Date endDate, Integer channelId,
			Integer siteId, Integer departmentId, String pattern, String contentStatus);

	public long statisticRankTotal(Date beginDate, Date endDate,
			Integer channelId, Integer siteId, Integer departmentId,String pattern, String contentStatus);

	public List<Object[]> getThisDepartmentCount(Date beginDate, Date endDate,
			Integer channelId, Integer siteId, Integer departmentId, String contentStatus);

	public long getThisDepartmentCountTotal(Date beginDate, Date endDate,
			Integer channelId, Integer siteId, Integer departmentId, String contentStatus);

	public List<Object[]> statisticRankByPattern(Integer viewCount,
			Date beginDate, Date endDate, Integer channelId, Integer siteId,
			String contentStatus, String[] pattern);

	public long statisticRankTotalByPattern(Date beginDate, Date endDate,
			Integer channelId, Integer siteId, String contentStatus,
			String[] pattern);

}