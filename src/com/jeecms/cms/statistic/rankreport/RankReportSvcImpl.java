package com.jeecms.cms.statistic.rankreport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.statistic.Rank;


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

	@Override
	public List<Rank> getYearRank(String pattern,boolean isPersonCount) {
		String[] patterns = pattern.split(",");
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String thisYear = format.format(new Date());
		List<RankReport> rankReportList = new ArrayList<RankReport>();
		rankReportList = rankReportDao.getYearRank(thisYear,patterns,isPersonCount);
		
		List<Rank> rankList = new ArrayList<Rank>();
		for(int i=0;i<rankReportList.size();i++){
			RankReport rankReport = rankReportList.get(i);
			Rank rank = new Rank();
			if(isPersonCount){
				rank.setCount(rankReport.getYearPer());
			}else{
				rank.setCount((float)rankReport.getYearTotal());
			}
			rank.setDepartmentId(rankReport.getDepartmentId());
			rank.setDepartmentName(rankReport.getDepartmentName());
			rankList.add(rank);
		}
		return rankList;
	}
}
