package com.developer.core.page;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class ContextInfo {

	private static ContextInfo instance = null;

	public static ContextInfo getInstance() {
		if (null == instance) {
			synchronized (ContextInfo.class) {
				if (null == instance) {
					instance = new ContextInfo();
				}
			}
		}
		return instance;
	}

	private String APPLICATION_DISK_BASEPATH = null;

	public void autoConfigure(ServletContext context) {
		String realPath = context.getRealPath("/");
		setRealPath(realPath);
	}

	public void setRealPath(String applicationDiskBasePath) {
		File file = null;
		try {
			file = new File(applicationDiskBasePath);
			APPLICATION_DISK_BASEPATH = file.getAbsolutePath();
		} catch (Exception e) {
		}

		finally {
			try {
				if (null == APPLICATION_DISK_BASEPATH || "".equals(APPLICATION_DISK_BASEPATH)) {
					String defaultPath = (new File(ContextInfo.class.getResource("/").getPath()).getParentFile().getParentFile().getAbsolutePath());
					APPLICATION_DISK_BASEPATH = defaultPath;
				}
			} catch (Exception e) {
			}
		}

	}

	/**
	 * Obtiene la direccion en disco de determinado recurso dentro de la
	 * aplicacion web
	 * 
	 * @param request
	 * @param request
	 * @param relativeWebAppFileName
	 * @return
	 */
	public String getRealPath(HttpServletRequest request) {
		if (null == APPLICATION_DISK_BASEPATH || "".equals(APPLICATION_DISK_BASEPATH)) {
			return null;
		}

		return APPLICATION_DISK_BASEPATH;

	}

	public String getDiskPathForResource(String relativeToContextPath) {
		return getRealPath(null) + System.getProperty("file.separator") + relativeToContextPath;
	}

}
