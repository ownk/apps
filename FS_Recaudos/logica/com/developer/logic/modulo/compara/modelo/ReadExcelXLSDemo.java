package com.developer.logic.modulo.compara.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

//import statements
public class ReadExcelXLSDemo {
	
	public ReadExcelXLSDemo() {
		// TODO Auto-generated constructor stub
	}
	
	public void  readExcel(){
		try {
			// Read XLS document - Office 97 -2003 format
			FileInputStream input_document = new FileInputStream(new File(
					"Consulta_Cuenta_Ahorros3403.xls"));

			// Read the Excel Workbook in a instance object
			HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);

			// This will read the sheet for us into another object
			HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);

			// Create iterator object
			Iterator<Row> rowIterator = my_worksheet.iterator();
			Row row = rowIterator.next();
			row = rowIterator.next();
			
			while (rowIterator.hasNext()) {

				// Read Rows from Excel document
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = cellIterator.next();

				int columna = 1;

				while (cellIterator.hasNext()) {
					columna++;

					cell = cellIterator.next();
					// Identify CELL type

					if (columna == 2) {

						String fechaExcel = cell.getStringCellValue();
						Date fecha = getDate(fechaExcel);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String date = sdf.format(fecha); 
						System.out.println(date); 
						System.out.println(fecha);
					}

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t");
						break;
					}
				}

				// To iterate over to the next row
				System.out.println("");
			}

			// Close the XLS file opened for printing
			input_document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	/*
	 * Fecha en formato: Julio 4 de 2015
	 */

	private Date getDate(String fechaExcel) {

		StringTokenizer st = new StringTokenizer(fechaExcel);

		Date date = null;

		while (st.hasMoreTokens()) {

			String mes = st.nextToken();

			String dia = st.nextToken();

			st.nextToken();

			String anho = st.nextToken();

			System.out.println();

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, getMonth(mes));
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
			calendar.set(Calendar.YEAR, Integer.parseInt(anho));

			date = calendar.getTime();

		}

		return date;

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
		ReadExcelXLSDemo demo = new ReadExcelXLSDemo();
		demo.readExcel();
		
	}

}