package com.developer.logic.modulo.conversion.modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.DetalleResumenConversionSIFI;
import com.developer.logic.modulo.utils.StringOsmoUtils;
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
	        
	        Long filas=new Long(0);
	        data.put(filas++, new Object[] {"id reg original", "Fecha recaudo", "Oficina BSC", "Oficina SIFI", "Estado SIFI", "Refencia Original", "Referencia Modificada", "Aportante", "Vlr.Efectivo", "Vlr.Cheque", "Vlr.Total", "Con BSC 1", "Tipo Recaudo", "Comprobante", "Cons BSC 2"});
	       
	        
	        Double vlrTotalEfectivo = new Double(0);
	        Double vlrTotalCheque = new Double(0);
	        Double vlrTotalRecaudo = new Double(0);
	        
	        for (DetalleResumenConversionSIFI detalle : listResumenSIFI) {
	        	
	        	
	        	String referenciaFinal = detalle.getDarge_referencia();
	        	if(getLong(detalle.getDaror_referencia()).equals(getLong(detalle.getDarge_referencia()))){
	        		
	        		referenciaFinal = "";
	        	}
	        	
	        	data.put(filas++, new Object[] {detalle.getDaror_id_reg(), detalle.getDaror_freca(), detalle.getDaror_ofic(), detalle.getDarge_ofic(), detalle.getDarge_erds(), detalle.getDaror_referencia(), referenciaFinal, detalle.getDaror_aportante(), detalle.getDarge_vefe_double(), detalle.getDarge_vche_double(), detalle.getDarge_vtot_double(), detalle.getDaror_cons_bsc_1(), detalle.getDaror_tipo_reca(),detalle.getDaror_comp(), detalle.getDaror_cons_bsc_2()});
	        	
	        	
	        	vlrTotalEfectivo = vlrTotalEfectivo+detalle.getDarge_vefe_double();
	        	vlrTotalCheque = vlrTotalCheque+detalle.getDarge_vche_double();
	        	vlrTotalRecaudo= vlrTotalRecaudo+detalle.getDarge_vtot_double();
			}
	       
	        data.put(filas++, new Object[] {"", "", "", "", "", "", "", "", vlrTotalEfectivo, vlrTotalCheque, vlrTotalRecaudo, "", "", "", ""});
		       
	       
	        
	        
	        /* Get access to HSSFCellStyle */
            XSSFCellStyle my_style = workbook.createCellStyle();
            /* Create HSSFFont object from the workbook */
            XSSFFont my_font=workbook.createFont();
            /* set the weight of the font */
            my_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            /* attach the font to the style created earlier */
            my_style.setFont(my_font);
	        
	        
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
	               
	               if(rownum==1 || rownum == filas){
	            	   
	            	   cell.setCellStyle(my_style);
	               }
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
	
	private Long getLong(String valor) {

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

	public static void main(String[] args) {
		
		//Se inicializa la configuracion del gestor de bases de datos
		SimpleLogger.info("Inicializando conexion database FSRecaudos");
		DBManagerFSRecaudos.initConfiguration();
		
		GeneradorArchivoExcelConvertidor excelConvertidor = new GeneradorArchivoExcelConvertidor();
		excelConvertidor.generarArchivo(new Long(1242), "excel.xlsx", new StringBuffer());
		
		
	}
	

}