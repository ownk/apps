package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.unificacion.mapper.dao.ArchivoZIPProcesoUnificacionDao;

public class ArchivoZIPProcesoUnificacionControllerDB {
	
	private static ArchivoZIPProcesoUnificacionControllerDB instance;
	
	public static ArchivoZIPProcesoUnificacionControllerDB getInstance() {
		if (instance == null) {
			instance = new ArchivoZIPProcesoUnificacionControllerDB();
		}
		
		return instance;
	}
	
	public Long getSiguienteID(){
		SqlSession session = DBManager.openSession();
		try {
			
			ArchivoZIPProcesoUnificacionDao dao = session.getMapper(ArchivoZIPProcesoUnificacionDao.class);
			Long azpu_azpu= dao.getSiguienteID();

			return azpu_azpu;

		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
			
		} 	finally {
			session.close();
		}
	}
	
	
	
	public Boolean crearDocumentoTransaccional(SqlSession session, ArchivoZIPProcesoUnificacion documento){
		
		try{
			
			ArchivoZIPProcesoUnificacionDao dao = session.getMapper(ArchivoZIPProcesoUnificacionDao.class);
			dao.crearArchivoZIP(documento);
			
			return true;
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearDocumentoTransaccional", e);
			return false;
		}
		
		
	}
	
	
	
	public ArchivoZIPProcesoUnificacion getDocumento(Long azpu_azpu){
		SqlSession session = DBManager.openSession();
		
		try{
			
			ArchivoZIPProcesoUnificacionDao dao = session.getMapper(ArchivoZIPProcesoUnificacionDao.class);
			return dao.getDocumento(azpu_azpu);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDocumento", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<ArchivoZIPProcesoUnificacion> getDocumentosPorProcesoUnificacion(Long prun_prun){
		SqlSession session = DBManager.openSession();
		
		try{
			
			ArchivoZIPProcesoUnificacionDao dao = session.getMapper(ArchivoZIPProcesoUnificacionDao.class);
			return dao.getDocumentosPorProcesoUnificacion(prun_prun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDocumentosPorProcesoUnificacion", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
		
	 

}
