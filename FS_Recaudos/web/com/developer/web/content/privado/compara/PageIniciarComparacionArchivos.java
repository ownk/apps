package com.developer.web.content.privado.compara;

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
import com.developer.logic.modulo.compara.dto.ArchivoInternetBSC;
import com.developer.logic.modulo.compara.dto.ComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.modelo.ComparacionArchivoRecaudoServicio;
import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.web.general.MensajeErrorWeb;

public class PageIniciarComparacionArchivos extends PrivatePage {

	String nextPage = null;
	MensajeErrorWeb errorWebComparacion = new MensajeErrorWeb();


	public StringBuffer executeAction(HttpServletRequest request) {

		StringBuffer xmlPage = new StringBuffer();
		ObjectToXML objectToXML = new ObjectToXML();

		// Session de aplicacion
		AutenticadorServicio autenticadorServicio = new AutenticadorServicio();
		SessionAppUsuario sessionAppUsuario = autenticadorServicio.getSessionAppUsuario(request);

		StringBuffer mensajeErrorOut = new StringBuffer();
		
		if (sessionAppUsuario != null) {

			Map<String, Object> parameters = getParameters(request, true);
			
			
			ComparacionArchivoRecaudo comparacionArchivoRecaudo= (ComparacionArchivoRecaudo) getParameterToObject(
					"ComparacionArchivoRecaudo",
					ComparacionArchivoRecaudo.class, null, parameters);

			// Se consultan los archivos temporales asociados
			if (comparacionArchivoRecaudo != null
					&& comparacionArchivoRecaudo.getCpar_arun() != null
					&& comparacionArchivoRecaudo.getCpar_cpar() != null
					&& comparacionArchivoRecaudo.getCpar_observ() !=null) {

				
		
				
				
				File excelFile = getExcelFile(comparacionArchivoRecaudo
						.getCpar_cpar());
				
				
				if(excelFile!= null){
				
					Date currentDate = new ServerServicio().getSysdate();
					
					ComparacionArchivoRecaudoServicio comparacionArchivoRecaudoServicio = new ComparacionArchivoRecaudoServicio();
					comparacionArchivoRecaudo = comparacionArchivoRecaudoServicio.iniciarComparacionTransaccional(	comparacionArchivoRecaudo.getCpar_arun(), 
																													comparacionArchivoRecaudo.getCpar_cpar(), 
																													comparacionArchivoRecaudo.getCpar_observ(), 
																													currentDate, 
																													excelFile, 
																													sessionAppUsuario.getUsuario(), 
																													mensajeErrorOut);
					
					
					
	
					if (comparacionArchivoRecaudo == null) {
						
						
						
						sessionAppUsuario
								.notificarEvento("Error iniciando proceso de unificacion de archivos: "
										+ mensajeErrorOut.toString());
						
						
						errorWebComparacion.setError("1");
						errorWebComparacion.setMensajeError(mensajeErrorOut.toString());
						
						xmlPage.append(objectToXML.getXML(errorWebComparacion));
						
					} else {
	
						
						// Se crea un nuevo mensaje de session
						sessionAppUsuario
								.notificarEvento("Comparacion Archivos No. "
										+ comparacionArchivoRecaudo.getCpar_cpar()
										+ " se ha creado con éxito!");
						
						
						xmlPage.append(objectToXML.getXML(comparacionArchivoRecaudo));
						
						
						
	
					}
				}else{
					
					mensajeErrorOut.append("No existen archivos asociados al proceso ");
					
					
					sessionAppUsuario
							.notificarEvento("Error iniciando proceso de unificacion de archivos: "
									+ mensajeErrorOut.toString());
					
					errorWebComparacion.setError("2");
					errorWebComparacion.setMensajeError(mensajeErrorOut.toString());
					
					xmlPage.append(objectToXML.getXML(errorWebComparacion));
					
				}
			}else{
				
				mensajeErrorOut.append("No se encontro identificador de proceso ");
				
				sessionAppUsuario
						.notificarEvento("Error iniciando comparacion de archivos: "
								+ mensajeErrorOut.toString());
				
				errorWebComparacion.setError("3");
				errorWebComparacion.setMensajeError(mensajeErrorOut.toString());
				
				xmlPage.append(objectToXML.getXML(errorWebComparacion));
				
			}

		} else {
			
			mensajeErrorOut.append("Usuario no se ha autenticado correctamente ");


			errorWebComparacion.setError(MensajeErrorWeb.ERROR_AUTENTICACION);
			errorWebComparacion.setMensajeError(mensajeErrorOut.toString());;
			xmlPage.append(objectToXML.getXML(errorWebComparacion));
						

			
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

	private File getExcelFile(Long cpar_cpar) {

		ArrayList<File> filesZIP = new ArrayList<File>();
		String rutaTempArchivos = new ComparacionArchivoRecaudoServicio().getRutaTemporalArchivosBSC(cpar_cpar);

		File folder = new File(rutaTempArchivos);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {

				String extension = FilenameUtils.getExtension(file.getName());

				if (extension.toUpperCase().equals(
						ArchivoInternetBSC.XLS) || extension.toUpperCase().equals(ArchivoInternetBSC.XLSX)) {
					filesZIP.add(file);
				}

			}
		}

		if (filesZIP.size() > 0) {
			return filesZIP.get(0);
		} else {
			return null;
		}

	}

}
