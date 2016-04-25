package com.jeecms.cms.statistic.rankreport;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class RankReportDaoImpl extends HibernateBaseDao<RankReport, Integer> implements
		RankReportDao {

	@Override
	protected Class<RankReport> getEntityClass() {
		return RankReport.class;
	}

	@Override
	public List<RankReport> getReportByYearAndPattern(String thisYear,
			String[] patterns) {
		Finder f = Finder.create(" FROM RankReport bean where 1=1 ");
		if(thisYear!=null){
			f.append(" and bean.year=:year ");
			f.setParam("year", thisYear);
		}
		if(patterns!=null&&patterns.length>0){
			f.append(" and (");
			for (int i = 0; i < patterns.length; i++) {
				if(i==0){
					f.append(" bean.departmentPattern =:pattern"+i);	
					f.setParam("pattern"+i, patterns[i]);
				}else{
					f.append(" or bean.departmentPattern =:pattern"+i);
					f.setParam("pattern"+i, patterns[i]);
				}
			}
			f.append(" )");
		}
		f.append(" order by bean.yearTotal desc");
		return find(f);
	}

	@Override
	public List<String> getYearList() {
		Finder f = Finder.create("select bean.year from RankReport bean where 1=1 ");
		f.append(" group by bean.year");
		return find(f);
	}

}
