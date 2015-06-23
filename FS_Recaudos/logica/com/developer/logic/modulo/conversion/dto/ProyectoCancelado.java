package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class ProyectoCancelado {
	
	Long    prca_proy 			;
	Long    prca_plan_sifi 		;
	String  prca_descri 		;	
	Date    prca_fcrea 			;
	public Long getPrca_proy() {
		return prca_proy;
	}
	public void setPrca_proy(Long prca_proy) {
		this.prca_proy = prca_proy;
	}
	public Long getPrca_plan_sifi() {
		return prca_plan_sifi;
	}
	public void setPrca_plan_sifi(Long prca_plan_sifi) {
		this.prca_plan_sifi = prca_plan_sifi;
	}
	public String getPrca_descri() {
		return prca_descri;
	}
	public void setPrca_descri(String prca_descri) {
		this.prca_descri = prca_descri;
	}
	public Date getPrca_fcrea() {
		return prca_fcrea;
	}
	public void setPrca_fcrea(Date prca_fcrea) {
		this.prca_fcrea = prca_fcrea;
	}
	
	

}
