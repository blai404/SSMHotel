package io.github.blai44.dao.admin;

import io.github.blai44.entity.admin.Role;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
/**
 * ½ÇÉ«RoleDao
 * @author blai
 *
 */
@Repository
public interface RoleDao {

	public int add(Role role);
	public int edit(Role role);
	public int delete(Long id);
	public List<Role> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public Role find(Long id);
}
