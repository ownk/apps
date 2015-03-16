package com.developer.core.page;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.developer.core.config.GeneralConstants;

public class RouterPage {
	
	private static RouterPage enrutador;
	private XMLPageExecuter xmlPageGenerator;
	private XSLTransformer xslTransformer;
	private IXMLPageGenerador privatePageGenerador;
	private IXMLPageGenerador publicPageGenerador;
	
	private String webpackage;
	
	private RouterPage (String webPackageInit, IXMLPageGenerador privatePageGenerator, IXMLPageGenerador publicPageGenerador){
		
		xmlPageGenerator = new XMLPageExecuter();
		xslTransformer = new XSLTransformer();
		this.webpackage = webPackageInit;
		this.privatePageGenerador = privatePageGenerator;
		this.publicPageGenerador = publicPageGenerador;
		
		
	}
	
	/**
	 * 
	 * 
	 * @param webPackageInit paquete donde se encuentran los controladores de las paginas publicas y privadas. Este paquete debe tener dos paquetes adicionales
	 * 						 privatepage y pubicpage asi:
	 * 		
	 * 						 webPackageInit: com.developer.web.content
	 * 						 privatepage: com.developer.web.content.privatepage
	 * 						 publicpage: com.developer.web.content.publicpage
	 * 
	 * @param privatePageGenerator: Entidad encargada de generar el xml general de una pagina privada
	 * 
	 * @param publicPageGenerator: Entidad encargada de generar el xml general de una pagina publica
	 * 						
	 * 
	 * @return
	 * @throws Exception 
	 */
	
	public static RouterPage getRouter(String webPackageInit, IXMLPageGenerador privatePageGenerator, IXMLPageGenerador publicPageGenerator) throws Exception{
		
		if(webPackageInit== null || privatePageGenerator == null || publicPageGenerator == null ){
		
			throw new Exception("Los parametros webPackageInit, privatePageGenerator, publicPageGenerator no pueden ser nulos ");
		}else{
		
			if(enrutador == null) {
				enrutador = new RouterPage(webPackageInit, privatePageGenerator, publicPageGenerator);
			
			}
			
			return enrutador;
		}
	}
	
	/**
	 * Se ejecuta la peticion y segun el tipo de extencion de pagina se hace el redireccionamiendo correspondiente. Las
	 * extenciones de pagina publica que estan soportadas son .pub, .xpub, .xxpub; para paginas privadas se soportan las
	 * extenciones .do, .xdo, .xxdo 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	
	public void routePage(HttpServlet httpServlet, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		

		String uri = request.getRequestURI();

		/*
		 * ===========================================================
		 *  PAGINAS PUBLICAS
		 * ===========================================================
		 */
		if (uri.endsWith(".pub")) {
			executePage(GeneralConstants.PUBLIC_PAGE, request, response, GeneralConstants.OUTPUT_FORM_XHTML);
			return;
		}
		
		if (uri.endsWith(".xpub")) {
			executePage(GeneralConstants.PUBLIC_PAGE, request, response, GeneralConstants.OUTPUT_FORM_XPAGE);
			return;
			
		}
		
		if (uri.endsWith(".xxpub")) {
			executePage(GeneralConstants.PUBLIC_PAGE, request, response, GeneralConstants.OUTPUT_FORM_XXPAGE);
			return;
		}

		/*
		 * ===========================================================
		 *  PAGINAS PRIVADAS
		 * ===========================================================
		 */
		if (uri.endsWith(".do")) {
			executePage(GeneralConstants.PRIVATE_PAGE, request, response, GeneralConstants.OUTPUT_FORM_XHTML);
			return;
		}
		if (uri.endsWith(".xdo")) {
			executePage(GeneralConstants.PRIVATE_PAGE, request, response, GeneralConstants.OUTPUT_FORM_XPAGE);
			return;
			
		}
		if (uri.endsWith(".xxdo")) {
			executePage(GeneralConstants.PRIVATE_PAGE, request, response, GeneralConstants.OUTPUT_FORM_XXPAGE);
			return;
		}

