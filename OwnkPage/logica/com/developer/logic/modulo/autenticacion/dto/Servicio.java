package com.developer.logic.modulo.autenticacion.dto;

import java.util.List;

public class Servicio {
	
	//Informacion del servicio
	Long servicio_servicio;
	String servicio_modulo;
	String servicio_descri;
	String servicio_nomb;
	
	//URL Principal del servicio
	String surl_url;
	
	//Informacion urls
	List<String> listUrls;
	
	//Informacion del modulo
	String modulo_modulo;
	String modulo_descri;
	String modulo_nomb;
	
	
	public Long getServicio_servicio() {
		return servicio_servicio;
	}
	public void setServicio_servicio(Long servicio_servicio) {
		this.servicio_servicio = servicio_servicio;
	}
	public String getServicio_modulo() {
		return servicio_modulo;
	}
	public void setServicio_modulo(String servicio_modulo) {
		this.servicio_modulo = servicio_modulo;
	}
	public String getServicio_descri() {
		return servicio_descri;
	}
	public void setServicio_descri(String servicio_descri) {
		this.servicio_descri = servicio_descri;
	}
	public String getServicio_nomb() {
		return servicio_nomb;
	}
	public void setServicio_nomb(String servicio_nomb) {
		this.servicio_nomb = servicio_nomb;
	}
	public List<String> getListUrls() {
		return listUrls;
	}
	public void setListUrls(List<String> listUrls) {
		this.listUrls = listUrls;
	}
	public String getModulo_modulo() {
		return modulo_modulo;
	}
	public void setModulo_modulo(String modulo_modulo) {
		this.modulo_modulo = modulo_modulo;
	}
	public String getModulo_descri() {
		return modulo_descri;
	}
	public void setModulo_descri(String modulo_descri) {
		this.modulo_descri = modulo_descri;
	}
	public String getModulo_nomb() {
		return modulo_nomb;
	}
	public void setModulo_nomb(String modulo_nomb) {
		this.modulo_nomb = modulo_nomb;
	}
	
	
	
	
	
	
}
