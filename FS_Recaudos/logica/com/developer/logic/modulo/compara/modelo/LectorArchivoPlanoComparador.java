package com.developer.logic.modulo.compara.modelo;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.compara.dto.DetalleComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.HomologacionTipoRecaudoComparador;
import com.developer.logic.modulo.general.modelo.LectorArchivoPlanoUtils;
import com.developer.logic.modulo.utils.StringOsmoUtils;

public class LectorArchivoPlanoComparador {

	File fileBSC;
	String[][] registroEncabezado;
	String[][] registrosDetalle;
	Boolean hayRegistros = false;
	
	List<HomologacionTipoRecaudoComparador> homologacionesTipoRecaudo;
	
	Long totalRegistrosFileOrigen;


	public LectorArchivoPlanoComparador(File fileBSC) {
		
		totalRegistrosFileOrigen = new Long(0);
		ComparacionArchivoRecaudoServicio comparacionServicio = new ComparacionArchivoRecaudoServicio();
		this.homologacionesTipoRecaudo = comparacionServicio.getAllHomologacionesTipoRecaudo();
		
		
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
			 * 30.		Aportante
			 * 16.		Valor efectivo
			 * 16.		Valor cheque	
			 * 16.		Valor total del recaudo
			 * 3.		Cons BSC 1
			 * 4.		Forma de recaudo
			 * 10.		Comprobante
			 * 11.		Cons BSC 2
			 */
			
			
			
			
			
			if (fileBSC != null) {
				Integer[] longitudes = new Integer[] { 1, 8, 5, 24, 30, 16, 16, 16,
						3, 4, 10, 9 };
				
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

	public List<DetalleComparacionArchivoRecaudo> generarDetalleArchivo(StringBuffer mensajeErrorOut) {

		/***************************************************************************
		 * Informacion del archivo
		 * 
		 * -Primera linea corresponde al encabezado 
		 * -El resto de lineas corresponden a los recaudos	
		 ***************************************************************************/
		

				
		if (hayRegistros) {			
			
			List<DetalleComparacionArchivoRecaudo> detalles = new ArrayList<DetalleComparacionArchivoRecaudo>();
			
			try {
				
				for (int i = 1; i < registrosDetalle.length; i++) {
					
					
					String tipoRecaudo = getHomologacionTipoRecaudo(registrosDetalle[i][9]);
					
					String fechaRecaudo = registrosDetalle[i][1];
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
					Date dateNormalizado = simpleDateFormat.parse(fechaRecaudo);
					
					
					//Si el recaudo es combinado se debe crear un detalle para efectivo y cheque
					if(tipoRecaudo.equals("RCOM")){
					
						//Se crea el detalle para efectivo
						DetalleComparacionArchivoRecaudo detalleEfectivo = new DetalleComparacionArchivoRecaudo();
						detalleEfectivo.setDcpar_freca_norm(dateNormalizado);
						detalleEfectivo.setDcpar_freca_orig(fechaRecaudo);
						detalleEfectivo.setDcpar_fuente(DetalleComparacionArchivoRecaudo.FUENTE_PLANO);
						detalleEfectivo.setDcpar_id_reg_orig(""+(i+1));
						detalleEfectivo.setDcpar_observ("Recaudo Combinado, Aportante: "+registrosDetalle[i][4]+", Oficina: "+registrosDetalle[i][2]+", Jornada: "+registrosDetalle[i][8]+", Comprobante: "+registrosDetalle[i][10]+" Valor total: "+registrosDetalle[i][7]);
						detalleEfectivo.setDcpar_ofic_norm(registrosDetalle[i][2]);
						detalleEfectivo.setDcpar_ofic_orig(registrosDetalle[i][2]);
						detalleEfectivo.setDcpar_referencia(registrosDetalle[i][3]);
						detalleEfectivo.setDcpar_treca_norm(getHomologacionTipoRecaudo("REFE"));
						detalleEfectivo.setDcpar_treca_orig(tipoRecaudo);	
						
						BigDecimal valorEfectivo = getValorMoneda(registrosDetalle[i][5]);
						double nuevoValorEfectivo = getRound2Decimals(valorEfectivo.doubleValue());
						detalleEfectivo.setDcpar_valor(nuevoValorEfectivo);
						
						detalles.add(detalleEfectivo);
						
						
						//Se crea el detalle en cheque
						
						DetalleComparacionArchivoRecaudo detalleCheque = new DetalleComparacionArchivoRecaudo();
						detalleCheque.setDcpar_freca_norm(dateNormalizado);
						detalleCheque.setDcpar_freca_orig(fechaRecaudo);
						detalleCheque.setDcpar_fuente(DetalleComparacionArchivoRecaudo.FUENTE_PLANO);
						detalleCheque.setDcpar_id_reg_orig(""+(i+1));
						detalleCheque.setDcpar_observ("Recaudo Combinado, Aportante: "+registrosDetalle[i][4]+", Oficina: "+registrosDetalle[i][2]+", Jornada: "+registrosDetalle[i][8]+", Comprobante: "+registrosDetalle[i][10]+" Valor total: "+registrosDetalle[i][7]);
						detalleCheque.setDcpar_ofic_norm(registrosDetalle[i][2]);
						detalleCheque.setDcpar_ofic_orig(registrosDetalle[i][2]);
						detalleCheque.setDcpar_referencia(registrosDetalle[i][3]);
						detalleCheque.setDcpar_treca_norm(getHomologacionTipoRecaudo("RCHE"));
						detalleCheque.setDcpar_treca_orig(tipoRecaudo);	
						
						BigDecimal valorCheque = getValorMoneda(registrosDetalle[i][6]);
						double nuevoValorCheque = getRound2Decimals(valorCheque.doubleValue());
						
						detalleCheque.setDcpar_valor(nuevoValorCheque);
						
						
						detalles.add(detalleCheque);
						
						
						
						
						
					
					}else{
						
						//Se crea el detalle 
						DetalleComparacionArchivoRecaudo detalle = new DetalleComparacionArchivoRecaudo();
						detalle.setDcpar_freca_norm(dateNormalizado);
						detalle.setDcpar_freca_orig(fechaRecaudo);
						detalle.setDcpar_fuente(DetalleComparacionArchivoRecaudo.FUENTE_PLANO);
						detalle.setDcpar_id_reg_orig(""+(i+1));
						detalle.setDcpar_observ("Recaudo Simple, Aportante: "+registrosDetalle[i][4]+", Oficina: "+registrosDetalle[i][2]+", Jornada: "+registrosDetalle[i][8]+", Comprobante: "+registrosDetalle[i][10]);
						detalle.setDcpar_ofic_norm(registrosDetalle[i][2]);
						detalle.setDcpar_ofic_orig(registrosDetalle[i][2]);
						detalle.setDcpar_referencia(registrosDetalle[i][3]);
						detalle.setDcpar_treca_norm(getHomologacionTipoRecaudo(tipoRecaudo));
						detalle.setDcpar_treca_orig(tipoRecaudo);	
						
						
						BigDecimal valorTotal = getValorMoneda(registrosDetalle[i][7]);
						double nuevoValorTotal = getRound2Decimals(valorTotal.doubleValue());
						
						detalle.setDcpar_valor(nuevoValorTotal);
						
						detalles.add(detalle);
						
					}
	
				}
				
				return detalles;
			
			} catch (Exception e) {
				
				mensajeErrorOut.append("Archivo"+fileBSC.getName()+": error leyendo registros del archivo.");
				return null;
			}

		} else {
			
			mensajeErrorOut.append("Archivo"+fileBSC.getName()+": no tiene registros a transformar");
			return null;
		}

	}

	
	
	private String getHomologacionTipoRecaudo(String tipoRecaudoOriginal){
		
		String homologacion= tipoRecaudoOriginal;
		
				
		if(homologacionesTipoRecaudo!= null && homologacionesTipoRecaudo.size()>0){
			
			for (HomologacionTipoRecaudoComparador homologacionTipoRecaudoComparador : homologacionesTipoRecaudo) {
				
				if(homologacionTipoRecaudoComparador.getHtpr_freca_orig().equals(tipoRecaudoOriginal)){
					homologacion = homologacionTipoRecaudoComparador.getHtpr_freca_norm();
					break;
				}
				
			}
			
		}
		
		return homologacion;
	}
	
	private BigDecimal getBigDecimal(String valor) {

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

	private BigDecimal getValorMoneda(String valor) {

		BigDecimal resultado = getBigDecimal(valor);
		return resultado;
		
	}
	
	private double getRound2Decimals(double valor) {

		DecimalFormat twoDForm = new DecimalFormat("#.00");
	    return Double.parseDouble(twoDForm.format(valor).replace(",", "."));

		

	}
	
		
	
	public static void main(String[] args) {
		File file = new File("Cta_4125_3004.d25");
		StringBuffer mensajeErrorOut = new StringBuffer();
		LectorArchivoPlanoComparador lector = new LectorArchivoPlanoComparador(file);
		lector.generarDetalleArchivo(mensajeErrorOut);


	}



}