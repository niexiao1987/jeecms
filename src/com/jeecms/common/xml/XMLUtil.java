package com.jeecms.common.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentExt;
import com.jeecms.cms.entity.main.ContentTxt;
import com.jeecms.cms.manager.assist.CmsFileMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.CmsModelItemMng;
import com.jeecms.cms.manager.main.CmsModelMng;
import com.jeecms.cms.manager.main.CmsTopicMng;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.cms.manager.main.ContentTypeMng;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.extend.manager.CmsDepartmentMng;
import com.jeecms.extend.manager.ContentCheckRecordMng;

public class XMLUtil {
	public static final String EXPORTPATH =File.separator+"u"+File.separator+"cms"+File.separator+"www"+File.separator+"export";
	public static final String ATTACHMENTPATH = File.separator+"u"+File.separator+"cms"+File.separator+"www";
	public static  Document ContentListToXml(List<Content> contentList){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("contentXmlExport"); 
		for (int i = 0; i < contentList.size(); i++) {
			Content content = contentList.get(i); 
			Element contentXmlElement = root.addElement("contentXml");
			Element contentElement = contentXmlElement.addElement("content");
			contentElement.setAttributeValue("id", String.valueOf(content.getId()));
			contentElement.addElement("id").addText(String.valueOf(content.getId()));
			contentElement.addElement("channelId").addText(String.valueOf(content.getChannel().getId()));
			contentElement.addElement("userId").addText(String.valueOf(content.getUser().getId()));
			contentElement.addElement("typeId").addText(String.valueOf(content.getType().getId()));
			contentElement.addElement("modelId").addText(content.getModel().getId()+"");
			contentElement.addElement("siteId").addText(String.valueOf(content.getSite().getId()));
			contentElement.addElement("sortDate").addText(String.valueOf(content.getSortDate().getTime()));
			contentElement.addElement("topLevel").addText(String.valueOf(content.getTopLevel()));
			contentElement.addElement("hasTitleImg").addText(String.valueOf("".equals(content.getContentImg())?0:1 ));
			contentElement.addElement("recommend").addText(String.valueOf(content.getSite().getId()));
			contentElement.addElement("status").addText(String.valueOf(content.getStatus()));
			contentElement.addElement("viewsDay").addText(String.valueOf(content.getViewsDay()));
			contentElement.addElement("commentsDay").addText(String.valueOf(content.getCommentsDay()));
			contentElement.addElement("downloadsDay").addText(String.valueOf(content.getDownloadsDay()));
			contentElement.addElement("upsDay").addText(String.valueOf(content.getUpsDay()));
			contentElement.addElement("score").addText(String.valueOf(content.getUpsDay()));
			contentElement.addElement("departmentId").addText(String.valueOf(content.getDepartment().getId()));
			//设置contentExt
			Element contentExt = contentXmlElement.addElement("contentExt");
			ContentExt contentExtBase = content.getContentExt();
			if(contentExtBase!=null){
				contentExt.setAttributeValue("id", String.valueOf(contentExtBase.getId()));
				contentExt.addElement("id").addText(String.valueOf(contentExtBase.getId()));
				contentExt.addElement("title").addText(String.valueOf(contentExtBase.getTitle()));
				contentExt.addElement("shortTitle").addText(String.valueOf(contentExtBase.getShortTitle()));
				contentExt.addElement("description").addText(String.valueOf(contentExtBase.getDescription()));
				contentExt.addElement("author").addText(String.valueOf(contentExtBase.getAuthor()));
				contentExt.addElement("origin").addText(String.valueOf(contentExtBase.getOrigin()));
				contentExt.addElement("originUrl").addText(String.valueOf(contentExtBase.getOriginUrl()));
				contentExt.addElement("releaseDate").addText(String.valueOf(contentExtBase.getReleaseDate().getTime()));
				contentExt.addElement("mediaPath").addText(String.valueOf(contentExtBase.getMediaPath()));
				contentExt.addElement("mediaType").addText(String.valueOf(contentExtBase.getMediaType()));
				contentExt.addElement("titleColor").addText(String.valueOf(contentExtBase.getTitleColor()));
				contentExt.addElement("bold").addText(String.valueOf(contentExtBase.getBold()));
				contentExt.addElement("titleImg").addText(String.valueOf(contentExtBase.getTitleImg()));
				contentExt.addElement("contentImg").addText(String.valueOf(contentExtBase.getContentImg()));
				contentExt.addElement("typeImg").addText(String.valueOf(contentExtBase.getTypeImg()));
				contentExt.addElement("link").addText(String.valueOf(contentExtBase.getLink()));
				contentExt.addElement("tplContent").addText(String.valueOf(contentExtBase.getTplContent()));
				contentExt.addElement("needRegenerate").addText(String.valueOf(contentExtBase.getNeedRegenerate()));
				contentExt.addElement("editor").addText(String.valueOf(contentExtBase.getEditor()));
				contentExt.addElement("approver").addText(String.valueOf(contentExtBase.getApprover()));
			}
			
			Element contentTxt = contentXmlElement.addElement("contentTxt");
			ContentTxt contentTxtBase = content.getContentTxt();
			if(contentTxtBase!=null){
				contentTxt.setAttributeValue("id", String.valueOf(contentTxtBase.getId()));
				contentTxt.addElement("id").addText(String.valueOf(contentTxtBase.getId()));
				contentTxt.addElement("txt").addText(String.valueOf(contentTxtBase.getTxt()));
				contentTxt.addElement("txt1").addText(String.valueOf(contentTxtBase.getTxt1()));
				contentTxt.addElement("txt2").addText(String.valueOf(contentTxtBase.getTxt2()));
				contentTxt.addElement("txt3").addText(String.valueOf(contentTxtBase.getTxt3()));
			}
			
			//副栏目
			Element channelIdsElement = contentXmlElement.addElement("channelIds");
			Integer[] channelIds = content.getChannelIds();
			if(channelIds!=null){
				for(int j=0;j<channelIds.length;j++){
					channelIdsElement.addElement("channelId").addText(String.valueOf(channelIds[j]));
				}
			}
			//专题
			Element topicIdsElement = contentXmlElement.addElement("topicIds");
			Integer[] topicIds = content.getTopicIds();
			if(topicIds!=null){
				for(int j=0;j<topicIds.length;j++){
					topicIdsElement.addElement("topicId").addText(String.valueOf(topicIds[j]));
				}
			}
			//浏览会员组
			Element viewGroupIdsElement = contentXmlElement.addElement("viewGroupIds");
			Integer[] viewGroupIds = content.getViewGroupIds();
			if(viewGroupIds!=null){
				for(int j=0;j<viewGroupIds.length;j++){
					viewGroupIdsElement.addElement("viewGroupId").addText(String.valueOf(viewGroupIds[j]));
				}
			}
			//标签
			Element tagsElement = contentXmlElement.addElement("tags");
			String[] tags = content.getTagArray();
			if(tags!=null){
				for(int j=0;j<tags.length;j++){
					tagsElement.addElement("tag").setText(String.valueOf(tags[j]));
				}
			}
			//附件
			Element attachmentPathsElement = contentXmlElement.addElement("attachmentPaths");
			String[] attachmentPaths = content.getAttachmentPaths();
			if(attachmentPaths!=null){
				for(int j=0;j<attachmentPaths.length;j++){
					attachmentPathsElement.addElement("attachmentPath").addText(String.valueOf(attachmentPaths[j]));
				}
			}
			Element attachmentNamesElement = contentXmlElement.addElement("attachmentNames");
			String[] attachmentNames = content.getAttachmentNames();
			if(attachmentNames!=null){
				for(int j=0;j<attachmentNames.length;j++){
					attachmentNamesElement.addElement("attachmentName").addText(String.valueOf(attachmentNames[j]));
				}
			}
			Element attachmentFileNamesElement = contentXmlElement.addElement("attachmentFileNames");
			String[] attachmentFileNames = content.getAttachmentFileNames();
			if(attachmentFileNames!=null){
				for(int j=0;j<attachmentFileNames.length;j++){
					attachmentFileNamesElement.addElement("attachmentFileName").addText(String.valueOf(attachmentFileNames[j]));
				}
			}
			//图片集
			Element picPathsElement = contentXmlElement.addElement("picPaths");
			String[] picPaths = content.getPicPaths();
			if(picPaths!=null){
				for(int j = 0;j<picPaths.length;j++){
					picPathsElement.addElement("picPath").addText(String.valueOf(picPaths[j]));
				}
			}
			Element picDescsElement = contentXmlElement.addElement("picDescs");
			String[] picDescs = content.getPicDescs();
			if(picDescs!=null){
				for(int j=0;j<picDescs.length;j++){
					picDescsElement.addElement("picDesc").addText(String.valueOf(picDescs[j]));
				}
			}
			//设置user的Step大于FinalStep，就审核了
			Element finalStepElement = contentXmlElement.addElement("finalStep");
			Byte finalStep = content.getChannel().getFinalStepExtends();
			if(finalStep!=null){
				finalStepElement.addElement("step").setText(finalStep+1+"");
			}
		}
		
		return document;
	}
	
	
	public static String xmlToContent(String xmlFileUrl,CmsUserMng userMng,CmsModelMng modelMng,CmsSiteMng siteMng,CmsDepartmentMng cmsDepartmentMng,ContentMng manager) throws UnsupportedEncodingException{
		SAXReader read = new SAXReader();
		
		try {
			Document doc = read.read(new String(xmlFileUrl.getBytes("iso8859-1"), "UTF-8"));
			Element root = doc.getRootElement();
			Iterator<Element> contentXmlList = root.elementIterator();
			while(contentXmlList.hasNext()){
				Element contentXml = contentXmlList.next();
				Iterator<Element>  baseContentList = contentXml.elementIterator();
				Content content = new Content();
				ContentExt contentExt = new ContentExt();
				ContentTxt contentTxt = new ContentTxt();
				Integer channelId = null;
				Integer typeId = null;
				CmsUser user = null;
				Integer[] channelIds=null;
				String topicIdsStr = null;
				Integer[] topicIds = null;
				String viewGroupIdsStr = null;
				Integer[] viewGroupIds = null;
				String tagArrStr = null;
				String[] tagArr = null;
				String attachmentPathsStr = null;
				String[] attachmentPaths = null;
				String attachmentNamesStr = null;
				String[] attachmentNames = null;
				String attachmentFilenamesStr = null;
				String[] attachmentFilenames = null;
				String picPathsStr = null;
				String[] picPaths = null;
				String picDescsStr = null;
				String[] picDescs = null;
				
				Boolean draft = null;
				while(baseContentList.hasNext()){
					Element baseContent = baseContentList.next();
					
					if("content".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							switch(fieldName.getName()){
								case "channelId" : channelId = Integer.parseInt(fieldName.getText()); break;
								case "userId" : user = userMng.findById(Integer.parseInt(fieldName.getText()));break;
								case "typeId" : typeId = Integer.parseInt(fieldName.getText());break;
								case "modelId" : content.setModel(modelMng.findById(Integer.parseInt(fieldName.getText())));break;
								case "siteId" : content.setSite(siteMng.findById(Integer.parseInt(fieldName.getText())));break;
								case "sortDate" : content.setSortDate(new Date(Long.parseLong(fieldName.getText())));break;
								case "topLevel" : content.setTopLevel(Byte.parseByte(fieldName.getText()));break;
								case "hasTitleImg" : content.setHasTitleImg(Boolean.parseBoolean(fieldName.getText()));break;
								case "recommend" : content.setRecommend(Boolean.parseBoolean(fieldName.getText()));break;
								case "status" : content.setStatus(Byte.parseByte(fieldName.getText()));break;
								case "viewsDay" : content.setViewsDay(Integer.parseInt(fieldName.getText()));break;
								case "commentsDay" : content.setCommentsDay(Short.parseShort(fieldName.getText()));break;
								case "downloadsDay" : content.setDownloadsDay(Short.parseShort(fieldName.getText()));break;
								case "upsDay" : content.setUpsDay(Short.parseShort(fieldName.getText()));break;
								case "score" : content.setScore(Integer.parseInt(fieldName.getText()));break;
								case "departmentId" : content.setDepartment(cmsDepartmentMng.findById(Integer.parseInt(fieldName.getText())));break;
							}
						}
						
					}
					if("contentExt".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							switch(fieldName.getName()){
								case "title" : contentExt.setTitle(covertNull(fieldName.getText()));break;
								case "shortTitle" : contentExt.setShortTitle(covertNull(fieldName.getText()));break;
								case "description" : contentExt.setDescription(covertNull(fieldName.getText()));break;
								case "author" : contentExt.setAuthor(covertNull(fieldName.getText()));break;
								case "origin" : contentExt.setOrigin(covertNull(fieldName.getText()));break;
								case "originUrl" : contentExt.setOriginUrl(covertNull(fieldName.getText()));break;
								case "releaseDate" : contentExt.setReleaseDate(new Date(Long.parseLong(covertNull(fieldName.getText()))));break;
								case "mediaPath" : contentExt.setMediaPath(covertNull(fieldName.getText()));break;
								case "mediaType" : contentExt.setMediaType(covertNull(fieldName.getText()));break;
								case "titleColor" : contentExt.setTitleColor(covertNull(fieldName.getText()));break;
								case "bold" : contentExt.setBold(Boolean.parseBoolean(covertNull(fieldName.getText())));break;
								case "titleImg" : contentExt.setTitleImg(covertNull(fieldName.getText()));break;
								case "contentImg" : contentExt.setContentImg(covertNull(fieldName.getText()));break;
								case "typeImg" : contentExt.setTypeImg(covertNull(fieldName.getText()));break;
								case "link" : contentExt.setLink(covertNull(fieldName.getText()));break;
								case "tplContent" : contentExt.setTplContent(covertNull(covertNull(fieldName.getText())));break;
								case "needRegenerate" : contentExt.setNeedRegenerate(Boolean.parseBoolean(covertNull(fieldName.getText())));break;
								case "editor" : contentExt.setEditor(covertNull(fieldName.getText()));break;
								case "approver" : contentExt.setApprover(covertNull(fieldName.getText()));break;
							}
						}
					}
				
					if("contentTxt".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							switch(fieldName.getName()){
								case "txt"  : contentTxt.setTxt(covertNull(fieldName.getText()));break;
								case "txt1"  : contentTxt.setTxt1(covertNull(fieldName.getText()));break;
								case "txt2"  : contentTxt.setTxt2(covertNull(fieldName.getText()));break;
								case "txt3"  : contentTxt.setTxt3(covertNull(fieldName.getText()));break;
							}
						}
					}
					//获取副栏目
					String channelIdsStr = "";
					if("channelIds".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							channelIdsStr = ","+fieldName.getText();
						}
					}
					
