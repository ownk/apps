package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.conversion.dto.ErrorArchivoRecaudo;



public interface ErrorArchivoRecaudoDao {

	public void crearError(ErrorArchivoRecaudo errorArchivoRecaudo);
	
	public List<ErrorArchivoRecaudo> getErroresPorARORxDAROR(HashMap<String, Object> hashMap);
	
	
	
	
	
	
}
