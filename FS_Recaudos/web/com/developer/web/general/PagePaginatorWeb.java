package com.developer.web.general;

public class PagePaginatorWeb {
	double number;
	Boolean isActive;
	long id;
	
	public PagePaginatorWeb(double number, Boolean isActive ) {
		this.number = number;
		this.isActive = isActive;
		this.id = Math.round(number);
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	
	public double getNumber() {
		return number;
	}
	
	public static void main(String[] args) {
		System.out.println(Math.round(1.0));
	}
}
