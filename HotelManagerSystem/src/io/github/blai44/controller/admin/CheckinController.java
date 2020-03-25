package io.github.blai44.controller.admin;

import io.github.blai44.entity.BookOrder;
import io.github.blai44.entity.RoomType;
import io.github.blai44.entity.admin.Checkin;
import io.github.blai44.entity.admin.Room;
import io.github.blai44.page.admin.Page;
import io.github.blai44.service.BookOrderService;
import io.github.blai44.service.RoomTypeService;
import io.github.blai44.service.admin.CheckinService;
import io.github.blai44.service.admin.RoomService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
 * ��ס�����̨������
 * @author blai
 *
 */
@Controller
@RequestMapping("/admin/checkin")
public class CheckinController {

	@Autowired
	private CheckinService checkinService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private BookOrderService bookOrderService;
	
	/**
	 * ��ס�����б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.addObject("roomList", roomService.findAll());
		model.setViewName("checkin/list");
		return model;
	}
	
	/**
	 * ��ҳ��ȡ��ס��Ϣ
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(name="name",defaultValue="")String name,
			@RequestParam(name="roomId",defaultValue="")Long roomId,
			@RequestParam(name="roomTypeId",defaultValue="")Long roomTypeId,
			@RequestParam(name="idCard",defaultValue="")String idCard,
			@RequestParam(name="phoneNum",defaultValue="")String phoneNum,
			@RequestParam(name="status",required=false)Integer status,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("status", status);
		queryMap.put("roomId", roomId);
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("idCard", idCard);
		queryMap.put("phoneNum", phoneNum);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", checkinService.findList(queryMap));
		ret.put("total", checkinService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��ס��Ϣ��Ӳ���
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Checkin checkin,
			@RequestParam(name="bookOrderId",required=false) Long bookOrderId
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(checkin == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����ס��Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getName())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����Ʋ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getPhoneNum())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ���ֻ��Ų���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����֤�Ų���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getArrivedDate())){
			ret.put("type", "error");
			ret.put("msg", "����ʱ�䲻��Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "���ʱ�䲻��Ϊ�գ�");
			return ret;
		}
		if(checkin.getRoomId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ס���䲻��Ϊ�գ�");
			return ret;
		}
		if(checkin.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "Ԥ�����Ͳ���Ϊ�գ�");
			return ret;
		}
		checkin.setCreateTime(new Date());
		if(checkinService.add(checkin) <= 0){
			ret.put("type", "ercheckinror");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		//��ס�ɹ����޸��йظ÷��͵�������Ϣ������ס��ǰ̨�����Ԥ����ס
		RoomType roomType = roomTypeService.find(checkin.getRoomTypeId());
		if(bookOrderId != null){
			//Ԥ�����˵���ס
			BookOrder bookOrder = bookOrderService.find(bookOrderId);
			bookOrder.setStatus(1);
			bookOrderService.edit(bookOrder);
			if(roomType != null){
				roomType.setBookNum(roomType.getBookNum() - 1);
			}
		}else{
			if(roomType != null){
				roomType.setAvilableNum(roomType.getAvilableNum() - 1);
			}
		}
		roomType.setLivedNum(roomType.getLivedNum() + 1);;
		roomTypeService.updateNum(roomType);
		if(roomType.getAvilableNum() == 0){
			roomType.setStatus(0);
			roomTypeService.edit(roomType);
		}
		Room room = roomService.find(checkin.getRoomId());
		if(room != null){
			//�ѷ���״̬���ó�����ס
			room.setStatus(1);
			roomService.edit(room);
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ���");
		return ret;
	}
	
	/**
	 * ��ס��Ϣ�༭����
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Checkin checkin,
			@RequestParam(name="bookOrderId",required=false) Long bookOrderId){
		Map<String, String> ret = new HashMap<String, String>();
		if(checkin == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����ס��Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getName())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����Ʋ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getPhoneNum())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ���ֻ��Ų���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "��ϵ�����֤�Ų���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getArrivedDate())){
			ret.put("type", "error");
			ret.put("msg", "����ʱ�䲻��Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "���ʱ�䲻��Ϊ�գ�");
			return ret;
		}
		if(checkin.getRoomId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ס���䲻��Ϊ�գ�");
			return ret;
		}
		if(checkin.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "Ԥ�����Ͳ���Ϊ�գ�");
			return ret;
		}
		Checkin existCheckin = checkinService.find(checkin.getId());
		if(existCheckin == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ����ȷ����ס��Ϣ��");
			return ret;
		}
		if(checkinService.edit(checkin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		RoomType oldRoomType = roomTypeService.find(existCheckin.getRoomTypeId());
		RoomType newRoomType = roomTypeService.find(checkin.getRoomTypeId());
		if(existCheckin.getRoomTypeId().longValue() != checkin.getRoomTypeId().longValue()){
			oldRoomType.setLivedNum(oldRoomType.getLivedNum() - 1);
			newRoomType.setLivedNum(newRoomType.getLivedNum() + 1);
			oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
			newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
			roomTypeService.updateNum(oldRoomType);
			if(oldRoomType.getAvilableNum() > 0){
				oldRoomType.setStatus(1);
			}
			//�޸��µķ��͵Ŀ�������Ԥ����
			roomTypeService.updateNum(newRoomType);
			if(newRoomType.getAvilableNum() <= 0){
				newRoomType.setStatus(0);
			}
		}
		
		roomTypeService.edit(oldRoomType);
		roomTypeService.edit(newRoomType);
		//�жϷ����Ƿ����仯
		if(checkin.getRoomId().longValue() != existCheckin.getRoomId().longValue()){
			//��ʾ���䷢���˱仯
			Room oldRoom = roomService.find(existCheckin.getRoomId());
			Room newRoom = roomService.find(checkin.getRoomId());
			oldRoom.setStatus(0);
			newRoom.setStatus(1);
			roomService.edit(newRoom);
			roomService.edit(oldRoom);
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}
	
	/**
	 * �˷�����
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/checkout",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkout(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫ���ݣ�");
			return ret;
		}
		Checkin checkin = checkinService.find(id);
		if(checkin == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ����ȷ�����ݣ�");
			return ret;
		}
		checkin.setStatus(1);
		if(checkinService.edit(checkin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�˷�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		//�޸ķ���
		RoomType roomType = roomTypeService.find(checkin.getRoomTypeId());
		if(roomType != null){
			roomType.setLivedNum(roomType.getLivedNum() - 1);
			roomType.setAvilableNum(roomType.getAvilableNum() + 1);
			if(roomType.getStatus() == 0){
				roomType.setStatus(1);
			}else if(roomType.getAvilableNum() > roomType.getRoomNum()){
				roomType.setAvilableNum(roomType.getRoomNum());
			}
			roomTypeService.updateNum(roomType);
			roomTypeService.edit(roomType);
		}
		//�޸ķ���
		Room room = roomService.find(checkin.getRoomId());
		if(room != null){
			room.setStatus(2);
			roomService.edit(room);
		}
		
		//�޸�Ԥ��״̬
		if(checkin.getBookOrderId() != null){
			BookOrder bookOrder = bookOrderService.find(checkin.getBookOrderId());
			bookOrder.setStatus(2);
			bookOrderService.edit(bookOrder);
		}
		
		ret.put("type", "success");
		ret.put("msg", "�˷��ɹ���");
		return ret;
	}
	
	/**
	 * ���ݷ������ͻ�ȡ����
	 * @param roomTypeId
	 * @return
	 */
	@RequestMapping(value="/load_room_list",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> load_room_list(Long roomTypeId){
		List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("status", 0);
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		List<Room> roomList = roomService.findList(queryMap);
		for(Room room : roomList){
			Map<String, Object> option = new HashMap<String, Object>();
			option.put("value", room.getId());
			option.put("text", room.getSn());
			retList.add(option);
		}
		return retList;
	}
}
	

