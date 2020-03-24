package io.github.blai44.service.admin.impl;

import io.github.blai44.dao.admin.LogDao;
import io.github.blai44.entity.admin.Log;
import io.github.blai44.service.admin.LogService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 日志Service接口实现
 * @author blai
 *
 */
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;
	
	@Override
	public int add(Log log) {
		return logDao.add(log);
	}

	@Override
	public List<Log> findList(Map<String, Object> queryMap) {
		return logDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return logDao.getTotal(queryMap);
	}

	@Override
	public int delete(String ids) {
		return logDao.delete(ids);
	}

	@Override
	public int add(String content) {
		Log log = new Log();
		log.setContent(content);
		log.setCreateTime(new Date());
		return logDao.add(log);
	}
}
