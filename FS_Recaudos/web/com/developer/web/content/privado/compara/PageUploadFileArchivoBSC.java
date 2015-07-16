package com.developer.web.content.privado.compara;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.developer.core.page.PrivatePage;
import com.developer.core.servlet.ServletUtils;
import com.developer.core.utils.ObjectToXML;
import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.compara.dto.ComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.modelo.ArchivoInternetBSCServicio;
import com.developer.logic.modulo.compara.modelo.ComparacionArchivoRecaudoServicio;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;

public class PageUploadFileArchivoBSC extends PrivatePage {

	String nextPage = null;

	public StringBuffer executeAction(HttpServletRequest request) {
		
		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();
		
		Map<String, Object> parameters = getParameters(request, true);
		ComparacionArchivoRecaudo comparacionArchivoRecaudo = (ComparacionArchivoRecaudo) getParameterToObject(
				"ComparacionArchivoRecaudo", ComparacionArchivoRecaudo.class,
				null, parameters);
		
		try {
			
			ArrayList<Object> files = (ArrayList<Object>)parameters.get("file[]");
			
			
			if(comparacionArchivoRecaudo != null && files.size()>0) {
	
				// Se crean los archivos en servidor acorde al numero de proceso
				crearArchivosEnServidor(comparacionArchivoRecaudo.getCpar_cpar(),files);
			
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

	private void crearArchivosEnServidor(Long cpar_cpar, ArrayList<Object> files) {

		try {

			String rutabase = new ComparacionArchivoRecaudoServicio().getRutaTemporalArchivosBSC(cpar_cpar);

			for (Object object : files) {
				FileItem fileItem = (FileItem) object;

				if (fileItem != null) {

					String nombreEnServidor = new ArchivoInternetBSCServicio().getNombreArchivoEnServidor(fileItem);

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
