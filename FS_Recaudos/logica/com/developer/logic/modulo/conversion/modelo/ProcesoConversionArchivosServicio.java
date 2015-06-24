package com.developer.logic.modulo.conversion.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.conversion.dto.HistoricoProcesoConversionArchivos;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.logic.modulo.general.dto.Persona;
import com.developer.logic.modulo.general.modelo.ConfiguracionGeneralServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.conversion.controllerdb.ProcesoConversionArchivosControllerDB;
import com.developer.persistence.modulo.conversion.mapper.dao.ProcesoConversionArchivosDao;
import com.developer.persistence.modulo.general.controllerdb.PersonaControllerDB;


public class ProcesoConversionArchivosServicio {
	
	
	private static ProcesoConversionArchivosServicio instance;
	
	public static ProcesoConversionArchivosServicio getInstance() {
		if (instance == null) {
			instance = new ProcesoConversionArchivosServicio();
		}
		
		return instance;
	}
	
	/**
	 * ======================================
	 * TRANSACCIONES ========================
	 * ======================================
	 */
	

		
	public ProcesoConversionArchivos iniciarProcesoConversionArchivosTransaccional(Long prun_prun,  String observacionDeInicio, Date currentDate, ArrayList<ArchivoRecaudoUnificado> files,  Date prco_fini, Date prco_ffin, Usuario usuario, StringBuffer mensajeErrorOut){
		
		
		SqlSession session = DBManagerFSRecaudos.openSession();
		ProcesoConversionArchivos procesoConversionArchivosIniciada= null;
		
		try {
			Boolean sinErrores = true;
			
			//Se valida la informacion
			if(observacionDeInicio==null){
				String error= "Error iniciando procesoConversionArchivos. No se ha especificado correctamente el titulo la observación de inicio del procesoConversionArchivos.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(prun_prun==null){
				String error= "Error iniciando procesoConversionArchivos. No se ha especificado el proceso que se desea iniciar como procesoConversionArchivos.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(usuario==null){
				String error= "Error iniciando procesoConversionArchivos. No se ha especificado el solicitante para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(currentDate==null){
				String error= "Error iniciando procesoConversionArchivos. No se ha especificado la fecha para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(files==null){
				String error= "Error iniciando procesoConversionArchivos. No se ha especificado los archivos para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(prco_fini==null){
				String error= "Error iniciando procesoConversionArchivos. No se ha especificado la fecha inicio desde la cual se hara la unificacion.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(prco_ffin==null){
				String error= "Error iniciando procesoConversionArchivos. No se ha especificado la fecha fin hsta la cual se hara la unificacion.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			//Se verifica que no existan errores para crear el procesoConversionArchivos
			if(sinErrores){
				ProcesoConversionArchivos procesoConversionArchivos = ProcesoConversionArchivosControllerDB.getInstance().iniciarProcesoConversionArchivosTransaccional(	session, 
																														prun_prun,
																														observacionDeInicio,
																														prco_fini,
																														prco_ffin,
																														currentDate,
																														usuario, 
																														mensajeErrorOut);
				
				
			
				if(procesoConversionArchivos!= null ){
						
				}
								
				
			}else{
				session.rollback();
				SimpleLogger.error("Error creando procesoConversionArchivos. No ha sido posible crear la información del Formulario de ProcesoConversionArchivos para el inicio del mismo");
				mensajeErrorOut.append("Error creando procesoConversionArchivos. No ha sido posible crear la información del Formulario de ProcesoConversionArchivos para el inicio del mismo");
				
			}
			
			return procesoConversionArchivosIniciada;
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			session.rollback();
			mensajeErrorOut.append("Error iniciarProcesoConversionArchivos. No se ha podido finalizar correctamente.");
			
			return null;
			
			
		} 	finally {
			session.close();
		}
		
	}	
	
	
	public Boolean cambiarEstadoProcesoConversionArchivos(SqlSession session, Long prco_prco,  String prco_eprco, Usuario usuario, String observacion, StringBuffer mensajeErrorOut ){
		
		Boolean respuesta = true;
		try {

			ProcesoConversionArchivos prco = new ProcesoConversionArchivos();
			prco.setPrco_prco(prco_prco);
			prco.setPrco_eprco(prco_eprco);
			
			
			try {
				
				//Se cambia de estado
				ProcesoConversionArchivosDao procesoConversionArchivosMapper = session.getMapper(ProcesoConversionArchivosDao.class);
				procesoConversionArchivosMapper.setEstadoProceso(prco);
				
				//Se crea el historico de la prepropuesta
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("hprco_prco", prco.getPrco_prco());
				hashMap.put("hprco_eprco", prco.getPrco_eprco());
				hashMap.put("hprco_usua", usuario.getUsua_usua());
				hashMap.put("hprco_obser", observacion);
				
				procesoConversionArchivosMapper.crearHistoricoProceso(hashMap);
				

			} catch (Exception e) {
				mensajeErrorOut.append("Error cambiarEstadoProcesoConversionArchivos");
				SimpleLogger.error("Error cambiarEstadoProcesoConversionArchivos",e );	
				respuesta = false;
			}
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			mensajeErrorOut.append("Error cambiarEstadoProcesoConversionArchivos");
			respuesta = false;
		} 	
		
		return respuesta;
	
	}
	
	
	
	
	
	
	
	/**
	 * ============================================
	 * CONSULTAS ==================================
	 * ============================================
	 * 
	 */
	
	
	public List<HistoricoProcesoConversionArchivos> getHistoricoPorProcesoConversionArchivos(Long prco_prco){
		
		List<HistoricoProcesoConversionArchivos> listaHistorico = ProcesoConversionArchivosControllerDB.getInstance().getHistoricoPorProcesoConversionArchivos(prco_prco);
		
		Usuario usuario = new Usuario();
		
		//Se asocia la informacion al persona asociada al usuario registrado en el historico
		if(listaHistorico!= null){
			for (HistoricoProcesoConversionArchivos historico: listaHistorico) {
				
				usuario.setUsua_usua(historico.getHprco_usua()); 
				Persona persona = PersonaControllerDB.getInstance().getPersonaPorUsuario(usuario);
				historico.setPersona(persona);
					
			}
		}
		
		return listaHistorico;
		
		
	}
	

	
	public ProcesoConversionArchivos getProcesoConversionArchivos(Long prco_prco){
		
		ProcesoConversionArchivosControllerDB controllerDB = ProcesoConversionArchivosControllerDB.getInstance();
		ProcesoConversionArchivos procesoConversionArchivos = controllerDB.getProcesoConversionArchivos(prco_prco);
		
		completarInformacionAdicionalProcesoUnificacion(procesoConversionArchivos);
		
		return procesoConversionArchivos;
		
	}
	
	
	

	
	public String getRutaTemporalPorProceso(Long prco_prco){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		return rutaGeneral+ "/temp/prco/"+prco_prco+"/";
		
	}


	
	
	public String getRutaFinalArchivosConvertidos(ProcesoConversionArchivos procesoConversionArchivos){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(procesoConversionArchivos.getPrco_fcrea());
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
		

		return rutaGeneral+ "/prco/"+year+"/"+month+"/"+day+"/prco_"+procesoConversionArchivos.getPrco_prco()+"/01_arge/";
		
	}
	
	private void completarInformacionAdicionalProcesoUnificacion(ProcesoConversionArchivos procesoConversionArchivos ){
		try {
			if(procesoConversionArchivos!=null && procesoConversionArchivos.getPrco_prco()!=null){
				List<HistoricoProcesoConversionArchivos> historicoProcesoConversionArchivos = getHistoricoPorProcesoConversionArchivos(procesoConversionArchivos.getPrco_prco());
				procesoConversionArchivos.setHistoricoProcesoConversionArchivos(historicoProcesoConversionArchivos);
				
				
				
				
				
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	
	
	
	
	

}
