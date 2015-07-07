package com.developer.web.general;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.IXMLPageGenerador;
import com.developer.core.utils.JavaToXML;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.generadorxml.general.GeneradorInfoSessionAppUsuarioXML;
import com.developer.logic.modulo.autenticacion.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.SessionAppUsuario;

public class XMLPrivateGenerator implements IXMLPageGenerador{
	
	AutenticadorServicio autenticacionController;
	
	public XMLPrivateGenerator(AutenticadorServicio autenticacionController){
		this.autenticacionController = autenticacionController;
	}
	
	@Override
	public StringBuffer getAccessInfoXML(HttpServletRequest request, StringBuffer outputExecutionPage) {
		StringBuffer xmlAccessInfo = null;
		ObjectToXML objectToXML = new ObjectToXML();
		SessionAppUsuario sessionAppUsuario = autenticacionController.getSessionAppUsuario(request);
		
		if(sessionAppUsuario!= null){
			xmlAccessInfo = new StringBuffer();
			GeneradorInfoSessionAppUsuarioXML infoSessionAppUsuarioXML= new GeneradorInfoSessionAppUsuarioXML(objectToXML);
			StringBuffer infoUsuarioXMl = infoSessionAppUsuarioXML.getInfoSessionAppUsuarioXML(sessionAppUsuario);
			xmlAccessInfo.append(infoUsuarioXMl);
			
		}
		
		return xmlAccessInfo;
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
			xmlRequesAttributes.append(JavaToXML.exe(attrname, request.getAttribute(attrname)));
		}
		
		return xmlRequesAttributes;
	}


	@Override
	public StringBuffer getRequestParametersXML(HttpServletRequest request, StringBuffer outputExecutionPage) {
		StringBuffer xmlRequesParameters = new StringBuffer();
		
		for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
			String attrname = e.nextElement();
			xmlRequesParameters.append(JavaToXML.exe(attrname, request.getParameter(attrname)));
		}
		
		return xmlRequesParameters;
	}


	@Override
	public StringBuffer getSessionParametersXML(HttpServletRequest request, StringBuffer outputExecutionPage) {
		
		StringBuffer xmlSessionInfo = null;
		SessionAppUsuario sessionAppUsuario = autenticacionController.getSessionAppUsuario(request);
		
		if(sessionAppUsuario!= null){
			
			xmlSessionInfo = new StringBuffer();
			for (Enumeration<String> e = sessionAppUsuario.getHttpSession().getAttributeNames(); e.hasMoreElements();) {
				String attrname = e.nextElement();
				
				
				//Solo las variables que inicien como var. seran mostradas en el xml de la pagina
				if(attrname.startsWith("var.")){
					xmlSessionInfo.append(JavaToXML.exe(attrname, sessionAppUsuario.getHttpSession().getAttribute(attrname)));
				
				}
			}
			
		}
		
		return xmlSessionInfo;
		
	}
	
	@Override
	public StringBuffer getGeneralInfoXML(HttpServletRequest arg0,
			StringBuffer arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
