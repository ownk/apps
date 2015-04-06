package com.developer.web.content.privado.unificacion;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;

import com.developer.core.page.PrivatePage;
import com.developer.core.utils.ObjectToXML;
import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;
import com.developer.logic.modulo.unificacion.modelo.UnificadorArchivosPorProcesoServicio;
import com.developer.web.general.MensajeErrorWeb;

public class PageIniciarProcesoUnificacionArchivos extends PrivatePage {

	String nextPage = null;
	MensajeErrorWeb errorWeb = new MensajeErrorWeb();

	public StringBuffer executeAction(HttpServletRequest request) {

		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = ObjectToXML.getInstance();

		// Session de aplicacion
		SessionAppUsuario sessionAppUsuario = AutenticadorServicio
				.getInstance().getSessionAppUsuario(request);

		StringBuffer mensajeErrorOut = new StringBuffer();
		
		if (sessionAppUsuario != null) {

			Map<String, Object> parameters = getParameters(request, true);
			ProcesoUnificacionArchivos procesoUnificacionArchivos = (ProcesoUnificacionArchivos) getParameterToObject(
					"ProcesoUnificacionArchivos",
					ProcesoUnificacionArchivos.class, null, parameters);

			// Se consultan los archivos temporales asociados al proceso
			if (procesoUnificacionArchivos != null
					&& procesoUnificacionArchivos.getPrun_prun() != null) {

				
		
				
				
				ArrayList<File> filesZIP = getFilesZIP(procesoUnificacionArchivos
						.getPrun_prun());
				
				
				if(filesZIP!= null && filesZIP.size()>0){
				
					Date currentDate = ServerServicio.getInstance().getSysdate();
					String observacion = null;
					
					if(procesoUnificacionArchivos.getPrun_observ()== null || procesoUnificacionArchivos.getPrun_observ().isEmpty()){
						observacion = "Inicio Proceso No "+procesoUnificacionArchivos.getPrun_prun();
					}else{
						observacion = procesoUnificacionArchivos.getPrun_observ();
					}
					
					ProcesoUnificacionArchivosServicio procesoUnificacionArchivosServicio = new ProcesoUnificacionArchivosServicio();
					procesoUnificacionArchivos = procesoUnificacionArchivosServicio
							.iniciarProcesoUnificacionArchivosTransaccional(
									procesoUnificacionArchivos.getPrun_prun(),
									observacion, currentDate, filesZIP, 
									procesoUnificacionArchivos.getPrun_fini(), 
									procesoUnificacionArchivos.getPrun_ffin(),
									sessionAppUsuario.getUsuario(), mensajeErrorOut);
					
					
					
	
					if (procesoUnificacionArchivos == null) {
						
						
						
					sessionAppUsuario
								.notificarEvento("Error iniciando proceso de unificacion de archivos: "
										+ mensajeErrorOut.toString());
						
						
						errorWeb.setError("1");
						errorWeb.setMensajeError(mensajeErrorOut.toString());
						
						xmlPage.append(objectToXML.getXML(errorWeb));
						
					} else {
	
						
						// Se crea un nuevo mensaje de session
						sessionAppUsuario
								.notificarEvento("Proceso Unificacion Archivos No. "
										+ procesoUnificacionArchivos.getPrun_prun()
										+ " se ha creado con éxito!");
						
						
						xmlPage.append(objectToXML.getXML(procesoUnificacionArchivos));
						
						//Se procede a crear la unificacion de archivos de forma automatica
						UnificadorArchivosPorProcesoServicio unificadorArchivosPorProcesoServicio = new UnificadorArchivosPorProcesoServicio();
						Boolean sinErrores = unificadorArchivosPorProcesoServicio.generarArchivosUnificadosPorProceso(procesoUnificacionArchivos, sessionAppUsuario.getUsuario(), mensajeErrorOut);
						
						if(sinErrores){

							// Se crea un nuevo mensaje de session
							sessionAppUsuario
									.notificarEvento("La unificacion de Archivos No. "
											+ procesoUnificacionArchivos.getPrun_prun()
											+ " se ha creado con éxito!");
		
						}
						
						//Se procede a eliminar archivos temporales
						try {
							procesoUnificacionArchivosServicio.eliminarArchivosTemporalesPorProceso(procesoUnificacionArchivos.getPrun_prun());
							
							
						} catch (Exception e) {
							SimpleLogger.warn("Warning: NO se ha podido eliminar el archivo temporal proceso "+procesoUnificacionArchivos.getPrun_prun(), e);
						}
						
	
					}
				}else{
					
					mensajeErrorOut.append("No existen archivos asociados al proceso ");
					
					
					sessionAppUsuario
							.notificarEvento("Error iniciando proceso de unificacion de archivos: "
									+ mensajeErrorOut.toString());
					
					errorWeb.setError("2");
					errorWeb.setMensajeError(mensajeErrorOut.toString());
					
					xmlPage.append(objectToXML.getXML(errorWeb));
					
				}
			}else{
				
				mensajeErrorOut.append("No se encontro identificador de proceso ");
				
				sessionAppUsuario
						.notificarEvento("Error iniciando proceso de unificacion de archivos: "
								+ mensajeErrorOut.toString());
				
				errorWeb.setError("3");
				errorWeb.setMensajeError(mensajeErrorOut.toString());
				
				xmlPage.append(objectToXML.getXML(errorWeb));
				
			}

		} else {
			
			mensajeErrorOut.append("Usuario no se ha autenticado correctamente ");


			errorWeb.setError(MensajeErrorWeb.ERROR_AUTENTICACION);
			errorWeb.setMensajeError(mensajeErrorOut.toString());;
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
						ArchivoZIPProcesoUnificacion.ZIP) || extension.toUpperCase().equals(ArchivoZIPProcesoUnificacion.RAR)) {
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
