package com.jeecms.common.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParseUtil {
	private static String WIDTH_SIZE = "width:500px";
	private static String HEIGHT_SIZE = "height:375px";
	/**
	 * 从一段html中获取传入的标签的list
	 * @return List<String>
	 * @author zhengpp
	 * @date 2016年1月15日 下午4:31:11
	 */
	public static List<String> getTagList(String htmlStr,String tag){
		String tagReg = "<\\s*"+tag+"\\s+([^>]*)\\s*>";
		
		Pattern pattern = Pattern.compile(tagReg);
		Matcher matcher = pattern.matcher(htmlStr);
		List<String> tagList = new ArrayList<String>();
		while(matcher.find()){
			tagList.add(matcher.group());
		}
		return tagList;
	}
	/**
	 * @Description:从一段html中获取某个标签的某个属性list
	 * @return List<String>
	 * @author zhengpp
	 * @date 2016年1月15日 下午4:31:49
	 */
	public static List<String> getAttrByFromTag(String htmlStr,String tag,String attr){
		String attrReg = attr+"=\"([^\"]+)\"";
		List<String> tagList = getTagList(htmlStr,tag);
		List<String> attrList = new ArrayList<String>();
		Pattern pattern = Pattern.compile(attrReg);
		
		for(int i=0;i<tagList.size();i++){
			Matcher matcher = pattern.matcher(tagList.get(i));
			if(matcher.find()){
				attrList.add(matcher.group());
			}
		}
		return attrList;
	}
	/**
	 * 某个标签中是否有某个属性
	 * @Description:TODO
	 * @return boolean
	 * @author zhengpp
	 * @date 2016年1月15日 下午4:30:39
	 */
	public static boolean hasThisAttr(String tag,String attr){
		String attrReg = attr+"=\"([^\"]+)\"";
		Pattern pattern = Pattern.compile(attrReg);
		Matcher matcher = pattern.matcher(tag);
		if(matcher.find()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @Description:一段html中是否有某个标签
	 * @return boolean
	 * @author zhengpp
	 * @date 2016年1月15日 下午4:34:36
	 */
	public static boolean hasThisTag(String htmlStr,String tag){
		String tagReg = "<\\s*"+tag+"\\s+([^>]*)\\s*>";
		
		Pattern pattern = Pattern.compile(tagReg);
		Matcher matcher = pattern.matcher(htmlStr);
		if(matcher.find()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断一段style中是否含有某个属性
	 * @param @param 传入style字符串
	 * @param @param 属性字符串
	 * @return boolean
	 * @author zhengpp
	 * @date 2016年1月18日 上午10:16:37
	 */
	public static boolean styleHasThisAttr(String styleStr,String attr){
		return styleStr.toLowerCase().contains(attr);
		
	}
	/**
	 * 修改style中的width
	 * @param @param style字符串
	 * @param @return
	 * @return String
	 * @author zhengpp
	 * @date 2016年1月18日 上午10:19:23
	 */
	public static String replaceStyleWidth(String style){
		String widthReg = "width\\s*:\\s*\\d+(px||pt)";
		String maxWidthReg = "max-width\\s*:\\s*\\d+(px||pt)";
		String minWidthReg = "min-width\\s*:\\s*\\d+(px||pt)";
		if(style!=null&&(styleHasThisAttr(style,"max-width"))){
			return style.toLowerCase().replaceAll(maxWidthReg, WIDTH_SIZE);
		}
		if(style!=null&&(styleHasThisAttr(style,"min-width"))){
			return style.toLowerCase().replaceAll(minWidthReg, WIDTH_SIZE);
		}
		if(style!=null&&(styleHasThisAttr(style,"width"))){
			return style.toLowerCase().replaceAll(widthReg, WIDTH_SIZE);
		}
		
		if(style!=null&&!(styleHasThisAttr(style,"width"))&&!(styleHasThisAttr(style,"max-width"))&&!(styleHasThisAttr(style,"min-width"))){
			return WIDTH_SIZE+style;
		}
		return style;
	}
	/**
	 * 修改style中的height
	 * @param @param style字符串
	 * @param @return
	 * @return String
	 * @author zhengpp
	 * @date 2016年1月18日 上午10:20:07
	 */
	public static String replaceStyleHeight(String style){
		String heightReg = "height\\s*:\\s*\\d+(px||pt)";
		String maxHeightReg = "max-height\\s*:\\s*\\d+(px||pt)";
		String minHeightReg = "min-height\\s*:\\s*\\d+(px||pt)";
		
		if(style!=null&&styleHasThisAttr(style,"max-height")){
			return style.toLowerCase().replaceAll(maxHeightReg, HEIGHT_SIZE);
		}
		if(style!=null&&styleHasThisAttr(style,"min-height")){
			return style.toLowerCase().replaceAll(minHeightReg, HEIGHT_SIZE);
		}
		if(style!=null&&styleHasThisAttr(style,"height")){
			return style.toLowerCase().replaceAll(heightReg, HEIGHT_SIZE);
		}
		if(style!=null&&!styleHasThisAttr(style,"height")&&!styleHasThisAttr(style,"max-height")&&!styleHasThisAttr(style,"min-height")){
			return HEIGHT_SIZE+style;
		}
		return style;
	}
	/**
	 * 修改html中img标签的宽和高
	 * @param @param htmlStr
	 * @param @return
	 * @return String
	 * @author zhengpp
	 * @date 2016年1月18日 上午10:20:31
	 */
	public static String modifyImgSize(String htmlStr){
		if(!StringUtil.isBlank(htmlStr)){
			Document document = Jsoup.parse(htmlStr);
			Elements elements = document.getElementsByTag("img");
			
			for(Element ele : elements){
				String style = ele.attr("style");
				
				style=replaceStyleWidth(style);
				
				style = replaceStyleHeight(style);
				
				ele.attr("style", style);
			}
			return document.toString();
		}
		return null;
	}
	
	/**
	 * 格式化投稿文字字体为仿宋，26px
	 */
	public static String formatTxt(String txt){
		if(txt!=null){
			txt = txt.replaceAll("font-size:\\s*\\d{1,3}(px||em||pt)", "font-size: 26px");
			txt = txt.replaceAll("font-family:\\s*.+;", "font-family: '仿宋';");
			txt = "<div style=\"font-size:26px;font-family:'仿宋';\">"+txt+"</div>";
		}
		return txt;
	}
	
	public static String getFirstImg(String txt){
		if(!StringUtil.isBlank(txt)){
			Document document = Jsoup.parse(txt);
			Elements elements = document.getElementsByTag("img");
			String imgSrc = "";
			if(elements.size()>0){
				imgSrc = elements.get(0).attr("src");
				return imgSrc;
			}
		}
		return "";
	}
	
    //获取所有图片
	public static List<String> getAllImg(String txt){
		List<String> imgList = new ArrayList<String>();
		if(!StringUtil.isBlank(txt)){
			Document document = Jsoup.parse(txt);
			Elements elements = document.getElementsByTag("img");
			for (int i = 0; i < elements.size(); i++) {
				imgList.add(elements.get(i).attr("src"));
			}
			
		}
		return imgList;
	}
	//获取所有图片和图片宽度
	public static Map<String,Integer> getAllImgAndWidth(String txt){
		Map<String,Integer> imgWidth = new HashMap<String,Integer>();
		if(!StringUtil.isBlank(txt)){
			Document document = Jsoup.parse(txt);
			Elements elements = document.getElementsByTag("img");
			String width = "600";
			for (int i = 0; i < elements.size(); i++) {
				String style = elements.get(i).attr("style");
				if(style.contains("width")){
					style = style.substring(style.indexOf("width:")+7);
				    width = style.substring(0, style.indexOf("px"));
				}
				imgWidth.put(elements.get(i).attr("src"), Integer.parseInt(width));
			}
		}
		return imgWidth;
	}
}
