package com.developer.web.content.privado.unificacion;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;

public class PageIniciarProcesoUnificacionArchivos extends PrivatePage {

	String nextPage = "/unificacion/PageProcesoUnificacionArchivos";

	public StringBuffer executeAction(HttpServletRequest request) {

		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = ObjectToXML.getInstance();

		// Session de aplicacion
		SessionAppUsuario sessionAppUsuario = AutenticadorServicio
				.getInstance().getSessionAppUsuario(request);

		if (sessionAppUsuario != null) {

			Map<String, Object> parameters = getParameters(request, true);
			ProcesoUnificacionArchivos procesoUnificacionArchivos = (ProcesoUnificacionArchivos) getParameterToObject(
					"ProcesoUnificacionArchivos",
					ProcesoUnificacionArchivos.class, null, parameters);

			// Se consultan los archivos temporales asociados al proceso
			if (procesoUnificacionArchivos != null
					&& procesoUnificacionArchivos.getPrun_prun() != null) {

				StringBuffer mensajeErrorOut = new StringBuffer();

				ArrayList<File> filesZIP = getFilesZIP(procesoUnificacionArchivos
						.getPrun_prun());
				
				Date currentDate = ServerServicio.getInstance().getSysdate();
				
				ProcesoUnificacionArchivosServicio procesoUnificacionArchivosServicio = new ProcesoUnificacionArchivosServicio();
				procesoUnificacionArchivos = procesoUnificacionArchivosServicio
						.iniciarProcesoUnificacionArchivosTransaccional(
								procesoUnificacionArchivos.getPrun_prun(),
								"Inicio de proceso ", currentDate, filesZIP,
								sessionAppUsuario.getUsuario(), mensajeErrorOut);
				
				
				

				if (procesoUnificacionArchivos == null) {
					
					
					String uri = request.getRequestURI();
					String rsname = uri.substring(request.getContextPath().length(), uri.lastIndexOf("."));
					
					this.nextPage = rsname+"?error=2";
					sessionAppUsuario.getHttpSession().removeAttribute(
							"var.error");
					sessionAppUsuario.getHttpSession().setAttribute(
							"var.error", mensajeErrorOut.toString());

					sessionAppUsuario
							.notificarEvento("Error iniciando proceso de unificacion de archivos: "
									+ mensajeErrorOut.toString());
				} else {

					sessionAppUsuario.getHttpSession().setAttribute(
							"var.procesoUnificacionArchivos",
							procesoUnificacionArchivos);

					// Se crea un nuevo mensaje de session
					sessionAppUsuario
							.notificarEvento("Proceso Unificacion Archivos No. "
									+ procesoUnificacionArchivos.getPrun_prun()
									+ " se ha creado con éxito!");

				}
			}

		} else {
			
			
			String uri = request.getRequestURI();
			String rsname = uri.substring(request.getContextPath().length(), uri.lastIndexOf("."));
			
			this.nextPage = rsname+"?error=1";
			

			
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

	private ArrayList<File> getFilesZIP(Long prun_prun) {

		ArrayList<File> filesZIP = new ArrayList<File>();
		String rutaTempArchivos = ProcesoUnificacionArchivosServicio
				.getInstance().getRutaTemporalArchivosZIP(prun_prun);

		File folder = new File(rutaTempArchivos);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {

				String extension = FilenameUtils.getExtension(file.getName());

				if (extension.toUpperCase().equals(
						ArchivoZIPProcesoUnificacion.ZIP)) {
					filesZIP.add(file);
				}

			}
		}

		if (filesZIP.size() > 0) {
			return filesZIP;
		} else {
			return null;
		}

	}

}
