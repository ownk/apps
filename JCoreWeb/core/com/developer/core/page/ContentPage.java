package com.developer.core.page;

public class ContentPage {
	
	String contentType = null;
	String content = null;
	String enconding = null;
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getEnconding() {
		return enconding;
	}
	
	public void setEnconding(String enconding) {
		this.enconding = enconding;
	}

}
