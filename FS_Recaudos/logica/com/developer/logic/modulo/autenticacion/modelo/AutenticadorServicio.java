package com.developer.logic.modulo.autenticacion.modelo;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Servicio;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.persistence.modulo.autenticacion.controllerdb.UsuarioControllerDB;

public class AutenticadorServicio {
	
	//Colaboradores
	GeneradorSessionApp generadorSession;
	
	private static AutenticadorServicio instance;
	
	public static AutenticadorServicio getInstance() {
		if (instance == null) {
			instance = new AutenticadorServicio();
		}
		
		return instance;
	}
	
	public void setGeneradorSession(GeneradorSessionApp generadorSession){
		this.generadorSession = generadorSession;
	}
	
		
	public Usuario isUsuarioValido(String login, String clave){
		
		String claveEncriptada = com.developer.core.utils.StringUtils.MD5(login+clave);
		Usuario usuarioAutenticado = UsuarioControllerDB.getInstance().isUsuarioValido(login, claveEncriptada);
						
		return usuarioAutenticado;
	}
	
	
	
	public Boolean isURLValidaPorUsuario(Usuario usuario, String url){
		
		Boolean isURLValida = false;
		
		//Se valida si existe algun servicio asociado a la url, para el usuario especificado
		Servicio servicio = UsuarioServicio.getInstance().getServicioUsuarioPorURL(usuario, url);
		
		if(servicio != null){
			isURLValida = true;
			
		}
		
		return isURLValida;
		
		
		
		
	}
	
	public Boolean isAccesoPrivadoValido(HttpServletRequest request){
		
		String url = request.getRequestURI().substring(request.getContextPath().length()+1);
		
		Boolean isAccesoValido = false;
		SessionAppUsuario sessionAppUsuario = null;
		
		//Se valida si la session app de usuario es correcta
		if(generadorSession!= null){
			sessionAppUsuario = generadorSession.getSessionAppUsuario(request);
			
			//Si la session es valida, se verifica el acceso a la url de la peticion
			if(sessionAppUsuario!=null){
				isAccesoValido = isURLValidaPorUsuario(sessionAppUsuario.getUsuario(), url);
				
			}
		}else{
			SimpleLogger.error("NO EXISTE GENERADOR DE SESSION APP REGISTRADO EN AUTENTICADOR CONTROLLER");
			isAccesoValido = false;
			
		}
		
		return isAccesoValido;
		
		
	}
	
		
	//TODO: Se debe crear un ticket dinamico 
	public SessionAppUsuario iniciarSessionUsuario(HttpServletRequest request, String login, String pass, StringBuffer mensajeError){
		
		SessionAppUsuario sessionAppUsuario = null;
		
		SimpleLogger.info("Iniciando autenticacion de usuario");
		
		if(!StringUtils.isEmpty(login) && !StringUtils.isEmpty(pass)){
			
			if(generadorSession!= null){
			
				sessionAppUsuario = this.generadorSession.iniciarSession(request, login, pass);
			}else{
				SimpleLogger.error("NO EXISTE GENERADOR DE SESSION APP REGISTRADO EN AUTENTICADOR CONTROLLER");
				mensajeError.append("Error de aplicacion. No existe generador de session registrado");
			}	
		}else{
			SimpleLogger.info("Informacion de usuario incorrecta");
			mensajeError.append("Error de inicio. Informació de usuario incorrecta");
		}
		
		return sessionAppUsuario;
		
		
	}
	
	public SessionAppUsuario getSessionAppUsuario(HttpServletRequest request){
		return generadorSession.getSessionAppUsuario(request);
	}
	
	
	public void cerrarSession(HttpServletRequest request){
		generadorSession.cerrarSession(request);
	}
		 

	public static void main(String[] args) {
		String claveEncriptada = com.developer.core.utils.StringUtils.MD5("1234");
		System.out.println(claveEncriptada);
		
		
	}
}
