package com.developer.persistence.modulo.general.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.general.mapper.dao.ConfiguracionGeneralDao;

public class ConfiguracionGeneralDB {
	
	private static ConfiguracionGeneralDB instance;
	
	public static ConfiguracionGeneralDB getInstance() {
		if (instance == null) {
			instance = new ConfiguracionGeneralDB();
		}
		
	
		return instance;
	}
	
		
	public List<ParametroConfiguracionGeneral> getConfiguracionGeneral(){
		SqlSession session = DBManager.openSession();

		try {

			ConfiguracionGeneralDao dao= session.getMapper(ConfiguracionGeneralDao.class);
			List<ParametroConfiguracionGeneral> parametros= dao.getConfiguracionGeneral();

						
			return parametros;

		}catch (Exception e) {
			SimpleLogger.error("Error getConfiguracionGeneral", e);
			return null;
			
		} finally {
			session.close();
		}
	}
	
	
	
	
		
	 

}
