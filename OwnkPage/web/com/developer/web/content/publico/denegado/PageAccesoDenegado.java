package com.developer.web.content.publico.denegado;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PublicPage;

public class PageAccesoDenegado extends PublicPage {

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer executeAction(HttpServletRequest request) {
		
		StringBuffer xmlPage = null;
		xmlPage = new StringBuffer();
		
		
		return xmlPage;
		
	
	}
	
	@Override
	public boolean isAccesoValido(HttpServletRequest arg0) {
		return true;

	}
	
	
	
}
