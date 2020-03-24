package io.github.blai44.service.admin.impl;

import io.github.blai44.dao.admin.RoomDao;
import io.github.blai44.entity.RoomType;
import io.github.blai44.entity.admin.Room;
import io.github.blai44.service.admin.RoomService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 房间Service接口
 * @author blai
 *
 */
@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;
	
	@Override
	public int add(Room room) {
		return roomDao.add(room);
	}

	@Override
	public int edit(Room room) {
		return roomDao.edit(room);
	}

	@Override
	public int delete(Long id) {
		return roomDao.delete(id);
	}

	@Override
	public List<Room> findList(Map<String, Object> queryMap) {
		return roomDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		return roomDao.getTotal(queryMap);
	}

	@Override
	public List<RoomType> findAll() {
		return roomDao.findAll();
	}

	@Override
	public Room find(Long id) {
		return roomDao.find(id);
	}

	@Override
	public Room findBySn(String sn) {
		return roomDao.findBySn(sn);
	}

}
