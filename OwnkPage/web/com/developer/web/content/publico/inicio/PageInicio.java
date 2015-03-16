package com.developer.web.content.publico.inicio;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.developer.core.page.PublicPage;

public class PageInicio extends PublicPage {

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer executeAction(HttpServletRequest request) {
		
		StringBuffer xmlPage = null;
		
		//Se verifica si existen errores
		String error = request.getParameter("error");
		if(!StringUtils.isEmpty(error)){
			//Se establece que la pagina tiene errores
			xmlPage = new StringBuffer();
			xmlPage.append("<error/>");
			
		}
		
		return xmlPage;
		
	
	}
	
	@Override
	public boolean isAccesoValido(HttpServletRequest arg0) {
		return true;

	}
	
	
	
}
