package com.developer.logic.modulo.compara.dto;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetalleComparacionArchivoRecaudo {
	
	
	public static final String FUENTE_PLANO = "PLANO";
	public static final String FUENTE_INTERNET = "INTERNET";		
			
	
	Long dcpar_cpar;
	String dcpar_id_reg_orig;
	String dcpar_fuente;
	Date dcpar_freca_norm;
	String dcpar_freca_orig;
	String dcpar_treca_norm;
	String dcpar_treca_orig;
	String dcpar_ofic_norm;
	String dcpar_ofic_orig;
	String dcpar_referencia;
	String dcpar_observ;
	Double dcpar_valor;
	Date dcpar_fcrea;
	String dcpar_freca_norm_string; 
	
	String dcpar_valor_format = DecimalFormat.getCurrencyInstance().format(0);
	
	public Long getDcpar_cpar() {
		return dcpar_cpar;
	}
	public void setDcpar_cpar(Long dcpar_cpar) {
		this.dcpar_cpar = dcpar_cpar;
	}
	public String getDcpar_id_reg_orig() {
		return dcpar_id_reg_orig;
	}
	public void setDcpar_id_reg_orig(String dcpar_id_reg_orig) {
		this.dcpar_id_reg_orig = dcpar_id_reg_orig;
	}
	public String getDcpar_fuente() {
		return dcpar_fuente;
	}
	public void setDcpar_fuente(String dcpar_fuente) {
		this.dcpar_fuente = dcpar_fuente;
	}
	public Date getDcpar_freca_norm() {
		return dcpar_freca_norm;
	}
	public void setDcpar_freca_norm(Date dcpar_freca_norm) {
		this.dcpar_freca_norm = dcpar_freca_norm;
		
		try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.dcpar_freca_norm_string = format.format(dcpar_freca_norm).replace(" 00:00:00", "").trim();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getDcpar_freca_orig() {
		return dcpar_freca_orig;
	}
	public void setDcpar_freca_orig(String dcpar_freca_orig) {
		this.dcpar_freca_orig = dcpar_freca_orig;
	}
	public String getDcpar_treca_norm() {
		return dcpar_treca_norm;
	}
	public void setDcpar_treca_norm(String dcpar_treca_norm) {
		this.dcpar_treca_norm = dcpar_treca_norm;
	}
	public String getDcpar_treca_orig() {
		return dcpar_treca_orig;
	}
	public void setDcpar_treca_orig(String dcpar_treca_orig) {
		this.dcpar_treca_orig = dcpar_treca_orig;
	}
	public String getDcpar_ofic_norm() {
		return dcpar_ofic_norm;
	}
	public void setDcpar_ofic_norm(String dcpar_ofic_norm) {
		this.dcpar_ofic_norm = dcpar_ofic_norm;
	}
	public String getDcpar_ofic_orig() {
		return dcpar_ofic_orig;
	}
	public void setDcpar_ofic_orig(String dcpar_ofic_orig) {
		this.dcpar_ofic_orig = dcpar_ofic_orig;
	}
	public String getDcpar_referencia() {
		return dcpar_referencia;
	}
	public void setDcpar_referencia(String dcpar_referencia) {
		this.dcpar_referencia = dcpar_referencia;
	}
	public String getDcpar_observ() {
		return dcpar_observ;
	}
	public void setDcpar_observ(String dcpar_observ) {
		this.dcpar_observ = dcpar_observ;
	}
	
	public Double getDcpar_valor() {
		
		return dcpar_valor;
	}
	public void setDcpar_valor(Double dcpar_valor) {
		
		this.dcpar_valor = dcpar_valor;
		
		try {
			
			dcpar_valor_format = DecimalFormat.getCurrencyInstance().format(getRound2Decimals(dcpar_valor));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Date getDcpar_fcrea() {
		return dcpar_fcrea;
	}
	public void setDcpar_fcrea(Date dcpar_fcrea) {
		this.dcpar_fcrea = dcpar_fcrea;
	}
	public String getDcpar_freca_norm_string() {
		return dcpar_freca_norm_string;
	}
	public void setDcpar_freca_norm_string(String dcpar_freca_norm_string) {
		this.dcpar_freca_norm_string = dcpar_freca_norm_string;
	}	
	
	
	
	private double getRound2Decimals(double valor) {

		DecimalFormat twoDForm = new DecimalFormat("#.00");
	    return Double.parseDouble(twoDForm.format(valor).replace(",", "."));

		

	}

	
	
}
