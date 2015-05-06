package com.developer.logic.modulo.unificacion.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificar;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoPorUnificarRepetido;
import com.developer.logic.modulo.unificacion.dto.ArchivoRecaudoUnificado;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.unificacion.dto.TipoArchivoRecaudo;

public class UnificadorArchivosDefault {

	public ArchivoRecaudoUnificado createARUN(String rutaArchivosUnificados,
			String nombreArchivoUnificado,
			ProcesoUnificacionArchivos procesoUnificacionArchivos,
			TipoArchivoRecaudo tipoArchivoRecaudo,
			List<ArchivoRecaudoPorUnificar> archivosARPU, String usua_usua,
			StringBuffer mensajeErrorOut) {

		HashMap<String, ArchivoRecaudoPorUnificar> archivosNoRepetidos = new HashMap<String, ArchivoRecaudoPorUnificar>();
		HashMap<String, ArchivoRecaudoPorUnificar> archivosRepetidos = new HashMap<String, ArchivoRecaudoPorUnificar>();
		List<ArchivoRecaudoPorUnificarRepetido> archivosRecaudoPorUnificarRepetidos = new ArrayList<ArchivoRecaudoPorUnificarRepetido>();

		ArchivoRecaudoUnificado archivoRecaudoUnificado = null;
		FileWriter fichero = null;
		PrintWriter printerWriter = null;
		Boolean sinErrores = true;
		Long totalRegistros = new Long(0);
		Long totalArchivos = new Long(0);

		try {

			fichero = new FileWriter(rutaArchivosUnificados
					+ nombreArchivoUnificado);
			
		
			printerWriter = new PrintWriter(fichero);

			for (ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar : archivosARPU) {

				File file = new File(archivoRecaudoPorUnificar.getArpu_url());

				if (file.exists()) {

					if (!archivosNoRepetidos
							.containsKey(archivoRecaudoPorUnificar
									.getArpu_nombre())) {

						archivosNoRepetidos.put(
								archivoRecaudoPorUnificar.getArpu_nombre(),
								archivoRecaudoPorUnificar);
						totalArchivos++;

						if (archivoRecaudoPorUnificar.getArpu_registros() > 0) {

							// Declarar una variable BufferedReader
							BufferedReader bufferedReader = null;
							try {
								// Crear un objeto BufferedReader al que se le
								// pasa
								// un objeto FileReader con el nombre del
								// fichero
								bufferedReader = new BufferedReader(
										   new InputStreamReader(
								                      new FileInputStream(file), "ISO-8859-1"));

								// Repetir mientras no se llegue al final del
								// fichero
								while (bufferedReader.ready()) {
									// Hacer lo que sea con la línea leída
									totalRegistros++;
									String linea = bufferedReader.readLine();
									printerWriter.println(linea);

								}
							} catch (FileNotFoundException e) {
								sinErrores = false;
								System.out
										.println("Error: Fichero no encontrado");
								System.out.println(e.getMessage());

							} catch (Exception e) {
								sinErrores = false;
								System.out
										.println("Error de lectura del fichero");
								System.out.println(e.getMessage());

							} finally {
								try {
									if (bufferedReader != null)
										bufferedReader.close();
								} catch (Exception e) {
									sinErrores = false;
									System.out
											.println("Error al cerrar el fichero");
									System.out.println(e.getMessage());

								}
							}
						}

					} else {
						archivosRepetidos.put(
								archivoRecaudoPorUnificar.getArpu_nombre(),
								archivoRecaudoPorUnificar);

					}

				}

			}

			// Se revisan los archivos repetidos
			for (ArchivoRecaudoPorUnificar archivoRecaudoPorUnificar : archivosARPU) {

				if (archivosRepetidos.containsKey(archivoRecaudoPorUnificar
						.getArpu_nombre())) {
					ArchivoRecaudoPorUnificarRepetido archivoRecaudoPorUnificarRepetido = new ArchivoRecaudoPorUnificarRepetido();
					archivoRecaudoPorUnificarRepetido
							.setArpr_prun(procesoUnificacionArchivos
									.getPrun_prun());
					archivoRecaudoPorUnificarRepetido
							.setArpr_arpu(archivoRecaudoPorUnificar
									.getArpu_arpu());

					archivosRecaudoPorUnificarRepetidos
							.add(archivoRecaudoPorUnificarRepetido);

				}

			}

		} catch (Exception e) {
			mensajeErrorOut.append(e.getMessage());
			e.printStackTrace();

		} finally {
			try {

				if (fichero != null) {
					fichero.close();
				}

			} catch (Exception e2) {
				mensajeErrorOut.append(e2.getMessage());
				e2.printStackTrace();

			}
		}

		if (sinErrores) {

			try {

				File fileUnificado = new File(rutaArchivosUnificados
						+ nombreArchivoUnificado);
				if (fileUnificado.exists()) {

					Long hash = FileUtils.checksumCRC32(fileUnificado);
					Long arun_arun = ArchivoRecaudoUnificadoServicio
							.getInstance().getSiguienteID();
					Long size = fileUnificado.length();

					archivoRecaudoUnificado = new ArchivoRecaudoUnificado();
					archivoRecaudoUnificado.setArun_archivos(totalArchivos);
					archivoRecaudoUnificado.setArun_arun(arun_arun);
					archivoRecaudoUnificado.setArun_bytes(size.toString());
					archivoRecaudoUnificado
							.setArun_earun(ArchivoRecaudoUnificado.CREADO);
					archivoRecaudoUnificado.setArun_extension(FilenameUtils
							.getExtension(fileUnificado.getName()));
					archivoRecaudoUnificado.setArun_nombre(FilenameUtils
							.getBaseName(fileUnificado.getName()));
					archivoRecaudoUnificado.setArun_hash(hash.toString());
					archivoRecaudoUnificado
							.setArun_observ("Creacion de archivo unificado "
									+ tipoArchivoRecaudo.getTpar_tpar()
									+ " proceso: "
									+ procesoUnificacionArchivos.getPrun_prun());
					archivoRecaudoUnificado
							.setArun_prun(procesoUnificacionArchivos
									.getPrun_prun());
					archivoRecaudoUnificado.setArun_registros(totalRegistros);
					archivoRecaudoUnificado.setArun_tpar(tipoArchivoRecaudo
							.getTpar_tpar());
					archivoRecaudoUnificado.setArun_url(rutaArchivosUnificados
							+ nombreArchivoUnificado);
					archivoRecaudoUnificado.setArun_usua(usua_usua);
					archivoRecaudoUnificado.setArun_archivos_repetidos(new Long(archivosRecaudoPorUnificarRepetidos.size()));
					
					
					archivoRecaudoUnificado.setArchivosPorUnificarRepetidos(archivosRecaudoPorUnificarRepetidos);

				} else {
					mensajeErrorOut
							.append("Error. No se pudo guardar archivo unificado "
									+ nombreArchivoUnificado);
					archivoRecaudoUnificado = null;
				}

			} catch (Exception e) {
				mensajeErrorOut
						.append("Error. No se pudo guardar archivo unificado "
								+ nombreArchivoUnificado);
				archivoRecaudoUnificado = null;
			}
		}

		return archivoRecaudoUnificado;

	}

}
