package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.conversion.dto.ValidacionArchivoRecaudo;



public interface ValidacionArchivoRecaudoDao {

	public void crearValidacion(ValidacionArchivoRecaudo validacionArchivoRecaudo);
	
	public List<ValidacionArchivoRecaudo> getValidacionesPorARORxDAROR(HashMap<String, Object> hashMap);
	
	
	
	
	
	
}
