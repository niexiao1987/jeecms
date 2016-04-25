package com.jeecms.cms.statistic.rankview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;

@Service
@Transactional
public class RankViewSvcImpl implements RankViewSvc{
	
	@Autowired
	private RankViewDao rankViewDao;

	@Override
	public RankView create(RankView rankView) {
		return rankViewDao.save(rankView);
		
	}
	
	@Override
	public Pagination getPageByQuery(Integer siteId, CmsUser user,
			int cpn, int pageSize,String inputTitle,String inputStatus,String inputPattern) {
		return rankViewDao.getPageByQuery(siteId,user,cpn,pageSize,inputTitle,inputStatus,inputPattern);
	}
	
	@Override
	public RankView findById(Integer id) {
		return rankViewDao.findById(id);
	}
	
	@Override
	public void update(RankView rankView) {
		rankViewDao.update(rankView);
		
	}

	@Override
	public void deleteById(Integer id) {
		rankViewDao.deleteById(id);
		
	}

	@Override
	public void delete(RankView rankView) {
		rankViewDao.delete(rankView);
		
	}

	@Override
	public Long getCountByStart(Integer id) {
		RankView rankView = new RankView();
		if(id!=null){
			rankView = rankViewDao.findById(id);
		}

		return rankViewDao.getCountByStart(rankView.getPattern());
	}

	@Override
	public RankView getStart(String pattern) {
		return rankViewDao.getStart(pattern);
	}

	@Override
	public List<RankView> getStartList() {
		return rankViewDao.getStartList();
	}

	
	
	

}
