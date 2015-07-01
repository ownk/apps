package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.ProyectoCancelado;
import com.developer.logic.modulo.conversion.dto.ProyectoConFormulaDistribucion;
import com.developer.logic.modulo.conversion.dto.ProyectoNoSIFIActivo;
import com.developer.persistence.modulo.conversion.controllerdb.ProyectoRecaudoControllerDB;

public class ProyectoRecaudoServicio {
	

	ProyectoRecaudoControllerDB controllerDB;
	public ProyectoRecaudoServicio() {
		controllerDB = new ProyectoRecaudoControllerDB();
	}
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
	

	public List<ProyectoNoSIFIActivo> getAllProyectosNoSIFIActivos(){
		ProyectoRecaudoControllerDB controllerDB = this.controllerDB;
		List<ProyectoNoSIFIActivo> list = controllerDB.getAllProyectosNoSIFIActivos();
		
		return list;
	}
	
	public ProyectoNoSIFIActivo getProyectoNoSIFIActivo(Long prns_proy){
		ProyectoRecaudoControllerDB controllerDB = this.controllerDB;
		ProyectoNoSIFIActivo noSIFIActivo = controllerDB.getProyectoNoSIFIActivo(prns_proy);
		
		return noSIFIActivo;
	}
		
	public List<ProyectoConFormulaDistribucion> getAllProyectosConFormulaDistribucion(){
		ProyectoRecaudoControllerDB controllerDB = this.controllerDB;
		List<ProyectoConFormulaDistribucion> list = controllerDB.getAllProyectosConFormulaDistribucion();
		
		return list;
		
	}
	
	public List<ProyectoConFormulaDistribucion> getProyectoConFormulaDistribucion(Long prfd_proy){
		ProyectoRecaudoControllerDB controllerDB = this.controllerDB;
		List<ProyectoConFormulaDistribucion> list = controllerDB.getProyectoConFormulaDistribucion(prfd_proy);
		
		return list;
		
	}
	
	public List<ProyectoCancelado> getAllProyectosCancelados(){
		ProyectoRecaudoControllerDB controllerDB = this.controllerDB;
		List<ProyectoCancelado> list = controllerDB.getAllProyectosCancelados();
		
		return list;
		
		
	}
	
	public ProyectoCancelado getProyectoCancelado(Long prca_proy){
		ProyectoRecaudoControllerDB controllerDB = this.controllerDB;
		ProyectoCancelado proyectoCancelado= controllerDB.getProyectoCancelado(prca_proy);
		
		return proyectoCancelado;
		
		
	}
			
	
	 

}
