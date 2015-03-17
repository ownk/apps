package com.developer.logic.modulo.notificaciones;

import java.util.List;
import java.util.Observable;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.notificaciones.dto.Notificacion;
import com.developer.persistence.modulo.notificaciones.NotificacionInternaControllerDB;

public class NotificacionServicio extends Observable{

	
	private static NotificacionServicio instance;
	
	public static NotificacionServicio getInstance() {
		if (instance == null) {
			instance = new NotificacionServicio();
		}
		
		return instance;
	}
	
	
	//TODO hacer cada notificador configurable
	public static void initService(){
		SimpleLogger.debug("Registrando observadores");
		getInstance().addObserver(new NotificadorCorreo());
		getInstance().addObserver(new NotificadorInterno());
		
		
	}
	
	public boolean generarNotificacionPorUsuario(String usua_usua_emisor, String titulo, String mensaje, String usua_usua_receptor){
		
		try {
			
			if(usua_usua_emisor==null){
				usua_usua_emisor = "_SYSTEM_";
				
			}
			
			Notificacion notificacion = new Notificacion();
			notificacion.setNoti_usua_emisor(usua_usua_emisor);
			notificacion.setNoti_titu(titulo);
			notificacion.setNoti_cont(mensaje);
			notificacion.setNoti_usua_receptor(usua_usua_receptor);
			
			
			//Se notifica a los observadores la generacion de la nueva notiticacion
			setChanged();
			notifyObservers(notificacion);
			return true;
		
		} catch (Exception e) {
			SimpleLogger.error("Error generando la notificacion", e);
			return false;
		}
		
	}
	
	public List<Notificacion> obtenerNotificacionesPendientesPorLeerPorUsuario(Usuario usuario){
		
		return NotificacionInternaControllerDB.getInstance().consultarNotificacionesPorUsuario(usuario.getUsua_usua(), Notificacion.ESTADO_PENDIENTE_LECTURA);
		
		
	}
	
	public  List<Notificacion> obtenerNotificacionesPorEstados(Usuario usuario, String...noti_esta){
		
		return NotificacionInternaControllerDB.getInstance().consultarNotificacionesPorUsuario(usuario.getUsua_usua(), noti_esta);
		
		
	}
	
	public List<Notificacion> obtenerNotificacionesNuevasPorUsuario(Usuario usuario){
		
		return NotificacionInternaControllerDB.getInstance().consultarNotificacionesPorUsuario(usuario.getUsua_usua(), Notificacion.ESTADO_NUEVA);
			
	}
	
	
	
	public Boolean marcarNotificacionPendientePorLeer(Usuario usuario, Long noti_noti){
		
		if (usuario != null) {
			NotificacionInternaControllerDB.getInstance().cambiarEstadoNotificacion(noti_noti, Notificacion.ESTADO_PENDIENTE_LECTURA);  	
		}
		return false;
	}
}
