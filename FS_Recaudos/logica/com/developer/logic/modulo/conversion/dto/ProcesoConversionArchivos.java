package com.developer.logic.modulo.conversion.dto;

import java.util.Date;
import java.util.List;

public class ProcesoConversionArchivos {
	
	
	public static String INICIADO = "INICIADO";
	public static String EN_PROCESO = "EN_PROCESO";
	public static String FINALIZADO = "FINALIZADO";
	
	Long    prco_prco 	;
	String  prco_usua 	;
	String  prco_eprco 	;
	Date    prco_fcrea 	;
	String  prco_observ ;
	Date    prco_fini 	;
	Date    prco_ffin 	;
	Long    prco_prun   ;
	
	
	List<HistoricoProcesoConversionArchivos> historicoProcesoConversionArchivos;
	
	public Long getPrco_prco() {
		return prco_prco;
	}
	public void setPrco_prco(Long prco_prco) {
		this.prco_prco = prco_prco;
	}
	public String getPrco_usua() {
		return prco_usua;
	}
	public void setPrco_usua(String prco_usua) {
		this.prco_usua = prco_usua;
	}
	public String getPrco_eprco() {
		return prco_eprco;
	}
	public void setPrco_eprco(String prco_eprco) {
		this.prco_eprco = prco_eprco;
	}
	public Date getPrco_fcrea() {
		return prco_fcrea;
	}
	public void setPrco_fcrea(Date prco_fcrea) {
		this.prco_fcrea = prco_fcrea;
	}
	public String getPrco_observ() {
		return prco_observ;
	}
	public void setPrco_observ(String prco_observ) {
		this.prco_observ = prco_observ;
	}
	public Date getPrco_fini() {
		return prco_fini;
	}
	public void setPrco_fini(Date prco_fini) {
		this.prco_fini = prco_fini;
	}
	public Date getPrco_ffin() {
		return prco_ffin;
	}
	public void setPrco_ffin(Date prco_ffin) {
		this.prco_ffin = prco_ffin;
	}
	public Long getPrco_prun() {
		return prco_prun;
	}
	public void setPrco_prun(Long prco_prun) {
		this.prco_prun = prco_prun;
	}
	
	public List<HistoricoProcesoConversionArchivos> getHistoricoProcesoConversionArchivos() {
		return historicoProcesoConversionArchivos;
	}
	public void setHistoricoProcesoConversionArchivos(
			List<HistoricoProcesoConversionArchivos> historicoProcesoConversionArchivos) {
		this.historicoProcesoConversionArchivos = historicoProcesoConversionArchivos;
	}
	

	

}
