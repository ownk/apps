package com.developer.persistence.modulo.conversion.controllerdb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ParametroGeneralConversion;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.mapper.dao.ParametroGeneralConversionDao;

public class ParametroGeneralConversionControllerDB {
	
	
	
				

	public List<ParametroGeneralConversion> getAllParametros(){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		
		try{
			
			ParametroGeneralConversionDao dao = session.getMapper(ParametroGeneralConversionDao.class);
			return dao.getAllParametros();
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getAllParametros", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	
	public ParametroGeneralConversion getParametroGeneral(String para_para){
		SqlSession session = DBManagerFSRecaudos.openSession();
		
		
		try{
			
			ParametroGeneralConversionDao dao = session.getMapper(ParametroGeneralConversionDao.class);
			return dao.getParametroGeneral( para_para);
			
			
		}catch (Exception e) {
			SimpleLogger.error("Error getParametroGeneral", e);
			return null;
		} 	finally {
			session.close();
		}
		
	}
	

	 

}
