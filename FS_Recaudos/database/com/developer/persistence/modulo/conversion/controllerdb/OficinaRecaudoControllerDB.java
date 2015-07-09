package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.OficinaRecaudo;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.OficinaRecaudoDao;

public class OficinaRecaudoControllerDB {
	
	
	
				

	public List<OficinaRecaudo> getAllOficinas(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		
		try{
			
			OficinaRecaudoDao dao = session.getMapper(OficinaRecaudoDao.class);
			return dao.getAllOficinas();
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllOficinas", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}

	public OficinaRecaudo getOficinaSIFI(String ofic_bsc, Long ofic_fond) {
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		
		try{
			
			//Se crea el historico de estado
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("ofic_bsc", ofic_bsc);
			hashMap.put("ofic_fond", ofic_fond);
			
			
			OficinaRecaudoDao dao = session.getMapper(OficinaRecaudoDao.class);
			return dao.getOficinaSIFI(hashMap);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getOficinasSIFI", e);
			return null;
		} 	finally {
			session.close();
		}
	}
	

	 

}
