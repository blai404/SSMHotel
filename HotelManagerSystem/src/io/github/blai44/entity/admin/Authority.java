package io.github.blai44.entity.admin;

import org.springframework.stereotype.Component;

/**
 * Ȩ��ʵ��
 * @author blai
 *
 */
@Component
public class Authority {

	private Long id;
	private Long roleId;//��ɫID
	private Long menuId;//�˵�ID
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	
}
