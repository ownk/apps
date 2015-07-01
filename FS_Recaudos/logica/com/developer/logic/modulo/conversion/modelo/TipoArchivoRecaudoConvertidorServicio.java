package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EstadoPlanAplicaPlanGenerico;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;
import com.developer.persistence.modulo.conversion.controllerdb.TipoArchivoRecaudoConvertidorControllerDB;

public class TipoArchivoRecaudoConvertidorServicio {
	
	private static TipoArchivoRecaudoConvertidorServicio instance;
	
	public static TipoArchivoRecaudoConvertidorServicio getInstance() {
		if (instance == null) {
			instance = new TipoArchivoRecaudoConvertidorServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public TipoArchivoRecaudoConvertidor getTipoArchivo(String tpar_tpar){
		
		TipoArchivoRecaudoConvertidorControllerDB controllerDB = TipoArchivoRecaudoConvertidorControllerDB.getInstance();
		return controllerDB.getTipoArchivo(tpar_tpar);
		
		
	}
	
	
	
	public List<TipoArchivoRecaudoConvertidor> getTiposArchivoPorPRCO(Long prco_prco){
		
		TipoArchivoRecaudoConvertidorControllerDB controllerDB = TipoArchivoRecaudoConvertidorControllerDB.getInstance();
		List<TipoArchivoRecaudoConvertidor> list = controllerDB.getTiposArchivoPorPRCO(prco_prco);
		
		return list;
	}
	
	public List<EstadoPlanAplicaPlanGenerico> getEstadosAplicaPlanGenericoPorTPAR(String tpar_tpar){
			
		TipoArchivoRecaudoConvertidorControllerDB controllerDB = TipoArchivoRecaudoConvertidorControllerDB.getInstance();
		List<EstadoPlanAplicaPlanGenerico> list = controllerDB.getEstadosAplicaPlanGenericoPorTPAR(tpar_tpar);
		
		return list;
	}
	 

}
