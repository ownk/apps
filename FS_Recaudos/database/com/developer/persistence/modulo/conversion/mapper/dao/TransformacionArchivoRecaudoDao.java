package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.unificacion.dto.TransformacionArchivoRecaudo;



public interface TransformacionArchivoRecaudoDao {

	public void crearTransformacion(TransformacionArchivoRecaudo transformacionArchivoRecaudo);
	
	public List<TransformacionArchivoRecaudo> getTransformacionesPorARORxDAROR(HashMap<String, Object> hashMap);
	
	
	
	
	
	
}