package com.developer.logic.modulo.unificacion.modelo;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.general.dto.Persona;
import com.developer.logic.modulo.unificacion.dto.HistoricoProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.mybatis.DBManager;
import com.developer.persistence.modulo.general.controllerdb.PersonaControllerDB;
import com.developer.persistence.modulo.unificacion.controllerdb.ProcesoUnificacionArchivosControllerDB;
import com.developer.persistence.modulo.unificacion.mapper.dao.ProcesoUnificacionArchivosDao;


public class ProcesoUnificacionArchivosServicio {
	
	public static String PROCESO_SOL_REVISOR_ANTEPROYECTO = "SLREV";
	
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
	
	
	public ProcesoUnificacionArchivos iniciarProcesoUnificacionArchivosTransaccional(Long prun_prun,  String observacionDeInicio,  Usuario usuario, StringBuffer mensajeErrorOut){
		
		
		SqlSession session = DBManager.openSession();
		ProcesoUnificacionArchivos procesoUnificacionArchivosIniciada= null;
		
		try {
			Boolean sinErrores = true;
			
			//Se valida la informacion
			if(observacionDeInicio==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado correctamente el titulo, la descripcion y  la observación de inicio del procesoUnificacionArchivos.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}
			
			if(prun_prun==null){
				String error= "Error iniciando procesoUnificacionArchivos. No se ha especificado la procesoUnificacionArchivos que se desea iniciar como procesoUnificacionArchivos.";
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
			
			
			
			//Se verifica que no existan errores para crear el procesoUnificacionArchivos
			if(sinErrores){
				ProcesoUnificacionArchivos procesoUnificacionArchivos = ProcesoUnificacionArchivosControllerDB.getInstance().iniciarProcesoUnificacionArchivosTransaccional(	session, 
																														prun_prun, 
																														observacionDeInicio,
																														usuario, 
																														mensajeErrorOut);
				
				
				// TODO: Colocar los arhivos ZIP a asociar al proceso unificado
				
				
				if(procesoUnificacionArchivos!= null){
					
					/*
						//Se crea el documento al procesoUnificacionArchivos acorde al documento al propuesta
						Long idDocumento = DocumentoProcesoUnificacionArchivosServicio.getInstance().getSiguienteID();
						Long version = DocumentoProcesoUnificacionArchivosServicio.getInstance().getSiguienteVersion(procesoUnificacionArchivos.getPrun_prop());
						
						String rutabase =  DocumentoProcesoUnificacionArchivosServicio.getInstance().getRutaBaseDeArchivos();
						String nombreEnServidor = DocumentoProcesoUnificacionArchivosServicio.getInstance().construirNombreDeArchivo(procesoUnificacionArchivos.getPrun_prop(), idDocumento, version);
						String ruta = rutabase+"/"+nombreEnServidor;
						
						DocumentoPropuesta docProp= DocumentoPropuestaServicio.getInstance().getDocumentoActualPorPropuesta(prun_prun);
						String filename = docProp.getDprop_url();
						File filePropuesta= new File(filename);
						File fileProcesoUnificacionArchivos = new File(ruta);
						
						if(filePropuesta.exists() ){
						
							FileUtils.copyFile(filePropuesta, fileProcesoUnificacionArchivos);
							
							DocumentoProcesoUnificacionArchivos documentoProcesoUnificacionArchivos = new DocumentoProcesoUnificacionArchivos();
							documentoProcesoUnificacionArchivos.setDprun_extension(docProp.getDprop_extension());
							documentoProcesoUnificacionArchivos.setDprun_falm(ServerServicio.getInstance().getSysdate());
							documentoProcesoUnificacionArchivos.setDprun_dprun(idDocumento);
							documentoProcesoUnificacionArchivos.setDprun_prun(procesoUnificacionArchivos.getPrun_prun());
							documentoProcesoUnificacionArchivos.setDprun_bytes(docProp.getDprop_bytes());
							documentoProcesoUnificacionArchivos.setDprun_nombre(docProp.getDprop_nombre());
							documentoProcesoUnificacionArchivos.setDprun_hash(docProp.getDprop_hash());
							documentoProcesoUnificacionArchivos.setDprun_url(ruta);
							documentoProcesoUnificacionArchivos.setDprun_vers(version);
							documentoProcesoUnificacionArchivos.setDprun_usua(usuario.getUsua_usua());
							documentoProcesoUnificacionArchivos.setDprun_observ(observacionDeInicio);
							
							sinErrores = DocumentoProcesoUnificacionArchivosServicio.getInstance().crearDocumentoTransaccional(session, documentoProcesoUnificacionArchivos) && sinErrores;
							
							if(sinErrores){
								session.commit();
								procesoUnificacionArchivos.setDocumentoProcesoUnificacionArchivos(documentoProcesoUnificacionArchivos);
								procesoUnificacionArchivosIniciada = procesoUnificacionArchivos;
							}else{
								session.rollback();
								SimpleLogger.error("Error creando procesoUnificacionArchivos. No se ha podido crear el documento asociado al procesoUnificacionArchivos de forma correcta");
								mensajeErrorOut.append("Error creando procesoUnificacionArchivos. No se ha podido crear el documento asociado al  procesoUnificacionArchivos de forma correcta");
							}
						}else{
							session.rollback();
							SimpleLogger.error("Error creando procesoUnificacionArchivos. No existe documento asociado al procesoUnificacionArchivos.");
							mensajeErrorOut.append("Error creando procesoUnificacionArchivos. No existe documento asociado al procesoUnificacionArchivos.");
						}
						
						*/
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
		return controllerDB.getProcesoUnificacionArchivos(prun_prun);
		
	}
	
	

	

}
