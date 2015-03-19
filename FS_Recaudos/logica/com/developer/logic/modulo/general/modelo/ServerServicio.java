package com.developer.logic.modulo.general.modelo;

import java.util.Date;

import com.developer.persistence.modulo.general.controllerdb.ServerControllerDB;

public class ServerServicio {
	
	private static ServerServicio instance;
	
	public static ServerServicio getInstance() {
		if (instance == null) {
			instance = new ServerServicio();
		}
		
		return instance;
	}
	
	public Date getSysdate(){
		
		return ServerControllerDB.getInstance().getSysdate();
		
	}

}
