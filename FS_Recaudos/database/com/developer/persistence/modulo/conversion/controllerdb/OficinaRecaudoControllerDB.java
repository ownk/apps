package com.developer.persistence.modulo.conversion.controllerdb;

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

	public OficinaRecaudo getOficinaSIFI(String ofic_bsc) {
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		
		try{
			
			OficinaRecaudoDao dao = session.getMapper(OficinaRecaudoDao.class);
			return dao.getOficinaSIFI(ofic_bsc);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getOficinasSIFI", e);
			return null;
		} 	finally {
			session.close();
		}
	}
	

	 

}
