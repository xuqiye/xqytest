package com.programs.service;

/**
 * Service --管理员  
 * @author xuqiye
 *
 */
public interface AdminService {
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);
}
