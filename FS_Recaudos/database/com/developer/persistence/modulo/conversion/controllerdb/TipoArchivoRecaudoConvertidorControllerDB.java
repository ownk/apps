package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.EstadoPlanAplicaPlanGenerico;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.TipoArchivoRecaudoConvertidorDao;

public class TipoArchivoRecaudoConvertidorControllerDB {
	
	private static TipoArchivoRecaudoConvertidorControllerDB instance;
	
	public static TipoArchivoRecaudoConvertidorControllerDB getInstance() {
		if (instance == null) {
			instance = new TipoArchivoRecaudoConvertidorControllerDB();
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
	
			TipoArchivoRecaudoConvertidorDao dao = session.getMapper(TipoArchivoRecaudoConvertidorDao.class);
			return dao.getTiposArchivoPorPRCO(prco_prco);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getTiposArchivoPorPRCO", e);
			return null;
	
		} finally {
			session.close();
		}
	}
	
	public List<EstadoPlanAplicaPlanGenerico> getEstadosAplicaPlanGenericoPorTPAR(String tpar_tpar){
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
	
			TipoArchivoRecaudoConvertidorDao dao = session.getMapper(TipoArchivoRecaudoConvertidorDao.class);
			return dao.getEstadosAplicaPlanGenericoPorTPAR(tpar_tpar);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getEstadosAplicaPlanGenericoPorTPAR", e);
			return null;
	
		} finally {
			session.close();
		}
	}
	
	

	 

}
