package com.jeecms.common.xml;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.jeecms.cms.entity.main.Content;

public class XMLUtil {
	
	public static  String ContentListToXml(List<Content> contentList){
		Document document = DocumentHelper.createDocument();
		for (int i = 0; i < contentList.size(); i++) {
			Content content = contentList.get(i);
			Element contentElement = document.addElement("content"+i); 
			Element contentId = contentElement.addElement("id");
			contentId.setData(content.getId());
			Element contentTitle = contentElement.addElement("title");
			contentTitle.setData(content.getTitle());
		}
		
		return document.getXMLEncoding();
	}
	
	public static void main(String[] args) {
		Content content = new Content();
		content.setId(4028);
		List<Content> contentList = new ArrayList<Content>();
		contentList.add(content);
		System.out.println(XMLUtil.ContentListToXml(contentList));
	}

}
