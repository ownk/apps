package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.conversion.dto.ErrorArchivoRecaudo;
import com.developer.persistence.modulo.conversion.controllerdb.ErrorArchivoRecaudoControllerDB;

public class ErrorArchivoRecaudoServicio {
	
	private static ErrorArchivoRecaudoServicio instance;
	
	public static ErrorArchivoRecaudoServicio getInstance() {
		if (instance == null) {
			instance = new ErrorArchivoRecaudoServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	

	
	public Boolean crearErrorTransaccional(SqlSession session, ErrorArchivoRecaudo errorArchivoRecaudo){
		
		Boolean sinErrores = true;
		ErrorArchivoRecaudoControllerDB controllerDB = ErrorArchivoRecaudoControllerDB.getInstance();
		sinErrores = controllerDB.crearErrorTransaccional(session, errorArchivoRecaudo);
		
		return sinErrores;
		
	}
	
	
	
	public List<ErrorArchivoRecaudo> getErroresPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		ErrorArchivoRecaudoControllerDB controllerDB = ErrorArchivoRecaudoControllerDB.getInstance();
		List<ErrorArchivoRecaudo> list = controllerDB.getErroresPorARORxDAROR(aror_aror, daror_id_reg);
		
		return list;
	}
	
	
			
	
	 

}
