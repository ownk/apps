package com.developer.persistence.modulo.compara.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.compara.dto.ComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.DetalleComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.HomologacionTipoRecaudoComparador;



public interface ComparacionArchivoRecaudoDao {

	
	public Long getSiguienteID();
	
	public void crearComparacion(ComparacionArchivoRecaudo ComparacionArchivoRecaudo);
	
	public ComparacionArchivoRecaudo getComparacion(Long cpar_cpar);
	
	public List<ComparacionArchivoRecaudo> getComparacionesPorARUN(Long arun_arun);
	
	public void crearDetalleComparacion(DetalleComparacionArchivoRecaudo detalleComparacionArchivoRecaudo);
	
	public List<DetalleComparacionArchivoRecaudo> getAllDetallesCPAR(Long cpar_cpar);
	
	public List<HomologacionTipoRecaudoComparador> getAllHomologacionesTipoRecaudo();

}
