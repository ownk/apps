package com.developer.logic.generadorxml.general;

import java.util.Date;

import com.developer.core.utils.JavaToXML;
import com.developer.core.utils.ObjectToXML;
import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.persistence.modulo.general.controllerdb.ServerControllerDB;

public class GeneradorInfoSessionAppUsuarioXML {
	
	ObjectToXML objectToXML;
	
	GeneradorMenuHorizontalXML generadorMenuHorizontalXML;
	
	public GeneradorInfoSessionAppUsuarioXML(ObjectToXML objectToXML) {
		this.objectToXML = objectToXML;
		this.generadorMenuHorizontalXML = new GeneradorMenuHorizontalXML(objectToXML);
		
	}
	
	public StringBuffer getInfoSessionAppUsuarioXML(SessionAppUsuario sessionAppUsuario){
		StringBuffer infoSessionAppUsuarioXML = null;
		
	
		Date sysdate = ServerControllerDB.getInstance().getSysdate();
		
		if(sessionAppUsuario != null && sysdate!=null){
			
			infoSessionAppUsuarioXML = new StringBuffer();
			
			infoSessionAppUsuarioXML.append("<sessionAppUsuario>");
			infoSessionAppUsuarioXML.append("<infoUsuario>");
			infoSessionAppUsuarioXML.append(objectToXML.getXML(sessionAppUsuario.getUsuario()));
			infoSessionAppUsuarioXML.append(objectToXML.getXML(sessionAppUsuario.getPersona()));
			infoSessionAppUsuarioXML.append(JavaToXML.exe("sysdate", sysdate));
			infoSessionAppUsuarioXML.append("</infoUsuario>");
			
			//Se agrega el informacion del menu horizontal
			infoSessionAppUsuarioXML.append(generadorMenuHorizontalXML.getMenuHorizontalPorUsuario(sessionAppUsuario.getUsuario()));
			infoSessionAppUsuarioXML.append("</sessionAppUsuario>");
			
		}else{
			
			SimpleLogger.error("Error generando la informacion del usuario.");
		}
		
		return infoSessionAppUsuarioXML;
	}

}
