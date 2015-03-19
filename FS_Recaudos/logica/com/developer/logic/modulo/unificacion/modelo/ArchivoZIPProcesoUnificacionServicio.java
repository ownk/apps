package com.developer.logic.modulo.unificacion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.logic.modulo.general.modelo.ConfiguracionGeneralServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoZIPProcesoUnificacionControllerDB;

public class ArchivoZIPProcesoUnificacionServicio {
	
	private static ArchivoZIPProcesoUnificacionServicio instance;
	
	public static ArchivoZIPProcesoUnificacionServicio getInstance() {
		if (instance == null) {
			instance = new ArchivoZIPProcesoUnificacionServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public Long getSiguienteID(){
		
		ArchivoZIPProcesoUnificacionControllerDB controllerDB = ArchivoZIPProcesoUnificacionControllerDB.getInstance();
		return controllerDB.getSiguienteID();
		
	}
	
	
	public ArchivoZIPProcesoUnificacion getDocumento(Long azpu_azpu){
		ArchivoZIPProcesoUnificacionControllerDB controllerDB = ArchivoZIPProcesoUnificacionControllerDB.getInstance();
		return controllerDB.getDocumento(azpu_azpu);
		
		
	}
	
	public List<ArchivoZIPProcesoUnificacion> getDocumentosPorAnteproyecto(Long prun_prun){
		ArchivoZIPProcesoUnificacionControllerDB controllerDB = ArchivoZIPProcesoUnificacionControllerDB.getInstance();
		return controllerDB.getDocumentosPorProcesoUnificacion(prun_prun);
		
		
	}
	
	public String getRutaBaseDeArchivos(){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		
		return rutaGeneral+ "ArchivosAnexosAnteproyecto";
		
	}
	
	public String construirNombreDeArchivo(Long prun_prun, Long azpu_azpu){
		
		return "docAnexoAntp_"+prun_prun.toString()+"_doc_"+azpu_azpu+".ud";
		
		
	}
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public boolean crearDocumentoTransaccional(SqlSession session,
			ArchivoZIPProcesoUnificacion documento) {
		
		return ArchivoZIPProcesoUnificacionControllerDB.getInstance().crearDocumentoTransaccional(session, documento);
		
	}

	
			
	
	 

}
