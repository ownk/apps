package com.developer.logic.modulo.compara.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.compara.dto.ComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.DetalleComparacionArchivoRecaudo;
import com.developer.persistence.modulo.compara.controllerdb.ComparacionArchivoRecaudoControllerDB;

public class ComparacionArchivoRecaudoServicio {
	
	ComparacionArchivoRecaudoControllerDB controllerDB;
	public ComparacionArchivoRecaudoServicio() {
		controllerDB = new ComparacionArchivoRecaudoControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
		
	public List<ComparacionArchivoRecaudo> getComparacionesPorARUN(Long arun_arun){
		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		List<ComparacionArchivoRecaudo> list = controllerDB.getComparacionesPorARUN(arun_arun);
		
		for (ComparacionArchivoRecaudo comparacionArchivo : list) {
			completarInformacionAdicionalArchivo(comparacionArchivo);
		}
		
		return list;
		
	}
	
	public ComparacionArchivoRecaudo getComparacion(Long cpar_cpar){
		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		ComparacionArchivoRecaudo comparacionArchivo = controllerDB.getComparacion(cpar_cpar);
		
		completarInformacionAdicionalArchivo(comparacionArchivo);
		
		return comparacionArchivo;
		
	}
	
	
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	
	public Long getSiguienteID(){
		
		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		return controllerDB.getSiguienteID();
		
	}
	
	
	public Boolean crearComparacionTransaccional(SqlSession session, 
											   ComparacionArchivoRecaudo comparacionArchivo, 
											   StringBuffer mensajeErrorOut ) {
		
		
		
		try {
			
			boolean sinErrores = true;
			
			
			sinErrores = sinErrores && this.controllerDB.crearComparacionTransaccional(session, comparacionArchivo);
			
			
			return sinErrores;		
					
        
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public Boolean crearDetallesTransaccional(SqlSession session, 
											 ComparacionArchivoRecaudo comparacionArchivo,
											 List<DetalleComparacionArchivoRecaudo> detallesComparacionArchivoRecaudo, 
											 StringBuffer mensajeErrorOut ) {

		boolean sinErrores = true;
		
		try {
			
			for (DetalleComparacionArchivoRecaudo detalleComparacionArchivoRecaudo : detallesComparacionArchivoRecaudo) {
				detalleComparacionArchivoRecaudo.setDcpar_cpar(comparacionArchivo.getCpar_cpar());
				detalleComparacionArchivoRecaudo.setDcpar_fcrea(comparacionArchivo.getCpar_fcrea());

				
				sinErrores = sinErrores && this.controllerDB.crearDetalleComparacionTransaccional(session, detalleComparacionArchivoRecaudo);
				
			}
		
			
		
			return sinErrores;		
		
		
		} catch (Exception e) {
			return false;
		}
	}

	
	public List<DetalleComparacionArchivoRecaudo> getAllDetallesCPAR(Long cpar_cpar){
		
		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		List<DetalleComparacionArchivoRecaudo> list = controllerDB.getAllDetallesCPAR(cpar_cpar);
		
		return list;
		
	}
	
	

	private void completarInformacionAdicionalArchivo(ComparacionArchivoRecaudo comparacionArchivo){
		try {
			
			if(comparacionArchivo!=null && comparacionArchivo.getCpar_cpar()!=null){
			
				List<DetalleComparacionArchivoRecaudo> detalles = controllerDB.getAllDetallesCPAR(comparacionArchivo.getCpar_cpar());
				comparacionArchivo.setDetalles(detalles);
				
				
				
			}
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando informacion adicional Archivo.",e);
		
		}
		
	}
			
	
	 

}
