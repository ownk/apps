package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.unificacion.mapper.dao.ArchivoRecaudoUnificadoDao;

public class ArchivoRecaudoUnificadoControllerDB {
	
	private static ArchivoRecaudoUnificadoControllerDB instance;
	
	public static ArchivoRecaudoUnificadoControllerDB getInstance() {
		if (instance == null) {
			instance = new ArchivoRecaudoUnificadoControllerDB();
		}
		
		return instance;
	}
	
	public Long getSiguienteID(){
		SqlSession session = DBManager.openSession();
		try {
			
			ArchivoRecaudoUnificadoDao dao = session.getMapper(ArchivoRecaudoUnificadoDao.class);
			Long arun_arun= dao.getSiguienteID();

			return arun_arun;

		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
			
		} 	finally {
			session.close();
		}
	}
	
	
	
	public Boolean crearDocumentoTransaccional(SqlSession session, ArchivoRecaudoUnificado documento){
		
		try{
			
			ArchivoRecaudoUnificadoDao dao = session.getMapper(ArchivoRecaudoUnificadoDao.class);
			dao.crearArchivo(documento);
			
			//Se crea el historico del documento
			try{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("harun_arun", documento.getArun_arun());
				hashMap.put("harun_earun", documento.getArun_earun());
				hashMap.put("harun_usua", documento.getArun_usua());
				hashMap.put("harun_obser", documento.getArun_observ());
				
				dao.crearHistorico(hashMap);
				
				return true;
			}catch (Exception e) {
				SimpleLogger.error("Error crearDocumentoTransaccional", e);
				return false;
			}
			
		
		}catch (Exception e) {
			SimpleLogger.error("Error crearDocumentoTransaccional", e);
			return false;
		}
		
		
	}
	
	
	public Boolean setEstado(Long arun_arun, String estado, String observacion, Usuario usuario){
		SqlSession session = DBManager.openSession();
		Boolean respuesta = true;
		try {

			ArchivoRecaudoUnificado archivoRecaudoPorUnificar = new ArchivoRecaudoUnificado();
			archivoRecaudoPorUnificar.setArun_arun(arun_arun);
			archivoRecaudoPorUnificar.setArun_earun(estado);
			archivoRecaudoPorUnificar.setArun_observ(observacion);
			
			
			try {
				
				ArchivoRecaudoUnificadoDao procesoUnificacionArchivosDao = session.getMapper(ArchivoRecaudoUnificadoDao.class);
				procesoUnificacionArchivosDao.setEstado(archivoRecaudoPorUnificar);
				
				
				try{
					
					
					//Se crea el historico de estado
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("harun_arun", archivoRecaudoPorUnificar.getArun_arun());
					hashMap.put("harun_earun", archivoRecaudoPorUnificar.getArun_earun());
					hashMap.put("harun_usua", usuario.getUsua_usua());
					hashMap.put("harun_obser", observacion);
					
					
					
				} catch (Exception e) {
					respuesta = false;
				}
				
				
				

			} catch (Exception e) {
				respuesta = false;
			}
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
		} 	finally {
			session.close();
		}
		
		return respuesta;
		
	}
	
	
	
	public ArchivoRecaudoUnificado getDocumento(Long arun_arun){
		SqlSession session = DBManager.openSession();
		
		try{
			
			ArchivoRecaudoUnificadoDao dao = session.getMapper(ArchivoRecaudoUnificadoDao.class);
			return dao.getDocumento(arun_arun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDocumento", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<ArchivoRecaudoUnificado> getDocumentosPorProcesoUnificacion(Long prun_prun){
		SqlSession session = DBManager.openSession();
		
		try{
			
			ArchivoRecaudoUnificadoDao dao = session.getMapper(ArchivoRecaudoUnificadoDao.class);
			return dao.getDocumentosPorProcesoUnificacion(prun_prun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDocumentosPorProcesoUnificacion", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
		
	 

}
