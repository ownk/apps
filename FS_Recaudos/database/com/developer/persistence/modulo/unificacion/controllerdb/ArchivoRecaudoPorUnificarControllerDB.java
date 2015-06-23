package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.HistoricoArchivoRecaudoPorUnificar;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.unificacion.mapper.dao.ArchivoRecaudoPorUnificarDao;

public class ArchivoRecaudoPorUnificarControllerDB {
	
	private static ArchivoRecaudoPorUnificarControllerDB instance;
	
	public static ArchivoRecaudoPorUnificarControllerDB getInstance() {
		if (instance == null) {
			instance = new ArchivoRecaudoPorUnificarControllerDB();
		}
		
		return instance;
	}
	
	public Long getSiguienteID(){
		SqlSession session = DBManagerFSRecaudos.openSession();
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
	
	
	
	public Boolean crearDocumentoTransaccional(SqlSession session, ArchivoRecaudoPorUnificar documento ){
		
		try{
			
			ArchivoRecaudoPorUnificarDao dao = session.getMapper(ArchivoRecaudoPorUnificarDao.class);
			dao.crearArchivo(documento);
			
			//Se crea el historico del documento
			try{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("harpu_arpu", documento.getArpu_arpu());
				hashMap.put("harpu_earpu", documento.getArpu_earpu());
				hashMap.put("harpu_usua", documento.getArpu_usua());
				hashMap.put("harpu_obser", documento.getArpu_observ());
				
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
	
	
	public Boolean setEstado(Long arpu_arpu, String estado, String observacion, Usuario usuario){
		SqlSession session = DBManagerFSRecaudos.openSession();
		Boolean respuesta = true;
		try {

			ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar = new ArchivoRecaudoPorUnificar();
			archivoRecaudoPorUnificar.setArpu_arpu(arpu_arpu);
			archivoRecaudoPorUnificar.setArpu_earpu(estado);
			archivoRecaudoPorUnificar.setArpu_observ(observacion);
			
			
			try {
				
				ArchivoRecaudoPorUnificarDao procesoUnificacionArchivosDao = session.getMapper(ArchivoRecaudoPorUnificarDao.class);
				procesoUnificacionArchivosDao.setEstado(archivoRecaudoPorUnificar);
				
				
				try{
					
					
					//Se crea el historico de estado
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("harpu_arpu", archivoRecaudoPorUnificar.getArpu_arpu());
					hashMap.put("harpu_earpu", archivoRecaudoPorUnificar.getArpu_earpu());
					hashMap.put("harpu_usua", usuario.getUsua_usua());
					hashMap.put("harpu_obser", observacion);
					
					
					
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
	
	
	
	
	public ArchivoRecaudoPorUnificar getDocumento(Long arpu_arpu){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoPorUnificarDao dao = session.getMapper(ArchivoRecaudoPorUnificarDao.class);
			return dao.getArchivo(arpu_arpu);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDocumento", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<ArchivoRecaudoPorUnificar> getArchivosPorPRUN(Long prun_prun){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
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
	
	
	public List<ArchivoRecaudoPorUnificar> getArchivosPorAZPU(Long azpu_azpu){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoPorUnificarDao dao = session.getMapper(ArchivoRecaudoPorUnificarDao.class);
			return dao.getArchivosPorAZPU(azpu_azpu);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivosPorAZPU", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
		
	public List<HistoricoArchivoRecaudoPorUnificar> getHistoricoArchivo(Long arpu_arpu){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoPorUnificarDao dao = session.getMapper(ArchivoRecaudoPorUnificarDao.class);
			return dao.getHistoricoArchivo(arpu_arpu);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getHistoricoArchivo", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public List<ArchivoRecaudoPorUnificar>  getArchivosTPARxPRUN(Long prun_prun, String tpar_tpar ) {

		SqlSession session = DBManagerFSRecaudos.openSession();

		try {

			ArchivoRecaudoPorUnificarDao dao = session.getMapper(ArchivoRecaudoPorUnificarDao.class);
			
			HashMap<String , Object> hashMap = new HashMap<String, Object>();
			hashMap.put("prun_prun", prun_prun);
			hashMap.put("tpar_tpar", tpar_tpar);
			
			
			return dao.getArchivosTPARxPRUN(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getArchivosTPARxPRUN", e);
			return null;

		} finally {
			session.close();
		}

	}
	 

}
