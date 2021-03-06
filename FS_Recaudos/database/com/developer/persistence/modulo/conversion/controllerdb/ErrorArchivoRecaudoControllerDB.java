package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ErrorArchivoRecaudo;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.ErrorArchivoRecaudoDao;

public class ErrorArchivoRecaudoControllerDB {
	
	
	
	public Boolean crearErrorTransaccional(SqlSession session, ErrorArchivoRecaudo errorArchivoRecaudo){
		
		try{
					
			ErrorArchivoRecaudoDao dao = session.getMapper(ErrorArchivoRecaudoDao.class);
			dao.crearError(errorArchivoRecaudo);
			
			return true;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearErrorTransaccional", e);
			return false;
		}
		
	}
	
	
	
	public List<ErrorArchivoRecaudo> getErroresPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
	
			
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("aror_aror", aror_aror);
			hashMap.put("daror_id_reg", daror_id_reg);
			
			ErrorArchivoRecaudoDao dao = session.getMapper(ErrorArchivoRecaudoDao.class);
			return dao.getErroresPorARORxDAROR(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getErroresPorARORxDAROR", e);
			return null;
	
		} finally {
			session.close();
		}
	}



	public List<ErrorArchivoRecaudo> getErroresPorAROR(Long aror_aror) {
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
	
			
			ErrorArchivoRecaudoDao dao = session.getMapper(ErrorArchivoRecaudoDao.class);
			return dao.getErroresPorAROR(aror_aror);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getErroresPorAROR", e);
			return null;
	
		} finally {
			session.close();
		}
	}
	
	

	 

}
