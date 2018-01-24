package com.mplescano.webapp.emailweb.controller;

import java.io.File;

public class AttachedBean {
	
	private String filenameAttached;
	
	private File fileAttached;
	
	public String getFilenameAttached() {
		return filenameAttached;
	}
	public void setFilenameAttached(String filenameAttached) {
		this.filenameAttached = filenameAttached;
	}
	public File getFileAttached() {
		return fileAttached;
	}
	public void setFileAttached(File fileAttached) {
		this.fileAttached = fileAttached;
	}
	

}
