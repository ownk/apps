package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.OficinaRecaudo;
import com.developer.persistence.modulo.conversion.controllerdb.OficinaRecaudoControllerDB;

public class OficinaRecaudoServicio {
	
	private static OficinaRecaudoServicio instance;
	
	public static OficinaRecaudoServicio getInstance() {
		if (instance == null) {
			instance = new OficinaRecaudoServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
	public List<OficinaRecaudo> getAllOficinas(){
		OficinaRecaudoControllerDB controllerDB = OficinaRecaudoControllerDB.getInstance();
		List<OficinaRecaudo> list = controllerDB.getAllOficinas();
		return list;
	}


	
			
	
	 

}
