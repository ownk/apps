package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class EncargoFiduciarioSIFI {
	
	Long 	plts_fond			;
	Long    plts_plan 			;
	String  plts_esta 			;
	Long    plts_fdei 			;
	String  plts_fdei_tpid 		;
	String  plts_fdei_titu 		;
	Date    plts_fcrea 			;
	Date    plts_freca 			;
	
	
	
	
	public Long getPlts_fond() {
		return plts_fond;
	}
	public void setPlts_fond(Long plts_fond) {
		this.plts_fond = plts_fond;
	}
	public Long getPlts_plan() {
		return plts_plan;
	}
	public void setPlts_plan(Long plts_plan) {
		this.plts_plan = plts_plan;
	}
	public String getPlts_esta() {
		return plts_esta;
	}
	public void setPlts_esta(String plts_esta) {
		this.plts_esta = plts_esta;
	}
	public Long getPlts_fdei() {
		return plts_fdei;
	}
	public void setPlts_fdei(Long plts_fdei) {
		this.plts_fdei = plts_fdei;
	}
	
	public Date getPlts_fcrea() {
		return plts_fcrea;
	}
	public void setPlts_fcrea(Date plts_fcrea) {
		this.plts_fcrea = plts_fcrea;
	}
	public Date getPlts_freca() {
		return plts_freca;
	}
	public void setPlts_freca(Date plts_freca) {
		this.plts_freca = plts_freca;
	}
	public String getPlts_fdei_tpid() {
		return plts_fdei_tpid;
	}
	public void setPlts_fdei_tpid(String plts_fdei_tpid) {
		this.plts_fdei_tpid = plts_fdei_tpid;
	}
	public String getPlts_fdei_titu() {
		return plts_fdei_titu;
	}
	public void setPlts_fdei_titu(String plts_fdei_titu) {
		this.plts_fdei_titu = plts_fdei_titu;
	}
	
	

}
