package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.conversion.dto.TransformacionArchivoRecaudo;
import com.developer.persistence.modulo.conversion.controllerdb.TransformacionArchivoRecaudoControllerDB;

public class TransformacionArchivoRecaudoServicio {
	
	
	TransformacionArchivoRecaudoControllerDB controllerDB;
	public TransformacionArchivoRecaudoServicio() {
		controllerDB = new TransformacionArchivoRecaudoControllerDB();
	}
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	

	
	public Boolean crearArchivoTransaccional(SqlSession session, TransformacionArchivoRecaudo transformacionArchivoRecaudo){
		
		Boolean sinErrores = true;
		TransformacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		sinErrores = controllerDB.crearTransformacionTransaccional(session, transformacionArchivoRecaudo);
		
		return sinErrores;
		
	}
	
	
	
	public List<TransformacionArchivoRecaudo> getTransformacionesPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		TransformacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		List<TransformacionArchivoRecaudo> list = controllerDB.getTransformacionesPorARORxDAROR(aror_aror, daror_id_reg);
		
		return list;
	}
	
	
	public List<TransformacionArchivoRecaudo> getTransformacionesPorAROR(Long aror_aror){
		
		TransformacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		List<TransformacionArchivoRecaudo> list = controllerDB.getTransformacionesPorAROR(aror_aror);
		
		return list;
	}		
	
	 

}
