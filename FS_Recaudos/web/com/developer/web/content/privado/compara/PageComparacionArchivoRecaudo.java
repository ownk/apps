package com.developer.web.content.privado.compara;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.compara.dto.ArchivoInternetBSC;
import com.developer.logic.modulo.compara.dto.ComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.DetalleComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.DetalleResumenComparacion;
import com.developer.logic.modulo.compara.dto.DiferenciaResumenComparacion;
import com.developer.logic.modulo.compara.dto.HomologacionTipoRecaudoComparador;
import com.developer.logic.modulo.compara.modelo.ArchivoInternetBSCServicio;
import com.developer.logic.modulo.compara.modelo.ComparacionArchivoRecaudoServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.modelo.ArchivoRecaudoUnificadoServicio;
import com.developer.web.general.MensajeErrorWeb;

public class PageComparacionArchivoRecaudo extends PrivatePage{

	@Override
	public StringBuffer executeAction(HttpServletRequest request) {
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();
		MensajeErrorWeb errorWeb = new MensajeErrorWeb();
		// Session de aplicacion
		AutenticadorServicio autenticadorServicio = new AutenticadorServicio();
		SessionAppUsuario sessionAppUsuario = autenticadorServicio.getSessionAppUsuario(request);

		
		if (sessionAppUsuario != null) {
			
			String cpar_cpar = request.getParameter("cpar_cpar");
			
			if(cpar_cpar !=null){
				
				Long cpar = new Long(cpar_cpar);
				
				ComparacionArchivoRecaudoServicio comparacionServicio = new ComparacionArchivoRecaudoServicio();
				ComparacionArchivoRecaudo comparacionArchivos = comparacionServicio.getComparacion(cpar, true);
				
				List<HomologacionTipoRecaudoComparador> homologaciones = comparacionServicio.getAllHomologacionesTipoRecaudo();
				
				ArchivoInternetBSCServicio archivoInternetBSCServicio = new ArchivoInternetBSCServicio();
				ArchivoInternetBSC archivoInternetBSC = archivoInternetBSCServicio.getArchivo(comparacionArchivos.getCpar_ibsc());
				
				ArchivoRecaudoUnificadoServicio archivoRecaudoUnificadoServicio = new ArchivoRecaudoUnificadoServicio();
				ArchivoRecaudoUnificado archivoRecaudoUnificado = archivoRecaudoUnificadoServicio.getArchivo(comparacionArchivos.getCpar_arun());
				
				if(comparacionArchivos!=null){
					
					xmlPage.append(objectToXML.getXML(comparacionArchivos));
					StringBuffer xmlResumen = construirResumen(comparacionArchivos.getResumen(), comparacionArchivos.getDiferenciasResumen());
					
					if(xmlResumen!=null){
						xmlPage.append(xmlResumen);
					}
					
					
					if(homologaciones!=null && homologaciones.size()>0)
						xmlPage.append(objectToXML.getXML(homologaciones));
					}
				
					if(archivoRecaudoUnificado!=null){
						xmlPage.append(objectToXML.getXML(archivoRecaudoUnificado));
					}
				
					if(archivoInternetBSC!=null){
						xmlPage.append(objectToXML.getXML(archivoInternetBSC));
					}
					
				}else{
					errorWeb = new MensajeErrorWeb();
					errorWeb.setError("2");
					errorWeb.setMensajeError("Acceso incorrecto. El numero de proceso especificado no es válido.");
					
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
	
	private StringBuffer construirResumen(List<DetalleResumenComparacion> detalles, List<DiferenciaResumenComparacion> diferenciasResumen){
		
		StringBuffer xmlResumen = new StringBuffer();
		xmlResumen.append("<ResumenCompara>");
		
		for (DiferenciaResumenComparacion diferencia : diferenciasResumen) {
			
			xmlResumen.append("<Detalle>");
			
			xmlResumen.append("<FuenteRecaudo>");
			xmlResumen.append(diferencia.getDcpar_treca_norm());
			xmlResumen.append("</FuenteRecaudo>");
			
			
			for (DetalleResumenComparacion detalle : detalles) {
				
				if(diferencia.getDcpar_treca_norm().equals(detalle.getDcpar_treca_norm())){
					
					if(detalle.getDcpar_fuente().equals(DetalleComparacionArchivoRecaudo.FUENTE_PLANO)){
						
						xmlResumen.append("<ValorArchivoPlano>");
						xmlResumen.append(detalle.getDcpar_valor_format());
						xmlResumen.append("</ValorArchivoPlano>");
						
						xmlResumen.append("<RegistrosArchivoPlano>");
						xmlResumen.append(detalle.getDcpar_cantidad());
						xmlResumen.append("</RegistrosArchivoPlano>");
						
					}else{
						xmlResumen.append("<ValorArchivoInternet>");
						xmlResumen.append(detalle.getDcpar_valor_format());
						xmlResumen.append("</ValorArchivoInternet>");
						
						xmlResumen.append("<RegistrosArchivoInternet>");
						xmlResumen.append(detalle.getDcpar_cantidad());
						xmlResumen.append("</RegistrosArchivoInternet>");
						
					}
					
					
					
				}
				
				
				
				
				
			}
			
			xmlResumen.append("<ValorDiferencia>");
			xmlResumen.append(diferencia.getDcpar_valor_format());
			xmlResumen.append("</ValorDiferencia>");
			
			xmlResumen.append("<RegistrosDiferencia>");
			xmlResumen.append(diferencia.getDcpar_cantidad());
			xmlResumen.append("</RegistrosDiferencia>");
			
			xmlResumen.append("</Detalle>");
			
		}
		xmlResumen.append("</ResumenCompara>");
		
		
		return xmlResumen;
	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
