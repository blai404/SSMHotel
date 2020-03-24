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
 * 预订订单管理后台控制器
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
	 * 预订订单管理列表页面
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
	 * 分页获取预订订单信息
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
	 * 预订订单信息添加操作
	 * @param roomType
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(BookOrder bookOrder){
		Map<String, String> ret = new HashMap<String, String>();
		if(bookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的预订订单信息！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getName())){
			ret.put("type", "error");
			ret.put("msg", "联系人名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getPhoneNum())){
			ret.put("type", "error");
			ret.put("msg", "联系人手机号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "联系人身份证号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getArrivedDate())){
			ret.put("type", "error");
			ret.put("msg", "到店时间不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "离店时间不能为空！");
			return ret;
		}
		if(bookOrder.getCustomerId() == null){
			ret.put("type", "error");
			ret.put("msg", "客户不能为空！");
			return ret;
		}
		if(bookOrder.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "预订房型不能为空！");
			return ret;
		}
		bookOrder.setBookTime(new Date());
		if(bookOrderService.add(bookOrder) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		//预订成功后修改有关该房型的数量信息
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
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	/**
	 * 预订订单信息编辑操作
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(BookOrder bookOrder){
		Map<String, String> ret = new HashMap<String, String>();
		if(bookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的预订订单信息！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getName())){
			ret.put("type", "error");
			ret.put("msg", "联系人名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getPhoneNum())){
			ret.put("type", "error");
			ret.put("msg", "联系人手机号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getIdCard())){
			ret.put("type", "error");
			ret.put("msg", "联系人身份证号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getArrivedDate())){
			ret.put("type", "error");
			ret.put("msg", "到店时间不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getLeaveDate())){
			ret.put("type", "error");
			ret.put("msg", "离店时间不能为空！");
			return ret;
		}
		if(bookOrder.getCustomerId() == null){
			ret.put("type", "error");
			ret.put("msg", "客户不能为空！");
			return ret;
		}
		if(bookOrder.getRoomTypeId() == null){
			ret.put("type", "error");
			ret.put("msg", "预订房型不能为空！");
			return ret;
		}
		BookOrder existBookOrder = bookOrderService.find(bookOrder.getId());
		if(existBookOrder == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的订单信息！");
			return ret;
		}
		if(bookOrderService.edit(bookOrder) <= 0){
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员！");
			return ret;
		}
		//判断房型是否发生变化
		if(existBookOrder.getRoomTypeId().longValue() != bookOrder.getRoomTypeId().longValue()){
			//房型发生了变化
			//首先恢复原来的房型预订数及可用数
			RoomType oldRoomType = roomTypeService.find(existBookOrder.getRoomTypeId());
			oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
			oldRoomType.setBookNum(oldRoomType.getBookNum() - 1);
			roomTypeService.updateNum(oldRoomType);
			if(oldRoomType.getAvilableNum() > 0){
				oldRoomType.setStatus(1);
				roomTypeService.edit(oldRoomType);
			}
			//修改新的房型的可用数及预订数
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
		ret.put("msg", "修改成功！");
		return ret;
	}
}
