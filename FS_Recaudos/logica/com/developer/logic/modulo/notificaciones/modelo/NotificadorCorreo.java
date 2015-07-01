package com.developer.logic.modulo.notificaciones.modelo;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang.StringUtils;

import com.developer.core.mail.Mail;
import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.autenticacion.modelo.UsuarioServicio;
import com.developer.logic.modulo.general.dto.Persona;
import com.developer.logic.modulo.notificaciones.dto.Notificacion;
import com.developer.persistence.modulo.autenticacion.controllerdb.UsuarioControllerDB;
import com.developer.persistence.modulo.general.controllerdb.PersonaControllerDB;

/**
 * Notificador de correo Observa el controlador de las notificaciones y envia
 * correo a la persona
 * 
 * 
 * @author oskar
 * 
 */
public class NotificadorCorreo implements Observer {

	public void enviarCorreoDeNotificacion(Notificacion notificacion) {

		try {
			SimpleLogger.debug("Enviando correo de notificacion: "+ notificacion);
			
			if(notificacion.getNoti_usua_receptor()!=null){
			
				Usuario receptor = new UsuarioServicio().getUsuario(notificacion.getNoti_usua_receptor());
				
				if(receptor!= null && !StringUtils.isEmpty(receptor.getUsua_mail())){
					
					Persona persona = new PersonaControllerDB().getPersonaPorUsuario(receptor);
					
					String nombreCompleto = persona.getPern_nomb()+" "+persona.getPern_papell()+" "+persona.getPern_sapell();
					
					Mail.enviar(receptor.getUsua_mail(), nombreCompleto, notificacion.getNoti_titu(), notificacion.getNoti_cont() );
					
					SimpleLogger.info("notificacion de correo enviada exitosamente!");
					
				}else{
					SimpleLogger.error("El usuario"+receptor.getUsua_usua()+" no tiene correcto para envio de notificaciones");
					
				}
			}else{
				SimpleLogger.error("Error enviando correo de notificaciones, el usuario receptor no existe");
				
			}
			
		} catch (Exception e) {
			SimpleLogger.error("Error enviando correo de notificaciones", e);
		}

	}

	@Override
	public void update(Observable o, Object notificacion) {
		if (notificacion != null && notificacion instanceof Notificacion) {
			enviarCorreoDeNotificacion((Notificacion) notificacion);
		} else {
			SimpleLogger.error("Notificacion no valida: " + notificacion);
		}
	}
	
	

}
