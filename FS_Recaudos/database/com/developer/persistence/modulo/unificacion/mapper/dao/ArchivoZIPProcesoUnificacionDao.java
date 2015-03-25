package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;


public interface ArchivoZIPProcesoUnificacionDao {

	public Long getSiguienteID();
	
	public void crearArchivoZIP(ArchivoZIPProcesoUnificacion documento);
	
	public ArchivoZIPProcesoUnificacion getArchivo(Long azpu_azpu);
	
	public List<ArchivoZIPProcesoUnificacion> getArchivosPorPRUN(Long prun_prun);
}
