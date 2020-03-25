package io.github.blai44.service.admin;

import io.github.blai44.entity.admin.Authority;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Ȩ��Service�ӿ�
 * @author blai
 *
 */
@Service
public interface AuthorityService {

	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);
	public List<Authority> findListByRoleId(Long roleId);
}
