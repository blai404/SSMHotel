package io.github.blai44.service.impl;

import io.github.blai44.dao.CustomerDao;
import io.github.blai44.entity.Customer;
import io.github.blai44.service.CustomerService;

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
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public int add(Customer customer) {
		return customerDao.add(customer);
	}

	@Override
	public int edit(Customer customer) {
		return customerDao.edit(customer);
	}

	@Override
	public List<Customer> findList(Map<String, Object> queryMap) {
		return customerDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		return customerDao.getTotal(queryMap);
	}

	@Override
	public Customer find(Long id) {
		return customerDao.find(id);
	}

	@Override
	public int delete(Long id) {
		return customerDao.delete(id);
	}

	@Override
	public Customer findByName(String name) {
		return customerDao.findByName(name);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
