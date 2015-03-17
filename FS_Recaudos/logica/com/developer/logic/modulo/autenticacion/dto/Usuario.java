package com.developer.logic.modulo.autenticacion.dto;

import java.util.List;

public class Usuario {

	String usua_usua;
	String usua_clave;
	String usua_mail;
	
	//Roles
	List<String> roles;
	
	public String getUsua_usua() {
		return usua_usua;
	}
	public void setUsua_usua(String usua_usua) {
		this.usua_usua = usua_usua;
	}
	public String getUsua_clave() {
		return usua_clave;
	}
	public void setUsua_clave(String usua_clave) {
		this.usua_clave = usua_clave;
	}
	
	public String getUsua_mail() {
		return usua_mail;
	}
	
	public void setUsua_mail(String usua_mail) {
		this.usua_mail = usua_mail;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
