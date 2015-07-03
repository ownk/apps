package com.developer.web.content.privado.unificacion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.logic.modulo.conversion.modelo.ArchivoRecaudoGeneradoSIFIServicio;
import com.developer.logic.modulo.conversion.modelo.ArchivoRecaudoOriginalPorConvertirServicio;
import com.developer.logic.modulo.conversion.modelo.ProcesoConversionArchivosServicio;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;
import com.developer.web.general.MensajeErrorWeb;

public class PageProcesoUnificacionArchivos extends PrivatePage{

	@Override
	public StringBuffer executeAction(HttpServletRequest request) {
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();
		MensajeErrorWeb errorWeb = new MensajeErrorWeb();
		// Session de aplicacion
		AutenticadorServicio autenticadorServicio = new AutenticadorServicio();
		SessionAppUsuario sessionAppUsuario = autenticadorServicio.getSessionAppUsuario(request);

		
		if (sessionAppUsuario != null) {
			
			String prun_prun = request.getParameter("ProcesoUnificacionArchivos.prun_prun");
			
			if(prun_prun !=null){
				ProcesoUnificacionArchivosServicio servicio = new ProcesoUnificacionArchivosServicio();
				ProcesoUnificacionArchivos procesoUnificacionArchivos = servicio.getProcesoUnificacionArchivos(Long.parseLong(prun_prun));
				
				
				
				if(procesoUnificacionArchivos!=null){
					
					//ProcesoUnificacionArchivos
					xmlPage.append(objectToXML.getXML(procesoUnificacionArchivos));
					
					
					
					//ProcesoConversionArchivos
					ProcesoConversionArchivosServicio procesoConversionArchivosServicio = new ProcesoConversionArchivosServicio();
					ProcesoConversionArchivos procesoConversionArchivos = procesoConversionArchivosServicio.getProcesoConversionArchivos(procesoUnificacionArchivos.getPrun_prun());
					
					if(procesoConversionArchivos!=null && procesoConversionArchivos.getPrco_prco()!=null){
						
						List<ArchivoRecaudoOriginalPorConvertir> archivos = getArchivosProcesoConversion(procesoConversionArchivos);
						xmlPage.append(objectToXML.getXML(archivos));
						
					}
					
							
					
					
				}else{
					errorWeb = new MensajeErrorWeb();
					errorWeb.setError("2");
					errorWeb.setMensajeError("Acceso incorrecto. El numero de proceso especificado no es válido.");
					
				}
			}
			
		}else {
			
			
			errorWeb.setError(MensajeErrorWeb.ERROR_AUTENTICACION);
			errorWeb.setMensajeError("Usuario no se ha autenticado correctamente ");;
			xmlPage.append(objectToXML.getXML(errorWeb));
						

			
		}
		
		return xmlPage;
		
		
		
	}

	@Override
	public boolean isAccesoValido(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new AutenticadorServicio().isAccesoPrivadoValido(request);
	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<ArchivoRecaudoOriginalPorConvertir> getArchivosProcesoConversion(ProcesoConversionArchivos procesoConversionArchivos ){
		
		ArchivoRecaudoOriginalPorConvertirServicio archivoRecaudoOriginalPorConvertirServicio = new ArchivoRecaudoOriginalPorConvertirServicio();
		List<ArchivoRecaudoOriginalPorConvertir> archivosOriginalesSinCompletar = archivoRecaudoOriginalPorConvertirServicio.getArchivosPorPRCO(procesoConversionArchivos.getPrco_prco());
		List<ArchivoRecaudoOriginalPorConvertir> archivosOriginalesCompletados = new ArrayList<ArchivoRecaudoOriginalPorConvertir>();
		if(archivosOriginalesSinCompletar!=null){
			
			//Archivos SIFI 
			ArchivoRecaudoGeneradoSIFIServicio archivoRecaudoGeneradoSIFIServicio = new ArchivoRecaudoGeneradoSIFIServicio();
			List<ArchivoRecaudoGeneradoSIFI> archivosSIFI = archivoRecaudoGeneradoSIFIServicio.getArchivosPorPRCO(procesoConversionArchivos.getPrco_prco());
			
			if(archivosSIFI!=null){
				
				for (ArchivoRecaudoOriginalPorConvertir archivoOriginal : archivosOriginalesSinCompletar) {
					
					for (ArchivoRecaudoGeneradoSIFI archivoSIFI : archivosSIFI) {
						
						if(archivoOriginal.getAror_aror().equals(archivoSIFI.getArge_aror())){
							archivoOriginal.setArchivoRecaudoGeneradoSIFI(archivoSIFI);
							
							
						}
						
					}
					
					archivosOriginalesCompletados.add(archivoOriginal);
					
				}
				
				
			}
			
			
			
			
		}
		
		return archivosOriginalesCompletados;
		
	}
	
}
