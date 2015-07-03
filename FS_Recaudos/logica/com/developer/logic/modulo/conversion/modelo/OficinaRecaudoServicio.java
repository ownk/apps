package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.OficinaRecaudo;
import com.developer.persistence.modulo.conversion.controllerdb.OficinaRecaudoControllerDB;

public class OficinaRecaudoServicio {
	

	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
	public List<OficinaRecaudo> getAllOficinas(){
		OficinaRecaudoControllerDB controllerDB = new OficinaRecaudoControllerDB();
		List<OficinaRecaudo> list = controllerDB.getAllOficinas();
		return list;
	}


	
	public OficinaRecaudo getOficinaSIFI(String ofic_bsc){
		OficinaRecaudoControllerDB controllerDB = new OficinaRecaudoControllerDB();
		OficinaRecaudo list = controllerDB.getOficinaSIFI(ofic_bsc);
		return list;
	}		
	
	 

}