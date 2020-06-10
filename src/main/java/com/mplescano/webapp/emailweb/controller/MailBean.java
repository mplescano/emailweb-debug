package com.mplescano.webapp.emailweb.controller;

public class MailBean implements Cloneable {
	
	private String from;
	
	private String to;

	private String subject;
	
	private String body;
	
	private Integer priority;
	
	private String replyTo;
	
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
