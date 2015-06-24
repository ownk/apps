package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.persistence.modulo.conversion.controllerdb.EncargoFiduciarioNoSIFIControllerDB;

public class EncargoFiduciarioNoSIFIServicio {
	
	private static EncargoFiduciarioNoSIFIServicio instance;
	
	public static EncargoFiduciarioNoSIFIServicio getInstance() {
		if (instance == null) {
			instance = new EncargoFiduciarioNoSIFIServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public List<EncargoFiduciarioNoSIFI> getAllEncargosNoSIFI(){
		EncargoFiduciarioNoSIFIControllerDB controllerDB = EncargoFiduciarioNoSIFIControllerDB.getInstance();
		List<EncargoFiduciarioNoSIFI> list = controllerDB.getAllEncargosNoSIFI();
		return list;
		
	}
	

	public EncargoFiduciarioNoSIFI getEncargoNoSIFI(EncargoFiduciarioNoSIFI encargoFiduciarioNoSIFI){
		EncargoFiduciarioNoSIFIControllerDB controllerDB = EncargoFiduciarioNoSIFIControllerDB.getInstance();
		return controllerDB.getEncargoNoSIFI(encargoFiduciarioNoSIFI);
		
		
	}
	

	
			
	
	 

}
