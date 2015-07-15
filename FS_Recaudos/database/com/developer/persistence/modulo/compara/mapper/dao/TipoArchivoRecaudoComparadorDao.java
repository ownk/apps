package com.developer.persistence.modulo.compara.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.compara.dto.TipoArchivoRecaudoComparador;

public interface TipoArchivoRecaudoComparadorDao {
	

	public TipoArchivoRecaudoComparador getTipoArchivo(String tpar_tpar);

	public List<TipoArchivoRecaudoComparador> getAllTiposArchivo();
	
	
}
