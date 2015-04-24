package com.developer.logic.modulo.unificacion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificarRepetido;
import com.developer.logic.modulo.unificacion.dto.HistoricoArchivoRecaudoPorUnificar;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoRecaudoPorUnificarControllerDB;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoRecaudoPorUnificarRepetidoControllerDB;

public class ArchivoRecaudoPorUnificarRepetidoServicio {
	
	private static ArchivoRecaudoPorUnificarRepetidoServicio instance;
	
	public static ArchivoRecaudoPorUnificarRepetidoServicio getInstance() {
		if (instance == null) {
			instance = new ArchivoRecaudoPorUnificarRepetidoServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public Long getSiguienteID(){
		
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		return controllerDB.getSiguienteID();
		
	}
	
		
	public List<ArchivoRecaudoPorUnificar> getArchivosPorARUN(Long prun_prun){
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		List<ArchivoRecaudoPorUnificar> list = controllerDB.getArchivosPorPRUN(prun_prun);
		
		for (ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar : list) {
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
			
			
			sinErrores = sinErrores &&ArchivoRecaudoPorUnificarRepetidoControllerDB.getInstance().crearDocumentoTransaccional(session, archivoRecaudoPorUnificarRepetido);
			
			
			return sinErrores;		
					
        
		} catch (Exception e) {
			return false;
		}
	}
	
	
	

	private void completarInformacionAdicionalArchivo(ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar){
		try {
			
			if(archivoRecaudoPorUnificar!=null && archivoRecaudoPorUnificar.getArpu_arpu()!=null){
			
				List<HistoricoArchivoRecaudoPorUnificar> historicoArchivoRecaudoPorUnificar= ArchivoRecaudoPorUnificarControllerDB.getInstance().getHistoricoArchivo(archivoRecaudoPorUnificar.getArpu_arpu());
				archivoRecaudoPorUnificar.setHistoricoArchivoRecaudoPorUnificar(historicoArchivoRecaudoPorUnificar);
				
			}
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando informacion adicional Archivo.",e);
		
		}
		
	}
			
	
	 

}
