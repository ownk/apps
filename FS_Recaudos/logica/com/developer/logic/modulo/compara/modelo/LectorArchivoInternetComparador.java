package com.developer.logic.modulo.compara.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.compara.dto.DetalleComparacionArchivoRecaudo;
import com.developer.logic.modulo.compara.dto.HomologacionTipoRecaudoComparador;
import com.developer.logic.modulo.utils.StringOsmoUtils;

//import statements
public class LectorArchivoInternetComparador {

	File fileExcel;
	Boolean isExcel;

	List<Row> listRow;
	List<HomologacionTipoRecaudoComparador> homologacionesTipoRecaudo;

	public LectorArchivoInternetComparador(File fileInternet) {
		this.fileExcel = fileInternet;

		ComparacionArchivoRecaudoServicio comparacionServicio = new ComparacionArchivoRecaudoServicio();
		this.homologacionesTipoRecaudo = comparacionServicio.getAllHomologacionesTipoRecaudo();
		
		listRow = new ArrayList<Row>();
		isExcel = true;

		// Se verifica si es excel
		if (!isXLS(fileExcel)) {

			isExcel = isXLSX(fileExcel);
		}

	}

	public Boolean isXLSX(File fileExcel) {
		Boolean isXLSX = true;

		try {
			FileInputStream file = new FileInputStream(fileExcel);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				listRow.add(row);

			}

			// Close the XLS file opened for printing
			file.close();

			
		} catch (Exception e) {
	
			isXLSX = false;
		}

