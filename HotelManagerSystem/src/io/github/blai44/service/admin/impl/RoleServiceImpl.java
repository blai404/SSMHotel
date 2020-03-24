package io.github.blai44.service.admin.impl;

import io.github.blai44.dao.admin.RoleDao;
import io.github.blai44.entity.admin.Role;
import io.github.blai44.service.admin.RoleService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 角色RoleService实现类
 * @author blai
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public int add(Role role) {
		return roleDao.add(role);
	}

	@Override
	public int edit(Role role) {
		return roleDao.edit(role);
	}

	@Override
	public int delete(Long id) {
		return roleDao.delete(id);
	}

	@Override
	public List<Role> findList(Map<String, Object> queryMap) {
		return roleDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return roleDao.getTotal(queryMap);
	}

	@Override
	public Role find(Long id) {
		return roleDao.find(id);
	}

}
