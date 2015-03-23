package com.developer.logic.modulo.appender;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;


public class Zipper {

	Map<String, Integer> mapExtensions;
	
	
	public Zipper() {
		
		mapExtensions = new HashMap<String, Integer>();
		
	}
	
	
	public void unZip(){
		
		
		/*
		 * Se hace lectura del archivo ZIP
		 */
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(new FileInputStream("FIDUCI0306.zip"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * Se revisan cada uno de sus archivos
		 */
		ZipEntry entrada;
		
		
		try {
			
			/*
			 * Contenido del archivos zip
			 */
			System.out.println("Contenido de archivos ");
			System.out.println("================================== ");
			while (null != (entrada=zis.getNextEntry()) ){
			   
			   String fileName = entrada.getName();
			   String fileExtension = FilenameUtils.getExtension(fileName);
			   
			   //Contador de archivos por extension
			   if(mapExtensions.containsKey(fileExtension)){
				   
				   Integer total = mapExtensions.get(fileExtension);
				   total++;
				   mapExtensions.replace(fileExtension, total);
				   
			   }else{
				   mapExtensions.put(fileExtension, new Integer(1));
			   }
			   
			   //Impresion de contenido
			   System.out.println(entrada.getName()+" tamanhoZip"+entrada.getCompressedSize()+"tamanhoUnZip"+entrada.getSize());

			   //UnZip Files
			   /*
			   FileOutputStream fos = new FileOutputStream(entrada.getName());
			   int leido;
			   byte [] buffer = new byte[1024];
			   while (0<(leido=zis.read(buffer))){
			      fos.write(buffer,0,leido);
			   }
			   fos.close();
			   zis.closeEntry();
			   */
			  
			}
			
			
			/*
			 * Se muestra el resumen por extension
			 */
			System.out.println("Resumen por extension ");
			System.out.println("================================== ");
			
			for (Map.Entry<String, Integer> entry : mapExtensions.entrySet())
			{
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			}
			
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		Zipper zipper = new Zipper();
		zipper.unZip();
	}
	
}
