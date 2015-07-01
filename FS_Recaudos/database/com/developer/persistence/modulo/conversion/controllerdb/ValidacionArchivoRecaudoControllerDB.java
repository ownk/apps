package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ValidacionArchivoRecaudo;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.ValidacionArchivoRecaudoDao;

public class ValidacionArchivoRecaudoControllerDB {
	
	
	
	
	public Boolean crearValidacionTransaccional(SqlSession session, ValidacionArchivoRecaudo validacionArchivoRecaudo){
		
		try{
					
			ValidacionArchivoRecaudoDao dao = session.getMapper(ValidacionArchivoRecaudoDao.class);
			dao.crearValidacion(validacionArchivoRecaudo);
			
			return true;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearValidacionTransaccional", e);
			return false;
		}
		
	}
	
	
	
	public List<ValidacionArchivoRecaudo> getValidacionesPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
	
			
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("aror_aror", aror_aror);
			hashMap.put("daror_id_reg", daror_id_reg);
			
			ValidacionArchivoRecaudoDao dao = session.getMapper(ValidacionArchivoRecaudoDao.class);
			return dao.getValidacionesPorARORxDAROR(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getValidacionesPorARORxDAROR", e);
			return null;
	
		} finally {
			session.close();
		}
	}
	
	

	 

}
