package com.developer.core.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.developer.core.utils.SimpleLogger;

public class ArchivoAdjuntoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session != null) {
			try {
				doUpload(session, request, response);
			} catch (FileUploadException e) {
				SimpleLogger.error("Ha ocurrido un error", e);
			}
		}
	}

	private void doUpload(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {

	    Integer idArchivo = null;
	    Integer respuesta =0;

		try {
			// Leyendo los parametros enviados desde el formulario
			Map<String, Object> param = ServletUtils.getParameters(request);

			// Guardando los archivos subidos a una lista
			FileItem[] archivos = new FileItem[1];
			
			archivos[0] = (FileItem) param.get("archivo1");
			
			String rutabase = "/home/jc/Desarrollo/upload";

			// Si existe el archivo prepararlo para procesamiento
			for (int i = 0; i < archivos.length; i++) {

				String ruta = ServletUtils.copyFileItem(rutabase, archivos[i], "/" + archivos[i].getName() + "" + ".uddoc");

				File file = new File(ruta);
				if (file.exists()) {
					respuesta=1;
				}
			}
			
		
		} catch (Throwable e) {
			respuesta=2;
			SimpleLogger.error("Error Generando pagina desde el servlet CargarArchivoPlanoServlet", e);
		}

		String nextJSP = "/inicio/home2.pub?respuesta="+respuesta;
		
		response.resetBuffer();
		response.setStatus(302);
		response.sendRedirect(request.getContextPath() + nextJSP);

	}

}
