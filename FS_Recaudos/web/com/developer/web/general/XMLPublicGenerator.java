package com.developer.web.general;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.IXMLPageGenerador;
import com.developer.core.utils.ObjectToXML;

public class XMLPublicGenerator implements IXMLPageGenerador {


	@Override
	public StringBuffer getAccessInfoXML(HttpServletRequest request, StringBuffer outputExecutionPage) {
				
		return null;
	}


	@Override
	public StringBuffer getPageDataXML(HttpServletRequest request, StringBuffer outputExecutionPage) {
		
		if(outputExecutionPage == null){
			return new StringBuffer("NO CONTENT ESPECIFIED");
			
		}else{
			return outputExecutionPage;
		}
	}


	@Override
	public StringBuffer getRequestAttributesXML(HttpServletRequest request, StringBuffer outputExecutionPage) {
		
		StringBuffer xmlRequesAttributes = new StringBuffer();
		for (Enumeration<String> e = request.getAttributeNames(); e.hasMoreElements();) {
			String attrname = e.nextElement();
			xmlRequesAttributes.append(new ObjectToXML().getXML(request.getAttribute(attrname)));
		}
		
		return xmlRequesAttributes;
	}


	@Override
	public StringBuffer getRequestParametersXML(HttpServletRequest request, StringBuffer outputExecutionPage) {
		StringBuffer xmlRequesParameters = new StringBuffer();
		
		for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
			String attrname = e.nextElement();
			xmlRequesParameters.append(new ObjectToXML().getXML(request.getParameter(attrname)));
		}
		
		return xmlRequesParameters;
	}


	@Override
	public StringBuffer getSessionParametersXML(HttpServletRequest request, StringBuffer outputExecutionPage) {
		
		return null;
		
	}
	
	@Override
	public StringBuffer getGeneralInfoXML(HttpServletRequest arg0,
			StringBuffer arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
