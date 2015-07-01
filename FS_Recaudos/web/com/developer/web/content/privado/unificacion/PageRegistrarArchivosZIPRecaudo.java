package com.developer.web.content.privado.unificacion;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;

public class PageRegistrarArchivosZIPRecaudo extends PrivatePage {
	
	public StringBuffer executeAction(HttpServletRequest request) {
		
		
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();
		
		//Session de aplicacion 
		AutenticadorServicio autenticadorServicio = new AutenticadorServicio();
		SessionAppUsuario sessionAppUsuario = autenticadorServicio.getSessionAppUsuario(request);
		
		if(sessionAppUsuario!=null){
						
			//Servicio
			ProcesoUnificacionArchivosServicio servicio = new ProcesoUnificacionArchivosServicio();
			
			//Se consulta el siguiente id de proceso
			Long prun_prun= servicio.getSiguienteID();
			xmlPage.append("<prun_prun>"+prun_prun+"</prun_prun>");
				
			Date currenteDate = new ServerServicio().getSysdate();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			xmlPage.append("<prun_fini>"+simpleDateFormat.format(currenteDate)+"</prun_fini>");
		
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
