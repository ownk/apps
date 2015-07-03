package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.HistoricoArchivoRecaudoOriginalPorConvertir;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.ArchivoRecaudoOriginalPorConvertirDao;

public class ArchivoRecaudoOriginalPorConvertirControllerDB {
	
	
	
	public Boolean crearArchivoTransaccional(SqlSession session, ArchivoRecaudoOriginalPorConvertir ArchivoRecaudoOriginalPorConvertir){
		
		try{
					
			ArchivoRecaudoOriginalPorConvertirDao dao = session.getMapper(ArchivoRecaudoOriginalPorConvertirDao.class);
			dao.crearArchivo(ArchivoRecaudoOriginalPorConvertir);
			
			//Se crea el historico del documento
			try{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("haror_aror", ArchivoRecaudoOriginalPorConvertir.getAror_aror());
				hashMap.put("haror_earor", ArchivoRecaudoOriginalPorConvertir.getAror_earor());
				hashMap.put("haror_usua", ArchivoRecaudoOriginalPorConvertir.getAror_usua());
				hashMap.put("haror_obser", ArchivoRecaudoOriginalPorConvertir.getAror_observ());
				
				dao.crearHistorico(hashMap);
				
				return true;
				
			}catch (Exception e) {
				SimpleLogger.error("Error crearArchivoTransaccional", e);
				return false;
			}
			
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearArchivoTransaccional", e);
			return false;
		}
		
	}
	
	
	public Boolean setEstado(Long aror_aror, String estado, String observacion, Usuario usuario){
		SqlSession session = DBManagerFSRecaudos.openSession();
		Boolean respuesta = true;
		try {
	
			respuesta = setEstadoTransaccional(session, aror_aror, estado, observacion, usuario);
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
		} 	finally {
			session.close();
		}
		
		return respuesta;
		
	}
	
	
	public Boolean setEstadoTransaccional(SqlSession session, Long aror_aror, String estado, String observacion, Usuario usuario){
		
		Boolean respuesta = true;
		try {
	
			ArchivoRecaudoOriginalPorConvertir ArchivoRecaudoOriginalPorConvertir = new ArchivoRecaudoOriginalPorConvertir();
			ArchivoRecaudoOriginalPorConvertir.setAror_aror(aror_aror);
			ArchivoRecaudoOriginalPorConvertir.setAror_earor(estado);
			ArchivoRecaudoOriginalPorConvertir.setAror_observ(observacion);
			
			
			try {
				
				ArchivoRecaudoOriginalPorConvertirDao archivoDao = session.getMapper(ArchivoRecaudoOriginalPorConvertirDao.class);
				archivoDao.setEstado(ArchivoRecaudoOriginalPorConvertir);
				
				
				try{
					
					
					//Se crea el historico de estado
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("haror_aror", ArchivoRecaudoOriginalPorConvertir.getAror_aror());
					hashMap.put("haror_earor", ArchivoRecaudoOriginalPorConvertir.getAror_earor());
					hashMap.put("haror_usua", usuario.getUsua_usua());
					hashMap.put("haror_obser", observacion);
					
					archivoDao.crearHistorico(hashMap);
					
				} catch (Exception e) {
					respuesta = false;
				}
				
				
				
	
			} catch (Exception e) {
				respuesta = false;
			}
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			respuesta = false;
		} 	
		
		return respuesta;
		
	}
	
	
		
	public Boolean crearDetalleArchivoTransaccional(SqlSession session, DetalleArchivoRecaudoOriginalPorConvertir detalleArchivoRecaudoOriginalPorConvertir){
		
		try{
			
			ArchivoRecaudoOriginalPorConvertirDao dao = session.getMapper(ArchivoRecaudoOriginalPorConvertirDao.class);
			dao.crearDetalleArchivo(detalleArchivoRecaudoOriginalPorConvertir);
			
			return true;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearDetalleArchivoTransaccional", e);
			return false;
		}
		
		
	}
	
	
	
	public ArchivoRecaudoOriginalPorConvertir getDocumento(Long aror_aror){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoOriginalPorConvertirDao dao = session.getMapper(ArchivoRecaudoOriginalPorConvertirDao.class);
			return dao.getArchivo(aror_aror);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDocumento", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<ArchivoRecaudoOriginalPorConvertir> getArchivosPorPRCO(Long prun_prun){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoOriginalPorConvertirDao dao = session.getMapper(ArchivoRecaudoOriginalPorConvertirDao.class);
			return dao.getArchivosPorPRCO(prun_prun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivosPorPRCO", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
		
	public List<HistoricoArchivoRecaudoOriginalPorConvertir> getHistoricoArchivo(Long aror_aror){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoOriginalPorConvertirDao dao = session.getMapper(ArchivoRecaudoOriginalPorConvertirDao.class);
			return dao.getHistoricoArchivo(aror_aror);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getHistoricoArchivo", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public List<DetalleArchivoRecaudoOriginalPorConvertir> getAllDetallesAROR(Long aror_aror){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoOriginalPorConvertirDao dao = session.getMapper(ArchivoRecaudoOriginalPorConvertirDao.class);
			return dao.getAllDetallesAROR(aror_aror);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllDetallesAROR", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	 

}
