package com.developer.persistence.modulo.autenticacion.mapper.dao;

import java.util.List;

import com.developer.logic.modulo.autenticacion.dto.Servicio;

public interface ServicioDao {

	
	public List<String> getURLSPorServicio(Servicio servicio);
	

}
