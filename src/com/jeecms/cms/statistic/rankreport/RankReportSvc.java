package com.jeecms.cms.statistic.rankreport;

import java.util.List;

<<<<<<< HEAD
public interface RankReportSvc {

	List<RankReport> getThisYearReport(String pattern, String pattern2);

	List<String> getYearList();
=======
import com.jeecms.cms.statistic.Rank;

public interface RankReportSvc {

	List<RankReport> getThisYearReport(String pattern, String pattern2);

	List<String> getYearList();

	List<Rank> getYearRank(String pattern, boolean isPersonCount);
>>>>>>> refs/remotes/origin/develop

}
