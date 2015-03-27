package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.unificacion.dto.TransformacionArchivoRecaudo;


public interface TransformacionArchivoRecaudoDao {

	public Long getSiguienteID();
	
	public void crearTransformacion(TransformacionArchivoRecaudo documento);
	
	public TransformacionArchivoRecaudo getTransformacion(Long trar_trar);
	
	public List<TransformacionArchivoRecaudo> getTransformacionsPorPRUN(Long prun_prun);
}
