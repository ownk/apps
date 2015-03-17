package com.developer.persistence.modulo.general;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.general.mapper.dao.ServerDao;

public class ServerControllerDB {
	
	private static ServerControllerDB instance;
	
	public static ServerControllerDB getInstance() {
		if (instance == null) {
			instance = new ServerControllerDB();
		}
		
	
		return instance;
	}
	
		
	public Date getSysdate(){
		SqlSession session = DBManager.openSession();

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
