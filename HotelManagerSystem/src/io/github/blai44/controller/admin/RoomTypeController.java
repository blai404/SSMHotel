package io.github.blai44.controller.admin;

import io.github.blai44.entity.RoomType;
import io.github.blai44.page.admin.Page;
import io.github.blai44.service.RoomTypeService;

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
 * �������͹����̨������
 * @author blai
 *
 */
@Controller
@RequestMapping("/admin/room_type")
public class RoomTypeController {

	@Autowired
	private RoomTypeService roomTypeService;
	
	/**
	 * �������͹����б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("room_type/list");
		return model;
	}
	
	/**
	 * ��ҳ��ȡ����������Ϣ
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(name="name",defaultValue="")String name,
			@RequestParam(name="status",required=false)Integer status,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("status", status);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", roomTypeService.findList(queryMap));
		ret.put("total", roomTypeService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ����������Ϣ��Ӳ���
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(RoomType roomType){
		Map<String, String> ret = new HashMap<String, String>();
		if(roomType == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ķ���������Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(roomType.getName())){
			ret.put("type", "error");
			ret.put("msg", "�����������Ʋ���Ϊ�գ�");
			return ret;
		}
		roomType.setAvilableNum(roomType.getRoomNum());//Ĭ�Ϸ��������ڿ��÷�����
		roomType.setBookNum(0);//������Ԥ����0
		roomType.setLivedNum(0);//��������ס��0
		if(roomTypeService.add(roomType) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ���");
		return ret;
	}
	
	/**
	 * ����������Ϣ�༭����
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(RoomType roomType){
		Map<String, String> ret = new HashMap<String, String>();
		if(roomType == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ķ���������Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(roomType.getName())){
			ret.put("type", "error");
			ret.put("msg", "�����������Ʋ���Ϊ�գ�");
			return ret;
		}
		RoomType existRoomType = roomTypeService.find(roomType.getId());
		if(existRoomType == null){
			ret.put("type", "error");
			ret.put("msg", "δ�ҵ������ݣ�");
			return ret;
		}
		int offset = roomType.getRoomNum() - existRoomType.getRoomNum();
		roomType.setAvilableNum(existRoomType.getAvilableNum() + offset);
		if(roomType.getAvilableNum() <= 0){
			roomType.setAvilableNum(0);//û�п��÷���
			roomType.setStatus(0);//��������
			if(roomType.getAvilableNum() + existRoomType.getLivedNum() + existRoomType.getBookNum() > roomType.getRoomNum()){
				ret.put("type", "error");
				ret.put("msg", "���������ò�����");
				return ret;
			}
		}else{
			roomType.setStatus(1);
		}
		if(roomTypeService.edit(roomType) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}
	
	/**
	 * ����������Ϣɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ���ķ���������Ϣ��");
			return ret;
		}
		try {
			if(roomTypeService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
			}
		} catch (Exception e) {
			ret.put("type", "error");
			ret.put("msg", "����ɾ���÷��������¹��������з�����Ϣ��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ���");
		return ret;
	}
}
