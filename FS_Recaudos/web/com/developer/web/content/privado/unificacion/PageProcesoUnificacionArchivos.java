package com.developer.web.content.privado.unificacion;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;
import com.developer.web.general.MensajeErrorWeb;

public class PageProcesoUnificacionArchivos extends PrivatePage{

	@Override
	public StringBuffer executeAction(HttpServletRequest request) {
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = ObjectToXML.getInstance();
		MensajeErrorWeb errorWeb = new MensajeErrorWeb();
		// Session de aplicacion
		SessionAppUsuario sessionAppUsuario = AutenticadorServicio
				.getInstance().getSessionAppUsuario(request);

		
		if (sessionAppUsuario != null) {
			
			String prun_prun = request.getParameter("ProcesoUnificacionArchivos.prun_prun");
			
			if(prun_prun !=null){
				ProcesoUnificacionArchivosServicio servicio = new ProcesoUnificacionArchivosServicio();
				ProcesoUnificacionArchivos procesoUnificacionArchivos = servicio.getProcesoUnificacionArchivos(Long.parseLong(prun_prun));
				
				if(procesoUnificacionArchivos!=null){
					
					//ProcesoUnificacionArchivos
					xmlPage.append(objectToXML.getXML(procesoUnificacionArchivos));
					
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
		return AutenticadorServicio.getInstance().isAccesoPrivadoValido(request);
	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
