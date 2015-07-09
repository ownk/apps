package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.conversion.dto.OficinaRecaudo;



public interface OficinaRecaudoDao {

	
	public List<OficinaRecaudo> getAllOficinas();

	public OficinaRecaudo getOficinaSIFI(HashMap<String, Object> hashMap);
	
	
	

	
}
