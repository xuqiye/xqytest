package com.programs.controller.admin;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.hql.ast.tree.BooleanLiteralNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programs.service.AdminService;
import com.programs.service.CaptchaService;

import framework.Message;
import framework.util.SpringUtils;
import framework.util.StringUtils;

/**
 * Controller --后台登录
 * @author xuqiye
 *
 */
@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController{
	@Autowired
	private AdminService adminService;
	@Autowired
	private CaptchaService captchaService;
	
	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest request){
		String captchaId = UUID.randomUUID().toString();
		model.addAttribute("captchaId", captchaId);
		return ADMIN_PATH+"/login/index";
		
	}
	@RequestMapping("/submit")
	@ResponseBody
	public Message submit(HttpServletRequest request , HttpServletResponse response){
		String captchaId = request.getParameter("captchaId");
		if(StringUtils.isNull(captchaId)){
			captchaId = request.getSession().getId();
		}
		boolean isCaptcha = captchaService.isValid(request, captchaId);
		if(!isCaptcha){
			return Message.error("admin.captcha.error");
		}
//		userService.get("userName",username);
		String username = request.getParameter("username");
		boolean usernameExists = adminService.usernameExists(username);
		if(!usernameExists){
			return Message.warn("admin.username.warn");
		}
		String enPassword = request.getParameter("enPassword");
		//解密
	
		return null;
	}
	
	
	
	
}