package io.github.blai44.service.admin;

import io.github.blai44.entity.admin.Role;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * ½ÇÉ«role service
 * @author blai
 *
 */
@Service
public interface RoleService {

	public int add(Role role);
	public int edit(Role role);
	public int delete(Long id);
	public List<Role> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public Role find(Long id);
}
