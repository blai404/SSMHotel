package io.github.blai44.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * ϵͳ��־
 * @author blai
 *
 */
@Component
public class Log {

	private Long id;
	private String content;//��־����
	private Date createTime;//����ʱ��
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
