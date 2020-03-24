package io.github.blai44.dao;

import io.github.blai44.entity.BookOrder;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * Ô¤¶©¶©µ¥Dao
 * @author blai
 *
 */
@Repository
public interface BookOrderDao {

	public int add(BookOrder bookOrder);
	public int edit(BookOrder bookOrder);
	public int delete(Long id);
	public List<BookOrder> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public BookOrder find(Long id);
}
