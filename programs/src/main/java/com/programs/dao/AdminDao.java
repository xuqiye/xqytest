package com.programs.dao;

import com.programs.entity.Admin;

/**
 * Dao - 管理员
 * @author xuqiye
 *
 */
public interface AdminDao extends BaseDao<Admin, Long>{
	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);
}
