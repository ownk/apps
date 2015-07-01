package com.developer.persistence.modulo.unificacion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificarRepetido;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.logic.modulo.unificacion.dto.HistoricoProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.TransformacionArchivoRecaudo;
import com.developer.logic.modulo.unificacion.modelo.ArchivoRecaudoPorUnificarServicio;
import com.developer.logic.modulo.unificacion.modelo.ArchivoRecaudoUnificadoServicio;
import com.developer.logic.modulo.unificacion.modelo.ArchivoZIPProcesoUnificacionServicio;
import com.developer.logic.modulo.unificacion.modelo.TransformadorArchivoRecaudoServicio;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.unificacion.mapper.dao.ArchivoRecaudoPorUnificarDao;
import com.developer.persistence.modulo.unificacion.mapper.dao.ArchivoRecaudoPorUnificarRepetidoDao;

public class ArchivoRecaudoPorUnificarRepetidoControllerDB {
	

	
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
	
	
	
	public Boolean crearDocumentoTransaccional(SqlSession session, ArchivoRecaudoPorUnificarRepetido documento ){
		
		try{
			
			ArchivoRecaudoPorUnificarRepetidoDao dao = session.getMapper(ArchivoRecaudoPorUnificarRepetidoDao.class);
			dao.crearArchivo(documento);
			
			return true;
			
		}catch (Exception e) {
			SimpleLogger.error("Error crearDocumentoTransaccional", e);
			return false;
		}
		
		
	}
	
	
			
	public List<ArchivoRecaudoPorUnificarRepetido> getArchivosPorARUN(Long arun_arun){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			ArchivoRecaudoPorUnificarRepetidoDao dao = session.getMapper(ArchivoRecaudoPorUnificarRepetidoDao.class);
			List<ArchivoRecaudoPorUnificarRepetido> list = dao.getArchivosPorARUN(arun_arun);
			
			if(list != null){
				for (ArchivoRecaudoPorUnificarRepetido archivoRecaudoPorUnificarRepetido : list) {
					completarInformacionAdicionalArchivo(archivoRecaudoPorUnificarRepetido);
				}
				
			}
			
			return list; 
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getArchivosPorARUN", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	
	private void completarInformacionAdicionalArchivo(ArchivoRecaudoPorUnificarRepetido archivoRecaudoPorUnificarRepetido ){
		try {
			if(archivoRecaudoPorUnificarRepetido!=null && archivoRecaudoPorUnificarRepetido.getArpr_arpr()!=null){
				
				ArchivoRecaudoPorUnificarServicio archivoRecaudoPorUnificarServicio = new ArchivoRecaudoPorUnificarServicio();
				ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar = archivoRecaudoPorUnificarServicio.getArchivoRecaudo(archivoRecaudoPorUnificarRepetido.getArpr_arpu());
				
				archivoRecaudoPorUnificarRepetido.setArchivoRecaudoPorUnificar(archivoRecaudoPorUnificar);
				
				
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	 

}
