package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.ProyectoCancelado;
import com.developer.logic.modulo.conversion.dto.ProyectoConFormulaDistribucion;
import com.developer.logic.modulo.conversion.dto.ProyectoNoSIFIActivo;
import com.developer.persistence.modulo.conversion.controllerdb.ProyectoRecaudoControllerDB;

public class ProyectoRecaudoServicio {
	
	private static ProyectoRecaudoServicio instance;
	
	public static ProyectoRecaudoServicio getInstance() {
		if (instance == null) {
			instance = new ProyectoRecaudoServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
	

	public List<ProyectoNoSIFIActivo> getAllProyectosNoSIFIActivos(){
		ProyectoRecaudoControllerDB controllerDB = ProyectoRecaudoControllerDB.getInstance();
		List<ProyectoNoSIFIActivo> list = controllerDB.getAllProyectosNoSIFIActivos();
		
		return list;
	}
	
		
	public List<ProyectoConFormulaDistribucion> getAllProyectosConFormulaDistribucion(){
		ProyectoRecaudoControllerDB controllerDB = ProyectoRecaudoControllerDB.getInstance();
		List<ProyectoConFormulaDistribucion> list = controllerDB.getAllProyectosConFormulaDistribucion();
		
		return list;
		
	}
	
	public List<ProyectoCancelado> getAllProyectosCancelados(){
		ProyectoRecaudoControllerDB controllerDB = ProyectoRecaudoControllerDB.getInstance();
		List<ProyectoCancelado> list = controllerDB.getAllProyectosCancelados();
		
		return list;
		
		
	}
			
	
	 

}
