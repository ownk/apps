package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.persistence.modulo.conversion.controllerdb.SIFIControllerDB;

public class SIFIServicio {
	
	private static SIFIServicio instance;
	
	public static SIFIServicio getInstance() {
		if (instance == null) {
			instance = new SIFIServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public List<EncargoFiduciarioSIFI> getEncargoSIFI (EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		
		List<EncargoFiduciarioSIFI> list = null;
		
		list = getEncargoSIFI29(encargoFiduciarioSIFI);
		
		if(list==null){
			list = getEncargoSIFI43(encargoFiduciarioSIFI);
			
		}
		
		return list;
		
		
		
	} 

	
	private List<EncargoFiduciarioSIFI> getEncargoSIFI29(EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		SIFIControllerDB controllerDB = SIFIControllerDB.getInstance();
		List<EncargoFiduciarioSIFI> list =  controllerDB.getEncargoSIFI43(encargoFiduciarioSIFI);
		
		return list;
		
		
	}
	

	private List<EncargoFiduciarioSIFI> getEncargoSIFI43(EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		SIFIControllerDB controllerDB = SIFIControllerDB.getInstance();
		List<EncargoFiduciarioSIFI> list  = controllerDB.getEncargoSIFI43(encargoFiduciarioSIFI);
		
		return list;
		
		
	}
	
	 

}
