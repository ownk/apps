package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EstadoPlanAplicaPlanGenerico;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;
import com.developer.persistence.modulo.conversion.controllerdb.TipoArchivoRecaudoConvertidorControllerDB;

public class TipoArchivoRecaudoConvertidorServicio {
	
	
	TipoArchivoRecaudoConvertidorControllerDB controllerDB;
	public TipoArchivoRecaudoConvertidorServicio() {
		controllerDB = new TipoArchivoRecaudoConvertidorControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public TipoArchivoRecaudoConvertidor getTipoArchivo(String tpar_tpar){
		
		TipoArchivoRecaudoConvertidorControllerDB controllerDB = this.controllerDB;
		return controllerDB.getTipoArchivo(tpar_tpar);
		
		
	}
	
	
	
	public List<TipoArchivoRecaudoConvertidor> getTiposArchivoPorPRCO(Long prco_prco){
		
		TipoArchivoRecaudoConvertidorControllerDB controllerDB = this.controllerDB;
		List<TipoArchivoRecaudoConvertidor> list = controllerDB.getTiposArchivoPorPRCO(prco_prco);
		
		return list;
	}
	
	public List<EstadoPlanAplicaPlanGenerico> getEstadosAplicaPlanGenericoPorTPAR(String tpar_tpar){
			
		TipoArchivoRecaudoConvertidorControllerDB controllerDB = this.controllerDB;
		List<EstadoPlanAplicaPlanGenerico> list = controllerDB.getEstadosAplicaPlanGenericoPorTPAR(tpar_tpar);
		
		return list;
	}
	
	
	public List<TipoArchivoRecaudoConvertidor> getAllTiposArchivo(){
		
		TipoArchivoRecaudoConvertidorControllerDB controllerDB = this.controllerDB;
		return controllerDB.getAllTiposArchivo();
		
		
	}
	 

}
