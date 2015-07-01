package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.HistoricoArchivoRecaudoGeneradoSIFI;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.ArchivoRecaudoGeneradoSIFIDao;

public class ArchivoRecaudoGeneradoSIFIControllerDB {
	
	
	public Boolean crearArchivoTransaccional(SqlSession session, ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI){
		
		try{
					
			ArchivoRecaudoGeneradoSIFIDao dao = session.getMapper(ArchivoRecaudoGeneradoSIFIDao.class);
			dao.crearArchivo(archivoRecaudoGeneradoSIFI);
			
			//Se crea el historico del documento
			try{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("harge_arge", archivoRecaudoGeneradoSIFI.getArge_arge());
				hashMap.put("harge_earge", archivoRecaudoGeneradoSIFI.getArge_earge());
				hashMap.put("harge_usua", archivoRecaudoGeneradoSIFI.getArge_usua());
				hashMap.put("harge_obser", archivoRecaudoGeneradoSIFI.getArge_observ());
				
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
	
	
	public Boolean setEstado(Long arge_arge, String estado, String observacion, Usuario usuario){
		
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		Boolean respuesta = true;
		try {
	
			ArchivoRecaudoGeneradoSIFI ArchivoRecaudoGeneradoSIFI = new ArchivoRecaudoGeneradoSIFI();
			ArchivoRecaudoGeneradoSIFI.setArge_arge(arge_arge);
			ArchivoRecaudoGeneradoSIFI.setArge_earge(estado);
			ArchivoRecaudoGeneradoSIFI.setArge_observ(observacion);
			
			
			try {
				
				ArchivoRecaudoGeneradoSIFIDao procesoUnificacionArchivosDao = session.getMapper(ArchivoRecaudoGeneradoSIFIDao.class);
				procesoUnificacionArchivosDao.setEstado(ArchivoRecaudoGeneradoSIFI);
				
				
				try{
					
					
					//Se crea el historico de estado
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("harge_arge", ArchivoRecaudoGeneradoSIFI.getArge_arge());
					hashMap.put("harge_earge", ArchivoRecaudoGeneradoSIFI.getArge_earge());
					hashMap.put("harge_usua", usuario.getUsua_usua());
					hashMap.put("harge_obser", observacion);
					
					
					
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
	
	
		
	public Boolean crearDetalleArchivoTransaccional(SqlSession session, DetalleArchivoRecaudoGeneradoSIFI detalleArchivoRecaudoGeneradoSIFI){
		
		try{
			
			ArchivoRecaudoGeneradoSIFIDao dao = session.getMapper(ArchivoRecaudoGeneradoSIFIDao.class);
			dao.crearDetalleArchivo(detalleArchivoRecaudoGeneradoSIFI);
			
			return true;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearDetalleArchivoTransaccional", e);
			return false;
		}
		
		
	}
	
	
	
	public ArchivoRecaudoGeneradoSIFI getDocumento(Long arge_arge){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoGeneradoSIFIDao dao = session.getMapper(ArchivoRecaudoGeneradoSIFIDao.class);
			return dao.getArchivo(arge_arge);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDocumento", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<ArchivoRecaudoGeneradoSIFI> getArchivosPorPRCO(Long prco_prco){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoGeneradoSIFIDao dao = session.getMapper(ArchivoRecaudoGeneradoSIFIDao.class);
			return dao.getArchivosPorPRCO(prco_prco);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivosPorPRCO", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
		
	public List<HistoricoArchivoRecaudoGeneradoSIFI> getHistoricoArchivo(Long arge_arge){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoGeneradoSIFIDao dao = session.getMapper(ArchivoRecaudoGeneradoSIFIDao.class);
			return dao.getHistoricoArchivo(arge_arge);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getHistoricoArchivo", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	

	 

}
