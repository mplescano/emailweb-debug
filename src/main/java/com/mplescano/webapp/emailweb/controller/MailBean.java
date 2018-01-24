package com.mplescano.webapp.emailweb.controller;

import java.util.ArrayList;
import java.util.List;

public class MailBean implements Cloneable {
	
	private String from;
	private String to;
	private String toCC;
	private String toBCC;
	private String subject;
	private String body;	
	
	//private List<AttachedBean> lstAttached = new ArrayList<AttachedBean>(); 
		
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getToCC() {
		return toCC;
	}
	public void setToCC(String toCC) {
		this.toCC = toCC;
	}
	public String getSubject() {
		return subject;
	}
	public String getToBCC() {
		return toBCC;
	}
	public void setToBCC(String toBCC) {
		this.toBCC = toBCC;
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
	
	@Override
	public MailBean clone() throws CloneNotSupportedException {
		return (MailBean) super.clone();
	}
}
