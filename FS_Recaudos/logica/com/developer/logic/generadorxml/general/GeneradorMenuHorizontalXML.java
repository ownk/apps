package com.developer.logic.generadorxml.general;

import java.util.List;

import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.dto.Modulo;
import com.developer.logic.modulo.autenticacion.dto.Servicio;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.persistence.modulo.autenticacion.UsuarioControllerDB;

public class GeneradorMenuHorizontalXML {
	
	ObjectToXML objectToXML;
	
	
	public GeneradorMenuHorizontalXML(ObjectToXML objectToXML) {
		this.objectToXML = objectToXML;
		
	}
	
	public StringBuffer getMenuHorizontalPorUsuario(Usuario usuario){
		
		StringBuffer serviciosXML = null;
		List<Servicio> servicios = UsuarioControllerDB.getInstance().getMenuHorizontalPorUsuario(usuario);
		List<Modulo> modulos = UsuarioControllerDB.getInstance().getModulosPorUsuario(usuario);
		
		if(servicios!=null && servicios.size()>0 && modulos!=null && modulos.size()>0){
			
			serviciosXML = new StringBuffer();
			serviciosXML.append("<menuHorizontal>");
			
			
			for (Modulo modulo : modulos) {
				serviciosXML.append("<menuItem>");
				
				serviciosXML.append(objectToXML.getXML(modulo));
				for (Servicio servicio : servicios) {
					if(servicio.getModulo_modulo().equals(modulo.getModulo_modulo())){
						serviciosXML.append(objectToXML.getXML(servicio));
						
					}
					
				}
				
				serviciosXML.append("</menuItem>");
			}
			
			serviciosXML.append("</menuHorizontal>");
			
		}
		
		return serviciosXML;
	}

}
