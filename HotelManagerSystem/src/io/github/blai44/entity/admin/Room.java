package io.github.blai44.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 房间类型实体类
 * @author blai
 *
 */
@Component
public class Room {

	private Long id;
	private String photo;
	private String sn;//房间编号
	private Long roomTypeId;//房型id
	private Long floorId;//所属楼层id
	private int status;//房型状态，0表示可入住，1表示已入住，2表示打扫中
	private String remark;//房型备注
	
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
