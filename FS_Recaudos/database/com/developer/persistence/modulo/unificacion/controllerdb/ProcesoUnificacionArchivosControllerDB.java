package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.unificacion.dto.HistoricoProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.mybatis.DBManagerFSRecaudos;
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
	
	public Long getSiguienteID(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
			
			ProcesoUnificacionArchivosDao dao = session.getMapper(ProcesoUnificacionArchivosDao.class);
			Long prun_prun= dao.getSiguienteID();

			return prun_prun;

		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			return null;
			
		} 	finally {
			session.close();
		}
	}
	
	
	
	public ProcesoUnificacionArchivos getProcesoUnificacionArchivos(Long prun_prun){
		SqlSession session = DBManagerFSRecaudos.openSession();

		try {

			ProcesoUnificacionArchivosDao dao = session.getMapper(ProcesoUnificacionArchivosDao.class);
			return dao.getProceso(prun_prun);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getProcesoUnificacionArchivos", e);
			return null;

		} finally {
			session.close();
		}
		
	}
	
	
	
	
	public List<HistoricoProcesoUnificacionArchivos> getHistoricoPorProcesoUnificacionArchivos(Long prun_prun) {
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ProcesoUnificacionArchivosDao dao = session.getMapper(ProcesoUnificacionArchivosDao.class);
			return dao.getHistoricoPorProceso(prun_prun);
			
		}catch (Exception e) {
			SimpleLogger.error("Error getHistoricoPorProcesoUnificacionArchivos ", e);
			return null;	
			
		}finally {
			session.close();
		}
		
	}


	public List<ProcesoUnificacionArchivos> getProcesoUnificacionArchivosPorEstados(String...prun_estados){
		
		SqlSession session = DBManagerFSRecaudos.openSession();

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
			
			return dao.getProcesoPorEstados(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getProcesoUnificacionArchivosPorEstados", e);
			return null;

		} finally {
			session.close();
		}
		
	}
	
	
	public List<ProcesoUnificacionArchivos> getProcesosPorEstadoFechaIniFin(Date prun_fini, Date prun_ffin, String...prun_estados){
		
		SqlSession session = DBManagerFSRecaudos.openSession();

		try {

			ProcesoUnificacionArchivosDao dao = session.getMapper(ProcesoUnificacionArchivosDao.class);
			
			HashMap<String , Object> hashMap = new HashMap<String, Object>();
			
			hashMap.put("prun_fini", prun_fini);
			hashMap.put("prun_ffin", prun_ffin);
			
			
			if(prun_estados!=null){
				String estados="";
				for (String string : prun_estados) {
					estados = estados+"'"+string+"',";
				}
				estados = estados.substring(0, estados.lastIndexOf(','));
				
				hashMap.put("prun_estados", estados);
			}
			
			return dao.getProcesosPorEstadoFechaIniFin(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getProcesoUnificacionArchivosPorEstados", e);
			return null;

		} finally {
			session.close();
		}
		
	}
	
	
	public List<ProcesoUnificacionArchivos> getProcesosUnificacionArchivosPaginado(Long pageNumber, Long pageSize, String...prun_estados){
		
		SqlSession session = DBManagerFSRecaudos.openSession();

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
			
			hashMap.put("pageNumber", pageNumber);
			hashMap.put("pageSize", pageSize);
			
			return dao.getProcesos(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getProcesosUnificacionArchivosPaginado", e);
			return null;

		} finally {
			session.close();
		}
		
	}
	
	public Long getTotalProcesos(String...prun_estados){
		
		SqlSession session = DBManagerFSRecaudos.openSession();

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
			
			
			
			return dao.getTotalProcesos(hashMap);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getProcesosUnificacionArchivosPaginado", e);
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
	

	public Boolean setEstadoProcesoUnificacionArchivos(SqlSession session, Long prun_prun, String estado, String observacion,  Usuario usuario, StringBuffer mensajeError){
		
		Boolean respuesta = true;
		try {

			ProcesoUnificacionArchivos procesoUnificacionArchivos = new ProcesoUnificacionArchivos();
			procesoUnificacionArchivos.setPrun_prun(prun_prun);
			procesoUnificacionArchivos.setPrun_eprun(estado);
			
			try {
				
				ProcesoUnificacionArchivosDao procesoUnificacionArchivosDao = session.getMapper(ProcesoUnificacionArchivosDao.class);
				procesoUnificacionArchivosDao.setEstadoProceso(procesoUnificacionArchivos);
				
				
				//Se crea el historico de la preprocesoUnificacionArchivos
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("hprun_prun", procesoUnificacionArchivos.getPrun_prun());
				hashMap.put("hprun_eprun", procesoUnificacionArchivos.getPrun_eprun());
				hashMap.put("hprun_usua", usuario.getUsua_usua());
				hashMap.put("hprun_obser", observacion);
				
				procesoUnificacionArchivosDao.crearHistoricoProceso(hashMap);
				

			} catch (Exception e) {
				SimpleLogger.error("Error al modificar el estado del proceso", e);
				mensajeError.append("Error al modificar el estado del proceso. "+e.getMessage());
				return false;
			}
			
		}catch (Exception e) {
			SimpleLogger.error("Error al modificar el estado del proceso", e);
			mensajeError.append("Error al modificar el estado del proceso. "+e.getMessage());
			return false;
		} 	
		
		return respuesta;
		
	}
	
	
	public ProcesoUnificacionArchivos iniciarProcesoUnificacionArchivosTransaccional(	SqlSession session, 
															Long prun_prun, 
															Long prun_archivos,
															String observacion,
															Date prun_fini,
															Date prun_ffin,
															Date currentDate,
															Usuario usuario, 
															StringBuffer mensajeError){
		
		
		ProcesoUnificacionArchivos procesoUnificacionArchivos = new ProcesoUnificacionArchivos();
		
		//Se estable que el identificador unico de la procesoUnificacionArchivos es el mismo numero de que la preprocesoUnificacionArchivos
		procesoUnificacionArchivos.setPrun_prun(prun_prun);
		procesoUnificacionArchivos.setPrun_observ(observacion);
		procesoUnificacionArchivos.setPrun_fini(prun_fini);
		procesoUnificacionArchivos.setPrun_ffin(prun_ffin);
		procesoUnificacionArchivos.setPrun_eprun(ProcesoUnificacionArchivos.INICIADO);
		procesoUnificacionArchivos.setPrun_usua(usuario.getUsua_usua());
		procesoUnificacionArchivos.setPrun_fcrea(currentDate);
		procesoUnificacionArchivos.setPrun_archivos(prun_archivos);
		
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
			
			procesoUnificacionArchivosDao.crearHistoricoProceso(hashMap);
			
			
			return procesoUnificacionArchivos;
		
		}catch (Exception e) {
			SimpleLogger.error("Error iniciarProcesoUnificacionArchivosTransaccional", e);
			mensajeError.append("Error al modificar el estado del proceso. "+e.getMessage());
			return null;
		}
			
	}
	

	

	 

}
