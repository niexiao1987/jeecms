package com.jeecms.cms.statistic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.statistic.CmsStatistic.CmsStatisticModel;

public interface CmsStatisticSvc {
	public List<CmsStatistic> statisticByModel(int type,
			CmsStatisticModel statisticModel, Integer year, Integer month,
			Integer day, Map<String, Object> restrictions);

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
			String contentStatus, String pattern);

	public long statisticRankTotalByPattern(Date beginDate, Date endDate,
			Integer channelId, Integer siteId, String contentStatus, String pattern);
}
