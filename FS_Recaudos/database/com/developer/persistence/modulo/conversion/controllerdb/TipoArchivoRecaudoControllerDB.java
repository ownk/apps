package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.TipoArchivoRecaudoDao;

public class TipoArchivoRecaudoControllerDB {
	
	private static TipoArchivoRecaudoControllerDB instance;
	
	public static TipoArchivoRecaudoControllerDB getInstance() {
		if (instance == null) {
			instance = new TipoArchivoRecaudoControllerDB();
		}
		
		return instance;
	}
	
	
	
	
	public TipoArchivoRecaudoConvertidor getTipoArchivo(String tpar_tpar){
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
		
			return getTipoArchivo(tpar_tpar);
		} catch (Exception e) {
			SimpleLogger.error("Error getTipoArchivo", e);
			return null;

		} finally {
			session.close();
		}
		
		
	}
	
	
	
	public List<TipoArchivoRecaudoConvertidor> getTiposArchivoPorPRCO(Long prco_prco){
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
	
			TipoArchivoRecaudoDao dao = session.getMapper(TipoArchivoRecaudoDao.class);
			return dao.getTiposArchivoPorPRCO(prco_prco);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getTiposArchivoPorPRCO", e);
			return null;
	
		} finally {
			session.close();
		}
	}
	
	

	 

}
