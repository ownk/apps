package com.developer.web.content.jsonrpc.services.modulo.unificacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.notificaciones.dto.Notificacion;
import com.developer.logic.modulo.notificaciones.modelo.NotificacionServicio;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;
import com.developer.web.content.jsonrpc.IJsonSessionService;

public class ProcesoUnificacionArchivosJSONServicio implements IJsonSessionService{

	private SessionAppUsuario sessionAppUsuario;
	private NotificacionServicio notificacionServicio;
	
	@Override
	public void setSessionAppUsuario(SessionAppUsuario sessionAppUsuario) {
		this.sessionAppUsuario= sessionAppUsuario;
		this.notificacionServicio = NotificacionServicio.getInstance();
		
	}
	
	public List<ProcesoUnificacionArchivos> getProcesosPorEstadoFechaIniFin(String prun_fini, String prun_ffin) {
	
		
		try {
			
			
				
			Date prun_fini_date = new SimpleDateFormat("dd/MM/yyyy").parse(prun_fini);
			Date prun_ffin_date = new SimpleDateFormat("dd/MM/yyyy").parse(prun_ffin);
				
			ProcesoUnificacionArchivosServicio archivosServicio = new ProcesoUnificacionArchivosServicio();
			return archivosServicio.getProcesosPorAnular(prun_fini_date, prun_ffin_date);
			
		} catch (Exception e) {
			return null;
		}
		
		
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



