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
	
	//算法名称 
    public static final String KEY_ALGORITHM = "DES";
    //算法名称/加密模式/填充方式 
    //DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
    public static final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    /**
     *   
     * 生成密钥key对象
     * @param KeyStr 密钥字符串 
     * @return 密钥对象 
     * @throws InvalidKeyException   
     * @throws NoSuchAlgorithmException   
     * @throws InvalidKeySpecException   
     * @throws Exception 
     */
    private SecretKey keyGenerator(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESKeySpec desKey = new DESKeySpec(input);
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        return securekey;
    }

    private int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换 
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
     * 加密数据
     * @param data 待加密数据
     * @param key 密钥
     * @return 加密后的数据 
     */
    public String encrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        // 实例化Cipher对象，它用于完成实际的加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecureRandom random = new SecureRandom();
        // 初始化Cipher对象，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
        byte[] results = cipher.doFinal(data.getBytes());
        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输 
        return Base64.encodeBase64String(results);
    }

    /** 
     * 解密数据 
     * @param data 待解密数据 
     * @param key 密钥 
     * @return 解密后的数据 
     */
    public String decrypt(String data, String key) throws Exception {
        Key deskey = keyGenerator(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //初始化Cipher对象，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        // 执行解密操作
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }
    
    /** 
     * 加密 
     * @method encrypt 
     * @param content   需要加密的内容 
     * @param password  加密密码 
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
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
        byte[] byteContent = data.getBytes();  
        cipher.init(Cipher.ENCRYPT_MODE, keyspec);// 初始化  
        byte[] result = cipher.doFinal(byteContent);  
        return Base64.encodeBase64String(result); // 加密  
    }  
      
    /** 
     * 解密 
     * @method decrypt 
     * @param content   待解密内容 
     * @param password  解密密钥 
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
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
        return new String(cipher.doFinal(Base64.decodeBase64(content))); // 解密  
    }
    
    public static void main(String[] args) throws Exception {
		String key = "zhangyingwei.com";
		String value = "zhangyw_001@126.com";
		String encodestr = new DESUtil().encryptNolengthLimit(value, key);
		System.out.println(encodestr);
		System.out.println(new DESUtil().decryptNolengthLimit(encodestr, key));
	}
}
