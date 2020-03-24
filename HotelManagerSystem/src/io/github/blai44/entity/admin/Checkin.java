package io.github.blai44.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 入住实体类
 * @author blai
 *
 */
@Component
public class Checkin {

	private Long id;
	private Long roomId;//房间Id
	private Long roomTypeId;//房间类型ID
	private Long bookOrderId;//预订订单id，可为空
	private Float checkinPrice;//入住价格
	private String name;
	private String idCard;//身份证号
	private String phoneNum;//手机号
	private int status;//状态，0表示入住中，1表示已结算离店
	private String arrivedDate;//入住日期
	private String leaveDate;//离店日期
	private Date createTime;//创建日期
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Long getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public Float getCheckinPrice() {
		return checkinPrice;
	}
	public void setCheckinPrice(Float checkinPrice) {
		this.checkinPrice = checkinPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getArrivedDate() {
		return arrivedDate;
	}
	public void setArrivedDate(String arrivedDate) {
		this.arrivedDate = arrivedDate;
	}
	public String getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getBookOrderId() {
		return bookOrderId;
	}
	public void setBookOrderId(Long bookOrderId) {
		this.bookOrderId = bookOrderId;
	}
	
}