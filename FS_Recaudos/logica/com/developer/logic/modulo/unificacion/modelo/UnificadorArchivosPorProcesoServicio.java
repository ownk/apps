package com.developer.logic.modulo.unificacion.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;

public class UnificadorArchivosPorProcesoServicio {
	
	
	public void generarArchivosUnificadosPorProces(ProcesoUnificacionArchivos procesoUnificacionArchivos){
		
		TipoArchivoRecaudoServicio tipoArchivoRecaudoServicio = new TipoArchivoRecaudoServicio();
		List<TipoArchivoRecaudo> tiposArchivoRecaudo = tipoArchivoRecaudoServicio.getTipoArchivosRecaudoPorPRUN(procesoUnificacionArchivos.getPrun_prun());
		
		ArchivoRecaudoPorUnificarServicio archivoRecaudoPorUnificarServicio = new ArchivoRecaudoPorUnificarServicio();
		ProcesoUnificacionArchivosServicio procesoUnificacionArchivosServicio = new ProcesoUnificacionArchivosServicio();
		
		
		String rutaArchivosUnificados = procesoUnificacionArchivosServicio.getRutaFinalArchivosUnificados(procesoUnificacionArchivos);
		
		//Se crea la carpeta general de unzip
	    File folder = new File(rutaArchivosUnificados);
		if(!folder.exists()){
	    		folder.mkdir();
		}
		
		
		for (TipoArchivoRecaudo tipoArchivoRecaudo : tiposArchivoRecaudo) {
			List<ArchivoRecaudoPorUnificar> archivosARPU = archivoRecaudoPorUnificarServicio.getArchivosTPARxPRUN(procesoUnificacionArchivos.getPrun_prun(), tipoArchivoRecaudo.getTpar_tpar());
			
			
			if(archivosARPU!=null){
			
				String nombreArchivoUnificado = "arun_"+procesoUnificacionArchivos.getPrun_prun()+"_"+getDateString(procesoUnificacionArchivos.getPrun_fcrea())+"."+tipoArchivoRecaudo.getTpar_tpar().toLowerCase();
				
				
				FileWriter fichero = null;
		        PrintWriter pw = null;
		        try
		        {
		            fichero = new FileWriter(rutaArchivosUnificados+nombreArchivoUnificado);
		            pw = new PrintWriter(fichero);
		 
		            for (ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar : archivosARPU) {
		            	
		            	Long registros = archivoRecaudoPorUnificar.getArpu_registros();
		            	
		            	 for (int i = 0; i < registros; i++){
		 	                pw.println("Linea "+ i+" de arpu "+archivoRecaudoPorUnificar.getArpu_nombre());
		            	 }
					}
		            
		            
		 
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		           try {
			          
			           if ( fichero != null){
			              fichero.close();
			           }
			           
		           } catch (Exception e2) {
		              e2.printStackTrace();
		           }
		        }
			
			}
			
			
		}
		
		
		
		
		
		
		
	}
	
	private String  getDateString(Date date){
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    return ""+year+month+day;
	}
}
