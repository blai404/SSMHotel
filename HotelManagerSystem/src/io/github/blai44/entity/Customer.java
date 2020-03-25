package io.github.blai44.entity;

import org.springframework.stereotype.Component;

/**
 * �ͻ�ʵ����
 * @author blai
 *
 */
@Component
public class Customer {

	private Long id;
	private String name;//��½��
	private String password;
	private String realName;//��ʵ����
	private String idCard;//���֤��
	private String phoneNum;//�ֻ���
	private String address;
	private int status;//״̬��0��ʾ���ã�1��ʾ����
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}