package com.developer.logic.modulo.unificacion.modelo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;

public class UnificadorArchivosPorProcesoServicio {
	
	UnificadorArchivosDefault unificadorArchivosDefault;
	UnificadorArchivosFiduciaria unificadorArchivosFiduciaria;
	
	public UnificadorArchivosPorProcesoServicio() {
		unificadorArchivosDefault = new UnificadorArchivosDefault();
		unificadorArchivosFiduciaria = new UnificadorArchivosFiduciaria();
		
	}
	
	
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
				
				if( (tipoArchivoRecaudo.getTpar_estr().equals(TipoArchivoRecaudo.ESTR_ASOBANCARIA)) || ( 
						tipoArchivoRecaudo.getTpar_estr().equals(TipoArchivoRecaudo.ESTR_FIDUCIARIA))	){
					
					
					unificadorArchivosFiduciaria.createARUN(rutaArchivosUnificados, nombreArchivoUnificado, archivosARPU);
				}else{
					unificadorArchivosDefault.createARUN(rutaArchivosUnificados, nombreArchivoUnificado, archivosARPU);
				}
				
			
			}
			
			
		}
		
		
	}
	
	private String  getDateString(Date date){
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat("ddMMyyyy");
		
		SimpleDateFormat horaFormat = new SimpleDateFormat("hhMMss");
		
	    return ""+fechaFormat.format(date)+"_"+horaFormat.format(date);
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
