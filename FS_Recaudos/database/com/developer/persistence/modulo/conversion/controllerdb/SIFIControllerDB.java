package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.mybatis.DBManagerSIFI29;
import com.developer.mybatis.DBManagerSIFI43;
import com.developer.persistence.modulo.conversion.mapper.dao.EncargoFiduciarioDao;

public class SIFIControllerDB {
	
	private static SIFIControllerDB instance;
	
	public static SIFIControllerDB getInstance() {
		if (instance == null) {
			instance = new SIFIControllerDB();
		}
		
		return instance;
	}
	
	
				
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI29(){
		SqlSession session = DBManagerSIFI29.openSession();
		
		try{
			
			EncargoFiduciarioDao dao = session.getMapper(EncargoFiduciarioDao.class);
			return dao.getAllEncargosSIFI();
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllEncargosSIFI29", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI43(){
		SqlSession session = DBManagerSIFI43.openSession();
		
		try{
			
			EncargoFiduciarioDao dao = session.getMapper(EncargoFiduciarioDao.class);
			return dao.getAllEncargosSIFI();
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllEncargosSIFI43", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	
	
	 

}
