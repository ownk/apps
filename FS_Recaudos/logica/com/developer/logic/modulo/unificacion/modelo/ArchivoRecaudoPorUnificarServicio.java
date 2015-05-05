package com.developer.logic.modulo.unificacion.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.logic.modulo.general.modelo.ConfiguracionGeneralServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.logic.modulo.unificacion.dto.HistoricoArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;
import com.developer.persistence.modulo.unificacion.controllerdb.ArchivoRecaudoPorUnificarControllerDB;

public class ArchivoRecaudoPorUnificarServicio {
	
	private static ArchivoRecaudoPorUnificarServicio instance;
	
	public static ArchivoRecaudoPorUnificarServicio getInstance() {
		if (instance == null) {
			instance = new ArchivoRecaudoPorUnificarServicio();
		}
		
		return instance;
	}
	
	/**
	 * ==========================================
	 * CONSULTAS ================================
	 * ==========================================
	 */
	
	public Long getSiguienteID(){
		
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		return controllerDB.getSiguienteID();
		
	}
	
	
	public ArchivoRecaudoPorUnificar getArchivoRecaudo(Long arpu_arpu){
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar = controllerDB.getDocumento(arpu_arpu);
		
		completarInformacionAdicionalArchivo(archivoRecaudoPorUnificar);
		
		return archivoRecaudoPorUnificar;
		
	}
	
	public List<ArchivoRecaudoPorUnificar> getArchivosPorPRUN(Long prun_prun){
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		List<ArchivoRecaudoPorUnificar> list = controllerDB.getArchivosPorPRUN(prun_prun);
		
		for (ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar : list) {
			completarInformacionAdicionalArchivo(archivoRecaudoPorUnificar);
		}
		
		return list;
		
	}
	
	public List<ArchivoRecaudoPorUnificar> getArchivosPorAZPU(Long azpu_azpu){
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		List<ArchivoRecaudoPorUnificar> list = controllerDB.getArchivosPorAZPU(azpu_azpu);
		
		for (ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar : list) {
			completarInformacionAdicionalArchivo(archivoRecaudoPorUnificar);
		}
		
		return list;
		
	}
	
	public String getRutaBaseDeArchivos(){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		
		return rutaGeneral+ "ArchivosAnexosAnteproyecto";
		
	}
	
	
	public List<ArchivoRecaudoPorUnificar> getArchivosTPARxPRUN(Long prun_prun, String tpar_tpar){
		ArchivoRecaudoPorUnificarControllerDB controllerDB = ArchivoRecaudoPorUnificarControllerDB.getInstance();
		List<ArchivoRecaudoPorUnificar> list = controllerDB.getArchivosTPARxPRUN(prun_prun, tpar_tpar);
		
		for (ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar : list) {
			completarInformacionAdicionalArchivo(archivoRecaudoPorUnificar);
		}
		
		return list;
		
		
	}
	
	/**
	 * ==========================================
	 * OPERACIONES TRANSACCIONES ================
	 * ==========================================
	 */
	
