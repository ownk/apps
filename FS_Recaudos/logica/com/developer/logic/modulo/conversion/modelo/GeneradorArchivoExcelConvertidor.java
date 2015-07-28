package com.developer.logic.modulo.conversion.modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.DetalleResumenConversionSIFI;
import com.developer.mybatis.DBManagerFSRecaudos;

//import statements
public class GeneradorArchivoExcelConvertidor {


	public GeneradorArchivoExcelConvertidor() {
		
	}

	
	public File generarArchivo(Long aror_aror , String rutaArchivo, StringBuffer mensajeErrorOut) {

		
		ArchivoRecaudoOriginalPorConvertirServicio archivoServicio = new ArchivoRecaudoOriginalPorConvertirServicio();
		List<DetalleResumenConversionSIFI> listResumenSIFI = archivoServicio.getResumenConversionSIFIAROR(aror_aror);
		
		
		if (listResumenSIFI!=null && listResumenSIFI.size() > 0) {
			
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Convertidor");
	          
	        //This data needs to be written (Object[])
	        Map<Long, Object[]> data = new TreeMap<Long, Object[]>();
	        data.put(new Long(1), new Object[] {"id reg original", "Fecha recaudo", "Oficina BSC", "Oficina SIFI", "Estado SIFI", "Refencia Original", "Referencia Final", "Aportante", "Vlr.Efectivo", "Vlr.Cheque", "Vlr.Total", "Con BSC 1", "Tipo Recaudo", "Comprobante", "Cons BSC 2"});
	       
	        Long filas=new Long(2);
	        Double vlrTotalEfectivo = new Double(0);
	        Double vlrTotalCheque = new Double(0);
	        Double vlrTotalRecaudo = new Double(0);
	        
	        for (DetalleResumenConversionSIFI detalle : listResumenSIFI) {
	        	data.put(filas, new Object[] {detalle.getDaror_id_reg(), detalle.getDaror_freca(), detalle.getDaror_ofic(), detalle.getDarge_ofic(), detalle.getDarge_erds(), detalle.getDaror_referencia(), detalle.getDarge_referencia(), detalle.getDaror_aportante(), detalle.getDarge_vefe_double(), detalle.getDarge_vche_double(), detalle.getDarge_vtot_double(), detalle.getDaror_cons_bsc_1(), detalle.getDaror_tipo_reca(),detalle.getDaror_comp(), detalle.getDaror_cons_bsc_2()});
	        	filas++;
	        	
	        	vlrTotalEfectivo = vlrTotalEfectivo+detalle.getDarge_vefe_double();
	        	vlrTotalCheque = vlrTotalCheque+detalle.getDarge_vche_double();
	        	vlrTotalRecaudo= vlrTotalRecaudo+detalle.getDarge_vtot_double();
			}
	        
	        filas++;
	        data.put(filas, new Object[] {"", "", "", "", "", "", "", "", vlrTotalEfectivo, vlrTotalCheque, vlrTotalRecaudo, "", "", "", ""});
		       
	       
	          
	        //Iterate over data and write to sheet
	        Set<Long> keyset = data.keySet();
	        int rownum = 0;
	        
	        for (Long key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	                else if(obj instanceof Double)
	                    cell.setCellValue((Double)obj);
	            }
	        }
	        try
	        {
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(rutaArchivo));
	            workbook.write(out);
	            out.close();
	            System.out.println(rutaArchivo+"on disk.");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
		}else{
			mensajeErrorOut.append("No existen registros por generar ");
			
		}

		return null;
		
	}

	public static void main(String[] args) {
		
		//Se inicializa la configuracion del gestor de bases de datos
		SimpleLogger.info("Inicializando conexion database FSRecaudos");
		DBManagerFSRecaudos.initConfiguration();
		
		GeneradorArchivoExcelConvertidor excelConvertidor = new GeneradorArchivoExcelConvertidor();
		excelConvertidor.generarArchivo(new Long(1242), "excel.xlsx", new StringBuffer());
		
		
	}
	

}