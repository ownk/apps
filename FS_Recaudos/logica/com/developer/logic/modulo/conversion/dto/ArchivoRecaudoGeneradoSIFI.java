package com.developer.logic.modulo.conversion.dto;

import java.util.Date;
import java.util.List;

public class ArchivoRecaudoGeneradoSIFI {
	
	

	//Estados
	public static String GENERADO = "GENERADO";
	
	//Atributos atomicos
	Long    arge_arge 		;
	String  arge_usua 	    ;
	Long    arge_prco 		;
	String  arge_tpar 	    ;
	String  arge_earge 	    ;
	String  arge_url 	    ;
	String  arge_hash 	    ;
	String  arge_bytes 	    ;
	String  arge_nombre 	;
	String  arge_observ 	;
	Date    arge_fcrea 	    ;
	String  arge_extension 	;
	Long    arge_registros 	;
	Long    arge_aror 	    ;
	
	
	double arge_vtot_recaudo;
	
	List<DetalleArchivoRecaudoGeneradoSIFI> detalles;
	
	public Double getArge_vtot_recaudo() {
		return arge_vtot_recaudo;
	}
	public void setArge_vtot_recaudo(Double arge_vtot_recaudo) {
		this.arge_vtot_recaudo = arge_vtot_recaudo;
	}

	
	
	
	
	public List<DetalleArchivoRecaudoGeneradoSIFI> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleArchivoRecaudoGeneradoSIFI> detalles) {
		this.detalles = detalles;
	}
	public Long getArge_arge() {
		return arge_arge;
	}
	public void setArge_arge(Long arge_arge) {
		this.arge_arge = arge_arge;
	}
	public String getArge_usua() {
		return arge_usua;
	}
	public void setArge_usua(String arge_usua) {
		this.arge_usua = arge_usua;
	}
	public Long getArge_prco() {
		return arge_prco;
	}
	public void setArge_prco(Long arge_prco) {
		this.arge_prco = arge_prco;
	}
	public String getArge_tpar() {
		return arge_tpar;
	}
	public void setArge_tpar(String arge_tpar) {
		this.arge_tpar = arge_tpar;
	}
	public String getArge_earge() {
		return arge_earge;
	}
	public void setArge_earge(String arge_earge) {
		this.arge_earge = arge_earge;
	}
	public String getArge_url() {
		return arge_url;
	}
	public void setArge_url(String arge_url) {
		this.arge_url = arge_url;
	}
	public String getArge_hash() {
		return arge_hash;
	}
	public void setArge_hash(String arge_hash) {
		this.arge_hash = arge_hash;
	}
	public String getArge_bytes() {
		return arge_bytes;
	}
	public void setArge_bytes(String arge_bytes) {
		this.arge_bytes = arge_bytes;
	}
	public String getArge_nombre() {
		return arge_nombre;
	}
	public void setArge_nombre(String arge_nombre) {
		this.arge_nombre = arge_nombre;
	}
	public String getArge_observ() {
		return arge_observ;
	}
	public void setArge_observ(String arge_observ) {
		this.arge_observ = arge_observ;
	}
	public Date getArge_fcrea() {
		return arge_fcrea;
	}
	public void setArge_fcrea(Date arge_fcrea) {
		this.arge_fcrea = arge_fcrea;
	}
	public String getArge_extension() {
		return arge_extension;
	}
	public void setArge_extension(String arge_extension) {
		this.arge_extension = arge_extension;
	}
	public Long getArge_registros() {
		return arge_registros;
	}
	public void setArge_registros(Long arge_registros) {
		this.arge_registros = arge_registros;
	}
	public Long getArge_aror() {
		return arge_aror;
	}
	public void setArge_aror(Long arge_aror) {
		this.arge_aror = arge_aror;
	}

	

	
}
