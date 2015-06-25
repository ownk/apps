package com.developer.logic.modulo.conversion.modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.general.modelo.LectorArchivoPlanoUtils;

public class LectorArchivoBSC {

	File fileBSC;
	String[][] registroEncabezado;
	String[][] registrosDetalle;
	Boolean hayRegistros = false;
	
	Long totalRegistrosFileOrigen;


	public LectorArchivoBSC(File fileBSC) {
		
		totalRegistrosFileOrigen = new Long(0);
		
		leerRegistros(fileBSC);
	}

	
	/**
	 * Revisa si hay registros en el archivo especificado
	 * 
	 * fileBSC Archivo con informacion de recaudos
	 */
	private Boolean leerRegistros(File fileBSC) {

		this.fileBSC = fileBSC;

		try {

			this.hayRegistros = false;
			
			
			
			/**
			 * Lector de registros de encabezado
			 * 
			 * tamano	descripcion
			 * ====== 	====================
			 * 1. 		Tipo de registro
			 * 80.		Titulo
			 * 13.		Cuenta recaudadora
			 * 18.		Valor total de archivo
			 * 5.		Numero de registros
			 * 8.		Fecha de archivo	
			 */
			
			if (fileBSC != null) {
				Integer[] longitudes = new Integer[] { 1, 80, 13, 18, 5, 8};
				registroEncabezado = LectorArchivoPlanoUtils.leerArchivo(
						fileBSC.getAbsolutePath(), longitudes);
			}
			
			
			
			/**
			 * Lector de registros de detalle
			 * 
			 * tamano	descripcion
			 * ====== 	====================
			 * 1. 		Tipo de registro
			 * 8.		Fecha de recaudo
			 * 5.		Oficina de recaudo
			 * 24.		Referencia (Encargo o Volante)
			 * 25.		Aportante
			 * 16.		Valor efectivo
			 * 16.		Valor cheque	
			 * 16.		Valor total del recaudo
			 * 3.		Cons BSC 1
			 * 4.		Forma de recaudo
			 * 8.		Comprobante
			 * 11.		Cons BSC 2
			 */
			
			
			
			
			
			if (fileBSC != null) {
				Integer[] longitudes = new Integer[] { 1, 8,5, 24, 30, 16, 16, 16,
						3, 4, 7, 3 };
				
				registrosDetalle = LectorArchivoPlanoUtils.leerArchivo(
						fileBSC.getAbsolutePath(), longitudes);

				if (registrosDetalle == null || registrosDetalle.length < 1) {
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

	public List<DetalleArchivoRecaudoOriginalPorConvertir> generarDetalleArchivo(StringBuffer mensajeErrorOut) {

		/***************************************************************************
		 * Informacion del archivo
		 * 
		 * -Primera linea corresponde al encabezado 
		 * -El resto de lineas corresponden a los recaudos	
		 ***************************************************************************/
		
		String cuentaBancaria;
				
		if (hayRegistros) {
			
			

			cuentaBancaria = registroEncabezado[0][2];
			
			
			
			List<DetalleArchivoRecaudoOriginalPorConvertir> detalles = new ArrayList<DetalleArchivoRecaudoOriginalPorConvertir>();
			
			for (int i = 1; i < registrosDetalle.length; i++) {
				
				DetalleArchivoRecaudoOriginalPorConvertir detalle = new DetalleArchivoRecaudoOriginalPorConvertir();
				
				detalle.setDaror_freca(registrosDetalle[i][1]);
				detalle.setDaror_ofic(registrosDetalle[i][2]);
				detalle.setDaror_referencia(registrosDetalle[i][3]);
				detalle.setDaror_aportante(registrosDetalle[i][4]);
				detalle.setDaror_vefe(registrosDetalle[i][5]);
				detalle.setDaror_vche(registrosDetalle[i][6]);
				detalle.setDaror_vtot(registrosDetalle[i][7]);
				detalle.setDaror_cons_bsc_1(registrosDetalle[i][8]);
				detalle.setDaror_tipo_reca(registrosDetalle[i][9]);
				
				detalle.setDaror_comp(registrosDetalle[i][10]);
				detalle.setDaror_cons_bsc_2(registrosDetalle[i][11]);
				detalle.setDaror_cta_reca(cuentaBancaria);
				detalle.setDaror_id_reg(new Long(i+1));
				detalle.setDaror_registro(""+registrosDetalle[i][0]+
											registrosDetalle[i][1]+
											registrosDetalle[i][2]+
											registrosDetalle[i][3]+
											registrosDetalle[i][4]+
											registrosDetalle[i][5]+
											registrosDetalle[i][6]+
											registrosDetalle[i][7]+
											registrosDetalle[i][8]+
											registrosDetalle[i][9]+
											registrosDetalle[i][10]+
											registrosDetalle[i][11]);
				detalles.add(detalle);

			}
			
			

			return detalles;

		} else {
			
			mensajeErrorOut.append("Archivo"+fileBSC.getName()+"no tiene registros a transformar");
			return null;
		}

	}

	
	
		
	
	public static void main(String[] args) {
		File file = new File("Cta_4125_3004.d25");
		StringBuffer mensajeErrorOut = new StringBuffer();
		LectorArchivoBSC lector = new LectorArchivoBSC(file);
		lector.generarDetalleArchivo(mensajeErrorOut);


	}



}