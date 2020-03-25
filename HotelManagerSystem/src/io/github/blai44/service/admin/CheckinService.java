package io.github.blai44.service.admin;

import io.github.blai44.entity.admin.Checkin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * ��סService
 * @author blai
 *
 */
@Service
public interface CheckinService {

	public int add(Checkin checkin);
	public int edit(Checkin checkin);
	public int delete(Long id);
	public List<Checkin> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public Checkin find(Long id);
	public List<Map> getStatsByMonth();
	public List<Map> getStatsByDay();
}
