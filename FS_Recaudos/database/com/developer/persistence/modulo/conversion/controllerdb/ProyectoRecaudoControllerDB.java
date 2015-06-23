package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ProyectoCancelado;
import com.developer.logic.modulo.conversion.dto.ProyectoConFormulaDistribucion;
import com.developer.logic.modulo.conversion.dto.ProyectoNoSIFIActivo;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.ProyectoRecaudoDao;

public class ProyectoRecaudoControllerDB {
	
	private static ProyectoRecaudoControllerDB instance;
	
	public static ProyectoRecaudoControllerDB getInstance() {
		if (instance == null) {
			instance = new ProyectoRecaudoControllerDB();
		}
		
		return instance;
	}
	
	
				
	public List<ProyectoNoSIFIActivo> getAllProyectosNoSIFIActivos(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ProyectoRecaudoDao dao = session.getMapper(ProyectoRecaudoDao.class);
			return dao.getAllProyectosNoSIFIActivos();
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllProyectosNoSIFIActivos", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
		
	public List<ProyectoConFormulaDistribucion> getAllProyectosConFormulaDistribucion(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ProyectoRecaudoDao dao = session.getMapper(ProyectoRecaudoDao.class);
			return dao.getAllProyectosConFormulaDistribucion();
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllProyectosConFormulaDistribucion", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public List<ProyectoCancelado> getAllProyectosCancelados(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ProyectoRecaudoDao dao = session.getMapper(ProyectoRecaudoDao.class);
			return dao.getAllProyectosCancelados();
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllProyectosCancelados", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	

	 

}
