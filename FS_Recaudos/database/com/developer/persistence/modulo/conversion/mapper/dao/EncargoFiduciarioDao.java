package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;



public interface EncargoFiduciarioDao {

	
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI();
	
	public List<EncargoFiduciarioNoSIFI> getAllEncargosNoSIFI();
	
	public void eliminarAllEncargosSIFI();
	
	public void crearRegistroEncargoSIFI(EncargoFiduciarioSIFI encargoFiduciarioSIFI);
	
}
