package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;



public interface SIFI29Dao {

	
	public Long getTotalEncargosSIFI();
	
	public List<EncargoFiduciarioSIFI> getEncargoSIFI(EncargoFiduciarioSIFI encargoFiduciarioSIFI);
}
