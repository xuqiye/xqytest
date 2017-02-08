package com.programs.dao.impl;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.programs.dao.AdminDao;
import com.programs.entity.Admin;



/**
 * Dao - 管理员
 * @author xuqiye
 *
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long>implements AdminDao{

	@Override
	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}

}
