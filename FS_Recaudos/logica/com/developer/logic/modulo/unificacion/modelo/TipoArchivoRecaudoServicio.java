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
		return controllerDB.getTipoArchivo(tpar_tpar.toUpperCase());
		
		
	}
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public boolean crearTipoArchivoTransaccional(SqlSession session,
			TipoArchivoRecaudo tipoArchivo) {
		
		tipoArchivo.setTpar_tpar(tipoArchivo.getTpar_tpar().toUpperCase());
		
		return TipoArchivoRecaudoControllerDB.getInstance().crearTipoArchivoTransaccional(session, tipoArchivo);
		
	}

	public TipoArchivoRecaudo getTipoArchivoRecaudoTransaccional(SqlSession session, String tpar_tpar){
		TipoArchivoRecaudoControllerDB controllerDB = TipoArchivoRecaudoControllerDB.getInstance();
		return controllerDB.getTipoArchivoTransaccional(session, tpar_tpar.toUpperCase());
		
		
	}
	
			
	
	 

}
