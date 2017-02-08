package com.programs.service.impl;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programs.service.CaptchaService;

import framework.util.StringUtils;

/**
 * Service -- 验证码
 * @author xqy
 *
 */
@Service
public class CaptchaServiceImpl implements CaptchaService{

	@Resource(name = "imageCaptchaService")
	private com.octo.captcha.service.CaptchaService imageCaptchaService;
	@Override
	public BufferedImage buildImage(String captchaId) {
		return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
	}
	
	public boolean isValid(HttpServletRequest request, String captchaId) {
		String captcha = request.getParameter("captcha");
		if(StringUtils.isNull(captcha) || StringUtils.isNull(captchaId)){
			return false;
		}
		try{
			return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase()).booleanValue();
		}catch (Exception e) {
			return false;
		}
	}

}
