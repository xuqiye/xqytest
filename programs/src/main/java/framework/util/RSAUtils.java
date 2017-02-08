package framework.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Utils -- RSA加密解密
 * @author xqy
 *
 */

public final class RSAUtils {
	/** 安全服务提供者 */
	private static final Provider PROVIDER = new BouncyCastleProvider();
	/** 密钥大小 */
	private static final int KEY_SIZE = 1024;
	private RSAUtils() {
		
	}
	/**
	 * 生成密钥对
	 * 
	 * @return 密钥对
	 */
	public static KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PROVIDER);
			keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
