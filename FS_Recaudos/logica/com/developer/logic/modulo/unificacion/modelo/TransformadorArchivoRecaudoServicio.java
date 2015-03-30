package com.developer.logic.modulo.unificacion.modelo;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;

import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;
import com.developer.logic.modulo.unificacion.dto.TransformacionArchivoRecaudo;
import com.developer.persistence.modulo.unificacion.controllerdb.TransformadorArchivoRecaudoControllerDB;

public class TransformadorArchivoRecaudoServicio {

	private static TransformadorArchivoRecaudoServicio instance;

	public static TransformadorArchivoRecaudoServicio getInstance() {
		if (instance == null) {
			instance = new TransformadorArchivoRecaudoServicio();
		}

		return instance;
	}

	/**
	 * ========================================== 
	 * CONSULTAS
	 * ==========================================
	 */

	public Long getSiguienteID() {

		TransformadorArchivoRecaudoControllerDB controllerDB = TransformadorArchivoRecaudoControllerDB
				.getInstance();
		return controllerDB.getSiguienteID();

	}


	public List<TransformacionArchivoRecaudo> getTranformacionsPorPRUN(
			Long prun_prun) {
		TransformadorArchivoRecaudoControllerDB controllerDB = TransformadorArchivoRecaudoControllerDB
				.getInstance();
		List<TransformacionArchivoRecaudo> list = controllerDB
				.getTranformacionsPorPRUN(prun_prun);

		for (TransformacionArchivoRecaudo transformacionArchivoRecaudo : list) {
			completarInformacionAdicionalArhivo(transformacionArchivoRecaudo);
		}

		return list;

	}



	/**
	 * ========================================== 
	 * OPERACIONES TRANSACCIONES
	 * ==========================================
	 */

	public File hacerTransformacionPorTipoArchivoTransaccional(
			SqlSession session, TipoArchivoRecaudo tipoArchivo, Long prun_prun,
			File file, String rutaArchivosPorUnificar, String usua_usua,
			StringBuffer mensajeErrorOut) {

		File fileTransformado = null;

		if (file != null && file.exists() && file.length() > 0) {
			
			if (tipoArchivo.getTpar_estr().equals(TipoArchivoRecaudo.ESTR_ASOBANCARIA)) {

				String fileName = FilenameUtils.getBaseName(file.getName());
				String fileExtension = FilenameUtils.getExtension(file.getName());

				String newFileName = fileName + "_FC." + fileExtension;

				ConvertidorArchivoAsobancaria convertidorArchivoAsobancaria =
						new ConvertidorArchivoAsobancaria(file, rutaArchivosPorUnificar, newFileName);

				fileTransformado = convertidorArchivoAsobancaria.generarArchivoFiduciaria(mensajeErrorOut);

				if (fileTransformado != null) {

					TransformacionArchivoRecaudo transformacionArchivoRecaudo =  creaTransformacionTransaccional(session, prun_prun,
							tipoArchivo, file, fileTransformado,
							convertidorArchivoAsobancaria.getTotalRegistrosFileOrigen(),
							convertidorArchivoAsobancaria.getTotalRegistrosFileDestino(), 
							usua_usua,
							mensajeErrorOut);
					
					if(transformacionArchivoRecaudo==null){
						fileTransformado  = null;
					}
				}

			} else {
				fileTransformado = file;
			}
		} else {
			fileTransformado = file;
		}

		return fileTransformado;

	}

	private TransformacionArchivoRecaudo creaTransformacionTransaccional(
			SqlSession session, Long prun_prun,
			TipoArchivoRecaudo tipoArchivoRecaudo, File fileOrigen,
			File fileDestino, Long totalRegistrosOrigen,
			Long totalRegistrosDestino, String usua_usua,
			StringBuffer mensajeErrorOut) {

		boolean sinErrores = true;

		try {

			TransformacionArchivoRecaudo transformacionArchivoRecaudo = new TransformacionArchivoRecaudo();
			transformacionArchivoRecaudo.setTrar_bytes_file_fin(fileDestino.length());
			transformacionArchivoRecaudo.setTrar_bytes_file_ini(fileOrigen.length());
			transformacionArchivoRecaudo.setTrar_observ("Transformacion de Archivos tipo "+ tipoArchivoRecaudo.getTpar_tpar());
			transformacionArchivoRecaudo.setTrar_prun(prun_prun);
			transformacionArchivoRecaudo.setTrar_reg_file_fin(totalRegistrosDestino);
			transformacionArchivoRecaudo.setTrar_reg_file_ini(totalRegistrosOrigen);
			transformacionArchivoRecaudo.setTrar_tpar(tipoArchivoRecaudo.getTpar_tpar());
			transformacionArchivoRecaudo.setTrar_trar(getSiguienteID());
			transformacionArchivoRecaudo.setTrar_url_file_fin(fileDestino.getAbsolutePath());
			transformacionArchivoRecaudo.setTrar_url_file_ini(fileOrigen.getAbsolutePath());
			transformacionArchivoRecaudo.setTrar_usua(usua_usua);

			sinErrores = sinErrores
					&& TransformadorArchivoRecaudoControllerDB.getInstance()
							.crearArchivoTransaccional(session,
									transformacionArchivoRecaudo);

			if (sinErrores) {
				mensajeErrorOut.append("Error generando registros de BD de transformacion de archivo.");
				return transformacionArchivoRecaudo;
			} else {
				
				return null;
			}

		} catch (Exception e) {
			mensajeErrorOut.append("Error generando registros de BD de transformacion de archivo.");
			mensajeErrorOut.append(e.getMessage());
			return null;
		}

	}

	private void completarInformacionAdicionalArhivo(
			TransformacionArchivoRecaudo transformacionArchivoRecaudo) {

		try {
			if (transformacionArchivoRecaudo != null
					&& transformacionArchivoRecaudo.getTrar_trar() != null) {

				// TODO completar informacion adicional

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
