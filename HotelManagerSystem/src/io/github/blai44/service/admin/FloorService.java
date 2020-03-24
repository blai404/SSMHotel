package io.github.blai44.service.admin;

import io.github.blai44.entity.admin.Floor;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * Â¥²ãService
 * @author blai
 *
 */
@Service
public interface FloorService {

	public int add(Floor floor);
	public int edit(Floor floor);
	public int delete(Long id);
	public List<Floor> findList(Map<String, Object> queryMap);
	public List<Floor> findAll();
	public int getTotal(Map<String, Object> queryMap);
}
