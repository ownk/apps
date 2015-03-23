package com.developer.logic.modulo.unificacion.modelo;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;

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
	
	
	
	public String getNombreArchivoEnServidor(FileItem file){
	
		
		String nombreReal = FilenameUtils.getBaseName(file.getName());
		String extension = FilenameUtils.getExtension(file.getName());
		String nombreEnServidor = nombreReal+"."+extension;
		
		return nombreEnServidor;
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
