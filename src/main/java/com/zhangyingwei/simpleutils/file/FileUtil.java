package com.zhangyingwei.simpleutils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import com.zhangyingwei.simpleutils.util.UtilFactory;

public class FileUtil {
	private FileUtil(){}
	
	private static class FileUtilHandler{
		private static FileUtil fileUtil = new FileUtil();
	}
	
	public static FileUtil getInstance(){
		return FileUtilHandler.fileUtil;
	}
	
	/**
	 * 功能描述：拷贝一个目录或者文件到指定路径下，即把源文件拷贝到目标文件路径下
	 * 
	 * @param source 源文件
	 * @param target 目标文件路径
	 * @return void
	 */
	public void copy(File source, File target) {
		File tarpath = new File(target, source.getName());
		if (source.isDirectory()) {
			tarpath.mkdir();
			File[] dir = source.listFiles();
			for (int i = 0; i < dir.length; i++) {
				copy(dir[i], tarpath);
			}
		} else {
			try {
				InputStream is = new FileInputStream(source); // 用于读取文件的原始字节流
				OutputStream os = new FileOutputStream(tarpath); // 用于写入文件的原始字节的流
				byte[] buf = new byte[1024];// 存储读取数据的缓冲区大小
				int len = 0;
				while ((len = is.read(buf)) != -1) {
					os.write(buf, 0, len);
				}
				is.close();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 功能描述：列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
	 * 
	 * @param path 文件夹
	 * @param fileList 接收文件列表的list
	 * @param endwith 文件后缀 例如：".pdf,.doc,.html"
	 * @throws FileNotFoundException
	 */
	public void listFile(File path,List fileList,String endwith) throws FileNotFoundException {
		if (!path.exists()) {
			throw new FileNotFoundException();
		} else {
			if (path.isFile()) {
				if(UtilFactory.getStringUtil().isNull(endwith)){
					fileList.add(path);
				}else{
					String[] endwiths = endwith.split(",");
					int length = endwiths.length;
					for(int i = 0;i<length;i++){
						if(path.getName().toLowerCase().endsWith(endwiths[i])){
							fileList.add(path);
						}
					}
				}
			} else {
				File[] files = path.listFiles();
				for (int i = 0; i < files.length; i++) {
					listFile(files[i],fileList,endwith);
				}
			}
		}
	}
}
