package io.github.blai44.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * ��סʵ����
 * @author blai
 *
 */
@Component
public class Checkin {

	private Long id;
	private Long roomId;//����Id
	private Long roomTypeId;//��������ID
	private Long bookOrderId;//Ԥ������id����Ϊ��
	private Float checkinPrice;//��ס�۸�
	private String name;
	private String idCard;//���֤��
	private String phoneNum;//�ֻ���
	private int status;//״̬��0��ʾ��ס�У�1��ʾ�ѽ������
	private String arrivedDate;//��ס����
	private String leaveDate;//�������
	private Date createTime;//��������
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