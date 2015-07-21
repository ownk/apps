package com.developer.web.content.privado.inicio;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;
import com.developer.web.general.MensajeErrorWeb;
import com.developer.web.general.PaginatorWeb;

public class PageBienvenida extends PrivatePage{
	
	

	public StringBuffer executeAction(HttpServletRequest request) {
	
		
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();
		MensajeErrorWeb errorWeb = new MensajeErrorWeb();
		// Session de aplicacion
		AutenticadorServicio autenticadorServicio = new AutenticadorServicio();
		SessionAppUsuario sessionAppUsuario = autenticadorServicio.getSessionAppUsuario(request);

		
		if (sessionAppUsuario != null) {
			
			String pageNumberString= request.getParameter("pageNumber");
			String pageSizeString = request.getParameter("pageSize");
			
			Long pageNumberLong = new Long(1);
			Long pageSizeLong = new Long(5);
			
			
			if(pageNumberString!=null){
				pageNumberLong = new Double(pageNumberString).longValue();
				
			}
			
			if(pageSizeString!=null){
				pageSizeLong = new Double(pageSizeString).longValue();
			}
			
			
			ProcesoUnificacionArchivosServicio procesosServicio = new ProcesoUnificacionArchivosServicio();
			
			
			Long totalProcesos = procesosServicio.getTotalProcesos(null);
			List<ProcesoUnificacionArchivos> procesos = procesosServicio.getProcesosUnificacionArchivosPaginado(pageNumberLong, pageSizeLong, false, ProcesoUnificacionArchivos.INICIADO, ProcesoUnificacionArchivos.FINALIZADO, ProcesoUnificacionArchivos.UNIFICANDO_ARCHIVOS);
			
			
			double totalPages = Math.ceil(totalProcesos.doubleValue()/pageSizeLong.doubleValue());
			
			PaginatorWeb paginatorWeb = new PaginatorWeb();
			paginatorWeb.setPageNumber(pageNumberLong);
			paginatorWeb.setPageSize(pageSizeLong);
			paginatorWeb.setTotalPages(Math.round(totalPages));
			paginatorWeb.setTotalSize(totalProcesos);
			paginatorWeb.setSizeIni(1+(pageNumberLong-1)*pageSizeLong);
			paginatorWeb.setSizeFin(procesos.size()+(pageNumberLong-1)*pageSizeLong);
			paginatorWeb.generatePages();
			
			if(procesos!=null){
				xmlPage.append(objectToXML.getXML(procesos));
			}
			
			xmlPage.append(objectToXML.getXML(paginatorWeb));
			
			
		}else {
			
			
			errorWeb.setError(MensajeErrorWeb.ERROR_AUTENTICACION);
			errorWeb.setMensajeError("Usuario no se ha autenticado correctamente ");;
			xmlPage.append(objectToXML.getXML(errorWeb));
						

			
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
