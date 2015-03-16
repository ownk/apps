package com.developer.core.page;

import javax.servlet.http.HttpServletRequest;

public abstract class PrivatePage extends Page{
	
	/**
	 * La pagina ejecuta las tareas asignadas y puede retornar un documento xml (StringBuffer)
	 * que contendra la informacion genera por la ejecucion de la pagina. La informacion que se 
	 * coloque en el objeto REQUEST se visualizara dentro de la seccion PARAMETRS del documento xml
	 * que se construira junton con el xml retornado por la pagina.
	 * 
	 * @param request
	 * @param session Dado 	que la pagina es privada se establece el objeto session
	 * 
	 * @return StringBuffer XML generado por cada pagina como informacion que desea visualizar
	 */
	
	public abstract StringBuffer executeAction(HttpServletRequest request);
	
	

}
