package com.developer.logic.modulo.unificacion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.logic.modulo.general.modelo.ConfiguracionGeneralServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoRecaudoPorUnificarControllerDB;

public class ArchivoRecaudoPorUnificarServicio {
	
	private static ArchivoRecaudoPorUnificarServicio instance;
	
	public static ArchivoRecaudoPorUnificarServicio getInstance() {
		if (instance == null) {
			instance = new ArchivoRecaudoPorUnificarServicio();
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
	
	
	public ArchivoRecaudoPorUnificar getDocumento(Long arpu_arpu){
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		return controllerDB.getDocumento(arpu_arpu);
		
		
	}
	
	public List<ArchivoRecaudoPorUnificar> getDocumentosPorAnteproyecto(Long prun_prun){
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		return controllerDB.getDocumentosPorProcesoUnificacion(prun_prun);
		
		
	}
	
	public String getRutaBaseDeArchivos(){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		
		return rutaGeneral+ "ArchivosAnexosAnteproyecto";
		
	}
	
	public String construirNombreDeArchivo(Long prun_prun, Long arpu_arpu){
		
		return "docAnexoAntp_"+prun_prun.toString()+"_doc_"+arpu_arpu+".ud";
		
		
	}
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public boolean crearDocumentoTransaccional(SqlSession session,
			ArchivoRecaudoPorUnificar documento) {
		
		return ArchivoRecaudoPorUnificarControllerDB.getInstance().crearDocumentoTransaccional(session, documento);
		
	}

	
			
	
	 

}
