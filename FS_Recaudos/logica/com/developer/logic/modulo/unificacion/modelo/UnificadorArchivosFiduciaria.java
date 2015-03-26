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

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;

public class UnificadorArchivosFiduciaria {
	

	
	public void createARUN(String rutaArchivosUnificados, String nombreArchivoUnificado, List<ArchivoRecaudoPorUnificar> archivosARPU){
		
		
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
	
	

}
