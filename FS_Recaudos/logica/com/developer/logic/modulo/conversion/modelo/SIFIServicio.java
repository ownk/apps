package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.persistence.modulo.conversion.controllerdb.SIFIControllerDB;

public class SIFIServicio {
	
	SIFIControllerDB controllerDB;
	
	public SIFIServicio() {
		controllerDB = new SIFIControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public List<EncargoFiduciarioSIFI> getEncargoSIFI (EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		
		
		/*
		List<EncargoFiduciarioSIFI> list = null;
		
		list = getEncargoSIFI29(encargoFiduciarioSIFI);
		
		if(list==null){
			list = getEncargoSIFI43(encargoFiduciarioSIFI);
			
		}
		
		return list;
		*/ 
		return null;
		
		
	} 

	
	private List<EncargoFiduciarioSIFI> getEncargoSIFI29(EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		SIFIControllerDB controllerDB = this.controllerDB;
		List<EncargoFiduciarioSIFI> list =  controllerDB.getEncargoSIFI43(encargoFiduciarioSIFI);
		
		return list;
		
		
	}
	

	private List<EncargoFiduciarioSIFI> getEncargoSIFI43(EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		SIFIControllerDB controllerDB = this.controllerDB;
		List<EncargoFiduciarioSIFI> list  = controllerDB.getEncargoSIFI43(encargoFiduciarioSIFI);
		
		return list;
		
		
	}
	
	 

}
