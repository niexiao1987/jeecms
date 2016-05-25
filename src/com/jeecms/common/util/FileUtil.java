package com.jeecms.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	/**
	 * copy图片
	 * @param from 文件来源
	 * @param targetPath 生成文件的目录
	 * @param prefixName 生成的文件名前缀，前缀为文件所在文件夹的名字，例如201605
	 */
	public static void copyImg(File from, String targetPath,String prefixName) {
		File targetFolder = new File(targetPath);
		if(!targetFolder.exists()){
			targetFolder.mkdir();
		}
		String targetName = from.getName();
		FileInputStream input = null;
		BufferedInputStream binput = null;
		FileOutputStream output = null;
		BufferedOutputStream boutput = null;
		try{
			input=new FileInputStream(from);
			binput = new BufferedInputStream(input);
			output=new FileOutputStream(targetPath+"/"+prefixName+targetName);
			boutput = new BufferedOutputStream(output);
			byte[] buffer = new byte[1024*8];
			int lenght = -1;
			while((lenght = binput.read(buffer))!=-1){
				boutput.write(buffer, 0, lenght);
			}
			
		
			}catch (IOException e){
			System.out.println(e.toString());
			}finally{
				try {
					input.close();
					output.close();
					binput.close();
					boutput.close();
				} catch (IOException e) {
					System.out.println(e.toString()+"关闭流失败");
				}
			}
	}
	/**
	 * 删除文件夹下的文件和文件夹
	 * @param file
	 */
	public static boolean deleteFile(File file) {
		if (file.isFile()) {
			return file.delete();
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
			return file.delete();
		}
	}
	
}
