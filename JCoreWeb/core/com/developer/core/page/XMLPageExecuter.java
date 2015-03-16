package com.developer.core.page;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.utils.JavaToXML;

public class XMLPageExecuter {
	
	/**
	 * Retorna XML de la pagina ejecutada
	 * 
	 * @param request
	 * @param outputExecutionPage
	 * @return
	 */
	
	
	public StringBuffer getXMLPage(HttpServletRequest request, IXMLPageGenerador pageGenerador,StringBuffer outputExecutionPage){
			
		
		StringBuffer xmlPage = new StringBuffer();

		xmlPage.append("<XMLPAGE>");
		
			//Informacion general estatica
			xmlPage.append("<GENERAL>");
				xmlPage.append(JavaToXML.exe("dateApp", new Date()));
				xmlPage.append(JavaToXML.exe("currentTimeMillis", System.currentTimeMillis()));
				xmlPage.append(JavaToXML.exe("contextPath", request.getContextPath()));
				xmlPage.append(JavaToXML.exe("remoteHost", request.getRemoteHost()));
				xmlPage.append(pageGenerador.getGeneralInfoXML(request, outputExecutionPage));
				
			xmlPage.append("</GENERAL>");
			
			//Informacion de acceso
			xmlPage.append("<ACCESS_INFO>");
				xmlPage.append(pageGenerador.getAccessInfoXML(request, outputExecutionPage));
			xmlPage.append("</ACCESS_INFO>");
			
			//Informacion de parametros de request
			xmlPage.append("<PARAMETERS>");
				xmlPage.append(pageGenerador.getRequestParametersXML(request, outputExecutionPage));
			xmlPage.append("</PARAMETERS>");
			
			//Informacion de atributos de request
			xmlPage.append("<ATTRIBUTES>");
				xmlPage.append(pageGenerador.getRequestAttributesXML(request, outputExecutionPage));
			xmlPage.append("</ATTRIBUTES>");
			
			//Informacion de objetos de session
			xmlPage.append("<SESSION>");
				xmlPage.append(pageGenerador.getSessionParametersXML(request, outputExecutionPage));
			xmlPage.append("</SESSION>");
			
			//Informacion del contenido de la pagina
			xmlPage.append("<PAGEDATA>");
				xmlPage.append(pageGenerador.getPageDataXML(request, outputExecutionPage));
				xmlPage.append("</PAGEDATA>");
			
		xmlPage.append("</XMLPAGE>");
		
		return xmlPage;
		
		
	}
	
	

}
