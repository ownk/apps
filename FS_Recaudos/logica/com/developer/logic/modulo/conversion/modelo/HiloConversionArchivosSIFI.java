package com.developer.logic.modulo.conversion.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;

public class HiloConversionArchivosSIFI implements Runnable{
	
	
	String rutaArchivosSIFI;
	ProcesoConversionArchivos procesoConversionArchivos;
	Usuario usuario;
	List<TipoArchivoRecaudoConvertidor> listTiposArhivo;
	
	public HiloConversionArchivosSIFI(	String rutaArchivosSIFI,
										ProcesoConversionArchivos procesoConversionArchivos,
										Usuario usuario) {
		
		this.rutaArchivosSIFI = rutaArchivosSIFI;
		this.procesoConversionArchivos = procesoConversionArchivos;
		this.usuario = usuario;
		this.listTiposArhivo = new TipoArchivoRecaudoConvertidorServicio().getAllTiposArchivo();
	}
	
	public void run()  {
		
		try {
			ProcesoConversionArchivosServicio procesoConversionArchivosServicio = new ProcesoConversionArchivosServicio();
		
			List<ArchivoRecaudoOriginalPorConvertir> archivos = new ArchivoRecaudoOriginalPorConvertirServicio().getArchivosPorPRCO(procesoConversionArchivos.getPrco_prco());
			
			System.out.println("iniciando proceso de conversion "+procesoConversionArchivos.getPrco_prco()+" total archivos: "+archivos.size());
			StringBuffer mensajeErrorOut = new StringBuffer();
			procesoConversionArchivosServicio.setEstado(procesoConversionArchivos.getPrco_prco(), ProcesoConversionArchivos.EN_PROCESO, usuario, "Inicia a procesar cantidad de archivos: "+archivos.size(), mensajeErrorOut);
			
			for (ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir : archivos) {
				
				String nombreArchivo = null;
				
				nombreArchivo= getPrefijoNombreTPAR(archivoRecaudoOriginalPorConvertir.getAror_tpar())+"_"+getDateString(procesoConversionArchivos.getPrco_ffin(), "ddMMyyyy");
				
				Boolean sinErrores = true;
				ConvertidorArchivoSIFIPorTipoArchivo convertidorArchivoSIFIPorTipoArchivo = new ConvertidorArchivoSIFIPorTipoArchivo();
				sinErrores = convertidorArchivoSIFIPorTipoArchivo.createARGE(this.rutaArchivosSIFI, nombreArchivo, this.procesoConversionArchivos, archivoRecaudoOriginalPorConvertir, this.usuario);
				System.out.println("archivo procesado "+procesoConversionArchivos.getPrco_prco()+" archivo "+archivoRecaudoOriginalPorConvertir.getAror_aror());
			}
			
			procesoConversionArchivosServicio.setEstado(procesoConversionArchivos.getPrco_prco(), ProcesoConversionArchivos.FINALIZADO, usuario, "finalizado. revisar en detalle cada uno de los archivos", mensajeErrorOut);
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
