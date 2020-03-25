package io.github.blai44.controller.admin;

import io.github.blai44.entity.admin.Log;
import io.github.blai44.page.admin.Page;
import io.github.blai44.service.admin.LogService;

import java.util.Date;
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
 * ��־���������
 * @author blai
 *
 */
@RequestMapping("/admin/log")
@Controller
public class LogController {

	@Autowired
	private LogService logService;
	
	/**
	 * ��־����ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("log/list");
		return model;
	}
	
	/**
	 * ��ȡ��־�б�
	 * @param page
	 * @param username
	 * @param roleId
	 * @param sex
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserList(Page page,
			@RequestParam(name="content", required=false) String content
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("content", content);
		ret.put("rows", logService.findList(queryMap));
		ret.put("total", logService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��־���
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Log log){
		Map<String, String> ret = new HashMap<String, String>();
		if(log == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����־��Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(log.getContent())){
			ret.put("type", "error");
			ret.put("msg", "����д��־���ݣ�");
			return ret;
		}
		log.setCreateTime(new Date());
		if(logService.add(log) <= 0){
			ret.put("type", "error");
			ret.put("msg", "��־���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��־��ӳɹ���");
		return ret;
	}
	
	/**
	 * ����ɾ����־��Ϣ
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(name="ids", required=true) String ids
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(ids)){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ������־��Ϣ��");
			return ret;
		}
		if(ids.contains(",")){
			ids = ids.substring(0, ids.length()-1);
		}
		if(logService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "��־ɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��־ɾ���ɹ���");
		return ret;
	}
}
