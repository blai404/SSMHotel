package io.github.blai44.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import io.github.blai44.entity.admin.User;

/**
 * User”√ªßdao
 * @author blai
 *
 */
@Repository
public interface UserDao {

	public User findByUsername(String username);
	public int add(User user);
	public int edit(User user);
	public int editPassword(User user);
	public int delete(String ids);
	public List<User> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