		/*
		 * ===========================================================
		 *  RECURSOS
		 * ===========================================================
		 */
		executeResource(httpServlet, request, response);
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param typePage
	 * @param request
	 * @param response
	 * @param outputForm
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void executePage(String typePage, HttpServletRequest request, HttpServletResponse response, int outputForm) throws ServletException, IOException {

		
		
		//PrintWriter out = response.getWriter();
		
		PrintWriter out = new PrintWriter( new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);

		try {

			
			String uri = request.getRequestURI();
			String rsname = uri.substring(request.getContextPath().length(), uri.lastIndexOf("."));
			StringBuffer outputExecutionPage = null;
			
			
			/*
			 * ===========================================================
			 *  CONTROLADOR DE CADA PAGINA
			 * ===========================================================
			 */
	
			String pname = webpackage + typePage + rsname.replace('/', '.');
			String packagename = pname.substring(0, pname.lastIndexOf("."));
			String simpleclassname = pname.substring(packagename.length() + 1);
			String namePage = simpleclassname.substring(0, 1).toUpperCase() + simpleclassname.substring(1);
			String classname = packagename + "." + namePage;
			
			Class<?> classPage = Class.forName(classname);

			String nextPage = null;
			StringBuffer xmlPage = null;
			
			/*
			 * ===========================================================
			 *  EXECUTE PUBLIC PAGE
			 * ===========================================================
			 */
			
			
			if (typePage.equalsIgnoreCase(GeneralConstants.PUBLIC_PAGE)) {
				PublicPage page = (PublicPage) classPage.newInstance();

				if (page.isAccesoValido(request) || outputForm != GeneralConstants.OUTPUT_FORM_XHTML) {
					outputExecutionPage = page.executeAction(request);
					xmlPage = xmlPageGenerator.getXMLPage(request, publicPageGenerador, outputExecutionPage);
					
				} else {
					out.println("acceso denegado");
					return;
				}

				nextPage = page.getNextPage();
			}

			/*
			 * ===========================================================
			 *  EXECUTE PRIVATE PAGE
			 * ===========================================================
			 */
			
			if (typePage.equalsIgnoreCase(GeneralConstants.PRIVATE_PAGE)) {
				PrivatePage page = (PrivatePage) classPage.newInstance();

				if (page.isAccesoValido(request) || outputForm != GeneralConstants.OUTPUT_FORM_XHTML) {
					outputExecutionPage = page.executeAction(request);
					xmlPage = xmlPageGenerator.getXMLPage(request, privatePageGenerador, outputExecutionPage);
					
				} else {
					out.println("acceso denegado");
					return;
				}

				nextPage = page.getNextPage();
			}

			/*
			 * ===========================================================
			 *  NEXT PAGE
			 * ===========================================================
			 */
			if (nextPage != null) {

				if (nextPage.charAt(0) != '/') {
					nextPage = "/" + nextPage;
				}

				response.resetBuffer();
				response.setStatus(302);
				response.sendRedirect(request.getContextPath() + nextPage);
				return;
			}

			/*
			 * ===========================================================
			 *  XSL TRANSFORMATION
			 * ===========================================================
			 */
			
			ContentPage pageContent = xslTransformer.executeTransformation(request, xmlPage, typePage, rsname, namePage, outputForm);
			
			
			/*
			 * ===========================================================
			 *  SHOW PAGE
			 * ===========================================================
			 */
						
			if(pageContent.getEnconding()!=null){
				response.setCharacterEncoding(pageContent.getEnconding());
			}
			response.setContentType(pageContent.getContentType());
			out.println(pageContent.getContent());
			

		} catch (Throwable e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}
	}

	

	/**
	 * Retorna un recurso: libreria, css, image etc
	 * 
	 * 
	 * @param httpServlet
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */

	private void executeResource(HttpServlet httpServlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String uri = req.getRequestURI();
			String res = uri.substring(req.getContextPath().length());

			if (res.length() == 0 || res.equalsIgnoreCase("/")) {
				res = "../index.html";
			}

			File file = new File(ContextInfo.getInstance().getDiskPathForResource(GeneralConstants.WEB_FOLDER+"/"+res));

			if (file.exists() && file.isFile()) {

				if (file.getName().endsWith(".html")) {

					resp.setContentType("text/html");
					PrintWriter out = resp.getWriter();
					out.println(FileUtils.readFileToString(file));

				} else {
					
					ServletOutputStream op = resp.getOutputStream();
					ServletContext context = httpServlet.getServletContext();
					String mimetype = context.getMimeType(file.getName());
					resp.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
					resp.setContentLength((int) file.length());

					resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
					
					
					byte[] bbuf = new byte[10000];
					DataInputStream in = new DataInputStream(new FileInputStream(file));

					int length = 0;
					while ((in != null) && ((length = in.read(bbuf)) != -1)) {
						op.write(bbuf, 0, length);
					}
					in.close();
					op.flush();
					op.close();
					
				}
			} else {
				PrintWriter out = resp.getWriter();
				out.println("Recurso No disponible: " + file.getAbsolutePath());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	

}
