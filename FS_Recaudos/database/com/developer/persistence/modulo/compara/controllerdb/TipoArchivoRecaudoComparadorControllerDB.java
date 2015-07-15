package com.developer.persistence.modulo.compara.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.compara.dto.TipoArchivoRecaudoComparador;
import com.developer.logic.modulo.conversion.dto.EstadoPlanAplicaPlanGenerico;
import com.developer.logic.modulo.conversion.dto.TipoRecaudoExcluir;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.compara.mapper.dao.TipoArchivoRecaudoComparadorDao;

public class TipoArchivoRecaudoComparadorControllerDB {
	
	
	
	
	
	public TipoArchivoRecaudoComparador getTipoArchivo(String tpar_tpar){
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
		
			return getTipoArchivo(tpar_tpar);
		} catch (Exception e) {
			SimpleLogger.error("Error getTipoArchivo", e);
			return null;

		} finally {
			session.close();
		}
		
		
	}
	
	
	
	



	public List<TipoArchivoRecaudoComparador> getAllTiposArchivo() {
		SqlSession session = DBManagerFSRecaudos.openSession();
		try {
	
			TipoArchivoRecaudoComparadorDao dao = session.getMapper(TipoArchivoRecaudoComparadorDao.class);
			return dao.getAllTiposArchivo();
			
		} catch (Exception e) {
			SimpleLogger.error("Error getAllTiposArchivo", e);
			return null;
	
		} finally {
			session.close();
		}
	}
	

	 

}
