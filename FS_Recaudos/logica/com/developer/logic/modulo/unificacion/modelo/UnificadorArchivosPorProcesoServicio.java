package com.developer.logic.modulo.unificacion.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;

public class UnificadorArchivosPorProcesoServicio {
	
	
	public void generarArchivosUnificadosPorProces(ProcesoUnificacionArchivos procesoUnificacionArchivos){
		
		TipoArchivoRecaudoServicio tipoArchivoRecaudoServicio = new TipoArchivoRecaudoServicio();
		List<TipoArchivoRecaudo> tiposArchivoRecaudo = tipoArchivoRecaudoServicio.getTipoArchivosRecaudoPorPRUN(procesoUnificacionArchivos.getPrun_prun());
		
		ArchivoRecaudoPorUnificarServicio archivoRecaudoPorUnificarServicio = new ArchivoRecaudoPorUnificarServicio();
		ProcesoUnificacionArchivosServicio procesoUnificacionArchivosServicio = new ProcesoUnificacionArchivosServicio();
		
		
		ServerServicio serverServicio = new ServerServicio();
		Date currentDate = serverServicio.getSysdate();
		
		
		String rutaArchivosUnificados = procesoUnificacionArchivosServicio.getRutaFinalArchivosUnificados(procesoUnificacionArchivos);
		
		//Se crea la carpeta general de unzip
	    File folder = new File(rutaArchivosUnificados);
		if(!folder.exists()){
	    		folder.mkdir();
		}
		
		
		for (TipoArchivoRecaudo tipoArchivoRecaudo : tiposArchivoRecaudo) {
			List<ArchivoRecaudoPorUnificar> archivosARPU = archivoRecaudoPorUnificarServicio.getArchivosTPARxPRUN(procesoUnificacionArchivos.getPrun_prun(), tipoArchivoRecaudo.getTpar_tpar());
			
			
			if(archivosARPU!=null){
			
				String nombreArchivoUnificado = "arun_"+procesoUnificacionArchivos.getPrun_prun()+"_"+getDateString(currentDate)+"."+tipoArchivoRecaudo.getTpar_tpar().toLowerCase();
				
				createARUN(rutaArchivosUnificados, nombreArchivoUnificado, archivosARPU);
				
			
			}
			
			
		}
		
		
	}
	
	private String  getDateString(Date date){
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat("ddMMyyyy");
		
		SimpleDateFormat horaFormat = new SimpleDateFormat("hhMMss");
		
	    return ""+fechaFormat.format(date)+"_"+horaFormat.format(date);
	}
	
	
	
	
	private void createARUN(String rutaArchivosUnificados, String nombreArchivoUnificado, List<ArchivoRecaudoPorUnificar> archivosARPU){
		
		
		FileWriter fichero = null;
        PrintWriter printerWriter = null;
        try
        {
            fichero = new FileWriter(rutaArchivosUnificados+nombreArchivoUnificado);
            printerWriter = new PrintWriter(fichero);
            
            Boolean isEncabezadoDone = false;
            
            for (ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar : archivosARPU) {
            	
            	
            	File file = new File(archivoRecaudoPorUnificar.getArpu_url());
            	
            	if(file.exists()){
            		
            		if(archivoRecaudoPorUnificar.getArpu_registros()>0){
            			
            			
            		
		            	//Declarar una variable BufferedReader
		                BufferedReader bufferedReader = null;
		                try {
		                   //Crear un objeto BufferedReader al que se le pasa 
		                   //un objeto FileReader con el nombre del fichero
		                   bufferedReader = new BufferedReader(new FileReader(file));
		                   
		                   String encabezado = bufferedReader.readLine();
		                   
		                   if(!isEncabezadoDone){
		                	  printerWriter.println(encabezado);
		                	  isEncabezadoDone = true;
		                   }
		                	
		                   //Repetir mientras no se llegue al final del fichero
		                   while(bufferedReader.ready())
		                   {
		                       //Hacer lo que sea con la línea leída
		                	   
		                	  String linea = bufferedReader.readLine();
		                	  printerWriter.println(linea);
		                	   
		                   }
		                }
		                catch (FileNotFoundException e) {
		                    System.out.println("Error: Fichero no encontrado");
		                    System.out.println(e.getMessage());
		                   
		                }
		                catch(Exception e) {
		                    System.out.println("Error de lectura del fichero");
		                    System.out.println(e.getMessage());
		                    
		                }
		                finally {
		                    try {
		                        if(bufferedReader != null)
		                        	bufferedReader.close();
		                    }
		                    catch (Exception e) {
		                    	
		                        System.out.println("Error al cerrar el fichero");
		                        System.out.println(e.getMessage());
		                     
		                    }
		                }
            		}
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
	
	
	public static void main(String[] args) {
		Date date = new Date();
		
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		//System.out.println(format.format((Date) date).replace(" 00:00:00", "").trim());
		System.out.println(format.format(date));
		
		format = new SimpleDateFormat("hhMMss");
		System.out.println(format.format(date));
	}
	
}
