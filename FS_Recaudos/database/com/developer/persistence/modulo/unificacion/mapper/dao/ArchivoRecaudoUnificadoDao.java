package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;


public interface ArchivoRecaudoUnificadoDao {

	public Long getSiguienteID();
	
	public void crearArchivo(ArchivoRecaudoUnificado documento);
	
	public ArchivoRecaudoUnificado getArchivo(Long arun_arun);
	
	public List<ArchivoRecaudoUnificado> getArchivosPorPRUN(Long prun_prun);
	
	public void crearHistorico(HashMap<String, Object> hashMap);
	
	public void setEstado(ArchivoRecaudoUnificado documento);
	
	public List<ArchivoRecaudoPorUnificar> getArchivosTPARxPRUN(HashMap<String, Object> hashMap);
	
}
