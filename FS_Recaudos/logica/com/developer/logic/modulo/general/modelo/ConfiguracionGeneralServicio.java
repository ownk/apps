package com.developer.logic.modulo.general.modelo;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.persistence.modulo.general.controllerdb.ConfiguracionGeneralDB;

public class ConfiguracionGeneralServicio {
	
	
	public static int RUTA_GRAL_ARCHIVOS = 5;
	
	private HashMap<Integer, ParametroConfiguracionGeneral> configInicial;
	
	private static ConfiguracionGeneralServicio instance;
	
	public static ConfiguracionGeneralServicio getInstance() {
		if (instance == null) {
			instance = new ConfiguracionGeneralServicio();
		}
		
		return instance;
	}
	
	public ConfiguracionGeneralServicio() {
		recargarConfiguracionInicial();
	}
	
	public Boolean recargarConfiguracionInicial(){
		
		try {
			
			//Se consultan los parametros de la base de datos
			ConfiguracionGeneralDB configuracionGeneralDB = ConfiguracionGeneralDB.getInstance();
			List<ParametroConfiguracionGeneral> parametros = configuracionGeneralDB.getConfiguracionGeneral();
			
			//Se inicia el mapa de parametros;
			if(configInicial == null){
				configInicial = new HashMap<Integer, ParametroConfiguracionGeneral>();
				
			}else{
				configInicial.clear();
			}
			
			//Se llena el mapa con los parametros;
			for (ParametroConfiguracionGeneral parametroConfiguracionGeneral : parametros) {
				configInicial.put(parametroConfiguracionGeneral.getConfig_config(), parametroConfiguracionGeneral);
					
			}
			
			return true;
		
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public ParametroConfiguracionGeneral getParametro(int parametro){
		if(configInicial==null){
			recargarConfiguracionInicial();
			
			return configInicial.get(parametro);
			
		}else{
			
			return configInicial.get(parametro);
		}
		
	} 
	
	public static void initConfiguracion(){
		getInstance();
		
	}
	
	public List<ParametroConfiguracionGeneral> getAllParametrosConfiguracionGeneral(){
		
		return ConfiguracionGeneralDB.getInstance().getConfiguracionGeneral();
	}
	
	
	
	
	
	

}

