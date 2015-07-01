package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class EncargoFiduciarioNoSIFI {
	
	
	public static final String PLAN_NOSIFI_ACT = "NO_SIFI_ACT";
	public static final String PLAN_NOSIFI_INACT = "NO_SIFI_INACT";

	
	Long    plns_plan 	;
	String  plns_esta 	;
	Date    plns_fcrea 	;
	
	
	public Long getPlns_plan() {
		return plns_plan;
	}
	public void setPlns_plan(Long plns_plan) {
		this.plns_plan = plns_plan;
	}
	public String getPlns_esta() {
		return plns_esta;
	}
	public void setPlns_esta(String plns_esta) {
		this.plns_esta = plns_esta;
	}
	public Date getPlns_fcrea() {
		return plns_fcrea;
	}
	public void setPlns_fcrea(Date plns_fcrea) {
		this.plns_fcrea = plns_fcrea;
	}
	
	
	

}
