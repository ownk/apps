package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.ParametroGeneralConversion;



public interface ParametroGeneralConversionDao {

	
	public ParametroGeneralConversion getParametroGeneral(String para_para);
	
	public List<ParametroGeneralConversion> getAllParametros();
	
	
	

	
}
