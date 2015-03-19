package com.developer.web.content.privado.inicio;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.developer.core.page.PrivatePage;
import com.developer.core.servlet.ServletUtils;
import com.developer.core.utils.SimpleLogger;

public class PageUploadFile extends PrivatePage{
	
	String nextPage = null;
	public StringBuffer executeAction(HttpServletRequest request) {
		ArrayList<Object> files = new ArrayList<Object>();
		
		if (ServletFileUpload.isMultipartContent(request)) {
			
			ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
			List<?> fileItemsList;
			try {
				fileItemsList = servletFileUpload.parseRequest(request);
				@SuppressWarnings("rawtypes")
				Iterator iter = fileItemsList.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					if (item.getFieldName().equals("file[]")) {
						files.add(item);
					
					}
				}
				
				createFiles(files);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
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
		// TODO Auto-generated method stub
		return true;
	}

	private void createFiles(ArrayList<Object> files){
		
		try {
			
			for (Object object : files) {
				FileItem fileItem = (FileItem) object;

				String rutabase =  "D:\\Proyectos\\Ownk\\Github\\apps\\FS_Recaudos";
			
				if (fileItem != null) {

					
					String nombreReal = FilenameUtils.getBaseName(fileItem.getName());
					String extension = FilenameUtils.getExtension(fileItem.getName());
					String nombreEnServidor = nombreReal+extension;
					
					String ruta = ServletUtils.copyFileItem(rutabase, fileItem, "/"+nombreEnServidor);
					
					Long longBytes =  fileItem.getSize();
					File file = new File(ruta);
					System.out.println(file.exists());
					Long checkSum = FileUtils.checksumCRC32(file); 
					
				}	
				
			}
			
			
			
			

		} catch (Throwable e) {
			SimpleLogger.error("Error obteniendo la informacion del docunmento de prepropuesta", e);
			
			
		}
		
		
	}
	

}
