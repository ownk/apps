package com.developer.persistence.modulo.compara.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.compara.dto.ComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.DetalleComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.DetalleResumenComparacion;
import com.developer.logic.modulo.compara.dto.DiferenciaResumenComparacion;
import com.developer.logic.modulo.compara.dto.HomologacionTipoRecaudoComparador;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.compara.mapper.dao.ComparacionArchivoRecaudoDao;

public class ComparacionArchivoRecaudoControllerDB {
	
	
	public Long getSiguienteID(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			Long cpar_cpar= dao.getSiguienteID();

			return cpar_cpar;

		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
			
		} 	finally {
			session.close();
		}
	}
	
	
	public Boolean crearComparacionTransaccional(SqlSession session, ComparacionArchivoRecaudo comparacionArchivoRecaudo){
		
		try{
					
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			dao.crearComparacion(comparacionArchivoRecaudo);
			
			
			return true;
			
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearComparacionTransaccional", e);
			return false;
		}
		
	}
	
	
	
		
	public Boolean crearDetalleComparacionTransaccional(SqlSession session, DetalleComparacionArchivoRecaudo detalleComparacionArchivoRecaudo){
		
		try{
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			dao.crearDetalleComparacion(detalleComparacionArchivoRecaudo);
			
			return true;
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearDetalleComparacionTransaccional", e);
			return false;
		}
		
		
	}
	
	
	
	public ComparacionArchivoRecaudo getComparacion(Long cpar_cpar){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			return dao.getComparacion(cpar_cpar);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDocumento", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
			
	public List<ComparacionArchivoRecaudo> getComparacionesPorARUN(Long arun_arun){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			return dao.getComparacionesPorARUN(arun_arun);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getComparacionesPorARUN", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public List<DetalleComparacionArchivoRecaudo> getFechasCPAR(Long cpar_cpar){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			return dao.getFechasCPAR(cpar_cpar);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getFechasCPAR", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}	
	
	public List<DetalleComparacionArchivoRecaudo> getAllDetallesCPAR(Long cpar_cpar){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			return dao.getAllDetallesCPAR(cpar_cpar);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllDetallesCPAR", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public List<DetalleResumenComparacion> getDetallesResumenCPAR(Long cpar_cpar){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			return dao.getDetallesResumenCPAR(cpar_cpar);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDetallesResumenCPAR", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public List<DiferenciaResumenComparacion> getDiferenciasResumenCPAR(Long cpar_cpar){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			return dao.getDiferenciasResumenCPAR(cpar_cpar);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getDiferenciasResumenCPAR", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	 
	public List<HomologacionTipoRecaudoComparador> getAllHomologacionesTipoRecaudo(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ComparacionArchivoRecaudoDao dao = session.getMapper(ComparacionArchivoRecaudoDao.class);
			return dao.getAllHomologacionesTipoRecaudo();
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllHomologacionesTipoRecaudo", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	

}
