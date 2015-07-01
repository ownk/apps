package com.developer.logic.modulo.unificacion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificarRepetido;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.logic.modulo.unificacion.dto.HistoricoArchivoRecaudoPorUnificar;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoRecaudoPorUnificarControllerDB;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoRecaudoPorUnificarRepetidoControllerDB;

public class ArchivoRecaudoPorUnificarRepetidoServicio {
	
	ArchivoRecaudoPorUnificarControllerDB controllerDB;
	
	public ArchivoRecaudoPorUnificarRepetidoServicio() {
		controllerDB = new ArchivoRecaudoPorUnificarControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public Long getSiguienteID(){
		
		ArchivoRecaudoPorUnificarControllerDB controllerDB = this.controllerDB;
		return controllerDB.getSiguienteID();
		
	}
	
		
	public List<ArchivoRecaudoPorUnificarRepetido> getArchivosPorARUN(Long arun_arun){
		ArchivoRecaudoPorUnificarRepetidoControllerDB controllerDB = new ArchivoRecaudoPorUnificarRepetidoControllerDB();
		List<ArchivoRecaudoPorUnificarRepetido> list = controllerDB.getArchivosPorARUN(arun_arun);
		
		for (ArchivoRecaudoPorUnificarRepetido archivoRecaudoPorUnificar : list) {
			completarInformacionAdicionalArchivo(archivoRecaudoPorUnificar);
		}
		
		return list;
		
	}
	
	
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public Boolean crearDocumentoTransaccional(SqlSession session, ArchivoRecaudoPorUnificarRepetido archivoRecaudoPorUnificarRepetido,
			StringBuffer mensajeErrorOut ) {
		
		
		try {
			
			archivoRecaudoPorUnificarRepetido.setArpr_arpr(getSiguienteID());
			boolean sinErrores = true;
			
			
			sinErrores = sinErrores && new ArchivoRecaudoPorUnificarRepetidoControllerDB().crearDocumentoTransaccional(session, archivoRecaudoPorUnificarRepetido);
			
			
			return sinErrores;		
					
        
		} catch (Exception e) {
			return false;
		}
	}
	
	
	

	private void completarInformacionAdicionalArchivo(ArchivoRecaudoPorUnificarRepetido archivoRecaudoPorUnificarRepetido){
		try {
			
			if(archivoRecaudoPorUnificarRepetido!=null && archivoRecaudoPorUnificarRepetido.getArpr_arpr()!=null){
			
				ArchivoRecaudoPorUnificarServicio servicioARPU = new ArchivoRecaudoPorUnificarServicio();
				ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar = servicioARPU.getArchivoRecaudo(archivoRecaudoPorUnificarRepetido.getArpr_arpu());
				archivoRecaudoPorUnificarRepetido.setArchivoRecaudoPorUnificar(archivoRecaudoPorUnificar);
				
				
			}
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando informacion adicional Archivo.",e);
		
		}
		
	}
			
	
	 

}
