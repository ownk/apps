package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.conversion.dto.HistoricoProcesoConversionArchivos;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;

public interface ProcesoConversionArchivosDao {
	

	public void iniciarProcesoConversionArchivos(ProcesoConversionArchivos procesoConversionArchivos);
	
	public void crearHistoricoProceso(HashMap<String, Object> hashMap);
	
	public void setEstadoProceso(ProcesoConversionArchivos procesoConversionArchivos);
	
	public List<HistoricoProcesoConversionArchivos> getHistoricoPorProceso(Long prco_prco);
	
	public ProcesoConversionArchivos getProcesoPorPRUN(Long prun_prun);
	
	public ProcesoConversionArchivos getProceso(Long prco_prco);
	
	
	
}
