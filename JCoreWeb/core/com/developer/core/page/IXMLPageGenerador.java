package com.developer.core.page;

import javax.servlet.http.HttpServletRequest;

public interface IXMLPageGenerador {
	
	/*
	 * Retorna el xml especifico de acceso
	 */
	public StringBuffer getAccessInfoXML(HttpServletRequest request, StringBuffer outputExecutionPage);
	
	public StringBuffer getRequestParametersXML(HttpServletRequest request, StringBuffer outputExecutionPage);
	
	public StringBuffer getRequestAttributesXML(HttpServletRequest request, StringBuffer outputExecutionPage);
	
	public StringBuffer getSessionParametersXML(HttpServletRequest request, StringBuffer outputExecutionPage);
	
	public StringBuffer getPageDataXML(HttpServletRequest request, StringBuffer outputExecutionPage);
	
	public StringBuffer getGeneralInfoXML(HttpServletRequest request, StringBuffer outputExecutionPage);
	
	
	
	
	

}
