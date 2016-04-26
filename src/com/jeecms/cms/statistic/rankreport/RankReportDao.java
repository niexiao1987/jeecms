package com.jeecms.cms.statistic.rankreport;

import java.util.List;

public interface RankReportDao {

	List<RankReport> getReportByYearAndPattern(String thisYear, String[] patterns);

	List<String> getYearList();

	List<RankReport> getYearRank(String thisYear, String[] pattern,
			boolean isPersonCount);

}
