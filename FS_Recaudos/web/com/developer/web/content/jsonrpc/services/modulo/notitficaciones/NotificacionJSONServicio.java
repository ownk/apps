package com.developer.web.content.jsonrpc.services.modulo.notitficaciones;

import java.util.List;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.SessionAppUsuario;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.notificaciones.NotificacionServicio;
import com.developer.logic.modulo.notificaciones.dto.Notificacion;
import com.developer.web.content.jsonrpc.IJsonSessionService;

public class NotificacionJSONServicio implements IJsonSessionService{

	private SessionAppUsuario sessionAppUsuario;
	private NotificacionServicio notificacionServicio;
	
	@Override
	public void setSessionAppUsuario(SessionAppUsuario sessionAppUsuario) {
		this.sessionAppUsuario= sessionAppUsuario;
		this.notificacionServicio = NotificacionServicio.getInstance();
		
	}
	
	public List<Notificacion> obtenerNotificacionesSinLeerPorUsuario() {
		Usuario usuario = sessionAppUsuario.getUsuario();
		
		List<Notificacion> notificaciones = null;
		if (usuario != null) {
	
			notificaciones = notificacionServicio.obtenerNotificacionesPorEstados(usuario, Notificacion.ESTADO_NUEVA, Notificacion.ESTADO_PENDIENTE_LECTURA);
			
		}else{
			SimpleLogger.error("No se pueden obtenerNotificacionesPendientesPorLeerPorUsuario, el usuario no existe");
			
		}
		
		return notificaciones;
	}
	
	public List<Notificacion> obtenerNotificacionesNuevasPorUsuario() {
		Usuario usuario = sessionAppUsuario.getUsuario();
		
		
		if (usuario != null) {
			return notificacionServicio.obtenerNotificacionesNuevasPorUsuario(usuario);
			
		}else{
			SimpleLogger.error("No se pueden obtenerNotificacionesSinLeerPorUsuario, el usuario no existe");
			
		}
		
		return null;
	}
	
	public Boolean marcarNotificacionPendientePorLeer(Long noti_noti){
		Usuario usuario = sessionAppUsuario.getUsuario();
		return NotificacionServicio.getInstance().marcarNotificacionPendientePorLeer(usuario, noti_noti);
		
		
	}


	

}



