package com.developer.logic.modulo.unificacion.modelo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.general.modelo.ServerServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificarRepetido;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;
import com.developer.mybatis.DBManager;

public class UnificadorArchivosPorProcesoServicio {
	
	UnificadorArchivosDefault unificadorArchivosDefault;
	UnificadorArchivosFiduciaria unificadorArchivosFiduciaria;
	
	public UnificadorArchivosPorProcesoServicio() {
		unificadorArchivosDefault = new UnificadorArchivosDefault();
		unificadorArchivosFiduciaria = new UnificadorArchivosFiduciaria();
		
	}
	
	
	public Boolean generarArchivosUnificadosPorProceso(ProcesoUnificacionArchivos procesoUnificacionArchivos, Usuario usuario, StringBuffer mensajeErrorOut){
		
		Boolean sinErrores =true; 
		
		TipoArchivoRecaudoServicio tipoArchivoRecaudoServicio = new TipoArchivoRecaudoServicio();
		List<TipoArchivoRecaudo> tiposArchivoRecaudo = tipoArchivoRecaudoServicio.getTipoArchivosRecaudoPorPRUN(procesoUnificacionArchivos.getPrun_prun());
		
		ArchivoRecaudoPorUnificarServicio archivoRecaudoPorUnificarServicio = new ArchivoRecaudoPorUnificarServicio();
		ProcesoUnificacionArchivosServicio procesoUnificacionArchivosServicio = new ProcesoUnificacionArchivosServicio();
		
		
		ServerServicio serverServicio = new ServerServicio();
		Date currentDate = serverServicio.getSysdate();
		
		
		String rutaArchivosUnificados = procesoUnificacionArchivosServicio.getRutaFinalArchivosUnificados(procesoUnificacionArchivos);
		
		//Se crea la carpeta general de unzip
	    File folder = new File(rutaArchivosUnificados);
		if(!folder.exists()){
	    		folder.mkdir();
		}
		
				
		SqlSession session = DBManager.openSession();
		
		try {
		
			for (TipoArchivoRecaudo tipoArchivoRecaudo : tiposArchivoRecaudo) {
				List<ArchivoRecaudoPorUnificar> archivosARPU = archivoRecaudoPorUnificarServicio.getArchivosTPARxPRUN(procesoUnificacionArchivos.getPrun_prun(), tipoArchivoRecaudo.getTpar_tpar());
				
				
				if(archivosARPU!=null){
					ArchivoRecaudoUnificado archivoRecaudoUnificado;
					
					String nombreArchivoUnificado = null;
					if(!StringUtils.isEmpty(tipoArchivoRecaudo.getTpar_nomb_arun())){
						
						nombreArchivoUnificado = tipoArchivoRecaudo.getTpar_nomb_arun()+"_"+getDateString(procesoUnificacionArchivos.getPrun_ffin(), "ddMM")+"."+tipoArchivoRecaudo.getTpar_tpar().toLowerCase();
						
					}else{
					
					
						nombreArchivoUnificado = "arun_"+procesoUnificacionArchivos.getPrun_prun()+"_"+getDateString(currentDate)+"."+tipoArchivoRecaudo.getTpar_tpar().toLowerCase();
					
					}
					
					if( (tipoArchivoRecaudo.getTpar_estr().equals(TipoArchivoRecaudo.ESTR_ASOBANCARIA)) || ( 
							tipoArchivoRecaudo.getTpar_estr().equals(TipoArchivoRecaudo.ESTR_FIDUCIARIA))	){
						
							archivoRecaudoUnificado = unificadorArchivosFiduciaria.createARUN(rutaArchivosUnificados, 
																							  nombreArchivoUnificado, 
																							  procesoUnificacionArchivos, 
																							  tipoArchivoRecaudo, 
																							  archivosARPU, 
																							  usuario.getUsua_usua(), 
																							  mensajeErrorOut);
						
					}else{
						
							archivoRecaudoUnificado = unificadorArchivosDefault.createARUN(	rutaArchivosUnificados, 
																						  	nombreArchivoUnificado, 
																						  	procesoUnificacionArchivos, 
																						  	tipoArchivoRecaudo, 
																						  	archivosARPU, 
																						  	usuario.getUsua_usua(), 
																						  	mensajeErrorOut);
					}
					
					
					if(archivoRecaudoUnificado==null){
						sinErrores = false;
						break;
					}else{
						
						ArchivoRecaudoUnificadoServicio archivoRecaudoUnificadoServicio = new ArchivoRecaudoUnificadoServicio();
						sinErrores = sinErrores && archivoRecaudoUnificadoServicio.crearArchivoTransaccional(session, archivoRecaudoUnificado);
						
						
						if(archivoRecaudoUnificado.getArchivosPorUnificarRepetidos().size()>0){
							
							for (ArchivoRecaudoPorUnificarRepetido archivo : archivoRecaudoUnificado.getArchivosPorUnificarRepetidos()) {
								
								
								ArchivoRecaudoPorUnificarRepetidoServicio archivoRecaudoPorUnificarRepetidoServicio = ArchivoRecaudoPorUnificarRepetidoServicio.getInstance();
								
								archivo.setArpr_arun(archivoRecaudoUnificado.getArun_arun());
								
								sinErrores = sinErrores && archivoRecaudoPorUnificarRepetidoServicio.crearDocumentoTransaccional(session, archivo, mensajeErrorOut);
							
							}
							
						}
						
						
						if(!sinErrores){
							
							mensajeErrorOut.append("Error creando registro de ArchivoUnificado en Base de Datos"+archivoRecaudoUnificado.getArun_nombre()+"."+archivoRecaudoUnificado.getArun_extension());
							break;
							
						}
					}
					
					
				
				}else{
					sinErrores=false;
				}
				
				
			}
			
			//Si al final del procso no hay errores se hace commt;
			if(sinErrores){
				
				sinErrores = sinErrores && procesoUnificacionArchivosServicio.cambiarEstadoProcesoUnificacionArchivos(session, procesoUnificacionArchivos.getPrun_prun(), ProcesoUnificacionArchivos.FINALIZADO, usuario, "Unificación Automática", mensajeErrorOut);
				
				if(sinErrores){
					session.commit();
				}
			}
					
			
		}catch (Exception e) {
			SimpleLogger.error("Error ", e);
			session.rollback();
			mensajeErrorOut.append("Error Unificando Archivos. No se ha podido finalizar correctamente.");
			
		} 	finally {
			session.close();
		}
		
		
		return sinErrores;
		
		
		
	}
	
	private String  getDateString(Date date, String format){
		
		
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat(format);
		
	    return ""+fechaFormat.format(date);
	}
	
	
	private String  getDateString(Date date){
		
		
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat horaFormat = new SimpleDateFormat("hhMMss");
		
	    return ""+fechaFormat.format(date)+"_"+horaFormat.format(date);
	}
	
	
	
	public static void main(String[] args) {
		Date date = new Date();
		
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		//System.out.println(format.format((Date) date).replace(" 00:00:00", "").trim());
		System.out.println(format.format(date));
		
		format = new SimpleDateFormat("hhMMss");
		System.out.println(format.format(date));
	}
	
}
