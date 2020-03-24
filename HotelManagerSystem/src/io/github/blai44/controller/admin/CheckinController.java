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
 * 入住管理后台控制器
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
	 * 入住管理列表页面
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
	 * 分页获取入住信息
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
	 * 入住信息添加操作
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
			ret.put("msg", "请填写正确的入住信息！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getName())){
			ret.put("type", "error");
			ret.put("msg", "联系人名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getPhoneNum())){
			ret.put("type", "error");
			ret.put("msg", "联系人手机号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "联系人身份证号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getArrivedDate())){
			ret.put("type", "error");
			ret.put("msg", "到店时间不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "离店时间不能为空！");
			return ret;
		}
		if(checkin.getRoomId() == null){
			ret.put("type", "error");
			ret.put("msg", "入住房间不能为空！");
			return ret;
		}
		if(checkin.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "预订房型不能为空！");
			return ret;
		}
		checkin.setCreateTime(new Date());
		if(checkinService.add(checkin) <= 0){
			ret.put("type", "ercheckinror");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		//入住成功后修改有关该房型的数量信息、、入住分前台办理和预订入住
		RoomType roomType = roomTypeService.find(checkin.getRoomTypeId());
		if(bookOrderId != null){
			//预订好了的入住
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
			//把房间状态设置成已入住
			room.setStatus(1);
			roomService.edit(room);
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	/**
	 * 入住信息编辑操作
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
			ret.put("msg", "请填写正确的入住信息！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getName())){
			ret.put("type", "error");
			ret.put("msg", "联系人名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getPhoneNum())){
			ret.put("type", "error");
			ret.put("msg", "联系人手机号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "联系人身份证号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getArrivedDate())){
			ret.put("type", "error");
			ret.put("msg", "到店时间不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(checkin.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "离店时间不能为空！");
			return ret;
		}
		if(checkin.getRoomId() == null){
			ret.put("type", "error");
			ret.put("msg", "入住房间不能为空！");
			return ret;
		}
		if(checkin.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "预订房型不能为空！");
			return ret;
		}
		Checkin existCheckin = checkinService.find(checkin.getId());
		if(existCheckin == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的入住信息！");
			return ret;
		}
		if(checkinService.edit(checkin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员！");
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
			//修改新的房型的可用数及预订数
			roomTypeService.updateNum(newRoomType);
			if(newRoomType.getAvilableNum() <= 0){
				newRoomType.setStatus(0);
			}
		}
		
		roomTypeService.edit(oldRoomType);
		roomTypeService.edit(newRoomType);
		//判断房间是否发生变化
		if(checkin.getRoomId().longValue() != existCheckin.getRoomId().longValue()){
			//表示房间发生了变化
			Room oldRoom = roomService.find(existCheckin.getRoomId());
			Room newRoom = roomService.find(checkin.getRoomId());
			oldRoom.setStatus(0);
			newRoom.setStatus(1);
			roomService.edit(newRoom);
			roomService.edit(oldRoom);
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
	}
	
	/**
	 * 退房管理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/checkout",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkout(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要数据！");
			return ret;
		}
		Checkin checkin = checkinService.find(id);
		if(checkin == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的数据！");
			return ret;
		}
		checkin.setStatus(1);
		if(checkinService.edit(checkin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "退房失败，请联系管理员！");
			return ret;
		}
		//修改房型
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
		//修改房间
		Room room = roomService.find(checkin.getRoomId());
		if(room != null){
			room.setStatus(2);
			roomService.edit(room);
		}
		
		//修改预订状态
		if(checkin.getBookOrderId() != null){
			BookOrder bookOrder = bookOrderService.find(checkin.getBookOrderId());
			bookOrder.setStatus(2);
			bookOrderService.edit(bookOrder);
		}
		
		ret.put("type", "success");
		ret.put("msg", "退房成功！");
		return ret;
	}
	
	/**
	 * 根据房间类型获取房间
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
	

