package com.jeecms.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;


public class ZipUtil {
	/**
	 * 
	 * @param file 要压缩的文件
	 * @param zipOut 压缩文件输出流ZipOutputStream
	 * @param dirName 图片或附件文件夹名称
	 * @throws IOException
	 */
	public static void compress(File file ,ZipOutputStream zipOut) throws IOException{
		InputStream input = null;
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i = 0;i < files.length;i++){
				compress(files[i],zipOut);
			}
		}else{
			input = new FileInputStream(file);
			zipOut.putNextEntry(new ZipEntry(file.getName()));
			byte[] buffer = new byte[1024*8];
			int length;
			while((length=input.read(buffer))!=-1){
				zipOut.write(buffer, 0, length);
			}
			input.close();
		}
	}
	/**
	 * 解压
	 * @param zipPath
	 * @throws IOException
	 */
	public static String unCompress(String zipPath,String compressPath,String wwwPath) throws IOException {
		String xmlPath = null;
		File file = new File(zipPath);
		File outFile = null;
		ZipFile zipFile = new ZipFile(file);
		ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
		ZipEntry entry = null;
		InputStream input = null;
		OutputStream output = null;
		while ((entry = zipInput.getNextEntry()) != null) {
			System.out.println("解压缩" + entry.getName() + "文件");
			//要解压的文件名
			String zipFileName = entry.getName();
			//文件要归属的文件夹名
			String folderName = zipFileName.substring(0, 6);
			//文件本来的名字
			String fileName = zipFileName.substring(6);
			
			File folderFile = new File(wwwPath+File.separator+folderName);
			if(!folderFile.exists()){
				folderFile.mkdir();
			}
			
			String outFilePath = wwwPath+File.separator+folderName+File.separator+fileName;
			outFile = new File(outFilePath);
			
			System.out.println(outFilePath);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdir();
			}
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			input = zipFile.getInputStream(entry);
			output = new FileOutputStream(outFile);
			byte[] buffer = new byte[1024*8];
			int length = -1;
			while ((length = input.read(buffer)) != -1) {
				output.write(buffer,0,length);
			}
			input.close();
			output.close();
			
			if(zipFileName.endsWith(".xml")){
				xmlPath = outFilePath;
			}
		}
		
		return xmlPath;
	}
	
    /**
     * 下载zip文件
     * @param response
     * @param folderPath
     * @throws UnsupportedEncodingException 
     */
	public static void downloadZipFile(HttpServletResponse response, String zipFilePath,String zipName) throws UnsupportedEncodingException { 
		response.setHeader("Pragma", "No-Cache");  
        response.setHeader("Cache-Control", "must-revalidate, no-transform");  
        response.setDateHeader("Expires", 0L);  
        response.setContentType("application/octet-stream");  
        response.setHeader("Content-Disposition", "attachment; filename="+ new String(zipName.getBytes(),"utf-8") );  
          
        // 获取服务其上生成的的ZIP文件  
        File zipFile = new File(zipFilePath);  
        // 将此文件流写入到response输出流中  
        FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        OutputStream ops = null;  
        BufferedOutputStream bos = null;  
        try {  
            fis = new FileInputStream(zipFile);  
            bis = new BufferedInputStream(fis);  
            ops = response.getOutputStream();  
            bos = new BufferedOutputStream(ops);  
            byte[] buffer = new byte[1024*10];  
            int length =-1;  
            
            while ((length = bis.read(buffer)) != -1) {  
              
                bos.write(buffer, 0, length);  
            }   
            bos.flush();  
            ops.flush();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                bos.close();  
                ops.close();  
                bis.close();  
                fis.close();   
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
   
	
}
