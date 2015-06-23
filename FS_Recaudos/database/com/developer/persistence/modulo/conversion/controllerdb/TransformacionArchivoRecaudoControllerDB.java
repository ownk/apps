package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.TransformacionArchivoRecaudo;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.TransformacionArchivoRecaudoDao;

public class TransformacionArchivoRecaudoControllerDB {
	
	private static TransformacionArchivoRecaudoControllerDB instance;
	
	public static TransformacionArchivoRecaudoControllerDB getInstance() {
		if (instance == null) {
			instance = new TransformacionArchivoRecaudoControllerDB();
		}
		
		return instance;
	}
	
	
	
	public Boolean crearArchivoTransaccional(SqlSession session, TransformacionArchivoRecaudo transformacionArchivoRecaudo){
		
		try{
					
			TransformacionArchivoRecaudoDao dao = session.getMapper(TransformacionArchivoRecaudoDao.class);
			dao.crearTransformacion(transformacionArchivoRecaudo);
			
			return true;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearArchivoTransaccional", e);
			return false;
		}
		
	}
	
	
	
	public List<TransformacionArchivoRecaudo> getTransformacionesPorARORxDAROR(Long aror_aror, Long daror_id_reg){
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
	
			
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("aror_aror", aror_aror);
			hashMap.put("daror_id_reg", daror_id_reg);
			
			TransformacionArchivoRecaudoDao dao = session.getMapper(TransformacionArchivoRecaudoDao.class);
			return dao.getTransformacionesPorARORxDAROR(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getTransformacionesPorARORxDAROR", e);
			return null;
	
		} finally {
			session.close();
		}
	}
	
	

	 

}
