package com.developer.test.utils;

import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("com.developer.logic.modulo.general.dto.Persona");
			
			Method[] methods = clazz.getMethods();

			for (Method metodo : methods) {

				System.out.println(metodo.getName());
				
				Class<?>[] parametros = metodo.getParameterTypes();
				
				System.out.println("--:"+metodo.toGenericString());
				
				
				if(parametros!=null && parametros.length>0){
				Class<?> tipo = parametros[0];
				
				System.out.println(tipo);
				/*
				if (metodo.getName().equals()) {

					String sname = metodo.toGenericString();

					sname = sname.substring(sname.indexOf("(") + 1);
					sname = sname.substring(0, sname.indexOf(")"));

					String[] params = sname.split(",");

					Class<?>[] classes = metodo.getParameterTypes();
					for (int i = 0; i < classes.length; i++) {
						Class<?> tipo = classes[i];
						argumentos[i] = createObject(params[i], tipo, parameters[i], mapRequest, request, resolver);
					}

					Object return_ = metodo.invoke(object, argumentos);
					return JavaToXML.exe(name != null ? name : return_.getClass().getSimpleName().toLowerCase(), return_, isname, prefix, uri, one_prefix).toString();
				}
				*/
				}
			}
		} catch (Exception e) {
		}
	}
	
}
