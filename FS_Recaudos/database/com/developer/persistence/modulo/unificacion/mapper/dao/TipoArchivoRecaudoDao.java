package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;


public interface TipoArchivoRecaudoDao {


	
	public void crearTipoArchivo(TipoArchivoRecaudo tipoArchivo);
	
	public TipoArchivoRecaudo getTipoArchivo(String tpar_tpar);
	
	public List<TipoArchivoRecaudo> getTiposArchivoPorPRUN(Long prun_prun);
}
