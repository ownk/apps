package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.controllerdb.ArchivoRecaudoOriginalPorConvertirControllerDB;

public class ArchivoRecaudoOriginalPorConvertirServicio {
	
	ArchivoRecaudoOriginalPorConvertirControllerDB controllerDB;
	public ArchivoRecaudoOriginalPorConvertirServicio() {
		controllerDB = new ArchivoRecaudoOriginalPorConvertirControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
		
	public List<ArchivoRecaudoOriginalPorConvertir> getArchivosPorPRCO(Long prco_prco){
		ArchivoRecaudoOriginalPorConvertirControllerDB controllerDB = this.controllerDB;
		List<ArchivoRecaudoOriginalPorConvertir> list = controllerDB.getArchivosPorPRCO(prco_prco);
		
		for (ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir : list) {
			completarInformacionAdicionalArchivo(archivoRecaudoOriginalPorConvertir);
		}
		
		return list;
		
	}
	
	public ArchivoRecaudoOriginalPorConvertir getArchivoRecaudo(Long aror_aror){
		ArchivoRecaudoOriginalPorConvertirControllerDB controllerDB = this.controllerDB;
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
			archivoRecaudoOriginalPorConvertir.setAror_earor(ArchivoRecaudoOriginalPorConvertir.EN_PROCESO);
			boolean sinErrores = true;
			
			
			sinErrores = sinErrores && this.controllerDB.crearArchivoTransaccional(session, archivoRecaudoOriginalPorConvertir);
			
			
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

				
				sinErrores = sinErrores && this.controllerDB.crearDetalleArchivoTransaccional(session, detalleArchivoRecaudoOriginalPorConvertir);
				
			}
		
			
		
			return sinErrores;		
		
		
		} catch (Exception e) {
			return false;
		}
	}

	
	public List<DetalleArchivoRecaudoOriginalPorConvertir> getAllDetallesAROR(Long aror_aror){
		
		ArchivoRecaudoOriginalPorConvertirControllerDB controllerDB = this.controllerDB;
		List<DetalleArchivoRecaudoOriginalPorConvertir> list = controllerDB.getAllDetallesAROR(aror_aror);
		
		return list;
		
	}
	
	
	
	
	public Boolean setEstadoTransaccional(SqlSession session, Long aror_aror, String estado, String observacion, Usuario usuario){
		
		ArchivoRecaudoOriginalPorConvertirControllerDB controllerDB = this.controllerDB;
		return controllerDB.setEstadoTransaccional(session, aror_aror, estado, observacion, usuario);
		
		
		
	}
	
	public Boolean setEstado(Long aror_aror, String estado, String observacion, Usuario usuario){
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		Boolean sinErrores = false;
		try {
			
			
			sinErrores = setEstadoTransaccional(session, aror_aror, estado, observacion, usuario);
			
			if(sinErrores){
				session.commit();
				
			}else{
				session.rollback();
			}
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			session.rollback();
			
			sinErrores = false;
			
		} 	finally {
			session.close();
		}
		
		return sinErrores;
		
		
	}
	
	
	

	private void completarInformacionAdicionalArchivo(ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir){
		try {
			
			if(archivoRecaudoOriginalPorConvertir!=null && archivoRecaudoOriginalPorConvertir.getAror_aror()!=null){
			
				List<DetalleArchivoRecaudoOriginalPorConvertir> detalles = controllerDB.getAllDetallesAROR(archivoRecaudoOriginalPorConvertir.getAror_aror());
				archivoRecaudoOriginalPorConvertir.setDetalles(detalles);
				
			}
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando informacion adicional Archivo.",e);
		
		}
		
	}
			
	
	 

}
