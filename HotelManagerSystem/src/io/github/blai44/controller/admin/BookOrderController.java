package io.github.blai44.controller.admin;

import io.github.blai44.entity.BookOrder;
import io.github.blai44.entity.RoomType;
import io.github.blai44.page.admin.Page;
import io.github.blai44.service.BookOrderService;
import io.github.blai44.service.CustomerService;
import io.github.blai44.service.RoomTypeService;

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
 * Ԥ�����������̨������
 * @author blai
 *
 */
@Controller
@RequestMapping("/admin/book_order")
public class BookOrderController {

	@Autowired
	private BookOrderService bookOrderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	/**
	 * Ԥ�����������б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.addObject("customerList", customerService.findAll());
		model.setViewName("book_order/list");
		return model;
	}
	
	/**
	 * ��ҳ��ȡԤ��������Ϣ
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(name="name",defaultValue="")String name,
			@RequestParam(name="customerId",defaultValue="")Long customerId,
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
		queryMap.put("customerId", customerId);
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("idCard", idCard);
		queryMap.put("phoneNum", phoneNum);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", bookOrderService.findList(queryMap));
		ret.put("total", bookOrderService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * Ԥ��������Ϣ��Ӳ���
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(BookOrder bookOrder){
		Map<String, String> ret = new HashMap<String, String>();
		if(bookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��Ԥ��������Ϣ��");
			return ret;
		}
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
		if(bookOrder.getCustomerId() == null){
			ret.put("type", "error");
			ret.put("msg", "�ͻ�����Ϊ�գ�");
			return ret;
		}
		if(bookOrder.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "Ԥ�����Ͳ���Ϊ�գ�");
			return ret;
		}
		bookOrder.setBookTime(new Date());
		if(bookOrderService.add(bookOrder) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա��");
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
		ret.put("msg", "��ӳɹ���");
		return ret;
	}
	
	/**
	 * Ԥ��������Ϣ�༭����
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(BookOrder bookOrder){
		Map<String, String> ret = new HashMap<String, String>();
		if(bookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��Ԥ��������Ϣ��");
			return ret;
		}
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
		if(bookOrder.getCustomerId() == null){
			ret.put("type", "error");
			ret.put("msg", "�ͻ�����Ϊ�գ�");
			return ret;
		}
		if(bookOrder.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "Ԥ�����Ͳ���Ϊ�գ�");
			return ret;
		}
		BookOrder existBookOrder = bookOrderService.find(bookOrder.getId());
		if(existBookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ����ȷ�Ķ�����Ϣ��");
			return ret;
		}
		if(bookOrderService.edit(bookOrder) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		//�жϷ����Ƿ����仯
		if(existBookOrder.getRoomTypeId().longValue() != bookOrder.getRoomTypeId().longValue()){
			//���ͷ����˱仯
			//���Ȼָ�ԭ���ķ���Ԥ������������
			RoomType oldRoomType = roomTypeService.find(existBookOrder.getRoomTypeId());
			oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
			oldRoomType.setBookNum(oldRoomType.getBookNum() - 1);
			roomTypeService.updateNum(oldRoomType);
			if(oldRoomType.getAvilableNum() > 0){
				oldRoomType.setStatus(1);
				roomTypeService.edit(oldRoomType);
			}
			//�޸��µķ��͵Ŀ�������Ԥ����
			RoomType newRoomType = roomTypeService.find(bookOrder.getRoomTypeId());
			newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
			newRoomType.setBookNum(newRoomType.getBookNum() + 1);
			roomTypeService.updateNum(newRoomType);
			if(newRoomType.getAvilableNum() <= 0){
				newRoomType.setStatus(0);
				roomTypeService.edit(newRoomType);
			}
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}
}
