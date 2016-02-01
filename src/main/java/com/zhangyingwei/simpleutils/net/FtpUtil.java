package com.zhangyingwei.simpleutils.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.zhangyingwei.simpleutils.entity.FTPEntity;

public class FtpUtil {
	private FtpUtil() {
	}

	private static class FtpUtilHandler {
		private static FtpUtil ftpUtil = new FtpUtil();
	}

	public static FtpUtil getInstance() {
		return FtpUtilHandler.ftpUtil;
	}
	
	/**
	 * ftp方式连接
	 */
	public static boolean uploadFileByFtp(FTPEntity ftpEntity,InputStream input) throws Exception{
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ftpEntity.getUrl(), ftpEntity.getPort());
			ftp.login(ftpEntity.getUsername(), ftpEntity.getPassword());
			int reply = ftp.getReplyCode();//获取状态码
			if (!FTPReply.isPositiveCompletion(reply)) { 
				ftp.disconnect();
				return success; 
			}
			ftp.changeWorkingDirectory(ftpEntity.getPath());
			ftp.storeFile(ftpEntity.getFilename(), input);
			input.close(); 
			ftp.logout(); 
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect();//断开连接
	            } catch (IOException ioe) {
	            } 
	        }
		}
		return success;
	}
	/**
	 * sftp方式连接
	 * @param ftpModel
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static boolean uploadFileBySFtp(FTPEntity ftpEntity,InputStream input) throws Exception{
		boolean success = false;
		ChannelSftp sftp = null;
		Channel channel = null;
		try {
			JSch jsch = new JSch();
			Session sshSession = jsch.getSession(ftpEntity.getUsername(), ftpEntity.getUrl(), ftpEntity.getPort()); 
			sshSession.setPassword(ftpEntity.getPassword()); 
			Properties sshConfig = new Properties(); 
			sshConfig.put("StrictHostKeyChecking", "no"); 
			sshSession.setConfig(sshConfig); 
			sshSession.connect();
			channel = sshSession.openChannel("sftp"); 
			channel.connect(); 
			sftp = (ChannelSftp) channel;
			sftp.cd(ftpEntity.getPath());
			sftp.put(input,ftpEntity.getFilename());
			success =true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			channel.disconnect();
			input.close();
		}
		return success;
	}
}
