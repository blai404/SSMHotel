package io.github.blai44.service.admin;

import io.github.blai44.entity.admin.Menu;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * ≤Àµ•π‹¿ÌService
 * @author blai
 *
 */
@Service
public interface MenuService {

	public int add(Menu menu);
	public List<Menu> findList(Map<String, Object> queryMap);
	public List<Menu> findTopList();
	public int getTotal(Map<String, Object> queryMap);
	public int edit(Menu menu);
	public int delete(Long id);
	public List<Menu> findChildrenList(Long parentId);
	public List<Menu> finListByIds(String ids);
}
