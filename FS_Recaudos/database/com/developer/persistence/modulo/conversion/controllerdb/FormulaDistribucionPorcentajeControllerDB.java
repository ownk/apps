package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.DistribucionPorFormulaPorcentaje;
import com.developer.logic.modulo.conversion.dto.EstadoPlanFormulaDistribucion;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.EncargoFiduciarioDao;
import com.developer.persistence.modulo.conversion.mapper.dao.FormulaDistribucionPorcentajeDao;

public class FormulaDistribucionPorcentajeControllerDB {
	
	private static FormulaDistribucionPorcentajeControllerDB instance;
	
	public static FormulaDistribucionPorcentajeControllerDB getInstance() {
		if (instance == null) {
			instance = new FormulaDistribucionPorcentajeControllerDB();
		}
		
		return instance;
	}
	
	
				
	public List<EstadoPlanFormulaDistribucion> getAllEstadosAplicaFormula(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			FormulaDistribucionPorcentajeDao dao = session.getMapper(FormulaDistribucionPorcentajeDao.class);
			return dao.getAllEstadosAplicaFormula();
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllEncargosSIFI", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
		
	public List<DistribucionPorFormulaPorcentaje> getAllDistribucionesPorcentuales(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		try{
			
			FormulaDistribucionPorcentajeDao dao = session.getMapper(FormulaDistribucionPorcentajeDao.class);
			return dao.getAllDistribucionesPorcentuales();
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllEncargosNoSIFI", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	

	 

}
