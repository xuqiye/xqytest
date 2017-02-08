package com.programs.service;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;

/**
 * Service -- 验证码
 * @author xqy
 *
 */
public interface CaptchaService {
	/**
	 * 生成验证码图片
	 * 
	 * @param captchaId
	 *            验证ID
	 * @return 验证码图片
	 */
	BufferedImage buildImage(String captchaId);
	
	/**
	 * 校验验证码
	 * @param request
	 * @param captchaId
	 * @return
	 */
	boolean isValid(HttpServletRequest request,String captchaId);
}
