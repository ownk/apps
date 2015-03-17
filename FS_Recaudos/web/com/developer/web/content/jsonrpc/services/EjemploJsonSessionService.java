package com.developer.web.content.jsonrpc.services;

import com.developer.logic.modulo.autenticacion.SessionAppUsuario;
import com.developer.web.content.jsonrpc.IJsonSessionService;

public class EjemploJsonSessionService implements IJsonSessionService{


	@Override
	public void setSessionAppUsuario(SessionAppUsuario sessionAppUsuario) {
		// TODO Auto-generated method stub
		
	}
	
	
	public int ejemplo(Integer parametro){
		
		if(parametro.equals(2)){
			
			return 2;
			
		}else {
			
			return -2;
		
		}
			
			
	}


	

}



