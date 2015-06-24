package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.conversion.dto.ValidacionArchivoRecaudo;
import com.developer.persistence.modulo.conversion.controllerdb.ValidacionArchivoRecaudoControllerDB;

public class ValidacionArchivoRecaudoServicio {
	
	private static ValidacionArchivoRecaudoServicio instance;
	
	public static ValidacionArchivoRecaudoServicio getInstance() {
		if (instance == null) {
			instance = new ValidacionArchivoRecaudoServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	

	
	public Boolean crearValidacionTransaccional(SqlSession session, ValidacionArchivoRecaudo transformacionArchivoRecaudo){
		
		Boolean sinErrores = true;
		ValidacionArchivoRecaudoControllerDB controllerDB = ValidacionArchivoRecaudoControllerDB.getInstance();
		sinErrores = controllerDB.crearValidacionTransaccional(session, transformacionArchivoRecaudo);
		
		return sinErrores;
		
	}
	
	
	
	public List<ValidacionArchivoRecaudo> getValidacionesPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		ValidacionArchivoRecaudoControllerDB controllerDB = ValidacionArchivoRecaudoControllerDB.getInstance();
		List<ValidacionArchivoRecaudo> list = controllerDB.getValidacionesPorARORxDAROR(aror_aror, daror_id_reg);
		
		return list;
	}
	
	
			
	
	 

}
