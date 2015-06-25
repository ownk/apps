package com.developer.logic.modulo.conversion.modelo;

import java.math.BigDecimal;
import java.util.List;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.utils.StringOsmoUtils;

public class ConvertidorArchivoSIFIPorTipoArchivo {
	
	
	
	
	public Boolean createARGE(	String rutaArchivosSIFI,
								String nombreArchivo,
								ProcesoUnificacionArchivos procesoUnificacionArchivos,
								ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir, 
								String usua_usua){
		
		
		
		ArchivoRecaudoOriginalPorConvertirServicio archivoServicio = ArchivoRecaudoOriginalPorConvertirServicio.getInstance();
		List<DetalleArchivoRecaudoOriginalPorConvertir>	listDetalles = archivoServicio.getAllDetallesAROR(archivoRecaudoOriginalPorConvertir.getAror_aror());
		
		
		
		Double totalCheque;
		Double totalEfectivo;
		Double totalArchivo;
		Long   referenciaFinal;
		String estadoSIFI;
		String estadoNOSIFI;
		
		
		for (DetalleArchivoRecaudoOriginalPorConvertir detalleArchivoRecaudoOriginalPorConvertir : listDetalles) {
			
			
			/**
			 * Validaciones generales
			 * =============================================
			 */
			//Tipo de recaudo -Excluir por forma de recaudo. Crear validacion
			
			
			//Validar el tamaño del numero de referencia. Crear validacion
			
			
			//Completar el tamaño con numero de fondo por tipo de archivo. Crear transformacion y validacion
			
			
			//Se verifica si es volante referencia inicia con 90
			
			
			//Determinar estado en encargo SIFI
				//Crear validacion si el encargo no tiene estado pero no es volante
			
				//1. Si el estado esta cancelado se debe crear una validacion especifica
			
				//2. Si el estado esta PCA y el recaudo es RCHE se debe crear validacion especifica
			
			
			//Identificacion del codigo del proyecto posicion 3 de tamaño 4
			
			
			//Validar Identificacion de aportante con los titulares del encargo
				//1. Si el encargo no tiene estado. Crear validacion de que no tiene titular contra quien comparar
				//2. Si tiene estado y no coincide el numero de identificacion del titular se debe crear validacion
			
			
			/**
			 * Transformaciones segun el estado o proyecto
			 * =============================================
			 */	
			//Transformaciones segun el estado o proyecto
			
			//Se busca el proyecto en la hoja de cancelados solo si esta cancelado. Si lo encuentra coloca el numero de encargo asignado
			//En caso de no encontrarlo lo deja vacio.
			
			
			//Si la referencia es un volante se debe crear una validacion
			//Si la referencia es un volante pero el tipo de archivo dice que lo reemplace por el generico se debe crear transformacion
			
			//Se deben consular contra los encargos no sifi
				//1. Si el encargo es un NO_SIFI_ACT y pertenece a un proyecto No SIFI Activo se debe colocar el numero de encargo que se especifique en el proyecto. Crear Transformacion
				
				//2. Si el encargo es un NO_SIFI_INACT se debe mandar al generico de cada tipo de archivo
			
			
			/**
			 * Transfomaciones por formula de distribucion
			 * =============================================
			 */
			//Se realizan transformaciones por formula de distribucion
				//1. Si estadoNOSIFI es NO_SIFI_ACT  o estadoSIFI es CAN y PCA y el proyecto pertenece a formula de distribucion
					//Se debe crear nuevos registros con la distribucion correspondiente y se debe ajustar correctamente el valor. Crear transformacion
			
					//Verificar que no exista en mas de una formula
			
			
			
			/**
			 * Transfomaciones por tipo de archivo de recaudo
			 * =============================================
			 */
			
			//Si el encargo esta ACT BPC o INA y es de universitas
				//Se debe crear transformacion en la que se completa el numero de encargo con el numero de fondo
			
			//Si es de universitas y no esta en los estados se debe colocar el encargo generico
			
			
			/**
			 * Transfomaciones asignacion encargo generico
			 * =============================================
			 */
			//If (Estado_SIFI = "___" And Estado_NO_SIFI = "" And aun no tiene cargo final asignado Or (Estado_SIFI = "CAN" And Estado_NO_SIFI = "" And no tiene encargo final asignado Or (Forma_de_Recaudo = "RCHE" And Estado_SIFI = "PCA") Then
				//1.if Estado_SIFI = "___" And Estado_NO_SIFI = "" And Range("G" & Registro).Value = "") Then 'Nov 5 2014: FAB Se incluye esta línea para que si no encuentra estado no cambie el número del encargo al genérico en Rentafácil
					//Crear validacion de que es volante
				
				//2.else En otro caso se valida que no por cuenta que no es universitas y coloca el generico especifico
			
			
				//3 if si el tipo de archivo es de universitas asignar el encargo generico 
			
				
			
			//Si al final no tiene encargo asignado se debe generar mensaje de error
			//Generar mensaje de error; 
			
			
			
			
				
			
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		return null;
		
	}
	
	// -----------

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

	// -----------

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

	

	private BigDecimal getMoneda2Decimals(String valor) {

		BigDecimal resultado = getBigDecimal(valor);

		if (resultado != null) {
			resultado = resultado.divide(new BigDecimal(100));

			return resultado;
		} else {
			return null;
		}

	}
	
	
}
