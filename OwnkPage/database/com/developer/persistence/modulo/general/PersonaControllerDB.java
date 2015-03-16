package com.developer.persistence.modulo.general;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.general.dto.Persona;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.general.mapper.dao.PersonaDao;

public class PersonaControllerDB {
	
	private static PersonaControllerDB instance;
	
	public static PersonaControllerDB getInstance() {
		if (instance == null) {
			instance = new PersonaControllerDB();
		}
		
	
		return instance;
	}
	
	//TODO deberia recibir el string del usua_usua
	public Persona getPersonaPorUsuario(Usuario usuario){
		SqlSession session = DBManager.openSession();

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
