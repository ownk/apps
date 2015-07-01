package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.DistribucionPorFormulaPorcentaje;
import com.developer.logic.modulo.conversion.dto.EstadoPlanFormulaDistribucion;
import com.developer.persistence.modulo.conversion.controllerdb.FormulaDistribucionPorcentajeControllerDB;

public class FormulaDistribucionPorcentajeServicio {
	
	private static FormulaDistribucionPorcentajeServicio instance;
	
	public static FormulaDistribucionPorcentajeServicio getInstance() {
		if (instance == null) {
			instance = new FormulaDistribucionPorcentajeServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
	public List<EstadoPlanFormulaDistribucion> getAllEstadosAplicaFormula(){
		FormulaDistribucionPorcentajeControllerDB controllerDB = FormulaDistribucionPorcentajeControllerDB.getInstance();
		List<EstadoPlanFormulaDistribucion> list = controllerDB.getAllEstadosAplicaFormula();
		
		return list;
	}
	
		
	public List<DistribucionPorFormulaPorcentaje> getAllDistribucionesPorcentuales(){
		FormulaDistribucionPorcentajeControllerDB controllerDB = FormulaDistribucionPorcentajeControllerDB.getInstance();
		List<DistribucionPorFormulaPorcentaje> list = controllerDB.getAllDistribucionesPorcentuales();
		
		return list;
		
	}
	
	public List<DistribucionPorFormulaPorcentaje> getDistribucionesPorcentualPorFormula(Long frdp_frdp){
		FormulaDistribucionPorcentajeControllerDB controllerDB = FormulaDistribucionPorcentajeControllerDB.getInstance();
		List<DistribucionPorFormulaPorcentaje> list = controllerDB.getAllDistribucionesPorcentuales();
		
		return list;
		
	}

	
			
	
	 

}
