package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.dto.HistoricoArchivoRecaudoPorUnificar;


public interface ArchivoRecaudoPorUnificarDao {

	public Long getSiguienteID();
	
	public void crearArchivo(ArchivoRecaudoPorUnificar documento);
	
	public ArchivoRecaudoPorUnificar getArchivo(Long arpu_arpu);
	
	public List<ArchivoRecaudoPorUnificar> getArchivosPorPRUN(Long prun_prun);
	
	public List<ArchivoRecaudoPorUnificar> getArchivosPorAZPU(Long azpu_azpu);
	
	public void crearHistorico(HashMap<String, Object> hashMap);
	
	public void setEstado(ArchivoRecaudoPorUnificar documento);
	
	public List<HistoricoArchivoRecaudoPorUnificar> getHistoricoArchivo(Long arpu_arpu);
	
	public List<ArchivoRecaudoPorUnificar> getArchivosTPARxPRUN(HashMap<String, Object> hashMap);
	
	
	
	
}
