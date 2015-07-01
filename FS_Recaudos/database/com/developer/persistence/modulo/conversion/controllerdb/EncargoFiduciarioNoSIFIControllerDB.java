package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.EncargoFiduciarioNoSIFIDao;

public class EncargoFiduciarioNoSIFIControllerDB {
	
	
	

		
	public List<EncargoFiduciarioNoSIFI> getAllEncargosNoSIFI(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			EncargoFiduciarioNoSIFIDao dao = session.getMapper(EncargoFiduciarioNoSIFIDao.class);
			return dao.getAllEncargosNoSIFI();
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllEncargosNoSIFI", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public EncargoFiduciarioNoSIFI getEncargoNoSIFI(EncargoFiduciarioNoSIFI encargoFiduciarioNoSIFI){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			EncargoFiduciarioNoSIFIDao dao = session.getMapper(EncargoFiduciarioNoSIFIDao.class);
			return dao.getEncargoNoSIFI(encargoFiduciarioNoSIFI);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getEncargoNoSIFI", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}

	
	 

}
