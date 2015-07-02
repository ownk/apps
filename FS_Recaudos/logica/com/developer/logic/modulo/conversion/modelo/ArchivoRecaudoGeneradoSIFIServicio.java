package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoGeneradoSIFI;
import com.developer.persistence.modulo.conversion.controllerdb.ArchivoRecaudoGeneradoSIFIControllerDB;

public class ArchivoRecaudoGeneradoSIFIServicio {
	
	ArchivoRecaudoGeneradoSIFIControllerDB controllerDB;
	public ArchivoRecaudoGeneradoSIFIServicio() {
		controllerDB = new ArchivoRecaudoGeneradoSIFIControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
		
	public List<ArchivoRecaudoGeneradoSIFI> getArchivosPorPRCO(Long prco_prco){
		ArchivoRecaudoGeneradoSIFIControllerDB controllerDB = this.controllerDB;
		List<ArchivoRecaudoGeneradoSIFI> list = controllerDB.getArchivosPorPRCO(prco_prco);
		
		for (ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI : list) {
			completarInformacionAdicionalArchivo(archivoRecaudoGeneradoSIFI);
		}
		
		return list;
		
	}
	
	public ArchivoRecaudoGeneradoSIFI getArchivoRecaudo(Long arge_arge){
		ArchivoRecaudoGeneradoSIFIControllerDB controllerDB = this.controllerDB;
		ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI = controllerDB.getDocumento(arge_arge);
		
		completarInformacionAdicionalArchivo(archivoRecaudoGeneradoSIFI);
		
		return archivoRecaudoGeneradoSIFI;
		
	}
	
	
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public Boolean crearDocumentoTransaccional(SqlSession session, 
											   ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI, 
											   StringBuffer mensajeErrorOut ) {
		
		
		
		try {
			
			archivoRecaudoGeneradoSIFI.setArge_arge(archivoRecaudoGeneradoSIFI.getArge_aror());
			archivoRecaudoGeneradoSIFI.setArge_earge(ArchivoRecaudoGeneradoSIFI.GENERADO);
			boolean sinErrores = true;
			
			
			sinErrores = sinErrores && this.controllerDB.crearArchivoTransaccional(session, archivoRecaudoGeneradoSIFI);
			
			
			return sinErrores;		
					
        
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public Boolean crearDetallesTransaccional(SqlSession session, 
											 ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI,
											 List<DetalleArchivoRecaudoGeneradoSIFI> detallesArchivoRecaudoGeneradoSIFI, 
											 StringBuffer mensajeErrorOut ) {

		boolean sinErrores = true;
		
		try {
			
			//Se contabiliza a partir del registro 2 ya que el encabezado sera el registro 1
			int numRegistro=2;
			for (DetalleArchivoRecaudoGeneradoSIFI detalleArchivoRecaudoGeneradoSIFI : detallesArchivoRecaudoGeneradoSIFI) {
				
				detalleArchivoRecaudoGeneradoSIFI.setDarge_arge(archivoRecaudoGeneradoSIFI.getArge_arge());
				detalleArchivoRecaudoGeneradoSIFI.setDarge_id_reg(new Long(numRegistro));
				numRegistro++;
				sinErrores = sinErrores && this.controllerDB.crearDetalleArchivoTransaccional(session, detalleArchivoRecaudoGeneradoSIFI);
				
			}
		
			
		
			return sinErrores;		
		
		
		} catch (Exception e) {
			return false;
		}
	}

	
	
	
	
	

	private void completarInformacionAdicionalArchivo(ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI){
		try {
			
			if(archivoRecaudoGeneradoSIFI!=null && archivoRecaudoGeneradoSIFI.getArge_arge()!=null){
			
				/*TODO 
				 * Se debe consultar el detalle del archivo
				 */
				
			}
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando informacion adicional Archivo.",e);
		
		}
		
	}
			
	
	 

}
