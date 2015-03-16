package com.developer.core.page;

import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.developer.core.config.GeneralConstants;
import com.developer.core.utils.JavaToXML;
import com.developer.core.utils.SimpleLogger;

public abstract class Page {

	
	
	private Map<String, Object> parametrosRequest;
	
	
	/**
	 * Permite validar si la pagina es valida 
	 * segun las condiciones que se establezcan 
	 * por cada una de las paginas
	 * 
	 * @param request
	 * @return TRUE si la pagina es valida y puede ser ejecuta. FALSE en caso contrario.
	 */
	public abstract boolean isAccesoValido(HttpServletRequest request);
	
	/**
	 * Retorna la pagina siguiente
	 * 
	 * @return pagina siguiente que debe ser ejecuta String
	 */

	public abstract String getNextPage();

	
	
	/**
	 * Retorna un objeto de acuerdo al parametro especificado
	 */
	public Object getParameterToObject(String parameterName,  Class<?> classPadre, Class<?> classContenida, Map<String, Object> mapRequest) {

		try {
			
			if(classContenida!= null){
				
				
				return JavaToXML.createObjectRequest(parameterName, classPadre.getName(), classContenida.getName(), mapRequest);
			
			}else{
				
				return JavaToXML.createObjectRequest(parameterName, classPadre.getName(), classPadre.getName(), mapRequest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return null;
	}
	
	public Map<String, Object> getParameters(HttpServletRequest request, Boolean reiniciarMapa){
		
		if(parametrosRequest== null || reiniciarMapa){
			
			Map<String, Object> mapMultipart = new HashMap<String, Object>();

			try {
				if (ServletFileUpload.isMultipartContent(request)) {
					
					ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
					List<?> fileItemsList = servletFileUpload.parseRequest(request);

					@SuppressWarnings("rawtypes")
					Iterator iter = fileItemsList.iterator();
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();

						if (item.isFormField()) {
							mapMultipart.put(item.getFieldName(), item.getString(GeneralConstants.ENCONDING));
						} else {
							mapMultipart.put(item.getFieldName(), item);
						}
					}
					
					parametrosRequest = mapMultipart;
				}else{
					
					Map<String, Object> mapSimple = new HashMap<String, Object>();
					for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
						String param = e.nextElement().toString();
						
						byte[] utf8 = new String(request.getParameter(param)).getBytes(Charset.forName(GeneralConstants.ENCONDING));
						String stringParameter = new String(utf8);
						
						mapSimple.put(param, stringParameter);
					}
					
					parametrosRequest = mapSimple;
					
				}
				
				
			} catch (Exception e) {
				SimpleLogger.error("Error generando el mapa de parametros",e);
				
			}
		}
		return parametrosRequest;
	}
	
	
	
	
	
	
}
