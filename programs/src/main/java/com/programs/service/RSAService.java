package com.programs.service;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端加密
 * @author xqy
 *
 */
public interface RSAService {
	/**
	 * 生成密匙
	 * @param request
	 * @return 生成的密匙
	 */
	public abstract RSAPublicKey generateKey(HttpServletRequest request);
}
