package com.programs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programs.dao.AdminDao;
import com.programs.service.AdminService;
/**
 * Service --管理员
 * @author xuqiye
 *
 */
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;


	@Transactional(readOnly=true)
	public boolean usernameExists(String username) {
		return adminDao.usernameExists(username);
	}
	

}
