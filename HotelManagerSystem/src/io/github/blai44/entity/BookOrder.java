package io.github.blai44.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Ԥ������ʵ����
 * @author blai
 *
 */
@Component
public class BookOrder {

	private Long id;
	private Long customerId;//�ͻ�Id
	private Long roomTypeId;//��������ID
	private String name;
	private String idCard;//���֤��
	private String phoneNum;//�ֻ���
	private int status;//״̬��0��ʾԤ���У�1��ʾ����ס��2��ʾ�������
	private String arrivedDate;//��ס����
	private String leaveDate;//�������
	private Date bookTime;//Ԥ������
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