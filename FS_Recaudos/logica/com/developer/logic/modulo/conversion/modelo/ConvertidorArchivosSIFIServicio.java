package com.developer.logic.modulo.conversion.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;

public class ConvertidorArchivosSIFIServicio {
	
	
	public ConvertidorArchivosSIFIServicio() {
		
	}
	
	
	public Boolean generarArchivosSIFIPorProceso(ProcesoUnificacionArchivos procesoUnificacionArchivos, Usuario usuario, StringBuffer mensajeErrorOut){
		
		Boolean sinErrores =true; 
		
		ProcesoUnificacionArchivosServicio procesoUnificacionArchivosServicio = new ProcesoUnificacionArchivosServicio();
		
		
		ServerServicio serverServicio = new ServerServicio();
		Date currentDate = serverServicio.getSysdate();
		
		
		procesoUnificacionArchivos= procesoUnificacionArchivosServicio.getProcesoUnificacionArchivos(procesoUnificacionArchivos.getPrun_prun());
		
				
		
		
		try {
		
			
			ProcesoConversionArchivosServicio procesoConversionArchivosServicio = ProcesoConversionArchivosServicio.getInstance();
			ProcesoConversionArchivos procesoConversionArchivos = procesoConversionArchivosServicio.iniciarProcesoConversionArchivosTransaccional(procesoUnificacionArchivos.getPrun_prun(), 
																							procesoUnificacionArchivos.getPrun_observ(), 
																							currentDate, 
																							procesoUnificacionArchivos.getArchivosARUN(), 
																							procesoUnificacionArchivos.getPrun_fini(), 
																							procesoUnificacionArchivos.getPrun_ffin(), 
																							usuario, 
																							mensajeErrorOut);
			
			
			
			
			//Si al final del procso no hay errores se hace commt;
			if(procesoConversionArchivos!=null){
				
				
			}else{
				mensajeErrorOut.append("Error Iniciando Proceso de Conversión. No se ha podido iniciar correctamente.");
			}
					
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			
			mensajeErrorOut.append("Error Unificando Archivos. No se ha podido finalizar correctamente.");
			
		} 	
		
		return sinErrores;
		
		
		
	}
	
	private String  getDateString(Date date, String format){
		
		
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat(format);
		
	    return ""+fechaFormat.format(date);
	}
	
	
	private String  getDateString(Date date){
		
		
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat horaFormat = new SimpleDateFormat("hhMMss");
		
	    return ""+fechaFormat.format(date)+"_"+horaFormat.format(date);
	}
	
	
	
	public static void main(String[] args) {
		Date date = new Date();
		
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		//System.out.println(format.format((Date) date).replace(" 00:00:00", "").trim());
		System.out.println(format.format(date));
		
		format = new SimpleDateFormat("hhMMss");
		System.out.println(format.format(date));
	}
	
}
