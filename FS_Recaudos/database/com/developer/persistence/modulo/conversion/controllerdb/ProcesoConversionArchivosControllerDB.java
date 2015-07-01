package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.conversion.dto.HistoricoProcesoConversionArchivos;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.ProcesoConversionArchivosDao;

public class ProcesoConversionArchivosControllerDB {
	
	
	/**
	 *===================================================
	 * CONSULTAS ========================================
	 *===================================================
	 */
	
	
	
	
	public ProcesoConversionArchivos getProcesoConversionArchivos(Long prco_prco){
		SqlSession session = DBManagerFSRecaudos.openSession();

		try {

			ProcesoConversionArchivosDao dao = session.getMapper(ProcesoConversionArchivosDao.class);
			return dao.getProceso(prco_prco);
			
		} catch (Exception e) {
			SimpleLogger.error("Error getProcesoConversionArchivos", e);
			return null;

		} finally {
			session.close();
		}
		
	}
	
	
	
	
	public List<HistoricoProcesoConversionArchivos> getHistoricoPorProcesoConversionArchivos(Long prco_prco) {
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ProcesoConversionArchivosDao dao = session.getMapper(ProcesoConversionArchivosDao.class);
			return dao.getHistoricoPorProceso(prco_prco);
			
		}catch (Exception e) {
			SimpleLogger.error("Error getHistoricoPorProcesoConversionArchivos ", e);
			return null;	
			
		}finally {
			session.close();
		}
		
	}


	
		
	/**
	 *===================================================
	 * OPERACIONES TRANSACCIONALES ======================
	 *===================================================
	 */
	

	public Boolean setEstadoProcesoConversionArchivos(SqlSession session, Long prco_prco, String estado, String observacion,  Usuario usuario, StringBuffer mensajeError){
		
		Boolean respuesta = true;
		try {

			ProcesoConversionArchivos ProcesoConversionArchivos = new ProcesoConversionArchivos();
			ProcesoConversionArchivos.setPrco_prco(prco_prco);
			ProcesoConversionArchivos.setPrco_eprco(estado);
			
			try {
				
				ProcesoConversionArchivosDao ProcesoConversionArchivosDao = session.getMapper(ProcesoConversionArchivosDao.class);
				ProcesoConversionArchivosDao.setEstadoProceso(ProcesoConversionArchivos);
				
				
				//Se crea el historico de la preProcesoConversionArchivos
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("hprco_prco", ProcesoConversionArchivos.getPrco_prco());
				hashMap.put("hprco_eprco", ProcesoConversionArchivos.getPrco_eprco());
				hashMap.put("hprco_usua", usuario.getUsua_usua());
				hashMap.put("hprco_obser", observacion);
				
				ProcesoConversionArchivosDao.crearHistoricoProceso(hashMap);
				

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
	
	
	public ProcesoConversionArchivos iniciarProcesoConversionArchivosTransaccional(	SqlSession session, 
															Long prco_prun, 
															String observacion,
															Date prco_fini,
															Date prco_ffin,
															Date currentDate,
															Usuario usuario, 
															StringBuffer mensajeError){
		
		
		ProcesoConversionArchivos procesoConversionArchivos = new ProcesoConversionArchivos();
		
		//Se estable que el identificador unico de la ProcesoConversionArchivos es el mismo numero de que la preProcesoConversionArchivos
		procesoConversionArchivos.setPrco_prco(prco_prun);
		procesoConversionArchivos.setPrco_observ(observacion);
		procesoConversionArchivos.setPrco_fini(prco_fini);
		procesoConversionArchivos.setPrco_ffin(prco_ffin);
		procesoConversionArchivos.setPrco_eprco(ProcesoConversionArchivos.INICIADO);
		procesoConversionArchivos.setPrco_usua(usuario.getUsua_usua());
		procesoConversionArchivos.setPrco_fcrea(currentDate);
		procesoConversionArchivos.setPrco_prun(prco_prun);
		
		try{
			ProcesoConversionArchivosDao procesoConversionArchivosDao= session.getMapper(ProcesoConversionArchivosDao.class);
			
			//Se crea la preProcesoConversionArchivos
			procesoConversionArchivosDao.iniciarProcesoConversionArchivos(procesoConversionArchivos);
			
			//Se crea el historico de la preProcesoConversionArchivos
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("hprco_prco", procesoConversionArchivos.getPrco_prco());
			hashMap.put("hprco_eprco", procesoConversionArchivos.getPrco_eprco());
			hashMap.put("hprco_usua", usuario.getUsua_usua());
			hashMap.put("hprco_obser", observacion);
			
			procesoConversionArchivosDao.crearHistoricoProceso(hashMap);
			
			
			return procesoConversionArchivos;
		
		}catch (Exception e) {
			SimpleLogger.error("Error iniciarProcesoConversionArchivosTransaccional", e);
			mensajeError.append("Error al modificar el estado del proceso. "+e.getMessage());
			return null;
		}
			
	}

	

	 

}
