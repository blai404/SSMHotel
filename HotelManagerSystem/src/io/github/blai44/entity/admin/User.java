package io.github.blai44.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * @author blai
 *
 */
@Component
public class User {

	private Long id;//设置自增
	private String username;
	private String password;
	private Long roleId;//所属角色Id
	private String photo;//用户头像文件地址
	private int sex;//性别，0代表未知，1代表男，2代表女
	private Integer age;
	private String address;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
