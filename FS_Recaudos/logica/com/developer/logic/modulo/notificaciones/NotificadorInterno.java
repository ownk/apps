package com.developer.logic.modulo.notificaciones;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang.StringUtils;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.notificaciones.dto.Notificacion;
import com.developer.persistence.modulo.notificaciones.NotificacionInternaControllerDB;

/**
 * Notificador de correo Observa el controlador de las notificaciones y envia
 * correo a la persona
 * 
 * 
 * @author oskar
 * 
 */
public class NotificadorInterno implements Observer {

	public void crearNotificacionInterna(Notificacion notificacion) {

		try {
			
			if(!StringUtils.isEmpty(notificacion.getNoti_usua_receptor())){
			
				SimpleLogger.debug("Enviando notificacion interna: "+ notificacion);
				
				if(NotificacionInternaControllerDB.getInstance().crearNotificacion(notificacion)){
					SimpleLogger.info("notificacion interna creada exitosamente!");
				}
			}else{
				SimpleLogger.warn("Error enviando notificacion interna, no se ha especificado usuario receptor");
			}
					
			
		} catch (Exception e) {
			SimpleLogger.error("Error enviando notificacion interna", e);
		}

	}

	@Override
	public void update(Observable o, Object notificacion) {
		if (notificacion != null && notificacion instanceof Notificacion) {
			crearNotificacionInterna((Notificacion) notificacion);
		} else {
			SimpleLogger.error("Notificacion no valida: " + notificacion);
		}
	}

}
