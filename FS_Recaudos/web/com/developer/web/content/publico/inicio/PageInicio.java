package com.developer.web.content.publico.inicio;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.developer.core.page.PublicPage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.web.general.MensajeErrorWeb;

public class PageInicio extends PublicPage {

	MensajeErrorWeb errorWeb;	
	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer executeAction(HttpServletRequest request) {
		
		StringBuffer xmlPage = null;
		xmlPage = new StringBuffer();
		
		
		//Se cierra la session si existe
		AutenticadorServicio.getInstance().cerrarSession(request);
		
				
		//Se verifica si existen errores
		String error = request.getParameter("error");
		if(!StringUtils.isEmpty(error)){
			//Se establece que la pagina tiene errores
			xmlPage = new StringBuffer();
			
			
			errorWeb = new MensajeErrorWeb();
			errorWeb.setError(MensajeErrorWeb.ERROR_AUTENTICACION);
		
			
			xmlPage.append(ObjectToXML.getInstance().getXML(errorWeb));
			
		}
		
		return xmlPage;
		
	
	}
	
	@Override
	public boolean isAccesoValido(HttpServletRequest arg0) {
		return true;

	}
	
	
	
}
