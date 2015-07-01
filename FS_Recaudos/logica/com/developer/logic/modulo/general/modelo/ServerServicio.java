package com.developer.logic.modulo.general.modelo;

import java.util.Date;

import com.developer.persistence.modulo.general.controllerdb.ServerControllerDB;

public class ServerServicio {
	
	
	
	public Date getSysdate(){
		
		return new ServerControllerDB().getSysdate();
		
	}

}
