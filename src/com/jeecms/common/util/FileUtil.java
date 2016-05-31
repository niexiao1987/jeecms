package com.jeecms.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {
	public static void fileChannelCopy(File from, String targetPath,String prefixName) {
		File targetFolder = new File(targetPath);
		if(!targetFolder.exists()){
			targetFolder.mkdir();
		}
		String targetName = from.getName();
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(from);
			fo = new FileOutputStream(targetPath+"/"+prefixName+targetName);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
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
			int lenght ;
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
