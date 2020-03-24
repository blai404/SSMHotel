package io.github.blai44.controller.admin;

import io.github.blai44.entity.Customer;
import io.github.blai44.page.admin.Page;
import io.github.blai44.service.CustomerService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户管理后台控制器
 * @author blai
 *
 */
@Controller
@RequestMapping("/admin/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	/**
	 * 用户管理列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("customer/list");
		return model;
	}
	
	/**
	 * 分页获取用户信息
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(name="name",defaultValue="")String name,
			@RequestParam(name="realName",defaultValue="")String realName,
			@RequestParam(name="idCard",defaultValue="")String idCard,
			@RequestParam(name="phoneNum",defaultValue="")String phoneNum,
			@RequestParam(name="status",required=false)Integer status,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("status", status);
		queryMap.put("realName", realName);
		queryMap.put("idCard", idCard);
		queryMap.put("phoneNum", phoneNum);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", customerService.findList(queryMap));
		ret.put("total", customerService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 用户信息添加操作
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Customer customer){
		Map<String, String> ret = new HashMap<String, String>();
		if(customer == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if(StringUtils.isEmpty(customer.getName())){
			ret.put("type", "error");
			ret.put("msg", "用户名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(customer.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "用户密码不能为空！");
			return ret;
		}
		if(isExist(customer.getName(), 0l)){
			ret.put("type", "error");
			ret.put("msg", "该用户名称已存在！");
			return ret;
		}
		if(customerService.add(customer) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	/**
	 * 用户信息编辑操作
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Customer customer){
		Map<String, String> ret = new HashMap<String, String>();
		if(customer == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if(StringUtils.isEmpty(customer.getName())){
			ret.put("type", "error");
			ret.put("msg", "用户名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(customer.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "用户密码不能为空！");
			return ret;
		}
		if(isExist(customer.getName(), customer.getId())){
			ret.put("type", "error");
			ret.put("msg", "该用户名称已存在！");
			return ret;
		}
		if(customerService.edit(customer) <= 0){
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
	}
	
	/**
	 * 用户信息删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的用户信息！");
			return ret;
		}
		try {
			if(customerService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
			}
		} catch (Exception e) {
			ret.put("type", "error");
			ret.put("msg", "请先删除该用户下关联的所有订单信息！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
	/**
	 * 判断用户名是否存在
	 * @param name
	 * @param id
	 * @return
	 */
	private boolean isExist(String name, Long id){
		Customer customer = customerService.findByName(name);
		if(customer == null)return false;
		if(customer.getId().longValue() == id.longValue())return false;
		return true;
	}
}
