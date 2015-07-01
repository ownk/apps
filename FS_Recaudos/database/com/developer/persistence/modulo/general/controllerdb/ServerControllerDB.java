package com.developer.persistence.modulo.general.controllerdb;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.general.mapper.dao.ServerDao;

public class ServerControllerDB {
	
		
	public Date getSysdate(){
		SqlSession session = DBManagerFSRecaudos.openSession();

		try {

			ServerDao dbMapper = session.getMapper(ServerDao.class);
			Date sysdate = dbMapper.getSysdate();

			return sysdate;	
		}catch (Exception e) {
			SimpleLogger.error("Error consultando hora actual DBServer", e);
			
			return null;
			
		} finally {
			session.close();
		}
		
		
		
	} 
	
		
	 

}
