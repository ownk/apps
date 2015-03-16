package com.developer.core.utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class SimpleLogger {
	
	static{
		
		//Se inicializa una configuracion basica;
		BasicConfigurator.configure();
	}
	
	private static Logger logger = Logger.getLogger("APP:");
	
	public static void setAppName(String name){
		logger = Logger.getLogger(name);
	}
	
	public static void debug(Object msg) {
		System.out.println("---------------------");
		logger.debug(msg);
		System.out.println("---------------------\n");
		
	}
	
	public static void debug(Object msg, Throwable t) {
		System.out.println("---------------------");
		logger.debug(msg,t);
		System.out.println("---------------------\n");
		
	}
	public static void error(Object msg) {
		System.out.println("---------------------");
		logger.error(msg);
		System.out.println("---------------------\n");
		
	}
	
	public static void error(Object msg, Throwable t) {
		
		System.out.println("---------------------");
		logger.error(msg,t);
		System.out.println("---------------------\n");
		
	}
	
	public static void fatal(Object msg) {
		System.out.println("---------------------");
		logger.fatal(msg);
		System.out.println("---------------------\n");
	}
	
	public static void fatal(Object msg, Throwable t) {
		System.out.println("---------------------");
		logger.fatal(msg,t);
		System.out.println("---------------------\n");
	}
	
	public static void info(Object msg) {
		System.out.println("---------------------");
		logger.info(msg);
		System.out.println("---------------------\n");
	}
	
	public static void info(Object msg, Throwable t) {
		System.out.println("---------------------");
		logger.info(msg,t);
		System.out.println("---------------------\n");
	}
	
	public static void warn(Object msg) {
		System.out.println("---------------------");
		logger.warn(msg);
		System.out.println("---------------------\n");
	}
	
	public static void warn(Object msg, Throwable t) {
		System.out.println("---------------------");
		logger.warn(msg,t);
		System.out.println("---------------------\n");
	}
	
	public Boolean isDebugEnabled(){
		return logger.isDebugEnabled();
	}
	
	public static Logger getLogger(Class<? extends Object> clazz) {
		return Logger.getLogger(clazz);
	}
	
	public static void main(String[] args) {
		SimpleLogger.error("error");
	}
}
