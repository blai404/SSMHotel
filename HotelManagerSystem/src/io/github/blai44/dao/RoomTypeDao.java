package io.github.blai44.dao;

import io.github.blai44.entity.RoomType;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * ����Dao
 * @author blai
 *
 */
@Repository
public interface RoomTypeDao {

	public int add(RoomType roomType);
	public int edit(RoomType roomType);
	public int delete(Long id);
	public List<RoomType> findList(Map<String, Object> queryMap);
	public List<RoomType> findAll();
	public Integer getTotal(Map<String, Object> queryMap);
	public RoomType find(Long id);
	public int updateNum(RoomType roomType);
}
