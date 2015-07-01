package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.ParametroGeneralConversion;
import com.developer.persistence.modulo.conversion.controllerdb.ParametroGeneralConversionControllerDB;

public class ParametroGeneralConversionServicio {
	

	
	public static String TAMANHO_MAX_REFERENCIA = "co.tam_referencia_recaudo";
	public static String PREFIJO_VOLANTE = "co.pefijo_volante";
	
	
	ParametroGeneralConversionControllerDB controllerDB;
	
	public ParametroGeneralConversionServicio() {
		controllerDB = new ParametroGeneralConversionControllerDB();
	}
	
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	




	public List<ParametroGeneralConversion> getAllParametros(){
		ParametroGeneralConversionControllerDB controllerDB = this.controllerDB;
		List<ParametroGeneralConversion> list = controllerDB.getAllParametros();
		return list;
		
	}
	
	public ParametroGeneralConversion getParametroGeneral(String para_para){
		
		
		ParametroGeneralConversionControllerDB controllerDB = this.controllerDB;
		return controllerDB.getParametroGeneral(para_para);
		
		
	}
	
	
			
	
	 

}
