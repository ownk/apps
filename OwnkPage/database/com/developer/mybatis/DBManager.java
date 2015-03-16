package com.developer.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.developer.core.utils.SimpleLogger;

public class DBManager {

	private static SqlSessionFactory sqlSessionFactory;

	
	
	public static void initConfiguration() {
		try {
			String resource = "mybatis-config-oracle.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception e) {
			SimpleLogger.error("Error iniciando la configuracion de la base de datos");
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public static SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}
	
	public static SqlSession openSessionAutoCommit() {
		return sqlSessionFactory.openSession(true);
	}
	
	

}
