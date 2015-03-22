package com.developer.persistence.modulo.unificacion.mapper.dao;

import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;


public interface TipoArchivoRecaudoDao {


	
	public void crearTipoArchivo(TipoArchivoRecaudo tipoArchivo);
	
	public TipoArchivoRecaudo getTipoArchivo(String tpar_tpar);
}
