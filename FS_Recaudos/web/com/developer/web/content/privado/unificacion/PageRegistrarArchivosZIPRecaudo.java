package com.developer.web.content.privado.unificacion;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;

public class PageRegistrarArchivosZIPRecaudo extends PrivatePage {
	
	public StringBuffer executeAction(HttpServletRequest request) {
		
		
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = ObjectToXML.getInstance();
		
		//Session de aplicacion 
		SessionAppUsuario sessionAppUsuario = AutenticadorServicio.getInstance().getSessionAppUsuario(request);
		
		if(sessionAppUsuario!=null){
						
			//Servicio
			ProcesoUnificacionArchivosServicio servicio = new ProcesoUnificacionArchivosServicio();
			
			//Se consulta el siguiente id de proceso
			Long prun_prun= servicio.getSiguienteID();
			xmlPage.append("<prun_prun>"+prun_prun+"</prun_prun>");
				
			
		
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
		return AutenticadorServicio.getInstance().isAccesoPrivadoValido(arg0);
	
	}
	


}
