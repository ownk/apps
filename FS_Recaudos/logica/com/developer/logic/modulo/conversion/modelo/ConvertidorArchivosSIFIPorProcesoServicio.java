package com.developer.logic.modulo.conversion.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;

public class ConvertidorArchivosSIFIPorProcesoServicio {
	
	
	public ConvertidorArchivosSIFIPorProcesoServicio() {
		
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
			
			
			
			
			/**
			 * Si el proceso se ha creado con exito se debe proceder 
			 * a generar los archivos por cada tipo de archivo
			 */
			if(procesoConversionArchivos!=null){
				
				ArchivoRecaudoOriginalPorConvertirServicio archivoRecaudoOriginalPorConvertirServicio = ArchivoRecaudoOriginalPorConvertirServicio.getInstance();
				List<ArchivoRecaudoOriginalPorConvertir> list = archivoRecaudoOriginalPorConvertirServicio.getArchivosPorPRCO(procesoConversionArchivos.getPrco_prco());
				
				
				//Se crean un pool de hilos para la creacion de archivos SIFI
				int totalHilos = list.size();
				ExecutorService executor = (ExecutorService) Executors.newFixedThreadPool(totalHilos);
			    List<HiloConversionPorTipoArchivo> listaHilos = new ArrayList<HiloConversionPorTipoArchivo>();
			    
			    for (ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir : list) {
					
			    	//HiloConversionPorTipoArchivo hiloConversion = new HiloConversionPorTipoArchivo(rutaArchivosSIFI, nombreArchivo, procesoUnificacionArchivos, archivoRecaudoOriginalPorConvertir, usua_usua);
			    	HiloConversionPorTipoArchivo hiloConversion = new HiloConversionPorTipoArchivo("", "", procesoUnificacionArchivos, archivoRecaudoOriginalPorConvertir, usuario.getUsua_usua());
			    	listaHilos.add(hiloConversion);
				}
			    
			    List<Future<Boolean>> listaResultados = null; 
			    try {
			        listaResultados = executor.invokeAll(listaHilos);      
			    
			    } catch (Exception e) {
			    		e.printStackTrace();
			    }    
			    
			    executor.shutdown();
			    
			    
			    //Se evaluan las respuestas de hilos 
			    for (int i = 0; i < listaResultados.size(); i++) {
			       Future<Boolean> resultado = listaResultados.get(i);
			       try {      
			         System.out.println("El resultado de la tarea "+i+ " es:" + resultado.get());
			       } catch (Exception e) {
			         e.printStackTrace();
			       }
			    }
				
				
				
				
				
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
