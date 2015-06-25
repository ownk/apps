package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.persistence.modulo.conversion.controllerdb.ArchivoRecaudoOriginalPorConvertirControllerDB;

public class ArchivoRecaudoOriginalPorConvertirServicio {
	
	private static ArchivoRecaudoOriginalPorConvertirServicio instance;
	
	public static ArchivoRecaudoOriginalPorConvertirServicio getInstance() {
		if (instance == null) {
			instance = new ArchivoRecaudoOriginalPorConvertirServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
		
	public List<ArchivoRecaudoOriginalPorConvertir> getArchivosPorPRCO(Long prco_prco){
		ArchivoRecaudoOriginalPorConvertirControllerDB controllerDB = ArchivoRecaudoOriginalPorConvertirControllerDB.getInstance();
		List<ArchivoRecaudoOriginalPorConvertir> list = controllerDB.getArchivosPorPRCO(prco_prco);
		
		for (ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir : list) {
			completarInformacionAdicionalArchivo(archivoRecaudoOriginalPorConvertir);
		}
		
		return list;
		
	}
	
	public ArchivoRecaudoOriginalPorConvertir getArchivoRecaudo(Long aror_aror){
		ArchivoRecaudoOriginalPorConvertirControllerDB controllerDB = ArchivoRecaudoOriginalPorConvertirControllerDB.getInstance();
		ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir = controllerDB.getDocumento(aror_aror);
		
		completarInformacionAdicionalArchivo(archivoRecaudoOriginalPorConvertir);
		
		return archivoRecaudoOriginalPorConvertir;
		
	}
	
	
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public Boolean crearDocumentoTransaccional(SqlSession session, 
											   ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir, 
											   StringBuffer mensajeErrorOut ) {
		
		
		
		try {
			
			archivoRecaudoOriginalPorConvertir.setAror_aror(archivoRecaudoOriginalPorConvertir.getAror_aror());
			archivoRecaudoOriginalPorConvertir.setAror_earor(ArchivoRecaudoOriginalPorConvertir.POR_CONVERTIR);
			boolean sinErrores = true;
			
			
			sinErrores = sinErrores && ArchivoRecaudoOriginalPorConvertirControllerDB.getInstance().crearArchivoTransaccional(session, archivoRecaudoOriginalPorConvertir);
			
			
			return sinErrores;		
					
        
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public Boolean crearDetallesTransaccional(SqlSession session, 
											 ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir,
											 List<DetalleArchivoRecaudoOriginalPorConvertir> detallesArchivoRecaudoOriginalPorConvertir, 
											 StringBuffer mensajeErrorOut ) {

		boolean sinErrores = true;
		
		try {
			
			for (DetalleArchivoRecaudoOriginalPorConvertir detalleArchivoRecaudoOriginalPorConvertir : detallesArchivoRecaudoOriginalPorConvertir) {
				detalleArchivoRecaudoOriginalPorConvertir.setDaror_aror(archivoRecaudoOriginalPorConvertir.getAror_aror());
				detalleArchivoRecaudoOriginalPorConvertir.setDaror_fcrea(archivoRecaudoOriginalPorConvertir.getAror_fcrea());

				
				sinErrores = sinErrores && ArchivoRecaudoOriginalPorConvertirControllerDB.getInstance().crearDetalleArchivoTransaccional(session, detalleArchivoRecaudoOriginalPorConvertir);
				
			}
		
			
		
			return sinErrores;		
		
		
		} catch (Exception e) {
			return false;
		}
	}

	
	
	
	
	

	private void completarInformacionAdicionalArchivo(ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir){
		try {
			
			if(archivoRecaudoOriginalPorConvertir!=null && archivoRecaudoOriginalPorConvertir.getAror_aror()!=null){
			
				/*TODO 
				 * Se debe consultar las validaciones y las transformaciones
				 * Se debe consultar el detalle del archivo
				 */
				
			}
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando informacion adicional Archivo.",e);
		
		}
		
	}
			
	
	 

}
