package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class TransformacionArchivoRecaudo {
	
	Long    trar_trar 		    ;
	Long    trar_tptr 		    ;
	Long    trar_aror           ;
	Long    trar_daror_id_reg   ;
	String  trar_valor_orig     ;
	String  trar_valor_modi     ;
	String  trar_valor_descri   ;
	Date    trar_fcrea 		    ;
	
	String  tptr_descri			;
	String  tptr_color			;
	
	
	
	
	public String getTptr_color() {
		return tptr_color;
	}
	public void setTptr_color(String tptr_color) {
		this.tptr_color = tptr_color;
	}
	public String getTptr_descri() {
		return tptr_descri;
	}
	public void setTptr_descri(String tptr_descri) {
		this.tptr_descri = tptr_descri;
	}
	public Long getTrar_trar() {
		return trar_trar;
	}
	public void setTrar_trar(Long trar_trar) {
		this.trar_trar = trar_trar;
	}
	public Long getTrar_tptr() {
		return trar_tptr;
	}
	public void setTrar_tptr(Long trar_tptr) {
		this.trar_tptr = trar_tptr;
	}
	public Long getTrar_aror() {
		return trar_aror;
	}
	public void setTrar_aror(Long trar_aror) {
		this.trar_aror = trar_aror;
	}
	public Long getTrar_daror_id_reg() {
		return trar_daror_id_reg;
	}
	public void setTrar_daror_id_reg(Long trar_daror_id_reg) {
		this.trar_daror_id_reg = trar_daror_id_reg;
	}
	public String getTrar_valor_orig() {
		return trar_valor_orig;
	}
	public void setTrar_valor_orig(String trar_valor_orig) {
		this.trar_valor_orig = trar_valor_orig;
	}
	public String getTrar_valor_modi() {
		return trar_valor_modi;
	}
	public void setTrar_valor_modi(String trar_valor_modi) {
		this.trar_valor_modi = trar_valor_modi;
	}
	public String getTrar_valor_descri() {
		return trar_valor_descri;
	}
	public void setTrar_valor_descri(String trar_valor_descri) {
		this.trar_valor_descri = trar_valor_descri;
	}
	public Date getTrar_fcrea() {
		return trar_fcrea;
	}
	public void setTrar_fcrea(Date trar_fcrea) {
		this.trar_fcrea = trar_fcrea;
	}
	
	@Override
	public String toString() {
		
		return ""+getTrar_aror()+"."+getTrar_daror_id_reg()+"."+getTrar_tptr()+"."+getTrar_valor_descri()+"."+getTrar_valor_orig()+"."+getTrar_valor_modi();
	}
	

}
