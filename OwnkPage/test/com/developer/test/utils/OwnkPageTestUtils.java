package com.developer.test.utils;

import com.developer.mybatis.DBManager;

public class OwnkPageTestUtils {
	
	/**
	 * Inicial la base de datos
	 */
	public static void initDB(){
		
		DBManager.initConfiguration();
	}

}
