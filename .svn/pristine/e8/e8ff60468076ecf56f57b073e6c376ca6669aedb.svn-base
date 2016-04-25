package com.jeecms.cms.statistic.rankview;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;

public interface RankViewDao {

	RankView save(RankView rankView);

	Pagination getPageByQuery(Integer siteId, CmsUser user, int cpn, int pageSize, String inputTitle, String inputStatus,String inputPattern);

	RankView findById(Integer id);

	void update(RankView rankView);

	void deleteById(Integer id);

	void delete(RankView rankView);

	Long getCountByStart(String string);

	RankView getStart(String pattern);

	List<RankView> getStartList();

}
