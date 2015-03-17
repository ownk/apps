package com.developer.web.content.privado.inicio;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.logic.modulo.autenticacion.AutenticadorServicio;

public class PageBienvenida extends PrivatePage{
	
	

	public StringBuffer executeAction(HttpServletRequest request) {
		return null;
		
	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccesoValido(HttpServletRequest arg0) {
		return AutenticadorServicio.getInstance().isAccesoPrivadoValido(arg0);
	}
	

}
