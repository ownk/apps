package com.developer.logic.modulo.unificacion.dto;


public class TipoArchivoRecaudo {
	
	
	public static Long ESTR_FIDUCIARIA = new Long(1);
	public static Long ESTR_ASOBANCARIA = new Long(2);
	public static Long ESTR_DEFAULT = new Long(3);
	
	String tpar_tpar;
	String tpar_descri;
	String tpar_usua;
	Long   tpar_estr;
	String tpar_nomb_arun;
	
	
	
	
	public String getTpar_tpar() {
		return tpar_tpar;
	}
	public void setTpar_tpar(String tpar_tpar) {
		this.tpar_tpar = tpar_tpar;
	}
	public String getTpar_descri() {
		return tpar_descri;
	}
	public void setTpar_descri(String tpar_descri) {
		this.tpar_descri = tpar_descri;
	}
	public String getTpar_usua() {
		return tpar_usua;
	}
	public void setTpar_usua(String tpar_usua) {
		this.tpar_usua = tpar_usua;
	}
	public Long getTpar_estr() {
		return tpar_estr;
	}
	public void setTpar_estr(Long tpar_estr) {
		this.tpar_estr = tpar_estr;
	}
	public String getTpar_nomb_arun() {
		return tpar_nomb_arun;
	}
	public void setTpar_nomb_arun(String tpar_nomb_arun) {
		this.tpar_nomb_arun = tpar_nomb_arun;
	} 
	
	
	

}
