package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.TransformacionArchivoRecaudo;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.unificacion.mapper.dao.TransformacionArchivoRecaudoDao;

public class TransformadorArchivoRecaudoControllerDB {
	
	private static TransformadorArchivoRecaudoControllerDB instance;
	
	public static TransformadorArchivoRecaudoControllerDB getInstance() {
		if (instance == null) {
			instance = new TransformadorArchivoRecaudoControllerDB();
		}
		
		return instance;
	}
	
	public Long getSiguienteID(){
		SqlSession session = DBManager.openSession();
		try {
			
			TransformacionArchivoRecaudoDao dao = session.getMapper(TransformacionArchivoRecaudoDao.class);
			Long azpu_azpu= dao.getSiguienteID();

			return azpu_azpu;

		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
			
		} 	finally {
			session.close();
		}
	}
	
	
	
	public Boolean crearArchivoTransaccional(SqlSession session, TransformacionArchivoRecaudo documento){
		
		try{
			
			TransformacionArchivoRecaudoDao dao = session.getMapper(TransformacionArchivoRecaudoDao.class);
			dao.crearTransformacion(documento);
			
			return true;
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearArchivoTransaccional", e);
			return false;
		}
		
		
	}
	
	
	
	public TransformacionArchivoRecaudo getArchivo(Long azpu_azpu){
		SqlSession session = DBManager.openSession();
		
		try{
			
			TransformacionArchivoRecaudoDao dao = session.getMapper(TransformacionArchivoRecaudoDao.class);
			return dao.getTransformacion(azpu_azpu);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivo", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<TransformacionArchivoRecaudo> getTranformacionsPorPRUN(Long prun_prun){
		SqlSession session = DBManager.openSession();
		
		try{
			
			TransformacionArchivoRecaudoDao dao = session.getMapper(TransformacionArchivoRecaudoDao.class);
			return dao.getTransformacionsPorPRUN(prun_prun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getTranformacionsPorProcesoUnificacion", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
		
	 

}
