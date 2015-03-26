package com.developer.logic.modulo.unificacion.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.utils.StringOsmoUtils;

public class ConvertidorArchivoAsobancaria {

	private static String TITULO = "ARCHIVO EN FORMATO FIDUCIARIA GENERADO LUEGO DE TRANSFORMAR ARCHIVO ASOBANCARIA ";

	File fileAsobancaria;
	String[][] registros;
	Boolean hayRegistros = false;

	String rutaArchivosPorUnificar;
	String nombreArchivoPorUnificar;
	
	public ConvertidorArchivoAsobancaria(File fileAsobancaria, String rutaArchivosPorUnificar, String nombreArchivoPorUnificar ) {
		this.rutaArchivosPorUnificar = rutaArchivosPorUnificar;
		this.nombreArchivoPorUnificar = nombreArchivoPorUnificar;
		
		leerRegistros(fileAsobancaria);
	}

	// --------------------------------------
	// ---- HOMOLOGACION REGISTROS --------
	// --------------------------------------

	/**
	 * Revisa si hay registros en el archivo especificado
	 * 
	 * fileAsobancaria Archivo con informacion de recaudos
	 */
	private Boolean leerRegistros(File fileAsobancaria) {

		this.fileAsobancaria = fileAsobancaria;

		try {

			this.hayRegistros = false;
			
			if (fileAsobancaria != null) {
				Integer[] longitudes = new Integer[] { 2, 48, 14, 2, 2, 6, 6,
						3, 4, 7, 3 };
				registros = LectorArchivoPlanoUtils.leerArchivo(
						fileAsobancaria.getAbsolutePath(), longitudes);

				if (registros == null || registros.length < 1) {
					hayRegistros = false;

				} else {
					hayRegistros = true;
				}
			}

			return hayRegistros;

		} catch (Exception e) {
			SimpleLogger.error("Error", e);
			hayRegistros = false;
			return hayRegistros;
		}

	}

	public File generarArchivoFiduciaria() {

		/***************************************************************************
		 * Informacion del archivo
		 * 
		 * -Primera linea corresponde al encabezado -Segunda linea corresponde a
		 * resumen -Lineas itermedias corresponden a detalles de recaudo
		 * -Penultima linea corresponde a resumen -Ultima linea tiene
		 * informacion de la cantidad de registros y el valor total
		 ***************************************************************************/
		String fecha;
		String cuentaBancaria;
		String totalRegistros;
		BigDecimal valorTotal;
		Long tipoRecaudo;

		String encabezado = null;
		List<String> listRegistros = new ArrayList<String>();

		// Si hay cuentas se buscan las cuentas del tercero
		if (hayRegistros) {

			String linea = registros[0][1];
			fecha = linea.substring(10, 18);
			cuentaBancaria = linea.substring(25, 38);

			linea = registros[registros.length - 2][1];
			totalRegistros = linea.substring(4, 9);
			valorTotal = getBigDecimal(linea.substring(9, 25));

			// Se genera encabezado
			encabezado = "1" + TITULO + cuentaBancaria
					+ String.format("%018.2f", valorTotal).replace(',', '.')
					+ totalRegistros + fecha;

			listRegistros.add(encabezado);
			// Se genera detalles
			String detalle;
			for (int i = 2; i < registros.length - 2; i++) {

				tipoRecaudo = getLong(registros[i][4]);
				Long oficina = getLong(registros[i][8]);
				String encargo = registros[i][1];

				BigDecimal valorEfectivo;
				BigDecimal valorCheque;
				BigDecimal valorRecaudo = getMoneda2Decimals(registros[i][2]);
				String formaRecaudo;
				if (tipoRecaudo == 2) {
					valorCheque = getMoneda2Decimals(registros[i][2]);
					valorEfectivo = new BigDecimal(0);
					formaRecaudo = "RCHE";
				} else {
					valorCheque = new BigDecimal(0);
					valorEfectivo = getMoneda2Decimals(registros[i][2]);
					formaRecaudo = "REFE";
				}

				detalle = "2"
						+ fecha
						+ String.format("%05d", oficina)
						+ String.format("%-24s", encargo.substring(35, 48))
						+ String.format("%30s", "")
						+ String.format("%016.2f", valorEfectivo).replace(',',
								'.')
						+ String.format("%016.2f", valorCheque).replace(',',
								'.')
						+ String.format("%016.2f", valorRecaudo).replace(',',
								'.') + "03A" + formaRecaudo
						+ "00000001  011000000";

				listRegistros.add(detalle);

			}

			return createFile(this.rutaArchivosPorUnificar, this.nombreArchivoPorUnificar, listRegistros);

		} else {
			return null;
		}

	}
	
	private File createFile(String rutaArchivosPorUnificar, String nombreArchivoPorUnificar, List<String> registros){
		
		
		FileWriter fichero = null;
        PrintWriter printerWriter = null;
        try
        {
            fichero = new FileWriter(rutaArchivosPorUnificar+nombreArchivoPorUnificar);
            printerWriter = new PrintWriter(fichero);
            
           for (String string : registros) {
        	   printerWriter.println(string);
           
           }
           
           
		   
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
	          
	           if ( fichero != null){
	              fichero.close();
	           }
	           
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		
        //Se verifica si el archivo existe
        File file = new File(rutaArchivosPorUnificar+nombreArchivoPorUnificar);
        
        if(file.exists()){
     	   return file;
        }else{
     	   return null;
        }
		
		
	}

	public String get(String valor) {

		try {

			if (StringOsmoUtils.esVacio(valor)) {
				return null;
			}

			return valor.trim();

		} catch (Exception e) {
			SimpleLogger.error("Error", e);
			return null;
		}
	}

	// -----------

	public Long getLong(String valor) {

		try {

			if (StringOsmoUtils.esVacio(valor)) {
				return null;

			}

			return Long.parseLong(valor.trim());

		} catch (Exception e) {
			SimpleLogger.error("Error", e);
			return null;
		}
	}

	// -----------

	public BigDecimal getBigDecimal(String valor) {

		try {

			if (StringOsmoUtils.esVacio(valor)) {
				return null;
			}

			BigDecimal bigDecimal = new BigDecimal(valor.trim());
			return bigDecimal;

		} catch (Exception e) {
			SimpleLogger.error("Error", e);
			return null;
		}
	}

	public Boolean getBoolean(String valor) {

		if (StringOsmoUtils.esVacio(valor)) {
			return null;
		}

		if (StringOsmoUtils.esVerdad(valor.trim())) {
			return true;
		} else {
			return false;
		}

	}

	public BigDecimal getMoneda2Decimals(String valor) {

		BigDecimal resultado = getBigDecimal(valor);

		if (resultado != null) {
			resultado = resultado.divide(new BigDecimal(100));

			return resultado;
		} else {
			return null;
		}

	}
	
	
	
	

	public static void main(String[] args) {
		File file = new File("RCCA03201.d50");

		ConvertidorArchivoAsobancaria lectorArchivoAsobancaria = new ConvertidorArchivoAsobancaria(file, "D:\\", "RCCA03201_jc.d50");
		lectorArchivoAsobancaria.generarArchivoFiduciaria();

		// System.out.println(String.format("%-24s", "12312456789789"));
		//
		// BigDecimal bigDecimal = new BigDecimal(1212);
		// bigDecimal = bigDecimal.divide(new BigDecimal(100));
		//
		// System.out.println(bigDecimal);

	}

}