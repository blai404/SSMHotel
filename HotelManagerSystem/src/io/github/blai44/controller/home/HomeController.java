package io.github.blai44.controller.home;

import io.github.blai44.entity.Customer;
import io.github.blai44.service.CustomerService;
import io.github.blai44.service.RoomTypeService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前台首页控制器
 * @author blai
 *
 */
@RequestMapping("/home")
@Controller
public class HomeController {

	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 前台首页
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model,
			@RequestParam(name="name",defaultValue="")String name
			){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("roomTypeList", roomTypeService.findList(queryMap));
		model.addObject("kw", name);
		model.setViewName("home/index/index");
		return model;
	}
	
	/**
	 * 前台登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("home/index/login");
		return model;
	}
	
	/**
	 * 登录信息提交
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(Customer customer, String vcode, HttpServletRequest request){
		Map<String, String> retMap = new HashMap<String, String>();
		if(customer == null){
			retMap.put("type", "error");
			retMap.put("msg", "请填写用户信息");
			return retMap;
		}
		if(StringUtils.isEmpty(vcode)){
			retMap.put("type", "error");
			retMap.put("msg", "请填写验证码");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "请填写用户名");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "请填写密码");
			return retMap;
		}
		Object loginCpacha = request.getSession().getAttribute("customerLoginCpacha");
		if(loginCpacha == null){
			retMap.put("type", "error");
			retMap.put("msg", "验证码失效");
			return retMap;
		}
		if(!vcode.equalsIgnoreCase(loginCpacha.toString())){
			retMap.put("type", "error");
			retMap.put("msg", "验证码错误！");
			return retMap;
		}
		Customer findByName = customerService.findByName(customer.getName());
		if(findByName == null){
			retMap.put("type", "error");
			retMap.put("msg", "用户名不存在！");
			return retMap;
		}
		if(!customer.getPassword().equals(findByName.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "密码不正确！");
			return retMap;
		}
		if(findByName.getStatus() == -1){
			retMap.put("type", "error");
			retMap.put("msg", "该用户已被禁用，请联系管理员！");
			return retMap;
		}
		request.getSession().setAttribute("customer", findByName);
		request.getSession().setAttribute("customerLoginCpacha", null);
		retMap.put("type", "success");
		retMap.put("msg", "登陆成功!");
		return retMap;
	}
	
	/**
	 * 前台注销功能
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("customer", null);
		return "redirect:index";
	}
	
	/**
	 * 前台注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.GET)
	public ModelAndView reg(ModelAndView model){
		model.setViewName("home/index/reg");
		return model;
	}
	
	/**
	 * 注册信息提交
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> regAct(Customer customer, HttpServletRequest request){
		Map<String, String> retMap = new HashMap<String, String>();
		if(customer == null){
			retMap.put("type", "error");
			retMap.put("msg", "请填写用户信息");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getPhoneNum())){
			retMap.put("type", "error");
			retMap.put("msg", "手机号不能为空！");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "请填写用户名！");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "请填写密码");
			return retMap;
		}
		if(isExist(customer.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "该用户名已存在，请重新输入！");
			return retMap;
		}
		if(customerService.add(customer) <= 0){
			retMap.put("type", "error");
			retMap.put("msg", "注册失败，请联系管理员！");
			return retMap;
		}
		retMap.put("type", "success");
		retMap.put("msg", "注册成功!");
		return retMap;
	}
	
	/**
	 * 判断用户名是否存在
	 * @param name
	 * @param id
	 * @return
	 */
	private boolean isExist(String name){
		Customer customer = customerService.findByName(name);
		if(customer == null)return false;
		return true;
	}

}
