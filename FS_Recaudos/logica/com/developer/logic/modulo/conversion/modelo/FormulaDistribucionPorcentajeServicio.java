package com.developer.logic.modulo.conversion.modelo;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.DistribucionPorFormulaPorcentaje;
import com.developer.logic.modulo.conversion.dto.EstadoPlanFormulaDistribucion;
import com.developer.persistence.modulo.conversion.controllerdb.FormulaDistribucionPorcentajeControllerDB;

public class FormulaDistribucionPorcentajeServicio {
	
	
	FormulaDistribucionPorcentajeControllerDB controllerDB;
	
	public FormulaDistribucionPorcentajeServicio() {
		controllerDB = new FormulaDistribucionPorcentajeControllerDB();
	}
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	
	public List<EstadoPlanFormulaDistribucion> getAllEstadosAplicaFormula(){
		FormulaDistribucionPorcentajeControllerDB controllerDB = this.controllerDB;
		List<EstadoPlanFormulaDistribucion> list = controllerDB.getAllEstadosAplicaFormula();
		
		return list;
	}
	
		
	public List<DistribucionPorFormulaPorcentaje> getAllDistribucionesPorcentuales(){
		FormulaDistribucionPorcentajeControllerDB controllerDB = this.controllerDB;
		List<DistribucionPorFormulaPorcentaje> list = controllerDB.getAllDistribucionesPorcentuales();
		
		return list;
		
	}
	
	public List<DistribucionPorFormulaPorcentaje> getDistribucionesPorcentualPorFormula(Long frdp_frdp){
		FormulaDistribucionPorcentajeControllerDB controllerDB = this.controllerDB;
		List<DistribucionPorFormulaPorcentaje> list = controllerDB.getAllDistribucionesPorcentuales();
		
		return list;
		
	}

	
			
	
	 

}
