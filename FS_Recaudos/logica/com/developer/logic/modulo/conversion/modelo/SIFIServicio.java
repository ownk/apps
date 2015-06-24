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
	
	
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI29(){
		SIFIControllerDB controllerDB = SIFIControllerDB.getInstance();
		List<EncargoFiduciarioSIFI> list = controllerDB.getAllEncargosSIFI29();
		
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
		
		
		
		
	}
	
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI43(){
		SIFIControllerDB controllerDB = SIFIControllerDB.getInstance();
		List<EncargoFiduciarioSIFI> list = controllerDB.getAllEncargosSIFI43();
		
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
		
		
	}
	
	 

}
