package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class EstadoPlanFormulaDistribucion {
	
	Long    plec_plan 			;
	String  plec_esta 			;
	Long    plec_fdei 			;
	String  plec_fdei_tipo 		;
	Date    plec_fcrea 			;
	
	public Long getPlec_plan() {
		return plec_plan;
	}
	public void setPlec_plan(Long plec_plan) {
		this.plec_plan = plec_plan;
	}
	public String getPlec_esta() {
		return plec_esta;
	}
	public void setPlec_esta(String plec_esta) {
		this.plec_esta = plec_esta;
	}
	public Long getPlec_fdei() {
		return plec_fdei;
	}
	public void setPlec_fdei(Long plec_fdei) {
		this.plec_fdei = plec_fdei;
	}
	public String getPlec_fdei_tipo() {
		return plec_fdei_tipo;
	}
	public void setPlec_fdei_tipo(String plec_fdei_tipo) {
		this.plec_fdei_tipo = plec_fdei_tipo;
	}
	public Date getPlec_fcrea() {
		return plec_fcrea;
	}
	public void setPlec_fcrea(Date plec_fcrea) {
		this.plec_fcrea = plec_fcrea;
	}
	
	

}
