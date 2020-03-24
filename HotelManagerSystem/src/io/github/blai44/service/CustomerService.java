package io.github.blai44.service;

import io.github.blai44.entity.Customer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * ¿Í»§Service
 * @author blai
 *
 */
@Service
public interface CustomerService {

	public int add(Customer customer);
	public int edit(Customer customer);
	public int delete(Long id);
	public List<Customer> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public Customer find(Long id);
	public Customer findByName(String name);
	public List<Customer> findAll();
}
