package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.mybatis.DBManagerFSRecaudos;
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
		SqlSession session = DBManagerFSRecaudos.openSession();
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
	
	
	
	public Boolean crearArchivoTransaccional(SqlSession session, ArchivoZIPProcesoUnificacion documento){
		
		try{
			
			ArchivoZIPProcesoUnificacionDao dao = session.getMapper(ArchivoZIPProcesoUnificacionDao.class);
			dao.crearArchivoZIP(documento);
			
			return true;
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearArchivoTransaccional", e);
			return false;
		}
		
		
	}
	
	
	
	public ArchivoZIPProcesoUnificacion getArchivo(Long azpu_azpu){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoZIPProcesoUnificacionDao dao = session.getMapper(ArchivoZIPProcesoUnificacionDao.class);
			return dao.getArchivo(azpu_azpu);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivo", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<ArchivoZIPProcesoUnificacion> getArchivosPorPRUN(Long prun_prun){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoZIPProcesoUnificacionDao dao = session.getMapper(ArchivoZIPProcesoUnificacionDao.class);
			return dao.getArchivosPorPRUN(prun_prun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivosPorProcesoUnificacion", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
		
	 

}
