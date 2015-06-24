package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;



public interface EncargoFiduciarioNoSIFIDao {

	
	public List<EncargoFiduciarioNoSIFI> getAllEncargosNoSIFI();
	
	public EncargoFiduciarioNoSIFI getEncargoNoSIFI(EncargoFiduciarioNoSIFI encargoFiduciarioNoSIFI);
	
	
}
