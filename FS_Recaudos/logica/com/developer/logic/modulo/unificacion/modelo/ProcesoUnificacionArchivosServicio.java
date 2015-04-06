package com.developer.logic.modulo.unificacion.modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.logic.modulo.general.dto.Persona;
import com.developer.logic.modulo.general.modelo.ConfiguracionGeneralServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.dto.ArchivoZIPProcesoUnificacion;
import com.developer.logic.modulo.unificacion.dto.HistoricoProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.TransformacionArchivoRecaudo;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.general.controllerdb.PersonaControllerDB;
import com.developer.persistence.modulo.unificacion.controllerdb.ProcesoUnificacionArchivosControllerDB;
import com.developer.persistence.modulo.unificacion.mapper.dao.ProcesoUnificacionArchivosDao;


public class ProcesoUnificacionArchivosServicio {
	
	
	private static ProcesoUnificacionArchivosServicio instance;
	
	public static ProcesoUnificacionArchivosServicio getInstance() {
		if (instance == null) {
			instance = new ProcesoUnificacionArchivosServicio();
		}
		
		return instance;
	}
	
	/**
	 * ======================================
	 * TRANSACCIONES ========================
	 * ======================================
	 */
	

	public Long getSiguienteID(){
		
		ProcesoUnificacionArchivosControllerDB controllerDB = ProcesoUnificacionArchivosControllerDB.getInstance();
		return controllerDB.getSiguienteID();
		
	}
	
	
	public ProcesoUnificacionArchivos iniciarProcesoUnificacionArchivosTransaccional(Long prun_prun,  String observacionDeInicio, Date currentDate, ArrayList<File> filesZIP,  Date prun_fini, Date prun_ffin, Usuario usuario, StringBuffer mensajeErrorOut){
		
		
		SqlSession session = DBManager.openSession();
		ProcesoUnificacionArchivos procesoUnificacionArchivosIniciada= null;
		
		try {
			Boolean sinErrores = true;
			
			//Se valida la informacion
			if(observacionDeInicio==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado correctamente el titulo la observación de inicio del procesoUnificacionArchivos.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(prun_prun==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado el proceso que se desea iniciar como procesoUnificacionArchivos.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(usuario==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado el solicitante para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(currentDate==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado la fecha para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(filesZIP==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado los archivos para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(prun_fini==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado la fecha inicio desde la cual se hara la unificacion.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(prun_ffin==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado la fecha fin hsta la cual se hara la unificacion.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			//Se verifica que no existan errores para crear el procesoUnificacionArchivos
			if(sinErrores){
				ProcesoUnificacionArchivos procesoUnificacionArchivos = ProcesoUnificacionArchivosControllerDB.getInstance().iniciarProcesoUnificacionArchivosTransaccional(	session, 
																														prun_prun,
																														new Long(filesZIP.size()),
																														observacionDeInicio,
																														prun_fini,
																														prun_ffin,
																														currentDate,
																														usuario, 
																														mensajeErrorOut);
				
				
			
				if(procesoUnificacionArchivos!= null ){
						
					ArchivoZIPProcesoUnificacionServicio archivoZIPProcesoUnificacionServicio = new ArchivoZIPProcesoUnificacionServicio();
					String rutabaseZIP = this.getRutaFinalArchivosZIP(procesoUnificacionArchivos);	
					String rutabaseUnZIP = this.getRutaFinalArchivosUnZIP(procesoUnificacionArchivos);
					
						for (File fileZIP : filesZIP) {
							
						
							//Se crean los documentos asociados al proceso
							String nombreEnServidor = archivoZIPProcesoUnificacionServicio.getNombreArchivoEnServidor(fileZIP);
							String ruta = rutabaseZIP+"/"+nombreEnServidor;
							
						
							File fileProcesoUnificacionArchivos = new File(ruta);
							
							if(fileZIP.exists() && sinErrores){
							
								FileUtils.copyFile(fileZIP, fileProcesoUnificacionArchivos);
								
								ArchivoZIPProcesoUnificacion archivoZIPProcesoUnificacion = archivoZIPProcesoUnificacionServicio.crearDocumentoTransaccional(session, procesoUnificacionArchivos, fileZIP, rutabaseUnZIP, mensajeErrorOut);
								
								if(archivoZIPProcesoUnificacion==null){
									sinErrores =false;
									SimpleLogger.error("Error creando archivo zip "+fileZIP.getName());
									mensajeErrorOut.append("Error creando procesoUnificacionArchivos. No existe documento asociado al procesoUnificacionArchivos.");
								}else{
									procesoUnificacionArchivos.getArchivosAZPU().add(archivoZIPProcesoUnificacion);
									
								}
								
								
								
							}else{
								sinErrores = false;
								SimpleLogger.error("Error creando procesoUnificacionArchivos. No existe documento asociado al procesoUnificacionArchivos.");
								mensajeErrorOut.append("Error creando procesoUnificacionArchivos. No existe documento asociado al procesoUnificacionArchivos.");
							}
						
						}
						
					
						
						
					
						if(sinErrores){
							
							
							//Si hay procesos en curso para las mismas fechas se anulan
							List<ProcesoUnificacionArchivos> listProcesoPorAnular = this.getProcesosPorAnular(procesoUnificacionArchivos.getPrun_fini(), procesoUnificacionArchivos.getPrun_ffin());
							for (ProcesoUnificacionArchivos procesoUnificacionArchivosAnular : listProcesoPorAnular) {
								String observacion = "Anulado por proceso No. "+procesoUnificacionArchivos.getPrun_prun();
								
								sinErrores = sinErrores && ProcesoUnificacionArchivosControllerDB.getInstance().setEstadoProcesoUnificacionArchivos(session, procesoUnificacionArchivosAnular.getPrun_prun(), ProcesoUnificacionArchivos.ANULADO, observacion, usuario, mensajeErrorOut);
								mensajeErrorOut.append("Error anulando procesos anteriores. No se ha podido anualar el proceso "+procesoUnificacionArchivosAnular.getPrun_prun());
							}
							
							
							if(sinErrores){
								session.commit();
								procesoUnificacionArchivosIniciada = procesoUnificacionArchivos;
							}else{
								session.rollback();
								SimpleLogger.error("Error creando procesoUnificacionArchivos. No se ha podido crear el documento asociado al procesoUnificacionArchivos de forma correcta");
								mensajeErrorOut.append("Error creando procesoUnificacionArchivos. No se ha podido crear el documento asociado al  procesoUnificacionArchivos de forma correcta");
							}
							
							
							
						}else{
							session.rollback();
							SimpleLogger.error("Error creando procesoUnificacionArchivos. No se ha podido crear el documento asociado al procesoUnificacionArchivos de forma correcta");
							mensajeErrorOut.append("Error creando procesoUnificacionArchivos. No se ha podido crear el documento asociado al  procesoUnificacionArchivos de forma correcta");
						}
						
						
						
						
						
						
					}else{
						session.rollback();
						SimpleLogger.error(mensajeErrorOut.toString());
						
					}
								
				
			}else{
				session.rollback();
				SimpleLogger.error("Error creando procesoUnificacionArchivos. No ha sido posible crear la información del Formulario de ProcesoUnificacionArchivos para el inicio del mismo");
				mensajeErrorOut.append("Error creando procesoUnificacionArchivos. No ha sido posible crear la información del Formulario de ProcesoUnificacionArchivos para el inicio del mismo");
				
			}
			
			return procesoUnificacionArchivosIniciada;
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			session.rollback();
			mensajeErrorOut.append("Error iniciarProcesoUnificacionArchivos. No se ha podido finalizar correctamente.");
			
			return null;
			
			
		} 	finally {
			session.close();
		}
		
	}	
	
	
	public Boolean cambiarEstadoProcesoUnificacionArchivos(SqlSession session, Long prun_prun,  String prun_eprun, Usuario usuario, String observacion, StringBuffer mensajeErrorOut ){
		
		Boolean respuesta = true;
		try {

			ProcesoUnificacionArchivos prun = new ProcesoUnificacionArchivos();
			prun.setPrun_prun(prun_prun);
			prun.setPrun_eprun(prun_eprun);
			
			
			try {
				
				//Se cambia de estado
				ProcesoUnificacionArchivosDao procesoUnificacionArchivosMapper = session.getMapper(ProcesoUnificacionArchivosDao.class);
				procesoUnificacionArchivosMapper.setEstadoProceso(prun);
				
				//Se crea el historico de la prepropuesta
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("hprun_prun", prun.getPrun_prun());
				hashMap.put("hprun_eprun", prun.getPrun_eprun());
				hashMap.put("hprun_usua", usuario.getUsua_usua());
				hashMap.put("hprun_obser", observacion);
				
				procesoUnificacionArchivosMapper.crearHistoricoProceso(hashMap);
				

			} catch (Exception e) {
				mensajeErrorOut.append("Error cambiarEstadoProcesoUnificacionArchivos");
				SimpleLogger.error("Error cambiarEstadoProcesoUnificacionArchivos",e );	
				respuesta = false;
			}
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			mensajeErrorOut.append("Error cambiarEstadoProcesoUnificacionArchivos");
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
	
	
	public List<HistoricoProcesoUnificacionArchivos> getHistoricoPorProcesoUnificacionArchivos(Long prun_prun){
		
		List<HistoricoProcesoUnificacionArchivos> listaHistorico = ProcesoUnificacionArchivosControllerDB.getInstance().getHistoricoPorProcesoUnificacionArchivos(prun_prun);
		
		Usuario usuario = new Usuario();
		
		//Se asocia la informacion al persona asociada al usuario registrado en el historico
		if(listaHistorico!= null){
			for (HistoricoProcesoUnificacionArchivos historico: listaHistorico) {
				
				usuario.setUsua_usua(historico.getHprun_usua()); 
				Persona persona = PersonaControllerDB.getInstance().getPersonaPorUsuario(usuario);
				historico.setPersona(persona);
					
			}
		}
		
		return listaHistorico;
		
		
	}
	

	
	public ProcesoUnificacionArchivos getProcesoUnificacionArchivos(Long prun_prun){
		
		ProcesoUnificacionArchivosControllerDB controllerDB = ProcesoUnificacionArchivosControllerDB.getInstance();
		ProcesoUnificacionArchivos procesoUnificacionArchivos = controllerDB.getProcesoUnificacionArchivos(prun_prun);
		
		completarInformacionAdicionalProcesoUnificacion(procesoUnificacionArchivos);
		
		return procesoUnificacionArchivos;
		
	}
	
	
	
	
	public String getRutaTemporalArchivosZIP(Long prun_prun){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		return rutaGeneral+ "/temp/prun/"+prun_prun+"/zip/";
		
	}

	public String getRutaFinalArchivosZIP(ProcesoUnificacionArchivos procesoUnificacionArchivos){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(procesoUnificacionArchivos.getPrun_fcrea());
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
		
		return rutaGeneral+ "/prun/"+year+"/"+month+"/"+day+"/prun_"+procesoUnificacionArchivos.getPrun_prun()+"/01_azpu/";
		
	}
	
	public String getRutaFinalArchivosUnZIP(ProcesoUnificacionArchivos procesoUnificacionArchivos){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(procesoUnificacionArchivos.getPrun_fcrea());
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	
		return rutaGeneral+ "/prun/"+year+"/"+month+"/"+day+"/prun_"+procesoUnificacionArchivos.getPrun_prun()+"/02_arpu/";
		
	}
	
	public String getRutaFinalArchivosUnificados(ProcesoUnificacionArchivos procesoUnificacionArchivos){
		ParametroConfiguracionGeneral parametroRutas = ConfiguracionGeneralServicio.getInstance().getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(procesoUnificacionArchivos.getPrun_fcrea());
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
		

		return rutaGeneral+ "/prun/"+year+"/"+month+"/"+day+"/prun_"+procesoUnificacionArchivos.getPrun_prun()+"/03_arun/";
		
	}
	
	private void completarInformacionAdicionalProcesoUnificacion(ProcesoUnificacionArchivos procesoUnificacionArchivos ){
		try {
			if(procesoUnificacionArchivos!=null && procesoUnificacionArchivos.getPrun_prun()!=null){
				List<HistoricoProcesoUnificacionArchivos> historicoProcesoUnificacionArchivos = getHistoricoPorProcesoUnificacionArchivos(procesoUnificacionArchivos.getPrun_prun());
				procesoUnificacionArchivos.setHistoricoProcesoUnificacionArchivos(historicoProcesoUnificacionArchivos);
				
				ArchivoZIPProcesoUnificacionServicio servicio = new ArchivoZIPProcesoUnificacionServicio();
				List<ArchivoZIPProcesoUnificacion> archivosAZPU = servicio.getArchivosPorProceso(procesoUnificacionArchivos.getPrun_prun());
				
				ArchivoRecaudoUnificadoServicio unificadoServicio = new ArchivoRecaudoUnificadoServicio();
				List<ArchivoRecaudoUnificado> archivosARUN = unificadoServicio.getArchivosPorPRUN(procesoUnificacionArchivos.getPrun_prun());
				
				TransformadorArchivoRecaudoServicio transformadorArchivoRecaudoServicio = new TransformadorArchivoRecaudoServicio();
				List<TransformacionArchivoRecaudo> archivosTRAR = transformadorArchivoRecaudoServicio.getTranformacionsPorPRUN(procesoUnificacionArchivos.getPrun_prun());
				
				
				procesoUnificacionArchivos.setArchivosARUN(archivosARUN);
				procesoUnificacionArchivos.setArchivosAZPU(archivosAZPU);
				procesoUnificacionArchivos.setArchivosTRAR(archivosTRAR);
				
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public List<ProcesoUnificacionArchivos> getProcesosUnificacionArchivosPaginado(Long pageNumber, Long pageSize, String...prun_estados){
		
		ProcesoUnificacionArchivosControllerDB controllerDB = ProcesoUnificacionArchivosControllerDB.getInstance();
		
		List<ProcesoUnificacionArchivos> procesos = controllerDB.getProcesosUnificacionArchivosPaginado(pageNumber, pageSize, prun_estados);

		if(procesos!=null){
			
			//Se completa la informacion adicional al anteproyecto
			for (ProcesoUnificacionArchivos proceso : procesos) {
				completarInformacionAdicionalProcesoUnificacion(proceso);
			}
			
		}
		
		return procesos;
		
		
	}
	
	public List<ProcesoUnificacionArchivos> getProcesosPorAnular(Date prun_fini, Date prun_ffin){
		
		
		
		ProcesoUnificacionArchivosControllerDB controllerDB = ProcesoUnificacionArchivosControllerDB.getInstance();
		
		List<ProcesoUnificacionArchivos> procesos = controllerDB.getProcesosPorEstadoFechaIniFin(prun_fini, prun_ffin, ProcesoUnificacionArchivos.INICIADO, ProcesoUnificacionArchivos.UNIFICANDO_ARCHIVOS, ProcesoUnificacionArchivos.FINALIZADO);

		if(procesos!=null){
			
			//Se completa la informacion adicional al anteproyecto
			for (ProcesoUnificacionArchivos proceso : procesos) {
				completarInformacionAdicionalProcesoUnificacion(proceso);
			}
			
		}
		
		return procesos;
		
		
	}
	
	public Long getTotalProcesos(String...prun_estados){
		
		ProcesoUnificacionArchivosControllerDB controllerDB = ProcesoUnificacionArchivosControllerDB.getInstance();
		
		Long total = controllerDB.getTotalProcesos( prun_estados);

		if(total==null){
			
			total = new Long(0);
		}
		
		return total;
		
		
	}
	

}
