package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;


public interface ArchivoRecaudoUnificadoDao {

	public Long getSiguienteID();
	
	public void crearArchivo(ArchivoRecaudoUnificado documento);
	
	public ArchivoRecaudoUnificado getDocumento(Long arun_arun);
	
	public List<ArchivoRecaudoUnificado> getDocumentosPorProcesoUnificacion(Long prun_prun);
	
	public void crearHistorico(HashMap<String, Object> hashMap);
	
	public void setEstado(ArchivoRecaudoUnificado documento);
}
