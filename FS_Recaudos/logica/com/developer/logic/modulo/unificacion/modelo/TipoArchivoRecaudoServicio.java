package com.developer.logic.modulo.unificacion.modelo;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;
import com.developer.persistence.modulo.unificacion.controllerdb.TipoArchivoRecaudoControllerDB;

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
	

	public TipoArchivoRecaudo getTipoArchivoRecaudo(String tpar_tpar){
		TipoArchivoRecaudoControllerDB controllerDB = TipoArchivoRecaudoControllerDB.getInstance();
		return controllerDB.getTipoArchivo(tpar_tpar);
		
		
	}
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public boolean crearTipoArchivoTransaccional(SqlSession session,
			TipoArchivoRecaudo documento) {
		
		return TipoArchivoRecaudoControllerDB.getInstance().crearTipoArchivoTransaccional(session, documento);
		
	}

	
			
	
	 

}
