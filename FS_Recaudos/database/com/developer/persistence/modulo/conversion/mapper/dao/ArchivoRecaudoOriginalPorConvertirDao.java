package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.HistoricoArchivoRecaudoOriginalPorConvertir;



public interface ArchivoRecaudoOriginalPorConvertirDao {

	public void crearArchivo(ArchivoRecaudoOriginalPorConvertir ArchivoRecaudoOriginalPorConvertir);
	
	public ArchivoRecaudoOriginalPorConvertir getArchivo(Long aror_aror);
	
	public List<ArchivoRecaudoOriginalPorConvertir> getArchivosPorPRCO(Long prco_prco);
	
	public void crearHistorico(HashMap<String, Object> hashMap);
	
	public void setEstado(ArchivoRecaudoOriginalPorConvertir documento);
	
	public  List<HistoricoArchivoRecaudoOriginalPorConvertir> getHistoricoArchivo(Long aror_aror);
		
	public void crearDetalleArchivo(DetalleArchivoRecaudoOriginalPorConvertir detalleArchivoRecaudoOriginalPorConvertir);
	
	public List<DetalleArchivoRecaudoOriginalPorConvertir> getAllDetallesAROR(Long aror_aror);
	
	
	
}
