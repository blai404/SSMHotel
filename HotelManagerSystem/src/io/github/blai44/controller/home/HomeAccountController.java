package io.github.blai44.controller.home;

import io.github.blai44.entity.BookOrder;
import io.github.blai44.entity.Customer;
import io.github.blai44.entity.RoomType;
import io.github.blai44.service.BookOrderService;
import io.github.blai44.service.CustomerService;
import io.github.blai44.service.RoomTypeService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * ǰ̨�û�������
 * @author blai
 *
 */
@RequestMapping("/home/account")
@Controller
public class HomeAccountController {

	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookOrderService bookOrderService;
	
	/**
	 * ǰ̨�û�������ҳ
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model, HttpServletRequest request ){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("customerId", customer.getId());
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("bookOrderList", bookOrderService.findList(queryMap));
		model.addObject("roomTypeList", roomTypeService.findList(queryMap));
		model.setViewName("home/account/index");
		return model;
	}
	
	/**
	 * Ԥ������ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/book_order",method=RequestMethod.GET)
	public ModelAndView bookOrder(ModelAndView model, Long roomTypeId){
		model.addObject("roomType", roomTypeService.find(roomTypeId));
		model.setViewName("home/account/book_order");
		return model;
	}
	
	/**
	 * Ԥ����Ϣ�ύ
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/book_order",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> bookOrderAct(BookOrder bookOrder, HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(bookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��Ԥ��������Ϣ��");
			return ret;
		}
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			ret.put("type", "error");
			ret.put("msg", "�ͻ�����Ϊ�գ�");
			return ret;
		}
		bookOrder.setCustomerId(customer.getId());
		if(StringUtils.isEmpty(bookOrder.getName())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����Ʋ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getPhoneNum())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ���ֻ��Ų���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����֤�Ų���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getArrivedDate())){
			ret.put("type", "error");
			ret.put("msg", "����ʱ�䲻��Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "���ʱ�䲻��Ϊ�գ�");
			return ret;
		}
		if(bookOrder.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "Ԥ�����Ͳ���Ϊ�գ�");
			return ret;
		}
		bookOrder.setBookTime(new Date());
		bookOrder.setStatus(0);
		if(bookOrderService.add(bookOrder) <= 0){
			ret.put("type", "error");
			ret.put("msg", "Ԥ��ʧ�ܣ�����ϵ�Ƶ�ǰ̨��");
			return ret;
		}
		//Ԥ���ɹ����޸��йظ÷��͵�������Ϣ
		RoomType roomType = roomTypeService.find(bookOrder.getRoomTypeId());
		if(roomType != null){
			roomType.setBookNum(roomType.getBookNum() + 1);
			roomType.setAvilableNum(roomType.getAvilableNum() - 1);
			roomTypeService.updateNum(roomType);
			if(roomType.getAvilableNum() == 0){
				roomType.setStatus(0);
				roomTypeService.edit(roomType);
			}
		}
		ret.put("type", "success");
		ret.put("msg", "Ԥ���ɹ���");
		return ret;
	}
	
	/**
	 * �޸ĸ�����Ϣ�ύ
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/update_info",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateInfoAct(Customer customer, HttpServletRequest request){
		Map<String, String> retMap = new HashMap<String, String>();
		if(customer == null){
			retMap.put("type", "error");
			retMap.put("msg", "����д�û���Ϣ");
			return retMap;
		}
		if(StringUtils.isEmpty(customer.getName())){
			retMap.put("type", "error");
			retMap.put("msg", "����д�û�����");
			return retMap;
		}
		Customer loginedCustomer = (Customer) request.getSession().getAttribute("customer");
		if(isExist(customer.getName(), loginedCustomer.getId())){
			retMap.put("type", "error");
			retMap.put("msg", "���û����Ѵ��ڣ����������룡");
			return retMap;
		}
		loginedCustomer.setAddress(customer.getAddress());
		loginedCustomer.setIdCard(customer.getIdCard());
		loginedCustomer.setPhoneNum(customer.getPhoneNum());
		loginedCustomer.setName(customer.getName());
		loginedCustomer.setRealName(customer.getRealName());
		if(customerService.edit(loginedCustomer) <= 0){
			retMap.put("type", "error");
			retMap.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return retMap;
		}
		request.getSession().setAttribute("customer", loginedCustomer);
		retMap.put("type", "success");
		retMap.put("msg", "�޸ĳɹ�!");
		return retMap;
	}
	
	/**
	 * �޸������ύ
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/update_pwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatePwdAct(String oldPassword, String newPassword, HttpServletRequest request){
		Map<String, String> retMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(oldPassword)){
			retMap.put("type", "error");
			retMap.put("msg", "����дԭ���룡");
			return retMap;
		}
		if(StringUtils.isEmpty(newPassword)){
			retMap.put("type", "error");
			retMap.put("msg", "����д�����룡");
			return retMap;
		}
		Customer loginedCustomer = (Customer) request.getSession().getAttribute("customer");
		if(oldPassword.equals(loginedCustomer.getPassword())){
			retMap.put("type", "error");
			retMap.put("msg", "ԭ���벻��ȷ��");
			return retMap;
		}
		loginedCustomer.setPassword(newPassword);
		if(customerService.edit(loginedCustomer) <= 0){
			retMap.put("type", "error");
			retMap.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return retMap;
		}
		retMap.put("type", "success");
		retMap.put("msg", "�޸ĳɹ�!");
		return retMap;
	}
	
	/**
	 * �ж��û����Ƿ����
	 * @param name
	 * @param id
	 * @return
	 */
	private boolean isExist(String name, Long id){
		Customer customer = customerService.findByName(name);
		if(customer == null)return false;
		if(customer.getId().longValue() == id){
			return false;
		}
		return true;
	}

}
