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
 * ǰ̨��ҳ������
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
	 * ǰ̨��ҳ
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
	 * ǰ̨��¼ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("home/index/login");
		return model;
	}
	
	/**
	 * ��¼��Ϣ�ύ
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(Customer customer, String vcode, HttpServletRequest request){
		Map<String, String> retMap = new HashMap<String, String>();
		if(customer == null){
			retMap.put("type", "error");
			retMap.put("msg", "����д�û���Ϣ");
			return retMap;
		}
		if(StringUtils.isEmpty(vcode)){
			retMap.put("type", "error");
			retMap.put("msg", "����д��֤��");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "����д�û���");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "����д����");
			return retMap;
		}
		Object loginCpacha = request.getSession().getAttribute("customerLoginCpacha");
		if(loginCpacha == null){
			retMap.put("type", "error");
			retMap.put("msg", "��֤��ʧЧ");
			return retMap;
		}
		if(!vcode.equalsIgnoreCase(loginCpacha.toString())){
			retMap.put("type", "error");
			retMap.put("msg", "��֤�����");
			return retMap;
		}
		Customer findByName = customerService.findByName(customer.getName());
		if(findByName == null){
			retMap.put("type", "error");
			retMap.put("msg", "�û��������ڣ�");
			return retMap;
		}
		if(!customer.getPassword().equals(findByName.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "���벻��ȷ��");
			return retMap;
		}
		if(findByName.getStatus() == -1){
			retMap.put("type", "error");
			retMap.put("msg", "���û��ѱ����ã�����ϵ����Ա��");
			return retMap;
		}
		request.getSession().setAttribute("customer", findByName);
		request.getSession().setAttribute("customerLoginCpacha", null);
		retMap.put("type", "success");
		retMap.put("msg", "��½�ɹ�!");
		return retMap;
	}
	
	/**
	 * ǰ̨ע������
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
	 * ǰ̨ע��ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.GET)
	public ModelAndView reg(ModelAndView model){
		model.setViewName("home/index/reg");
		return model;
	}
	
	/**
	 * ע����Ϣ�ύ
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> regAct(Customer customer, HttpServletRequest request){
		Map<String, String> retMap = new HashMap<String, String>();
		if(customer == null){
			retMap.put("type", "error");
			retMap.put("msg", "����д�û���Ϣ");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getPhoneNum())){
			retMap.put("type", "error");
			retMap.put("msg", "�ֻ��Ų���Ϊ�գ�");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "����д�û�����");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "����д����");
			return retMap;
		}
		if(isExist(customer.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "���û����Ѵ��ڣ����������룡");
			return retMap;
		}
		if(customerService.add(customer) <= 0){
			retMap.put("type", "error");
			retMap.put("msg", "ע��ʧ�ܣ�����ϵ����Ա��");
			return retMap;
		}
		retMap.put("type", "success");
		retMap.put("msg", "ע��ɹ�!");
		return retMap;
	}
	
	/**
	 * �ж��û����Ƿ����
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
