package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.TransformacionArchivoRecaudo;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.TransformacionArchivoRecaudoDao;

public class TransformacionArchivoRecaudoControllerDB {
	
	
	
	
	public Boolean crearTransformacionTransaccional(SqlSession session, TransformacionArchivoRecaudo transformacionArchivoRecaudo){
		
		try{
			if(transformacionArchivoRecaudo.getTrar_valor_descri()==null){
				transformacionArchivoRecaudo.setTrar_valor_descri("Sin valor");
			}		
			TransformacionArchivoRecaudoDao dao = session.getMapper(TransformacionArchivoRecaudoDao.class);
			dao.crearTransformacion(transformacionArchivoRecaudo);
			
			return true;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearTransformacionTransaccional", e);
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



	public List<TransformacionArchivoRecaudo> getTransformacionesPorAROR(Long aror_aror) {
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
	
			TransformacionArchivoRecaudoDao dao = session.getMapper(TransformacionArchivoRecaudoDao.class);
			return dao.getTransformacionesPorAROR(aror_aror);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getTransformacionesPorAROR", e);
			return null;
	
		} finally {
			session.close();
		}
	}
	
	

	 

}
