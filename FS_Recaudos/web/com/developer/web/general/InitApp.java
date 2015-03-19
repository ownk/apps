package com.developer.web.general;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.developer.core.config.GeneralConstants;
import com.developer.core.page.ContextInfo;
import com.developer.core.page.IXMLPageGenerador;
import com.developer.core.page.RouterPage;
import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.modelo.AutenticadorServicio;
import com.developer.logic.modulo.autenticacion.modelo.GeneradorSessionApp;
import com.developer.logic.modulo.autenticacion.modelo.SessionAppUsuario;
import com.developer.logic.modulo.notificaciones.modelo.NotificacionServicio;
import com.developer.mybatis.DBManager;
import com.developer.web.content.jsonrpc.JSONServiceManager;

public class InitApp extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private IXMLPageGenerador publicPageGenerator = null;
	private IXMLPageGenerador privatePageGenerator = null;
	

	// --------------------------

	@Override
	public void init() throws ServletException {
		
		//Se inicializa el contexto
		SimpleLogger.info("Inicializando informacion de contexto");
		ContextInfo.getInstance().autoConfigure(getServletConfig().getServletContext());
		
		//Se inicializan los servicios json que no requieren session
		SimpleLogger.info("Inicializando servicios json staticos");
		JSONServiceManager.geJsonrpcManager().initJsonNonSessionServices();
		
		//Se inicializa la configuracion del gestor de bases de datos
		SimpleLogger.info("Inicializando conexion database");
		DBManager.initConfiguration();
		
		//Se inicializa el controlador de autenticacion
		SimpleLogger.info("Inicializando servicio de autenticacion");
		AutenticadorServicio.getInstance().setGeneradorSession(new GeneradorSessionApp());
		
		//Configuracion del correo
		SimpleLogger.info("Inicializando servicio correo");
		//Mail.configure("smtp.gmail.com", "udistesis@gmail.com", "tesis2011", true, true, "udistesis@gmail.com", "SGPG-UD", "udistesis@gmail.com");
		
		//Iniciar servicio de notificaciones
		SimpleLogger.info("Inicializando servicios de notificacion");
		NotificacionServicio.initService();
		
		SimpleLogger.info("Inicializando generadores contenido xml");
		this.privatePageGenerator = new XMLPrivateGenerator(AutenticadorServicio.getInstance());
		this.publicPageGenerator = new XMLPublicGenerator();
		
		
	}

	// --------------------------

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		executePage(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		executePage(request, response);
		
	}
	
	private void executePage(HttpServletRequest request, HttpServletResponse response){
		
		try {
			RouterPage routerPage;
			routerPage = RouterPage.getRouter(GeneralConstants.WEBPACKAGE, privatePageGenerator, publicPageGenerator, "/denegado/PageAccesoDenegado.pub", "/error/PageError.pub");
			routerPage.routePage(this, request, response);
			
			//Se inicializan servicios json si aun no se han inicializado
			SessionAppUsuario sessionAppUsuario = AutenticadorServicio.getInstance().getSessionAppUsuario(request);
			
			if(sessionAppUsuario!=null){
				JSONServiceManager.geJsonrpcManager().initJSonSessionServices(sessionAppUsuario);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	
	

}
