package io.github.blai44.service.admin.impl;

import io.github.blai44.dao.admin.AuthorityDao;
import io.github.blai44.entity.admin.Authority;
import io.github.blai44.service.admin.AuthorityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 权限Service实现
 * @author blai
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;
	
	@Override
	public int add(Authority authority) {
		return authorityDao.add(authority);
	}

	@Override
	public int deleteByRoleId(Long roleId) {
		return authorityDao.deleteByRoleId(roleId);
	}

	@Override
	public List<Authority> findListByRoleId(Long roleId) {
		return authorityDao.findListByRoleId(roleId);
	}

}
