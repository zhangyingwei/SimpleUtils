package com.zhangyingwei.simpleutils.encode;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.zhangyingwei.simpleutils.file.FileUtil;

public class DESUtil {
	private DESUtil(){}
	
	private static class DESUtilHandler{
		private static DESUtil desUtil = new DESUtil();
	}
	
	public static DESUtil getInstance(){
		return DESUtilHandler.desUtil;
	}
	
	//�㷨���� 
    public static final String KEY_ALGORITHM = "DES";
    //�㷨����/����ģʽ/��䷽ʽ 
    //DES�������ֹ���ģʽ-->>ECB���������뱾ģʽ��CBC�����ܷ�������ģʽ��CFB�����ܷ���ģʽ��OFB���������ģʽ
    public static final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    /**
     *   
     * ������Կkey����
     * @param KeyStr ��Կ�ַ��� 
     * @return ��Կ���� 
     * @throws InvalidKeyException   
     * @throws NoSuchAlgorithmException   
     * @throws InvalidKeySpecException   
     * @throws Exception 
     */
    private SecretKey keyGenerator(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESKeySpec desKey = new DESKeySpec(input);
        //����һ���ܳ׹�����Ȼ��������DESKeySpecת����
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        return securekey;
    }

    private int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // ��ʮ�������ַ������ֽ�����ת�� 
    public byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /** 
     * ��������
     * @param data ����������
     * @param key ��Կ
     * @return ���ܺ������ 
     */
    public String encrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        // ʵ����Cipher�������������ʵ�ʵļ��ܲ���
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecureRandom random = new SecureRandom();
        // ��ʼ��Cipher��������Ϊ����ģʽ
        cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
        byte[] results = cipher.doFinal(data.getBytes());
        // ִ�м��ܲ��������ܺ�Ľ��ͨ��������Base64������д��� 
        return Base64.encodeBase64String(results);
    }

    /** 
     * �������� 
     * @param data ���������� 
     * @param key ��Կ 
     * @return ���ܺ������ 
     */
    public String decrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //��ʼ��Cipher��������Ϊ����ģʽ
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        // ִ�н��ܲ���
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }
    
    /** 
     * ���� 
     * @method encrypt 
     * @param content   ��Ҫ���ܵ����� 
     * @param password  �������� 
     * @return 
     * @throws Exception 
     * @throws  
     * @since v1.0 
     */  
    public String encryptNolengthLimit(String data, String key) throws Exception{  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(key.getBytes()));  
        SecretKey secretKey = kgen.generateKey();  
        byte[] enCodeFormat = secretKey.getEncoded();  
        SecretKeySpec keyspec = new SecretKeySpec(enCodeFormat, "AES");  
        Cipher cipher = Cipher.getInstance("AES");// ����������  
        byte[] byteContent = data.getBytes();  
        cipher.init(Cipher.ENCRYPT_MODE, keyspec);// ��ʼ��  
        byte[] result = cipher.doFinal(byteContent);  
        return Base64.encodeBase64String(result); // ����  
    }  
      
    /** 
     * ���� 
     * @method decrypt 
     * @param content   ���������� 
     * @param password  ������Կ 
     * @return 
     * @throws Exception 
     * @throws  
     * @since v1.0 
     */  
    public String decryptNolengthLimit(String content, String password) throws Exception{  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(password.getBytes()));  
        SecretKey secretKey = kgen.generateKey();  
        byte[] enCodeFormat = secretKey.getEncoded();  
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
        Cipher cipher = Cipher.getInstance("AES");// ����������  
        cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��  
        return new String(cipher.doFinal(Base64.decodeBase64(content))); // ����  
    }
    
    public static void main(String[] args) throws Exception {
		String key = "zhangyingwei.com";
		String value = "zhangyw_001@126.com";
		String encodestr = new DESUtil().encryptNolengthLimit(value, key);
		System.out.println(encodestr);
		System.out.println(new DESUtil().decryptNolengthLimit(encodestr, key));
	}
}
