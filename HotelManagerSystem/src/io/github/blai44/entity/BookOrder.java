package io.github.blai44.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 预订订单实体类
 * @author blai
 *
 */
@Component
public class BookOrder {

	private Long id;
	private Long customerId;//客户Id
	private Long roomTypeId;//房间类型ID
	private String name;
	private String idCard;//身份证号
	private String phoneNum;//手机号
	private int status;//状态，0表示预订中，1表示已入住，2表示结算离店
	private String arrivedDate;//入住日期
	private String leaveDate;//离店日期
	private Date bookTime;//预订日期
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
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
	public Date getBookTime() {
		return bookTime;
	}
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}