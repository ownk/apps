package com.developer.logic.modulo.compara.dto;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetalleResumenComparacion {
	

	Long dcpar_cpar;
	String dcpar_fuente;
	String dcpar_treca_norm;
	Double dcpar_valor;
	Long dcpar_cantidad;

	 
	String dcpar_valor_format = DecimalFormat.getCurrencyInstance().format(0);
	
	public Long getDcpar_cpar() {
		return dcpar_cpar;
	}
	public void setDcpar_cpar(Long dcpar_cpar) {
		this.dcpar_cpar = dcpar_cpar;
	}

	public String getDcpar_fuente() {
		return dcpar_fuente;
	}
	public void setDcpar_fuente(String dcpar_fuente) {
		this.dcpar_fuente = dcpar_fuente;
	}
	public String getDcpar_treca_norm() {
		return dcpar_treca_norm;
	}
	public void setDcpar_treca_norm(String dcpar_treca_norm) {
		this.dcpar_treca_norm = dcpar_treca_norm;
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
	
	
	
	
	public Long getDcpar_cantidad() {
		return dcpar_cantidad;
	}
	public void setDcpar_cantidad(Long dcpar_cantidad) {
		this.dcpar_cantidad = dcpar_cantidad;
	}
	public String getDcpar_valor_format() {
		return dcpar_valor_format;
	}
	public void setDcpar_valor_format(String dcpar_valor_format) {
		this.dcpar_valor_format = dcpar_valor_format;
	}
	private double getRound2Decimals(double valor) {

		DecimalFormat twoDForm = new DecimalFormat("#.00");
	    return Double.parseDouble(twoDForm.format(valor).replace(",", "."));

		

	}
	
	

}
