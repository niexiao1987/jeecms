package com.jeecms.cms.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_BEAN;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.statistic.CmsStatisticSvc;
import com.jeecms.cms.statistic.Rank;
import com.jeecms.cms.statistic.rankreport.RankReportSvc;
import com.jeecms.cms.statistic.rankview.RankView;
import com.jeecms.cms.statistic.rankview.RankViewPattern;
import com.jeecms.cms.statistic.rankview.RankViewSvc;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.common.web.freemarker.ParamsRequiredException;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.extend.entity.CmsDepartment;
import com.jeecms.extend.manager.CmsDepartmentMng;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class RankViewDirective implements TemplateDirectiveModel {
	//输入参数，rankview 的id
	private static final String PRARM_id = "id";
	
	//输入参数，只根据rankview的id来获取，不选择后台设置为启用的
	private static final String PRARM_CLOSE_START = "close";
	//排名分类
	private static final String PRARM_PATTERN = "pattern";
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		RankView rankView;
		//获取close参数
		Boolean close = DirectiveUtils.getBool(PRARM_CLOSE_START, params);
		//获取id参数
		Integer id = DirectiveUtils.getInt(PRARM_id, params);
		//获取pattern参数
		String pattern = DirectiveUtils.getString(PRARM_PATTERN, params);
		if(pattern==null){
			//必须参数。没有则抛出异常
			throw new ParamsRequiredException(PRARM_PATTERN);
		}
		//如果为true，则根据rankview的id来查询
		List<Rank> rankList = new ArrayList<Rank>();
		if(id!=null&&close!=null&&close){
			rankView = rankViewSvc.findById(id);
			if(rankView!=null){
				rankList = getDisplayData(rankView);
			}else{
				//返回id错误
				throw new ParamsRequiredException(PRARM_id);
			}
		}else{
			rankView = rankViewSvc.getStart(pattern);
			//如果没有设置，获取模板中的id
			if(rankView==null){
				//返回错误，未设置启用
				throw new ParamsRequiredException("未设置启用的展示列表");
			}else{
				rankList = getDisplayDataThisYear(rankView);
			}
			
		}
		
		//把数据返回标签
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(rankList));
		if(rankView!=null){
			paramWrap.put(OUT_BEAN, DEFAULT_WRAPPER.wrap(rankView));
			
		}
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		
	}
	
	private List<Rank> getDisplayData(RankView rankView){
		List<Rank> rankList = new ArrayList<Rank>();
		List<Object[]> list = new ArrayList<Object[]>();
		list = cmsStatisticSvc.statisticRankByPattern(rankView.getViewCount(), rankView.getBeginDate(), rankView.getEndDate(), rankView.getChannel()==null? null:rankView.getChannel().getId(), rankView.getSite().getId(),"checked",rankView.getPattern());
		Map<Integer, Long> listMap = new HashMap<Integer, Long>();
		for (int i = 0; i < list.size(); i++) {
				listMap.put((Integer)list.get(i)[0], (Long)list.get(i)[1]);
		}
		long total = cmsStatisticSvc.statisticRankTotalByPattern(rankView.getBeginDate(), rankView.getEndDate(),rankView.getChannel()==null? null:rankView.getChannel().getId(), rankView.getSite().getId(),"checked",rankView.getPattern());
		
		CmsDepartment department = null;
		List<CmsDepartment> dList = new ArrayList<CmsDepartment>();
		if(!StringUtils.isBlank(rankView.getPattern())){
			dList = cmsDepartmentMng.getListByCategory(rankView.getPattern());
		}
		boolean isPersonCount = RankViewPattern.PERSONCOUNT.equals(rankView.getCategory());
		boolean isRankCount = RankViewPattern.RANKCOUNT.equals(rankView.getCategory());
	
		for (int i = 0; i < dList.size(); i++) {
			if (dList.get(i) != null) {
				Rank rank = new Rank();
				if(isRankCount){
					rank.setTotal(total);
				}
				department = dList.get(i);
				if (listMap.get(department.getId()) != null) {
					if(isPersonCount){
						DecimalFormat decimalFormat = new DecimalFormat("0.00");
						String count = decimalFormat.format(listMap.get(department.getId())/(double)department.getPersonCount());
						rank.setCount(Float.valueOf(count));
					}
					if(isRankCount){
						rank.setCount((float)listMap.get(department.getId()));
					}
				} else {
					rank.setCount(0f);
				}
				rank.setDepartmentId(department.getId());
				rank.setDepartmentName(department.getDepartmentExt().getName());
				rankList.add(rank);
			}
		}

		//对列表进行排序
		Collections.sort(rankList, new Comparator() {
			public int compare(Object a,Object b){
				if(a!=null&&b!=null&&(((Rank)b).getCount() - ((Rank)a).getCount())>0){
					return 1;
				}else{
					return -1;
				}
			}
		});
		if(rankView.getViewCount()<rankList.size()){
			rankList = rankList.subList(0, rankView.getViewCount());
		}
		return rankList;
	}
	
	private List<Rank> getDisplayDataThisYear(RankView rankView){
		List<Rank> rankList = new ArrayList<Rank>();
		boolean isPersonCount = RankViewPattern.PERSONCOUNT.equals(rankView.getCategory());
		//boolean isRankCount = RankViewPattern.RANKCOUNT.equals(rankView.getCategory());
		if(!StringUtils.isBlank(rankView.getPattern())){
			rankList = rankReportSvc.getYearRank(rankView.getPattern(),isPersonCount);
		}
		if(rankView.getViewCount()<rankList.size()){
			rankList = rankList.subList(0, rankView.getViewCount());
		}
		return rankList;
		
	}
	@Autowired
	private CmsDepartmentMng cmsDepartmentMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private RankViewSvc rankViewSvc;
	@Autowired
	private CmsStatisticSvc cmsStatisticSvc;
	@Autowired
	private RankReportSvc rankReportSvc;

}
