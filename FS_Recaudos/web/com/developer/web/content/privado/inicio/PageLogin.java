package com.developer.web.content.privado.inicio;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.logic.modulo.autenticacion.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.SessionAppUsuario;

public class PageLogin extends PrivatePage{
	
	String nextPage = null;
	public StringBuffer executeAction(HttpServletRequest request) {
		String login = request.getParameter("login");
		String pass= request.getParameter("pass");
		
		StringBuffer mensajeError = new StringBuffer();
		
		SessionAppUsuario sessionAppUsuario= AutenticadorServicio.getInstance().iniciarSessionUsuario(request, login, pass, mensajeError);
		
		if(sessionAppUsuario!=null){
			this.nextPage = "inicio/PageBienvenida.do";
		
		}else{
			
			this.nextPage = "inicio/PageInicio.pub?error=1";
			
		}
		return null;
		
	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return nextPage;
	}

	@Override
	public boolean isAccesoValido(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	
	

}
