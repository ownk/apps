package com.developer.logic.controller.notificaciones.test;

import java.util.List;

import junit.framework.TestCase;

import com.developer.core.mail.Mail;
import com.developer.logic.modulo.notificaciones.NotificacionServicio;
import com.developer.logic.modulo.notificaciones.dto.Notificacion;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.notificaciones.NotificacionInternaControllerDB;

public class NotificacionControllerTest extends TestCase{

	@Override
	protected void setUp() throws Exception {
		DBManager.initConfiguration();
		Mail.configure("smtp.gmail.com", 25, "proyectos@ownk.co", "900819569", true, true, "proyectos@ownk.co", "Ownk", "proyectos@ownk.co");
		NotificacionServicio.initService();
		
	}
	
	public void testNotificarUsuario() {
		Notificacion notificacion = new Notificacion();

		notificacion.setNoti_cont("prueba");
		notificacion.setNoti_titu("prueba");
		notificacion.setNoti_usua_emisor("20042020111");
		notificacion.setNoti_usua_receptor("20042020111");

		Boolean respuesta = NotificacionServicio.getInstance().generarNotificacionPorUsuario("Emisor", "Prueba", "Mensaje de prueba", "20042020111");
		System.out.println(respuesta);
		
		assertTrue(respuesta);
	}
	
	public void testConsultarNotificacionesUsuarioPorEstado() {

		List<Notificacion> notificaciones = NotificacionInternaControllerDB.getInstance().consultarNotificacionesPorUsuario("20042020111", null);
		assertNotNull(notificaciones);
		assertTrue(notificaciones.size() > 0);
		System.out.println(notificaciones);
		System.out.println("total: " + notificaciones.size());
		
	}
	
	public void testCambiarEstadoNotificacion() {
		boolean respuesta = NotificacionInternaControllerDB.getInstance().cambiarEstadoNotificacion(1L, Notificacion.ESTADO_ELIMINADA);
		assertTrue(respuesta);
		System.out.println(respuesta);
	}

}
