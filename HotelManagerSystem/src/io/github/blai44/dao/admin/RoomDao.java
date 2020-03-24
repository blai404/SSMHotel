package io.github.blai44.dao.admin;

import io.github.blai44.entity.RoomType;
import io.github.blai44.entity.admin.Room;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 *	∑øº‰Dao
 * @author blai
 *
 */
@Repository
public interface RoomDao {

	public int add(Room room);
	public int edit(Room room);
	public int delete(Long id);
	public List<Room> findList(Map<String, Object> queryMap);
	public List<RoomType> findAll();
	public Integer getTotal(Map<String, Object> queryMap);
	public Room find(Long id);
	public Room findBySn(String sn);
}
