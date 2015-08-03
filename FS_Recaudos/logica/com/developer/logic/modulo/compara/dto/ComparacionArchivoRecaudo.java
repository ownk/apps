package com.developer.logic.modulo.compara.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;

public class ComparacionArchivoRecaudo {
	
	public static String EJECUTADA = "EJECUTADA";
	
	Long    cpar_cpar 	;
	String  cpar_usua 	;
	String  cpar_tpar 	;
	String  cpar_observ	;
	String  cpar_ecpar 	;
	Date    cpar_fcrea 	;
	Date    cpar_fini 	;
	Date    cpar_ffin 	;
	Long    cpar_arun 	;
	Long    cpar_ibsc 	;
	String  cpar_arun_cta;
	String 	cpar_ibsc_cta;

	String cpar_fini_string;
	String cpar_ffin_string; 
	
	List<DetalleComparacionArchivoRecaudo> detalles;
	
	List<DetalleResumenComparacion> resumen;
	
	List<DiferenciaResumenComparacion> diferenciasResumen;
	
	List<DetalleComparacionArchivoRecaudo> fechas;
	
	public Long getCpar_cpar() {
		return cpar_cpar;
	}


	public void setCpar_cpar(Long cpar_cpar) {
		this.cpar_cpar = cpar_cpar;
	}


	public String getCpar_usua() {
		return cpar_usua;
	}


	public void setCpar_usua(String cpar_usua) {
		this.cpar_usua = cpar_usua;
	}


	public String getCpar_tpar() {
		return cpar_tpar;
	}


	public void setCpar_tpar(String cpar_tpar) {
		this.cpar_tpar = cpar_tpar;
	}


	public String getCpar_observ() {
		return cpar_observ;
	}


	public void setCpar_observ(String cpar_observ) {
		this.cpar_observ = cpar_observ;
	}


	public String getCpar_ecpar() {
		return cpar_ecpar;
	}


	public void setCpar_ecpar(String cpar_ecpar) {
		this.cpar_ecpar = cpar_ecpar;
	}


	public Date getCpar_fcrea() {
		return cpar_fcrea;
	}


	public void setCpar_fcrea(Date cpar_fcrea) {
		this.cpar_fcrea = cpar_fcrea;
	}


	public Date getCpar_fini() {
		return cpar_fini;
	}


	public void setCpar_fini(Date cpar_fini) {
		this.cpar_fini = cpar_fini;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.cpar_fini_string = format.format(cpar_fini).replace(" 00:00:00", "").trim();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public Date getCpar_ffin() {
		return cpar_ffin;
	}


	public void setCpar_ffin(Date cpar_ffin) {
		this.cpar_ffin = cpar_ffin;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.cpar_ffin_string = format.format(cpar_ffin).replace(" 00:00:00", "").trim();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public Long getCpar_arun() {
		return cpar_arun;
	}


	public void setCpar_arun(Long cpar_arun) {
		this.cpar_arun = cpar_arun;
	}


	public Long getCpar_ibsc() {
		return cpar_ibsc;
	}


	public void setCpar_ibsc(Long cpar_ibsc) {
		this.cpar_ibsc = cpar_ibsc;
	}


	public List<DetalleComparacionArchivoRecaudo> getDetalles() {
		return detalles;
	}


	public void setDetalles(List<DetalleComparacionArchivoRecaudo> detalles) {
		this.detalles = detalles;
	}


	public String getCpar_arun_cta() {
		return cpar_arun_cta;
	}


	public void setCpar_arun_cta(String cpar_arun_cta) {
		this.cpar_arun_cta = cpar_arun_cta;
	}


	public String getCpar_ibsc_cta() {
		return cpar_ibsc_cta;
	}


	public void setCpar_ibsc_cta(String cpar_ibsc_cta) {
		this.cpar_ibsc_cta = cpar_ibsc_cta;
	}


	public String getCpar_fini_string() {
		return cpar_fini_string;
	}


	public void setCpar_fini_string(String cpar_fini_string) {
		this.cpar_fini_string = cpar_fini_string;
	}


	public String getCpar_ffin_string() {
		return cpar_ffin_string;
	}


	public void setCpar_ffin_string(String cpar_ffin_string) {
		this.cpar_ffin_string = cpar_ffin_string;
	}


	public List<DetalleResumenComparacion> getResumen() {
		return resumen;
	}


	public void setResumen(List<DetalleResumenComparacion> resumen) {
		this.resumen = resumen;
	}


	public List<DiferenciaResumenComparacion> getDiferenciasResumen() {
		return diferenciasResumen;
	}


	public void setDiferenciasResumen(
			List<DiferenciaResumenComparacion> diferenciasResumen) {
		this.diferenciasResumen = diferenciasResumen;
	}


	public List<DetalleComparacionArchivoRecaudo> getFechas() {
		return fechas;
	}


	public void setFechas(List<DetalleComparacionArchivoRecaudo> fechas) {
		this.fechas = fechas;
	}

	
	


	
	

	
}
