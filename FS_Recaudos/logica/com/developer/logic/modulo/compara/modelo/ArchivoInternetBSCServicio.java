package com.developer.logic.modulo.compara.modelo;

import java.io.File;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.compara.dto.ArchivoInternetBSC;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.persistence.modulo.compara.controllerdb.ArchivoInternetBSCControllerDB;

public class ArchivoInternetBSCServicio {
	
	
	ArchivoInternetBSCControllerDB controllerDB;
	public ArchivoInternetBSCServicio() {
		controllerDB = new ArchivoInternetBSCControllerDB();
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public Long getSiguienteID(){
		
		ArchivoInternetBSCControllerDB controllerDB = this.controllerDB;
		return controllerDB.getSiguienteID();
		
	}
	
	
	public ArchivoInternetBSC getArchivo(Long ibsc_ibsc){
		ArchivoInternetBSCControllerDB controllerDB = this.controllerDB;
		ArchivoInternetBSC archivoBSC =  controllerDB.getArchivo(ibsc_ibsc);
		
		completarInformacionAdicionalArhivo(archivoBSC);
		
		return archivoBSC;
	}
	
	public ArchivoInternetBSC getArchivoBasico(Long ibsc_ibsc){
		ArchivoInternetBSCControllerDB controllerDB = this.controllerDB;
		ArchivoInternetBSC archivoBSC =  controllerDB.getArchivo(ibsc_ibsc);
		
		return archivoBSC;
	}
	
	public List<ArchivoInternetBSC> getArchivosPorARUN(Long prun_prun){
		ArchivoInternetBSCControllerDB controllerDB = this.controllerDB;
		List<ArchivoInternetBSC> list =  controllerDB.getArchivosPorARUN(prun_prun);
		
		for (ArchivoInternetBSC archivoBSC : list) {
			completarInformacionAdicionalArhivo(archivoBSC);
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
	
	public ArchivoInternetBSC crearDocumentoTransaccional(SqlSession session, ArchivoRecaudoUnificado archivoUnificado,
			File fileExcel, String observacion, Usuario usuario,  StringBuffer mensajeErrorOut) {
		
		boolean sinErrores = true;
		
		if (fileExcel.exists()){
			
			try {
				
				
					Long hash =FileUtils.checksumCRC32(fileExcel);
					Long size = fileExcel.length();
					
					ArchivoInternetBSC archivoBSC = new ArchivoInternetBSC();
					archivoBSC.setIbsc_ibsc(getSiguienteID());
					archivoBSC.setIbsc_bytes(size.toString());
					archivoBSC.setIbsc_extension(FilenameUtils.getExtension(fileExcel.getName()));
					archivoBSC.setIbsc_nombre(FilenameUtils.getBaseName(fileExcel.getName()));
					archivoBSC.setIbsc_hash( hash.toString());
					archivoBSC.setIbsc_observ(observacion);
					archivoBSC.setIbsc_arun(archivoUnificado.getArun_arun());
					archivoBSC.setIbsc_url(fileExcel.getAbsolutePath());
					archivoBSC.setIbsc_usua(usuario.getUsua_usua());
					
				
					
					sinErrores = sinErrores && this.controllerDB.crearArchivoTransaccional(session, archivoBSC);
					
					if(sinErrores){
						
						return archivoBSC;
					}else{
						
						return null;
					}
				
			
				
				
			} catch (Exception e) {
				return null;
			}
		
		
		}else{
			
			return null;
		}
			
		
	}
	
	
	
	
	

	private void completarInformacionAdicionalArhivo(ArchivoInternetBSC archivoBSC){
		
		try {
			if(archivoBSC!= null && archivoBSC.getIbsc_ibsc()!=null){
				
				//TODO: Revisar que se debe completar
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
			
	
	 

}
