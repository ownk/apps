package com.developer.logic.modulo.general;

import java.util.Date;

import com.developer.persistence.modulo.general.ServerControllerDB;

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
