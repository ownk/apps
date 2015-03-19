package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.unificacion.dto.HistoricoProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.unificacion.mapper.dao.ProcesoUnificacionArchivosDao;

public class ProcesoUnificacionArchivosControllerDB {
	
	private static ProcesoUnificacionArchivosControllerDB instance;
	
	public static ProcesoUnificacionArchivosControllerDB getInstance() {
		if (instance == null) {
			instance = new ProcesoUnificacionArchivosControllerDB();
		}
		
		return instance;
	}
	
	/**
	 *===================================================
	 * CONSULTAS ========================================
	 *===================================================
	 */
	
	public ProcesoUnificacionArchivos getProcesoUnificacionArchivos(Long prun_prun){
		SqlSession session = DBManager.openSession();

		try {

			ProcesoUnificacionArchivosDao dao = session.getMapper(ProcesoUnificacionArchivosDao.class);
			return dao.getProcesoUnificacionArchivos(prun_prun);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getProcesoUnificacionArchivos", e);
			return null;

		} finally {
			session.close();
		}
		
	}
	
	
	
	
	public List<HistoricoProcesoUnificacionArchivos> getHistoricoPorProcesoUnificacionArchivos(Long prun_prun) {
		SqlSession session = DBManager.openSession();
		
		try{
			
			ProcesoUnificacionArchivosDao dao = session.getMapper(ProcesoUnificacionArchivosDao.class);
			return dao.getHistoricoPorProcesoUnificacionArchivos(prun_prun);
			
		}catch (Exception e) {
			SimpleLogger.error("Error getHistoricoPorProcesoUnificacionArchivos ", e);
			return null;	
			
		}finally {
			session.close();
		}
		
	}


	public List<ProcesoUnificacionArchivos> getProcesoUnificacionArchivosPorEstados(String...prun_estados){
		
		SqlSession session = DBManager.openSession();

		try {

			ProcesoUnificacionArchivosDao dao = session.getMapper(ProcesoUnificacionArchivosDao.class);
			
			HashMap<String , Object> hashMap = new HashMap<String, Object>();
			
			if(prun_estados!=null){
				String estados="";
				for (String string : prun_estados) {
					estados = estados+"'"+string+"',";
				}
				estados = estados.substring(0, estados.lastIndexOf(','));
				
				hashMap.put("prun_estados", estados);
			}
			
			return dao.getProcesoUnificacionArchivossPorEstados(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getProcesoUnificacionArchivosPorEstados", e);
			return null;

		} finally {
			session.close();
		}
		
	}
	
		
	/**
	 *===================================================
	 * OPERACIONES TRANSACCIONALES ======================
	 *===================================================
	 */
	

	public Boolean setEstadoProcesoUnificacionArchivos(Long prun_prun, String estado){
		SqlSession session = DBManager.openSession();
		Boolean respuesta = true;
		try {

			ProcesoUnificacionArchivos procesoUnificacionArchivos = new ProcesoUnificacionArchivos();
			procesoUnificacionArchivos.setPrun_prun(prun_prun);
			procesoUnificacionArchivos.setPrun_eprun(estado);
			
			try {
				
				ProcesoUnificacionArchivosDao procesoUnificacionArchivosDao = session.getMapper(ProcesoUnificacionArchivosDao.class);
				procesoUnificacionArchivosDao.setEstadoProcesoUnificacionArchivos(procesoUnificacionArchivos);

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
	
	
	public ProcesoUnificacionArchivos iniciarProcesoUnificacionArchivosTransaccional(	SqlSession session, 
															Long prun_prun, 
															String observacion,
															Usuario usuario, 
															StringBuffer mensajeError){
		
		
		ProcesoUnificacionArchivos procesoUnificacionArchivos = new ProcesoUnificacionArchivos();
		
		//Se estable que el identificador unico de la procesoUnificacionArchivos es el mismo numero de que la preprocesoUnificacionArchivos
		procesoUnificacionArchivos.setPrun_prun(prun_prun);
		procesoUnificacionArchivos.setPrun_observ(observacion);
		procesoUnificacionArchivos.setPrun_eprun(ProcesoUnificacionArchivos.INICIADO);
		
		try{
			ProcesoUnificacionArchivosDao procesoUnificacionArchivosDao= session.getMapper(ProcesoUnificacionArchivosDao.class);
			
			//Se crea la preprocesoUnificacionArchivos
			procesoUnificacionArchivosDao.iniciarProcesoUnificacionArchivos(procesoUnificacionArchivos);
			
			//Se crea el historico de la preprocesoUnificacionArchivos
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("hprun_prun", procesoUnificacionArchivos.getPrun_prun());
			hashMap.put("hprun_eprun", procesoUnificacionArchivos.getPrun_eprun());
			hashMap.put("hprun_usua", usuario.getUsua_usua());
			hashMap.put("hprun_obser", observacion);
			
			procesoUnificacionArchivosDao.crearHistoricoProcesoUnificacionArchivos(hashMap);
			
			
			return procesoUnificacionArchivos;
		
		}catch (Exception e) {
			SimpleLogger.error("Error iniciarProcesoUnificacionArchivosTransaccional", e);
			mensajeError.append("Error al crear el registro de procesoUnificacionArchivos");
			return null;
		}
			
	}
	

	

	 

}
