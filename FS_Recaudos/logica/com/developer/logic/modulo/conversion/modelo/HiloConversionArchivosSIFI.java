package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;

public class HiloConversionArchivosSIFI implements Runnable{
	
	
	String rutaArchivosSIFI;
	String nombreArchivo;
	ProcesoConversionArchivos procesoConversionArchivos;
	String usua_usua;
	
	
	public HiloConversionArchivosSIFI(String rutaArchivosSIFI,
										String nombreArchivo,
										ProcesoConversionArchivos procesoConversionArchivos,
										String usua_usua) {
		
		this.rutaArchivosSIFI = rutaArchivosSIFI;
		this.nombreArchivo = nombreArchivo;
		this.procesoConversionArchivos = procesoConversionArchivos;
		this.usua_usua = usua_usua;
		
	}
	
	public void run()  {
		
		try {
			
		
			List<ArchivoRecaudoOriginalPorConvertir> archivos = new ArchivoRecaudoOriginalPorConvertirServicio().getArchivosPorPRCO(procesoConversionArchivos.getPrco_prco());
			
			
			for (ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir : archivos) {
				Boolean sinErrores = true;
				ConvertidorArchivoSIFIPorTipoArchivo convertidorArchivoSIFIPorTipoArchivo = new ConvertidorArchivoSIFIPorTipoArchivo();
				sinErrores = convertidorArchivoSIFIPorTipoArchivo.createARGE(this.rutaArchivosSIFI, this.nombreArchivo, this.procesoConversionArchivos, archivoRecaudoOriginalPorConvertir, this.usua_usua);
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

	

}
