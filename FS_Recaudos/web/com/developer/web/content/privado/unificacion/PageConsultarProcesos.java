package com.developer.web.content.privado.unificacion;

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

public class PageConsultarProcesos extends PrivatePage{

	@Override
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
			Long pageSizeLong = new Long(10);
			
			
			if(pageNumberString!=null){
				pageNumberLong = new Double(pageNumberString).longValue();
				
			}
			
			if(pageSizeString!=null){
				pageSizeLong = new Double(pageSizeString).longValue();
			}
			
			
			ProcesoUnificacionArchivosServicio procesosServicio = new ProcesoUnificacionArchivosServicio();
			
			
			Long totalProcesos = procesosServicio.getTotalProcesos(null);
			
			if(totalProcesos>PaginatorWeb.MAX_SIZE){
				totalProcesos =  new Long(PaginatorWeb.MAX_SIZE);
			}
			
			List<ProcesoUnificacionArchivos> procesos = procesosServicio.getProcesosUnificacionArchivosPaginado(pageNumberLong, pageSizeLong, null);
			
			
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
	public boolean isAccesoValido(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new AutenticadorServicio().isAccesoPrivadoValido(request);
	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		
		Long total = new Long(19);
		Long size = new Long(10);
		double pages = Math.ceil(total.doubleValue()/size.doubleValue());
		System.out.println(pages);
	}
	
}
