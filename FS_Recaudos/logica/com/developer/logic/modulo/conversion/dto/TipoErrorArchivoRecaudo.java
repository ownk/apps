package com.developer.logic.modulo.conversion.dto;

public class TipoErrorArchivoRecaudo {
	
	public static final Long TPER_ESTADO_PLAN_VACIO = new Long(1);
	public static final Long TPER_PLAN_SIFI_NULO 	= new Long(2);
	public static final Long TPER_REF_NULO 			= new Long(3);
	public static final Long TPER_PRCA_PLAN_SIFI_NULO = new Long(4);
		
	
	Long    tper_tper 	; 
	String  tper_descri ;
	String  tper_color 	;
	
	
	public Long getTper_tper() {
		return tper_tper;
	}
	public void setTper_tper(Long tper_tper) {
		this.tper_tper = tper_tper;
	}
	public String getTper_descri() {
		return tper_descri;
	}
	public void setTper_descri(String tper_descri) {
		this.tper_descri = tper_descri;
	}
	public String getTper_color() {
		return tper_color;
	}
	public void setTper_color(String tper_color) {
		this.tper_color = tper_color;
	}   
	
	

}
