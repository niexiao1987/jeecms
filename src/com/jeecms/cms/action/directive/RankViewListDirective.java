package com.jeecms.cms.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_BEAN;
import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.statistic.rankview.RankView;
import com.jeecms.cms.statistic.rankview.RankViewSvc;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.extend.manager.CmsDepartmentMng;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 前台展示开启的排名
 * @className:RankViewListDirective
 * @Description:TODO
 * @author zhengpp
 * @date 2016年3月3日
 */
public class RankViewListDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException  {
		//获取已开启的排名展示列表，最多三个
		List<RankView> rankViewList = rankViewSvc.getStartList();
		//把数据返回标签
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(rankViewList));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	
	
	@Autowired
	private CmsDepartmentMng cmsDepartmentMng;
	@Autowired
	private RankViewSvc rankViewSvc;

}
