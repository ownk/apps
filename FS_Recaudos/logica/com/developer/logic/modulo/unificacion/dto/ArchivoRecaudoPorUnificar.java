package com.developer.logic.modulo.unificacion.dto;

import java.util.Date;

public class ArchivoRecaudoPorUnificar {
	
	

	//Estados
	
	public static String CARGADO = "CARGADO";
	public static String UNIFICADO = "UNIFICADO";
	

	
	
	//Atributos atomicos
	Long arpu_arpu;
	Long arpu_azpu;
	String arpu_tpar;
	String arpu_usua;
	Long arpu_prun;
	String arpu_earpu;
	String arpu_url;
	String arpu_hash;
	String arpu_bytes;
	String arpu_nombre;
	String arpu_observ;
	String arpu_extension;
	Long arpu_registros;
	Date arpu_fcrea;
	
	
	//Atributos Calculados
	ProcesoUnificacionArchivos procesoUnificacionArchivos;
	ArchivoZIPProcesoUnificacion archivoAZPU;
	
	
	public Long getArpu_arpu() {
		return arpu_arpu;
	}
	public void setArpu_arpu(Long arpu_arpu) {
		this.arpu_arpu = arpu_arpu;
	}
	public Long getArpu_azpu() {
		return arpu_azpu;
	}
	public void setArpu_azpu(Long arpu_azpu) {
		this.arpu_azpu = arpu_azpu;
	}
	public String getArpu_tpar() {
		return arpu_tpar;
	}
	public void setArpu_tpar(String arpu_tpar) {
		this.arpu_tpar = arpu_tpar;
	}
	public String getArpu_usua() {
		return arpu_usua;
	}
	public void setArpu_usua(String arpu_usua) {
		this.arpu_usua = arpu_usua;
	}
	public Long getArpu_prun() {
		return arpu_prun;
	}
	public void setArpu_prun(Long arpu_prun) {
		this.arpu_prun = arpu_prun;
	}
	public String getArpu_earpu() {
		return arpu_earpu;
	}
	public void setArpu_earpu(String arpu_earpu) {
		this.arpu_earpu = arpu_earpu;
	}
	public String getArpu_url() {
		return arpu_url;
	}
	public void setArpu_url(String arpu_url) {
		this.arpu_url = arpu_url;
	}
	public String getArpu_hash() {
		return arpu_hash;
	}
	public void setArpu_hash(String arpu_hash) {
		this.arpu_hash = arpu_hash;
	}
	public String getArpu_bytes() {
		return arpu_bytes;
	}
	public void setArpu_bytes(String arpu_bytes) {
		this.arpu_bytes = arpu_bytes;
	}
	public String getArpu_nombre() {
		return arpu_nombre;
	}
	public void setArpu_nombre(String arpu_nombre) {
		this.arpu_nombre = arpu_nombre;
	}
	public String getArpu_observ() {
		return arpu_observ;
	}
	public void setArpu_observ(String arpu_observ) {
		this.arpu_observ = arpu_observ;
	}
	public String getArpu_extension() {
		return arpu_extension;
	}
	public void setArpu_extension(String arpu_extension) {
		this.arpu_extension = arpu_extension;
	}
	public Long getArpu_registros() {
		return arpu_registros;
	}
	public void setArpu_registros(Long arpu_registros) {
		this.arpu_registros = arpu_registros;
	}
	public Date getArpu_fcrea() {
		return arpu_fcrea;
	}
	public void setArpu_fcrea(Date arpu_fcrea) {
		this.arpu_fcrea = arpu_fcrea;
	}
	
	public ProcesoUnificacionArchivos getProcesoUnificacionArchivos() {
		return procesoUnificacionArchivos;
	}
	public void setProcesoUnificacionArchivos(
			ProcesoUnificacionArchivos procesoUnificacionArchivos) {
		this.procesoUnificacionArchivos = procesoUnificacionArchivos;
	}
	public ArchivoZIPProcesoUnificacion getArchivoAZPU() {
		return archivoAZPU;
	}
	public void setArchivoAZPU(ArchivoZIPProcesoUnificacion archivosAZPU) {
		this.archivoAZPU = archivosAZPU;
	}
	
	

	

}
