package com.programs.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programs.service.CaptchaService;
import com.programs.service.RSAService;


import framework.util.StringUtils;


/**
 * Controller --公用
 * @author xqy
 *
 */
@Controller
@RequestMapping("/admin/common")
public class CommonController extends BaseController{
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private RSAService rSAService;
	/**
	 * 验证码
	 */
	@RequestMapping(value="/captcha",method=RequestMethod.GET)
	public void captchaImage(String captchaId,HttpServletRequest req,HttpServletResponse res){
		if(StringUtils.isNull(captchaId)){
			captchaId = req.getSession().getId();
		}
		//没有缓存，设置两行表示不缓存
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Cache-Control", "no-store");
		//设置过期的时间期限 
		res.setDateHeader("Expires", 0);
		//设置传送类型
		res.setContentType("image/jpeg");
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = res.getOutputStream();
			BufferedImage buildImage = captchaService.buildImage(captchaId);
			ImageIO.write(buildImage, "jpg", outputStream);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(outputStream);
		}
		
		
	}
	/**
	 * 前端加密
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/publicKey",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> publicKey(HttpServletRequest request){
		RSAPublicKey rsapublickey = rSAService.generateKey(request);
		Map<String, String> obj= new HashMap<String,String>();
		obj.put("modulus", Base64.encodeBase64String(rsapublickey.getModulus().toByteArray()));
		obj.put("exponent", Base64.encodeBase64String(rsapublickey.getPublicExponent().toByteArray()));
		return obj;
	}
	
}
