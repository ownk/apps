package com.developer.web.content.privado.compara;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.compara.modelo.ComparacionArchivoRecaudoServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.modelo.ArchivoRecaudoUnificadoServicio;

public class PageRegistrarArchivoInternetBSC extends PrivatePage {
	
	public StringBuffer executeAction(HttpServletRequest request) {
		
		
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();
		
		//Session de aplicacion 
		AutenticadorServicio autenticadorServicio = new AutenticadorServicio();
		SessionAppUsuario sessionAppUsuario = autenticadorServicio.getSessionAppUsuario(request);
		
	
		if(sessionAppUsuario!=null){
			
			
			String arun_arun = request.getParameter("arun_arun");
						
			//Servicio
			ArchivoRecaudoUnificadoServicio archivoUnificadoServicio = new ArchivoRecaudoUnificadoServicio();
			ComparacionArchivoRecaudoServicio comparacionArchivoRecaudoServicio = new ComparacionArchivoRecaudoServicio();
			
			//Se consulta archivo de recaudo
			ArchivoRecaudoUnificado archivoRecaudoUnificado = archivoUnificadoServicio.getArchivo(Long.parseLong(arun_arun));
			xmlPage.append(objectToXML.getXML(archivoRecaudoUnificado));
			
			//Se consulta el siguiente id de comparacion
			Long cpar_cpar= comparacionArchivoRecaudoServicio.getSiguienteID();
			xmlPage.append("<cpar_cpar>"+cpar_cpar+"</cpar_cpar>");
				
			
		}else{
			xmlPage.append("<error>1</error>");
			xmlPage.append(objectToXML.getXML(new String("Usuario invalido. Por favor inicie session correctamente")));
			
		}
			
		
		return xmlPage;
		
	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccesoValido(HttpServletRequest arg0) {
		return new AutenticadorServicio().isAccesoPrivadoValido(arg0);
	
	}
	


}
