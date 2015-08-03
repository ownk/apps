package com.developer.web.content.servlet.modulo.conversion;

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
import com.developer.logic.modulo.conversion.modelo.GeneradorArchivoExcelConvertidor;

public class DownloadDocExcelCPARServlet extends HttpServlet {
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

		Long aror_aror = Long.parseLong(request.getParameter("dcpar"));

		GeneradorArchivoExcelConvertidor excelConvertidor = new GeneradorArchivoExcelConvertidor();
		
		StringBuffer mensajeErrorOut = new StringBuffer();
		File fileExcel = excelConvertidor.generarArchivo(aror_aror, mensajeErrorOut);
		

		if(fileExcel!=null){
		
			int length = 0;
			ServletOutputStream op = response.getOutputStream();
			ServletContext context = getServletConfig().getServletContext();
			String mimetype = context.getMimeType(fileExcel.getAbsolutePath());
	
			response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
			response.setContentLength((int) fileExcel.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileExcel.getName() + "\"");
	
			byte[] bbuf = new byte[10000];
			DataInputStream in = new DataInputStream(new FileInputStream(fileExcel));
	
			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}
	
			in.close();
			op.flush();
			op.close();
		
		}
	}

}
