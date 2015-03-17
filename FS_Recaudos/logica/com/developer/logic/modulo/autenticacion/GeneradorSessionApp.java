package com.developer.logic.modulo.autenticacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.general.dto.Persona;
import com.developer.persistence.modulo.general.PersonaControllerDB;


public class GeneradorSessionApp {
	
	public static String TOKEN= "01928384756";
	
	//atributos que se registran en el inicio de session
	public static String SESSION_TOKEN= "SESSION_TOKEN";
	public static String SESSION_USUARIO= "USUA_USUA";
	public static String SESSION_PERSONA= "PERN_PERN";
	public static String SESSION_APP= "SESSION_APP";
	
	
	public SessionAppUsuario iniciarSession(HttpServletRequest request, String login, String pass){
		
		SessionAppUsuario sessionAppUsuario = null;
		
		SimpleLogger.info("Validando usuario: "+login);
		
		//Se valida que el login y password sean correctos
		Usuario  usuario = AutenticadorServicio.getInstance().isUsuarioValido(login, pass);
				
		if(usuario != null){
			HttpSession session = null;
			
			//Se crea la session
			if(request.getSession(false)!= null){
				
				SimpleLogger.info("Session ya iniciada. Se invalida session y se crea nueva session");
				
				request.getSession().invalidate();
				session = request.getSession();
									
				
			}else{
				//Se crea la session y se agrega parametro de inicio
				SimpleLogger.info("No existe session. Se crea nueva session");
				session = request.getSession();
				
			}
			
			//Se registran los parametros de inicio
			sessionAppUsuario = generarSessionAppUsuario(session, usuario);
			
			//Si la sessionUsuario no fue generada se debe invalidar la session http
			if(sessionAppUsuario== null){
				session.invalidate();
			}
		}else{
			
			SimpleLogger.info("Usuario invalido");
		}
		
		return sessionAppUsuario;
		
	}
	
	private SessionAppUsuario generarSessionAppUsuario(HttpSession session, Usuario usuario){
		SessionAppUsuario sessionUsuario = null;
		
		SimpleLogger.info("Registrando Parametros de inicio de session.");
		
		//Se agrega el token para identificar que la session es controlada
		session.setAttribute(SESSION_TOKEN, TOKEN);
		
		//Se agrega el usuario en la session
		session.setAttribute(SESSION_USUARIO, usuario);
		
		//Se consulta la persona asociada al usuario
		Persona persona = PersonaControllerDB.getInstance().getPersonaPorUsuario(usuario);
		if(persona!= null){
			session.setAttribute(SESSION_PERSONA, persona);
			sessionUsuario = new SessionAppUsuario(session, usuario, persona );
			
			session.setAttribute(SESSION_APP,sessionUsuario);
			
			
		}else{
			SimpleLogger.error("Error registrando parametros de inicio de sesion. No existe persona asociada al usuario");
			
		}
		
		return sessionUsuario;
	}
	
	
	public SessionAppUsuario getSessionAppUsuario(HttpServletRequest request){

		SessionAppUsuario sessionAppUsuario= null;
		
		HttpSession session = request.getSession(false);
		
		//Se revisa si ya existe session
		if(session!=null){
			
			//Se revisa si los parametros de inicio correctos
			String sessionInit= (String)session.getAttribute(GeneradorSessionApp.SESSION_TOKEN);
			if(sessionInit!= null && sessionInit.equals(TOKEN)){
				
				Usuario  usuario = (Usuario)session.getAttribute(SESSION_USUARIO);
				if(usuario!=null){
					
					Persona persona = (Persona)session.getAttribute(SESSION_PERSONA);
					if(persona != null){
						
							 sessionAppUsuario = (SessionAppUsuario)session.getAttribute(GeneradorSessionApp.SESSION_APP);
							 if(sessionAppUsuario!=null){
								 sessionAppUsuario.setHttpSession(session);
								 sessionAppUsuario.setPersona(persona);
								 sessionAppUsuario.setUsuario(usuario);
								 
							 }
						
						
						
					}else{
						SimpleLogger.info("Session APP INVALIDA. Persona no registrado");
					}
					
				}else{
					
					SimpleLogger.info("Session APP INVALIDA. Usuario no registrado");
				}
				
				
			}
		}
		
		return sessionAppUsuario;
		
	}
	
	
	
	
	
}