		return isXLSX;

	}

	public Boolean isXLS(File fileExcel) {

		Boolean isXLS = true;

		try {
			// Read XLS document - Office 97 -2003 format
			FileInputStream input_document = new FileInputStream(fileExcel);

			// Read the Excel Workbook in a instance object
			HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);

			// This will read the sheet for us into another object
			HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);

			// Create iterator object
			Iterator<Row> rowIterator = my_worksheet.iterator();
			while (rowIterator.hasNext()) {
				// Read Rows from Excel document
				Row row = rowIterator.next();
				listRow.add(row);

			}

			// Close the XLS file opened for printing
			input_document.close();
			
		} catch (Exception e) {
	
			isXLS = false;
		}

		return isXLS;

	}

	public List<DetalleComparacionArchivoRecaudo> generarDetalleArchivo(
			StringBuffer mensajeErrorOut) {

		if (isExcel && listRow.size() > 0) {
			
			try {
				
			List<DetalleComparacionArchivoRecaudo> detalles = new ArrayList<DetalleComparacionArchivoRecaudo>();

			int filas = 0;
			for (Row row : listRow) {
				filas++;

				// Se lee los detalles apartir de la 3ra fial del archivo
				if (filas > 2) {

					Iterator<Cell> cellIterator = row.cellIterator();
					Cell cell = cellIterator.next();

					int columna = 1;
					
					
					DetalleComparacionArchivoRecaudo detalle = new DetalleComparacionArchivoRecaudo();
					detalle.setDcpar_fuente(DetalleComparacionArchivoRecaudo.FUENTE_INTERNET);
					detalle.setDcpar_id_reg_orig(""+filas);
					
					Date dateNormalizado = null;
					String fechaRecaudo = null; 
					String tipoRecaudo = null;
					String oficina = null;
					Double valor = null;
					String referencia = null;
					String observacion = null;
					
					
					while (cellIterator.hasNext()) {
						columna++;
						cell = cellIterator.next();

						//Fecha 
						if (columna == 2) {
							fechaRecaudo = cell.getStringCellValue();
							dateNormalizado = getDate(fechaRecaudo);
						
						}
						
						//Tipo de recaudo
						if (columna == 3) {
							tipoRecaudo = cell.getStringCellValue().trim();
						
						}
						
						//Oficina
						if (columna == 4) {
							oficina = cell.getStringCellValue();
						
						}
						
						//Valor
						if (columna == 5) {
							
							String valorString = cell.getStringCellValue();
							Boolean isNegativo = false;
							valorString = valorString.replace(".", "");
							valorString = valorString.replace(",", ".");
							valorString = valorString.replace("$", "");
							
							
							
							
							if(valorString.contains("(") & valorString.contains(")") ){
								isNegativo = true;
								
								valorString = valorString.replace("(", "");
								valorString = valorString.replace(")", "");
								
							}
							
							BigDecimal valorBigDecimal = getValorMoneda(valorString);
							valor = getRound2Decimals(valorBigDecimal.doubleValue());
							
							if(isNegativo){
								valor = valor*(-1);
							}
						
						}
						
						//Referencia
						if(columna == 6) {
							
							referencia="-";
							if(!cell.getStringCellValue().isEmpty()){
								referencia = cell.getStringCellValue();
							}
							
						}
						
						//Observacion
						if(columna == 7) {
							
							observacion="-";
							if(!cell.getStringCellValue().isEmpty()){
								observacion = cell.getStringCellValue();
							}
							
						}
						
						
					}
					
					
					detalle.setDcpar_freca_norm(dateNormalizado);
					detalle.setDcpar_freca_orig(fechaRecaudo);
					detalle.setDcpar_observ("Recaudo Simple,"+observacion );
					detalle.setDcpar_ofic_norm(oficina);
					detalle.setDcpar_ofic_orig(oficina);
					detalle.setDcpar_referencia(referencia);
					detalle.setDcpar_treca_norm(getHomologacionTipoRecaudo(tipoRecaudo));
					detalle.setDcpar_treca_orig(tipoRecaudo);	
										
					detalle.setDcpar_valor(valor);

					detalles.add(detalle);

				}
			}
				return detalles;
			
			} catch (Exception e) {
				
				mensajeErrorOut.append("Error leyendo registro");
				return null;
			}
			

			
		}else{
			mensajeErrorOut.append("Archivo no tiene registros");
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
	
	
	/*
	 * Fecha en formato: Julio 4 de 2015
	 */

	private Date getDate(String fechaExcel) {

		StringTokenizer st = new StringTokenizer(fechaExcel);

		Date dateNormalizado = null;
		
		try {
			while (st.hasMoreTokens()) {
	
				String mes = st.nextToken();
	
				String dia = st.nextToken();
	
				st.nextToken();
	
				String anho = st.nextToken();
	
							Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.MONTH, getMonth(mes));
				calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
				calendar.set(Calendar.YEAR, Integer.parseInt(anho));
	
				Date date = calendar.getTime();
				
				DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				dateNormalizado =formatter.parse(formatter.format(date));
	
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}


		return dateNormalizado;

	}

	private int getMonth(String mes) {

		int monthInt = -1;

		String monthString = mes.trim().toUpperCase();

		if (monthString.equals("ENERO")) {

			return Calendar.JANUARY;

		}

		if (monthString.equals("FEBRERO")) {

			return Calendar.FEBRUARY;

		}
		if (monthString.equals("MARZO")) {

			return Calendar.MARCH;

		}

		if (monthString.equals("ABRIL")) {

			return Calendar.APRIL;

		}

		if (monthString.equals("MAYO")) {

			return Calendar.MAY;

		}

		if (monthString.equals("JUNIO")) {

			return Calendar.JUNE;

		}

		if (monthString.equals("JULIO")) {

			return Calendar.JULY;

		}

		if (monthString.equals("AGOSTO")) {

			return Calendar.AUGUST;

		}

		if (monthString.equals("SEPTIEMBRE")) {

			return Calendar.SEPTEMBER;

		}

		if (monthString.equals("OCTUBRE")) {

			return Calendar.OCTOBER;

		}

		if (monthString.equals("NOVIEMBRE")) {

			return Calendar.NOVEMBER;

		}

		if (monthString.equals("DICIEMBRE")) {

			return Calendar.DECEMBER;

		}

		return monthInt;

	}

	public static void main(String[] args) {
		LectorArchivoInternetComparador demo = new LectorArchivoInternetComparador(
				new File("Consulta_Cuenta_Ahorros3403.xls"));

		StringBuffer mensajeErrorOut = new StringBuffer();
		demo.generarDetalleArchivo(mensajeErrorOut);

	}

}