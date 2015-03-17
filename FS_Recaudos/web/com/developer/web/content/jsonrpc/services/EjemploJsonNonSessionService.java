package com.developer.web.content.jsonrpc.services;


import com.developer.core.utils.SimpleLogger;


public class EjemploJsonNonSessionService {

	public int ejemplo(Integer parametro){
		
		try {
			//Mail.enviar("jerezjcv@gmail.com", "Amigo","Asunto", "plano: Prueba de Mensajeria");
			
		 
		} catch (Exception ex) {
		      SimpleLogger.error("Error enviando mail", ex);
		}
		
		
		if(parametro.equals(1)){
			
			return 1;
			
		}else {
			
			return -1;
		
		}
			
			
	}

	

}



