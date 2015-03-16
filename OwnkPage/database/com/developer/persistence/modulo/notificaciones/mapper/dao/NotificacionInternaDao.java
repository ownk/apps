package com.developer.persistence.modulo.notificaciones.mapper.dao;

import java.util.HashMap;
import java.util.List;

import com.developer.logic.modulo.notificaciones.dto.Notificacion;


public interface NotificacionInternaDao {

	public void guardarNotificacion(Notificacion notificacion);
	
	public List<Notificacion> consultarNotificacionesPorUsuario(HashMap<String, Object> map) ;
	
	public void cambiarEstadoNotificacion(Notificacion notificacion);
	
}
