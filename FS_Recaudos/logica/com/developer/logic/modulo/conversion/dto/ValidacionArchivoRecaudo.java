package com.developer.logic.modulo.conversion.dto;

import java.util.Date;

public class ValidacionArchivoRecaudo {
	
	Long    vlar_vlar 		    ;
	Long    vlar_tpvl 		    ;
	Long    vlar_aror           ;   
	Long    vlar_daror_id_reg   ;   
	String  vlar_valor_descri   ;	
	Date    vlar_fcrea 		    ;
	
	String 	tpvl_descri			;
	String 	tpvl_color;
	
	
	
	public String getTpvl_color() {
		return tpvl_color;
	}
	public void setTpvl_color(String tpvl_color) {
		this.tpvl_color = tpvl_color;
	}
	public String getTpvl_descri() {
		return tpvl_descri;
	}
	public void setTpvl_descri(String tpvl_descri) {
		this.tpvl_descri = tpvl_descri;
	}
	public Long getVlar_vlar() {
		return vlar_vlar;
	}
	public void setVlar_vlar(Long vlar_vlar) {
		this.vlar_vlar = vlar_vlar;
	}
	public Long getVlar_tpvl() {
		return vlar_tpvl;
	}
	public void setVlar_tpvl(Long vlar_tpvl) {
		this.vlar_tpvl = vlar_tpvl;
	}
	public Long getVlar_aror() {
		return vlar_aror;
	}
	public void setVlar_aror(Long vlar_aror) {
		this.vlar_aror = vlar_aror;
	}
	public Long getVlar_daror_id_reg() {
		return vlar_daror_id_reg;
	}
	public void setVlar_daror_id_reg(Long vlar_daror_id_reg) {
		this.vlar_daror_id_reg = vlar_daror_id_reg;
	}
	public String getVlar_valor_descri() {
		return vlar_valor_descri;
	}
	public void setVlar_valor_descri(String vlar_valor_descri) {
		this.vlar_valor_descri = vlar_valor_descri;
	}
	public Date getVlar_fcrea() {
		return vlar_fcrea;
	}
	public void setVlar_fcrea(Date vlar_fcrea) {
		this.vlar_fcrea = vlar_fcrea;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+getVlar_aror()+"."+getVlar_daror_id_reg()+"."+getVlar_tpvl()+""+getVlar_valor_descri();
	}

}
