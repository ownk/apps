package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.ParametroGeneralConversion;
import com.developer.persistence.modulo.conversion.controllerdb.ParametroGeneralConversionControllerDB;

public class ParametroGeneralConversionServicio {
	
	private static ParametroGeneralConversionServicio instance;
	
	public static ParametroGeneralConversionServicio getInstance() {
		if (instance == null) {
			instance = new ParametroGeneralConversionServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	




	public List<ParametroGeneralConversion> getAllParametros(){
		ParametroGeneralConversionControllerDB controllerDB = ParametroGeneralConversionControllerDB.getInstance();
		List<ParametroGeneralConversion> list = controllerDB.getAllParametros();
		return list;
		
	}
	
	public ParametroGeneralConversion getParametroGeneral(String para_para){
		ParametroGeneralConversionControllerDB controllerDB = ParametroGeneralConversionControllerDB.getInstance();
		return controllerDB.getParametroGeneral(para_para);
		
		
	}
	
	
			
	
	 

}
