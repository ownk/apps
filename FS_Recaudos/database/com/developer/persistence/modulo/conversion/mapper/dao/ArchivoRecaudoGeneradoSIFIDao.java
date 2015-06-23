package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.HistoricoArchivoRecaudoGeneradoSIFI;



public interface ArchivoRecaudoGeneradoSIFIDao {

	public void crearArchivo(ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI);
	
	public ArchivoRecaudoGeneradoSIFI getArchivo(Long arge_arge);
	
	public List<ArchivoRecaudoGeneradoSIFI> getArchivosPorPRCO(Long prco_prco);
	
	public void crearHistorico(HashMap<String, Object> hashMap);
	
	public void setEstado(ArchivoRecaudoGeneradoSIFI documento);
	
	public  List<HistoricoArchivoRecaudoGeneradoSIFI> getHistoricoArchivo(Long arge_arge);
		
	public void crearDetalleArchivo(DetalleArchivoRecaudoGeneradoSIFI detalleArchivoRecaudoGeneradoSIFI);
	
	
	
	
}
