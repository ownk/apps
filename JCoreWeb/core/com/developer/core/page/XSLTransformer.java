package com.developer.core.page;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.cocoon.pipeline.NonCachingPipeline;
import org.apache.cocoon.pipeline.Pipeline;
import org.apache.cocoon.pipeline.component.sax.StringGenerator;
import org.apache.cocoon.pipeline.component.sax.XMLSerializer;
import org.apache.cocoon.pipeline.component.sax.XSLTTransformer;
import org.apache.commons.io.output.ByteArrayOutputStream;

import com.developer.core.config.GeneralConstants;
import com.developer.core.utils.SimpleLogger;
import com.developer.core.utils.XMLFormat;

public class XSLTransformer {
	
	
	public ContentPage executeTransformation(HttpServletRequest request, StringBuffer xmlPage, String typePage, String rsname, String namePage, int outputForm ) throws MalformedURLException, Throwable{
		

		/*
		 * ===========================================================
		 * Contenido que se mostrara en la pagina
		 * ===========================================================
		 */
	
		ContentPage pageContent = new ContentPage();
		
		
		/*
		 * ===========================================================
		 *  TRANSFORMADORES XSL
		 * ===========================================================
		 */
	
		File xslPage = new File(getUriResource(GeneralConstants.WEB_FOLDER+"/"+typePage +"/"+rsname+"/"+namePage+".xslt"));
		if (!xslPage.exists()) {
			throw new RuntimeException("No existe el archivo " + xslPage.getAbsolutePath());
		}
			
		File xslGeneral = new File(getUriResource("app/xhtml.xslt"));
		if (!xslGeneral.exists()) {
			throw new RuntimeException("No existe el archivo " + xslGeneral.getAbsolutePath());
		}
		
		
		/*
		 * ===========================================================
		 *  TRANSFORMACION SEGUN EL TIPO DE SALIDA 
		 * ===========================================================
		 */
		
		if (outputForm == GeneralConstants.OUTPUT_FORM_XHTML) {
			
			/*
			 * =============================================
			 *  Transformacion propia de cada pagina
			 * =============================================
			 */
			
			StringBuffer  xml1 = new StringBuffer(GeneralConstants.XML_HEADER);
			xml1.append("<WEB>");
			xml1.append(xmlPage);
			xml1.append("</WEB>");
			
			String resultTransformationXML = doTransformation(xml1, xslPage.toURI().toURL() );
	
			/*
			 * =============================================
			 *  Transformacion general XTHML
			 * =============================================
			 */
	
			StringBuffer xml2 = new StringBuffer(GeneralConstants.XML_HEADER);
			xml2.append("<WEB>");
			xml2.append(resultTransformationXML.substring(xml1.indexOf("?>") + 2));
			xml2.append("</WEB>");
	
		
			
			String xhtml = doTransformation(xml2, xslGeneral.toURI().toURL());
			xhtml = xhtml.substring(xml1.indexOf("?>") + 2);
			xhtml = GeneralConstants.HTML_DOCTYPE+xhtml;
			pageContent.setContent(xhtml);
			pageContent.setContentType("text/html");
			pageContent.setEnconding(GeneralConstants.ENCONDING);
			
			
	
		} else if (outputForm == GeneralConstants.OUTPUT_FORM_XPAGE) {
			
			byte[] utf8 = new String(xmlPage.toString()).getBytes(Charset.forName(GeneralConstants.ENCONDING));
			String string = new String(utf8);
			
			String xmlFormat = XMLFormat.format(string);
			pageContent.setContent(xmlFormat);
			pageContent.setContentType("application/xml");
			pageContent.setEnconding(GeneralConstants.ENCONDING);
			
			
			
		} else if (outputForm == GeneralConstants.OUTPUT_FORM_XXPAGE) {
			
			/*
			 * =============================================
			 *  Transformacion propia de cada pagina
			 * =============================================
			 */
			
			StringBuffer  xml1 = new StringBuffer(GeneralConstants.XML_HEADER);
			xml1.append("<WEB>");
			xml1.append(xmlPage);
			xml1.append("</WEB>");
			
			String resultTransformationXML = doTransformation(xml1, xslPage.toURI().toURL() );
	
			/*
			 * =============================================
			 *  Transformacion general XTHML
			 * =============================================
			 */
	
			StringBuffer xml2 = new StringBuffer(GeneralConstants.XML_HEADER);
			xml2.append("<WEB>");
			xml2.append(resultTransformationXML.substring(xml1.indexOf("?>") + 2));
			xml2.append("</WEB>");
			
			byte[] utf8 = new String(xml2.toString()).getBytes(Charset.forName(GeneralConstants.ENCONDING));
			String string = new String(utf8);
			
			String xmlFormat = XMLFormat.format(string);
			pageContent.setContent(xmlFormat);
			pageContent.setContentType("application/xml");
			pageContent.setEnconding(GeneralConstants.ENCONDING);
			
			
		} else {
			
			pageContent.setContent("TRANSFORMER ERROR");
			pageContent.setContentType("application/xml");
			pageContent.setEnconding(GeneralConstants.ENCONDING);
		}	
	
		// ---------------------------------------------------------
		return pageContent;
	
	}
	
	/**
	 * 
	 * @param xml documento fuente que sera transformado por los xslts
	 * @param xsltList listado archivos xslt que transformaran el documento xml fuente
	 * 
	 * @return St ring. Documento transformado
	 * @throws Throwable
	 */
	
	private String doTransformation(StringBuffer xml, URL... xsltList) {
		
		try {
			
		Pipeline pipeline = new NonCachingPipeline();
		//PRUEBE
		if (xsltList != null && xsltList.length > 0) {
			for (URL xslt : xsltList) {
				
				byte[] utf8 = new String(xml.toString()).getBytes(Charset.forName(GeneralConstants.ENCONDING));
				String string = new String(utf8);
				pipeline.addComponent(new StringGenerator(string));
				pipeline.addComponent(new XSLTTransformer(xslt));
				
			}
		} 

		pipeline.addComponent(new XMLSerializer());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		pipeline.setup(byteArrayOutputStream);
		pipeline.execute();

		return new String(byteArrayOutputStream.toByteArray(), Charset.forName(GeneralConstants.ENCONDING));
	
		} catch (Exception e) {
			SimpleLogger.error("Error realizando la transformacion de xml", e);
			return null;
		}
		
	}
	
	
	/**
	 * Retorna la ruta absoluta del recurso
	 * 
	 * @param relativeToContextPath  Ruta relativa del recurso
	 * @return String Ruta absoluta
	 */

	private String getUriResource(String relativeToContextPath) {
		return ContextInfo.getInstance().getDiskPathForResource(relativeToContextPath);
	}
	
	
	
	

}
