package com.developer.web.content.privado.unificacion;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.developer.core.page.PrivatePage;
import com.developer.core.servlet.ServletUtils;
import com.developer.core.utils.ObjectToXML;
import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ArchivoZIPProcesoUnificacionServicio;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;

public class PageUploadFilesProcesoUnificacion extends PrivatePage {

	String nextPage = null;

	public StringBuffer executeAction(HttpServletRequest request) {
		
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();
		
		Map<String, Object> parameters = getParameters(request, true);
		ProcesoUnificacionArchivos procesoUnificacionArchivos = (ProcesoUnificacionArchivos) getParameterToObject(
				"ProcesoUnificacionArchivos", ProcesoUnificacionArchivos.class,
				null, parameters);
		
		try {
			
			ArrayList<Object> files = (ArrayList<Object>)parameters.get("file[]");
			
			
			if(procesoUnificacionArchivos != null && files.size()>0) {
	
				// Se crean los archivos en servidor acorde al numero de proceso
				crearArchivosEnServidor(procesoUnificacionArchivos.getPrun_prun(),files);
			
			}
		
		} catch (Exception e) {
			xmlPage.append("<error>1</error>");
			xmlPage.append(objectToXML.getXML(new String("Error Creando archivos en servidor")));
		}
		
		return null;

	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return nextPage;
	}

	@Override
	public boolean isAccesoValido(HttpServletRequest arg0) {
		return new AutenticadorServicio().isAccesoPrivadoValido(arg0);

	}

	private void crearArchivosEnServidor(Long prun_prun, ArrayList<Object> files) {

		try {

			String rutabase = new ProcesoUnificacionArchivosServicio().getRutaTemporalArchivosZIP(prun_prun);

			for (Object object : files) {
				FileItem fileItem = (FileItem) object;

				if (fileItem != null) {

					String nombreEnServidor = new ArchivoZIPProcesoUnificacionServicio().getNombreArchivoEnServidor(fileItem);

					ServletUtils.copyFileItem(rutabase, fileItem,
							nombreEnServidor);

					

				}

			}

		} catch (Throwable e) {
			SimpleLogger
					.error("Error obteniendo la informacion archivo zip", e);

		}

	}

}
