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
