package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.DistribucionPorFormulaPorcentaje;
import com.developer.logic.modulo.conversion.dto.EstadoPlanFormulaDistribucion;



public interface FormulaDistribucionPorcentajeDao {

	
	public List<EstadoPlanFormulaDistribucion> getAllEstadosAplicaFormula();
	
	public List<DistribucionPorFormulaPorcentaje> getAllDistribucionesPorcentuales();
	

	
}
