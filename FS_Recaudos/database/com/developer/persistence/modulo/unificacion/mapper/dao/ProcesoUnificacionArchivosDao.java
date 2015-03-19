package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.HistoricoProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;

public interface ProcesoUnificacionArchivosDao {

	public void iniciarProcesoUnificacionArchivos(ProcesoUnificacionArchivos procesoUnificacionArchivos);
	
	public void crearHistoricoProcesoUnificacionArchivos(HashMap<String, Object> hashMap);
	
	public void setEstadoProcesoUnificacionArchivos(ProcesoUnificacionArchivos procesoUnificacionArchivos);
	
	public List<HistoricoProcesoUnificacionArchivos> getHistoricoPorProcesoUnificacionArchivos(Long prun_prun);
	
	public ProcesoUnificacionArchivos getProcesoUnificacionArchivos(Long prun_prun);
	
	public List<ProcesoUnificacionArchivos> getProcesoUnificacionArchivossPorEstados(HashMap<String, Object> hashMap);
	

}
