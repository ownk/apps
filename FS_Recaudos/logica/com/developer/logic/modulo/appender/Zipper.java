package com.developer.logic.modulo.appender;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FilenameUtils;


public class Zipper {

	private Map<String, Integer> mapExtensions;
	private ArrayList<File> filesUnZip;
	
	public Zipper() {
		
		mapExtensions = new HashMap<String, Integer>();
		filesUnZip = new ArrayList<File>();
		
	}
	
	
	
	public Map<String, Integer> getMapExtensions() {
		return mapExtensions;
	}



	public ArrayList<File> getFilesUnZip() {
		return filesUnZip;
	}

	
	
	public Boolean unZip(File fileZIP, String rutaUnZIP) 
	{
		
		filesUnZip = new ArrayList<File>();
		mapExtensions = new HashMap<String, Integer>();
		
	    System.out.println(fileZIP.getAbsolutePath());
	    int BUFFER = 2048;
	 

	    ZipFile zip;
		try {
			zip = new ZipFile(fileZIP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	    
	    //Se crea la carpeta general de unzip
	    File folder = new File(rutaUnZIP);
		if(!folder.exists()){
	    		folder.mkdir();
		}
		
		//Se crea una carpera especifica para el archivo zip
	    String newPath = rutaUnZIP+File.separator+FilenameUtils.getBaseName(fileZIP.getName());

	    Enumeration zipFileEntries = zip.entries();

	    // Se procesa cada entrada
	    while (zipFileEntries.hasMoreElements())
	    {
	        
	        ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
	        String currentEntry = entry.getName();
	        File fileUnZIP = new File(newPath, currentEntry);
	        File destinationParent = fileUnZIP.getParentFile();

	        //Se crea la estructura de carpetas si se requiere
	        destinationParent.mkdirs();

	        //Si el arhivo no es un directorio se procede a realizar el proceso de unzip
	        if (!entry.isDirectory())
	        {
	            BufferedInputStream is;
				try {
					is = new BufferedInputStream(zip
					.getInputStream(entry));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
	            int currentByte;
	            // establish buffer for writing fileZIP
	            byte data[] = new byte[BUFFER];

	            // write the current file to disk
	            FileOutputStream fos;
				try {
					fos = new FileOutputStream(fileUnZIP);
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
					return false;
				}
	            BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

	            // read and write until last byte is encountered
	            try {
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
					    dest.write(data, 0, currentByte);
					}
					
					dest.flush();
					dest.close();
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
	           
	            this.filesUnZip.add(fileUnZIP);
	            
	            
	            String fileName = fileUnZIP.getName();
				String fileExtension = FilenameUtils.getExtension(fileName).toUpperCase();
				   
				//Contador de archivos por extension
				if(mapExtensions.containsKey(fileExtension)){
					   
					  Integer total = mapExtensions.get(fileExtension);
					  total++;
					  mapExtensions.replace(fileExtension, total);
					   
				}else{
					  mapExtensions.put(fileExtension, new Integer(1));
				}
				   
	            
	            
	        }

	        
	    }
	    
	    return true;
	}
	
	
	
	
	

	
	
	public static void main(String[] args) {
	
			
			Zipper zipper = new Zipper();
			zipper.unZip(new File("jc.zip"), "D:/");
			
			
			/*
			 * Se muestra el resumen por extension
			 */
			System.out.println("Resumen total de archivos");
			System.out.println("================================== ");
			
			System.out.println(zipper.getFilesUnZip().size());
			
			
			
			
			/*
			 * Se muestra el resumen por extension
			 */
			System.out.println("Resumen por extension ");
			System.out.println("================================== ");
			
			for (Map.Entry<String, Integer> entry : zipper.getMapExtensions().entrySet())
			{
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			}
		
		
	}
	
	
	
}
