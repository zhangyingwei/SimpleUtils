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
	 * ��������������һ��Ŀ¼�����ļ���ָ��·���£�����Դ�ļ�������Ŀ���ļ�·����
	 * 
	 * @param source Դ�ļ�
	 * @param target Ŀ���ļ�·��
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
				InputStream is = new FileInputStream(source); // ���ڶ�ȡ�ļ���ԭʼ�ֽ���
				OutputStream os = new FileOutputStream(tarpath); // ����д���ļ���ԭʼ�ֽڵ���
				byte[] buf = new byte[1024];// �洢��ȡ���ݵĻ�������С
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
	 * �����������г�ĳ�ļ��м������ļ���������ļ������ɸ�����չ������
	 * 
	 * @param path �ļ���
	 * @param fileList �����ļ��б��list
	 * @param endwith �ļ���׺ ���磺".pdf,.doc,.html"
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
