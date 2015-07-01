package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.mybatis.DBManagerSIFI29;
import com.developer.mybatis.DBManagerSIFI43;
import com.developer.persistence.modulo.conversion.mapper.dao.SIFI29Dao;
import com.developer.persistence.modulo.conversion.mapper.dao.SIFI43Dao;

public class SIFIControllerDB {
	
	
	
				
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI29(){
		SqlSession session = DBManagerSIFI29.openSession();
		
		if(session!=null){
		
			try{
				
				SIFI29Dao dao = session.getMapper(SIFI29Dao.class);
				return dao.getAllEncargosSIFI();
				
			}catch (Exception e) {
				SimpleLogger.error("Error getAllEncargosSIFI29", e);
				return null;
			} 	finally {
				session.close();
			}
		
		}else{
			
			return null;
		}
		
	}
	
	public List<EncargoFiduciarioSIFI>  getEncargosSIFI29(EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		SqlSession session = DBManagerSIFI29.openSession();
		
		if(session!=null){
		
		try{
			
			SIFI29Dao dao = session.getMapper(SIFI29Dao.class);
			return dao.getEncargoSIFI(encargoFiduciarioSIFI);
			
		}catch (Exception e) {
			SimpleLogger.error("Error getEncargoSIFI29", e);
			return null;
		} 	finally {
			session.close();
		}
		
		}else{
			
			return null;
		}
		
	}
	
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI43(){
		SqlSession session = DBManagerSIFI43.openSession();
		
		if(session!=null){
		
			try{
				
				SIFI43Dao dao = session.getMapper(SIFI43Dao.class);
				return dao.getAllEncargosSIFI();
				
			}catch (Exception e) {
				SimpleLogger.error("Error getAllEncargosSIFI43", e);
				return null;
			} 	finally {
				session.close();
			}
		}else{
			return null;
		}
		
	}
	
	public List<EncargoFiduciarioSIFI> getEncargoSIFI43(EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		SqlSession session = DBManagerSIFI43.openSession();
		
		if(session!=null){
		
			try{
				
				SIFI43Dao dao = session.getMapper(SIFI43Dao.class);
				return dao.getEncargoSIFI(encargoFiduciarioSIFI);
				
			}catch (Exception e) {
				SimpleLogger.error("Error getEncargoSIFI43", e);
				return null;
			} 	finally {
				session.close();
			}
		}else{
			return null;
		}
		
	}
	
	
	
	 

}