	public ArchivoRecaudoPorUnificar crearDocumentoTransaccional(SqlSession session, ArchivoZIPProcesoUnificacion archivoZIPProcesoUnificacion,
			File fileUnZIP, String rutaUnZip, StringBuffer mensajeErrorOut ) {
		
		
		try {
			
		
			boolean sinErrores = true;
			
			
			
			
			String tpar_tpar =FilenameUtils.getExtension(fileUnZIP.getName());
	        TipoArchivoRecaudo tipoArchivoRecaudo = TipoArchivoRecaudoServicio.getInstance().getTipoArchivoRecaudoTransaccional(session, tpar_tpar);
	        
	        if(tipoArchivoRecaudo==null){
	        	
	        	tipoArchivoRecaudo = new TipoArchivoRecaudo();
	    		tipoArchivoRecaudo.setTpar_descri("Nuevo tipo de archivo creado por proceso "+archivoZIPProcesoUnificacion.getAzpu_prun());
	    		tipoArchivoRecaudo.setTpar_tpar(tpar_tpar);
	    		tipoArchivoRecaudo.setTpar_usua(archivoZIPProcesoUnificacion.getAzpu_usua());
	    		tipoArchivoRecaudo.setTpar_estr(TipoArchivoRecaudo.ESTR_FIDUCIARIA);
	        	
	        	
	    		sinErrores = sinErrores && TipoArchivoRecaudoServicio.getInstance().crearTipoArchivoTransaccional(session, tipoArchivoRecaudo);
	        }
	        
	        
	        
	        fileUnZIP = TransformadorArchivoRecaudoServicio.getInstance().hacerTransformacionPorTipoArchivoTransaccional(session, tipoArchivoRecaudo, archivoZIPProcesoUnificacion.getAzpu_prun(), fileUnZIP, rutaUnZip, archivoZIPProcesoUnificacion.getAzpu_usua(), mensajeErrorOut);
	        
			
			
			if(fileUnZIP!=null && fileUnZIP.exists() && sinErrores){
			
			
					Long hash =FileUtils.checksumCRC32(fileUnZIP);
					Long size = fileUnZIP.length();
					Long totalRegistros=new Long(0);
					
										 
			        //Declarar una variable BufferedReader
			        BufferedReader bufferedReader = null;
			        try {
			           //Crear un objeto BufferedReader al que se le pasa 
			           //un objeto FileReader con el nombre del fichero
			        	bufferedReader = new BufferedReader(new FileReader(fileUnZIP));
			        	
			        			        			           
			           //Repetir mientras no se llegue al final del fichero
			           while(bufferedReader.ready())
			           {
			               //Hacer lo que sea con la línea leída
			        	   
			        	  bufferedReader.readLine();
			        	   totalRegistros++;
			           }
			        }
			        catch (FileNotFoundException e) {
			            System.out.println("Error: Fichero no encontrado");
			            System.out.println(e.getMessage());
			            return null;
			        }
			        catch(Exception e) {
			            System.out.println("Error de lectura del fichero");
			            System.out.println(e.getMessage());
			            return null;
			        }
			        finally {
			            try {
			                if(bufferedReader != null)
			                	bufferedReader.close();
			            }
			            catch (Exception e) {
			            	
			                System.out.println("Error al cerrar el fichero");
			                System.out.println(e.getMessage());
			                return null;
			            }
			        }
					
					
			        if(sinErrores){
			        	
			        	
			        	//Se verifica si es necesario h
			        
						ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar = new ArchivoRecaudoPorUnificar();
						archivoRecaudoPorUnificar.setArpu_azpu(archivoZIPProcesoUnificacion.getAzpu_azpu());
						archivoRecaudoPorUnificar.setArpu_arpu(getSiguienteID());
						archivoRecaudoPorUnificar.setArpu_bytes(size.toString());
						archivoRecaudoPorUnificar.setArpu_extension(FilenameUtils.getExtension(fileUnZIP.getName()));
						archivoRecaudoPorUnificar.setArpu_nombre(FilenameUtils.getBaseName(fileUnZIP.getName()));
						archivoRecaudoPorUnificar.setArpu_fcrea(archivoZIPProcesoUnificacion.getAzpu_fcrea());
						archivoRecaudoPorUnificar.setArpu_hash( hash.toString());
						archivoRecaudoPorUnificar.setArpu_observ(archivoZIPProcesoUnificacion.getAzpu_observ());
						archivoRecaudoPorUnificar.setArpu_prun(archivoZIPProcesoUnificacion.getAzpu_prun());
						archivoRecaudoPorUnificar.setArpu_url(fileUnZIP.getAbsolutePath());
						archivoRecaudoPorUnificar.setArpu_usua(archivoZIPProcesoUnificacion.getAzpu_usua());
						archivoRecaudoPorUnificar.setArpu_tpar(tipoArchivoRecaudo.getTpar_tpar());
						archivoRecaudoPorUnificar.setArpu_earpu(ArchivoRecaudoPorUnificar.CARGADO);
						archivoRecaudoPorUnificar.setArpu_registros(totalRegistros);
						
						
						sinErrores = sinErrores &&ArchivoRecaudoPorUnificarControllerDB.getInstance().crearDocumentoTransaccional(session, archivoRecaudoPorUnificar);
						
						
						if(sinErrores){
							
							return archivoRecaudoPorUnificar;
						}else{
							return null;
						}
						
						
			        }else{
			        	
			        	return null;
			        }
			}else{
				return null;
			}
        
		} catch (Exception e) {
			return null;
		}
	}
	
	
	

	private void completarInformacionAdicionalArchivo(ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar){
		try {
			
			if(archivoRecaudoPorUnificar!=null && archivoRecaudoPorUnificar.getArpu_arpu()!=null){
			
				List<HistoricoArchivoRecaudoPorUnificar> historicoArchivoRecaudoPorUnificar= ArchivoRecaudoPorUnificarControllerDB.getInstance().getHistoricoArchivo(archivoRecaudoPorUnificar.getArpu_arpu());
				archivoRecaudoPorUnificar.setHistoricoArchivoRecaudoPorUnificar(historicoArchivoRecaudoPorUnificar);
				
				ArchivoZIPProcesoUnificacionServicio servicioAZIP = ArchivoZIPProcesoUnificacionServicio.getInstance();
				ArchivoZIPProcesoUnificacion archivoZIP = servicioAZIP.getArchivoBasico(archivoRecaudoPorUnificar.getArpu_azpu());
				archivoRecaudoPorUnificar.setArchivoAZPU(archivoZIP);
				
			}
			
			
		} catch (Exception e) {
			SimpleLogger.error("Error consultando informacion adicional Archivo.",e);
		
		}
		
	}
			
	
	 

}
