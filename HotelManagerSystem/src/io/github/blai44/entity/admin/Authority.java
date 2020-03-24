package io.github.blai44.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 权限实体
 * @author blai
 *
 */
@Component
public class Authority {

	private Long id;
	private Long roleId;//角色ID
	private Long menuId;//菜单ID
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
