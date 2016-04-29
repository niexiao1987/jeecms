package com.jeecms.cms.statistic.rankreport;

import static com.jeecms.cms.Constants.TPLDIR_RANKREPORT;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class RankReportAct {
	public static final String RANK_REPORT = "tpl.rankReport";
	
	@RequestMapping("/rankReport/toReport.jspx")
	public String toReport(String pattern,String year,HttpServletRequest request,ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		if(StringUtils.isBlank(year)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
		    year = format.format(new Date());
			
		}
		List<RankReport> rList = rankReportSvc.getThisYearReport(year,pattern);

		model.addAttribute("reportList", rList);
		//获取所有年份，以供选择
		List<String> yearList = rankReportSvc.getYearList();
		model.addAttribute("yearList", yearList);
		model.addAttribute("thisYear", year);
		model.addAttribute("pattern", pattern);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_RANKREPORT, RANK_REPORT);
	}
	
	@Autowired
	private RankReportSvc rankReportSvc;
	
}
