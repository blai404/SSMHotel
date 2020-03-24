package io.github.blai44.service.admin.impl;

import io.github.blai44.dao.admin.FloorDao;
import io.github.blai44.entity.admin.Floor;
import io.github.blai44.service.admin.FloorService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * ¬•≤„Service µœ÷¿‡
 * @author blai
 *
 */
@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorDao floorDao;
	
	@Override
	public int add(Floor floor) {
		return floorDao.add(floor);
	}

	@Override
	public int edit(Floor floor) {
		return floorDao.edit(floor);
	}

	@Override
	public int delete(Long id) {
		return floorDao.delete(id);
	}

	@Override
	public List<Floor> findList(Map<String, Object> queryMap) {
		return floorDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return floorDao.getTotal(queryMap);
	}

	@Override
	public List<Floor> findAll() {
		return floorDao.findAll();
	}

}