					if(isNotNull(channelIdsStr)){
						channelIds = StringToIntArr(channelIdsStr.substring(1));
					}
					//获取专题
					
					if("topicIds".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							topicIdsStr = ","+fieldName.getText();
						}
					}
					
					if(isNotNull(topicIdsStr)){
						topicIds = StringToIntArr(topicIdsStr);
					}
					// 保存浏览会员组
					if("viewGroupIds".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							viewGroupIdsStr = ","+fieldName.getText();
						}
					}
					if(isNotNull(viewGroupIdsStr)){
						viewGroupIds = StringToIntArr(viewGroupIdsStr);
					}
					
					// 保存标签
					if("tags".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							tagArrStr = "," + fieldName.getText();
						}
					}
					if(isNotNull(tagArrStr)){
						tagArr = tagArrStr.split(",");
					}
					// 保存附件
					if("attachmentPaths".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							attachmentPathsStr = "," + fieldName.getText();
						}
					}
					if(isNotNull(attachmentPathsStr)){
						if(attachmentPathsStr.startsWith(",")){
							attachmentPathsStr = attachmentPathsStr.substring(1);
						}
						attachmentPaths = attachmentPathsStr.split(",");
					}
					
					if("attachmentNames".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							attachmentNamesStr = "," + fieldName.getText();
						}
					}
					if(isNotNull(attachmentNamesStr)){
						if(attachmentNamesStr.startsWith(",")){
							attachmentNamesStr = attachmentNamesStr.substring(1);
						}
						attachmentNames = attachmentNamesStr.split(",");
					}
					
					if("attachmentFileNames".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							attachmentFilenamesStr = "," + fieldName.getText();
						}
					}
					if(isNotNull(attachmentFilenamesStr)){
						if(attachmentFilenamesStr.startsWith(",")){
							attachmentFilenamesStr = attachmentFilenamesStr.substring(1);
						}
						attachmentFilenames = attachmentFilenamesStr.split(",");
					}
					// 保存图片集
					if("picPaths".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							picPathsStr = "," + fieldName.getText();
						}
					}
					if(isNotNull(picPathsStr)){
						picPaths = picPathsStr.split(",");
					}
					
					if("picDescs".equals(baseContent.getName())){
						Iterator<Element> contentFieldList =  baseContent.elementIterator();
						while(contentFieldList.hasNext()){
							Element fieldName = contentFieldList.next();
							picDescsStr = "," + fieldName.getText();
						}
					}
					if(isNotNull(picDescsStr)){
						picDescs = picDescsStr.split(",");
					}
					
				}
				content = manager.save(content, contentExt, contentTxt, channelIds, topicIds, viewGroupIds,
						tagArr, attachmentPaths, attachmentNames, attachmentFilenames,
						picPaths, picDescs, channelId, typeId, draft, false, user,
						false);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return "";
	}
	//String 转 Integer[]
	public static Integer[] StringToIntArr(String str){
		String[] strArr = str.split(",");
		Integer[] intArr = new Integer[strArr.length];
		for(int i = 0 ;i<strArr.length;i++){
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}
	
	
	//如果String类型为null，设置为空白
	public static String covertNull(String str){
		if(str==null||"null".endsWith(str)){
			return "";
		}else{
			return str;
		}
	}

	public static boolean isNotNull(String str){
		if(str!=null&&!"".equals(str)){
			return true;
		}
		return false;
	}
	

}
