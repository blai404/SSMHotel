package io.github.blai44.entity.admin;

import org.springframework.stereotype.Component;

/**
 * ��������ʵ����
 * @author blai
 *
 */
@Component
public class Room {

	private Long id;
	private String photo;
	private String sn;//������
	private Long roomTypeId;//����id
	private Long floorId;//����¥��id
	private int status;//����״̬��0��ʾ����ס��1��ʾ����ס��2��ʾ��ɨ��
	private String remark;//���ͱ�ע
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Long getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public Long getFloorId() {
		return floorId;
	}
	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}