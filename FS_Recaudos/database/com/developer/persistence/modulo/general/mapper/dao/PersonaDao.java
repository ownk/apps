package com.developer.persistence.modulo.general.mapper.dao;

import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.general.dto.Persona;

public interface PersonaDao {

	public Persona getPersonaPorUsuario(Usuario usuario);
	
	public Persona getPersona(Long pern_pern);
	
	
}
