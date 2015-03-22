package com.developer.persistence.modulo.unificacion.controllerdb;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.unificacion.mapper.dao.TipoArchivoRecaudoDao;

public class TipoArchivoRecaudoControllerDB {
	
	private static TipoArchivoRecaudoControllerDB instance;
	
	public static TipoArchivoRecaudoControllerDB getInstance() {
		if (instance == null) {
			instance = new TipoArchivoRecaudoControllerDB();
		}
		
		return instance;
	}
	
	
	public Boolean crearTipoArchivoTransaccional(SqlSession session, TipoArchivoRecaudo tipoArchivo ){
		
		try{
			
			TipoArchivoRecaudoDao dao = session.getMapper(TipoArchivoRecaudoDao.class);
			dao.crearTipoArchivo(tipoArchivo);
			
			return true;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearTipoArchivoTransaccional", e);
			return false;
		}
		
		
	}
	
	public TipoArchivoRecaudo getTipoArchivo(String tpar_tpar){
		
		SqlSession session = DBManager.openSession();

		try {

			TipoArchivoRecaudoDao dao = session.getMapper(TipoArchivoRecaudoDao.class);
			return dao.getTipoArchivo(tpar_tpar);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getTipoArchivoRecaudo", e);
			return null;

		} finally {
			session.close();
		}
		
		
	}
	
		
	 

}
