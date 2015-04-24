package com.developer.persistence.modulo.unificacion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificarRepetido;


public interface ArchivoRecaudoPorUnificarRepetidoDao {

	public Long getSiguienteID();
	
	public void crearArchivo(ArchivoRecaudoPorUnificarRepetido documento);
	
	public List<ArchivoRecaudoPorUnificar> getArchivosPorARUN(Long arun_arun);
	
	
	
	
	
}
