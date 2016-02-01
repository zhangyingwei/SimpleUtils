package com.zhangyingwei.simpleutils.util;

import java.io.File;
import java.util.Map;

import com.zhangyingwei.simpleutils.encode.DESUtil;
import com.zhangyingwei.simpleutils.encode.MD5Util;

public class EncodeUtil {
	private EncodeUtil(){}
	
	private static class EncodeUtilHandler{
		private static EncodeUtil encodeUtil = new EncodeUtil();
	}
	
	public static EncodeUtil getInstance(){
		return EncodeUtilHandler.encodeUtil;
	}
	
	private MD5Util md5Util = MD5Util.getInstance();
	private DESUtil desUtil = DESUtil.getInstance();
	
	/**
	 * get md5 of String
	 * @param data
	 * @return String 
	 */
	public String encodeMD5(String data){
		return md5Util.getMD5ofStr(data);
	}
	/**
	 * get md5 of file
	 * @param file
	 * @return String
	 */
	public String encodeMD5(File file){
		return md5Util.getMD5OfFile(file);
	}
	/**
	 * 获取文件夹中文件的MD5值
	 * @param file
	 * @param listChild;true递归子目录中的文件
	 * @return Map
	 */
	public Map encodeMD5(File file,boolean listChild){
		return md5Util.getMD5OfDir(file, listChild);
	}
	/**
	 * get SHA1 of file
	 * @param file
	 * @return String
	 */
	public String encodeSHA1(File file){
		return md5Util.getSHA1OfFile(file);
	}
	/**
	 * get SHA1 of dir
	 * @param file
	 * @param listChild true递归子目录中的文件
	 * @return Map
	 */
	public Map encodeSHA1(File file,boolean listChild){
		return md5Util.getSHA1OfDir(file, listChild);
	}
	/**
	 * get CRC32 of file
	 * @param file
	 * @return
	 */
	public String encodeCRC32(File file){
		return md5Util.getCRC32OfFile(file);
	}
	/**
	 * get CRC32 of dir
	 * @param file
	 * @param listChild true递归子目录中的文件
	 * @return Map
	 */
	public Map encodeCRC32(File file,boolean listChild){
		return md5Util.getCRC32OfDir(file, listChild);
	}
	
	/**
	 * encode by des
	 * @param data 8byte
	 * @param key 16byte
	 * @return String
	 * @throws Exception
	 */
	public String encodeDES(String data,String key) throws Exception{
		return this.desUtil.encryptNolengthLimit(data, key);
	}
	/**
	 * decode by des
	 * @param data 8byte
	 * @param key 16byte
	 * @return String
	 * @throws Exception 
	 */
	public String decodeDES(String data,String key) throws Exception{
		return this.desUtil.decryptNolengthLimit(data, key);
	}
}
