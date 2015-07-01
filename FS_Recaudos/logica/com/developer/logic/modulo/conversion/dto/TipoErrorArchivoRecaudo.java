package com.developer.logic.modulo.conversion.dto;

public class TipoErrorArchivoRecaudo {
	
	public static final Long TPER_PLAN_SIFI_SIN_ESTADO 	= new Long(1);
	public static final Long TPER_PLAN_SIFI_NULO 		= new Long(2);
	public static final Long TPER_REF_NULO 				= new Long(3);
	public static final Long TPER_PRCA_PLAN_SIFI_NULO 	= new Long(4);
	public static final Long TPER_PLAN_SIN_ESTADO 		= new Long(5);
	public static final Long TPER_PLAN_FRDP_MULTIPLE 	= new Long(6);
	public static final Long TPER_PLAN_DPFD_NULO 		= new Long(7);
	public static final Long TPER_REGLA_NOEXISTE 		= new Long(8);
		
	
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
