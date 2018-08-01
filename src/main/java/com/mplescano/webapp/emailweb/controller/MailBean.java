package com.mplescano.webapp.emailweb.controller;

import java.util.ArrayList;
import java.util.List;

/*import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;*/

public class MailBean implements Cloneable {
	
	/*@NotBlank
	@Email*/
	private String from;
	
	/*@NotBlank
	@Email*/
	private String to;

	/*@NotBlank
	@Size(min=2)*/
	private String subject;
	
	private String body;
	
	private Integer priority;
	
	private String replyTo;
	
	//private List<AttachedBean> lstAttached = new ArrayList<AttachedBean>(); 
		
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
	
	/*public List<AttachedBean> getLstAttached() {
		return lstAttached;
	}
	public void setLstAttached(List<AttachedBean> lstAttached) {
		this.lstAttached = lstAttached;
	}*/
	
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	@Override
	public MailBean clone() throws CloneNotSupportedException {
		return (MailBean) super.clone();
	}
}
