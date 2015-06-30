package com.developer.logic.modulo.conversion.dto;

public class TipoTransformacionArchivoRecaudo {
	
	public static final long TPTR_PLAN_GENERICO_TAM_REF_MAX = 1;
	public static final Long TPTR_TPAR_MANEJA_VOLANTE_NO = null;
		
	
	Long    tptr_tptr 	; 
	String  tptr_descri ;
	String  tptr_color 	;
	
	
	public Long getTptr_tptr() {
		return tptr_tptr;
	}
	public void setTptr_tptr(Long tptr_tptr) {
		this.tptr_tptr = tptr_tptr;
	}
	public String getTptr_descri() {
		return tptr_descri;
	}
	public void setTptr_descri(String tptr_descri) {
		this.tptr_descri = tptr_descri;
	}
	public String getTptr_color() {
		return tptr_color;
	}
	public void setTptr_color(String tptr_color) {
		this.tptr_color = tptr_color;
	}   
	
	

}
