package com.developer.logic.modulo.unificacion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;
import com.developer.persistence.modulo.unificacion.controllerdb.TipoArchivoRecaudoControllerDB;

public class TipoArchivoRecaudoServicio {
	
	
	TipoArchivoRecaudoControllerDB controllerDB;
	public TipoArchivoRecaudoServicio() {
		controllerDB = new TipoArchivoRecaudoControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	

	public TipoArchivoRecaudo getTipoArchivoRecaudo(String tpar_tpar){
		TipoArchivoRecaudoControllerDB controllerDB = this.controllerDB;
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
		
		return this.controllerDB.crearTipoArchivoTransaccional(session, tipoArchivo);
		
	}

	public TipoArchivoRecaudo getTipoArchivoRecaudoTransaccional(SqlSession session, String tpar_tpar){
		TipoArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		return controllerDB.getTipoArchivoTransaccional(session, tpar_tpar.toUpperCase());
		
		
	}
	
			
	public List<TipoArchivoRecaudo> getTipoArchivosRecaudoPorPRUN( Long prun_prun){
		TipoArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		return controllerDB.getTiposArchivoPorPRUN(prun_prun);
		
		
	}
	 

}
