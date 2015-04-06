package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.HistoricoProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;

public interface ProcesoUnificacionArchivosDao {
	
	public Long getSiguienteID();

	public void iniciarProcesoUnificacionArchivos(ProcesoUnificacionArchivos procesoUnificacionArchivos);
	
	public void crearHistoricoProceso(HashMap<String, Object> hashMap);
	
	public void setEstadoProceso(ProcesoUnificacionArchivos procesoUnificacionArchivos);
	
	public List<HistoricoProcesoUnificacionArchivos> getHistoricoPorProceso(Long prun_prun);
	
	public ProcesoUnificacionArchivos getProceso(Long prun_prun);
	
	public List<ProcesoUnificacionArchivos> getProcesoPorEstados(HashMap<String, Object> hashMap);
	
	public List<ProcesoUnificacionArchivos> getProcesos(HashMap<String, Object> hashMap);
	
	public List<ProcesoUnificacionArchivos> getProcesosPorEstadoFechaIniFin (HashMap<String, Object> hashMap);
	
	public Long getTotalProcesos(HashMap<String, Object> hashMap);
}
