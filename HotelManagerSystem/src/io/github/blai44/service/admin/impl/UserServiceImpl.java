package io.github.blai44.service.admin.impl;

import java.util.List;
import java.util.Map;

import io.github.blai44.dao.admin.UserDao;
import io.github.blai44.entity.admin.User;
import io.github.blai44.service.admin.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * User”√ªßServiceImpl
 * @author blai
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public int edit(User user) {
		return userDao.edit(user);
	}

	@Override
	public int delete(String ids) {
		return userDao.delete(ids);
	}

	@Override
	public List<User> findList(Map<String, Object> queryMap) {
		return userDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return userDao.getTotal(queryMap);
	}

	@Override
	public int editPassword(User user) {
		return userDao.editPassword(user);
	}

}
