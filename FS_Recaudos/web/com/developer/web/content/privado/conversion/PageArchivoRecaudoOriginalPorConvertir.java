package com.developer.web.content.privado.conversion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleResumenConversionSIFI;
import com.developer.logic.modulo.conversion.dto.ErrorArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.logic.modulo.conversion.dto.TipoRecaudoExcluir;
import com.developer.logic.modulo.conversion.dto.TransformacionArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.ValidacionArchivoRecaudo;
import com.developer.logic.modulo.conversion.modelo.ArchivoRecaudoGeneradoSIFIServicio;
import com.developer.logic.modulo.conversion.modelo.ArchivoRecaudoOriginalPorConvertirServicio;
import com.developer.logic.modulo.conversion.modelo.ErrorArchivoRecaudoServicio;
import com.developer.logic.modulo.conversion.modelo.TipoArchivoRecaudoConvertidorServicio;
import com.developer.logic.modulo.conversion.modelo.TransformacionArchivoRecaudoServicio;
import com.developer.logic.modulo.conversion.modelo.ValidacionArchivoRecaudoServicio;
import com.developer.web.general.MensajeErrorWeb;

public class PageArchivoRecaudoOriginalPorConvertir extends PrivatePage{

	@Override
	public StringBuffer executeAction(HttpServletRequest request) {
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();
		MensajeErrorWeb errorWeb = new MensajeErrorWeb();
		// Session de aplicacion
		AutenticadorServicio autenticadorServicio = new AutenticadorServicio();
		SessionAppUsuario sessionAppUsuario = autenticadorServicio.getSessionAppUsuario(request);

		
		if (sessionAppUsuario != null) {
			
			String aror_aror = request.getParameter("aror_aror");
			
			if(aror_aror !=null){
				
				Long aror = new Long(aror_aror);
				
				ArchivoRecaudoOriginalPorConvertirServicio archivoServicio = new ArchivoRecaudoOriginalPorConvertirServicio();
				ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir = archivoServicio.getArchivoRecaudo(aror);
				
				
				TransformacionArchivoRecaudoServicio transformacionServicio = new TransformacionArchivoRecaudoServicio();
				ErrorArchivoRecaudoServicio errorServicio = new ErrorArchivoRecaudoServicio();
				ValidacionArchivoRecaudoServicio validacionServicio = new ValidacionArchivoRecaudoServicio();
				ArchivoRecaudoGeneradoSIFIServicio archivoSIFIServicio = new ArchivoRecaudoGeneradoSIFIServicio();
				TipoArchivoRecaudoConvertidorServicio tipoArchivoServicio = new TipoArchivoRecaudoConvertidorServicio();
				
				if(archivoRecaudoOriginalPorConvertir!=null){
					
					xmlPage.append(objectToXML.getXML(archivoRecaudoOriginalPorConvertir));
					
					
					
					List<ErrorArchivoRecaudo> errores = errorServicio.getErroresPorAROR(aror);
					List<ValidacionArchivoRecaudo> validaciones = validacionServicio.getValidacionesPorAROR(aror);
					List<TransformacionArchivoRecaudo> transformaciones = transformacionServicio.getTransformacionesPorAROR(aror);
					List<TipoRecaudoExcluir> tiposRecaudoExcluidos = tipoArchivoServicio.getTipoRecaudoExcluirPorTPAR(archivoRecaudoOriginalPorConvertir.getAror_tpar());
					ArchivoRecaudoGeneradoSIFI archivoSIFI = archivoSIFIServicio.getArchivoRecaudo(aror);
					
					if(tiposRecaudoExcluidos!=null && tiposRecaudoExcluidos.size()>0){
						xmlPage.append(objectToXML.getXML(tiposRecaudoExcluidos));
					}
					
					
					if(errores!=null && errores.size()>0){
						xmlPage.append(objectToXML.getXML(errores));
					}
					
					if(validaciones!=null && validaciones.size()>0){
						xmlPage.append(objectToXML.getXML(validaciones));
					}
					
					if(transformaciones!=null && transformaciones.size()>0){
						xmlPage.append(objectToXML.getXML(transformaciones));
					}
					
					if(archivoSIFI!=null){
						xmlPage.append(objectToXML.getXML(archivoSIFI));
						
						List<DetalleResumenConversionSIFI> listResumenSIFI = archivoServicio.getResumenConversionSIFIAROR(aror);
						xmlPage.append(objectToXML.getXML(listResumenSIFI));
						
						Double totalRecaudoOriginal = new Double(0);
						for (DetalleArchivoRecaudoOriginalPorConvertir detalleOriginal : archivoRecaudoOriginalPorConvertir.getDetalles()) {
							totalRecaudoOriginal = totalRecaudoOriginal+detalleOriginal.getDaror_vtot_double();
							
						}
						
						Double totalRecaudoFinal = new Double(0);
						for (DetalleResumenConversionSIFI detalleFinal :listResumenSIFI) {
							totalRecaudoFinal = totalRecaudoFinal+detalleFinal.getDarge_vtot_double();
							
						}
						
						Double diferencia = totalRecaudoOriginal-totalRecaudoFinal;
						
						xmlPage.append("<totalRecaudoOriginal>"+DecimalFormat.getCurrencyInstance().format(totalRecaudoOriginal)+"</totalRecaudoOriginal>");
						xmlPage.append("<totalRecaudoFinal>"+DecimalFormat.getCurrencyInstance().format(totalRecaudoFinal)+"</totalRecaudoFinal>");
						xmlPage.append("<totalRecaudoDiferencia>"+DecimalFormat.getCurrencyInstance().format(diferencia)+"</totalRecaudoDiferencia>");
						
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
