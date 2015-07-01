package com.developer.persistence.modulo.general.controllerdb;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.general.dto.Persona;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.general.mapper.dao.PersonaDao;

public class PersonaControllerDB {
	
	
	//TODO deberia recibir el string del usua_usua
	public Persona getPersonaPorUsuario(Usuario usuario){
		SqlSession session = DBManagerFSRecaudos.openSession();

		try {

			PersonaDao personaMapper = session.getMapper(PersonaDao.class);
			Persona persona = personaMapper.getPersonaPorUsuario(usuario);

			return persona;	
		}catch (Exception e) {
			SimpleLogger.error("Error consultando Persona por usuario", e);
			
			return null;
			
		} finally {
			session.close();
		}
		
		
		
	} 
	
		
	 

}
