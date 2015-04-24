package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificarRepetido;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.unificacion.mapper.dao.ArchivoRecaudoPorUnificarDao;
import com.developer.persistence.modulo.unificacion.mapper.dao.ArchivoRecaudoPorUnificarRepetidoDao;

public class ArchivoRecaudoPorUnificarRepetidoControllerDB {
	
	private static ArchivoRecaudoPorUnificarRepetidoControllerDB instance;
	
	public static ArchivoRecaudoPorUnificarRepetidoControllerDB getInstance() {
		if (instance == null) {
			instance = new ArchivoRecaudoPorUnificarRepetidoControllerDB();
		}
		
		return instance;
	}
	
	public Long getSiguienteID(){
		SqlSession session = DBManager.openSession();
		try {
			
			ArchivoRecaudoPorUnificarDao dao = session.getMapper(ArchivoRecaudoPorUnificarDao.class);
			Long arpu_arpu= dao.getSiguienteID();

			return arpu_arpu;

		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
			
		} 	finally {
			session.close();
		}
	}
	
	
	
	public Boolean crearDocumentoTransaccional(SqlSession session, ArchivoRecaudoPorUnificarRepetido documento ){
		
		try{
			
			ArchivoRecaudoPorUnificarRepetidoDao dao = session.getMapper(ArchivoRecaudoPorUnificarRepetidoDao.class);
			dao.crearArchivo(documento);
			
			return true;
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearDocumentoTransaccional", e);
			return false;
		}
		
		
	}
	
	
			
	public List<ArchivoRecaudoPorUnificar> getArchivosPorARUN(Long prun_prun){
		SqlSession session = DBManager.openSession();
		
		try{
			
			ArchivoRecaudoPorUnificarDao dao = session.getMapper(ArchivoRecaudoPorUnificarDao.class);
			return dao.getArchivosPorPRUN(prun_prun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivosPorPRUN", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	
	 

}
