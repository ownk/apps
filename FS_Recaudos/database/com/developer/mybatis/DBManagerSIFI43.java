package com.developer.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.developer.core.utils.SimpleLogger;

public class DBManagerSIFI43 {

	private static SqlSessionFactory sqlSessionFactory;

	
	
	public static void initConfiguration() {
		try {
			String resource = "mybatis-config-oracle-sifi43.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error iniciando la configuracion de la base de datos"+e.getMessage());
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public static SqlSession openSession() {
		
		
		try {
			return sqlSessionFactory.openSession();
		} catch (Exception e) {
			SimpleLogger.error("Error iniciando la session de la base de datos"+e.getMessage());
			e.printStackTrace();
		}
		
		return null;
				
	}
	
	public static SqlSession openSessionAutoCommit() {
		
		try {
			return sqlSessionFactory.openSession(true);
		}catch (Exception e) {
			SimpleLogger.error("Error iniciando la session autocommit de la base de datos"+e.getMessage());
			e.printStackTrace();
		}
		
		return null;
				
	}
	
	

}
