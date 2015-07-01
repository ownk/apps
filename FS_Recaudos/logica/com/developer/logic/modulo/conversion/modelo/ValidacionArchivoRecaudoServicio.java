package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.bcel.generic.NEW;
import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.conversion.dto.ValidacionArchivoRecaudo;
import com.developer.persistence.modulo.conversion.controllerdb.ValidacionArchivoRecaudoControllerDB;

public class ValidacionArchivoRecaudoServicio {
	
	ValidacionArchivoRecaudoControllerDB controllerDB;
	public ValidacionArchivoRecaudoServicio() {
		controllerDB = new ValidacionArchivoRecaudoControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	

	
	public Boolean crearValidacionTransaccional(SqlSession session, ValidacionArchivoRecaudo validacionArchivoRecaudo){
		
		Boolean sinErrores = true;
		ValidacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		sinErrores = controllerDB.crearValidacionTransaccional(session, validacionArchivoRecaudo);
		
		return sinErrores;
		
	}
	
	
	
	public List<ValidacionArchivoRecaudo> getValidacionesPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		ValidacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		List<ValidacionArchivoRecaudo> list = controllerDB.getValidacionesPorARORxDAROR(aror_aror, daror_id_reg);
		
		return list;
	}
	
	
			
	
	 

}
