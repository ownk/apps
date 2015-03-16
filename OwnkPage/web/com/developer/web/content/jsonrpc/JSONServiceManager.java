package com.developer.web.content.jsonrpc;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import com.developer.core.page.ContextInfo;
import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.SessionAppUsuario;



public class JSONServiceManager {
	
	
	private static JSONServiceManager jsonrpcManager;
	
	
	public static JSONServiceManager geJsonrpcManager(){
		
		if(jsonrpcManager == null){
			
			jsonrpcManager = new JSONServiceManager();
			
		}
		
		return jsonrpcManager;
	}

	
	public void initJsonNonSessionServices(){
		
	
		org.jabsorb.JSONRPCBridge jsonBridge = org.jabsorb.JSONRPCBridge.getGlobalBridge();
	
		
	  /*
	   * ==============================================================
	   * Se cargan los json declarados en el archivo json-services.xml
	   * ==============================================================
	   */
	  
	  	XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;
		String filename;
		filename = ContextInfo.getInstance().getDiskPathForResource("WEB-INF/json-services.xml");

		try {
			xmlDocument = new File(filename);
			if (xmlDocument.exists()) {
				InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
				Integer cantidad = Integer.parseInt(xPath.evaluate("count(/json-services/service)", inputSource));

				for (int i = 1; i <= cantidad; i++) {

					try {

						String urn = xPath.evaluate("/json-services/service[" + i + "]/@urn", new InputSource(
								new FileInputStream(xmlDocument)));
						String className = xPath.evaluate("/json-services/service[" + i + "]/@class", new InputSource(new FileInputStream(xmlDocument)));

						Object obj = Class.forName(className).newInstance();
						jsonBridge.registerObject(urn, obj);

						SimpleLogger.info("Se ha registradro servicio JSON " + urn + ":" + obj.getClass().getSimpleName());

					} catch (Throwable e) {
						SimpleLogger.error("No se ha podido agregar el servicio json", e);
					}
				}

			}
		} catch (Exception e) {
			SimpleLogger.error("Error al realizar inicializacion de servicios json sin-session", e);
		}
		
	}
	
	public void initJSonSessionServices(SessionAppUsuario sessionAppUsuario){
		
		
		/*
		 * ======================================================
		 * Se cargan los servicios json que sean de session
		 * ======================================================
		 */
		
		if(sessionAppUsuario != null) {
		
			org.jabsorb.JSONRPCBridge json_bridge = null;
			json_bridge = (org.jabsorb.JSONRPCBridge) sessionAppUsuario.getHttpSession().getAttribute("JSONRPCBridge");
			
			if(json_bridge == null) {
				  SimpleLogger.warn("Iniciando servicios JSON de session");	
				
				  json_bridge = new org.jabsorb.JSONRPCBridge();
				  sessionAppUsuario.getHttpSession().setAttribute("JSONRPCBridge", json_bridge);
				  
				  /*
				   * ==============================================================
				   * Se cargan los json declarados en el archivo json-services.xml
				   * ==============================================================
				   */
				  
				  
				  	XPathFactory factory = XPathFactory.newInstance();
					XPath xPath = factory.newXPath();
					File xmlDocument = null;
					String filename;
					//filename = new File(AutenticacionServicio.class.getResource("/").getPath()).getParentFile().getAbsolutePath()+ "/json-services.xml";
					filename = ContextInfo.getInstance().getDiskPathForResource("WEB-INF/json-services.xml");
			
					try {
						xmlDocument = new File(filename);
						if (xmlDocument.exists()) {
							InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
							Integer cantidad = Integer.parseInt(xPath.evaluate("count(/json-services/session-service)", inputSource));
			
							for (int i = 1; i <= cantidad; i++) {
			
								try {
			
									String urn = xPath.evaluate("/json-services/session-service[" + i + "]/@urn", new InputSource(
											new FileInputStream(xmlDocument)));
									String className = xPath.evaluate("/json-services/session-service[" + i + "]/@class", new InputSource(new FileInputStream(xmlDocument)));
			
									Object obj = Class.forName(className).newInstance();
			
									if (obj instanceof IJsonSessionService) {
										((IJsonSessionService) obj).setSessionAppUsuario(sessionAppUsuario);
									} else {
										SimpleLogger.warn("El servicio Json de session no es JsonService");
									}
			
									json_bridge.registerObject(urn, obj);
			
									SimpleLogger.info("registrando servicio " + urn + ":" + obj.getClass().getSimpleName());
			
								} catch (Throwable e) {
									SimpleLogger.error("No se ha podido agregar el servicio json", e);
								}
							}
			
						}
					} catch (Exception e) {
						SimpleLogger.error("Error al realizar inicializacion de servicios json de session", e);
					}
				
			}	
		
		}
		
	}


}
