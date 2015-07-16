package com.developer.persistence.modulo.compara.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.compara.dto.ArchivoInternetBSC;


public interface ArchivoInternetBSCDao {

	public Long getSiguienteID();
	
	public void crearArchivo(ArchivoInternetBSC documento);
	
	public ArchivoInternetBSC getArchivo(Long azpu_azpu);
	
	public List<ArchivoInternetBSC> getArchivosPorARUN(Long arun_arun);
}
