package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.OficinaRecaudo;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.controllerdb.TipoArchivoRecaudoControllerDB;
import com.developer.persistence.modulo.conversion.mapper.dao.TipoArchivoRecaudoDao;

public class TipoArchivoRecaudoServicio {
	
	private static TipoArchivoRecaudoServicio instance;
	
	public static TipoArchivoRecaudoServicio getInstance() {
		if (instance == null) {
			instance = new TipoArchivoRecaudoServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public TipoArchivoRecaudoConvertidor getTipoArchivo(String tpar_tpar){
		
		TipoArchivoRecaudoControllerDB controllerDB = TipoArchivoRecaudoControllerDB.getInstance();
		return controllerDB.getTipoArchivo(tpar_tpar);
		
		
	}
	
	
	
	public List<TipoArchivoRecaudoConvertidor> getTiposArchivoPorPRCO(Long prco_prco){
		
		TipoArchivoRecaudoControllerDB controllerDB = TipoArchivoRecaudoControllerDB.getInstance();
		List<TipoArchivoRecaudoConvertidor> list = controllerDB.getTiposArchivoPorPRCO(prco_prco);
		
		return list;
	}
	
	
			
	
	 

}
