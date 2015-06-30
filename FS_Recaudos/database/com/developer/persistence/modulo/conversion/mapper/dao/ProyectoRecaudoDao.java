package com.developer.persistence.modulo.conversion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.conversion.dto.ProyectoCancelado;
import com.developer.logic.modulo.conversion.dto.ProyectoConFormulaDistribucion;
import com.developer.logic.modulo.conversion.dto.ProyectoNoSIFIActivo;

public interface ProyectoRecaudoDao {
	


	public List<ProyectoNoSIFIActivo> getAllProyectosNoSIFIActivos();
	public ProyectoNoSIFIActivo getProyectoNoSIFIActivo(Long prns_proy);
	
	public List<ProyectoConFormulaDistribucion> getAllProyectosConFormulaDistribucion();
	public ProyectoConFormulaDistribucion getProyectoConFormulaDistribucion(Long prfd_proy);
	
	public List<ProyectoCancelado> getAllProyectosCancelados();
	public ProyectoCancelado getProyectoCancelado(Long prca_proy);
	

	
	
}
