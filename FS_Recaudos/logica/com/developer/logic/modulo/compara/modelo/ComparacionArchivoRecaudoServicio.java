package com.developer.logic.modulo.compara.modelo;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.dto.Usuario;
import com.developer.logic.modulo.compara.dto.ArchivoInternetBSC;
import com.developer.logic.modulo.compara.dto.ComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.DetalleComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.DetalleResumenComparacion;
import com.developer.logic.modulo.compara.dto.DiferenciaResumenComparacion;
import com.developer.logic.modulo.compara.dto.HomologacionTipoRecaudoComparador;
import com.developer.logic.modulo.general.dto.ParametroConfiguracionGeneral;
import com.developer.logic.modulo.general.modelo.ConfiguracionGeneralServicio;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.modelo.ArchivoRecaudoUnificadoServicio;
import com.developer.logic.modulo.unificacion.modelo.ProcesoUnificacionArchivosServicio;
import com.developer.mybatis.DBManagerFSRecaudos;
import com.developer.persistence.modulo.compara.controllerdb.ComparacionArchivoRecaudoControllerDB;

public class ComparacionArchivoRecaudoServicio {

	ComparacionArchivoRecaudoControllerDB controllerDB;

	public ComparacionArchivoRecaudoServicio() {
		controllerDB = new ComparacionArchivoRecaudoControllerDB();
	}

	/**
	 * ========================================== 
	 * CONSULTAS ================================
	 * ==========================================
	 */

	public List<ComparacionArchivoRecaudo> getComparacionesPorARUN(
			Long arun_arun, Boolean infoCompleta) {
		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		List<ComparacionArchivoRecaudo> list = controllerDB
				.getComparacionesPorARUN(arun_arun);

		
		if(infoCompleta){
		
			for (ComparacionArchivoRecaudo comparacionArchivo : list) {
				completarInformacionAdicionalArchivo(comparacionArchivo);
			}
		}

		return list;

	}

	public ComparacionArchivoRecaudo getComparacion(Long cpar_cpar, Boolean infoCompleta ) {
		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		ComparacionArchivoRecaudo comparacionArchivo = controllerDB
				.getComparacion(cpar_cpar);

		if(infoCompleta){
			completarInformacionAdicionalArchivo(comparacionArchivo);
		}

		return comparacionArchivo;

	}
	
	public List<HomologacionTipoRecaudoComparador> getAllHomologacionesTipoRecaudo(){
		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		return  controllerDB.getAllHomologacionesTipoRecaudo();

	}

	/**
	 * ========================================== 
	 * OPERACIONES TRANSACCIONES ================ 
	 * ==========================================
	 */

	public Long getSiguienteID() {

		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		return controllerDB.getSiguienteID();

	}

