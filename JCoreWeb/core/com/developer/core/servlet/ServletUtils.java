package com.developer.core.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.developer.core.utils.SimpleLogger;

public class ServletUtils {

	
	public static String copyFileItem(String dirName, FileItem fileItem, String optionalFileName) {
		String fileName = fileItem.getName();
		if (optionalFileName == null || optionalFileName.trim().equals("")) {
			fileName = FilenameUtils.getName(fileName);
		} else {
			fileName = optionalFileName;
		}

		new File(dirName).mkdirs();
		String ruta = dirName + "/" + fileName;
		try {
			byte[] buf = new byte[1024];
			InputStream in = fileItem.getInputStream();

			FileOutputStream out = new FileOutputStream(ruta);
			int numRead = 0;
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}
			out.close();

		} catch (Exception e) {
			SimpleLogger.error("Ha ocurrido un error al copiar FileItem", e);
		}
		return ruta;
	}
	
	public static Map<String, Object> getParameters(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
				List<?> fileItemsList = servletFileUpload.parseRequest(request);

				@SuppressWarnings("rawtypes")
				Iterator iter = fileItemsList.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					if (item.isFormField()) {
						map.put(item.getFieldName(), item.getString());
					} else {
						map.put(item.getFieldName(), item);
					}
				}
			}
		} catch (Exception e) {
		}

		return map;
	}

	
}
