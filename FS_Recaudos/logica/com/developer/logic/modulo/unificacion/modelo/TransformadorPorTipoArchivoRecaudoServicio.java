package com.developer.logic.modulo.unificacion.modelo;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;

public class TransformadorPorTipoArchivoRecaudoServicio  {
	
	
	private static TransformadorPorTipoArchivoRecaudoServicio instance;
	
	private TransformadorPorTipoArchivoRecaudoServicio(){
		
	}
	
	public static TransformadorPorTipoArchivoRecaudoServicio getInstance() {
		if (instance == null) {
			instance = new TransformadorPorTipoArchivoRecaudoServicio();
		}
		
		return instance;
	}
	
	public File hacerTransformacionPorTipoArchivo(TipoArchivoRecaudo tipoArchivo, File file, String rutaArchivosPorUnificar, StringBuffer mensajeErrorOut){
		
		File fileTransformado = null;
		
		if(file!=null && file.exists() && file.length()>0) {
			if(tipoArchivo.getTpar_estr().equals(TipoArchivoRecaudo.ESTR_ASOBANCARIA)){
				
				String fileName= FilenameUtils.getBaseName(file.getName());
				String fileExtension = FilenameUtils.getExtension(file.getName());
				
				String newFileName = fileName+"_FC."+fileExtension;
				
				
				ConvertidorArchivoAsobancaria convertidorArchivoAsobancaria = new ConvertidorArchivoAsobancaria(file, rutaArchivosPorUnificar, newFileName);
				
				fileTransformado= convertidorArchivoAsobancaria.generarArchivoFiduciaria(mensajeErrorOut);
				
			}else{
				fileTransformado = file;
			}
		}else{
			fileTransformado = file;
		}
		
		return fileTransformado;
		
		
	}

}
