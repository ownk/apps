package com.developer.logic.modulo.conversion.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;

public class HiloConversionArchivosSIFI implements Runnable{
	
	
	String rutaArchivosSIFI;
	ProcesoConversionArchivos procesoConversionArchivos;
	String usua_usua;
	List<TipoArchivoRecaudoConvertidor> listTiposArhivo;
	
	public HiloConversionArchivosSIFI(	String rutaArchivosSIFI,
										ProcesoConversionArchivos procesoConversionArchivos,
										String usua_usua) {
		
		this.rutaArchivosSIFI = rutaArchivosSIFI;
		this.procesoConversionArchivos = procesoConversionArchivos;
		this.usua_usua = usua_usua;
		this.listTiposArhivo = new TipoArchivoRecaudoConvertidorServicio().getAllTiposArchivo();
	}
	
	public void run()  {
		
		try {
			
		
			List<ArchivoRecaudoOriginalPorConvertir> archivos = new ArchivoRecaudoOriginalPorConvertirServicio().getArchivosPorPRCO(procesoConversionArchivos.getPrco_prco());
			
			System.out.println("iniciando proceso de conversion "+procesoConversionArchivos.getPrco_prco()+" total archivos:"+archivos.size());
			
			for (ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir : archivos) {
				
				
				
				String nombreArchivo = null;
				
				nombreArchivo= getPrefijoNombreTPAR(archivoRecaudoOriginalPorConvertir.getAror_tpar())+""+getDateString(procesoConversionArchivos.getPrco_ffin(), "yyyy")+"."+archivoRecaudoOriginalPorConvertir.getAror_tpar().toLowerCase();
				
				Boolean sinErrores = true;
				ConvertidorArchivoSIFIPorTipoArchivo convertidorArchivoSIFIPorTipoArchivo = new ConvertidorArchivoSIFIPorTipoArchivo();
				sinErrores = convertidorArchivoSIFIPorTipoArchivo.createARGE(this.rutaArchivosSIFI, nombreArchivo, this.procesoConversionArchivos, archivoRecaudoOriginalPorConvertir, this.usua_usua);
				System.out.println("archivo procesado "+procesoConversionArchivos.getPrco_prco()+" archivo "+archivoRecaudoOriginalPorConvertir.getAror_aror());
			}
			
			System.out.println("finalizando proceso de conversion "+procesoConversionArchivos.getPrco_prco());
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}
	
	private String  getDateString(Date date, String format){
		
		
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat(format);
		
	    return ""+fechaFormat.format(date);
	}
	
	
	private String  getPrefijoNombreTPAR(String tpar_tpar){
		
		String prefijo = "arge_";
		
		for (TipoArchivoRecaudoConvertidor tipoArchivoRecaudoConvertidor : listTiposArhivo) {
			if(tipoArchivoRecaudoConvertidor.getTpar_tpar().equals(tpar_tpar)){
				
				prefijo = tipoArchivoRecaudoConvertidor.getTpar_nomb_arge();
				break;
				
			}
			
		}
		
		return prefijo;
		
	}

	

}
