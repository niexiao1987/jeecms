package com.jeecms.cms.statistic.rankreport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RankReportSvcImpl implements RankReportSvc {
	@Autowired
	private RankReportDao rankReportDao;

	@Override
	public List<RankReport> getThisYearReport(String thisYear,String pattern) {
		String[] patterns = pattern.split(",");
		List<RankReport> rList = rankReportDao.getReportByYearAndPattern(thisYear,patterns);
		return rList;
	}

	@Override
	public List<String> getYearList() {
		return rankReportDao.getYearList();
	}
}
