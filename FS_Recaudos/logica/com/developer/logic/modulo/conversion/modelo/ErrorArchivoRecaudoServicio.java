package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.conversion.dto.ErrorArchivoRecaudo;
import com.developer.persistence.modulo.conversion.controllerdb.ErrorArchivoRecaudoControllerDB;

public class ErrorArchivoRecaudoServicio {
	
	ErrorArchivoRecaudoControllerDB controllerDB;
	
	public ErrorArchivoRecaudoServicio() {
		controllerDB = new ErrorArchivoRecaudoControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	

	
	public Boolean crearErrorTransaccional(SqlSession session, ErrorArchivoRecaudo errorArchivoRecaudo){
		
		Boolean sinErrores = true;
		ErrorArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		sinErrores = controllerDB.crearErrorTransaccional(session, errorArchivoRecaudo);
		
		return sinErrores;
		
	}
	
	
	
	public List<ErrorArchivoRecaudo> getErroresPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		ErrorArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		List<ErrorArchivoRecaudo> list = controllerDB.getErroresPorARORxDAROR(aror_aror, daror_id_reg);
		
		return list;
	}
	
	
			
	
	 

}
