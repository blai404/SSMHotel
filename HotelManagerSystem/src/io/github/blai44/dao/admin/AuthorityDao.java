package io.github.blai44.dao.admin;

import io.github.blai44.entity.admin.Authority;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * Ȩ��Dao�ӿ�
 * @author blai
 *
 */
@Repository
public interface AuthorityDao {

	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);
	public List<Authority> findListByRoleId(Long roleId);
}
