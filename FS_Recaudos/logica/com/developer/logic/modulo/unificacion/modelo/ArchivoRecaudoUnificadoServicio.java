package com.developer.logic.modulo.unificacion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.logic.modulo.general.modelo.ConfiguracionGeneralServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoRecaudoUnificadoControllerDB;

public class ArchivoRecaudoUnificadoServicio {
	
	private static ArchivoRecaudoUnificadoServicio instance;
	
	public static ArchivoRecaudoUnificadoServicio getInstance() {
		if (instance == null) {
			instance = new ArchivoRecaudoUnificadoServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public Long getSiguienteID(){
		
		ArchivoRecaudoUnificadoControllerDB controllerDB = ArchivoRecaudoUnificadoControllerDB.getInstance();
		return controllerDB.getSiguienteID();
		
	}
	
	
	public ArchivoRecaudoUnificado getArchivo(Long arun_arun){
		ArchivoRecaudoUnificadoControllerDB controllerDB = ArchivoRecaudoUnificadoControllerDB.getInstance();
		return controllerDB.getArchivo(arun_arun);
		
		
	}
	
	public List<ArchivoRecaudoUnificado> getArchivosPorPRUN(Long prun_prun){
		ArchivoRecaudoUnificadoControllerDB controllerDB = ArchivoRecaudoUnificadoControllerDB.getInstance();
		return controllerDB.getArchivosPorProcesoUnificacion(prun_prun);
		
		
	}
	
	public String getRutaBaseDeArchivos(){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		
		return rutaGeneral+ "ArchivosAnexosAnteproyecto";
		
	}
	
	public String construirNombreDeArchivo(Long prun_prun, Long arun_arun){
		
		return "docAnexoAntp_"+prun_prun.toString()+"_doc_"+arun_arun+".ud";
		
		
	}
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public boolean crearArchivoTransaccional(SqlSession session,
			ArchivoRecaudoUnificado documento) {
		
		return ArchivoRecaudoUnificadoControllerDB.getInstance().crearArchivoTransaccional(session, documento);
		
	}

	
			
	
	 

}
