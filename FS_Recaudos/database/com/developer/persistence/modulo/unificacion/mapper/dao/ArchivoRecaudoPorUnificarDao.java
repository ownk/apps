package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;


public interface ArchivoRecaudoPorUnificarDao {

	public Long getSiguienteID();
	
	public void crearArchivo(ArchivoRecaudoPorUnificar documento);
	
	public ArchivoRecaudoPorUnificar getDocumento(Long arpu_arpu);
	
	public List<ArchivoRecaudoPorUnificar> getDocumentosPorProcesoUnificacion(Long prun_prun);
	
	public void crearHistorico(HashMap<String, Object> hashMap);
	
	public void setEstado(ArchivoRecaudoPorUnificar documento);
}
