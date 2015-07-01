package com.developer.web.content.privado.inicio;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.rsa.EncryptionRSA;

public class PageLogin extends PrivatePage{
	
	String nextPage = null;
	public StringBuffer executeAction(HttpServletRequest request) {
		
		
		String loginEncrypt = request.getParameter("documento_usuario");
		String passEncrypt= request.getParameter("password");
		
				
		//Se desencriptan tanto el login como el passord
		String loginDecrypt = EncryptionRSA.getInstance().decrypt(loginEncrypt);
		String passDecrypt = EncryptionRSA.getInstance().decrypt(passEncrypt);
		
		
		
		StringBuffer mensajeError = new StringBuffer();
		
		SessionAppUsuario sessionAppUsuario= new AutenticadorServicio().iniciarSessionUsuario(request, loginDecrypt, passDecrypt, mensajeError);
		
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
