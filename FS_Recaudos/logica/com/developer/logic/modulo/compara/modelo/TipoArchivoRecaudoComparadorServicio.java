package com.developer.logic.modulo.compara.modelo;

import java.util.List;

import com.developer.logic.modulo.compara.dto.TipoArchivoRecaudoComparador;
import com.developer.persistence.modulo.compara.controllerdb.TipoArchivoRecaudoComparadorControllerDB;

public class TipoArchivoRecaudoComparadorServicio {
	
	
	TipoArchivoRecaudoComparadorControllerDB controllerDB;
	public TipoArchivoRecaudoComparadorServicio() {
		controllerDB = new TipoArchivoRecaudoComparadorControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public TipoArchivoRecaudoComparador getTipoArchivo(String tpar_tpar){
		
		TipoArchivoRecaudoComparadorControllerDB controllerDB = this.controllerDB;
		return controllerDB.getTipoArchivo(tpar_tpar);
		
		
	}
	
	
	
	
	public List<TipoArchivoRecaudoComparador> getAllTiposArchivo(){
		
		TipoArchivoRecaudoComparadorControllerDB controllerDB = this.controllerDB;
		return controllerDB.getAllTiposArchivo();
		
		
	}
	


}
