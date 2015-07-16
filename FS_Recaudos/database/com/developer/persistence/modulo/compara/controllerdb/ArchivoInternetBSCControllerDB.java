package com.developer.persistence.modulo.compara.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.compara.dto.ArchivoInternetBSC;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.compara.mapper.dao.ArchivoInternetBSCDao;

public class ArchivoInternetBSCControllerDB {
	
	
	
	public Long getSiguienteID(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
			
			ArchivoInternetBSCDao dao = session.getMapper(ArchivoInternetBSCDao.class);
			Long ibsc_ibsc= dao.getSiguienteID();

			return ibsc_ibsc;

		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
			
		} 	finally {
			session.close();
		}
	}
	
	
	
	public Boolean crearArchivoTransaccional(SqlSession session, ArchivoInternetBSC documento){
		
		try{
			
			ArchivoInternetBSCDao dao = session.getMapper(ArchivoInternetBSCDao.class);
			dao.crearArchivo(documento);
			
			return true;
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearArchivoTransaccional", e);
			return false;
		}
		
		
	}
	
	
	
	public ArchivoInternetBSC getArchivo(Long ibsc_ibsc){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoInternetBSCDao dao = session.getMapper(ArchivoInternetBSCDao.class);
			return dao.getArchivo(ibsc_ibsc);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivo", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<ArchivoInternetBSC> getArchivosPorARUN(Long arun_arun){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoInternetBSCDao dao = session.getMapper(ArchivoInternetBSCDao.class);
			return dao.getArchivosPorARUN(arun_arun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivosPorARUN", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
		
	 

}
