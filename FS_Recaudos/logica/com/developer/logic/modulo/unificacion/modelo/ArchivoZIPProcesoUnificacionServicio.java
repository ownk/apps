package com.developer.logic.modulo.unificacion.modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.appender.Zipper;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
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
	
	
	public ArchivoZIPProcesoUnificacion getArchivo(Long azpu_azpu){
		ArchivoZIPProcesoUnificacionControllerDB controllerDB = ArchivoZIPProcesoUnificacionControllerDB.getInstance();
		ArchivoZIPProcesoUnificacion archivoZIPProcesoUnificacion =  controllerDB.getArchivo(azpu_azpu);
		
		completarInformacionAdicionalArhivo(archivoZIPProcesoUnificacion);
		
		return archivoZIPProcesoUnificacion;
	}
	
	public List<ArchivoZIPProcesoUnificacion> getArchivosPorProceso(Long prun_prun){
		ArchivoZIPProcesoUnificacionControllerDB controllerDB = ArchivoZIPProcesoUnificacionControllerDB.getInstance();
		List<ArchivoZIPProcesoUnificacion> list =  controllerDB.getArchivosPorPRUN(prun_prun);
		
		for (ArchivoZIPProcesoUnificacion archivoZIPProcesoUnificacion : list) {
			completarInformacionAdicionalArhivo(archivoZIPProcesoUnificacion);
		}
		
		return list;
		
	}
	
	
	
	public String getNombreArchivoEnServidor(FileItem file){
		return getNombreArchivoEnServidor(file.getName());
	}
	
	public String getNombreArchivoEnServidor(File file){
		return getNombreArchivoEnServidor(file.getName());
		
		
	}
	
	
	private String getNombreArchivoEnServidor(String fileName){
		String nombreReal = FilenameUtils.getBaseName(fileName);
		String extension = FilenameUtils.getExtension(fileName);
		String nombreEnServidor = nombreReal+"."+extension;
		
		return nombreEnServidor;
	}
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public ArchivoZIPProcesoUnificacion crearDocumentoTransaccional(SqlSession session, ProcesoUnificacionArchivos procesoUnificacionArchivos,
			File fileZIP, String rutaUnZip, StringBuffer mensajeErrorOut) {
		
		boolean sinErrores = true;
		
		if (fileZIP.exists()){
			
			try {
				
				
				Zipper zipper = new Zipper();
				sinErrores = sinErrores && zipper.unZip(fileZIP, rutaUnZip);
				
				
				if(!sinErrores){
					
					String error= "Error obtieniendo archivos comprimidos del archivo. "+fileZIP.getName();
					SimpleLogger.error(error);
					mensajeErrorOut.append(error);
					
					return null;
				
				}else{
				
					ArrayList<File> unZIPFiles= zipper.getFilesUnZip();
					int totalFiles = unZIPFiles.size();
					
					Long hash =FileUtils.checksumCRC32(fileZIP);
					Long size = fileZIP.length();
					
					ArchivoZIPProcesoUnificacion archivoZIPProcesoUnificacion = new ArchivoZIPProcesoUnificacion();
					archivoZIPProcesoUnificacion.setAzpu_azpu(getSiguienteID());
					archivoZIPProcesoUnificacion.setAzpu_bytes(size.toString());
					archivoZIPProcesoUnificacion.setAzpu_extension(FilenameUtils.getExtension(fileZIP.getName()));
					archivoZIPProcesoUnificacion.setAzpu_nombre(FilenameUtils.getBaseName(fileZIP.getName()));
					archivoZIPProcesoUnificacion.setAzpu_fcrea(procesoUnificacionArchivos.getPrun_fcrea());
					archivoZIPProcesoUnificacion.setAzpu_hash( hash.toString());
					archivoZIPProcesoUnificacion.setAzpu_observ(procesoUnificacionArchivos.getPrun_observ());
					archivoZIPProcesoUnificacion.setAzpu_prun(procesoUnificacionArchivos.getPrun_prun());
					archivoZIPProcesoUnificacion.setAzpu_url(fileZIP.getAbsolutePath());
					archivoZIPProcesoUnificacion.setAzpu_usua(procesoUnificacionArchivos.getPrun_usua());
					archivoZIPProcesoUnificacion.setAzpu_archivos(new Long(totalFiles));
				
					
					sinErrores = sinErrores && ArchivoZIPProcesoUnificacionControllerDB.getInstance().crearArchivoTransaccional(session, archivoZIPProcesoUnificacion);
					
					if(sinErrores){
						archivoZIPProcesoUnificacion.setProcesoUnificacionArchivos(procesoUnificacionArchivos);
						
						ArchivoRecaudoPorUnificarServicio archivoRecaudoPorUnificarServicio = new ArchivoRecaudoPorUnificarServicio();
						
						for (File file : unZIPFiles) {
							
							ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar= archivoRecaudoPorUnificarServicio.crearDocumentoTransaccional(session, archivoZIPProcesoUnificacion, file, mensajeErrorOut);
						
							if(archivoRecaudoPorUnificar==null){
								return null;
								
							}else{
								
								archivoRecaudoPorUnificar.setArchivoAZPU(archivoZIPProcesoUnificacion);
								archivoRecaudoPorUnificar.setProcesoUnificacionArchivos(procesoUnificacionArchivos);
								archivoZIPProcesoUnificacion.getArchivosARPU().add(archivoRecaudoPorUnificar);
							}
							
							
						}
						
						
						return archivoZIPProcesoUnificacion;
					}else{
						
						return null;
					}
				
					 
				}
				
				
				
				
			} catch (Exception e) {
				return null;
			}
		
		
		}else{
			
			return null;
		}
			
		
	}
	
	

	private void completarInformacionAdicionalArhivo(ArchivoZIPProcesoUnificacion archivoZIPProcesoUnificacion){
		
		try {
			if(archivoZIPProcesoUnificacion!= null && archivoZIPProcesoUnificacion.getAzpu_azpu()!=null){
				
				ArchivoRecaudoPorUnificarServicio servicio = new ArchivoRecaudoPorUnificarServicio();
				List<ArchivoRecaudoPorUnificar> archivosARPU =servicio.getArchivosPorAZPU(archivoZIPProcesoUnificacion.getAzpu_azpu());
				
				archivoZIPProcesoUnificacion.setArchivosARPU(archivosARPU);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
			
	
	 

}
