package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.persistence.modulo.conversion.controllerdb.EncargoFiduciarioNoSIFIControllerDB;

public class EncargoFiduciarioNoSIFIServicio {
	
	EncargoFiduciarioNoSIFIControllerDB controllerDB;
	public EncargoFiduciarioNoSIFIServicio() {
		controllerDB = new EncargoFiduciarioNoSIFIControllerDB();
	}
	
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public List<EncargoFiduciarioNoSIFI> getAllEncargosNoSIFI(){
		EncargoFiduciarioNoSIFIControllerDB controllerDB = this.controllerDB;
		List<EncargoFiduciarioNoSIFI> list = controllerDB.getAllEncargosNoSIFI();
		return list;
		
	}
	

	public EncargoFiduciarioNoSIFI getEncargoNoSIFI(EncargoFiduciarioNoSIFI encargoFiduciarioNoSIFI){
		EncargoFiduciarioNoSIFIControllerDB controllerDB = this.controllerDB;
		return controllerDB.getEncargoNoSIFI(encargoFiduciarioNoSIFI);
		
		
	}
	

	
			
	
	 

}
