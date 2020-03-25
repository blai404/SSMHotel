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
 * �û������̨������
 * @author blai
 *
 */
@Controller
@RequestMapping("/admin/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	/**
	 * �û������б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("customer/list");
		return model;
	}
	
	/**
	 * ��ҳ��ȡ�û���Ϣ
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
	 * �û���Ϣ��Ӳ���
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Customer customer){
		Map<String, String> ret = new HashMap<String, String>();
		if(customer == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ���û���Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(customer.getName())){
			ret.put("type", "error");
			ret.put("msg", "�û����Ʋ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(customer.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "�û����벻��Ϊ�գ�");
			return ret;
		}
		if(isExist(customer.getName(), 0l)){
			ret.put("type", "error");
			ret.put("msg", "���û������Ѵ��ڣ�");
			return ret;
		}
		if(customerService.add(customer) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ���");
		return ret;
	}
	
	/**
	 * �û���Ϣ�༭����
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Customer customer){
		Map<String, String> ret = new HashMap<String, String>();
		if(customer == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ���û���Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(customer.getName())){
			ret.put("type", "error");
			ret.put("msg", "�û����Ʋ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(customer.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "�û����벻��Ϊ�գ�");
			return ret;
		}
		if(isExist(customer.getName(), customer.getId())){
			ret.put("type", "error");
			ret.put("msg", "���û������Ѵ��ڣ�");
			return ret;
		}
		if(customerService.edit(customer) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}
	
	/**
	 * �û���Ϣɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ�����û���Ϣ��");
			return ret;
		}
		try {
			if(customerService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
			}
		} catch (Exception e) {
			ret.put("type", "error");
			ret.put("msg", "����ɾ�����û��¹��������ж�����Ϣ��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ���");
		return ret;
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
		if(customer.getId().longValue() == id.longValue())return false;
		return true;
	}
}
