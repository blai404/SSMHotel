package io.github.blai44.dao.admin;

import java.util.List;
import java.util.Map;

import io.github.blai44.entity.admin.Menu;

import org.springframework.stereotype.Repository;

/**
 * ≤Àµ•π‹¿Ìdao
 * @author blai
 *
 */
@Repository
public interface MenuDao {

	public int add(Menu menu);
	public List<Menu> findList(Map<String, Object> queryMap);
	public List<Menu> findTopList();
	public int getTotal(Map<String, Object> queryMap);
	public int edit(Menu menu);
	public int delete(Long id);
	public List<Menu> findChildrenList(Long parentId);
	public List<Menu> finListByIds(String ids);
}
