package com.developer.logic.modulo.general.dto;

public class ParametroConfiguracionGeneral {
	
	
	public static int TIPO_DATO_NUMERICO = 1;
	public static int TIPO_DATO_STRING = 2;
	public static int TIPO_DATO_BOOLEAN = 3;
	
	int config_config;
	String config_descri;
	String config_valor;
	int config_tipo_dato;
	
	public int getConfig_config() {
		return config_config;
	}
	public void setConfig_config(int cONFIG_config) {
		config_config = cONFIG_config;
	}
	public String getConfig_descri() {
		return config_descri;
	}
	public void setConfig_descri(String config_descri) {
		this.config_descri = config_descri;
	}
	public String getConfig_valor() {
		return config_valor;
	}
	public void setConfig_valor(String config_valor) {
		this.config_valor = config_valor;
	}
	
	
	
	

}
