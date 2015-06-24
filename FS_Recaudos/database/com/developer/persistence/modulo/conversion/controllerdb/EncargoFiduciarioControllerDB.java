package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.EncargoFiduciarioDao;

public class EncargoFiduciarioControllerDB {
	
	private static EncargoFiduciarioControllerDB instance;
	
	public static EncargoFiduciarioControllerDB getInstance() {
		if (instance == null) {
			instance = new EncargoFiduciarioControllerDB();
		}
		
		return instance;
	}
	
	
				
	public List<EncargoFiduciarioSIFI> getAllEncargosSIFI(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			EncargoFiduciarioDao dao = session.getMapper(EncargoFiduciarioDao.class);
			return dao.getAllEncargosSIFI();
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllEncargosSIFI", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
		
	public List<EncargoFiduciarioNoSIFI> getAllEncargosNoSIFI(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			EncargoFiduciarioDao dao = session.getMapper(EncargoFiduciarioDao.class);
			return dao.getAllEncargosNoSIFI();
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllEncargosNoSIFI", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	

	public Boolean eliminarAllEncargosSIFITransaccional(SqlSession session){
		
		try{
			
			EncargoFiduciarioDao dao = session.getMapper(EncargoFiduciarioDao.class);
			dao.eliminarAllEncargosSIFI();
			
			return true;
			
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error eliminarAllEncargosSIFITransaccional", e);
			return false;
		}
	}
	
	
	public Boolean crearRegistroEncargoSIFITransaccional(SqlSession session, EncargoFiduciarioSIFI encargoFiduciarioSIFI){
		try{
			
			EncargoFiduciarioDao dao = session.getMapper(EncargoFiduciarioDao.class);
			dao.crearRegistroEncargoSIFI(encargoFiduciarioSIFI);
			
			return true;
			
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearRegistroEncargoSIFITransaccional", e);
			return false;
		}
		
	}
	
	 

}
