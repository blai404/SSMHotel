package io.github.blai44.service.impl;

import io.github.blai44.dao.RoomTypeDao;
import io.github.blai44.entity.RoomType;
import io.github.blai44.service.RoomTypeService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 房型Service接口
 * @author blai
 *
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomTypeDao roomTypeDao;
	
	@Override
	public int add(RoomType roomType) {
		return roomTypeDao.add(roomType);
	}

	@Override
	public int edit(RoomType roomType) {
		return roomTypeDao.edit(roomType);
	}

	@Override
	public int delete(Long id) {
		return roomTypeDao.delete(id);
	}

	@Override
	public List<RoomType> findList(Map<String, Object> queryMap) {
		return roomTypeDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		return roomTypeDao.getTotal(queryMap);
	}

	@Override
	public List<RoomType> findAll() {
		return roomTypeDao.findAll();
	}

	@Override
	public RoomType find(Long id) {
		return roomTypeDao.find(id);
	}

	@Override
	public int updateNum(RoomType roomType) {
		return roomTypeDao.updateNum(roomType);
	}

}
