package com.developer.web.content.servlet.modulo.unificacion;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.modelo.ArchivoRecaudoUnificadoServicio;

public class DownloadDocARUNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doDownload(request, response);
		} catch (Exception e) {
			SimpleLogger.error("Ha ocurrido un error", e);
		}
	}

	private void doDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long arun_arun = Long.parseLong(request.getParameter("darun"));

		ArchivoRecaudoUnificado arun = ArchivoRecaudoUnificadoServicio.getInstance().getArchivo(arun_arun);
		
		String original_filename = arun.getArun_nombre()+"."+arun.getArun_extension();
		
		String filename = arun.getArun_url();
		File file = new File(filename);

		
		int length = 0;
		ServletOutputStream op = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		String mimetype = context.getMimeType(filename);

		response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + original_filename + "\"");

		byte[] bbuf = new byte[10000];
		DataInputStream in = new DataInputStream(new FileInputStream(file));

		while ((in != null) && ((length = in.read(bbuf)) != -1)) {
			op.write(bbuf, 0, length);
		}

		in.close();
		op.flush();
		op.close();
	}

}
