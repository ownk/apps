package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class ErrorArchivoRecaudo {
	
	Long    erar_erar 		    ;
	Long    erar_tper 		    ;
	Long    erar_aror           ;   
	Long    erar_daror_id_reg   ;   
	String  erar_error_descri   ;	
	Date    erar_fcrea 		    ;
	String  tper_descri			;
	String  tper_color		;
	
	
	public String getTper_color() {
		return tper_color;
	}
	public void setTper_color(String tper_color) {
		this.tper_color = tper_color;
	}
	public String getTper_descri() {
		return tper_descri;
	}
	public void setTper_descri(String tper_descri) {
		this.tper_descri = tper_descri;
	}
	public Long getErar_erar() {
		return erar_erar;
	}
	public void setErar_erar(Long erar_erar) {
		this.erar_erar = erar_erar;
	}
	public Long getErar_tper() {
		return erar_tper;
	}
	public void setErar_tper(Long erar_tper) {
		this.erar_tper = erar_tper;
	}
	public Long getErar_aror() {
		return erar_aror;
	}
	public void setErar_aror(Long erar_aror) {
		this.erar_aror = erar_aror;
	}
	public Long getErar_daror_id_reg() {
		return erar_daror_id_reg;
	}
	public void setErar_daror_id_reg(Long erar_daror_id_reg) {
		this.erar_daror_id_reg = erar_daror_id_reg;
	}
	public String getErar_error_descri() {
		return erar_error_descri;
	}
	public void setErar_error_descri(String erar_error_descri) {
		this.erar_error_descri = erar_error_descri;
	}
	public Date getErar_fcrea() {
		return erar_fcrea;
	}
	public void setErar_fcrea(Date erar_fcrea) {
		this.erar_fcrea = erar_fcrea;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+getErar_aror()+"."+getErar_daror_id_reg()+"."+getErar_tper()+"."+getErar_error_descri();
	}
	

}
