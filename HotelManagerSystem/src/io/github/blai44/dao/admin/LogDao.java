package io.github.blai44.dao.admin;

import io.github.blai44.entity.admin.Log;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * »’÷æDao
 * @author blai
 *
 */
@Repository
public interface LogDao {
	
	public int add(Log log);
	public int add(String content);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}
