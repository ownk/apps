package com.developer.logic.modulo.unificacion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.compara.dto.ComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.modelo.ComparacionArchivoRecaudoServicio;
import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.logic.modulo.general.modelo.ConfiguracionGeneralServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificarRepetido;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoRecaudoUnificadoControllerDB;

public class ArchivoRecaudoUnificadoServicio {
	
	ArchivoRecaudoUnificadoControllerDB controllerDB;
	public ArchivoRecaudoUnificadoServicio() {
		controllerDB = new ArchivoRecaudoUnificadoControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public Long getSiguienteID(){
		
		ArchivoRecaudoUnificadoControllerDB controllerDB = this.controllerDB;
		return controllerDB.getSiguienteID();
		
	}
	
	
	public ArchivoRecaudoUnificado getArchivo(Long arun_arun){
		ArchivoRecaudoUnificadoControllerDB controllerDB = this.controllerDB;
		return controllerDB.getArchivo(arun_arun);
		
		
	}
	
	public List<ArchivoRecaudoUnificado> getArchivosPorPRUN(Long prun_prun, Boolean infoCompleta){
		ArchivoRecaudoUnificadoControllerDB controllerDB = this.controllerDB;
		List<ArchivoRecaudoUnificado> list = controllerDB.getArchivosPorProcesoUnificacion(prun_prun);
		
		
		if(infoCompleta){
			for (ArchivoRecaudoUnificado archivoRecaudoUnificado : list) {
				completarInformacionAdicionalArchivo(archivoRecaudoUnificado);
				
			}
		}
		
		return list;
		
		
		
		
	}
	
	public String getRutaBaseDeArchivos(){
		ParametroConfiguracionGeneral parametroRutas = new ConfiguracionGeneralServicio().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
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
		
		return this.controllerDB.crearArchivoTransaccional(session, documento);
		
	}
	
	
	private void completarInformacionAdicionalArchivo(ArchivoRecaudoUnificado archivoRecaudoUnificado ){
		try {
			
			if(archivoRecaudoUnificado!=null && archivoRecaudoUnificado.getArun_arun()!=null){
				ArchivoRecaudoPorUnificarRepetidoServicio servicio = new ArchivoRecaudoPorUnificarRepetidoServicio();
				List<ArchivoRecaudoPorUnificarRepetido> list = servicio.getArchivosPorARUN(archivoRecaudoUnificado.getArun_arun());
				
				archivoRecaudoUnificado.setArchivosPorUnificarRepetidos(list);
				
				ComparacionArchivoRecaudoServicio comparacionArchivoRecaudoServicio = new ComparacionArchivoRecaudoServicio();
				List<ComparacionArchivoRecaudo> comparacionesArchivoRecaudos = comparacionArchivoRecaudoServicio.getComparacionesPorARUN(archivoRecaudoUnificado.getArun_arun(), true);
				
				archivoRecaudoUnificado.setComparacionesArchivoRecaudos(comparacionesArchivoRecaudos);
				
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
			
	
	 

}
