package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.conversion.dto.ValidacionArchivoRecaudo;



public interface ValidacionArchivoRecaudoDao {

	public void crearTransformacion(ValidacionArchivoRecaudo validacionArchivoRecaudo);
	
	public List<ValidacionArchivoRecaudo> getTransformacionesPorARORxDAROR(HashMap<String, Object> hashMap);
	
	
	
	
	
	
}