	public ComparacionArchivoRecaudo iniciarComparacionTransaccional(
			Long arun_arun, Long cpar_cpar, String observacionDeInicio,
			Date currentDate, File fileExcel, Usuario usuario,
			StringBuffer mensajeErrorOut) {

		SqlSession session = DBManagerFSRecaudos.openSession();

		ComparacionArchivoRecaudo comparacionArchivoRecaudo = null;

		ArchivoRecaudoUnificadoServicio archivoUnificadoServicio = new ArchivoRecaudoUnificadoServicio();
		ProcesoUnificacionArchivosServicio procesoUnificacionServicio = new ProcesoUnificacionArchivosServicio();
		ArchivoInternetBSCServicio ibscServicio = new ArchivoInternetBSCServicio();

		try {
			Boolean sinErrores = true;

			// Se valida la informacion
			if (observacionDeInicio == null) {
				String error = "Error iniciando comparacion de archivo. No se ha especificado correctamente la observación de inicio.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}

			if (arun_arun == null) {
				String error = "Error iniciando comparacion de archivo. No se ha especificado el archivo unificado para comparar.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}

			if (cpar_cpar == null) {
				String error = "Error iniciando comparacion de archivo. No se ha especificado el identificador unico de comparacion.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}

			if (usuario == null) {
				String error = "Error iniciando comparacion de archivo. No se ha especificado el solicitante para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}

			if (currentDate == null) {
				String error = "Error iniciando comparacion de archivo. No se ha especificado la fecha para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}

			if (fileExcel == null) {
				String error = "Error iniciando comparacion de archivo. No se ha especificado los archivos para el inicio del mismo.";
				SimpleLogger.error(error);
				mensajeErrorOut.append(error);
				sinErrores = false;
				return null;
			}

			// Se verifica que no existan errores para crear la comparacion
			if (sinErrores) {

				ArchivoRecaudoUnificado archivoUnificado = archivoUnificadoServicio.getArchivo(arun_arun);
				ProcesoUnificacionArchivos procesoUnificacionArchivos = procesoUnificacionServicio.getProcesoUnificacionArchivos(archivoUnificado.getArun_prun());
				
				
				
				//Se crean los documentos asociados al proceso
				String nombreEnServidor = ibscServicio.getNombreArchivoEnServidor(fileExcel);
				String rutabaseExcel = this.getRutaFinalArchivosBSC(cpar_cpar, currentDate);	
				String ruta = rutabaseExcel+"/"+nombreEnServidor;
				
			
				File fileServidor = new File(ruta);
				
				if(fileExcel.exists() && sinErrores){
				
					FileUtils.copyFile(fileExcel, fileServidor);
				
					// Se crea archivo BSC
					ArchivoInternetBSC archivoInternetBSC = ibscServicio.crearDocumentoTransaccional(	session, 
																										archivoUnificado,
																										fileServidor, 
																										observacionDeInicio, 
																										usuario,
																										mensajeErrorOut);

					if (archivoInternetBSC != null) {
						
						//Se crean los detalles del archivo plano
						File fileBSC = new File(archivoUnificado.getArun_url());
						LectorArchivoPlanoComparador lectorArchivoPlanoComparador = new LectorArchivoPlanoComparador(fileBSC, archivoUnificado.getArun_tpar());
						List<DetalleComparacionArchivoRecaudo> detallesBSC = lectorArchivoPlanoComparador.generarDetalleArchivo(mensajeErrorOut);
						
						
						//Se crean los detalle del archivo internet
						LectorArchivoInternetComparador lectorArchivoInternetComparador = new LectorArchivoInternetComparador(fileServidor);
						List<DetalleComparacionArchivoRecaudo> detallesInternet = lectorArchivoInternetComparador.generarDetalleArchivo(mensajeErrorOut);
						
						comparacionArchivoRecaudo = new ComparacionArchivoRecaudo();
						comparacionArchivoRecaudo.setCpar_arun(arun_arun);
						comparacionArchivoRecaudo.setCpar_cpar(cpar_cpar);
						comparacionArchivoRecaudo.setCpar_ecpar(ComparacionArchivoRecaudo.EJECUTADA);
						comparacionArchivoRecaudo.setCpar_fini(procesoUnificacionArchivos.getPrun_fini());
						comparacionArchivoRecaudo.setCpar_ffin(procesoUnificacionArchivos.getPrun_ffin());
						comparacionArchivoRecaudo.setCpar_ibsc(archivoInternetBSC.getIbsc_ibsc());
						comparacionArchivoRecaudo.setCpar_observ(observacionDeInicio);
						comparacionArchivoRecaudo.setCpar_tpar(archivoUnificado.getArun_tpar());
						comparacionArchivoRecaudo.setCpar_usua(usuario.getUsua_usua());
						comparacionArchivoRecaudo.setCpar_arun_cta(lectorArchivoPlanoComparador.getCuentaBancaria());
						comparacionArchivoRecaudo.setCpar_ibsc_cta(lectorArchivoInternetComparador.getCuentaBancaria());

					
						sinErrores = sinErrores
								&& this.controllerDB.crearComparacionTransaccional(
										session, comparacionArchivoRecaudo);
						
						sinErrores= sinErrores && crearDetallesTransaccional(session,comparacionArchivoRecaudo, detallesBSC, mensajeErrorOut);
						
						sinErrores= sinErrores && crearDetallesTransaccional(session,comparacionArchivoRecaudo, detallesInternet, mensajeErrorOut);
						
						
						if (sinErrores) {

							session.commit();

						} else {
							session.rollback();
							SimpleLogger.error("Error creando Comparacion. No se ha podido crear los registros en BD");
							mensajeErrorOut.append("Error creando Comparacion. No se ha podido crear los registros en BD");
							comparacionArchivoRecaudo = null;
						}

					}
				}
				
				

				

			} else {
				session.rollback();
				SimpleLogger
						.error("Error creando procesoUnificacionArchivos. No ha sido posible crear la información del Formulario de ProcesoUnificacionArchivos para el inicio del mismo");
				mensajeErrorOut
						.append("Error creando procesoUnificacionArchivos. No ha sido posible crear la información del Formulario de ProcesoUnificacionArchivos para el inicio del mismo");
				comparacionArchivoRecaudo = null;
			}

			return comparacionArchivoRecaudo;

		} catch (Exception e) {
			SimpleLogger.error("Error ", e);
			session.rollback();
			mensajeErrorOut
					.append("Error iniciarProcesoUnificacionArchivos. No se ha podido finalizar correctamente.");

			return null;

		} finally {
			session.close();
		}

	}

	private Boolean crearDetallesTransaccional(
			SqlSession session,
			ComparacionArchivoRecaudo comparacionArchivo,
			List<DetalleComparacionArchivoRecaudo> detallesComparacionArchivoRecaudo,
			StringBuffer mensajeErrorOut) {

		boolean sinErrores = true;

		try {

			for (DetalleComparacionArchivoRecaudo detalleComparacionArchivoRecaudo : detallesComparacionArchivoRecaudo) {
				detalleComparacionArchivoRecaudo
						.setDcpar_cpar(comparacionArchivo.getCpar_cpar());
				detalleComparacionArchivoRecaudo
						.setDcpar_fcrea(comparacionArchivo.getCpar_fcrea());

				sinErrores = sinErrores
						&& this.controllerDB
								.crearDetalleComparacionTransaccional(session,
										detalleComparacionArchivoRecaudo);

			}

			return sinErrores;

		} catch (Exception e) {
			return false;
		}
	}

	public List<DetalleComparacionArchivoRecaudo> getAllDetallesCPAR(
			Long cpar_cpar) {

		ComparacionArchivoRecaudoControllerDB controllerDB = this.controllerDB;
		List<DetalleComparacionArchivoRecaudo> list = controllerDB
				.getAllDetallesCPAR(cpar_cpar);

		return list;

	}

	public String getRutaTemporalArchivosBSC(Long cpar_cpar) {
		ParametroConfiguracionGeneral parametroRutas = new ConfiguracionGeneralServicio()
				.getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();

		return rutaGeneral + "/temp/cpar/" + cpar_cpar + "/";

	}

	public String getRutaFinalArchivosBSC(Long cpar_cpar, Date currentDate) {
		ParametroConfiguracionGeneral parametroRutas = new ConfiguracionGeneralServicio()
				.getParametro(ConfiguracionGeneralServicio.RUTA_GRAL_ARCHIVOS);
		String rutaGeneral = parametroRutas.getConfig_valor();

		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		return rutaGeneral + "/cpar/" + year + "/" + month + "/" + day
				+ "/cpar_" + cpar_cpar
				+ "/01_ibsc/";

	}

	private void completarInformacionAdicionalArchivo(
			ComparacionArchivoRecaudo comparacionArchivo) {
		try {

			if (comparacionArchivo != null
					&& comparacionArchivo.getCpar_cpar() != null) {

				List<DetalleComparacionArchivoRecaudo> detalles = controllerDB
						.getAllDetallesCPAR(comparacionArchivo.getCpar_cpar());
				comparacionArchivo.setDetalles(detalles);
				
				List<DetalleResumenComparacion> resumen = controllerDB
						.getDetallesResumenCPAR(comparacionArchivo.getCpar_cpar());
				comparacionArchivo.setResumen(resumen);
				
				List<DiferenciaResumenComparacion> diferencias = controllerDB
						.getDiferenciasResumenCPAR(comparacionArchivo.getCpar_cpar());
				comparacionArchivo.setDiferenciasResumen(diferencias);
				
				List<DetalleComparacionArchivoRecaudo> fechas = controllerDB
						.getFechasCPAR(comparacionArchivo.getCpar_cpar());
				comparacionArchivo.setFechas(fechas);
				

			}

		} catch (Exception e) {
			SimpleLogger.error(
					"Error consultando informacion adicional Archivo.", e);

		}

	}

}
