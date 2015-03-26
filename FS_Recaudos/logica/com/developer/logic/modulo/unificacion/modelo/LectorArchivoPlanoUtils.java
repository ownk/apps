package com.developer.logic.modulo.unificacion.modelo;



import java.math.BigDecimal;
import java.util.List;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.utils.FileOsmoUtils;
import com.developer.logic.modulo.utils.StringOsmoUtils;


public class LectorArchivoPlanoUtils {
	
	
	public static String[][] leerArchivo(String archivo,Integer longitudes[]){

		try {
			
			List<String> lineas = FileOsmoUtils.getContentFileList(archivo);
			
			if(lineas!=null){
			
				String [][] textos = new String[lineas.size()][longitudes.length];
				
				int j = 0;
				for (String linea : lineas) {
					
					int posicion = 0;
					
					for (int i = 0; i < longitudes.length; i++) {
						
						if (posicion + longitudes[i] <= linea.length()) {
							textos[j][i] = linea.substring(posicion, posicion + longitudes[i]);
						}
						
						posicion = posicion + longitudes[i];
					}
					j++;
				}
				return textos;
			
			}else{
				
				return null;
				
			}
		} catch (Exception e) {
			SimpleLogger.error("Ha ocurrido un error", e);
		}
		
		return null;
	}
	
	public static BigDecimal obtenerNumero2Decimales(String str){
		if(StringOsmoUtils.esVacio(str) || str.length() < 2 ){
			return null;
		}
		str = str.substring(0, str.length() - 2) + "." + str.substring(str.length() - 2);
		return new BigDecimal(str);
	}
	
}
