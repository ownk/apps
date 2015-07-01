package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.EstadoPlanAplicaPlanGenerico;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;

public interface TipoArchivoRecaudoConvertidorDao {
	

public void crearArchivo(ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI);
	
	public TipoArchivoRecaudoConvertidor getTipoArchivo(String tpar_tpar);

	public List<TipoArchivoRecaudoConvertidor> getTiposArchivoPorPRCO(Long prco_prco);
	
	public List<EstadoPlanAplicaPlanGenerico> getEstadosAplicaPlanGenericoPorTPAR(String tpar_tpar);
	
}
