package com.developer.logic.modulo.notificadoreventos;

import java.util.ArrayList;
import java.util.List;

import com.developer.core.utils.SimpleLogger;

/**
 * Notificador de eventos de la sesión
 * 
 * @author $Author: abcoskar@gmail.com $
 * 
 */
public class NotificadorEventosSesion{

	public static final int MAXIMA_CANTIDAD_EVENTOS = 5; 

	private List<String> listaEventos;
	
	/**
	 * Actualiza el listado de eventos en la sesión
	 */
	public void notificar(String mensaje) {
		
		try {
			
			if (mensaje == null) {
				return;
			}
			if(listaEventos == null ){
				listaEventos = new ArrayList<String>();
			}
			
			int total = listaEventos.size();
			
			
			synchronized (listaEventos) {
				
				if (total == MAXIMA_CANTIDAD_EVENTOS) {
					//Se elimina el elemento mas antiguo
					listaEventos.remove(0);
				}
				
				listaEventos.add(mensaje);
			}
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error actualizando eventos en la sesion");
		}

		
	}

	public List<String> getListaEventos() {
		return listaEventos;
	}
	
	
	
	

}
