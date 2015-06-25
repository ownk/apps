package com.developer.logic.modulo.conversion.modelo;

import java.util.concurrent.Callable;

import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;

public class HiloConversionPorTipoArchivo implements Callable<Boolean>{
	
	
	String rutaArchivosSIFI;
	String nombreArchivo;
	ProcesoUnificacionArchivos procesoUnificacionArchivos;
	ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir; 
	String usua_usua;
	
	
	public HiloConversionPorTipoArchivo(String rutaArchivosSIFI,
										String nombreArchivo,
										ProcesoUnificacionArchivos procesoUnificacionArchivos,
										ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir, 
										String usua_usua) {
		
		this.rutaArchivosSIFI = rutaArchivosSIFI;
		this.nombreArchivo = nombreArchivo;
		this.procesoUnificacionArchivos = procesoUnificacionArchivos;
		this.archivoRecaudoOriginalPorConvertir = archivoRecaudoOriginalPorConvertir;
		this.usua_usua = usua_usua;
		
	}
	
	public Boolean call()  {
		
		
		Boolean sinErrores = true;
		ConvertidorArchivoSIFIPorTipoArchivo convertidorArchivoSIFIPorTipoArchivo = new ConvertidorArchivoSIFIPorTipoArchivo();
		sinErrores = convertidorArchivoSIFIPorTipoArchivo.createARGE(this.rutaArchivosSIFI, this.nombreArchivo, this.procesoUnificacionArchivos, this.archivoRecaudoOriginalPorConvertir, this.usua_usua);
		
		return sinErrores;
	}

	

}
