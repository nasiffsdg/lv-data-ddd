package com.lv.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;
 
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;

/**
 * AES 128bit 加密解密工具类
 * 使用AES-128-CBC加密模式，key需要为16位,key和iv可以相同！
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 1:30 AM
 */
@Log4j2
public class AesEncryptUtil {

	private final static String BASE = "abcdefghijklmnopqrstuvwxyz0123456789";
	private final static String KEY = "0000001728397354";
	private final static String IV = "c2effmldhkl6m4vu";

	/**
	 * 加密方法
	 * @param data  要加密的数据
	 * @param key 加密key
	 * @param iv 加密iv
	 * @return 加密的结果
	 */
	public static String encrypt(String data, String key, String iv) {
		log.info("AES开始加密，加密信息：{}", JSONObject.toJSONString(data));
		try {
			//"算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspace = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec inspect = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspace, inspect);
			// 加密
			byte[] encrypted = cipher.doFinal(plaintext);
			log.info("AES加密成功");
			//通过Base64转码返回
			return new Base64().encodeToString(encrypted);
		} catch (Exception e) {
			log.info("AES加密失败，异常信息: {}", e.getMessage());
		}
		return null;
	}
 
	/**
	 * 解密方法
	 * @param data 要解密的数据
	 * @param key  解密key
	 * @param iv 解密iv
	 * @return 解密的结果
	 */
	public static String desEncrypt(String data, String key, String iv) {

		log.info("AES开始解密，密文信息：{}", data);
		try {
			byte[] encrypted1 = new Base64().decode(data);
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			//使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			//执行操作
			byte[] original = cipher.doFinal(encrypted1);
			int length = 0;
			for (int i = 0; i < original.length; ++i) {
				if (original[i] == 0) {
					length = i;
					break;
				}
			}
			return new String(original, 0, length, StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("AES解密失败：{}", e.getMessage());
		}
		return null;
	}

	/**
	 * 加密方法
	 * @param data  要加密的数据
	 * @return 加密的结果
	 */
	public static String encrypt(String data) {
		log.info("AES开始加密，加密信息：{}", JSONObject.toJSONString(data));
		try {
			//"算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspace = new SecretKeySpec(KEY.getBytes(), "AES");
			IvParameterSpec inspect = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspace, inspect);
			// 加密
			byte[] encrypted = cipher.doFinal(plaintext);
			log.info("AES加密成功");
			//通过Base64转码返回
			return new Base64().encodeToString(encrypted);
		} catch (Exception e) {
			log.info("AES加密失败，异常信息: {}", e.getMessage());
		}
		return null;
	}

	/**
	 * 解密方法
	 * @param data 要解密的数据
	 * @return 解密的结果
	 */
	public static String desEncrypt(String data) {

		log.info("AES开始解密，密文信息：{}", data);
		try {
			byte[] encrypted1 = new Base64().decode(data);
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keyspec = new SecretKeySpec(KEY.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());
			//使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			//执行操作
			byte[] original = cipher.doFinal(encrypted1);
			int length = 0;
			for (int i = 0; i < original.length; ++i) {
				if (original[i] == 0) {
					length = i;
					break;
				}
			}
			return new String(original, 0, length, StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("AES解密失败：{}", e.getMessage());
		}
		return null;
	}
	
	/**
	  * 用于生成一组16位随机数 key
	 *  @return 加密的key
	 */
	public static String getRandomStringKey() {
		int hashCodeValue = UUID.randomUUID().hashCode();
		if(hashCodeValue < 0) {
			hashCodeValue = -hashCodeValue;
		}
		//左边补0,16位，进制（d,x）
		return String.format("%016d",hashCodeValue);
	}
	
	/**
	  * 用于生成16位的随机数 iv
	 *  @return 加密的iv
	 */
    public static String getRandomStringIv(){
		Random random=new Random();
		StringBuilder key = new StringBuilder();
		for(int i=0;i<16;i++){ 
			int keyNumber = random.nextInt(BASE.length());
			key.append(BASE.charAt(keyNumber));
		}  
		return key.toString();
	}
}
