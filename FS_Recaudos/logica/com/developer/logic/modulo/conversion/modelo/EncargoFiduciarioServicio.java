package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.persistence.modulo.conversion.controllerdb.EncargoFiduciarioControllerDB;

public class EncargoFiduciarioServicio {
	
	private static EncargoFiduciarioServicio instance;
	
	public static EncargoFiduciarioServicio getInstance() {
		if (instance == null) {
			instance = new EncargoFiduciarioServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI(){
		EncargoFiduciarioControllerDB controllerDB = EncargoFiduciarioControllerDB.getInstance();
		List<EncargoFiduciarioSIFI> list = controllerDB.getAllEncargosSIFI();
		return list;
	}
	
		
	public List<EncargoFiduciarioNoSIFI> getAllEncargosNoSIFI(){
		EncargoFiduciarioControllerDB controllerDB = EncargoFiduciarioControllerDB.getInstance();
		List<EncargoFiduciarioNoSIFI> list = controllerDB.getAllEncargosNoSIFI();
		return list;
		
	}
	

	public Boolean eliminarAllEncargosSIFITransaccional(SqlSession session){
		
		Boolean sinErrores = true;
		EncargoFiduciarioControllerDB controllerDB = EncargoFiduciarioControllerDB.getInstance();
		sinErrores=controllerDB.eliminarAllEncargosSIFITransaccional(session);
		
		return sinErrores;
	}
	
	
	public Boolean crearRegistroEncargoSIFITransaccional(SqlSession session, EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		Boolean sinErrores = true;
		EncargoFiduciarioControllerDB controllerDB = EncargoFiduciarioControllerDB.getInstance();
		sinErrores=controllerDB.crearRegistroEncargoSIFITransaccional(session, encargoFiduciarioSIFI);
		
		return sinErrores;
		
	}
	

	
			
	
	 

}
