package com.developer.logic.modulo.compara.dto;

import java.util.Date;
import java.util.List;

public class ComparacionArchivoRecaudo {
	
	public static String COMPARADO = "COMPARADO";
	
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

	
	List<DetalleComparacionArchivoRecaudo> detalles;


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
	}


	public Date getCpar_ffin() {
		return cpar_ffin;
	}


	public void setCpar_ffin(Date cpar_ffin) {
		this.cpar_ffin = cpar_ffin;
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
	
	
	

}
