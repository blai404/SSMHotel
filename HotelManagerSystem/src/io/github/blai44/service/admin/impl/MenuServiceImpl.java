package io.github.blai44.service.admin.impl;

import java.util.List;
import java.util.Map;

import io.github.blai44.dao.admin.MenuDao;
import io.github.blai44.entity.admin.Menu;
import io.github.blai44.service.admin.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * ≤Àµ•π‹¿ÌServiceImpl
 * @author blai
 *
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	public int add(Menu menu) {
		return menuDao.add(menu);
	}
	
	@Override
	public List<Menu> findList(Map<String, Object> queryMap){
		return menuDao.findList(queryMap);
	}

	@Override
	public List<Menu> findTopList() {
		return menuDao.findTopList();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return menuDao.getTotal(queryMap);
	}

	@Override
	public int edit(Menu menu) {
		return menuDao.edit(menu);
	}

	@Override
	public int delete(Long id) {
		return menuDao.delete(id);
	}

	@Override
	public List<Menu> findChildrenList(Long parentId) {
		return menuDao.findChildrenList(parentId);
	}

	@Override
	public List<Menu> finListByIds(String ids) {
		return menuDao.finListByIds(ids);
	}

}
