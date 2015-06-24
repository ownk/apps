package com.developer.logic.modulo.conversion.modelo;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;
import com.developer.logic.modulo.conversion.dto.TransformacionArchivoRecaudo;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.controllerdb.TransformacionArchivoRecaudoControllerDB;
import com.developer.persistence.modulo.conversion.mapper.dao.TransformacionArchivoRecaudoDao;

public class TransformacionArchivoRecaudoServicio {
	
	private static TransformacionArchivoRecaudoServicio instance;
	
	public static TransformacionArchivoRecaudoServicio getInstance() {
		if (instance == null) {
			instance = new TransformacionArchivoRecaudoServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	

	
	public Boolean crearArchivoTransaccional(SqlSession session, TransformacionArchivoRecaudo transformacionArchivoRecaudo){
		
		Boolean sinErrores = true;
		TransformacionArchivoRecaudoControllerDB controllerDB = TransformacionArchivoRecaudoControllerDB.getInstance();
		sinErrores = controllerDB.crearTransformacionTransaccional(session, transformacionArchivoRecaudo);
		
		return sinErrores;
		
	}
	
	
	
	public List<TransformacionArchivoRecaudo> getTransformacionesPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		TransformacionArchivoRecaudoControllerDB controllerDB = TransformacionArchivoRecaudoControllerDB.getInstance();
		List<TransformacionArchivoRecaudo> list = controllerDB.getTransformacionesPorARORxDAROR(aror_aror, daror_id_reg);
		
		return list;
	}
	
	
			
	
	 

}
