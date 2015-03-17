package com.developer.logic.modulo.autenticacion.dto;

public class UsuarioURL {
	String usua_usua;
	String url;
	
	public UsuarioURL(String usua_usua, String url) {
		setUsua_usua(usua_usua);
		setUrl(url);	
	}
	
	public String getUsua_usua() {
		return usua_usua;
	}
	public void setUsua_usua(String usua_usua) {
		this.usua_usua = usua_usua;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
