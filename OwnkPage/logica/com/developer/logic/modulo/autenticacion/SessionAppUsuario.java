package com.developer.logic.modulo.autenticacion;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.general.dto.Persona;
import com.developer.logic.modulo.notificadoreventos.NotificadorEventosSesion;


public class SessionAppUsuario {
	
	HttpSession httpSession;
	Usuario usuario;
	Persona persona;
	NotificadorEventosSesion notificadorEventosSesion;
	
	public SessionAppUsuario(HttpSession httpSession, Usuario usuario, Persona persona) {
		this.httpSession = httpSession;
		this.usuario = usuario;
		this.persona = persona;
		notificadorEventosSesion = new NotificadorEventosSesion();
		
	}
	
	public HttpSession getHttpSession() {
		return httpSession;
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public void notificarEvento(String mensaje){
		notificadorEventosSesion.notificar(mensaje);
		
		httpSession.setAttribute("var.eventosSession", notificadorEventosSesion.getListaEventos());
	}
	
	public List<String> obtenerEventos(){
		return notificadorEventosSesion.getListaEventos();
	}
	
	
	
}
