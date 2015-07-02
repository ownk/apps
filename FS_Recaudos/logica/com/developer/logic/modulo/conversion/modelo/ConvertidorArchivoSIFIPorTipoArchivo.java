package com.developer.logic.modulo.conversion.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DistribucionPorFormulaPorcentaje;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.logic.modulo.conversion.dto.ErrorArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.EstadoPlanAplicaPlanGenerico;
import com.developer.logic.modulo.conversion.dto.EstadoPlanFormulaDistribucion;
import com.developer.logic.modulo.conversion.dto.OficinaRecaudo;
import com.developer.logic.modulo.conversion.dto.ParametroGeneralConversion;
import com.developer.logic.modulo.conversion.dto.ProcesoConversionArchivos;
import com.developer.logic.modulo.conversion.dto.ProyectoCancelado;
import com.developer.logic.modulo.conversion.dto.ProyectoConFormulaDistribucion;
import com.developer.logic.modulo.conversion.dto.ProyectoNoSIFIActivo;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;
import com.developer.logic.modulo.conversion.dto.TipoErrorArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.TipoTransformacionArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.TipoValidacionArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.TransformacionArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.ValidacionArchivoRecaudo;
import com.developer.logic.modulo.general.modelo.LectorArchivoPlanoUtils;
import com.developer.logic.modulo.utils.StringOsmoUtils;
import com.developer.mybatis.DBManagerFSRecaudos;

public class ConvertidorArchivoSIFIPorTipoArchivo {
	
	
	int tamMaximoReferencia;
	List<EstadoPlanFormulaDistribucion> estadosPlanFormulaDistribucion;
	List<EstadoPlanAplicaPlanGenerico> estadosPlanAplicaPlanGenerico;
	List<TipoArchivoRecaudoConvertidor> tiposArchivoRecaudo;
	
	
	
	public Boolean createARGE(	String rutaArchivosSIFI,
								String nombreArchivo,
								ProcesoConversionArchivos procesoConversionArchivos,
								ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir, 
								String usua_usua){
		
		
		System.out.println("iniciando ... proceso:"+procesoConversionArchivos.getPrco_prco()+" archivo:"+archivoRecaudoOriginalPorConvertir.getAror_nombre());
		ArchivoRecaudoOriginalPorConvertirServicio archivoServicio = new ArchivoRecaudoOriginalPorConvertirServicio();
		List<DetalleArchivoRecaudoOriginalPorConvertir>	listDetallesArchivoOriginal = archivoServicio.getAllDetallesAROR(archivoRecaudoOriginalPorConvertir.getAror_aror());
		List<DetalleArchivoRecaudoGeneradoSIFI> listDetallesArchivoGenerado = new ArrayList<DetalleArchivoRecaudoGeneradoSIFI>();
		
		
		Double totalCheque = new Double(0);
		Double totalEfectivo = new Double(0);
		Double totalArchivo = new Double(0);
		
		ParametroGeneralConversionServicio parametroGeneralConversionServicio = new ParametroGeneralConversionServicio();
		ParametroGeneralConversion parametroTamMax =  parametroGeneralConversionServicio.getParametroGeneral(ParametroGeneralConversionServicio.TAMANHO_MAX_REFERENCIA);
		ParametroGeneralConversion parametroPrefVolante =  parametroGeneralConversionServicio.getParametroGeneral(ParametroGeneralConversionServicio.PREFIJO_VOLANTE);
		
		
		SIFIServicio sifiServicio = new SIFIServicio();
		EncargoFiduciarioNoSIFIServicio noSIFIServicio = new EncargoFiduciarioNoSIFIServicio();
		TipoArchivoRecaudoConvertidorServicio tipoArchivoServicio = new TipoArchivoRecaudoConvertidorServicio();
		ProyectoRecaudoServicio proyectoRecaudoServicio = new ProyectoRecaudoServicio();
		FormulaDistribucionPorcentajeServicio formulaDistribucionPorcentajeServicio = new FormulaDistribucionPorcentajeServicio();
		OficinaRecaudoServicio oficinaRecaudoServicio = new OficinaRecaudoServicio();
		
		estadosPlanFormulaDistribucion = formulaDistribucionPorcentajeServicio.getAllEstadosAplicaFormula();
		estadosPlanAplicaPlanGenerico = tipoArchivoServicio.getEstadosAplicaPlanGenericoPorTPAR(archivoRecaudoOriginalPorConvertir.getAror_tpar());
		tiposArchivoRecaudo = tipoArchivoServicio.getAllTiposArchivo();
		
		if(parametroTamMax!=null && parametroTamMax.getPara_valor()!=null && parametroPrefVolante!=null && parametroPrefVolante.getPara_valor()!=null){
			
			List<TransformacionArchivoRecaudo> 	listTransformaciones = new ArrayList<TransformacionArchivoRecaudo>();
			List<ValidacionArchivoRecaudo> 		listValidaciones 	 = new ArrayList<ValidacionArchivoRecaudo>();
			List<ErrorArchivoRecaudo> 			listErrores 		 = new ArrayList<ErrorArchivoRecaudo>();
			
			tamMaximoReferencia = Integer.parseInt(parametroTamMax.getPara_valor());
			
			
			for (DetalleArchivoRecaudoOriginalPorConvertir detalleArchivo : listDetallesArchivoOriginal) {
				
				List<DetalleArchivoRecaudoGeneradoSIFI> detallesPorRegistro = new ArrayList<DetalleArchivoRecaudoGeneradoSIFI>();
				
				String estadoSIFI 		= null;
				String estadoNOSIFI 	= null;
				String formaRecaudo 	= null;
				String oficinaOriginal 	= null;
				String oficinaFinal 	= null;
				
				Long   	referenciaOriginal = null;
				Long   	referenciaFinal = null;;
				Long 	aportante = null;
				Long 	proyecto = null;
				
				
				
				Boolean esEncargo = true;
				Boolean esProyectoCancelado = null;
				Boolean esProyectoNOSIFI_ACTIVO= null;
				Boolean isEstadoPlanFormulaDistribucion = null;
				Boolean isEstadoPlanAplicaPlanGenerico = null;
				Boolean tieneFormulaDistribucion = null;
				
				Boolean detallesCreados = false;
				/**
				 * Validaciones generales
				 * =============================================
				 */
				try{
					
					formaRecaudo = detalleArchivo.getDaror_tipo_reca();
					aportante = getLong(detalleArchivo.getDaror_aportante());
					oficinaOriginal = detalleArchivo.getDaror_ofic();
					
					//Tipo de recaudo -Excluir por forma de recaudo. Crear validacion
					if(!formaRecaudo.equals("RNDB")){
						
						//Validar el tamaño del numero de referencia. Crear validacion
						if(detalleArchivo.getDaror_referencia()!=null){
							
							//Se eliminan los espacios y ceros a la izquierda
							referenciaOriginal = getLong(detalleArchivo.getDaror_referencia());
							proyecto = getProyecto(""+referenciaOriginal);
							
							//Completar el tamaño con numero de fondo por tipo de archivo. Crear transformacion y validacion
							if(referenciaOriginal.toString().length()<tamMaximoReferencia){
								
								referenciaFinal = completarReferenciaPorTipoArchivo(referenciaOriginal, archivoRecaudoOriginalPorConvertir.getAror_tpar());
								
								
								//TODO Crear validacion
								ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
								validacion.setVlar_aror(detalleArchivo.getDaror_aror());
								validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_TAM_REF_MENOR);
								validacion.setVlar_valor_descri("REF:"+referenciaOriginal+" Tam:"+referenciaOriginal.toString().length());
								
								listValidaciones.add(validacion);
								
								//Se crea transformacion de referencia final
								
								if(!referenciaOriginal.equals(referenciaFinal)){
									TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
									transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
									transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_COMP_RF_FOND);
									transformacion.setTrar_valor_orig(""+referenciaOriginal);
									transformacion.setTrar_valor_modi(""+referenciaFinal);
									
																									
									listTransformaciones.add(transformacion);
									
									if(!detallesCreados){

										DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
										detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
										
										detallesPorRegistro.add(detalleArchivoSIFI);
										
										detallesCreados=true;
									}
									
									
									
								}
								//Para busquedas se convierte la referencia final como la referencia original 
								referenciaOriginal = referenciaFinal;
								
								
								
							}else if(referenciaOriginal.toString().length()>tamMaximoReferencia){
								
								/**
								 * Transfomaciones asignacion encargo generico
								 * =============================================
								 */
								
								//TODO Crear transformacion y validacion y asignar encargo generico asociado al tipo de archivo
								ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
								validacion.setVlar_aror(detalleArchivo.getDaror_aror());
								validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_TAM_REF_MAYOR);
								validacion.setVlar_valor_descri("REF:"+referenciaOriginal+" Tam:"+referenciaOriginal.toString().length());
								
								listValidaciones.add(validacion);
								
								
								//Se crea transformacion de referencia final
								referenciaFinal = getEncargoGenericoPorTipoArchivo(archivoRecaudoOriginalPorConvertir.getAror_tpar());
								
								TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
								transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
								transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_TAM_REF_MAX);
								transformacion.setTrar_valor_orig(""+referenciaOriginal);
								transformacion.setTrar_valor_modi(""+referenciaFinal);
								
																								
								listTransformaciones.add(transformacion);
								
								if(!detallesCreados){

									DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
									detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
									
									detallesPorRegistro.add(detalleArchivoSIFI);
									
									detallesCreados=true;
								}
								
								
							}
							
							
							
							//Se verifica si es volante de referencia 
							if(referenciaOriginal.toString().startsWith(parametroPrefVolante.getPara_valor())){
								
								esEncargo = false;
								
								//Si la referencia es un volante se debe crear una validacion
								ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
								validacion.setVlar_aror(detalleArchivo.getDaror_aror());
								validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_REF_VOLANTE);
								validacion.setVlar_valor_descri("REF:"+referenciaOriginal+" PrefijoVol:"+parametroPrefVolante.getPara_valor());
								
								listValidaciones.add(validacion);
								
								
							}else{
								esEncargo = true;
							}
							
							
							
							/**
							 * ==========================
							 * 	REFERENCIA ES UN ENCARGO
							 * ==========================
							 * 
							 */
							
							if(esEncargo){
								
								
								//Determinar estado en encargo SIFI
								EncargoFiduciarioSIFI encargoFiduciarioSIFI = new EncargoFiduciarioSIFI();
								encargoFiduciarioSIFI.setPlts_plan(referenciaOriginal);
								
								List<EncargoFiduciarioSIFI> encargosConTitulares = sifiServicio.getEncargoSIFI(encargoFiduciarioSIFI);
								
								
								if(encargosConTitulares!=null && encargosConTitulares.size()>0){
								
									estadoSIFI = encargosConTitulares.get(0).getPlts_esta();
									
									//Crear validacion si el encargo no tiene estado
									if(estadoSIFI==null || estadoSIFI.isEmpty()){
										
										//1. Si el encargo no tiene estado. Crear validacion de que no tiene titular contra quien comparar
										//TODO crear error de que no tiene estado
										
										ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
										errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
										errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
										errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_PLAN_SIFI_SIN_ESTADO);
										errorArchivoRecaudo.setErar_error_descri("REF: "+referenciaOriginal+"; Titular:"+encargosConTitulares.get(0).getPlts_fdei()+"; Estado:"+estadoSIFI);
										
										listErrores.add(errorArchivoRecaudo);
									}else{
										
										
										ValidacionArchivoRecaudo validacionSIFI = new ValidacionArchivoRecaudo();
										validacionSIFI.setVlar_aror(detalleArchivo.getDaror_aror());
										validacionSIFI.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
										validacionSIFI.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_SIFI_EXISTE);
										validacionSIFI.setVlar_valor_descri("REF:"+referenciaOriginal+"; EstadoSIFI:"+estadoSIFI);
										listValidaciones.add(validacionSIFI);
										/**
										 * Transformaciones segun el estado o proyecto
										 * =============================================
										 */	
										
										if(estadoSIFI.equals(EncargoFiduciarioSIFI.ESTADO_CAN)){
											//Se busca el proyecto en los proyectos cancelados solo si esta cancelado. Si lo encuentra coloca el numero de encargo asignado
											//En caso de no encontrarlo lo deja vacio.
											
											//1. Si el estado esta cancelado se debe crear una validacion especifica
											ValidacionArchivoRecaudo validacionCAN = new ValidacionArchivoRecaudo();
											validacionCAN.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionCAN.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionCAN.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_CAN);
											validacionCAN.setVlar_valor_descri("REF:"+referenciaOriginal+"; Estado:"+estadoSIFI);
											
											listValidaciones.add(validacionCAN);
											
											
											ProyectoCancelado proyectoCancelado = proyectoRecaudoServicio.getProyectoCancelado(proyecto);
											
											if(proyectoCancelado!=null && proyectoCancelado.getPrca_proy()!=null){
												
												
												esProyectoCancelado = true;
												
												ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
												validacion.setVlar_aror(detalleArchivo.getDaror_aror());
												validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
												validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PROY_CAN);
												validacion.setVlar_valor_descri("REF:"+referenciaOriginal+"; Proyecto:"+proyecto+"; EncargoSIFI: "+proyectoCancelado.getPrca_plan_sifi());
												listValidaciones.add(validacion);
												
												
												
												if(proyectoCancelado.getPrca_plan_sifi()!=null){
													
													
													referenciaFinal = proyectoCancelado.getPrca_plan_sifi();
													
													TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
													transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
													transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
													transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_PROY_CAN);
													transformacion.setTrar_valor_orig(""+referenciaOriginal);
													transformacion.setTrar_valor_modi(""+referenciaFinal);
													
													listTransformaciones.add(transformacion);
													
													if(!detallesCreados){

														DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
														detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
														
														detallesPorRegistro.add(detalleArchivoSIFI);
														
														detallesCreados=true;
													}
													
													
													
												}else{
													
													ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
													errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
													errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
													errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_PRCA_PLAN_SIFI_NULO);
													errorArchivoRecaudo.setErar_error_descri("REF: "+referenciaOriginal+"; ProyectoCancelado:"+proyecto);
													listErrores.add(errorArchivoRecaudo);
													
												}
												
												
												
												
												
											}
											
											
											
										}
										
										//2. Si el estado esta PCA y el recaudo es RCHE se debe crear validacion especifica
										if(estadoSIFI.equals(EncargoFiduciarioSIFI.ESTADO_PCA) && formaRecaudo.equals("RCHE")){
											ValidacionArchivoRecaudo validacionPCA = new ValidacionArchivoRecaudo();
											validacionPCA.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionPCA.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionPCA.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_PCA);
											validacionPCA.setVlar_valor_descri("REF: "+referenciaOriginal+"; EstadoSIFI:"+estadoSIFI);
											
											listValidaciones.add(validacionPCA);
											
											ValidacionArchivoRecaudo validacionRCHE = new ValidacionArchivoRecaudo();
											validacionRCHE.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionRCHE.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionRCHE.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_TIPO_RECA_RCHE);
											validacionPCA.setVlar_valor_descri("REF: "+referenciaOriginal+"; TipoRecaudo:"+formaRecaudo);
											
											listValidaciones.add(validacionRCHE);
											
										}
										
										
											
										//Validar Identificacion de aportante con los titulares del encargo
										Boolean esTitular = false;
										
										if(aportante!=null){
										
											String titulares = "";
											for (EncargoFiduciarioSIFI encargoConTitular : encargosConTitulares) {
												if(encargoConTitular.getPlts_fdei()!=null){
													
													titulares = titulares+encargoConTitular.getPlts_fdei()+", ";
													if(encargoConTitular.getPlts_fdei().equals(aportante)){
														
														esTitular=true;
													}
													
												}
												
											}
											
											if(!esTitular){
												
												//2. Si tiene estado y no coincide el numero de identificacion del titular se debe crear validacion
												//TODO crear validacion que no es titular
												ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
												validacion.setVlar_aror(detalleArchivo.getDaror_aror());
												validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
												validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_APOR_NO_TITULAR);
												validacion.setVlar_valor_descri("REF: "+referenciaOriginal+"; Aportante:"+aportante+"; Titulares: "+titulares);
												
												listValidaciones.add(validacion);
												
											}
											
										}else{
											
											//TODO crear validacion que aportante es vacio
											ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
											validacion.setVlar_aror(detalleArchivo.getDaror_aror());
											validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_APOR_VACIO);
											validacion.setVlar_valor_descri("REF: "+referenciaOriginal+"; Aportante:"+aportante);
											
											
											listValidaciones.add(validacion);
											
											
										}
										
											
										
									}
								
								}
								
								
								
								//Se deben consular contra los encargos NOSIFI
								EncargoFiduciarioNoSIFI encargoFiduciarioNoSIFI = new EncargoFiduciarioNoSIFI();
								encargoFiduciarioNoSIFI.setPlns_plan(referenciaOriginal);
								encargoFiduciarioNoSIFI = noSIFIServicio.getEncargoNoSIFI(encargoFiduciarioNoSIFI);
								
								
								if(encargoFiduciarioNoSIFI!=null && encargoFiduciarioNoSIFI.getPlns_esta()!=null){
									
									estadoNOSIFI = encargoFiduciarioNoSIFI.getPlns_esta();
									
									

									ValidacionArchivoRecaudo validacionNOSIFI = new ValidacionArchivoRecaudo();
									validacionNOSIFI.setVlar_aror(detalleArchivo.getDaror_aror());
									validacionNOSIFI.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									validacionNOSIFI.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_NOSIFI_EXISTE);
									validacionNOSIFI.setVlar_valor_descri("REF:"+referenciaOriginal+"; EstadoNOSIFI:"+estadoNOSIFI);
									listValidaciones.add(validacionNOSIFI);
									
									
									if(estadoNOSIFI.equals(EncargoFiduciarioNoSIFI.PLAN_NOSIFI_ACT)){
										
										//1. Si el encargo es un NO_SIFI_ACT y pertenece a un proyecto No SIFI Activo se debe colocar el numero de encargo que se especifique en el proyecto. Crear Transformacion
										ProyectoNoSIFIActivo proyectoNoSIFIActivo = proyectoRecaudoServicio.getProyectoNoSIFIActivo(proyecto);
										
										
										
										if(proyectoNoSIFIActivo!=null && proyectoNoSIFIActivo.getPnsa_plan_sifi()!=null){
											
											esProyectoNOSIFI_ACTIVO = true;
											
											ValidacionArchivoRecaudo validacionPlan = new ValidacionArchivoRecaudo();
											validacionPlan.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionPlan.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionPlan.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_NOSIFI_ACT);
											validacionPlan.setVlar_valor_descri("REF: "+referenciaOriginal+"; EstadoNOSIFI:"+estadoNOSIFI);
											
											
											listValidaciones.add(validacionPlan);
											
											
											ValidacionArchivoRecaudo validacionProy = new ValidacionArchivoRecaudo();
											validacionProy.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionProy.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionProy.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PROY_RECA_NOSIFI_ACT);
											validacionProy.setVlar_valor_descri("REF: "+referenciaOriginal+"; ProyectoNOSIFI_ACT"+proyecto);
											
											listValidaciones.add(validacionProy);
											
											
											referenciaFinal = proyectoNoSIFIActivo.getPnsa_plan_sifi();
											
											TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
											transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
											transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_PROY_NOSIFI_ACT);
											transformacion.setTrar_valor_orig(""+referenciaOriginal);
											transformacion.setTrar_valor_modi(""+referenciaFinal);
											
											listTransformaciones.add(transformacion);
											
											if(!detallesCreados){

												DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
												detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
												
												detallesPorRegistro.add(detalleArchivoSIFI);
												
												detallesCreados=true;
											}
											
											
											
											
										}else{
											
											ValidacionArchivoRecaudo validacionProy = new ValidacionArchivoRecaudo();
											validacionProy.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionProy.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionProy.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PROY_RECA_NOSIFI_ACT_SINPLAN);
											validacionProy.setVlar_valor_descri("REF: "+referenciaOriginal+"; Proyecto:"+proyecto+"; EstadoNOSIFI"+estadoNOSIFI);
											
											listValidaciones.add(validacionProy);
										}
										
										
										
									}else{
										//2. Si el encargo es un NO_SIFI_INACT se debe mandar al generico de cada tipo de archivo	
										
										ValidacionArchivoRecaudo validacionPlan = new ValidacionArchivoRecaudo();
										validacionPlan.setVlar_aror(detalleArchivo.getDaror_aror());
										validacionPlan.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
										validacionPlan.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_NOSIFI_INACT);
										validacionPlan.setVlar_valor_descri("REF: "+referenciaOriginal+"; EstadoNOSIFI "+estadoNOSIFI);
										
										
										listValidaciones.add(validacionPlan);
										
										
										referenciaFinal = getEncargoGenericoPorTipoArchivo(archivoRecaudoOriginalPorConvertir.getAror_tpar());
										
										TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
										transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
										transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
										transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_NOSIFI_INACT);
										transformacion.setTrar_valor_orig(""+referenciaOriginal);
										transformacion.setTrar_valor_modi(""+referenciaFinal);
										
										listTransformaciones.add(transformacion);
										
										if(!detallesCreados){

											DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
											detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
											
											detallesPorRegistro.add(detalleArchivoSIFI);
											
											detallesCreados=true;
										}
										
										
									}
									
									
									
								}
								
								
								
								/**
								 * Transfomaciones por formula de distribucion
								 * =============================================
								 */
								
								
								String estadoPlan = null;
								
								if(estadoSIFI!=null && !estadoSIFI.isEmpty()){
									estadoPlan = estadoSIFI;
									
								}else if(estadoNOSIFI !=null && !estadoNOSIFI.isEmpty()){
									estadoPlan = estadoNOSIFI;
									
								}
								
								
								
								if(estadoPlan!=null){
									isEstadoPlanFormulaDistribucion = isEstadoPlanFormulaDistribucion(estadoPlan);
									
									List<ProyectoConFormulaDistribucion> list = proyectoRecaudoServicio.getProyectoConFormulaDistribucion(proyecto);
									
									
									
									
									if( list!=null && list.size()==1){
										
										tieneFormulaDistribucion = true;
										
										//1. Si estadoNOSIFI es NO_SIFI_ACT  o estadoSIFI es CAN y PCA y el proyecto pertenece a formula de distribucion
										if( isEstadoPlanFormulaDistribucion){
										
											ValidacionArchivoRecaudo validacionPlan = new ValidacionArchivoRecaudo();
											validacionPlan.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionPlan.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionPlan.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_FRDP_ACT);
											validacionPlan.setVlar_valor_descri("REF: "+referenciaOriginal+"; Estado:"+estadoNOSIFI+"; Proyecto:"+proyecto+"; Formula:"+list.get(0).getPyfd_frdp_descri());
											
											
											listValidaciones.add(validacionPlan);
											
											//Se debe crear nuevos registros con la distribucion correspondiente y se debe ajustar correctamente el valor. Crear transformacion
											
											List<DistribucionPorFormulaPorcentaje> distribuciones = formulaDistribucionPorcentajeServicio.getDistribucionesPorcentualPorFormula(list.get(0).getPyfd_frdp());
											
											if(distribuciones!=null && distribuciones.size()>0){
													
													int totalDistribuciones = 0;
													double valorTotalDistribuido = 0;
													
												
													for (DistribucionPorFormulaPorcentaje distribucionPorFormulaPorcentaje : distribuciones) {
														
														totalDistribuciones++;
														
														ValidacionArchivoRecaudo validacionDPFD = new ValidacionArchivoRecaudo();
														validacionDPFD.setVlar_aror(detalleArchivo.getDaror_aror());
														validacionDPFD.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
														validacionDPFD.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_DPFD_ACT);
														validacionDPFD.setVlar_valor_descri("REF: "+referenciaOriginal+"; Estado:"+estadoNOSIFI+"; Proyecto:"+proyecto+"; NuevoEncargo:"+distribucionPorFormulaPorcentaje.getDpfd_plan_dest()+" PorcentajeDistr. "+distribucionPorFormulaPorcentaje.getDpfd_porc_reca());
														listValidaciones.add(validacionDPFD);
														
														
														referenciaFinal = distribucionPorFormulaPorcentaje.getDpfd_plan_dest();
														
														TransformacionArchivoRecaudo transformacionPlan = new TransformacionArchivoRecaudo();
														transformacionPlan.setTrar_aror(detalleArchivo.getDaror_aror());
														transformacionPlan.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
														transformacionPlan.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPER_PLAN_DPFD_ACT);
														transformacionPlan.setTrar_valor_orig(""+referenciaOriginal);
														transformacionPlan.setTrar_valor_modi(""+referenciaFinal);
														listTransformaciones.add(transformacionPlan);
														
														
														BigDecimal valorCheque = getValorMoneda(detalleArchivo.getDaror_vche());
														double nuevoValorCheque = getRound2Decimals(valorCheque.multiply(new BigDecimal(distribucionPorFormulaPorcentaje.getDpfd_porc_reca())).doubleValue());
														
														BigDecimal valorEfectivo = getValorMoneda(detalleArchivo.getDaror_vefe());
														double nuevoValorEfectivo = getRound2Decimals(valorEfectivo.multiply(new BigDecimal(distribucionPorFormulaPorcentaje.getDpfd_porc_reca())).doubleValue());
														
														BigDecimal valorTotal = getValorMoneda(detalleArchivo.getDaror_vtot());
														double nuevoValorTotal = getRound2Decimals(valorTotal.multiply(new BigDecimal(distribucionPorFormulaPorcentaje.getDpfd_porc_reca())).doubleValue());
														
														valorTotalDistribuido = valorTotalDistribuido+nuevoValorTotal;
														
														if(totalDistribuciones== distribuciones.size()){
															
															
															
															Double valorDiferencia = valorTotal.doubleValue() - valorTotalDistribuido;
															
															//Ajusta el valor de diferencia
															if(valorDiferencia!=0){
																
																if(nuevoValorEfectivo>0){
																	nuevoValorEfectivo=nuevoValorEfectivo+valorDiferencia;
																	nuevoValorEfectivo = getRound2Decimals(nuevoValorEfectivo);
																	
																}else if(nuevoValorCheque>0){
																	nuevoValorCheque=nuevoValorCheque+valorDiferencia;
																	nuevoValorCheque = getRound2Decimals(nuevoValorCheque);
																}
																
																nuevoValorTotal = nuevoValorTotal+valorDiferencia;	
																nuevoValorTotal = getRound2Decimals(nuevoValorTotal);
															}
															
														}
														
														
														TransformacionArchivoRecaudo transformacionValorCheque = new TransformacionArchivoRecaudo();
														transformacionValorCheque.setTrar_aror(detalleArchivo.getDaror_aror());
														transformacionValorCheque.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
														transformacionValorCheque.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPER_VCHE_DPFD_ACT);
														transformacionValorCheque.setTrar_valor_orig(""+valorCheque.doubleValue());
														transformacionValorCheque.setTrar_valor_modi(""+nuevoValorCheque);
														listTransformaciones.add(transformacionValorCheque);
														
														TransformacionArchivoRecaudo transformacionValorEfectivo = new TransformacionArchivoRecaudo();
														transformacionValorEfectivo.setTrar_aror(detalleArchivo.getDaror_aror());
														transformacionValorEfectivo.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
														transformacionValorEfectivo.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPER_VEFE_DPFD_ACT);
														transformacionValorEfectivo.setTrar_valor_orig(""+valorEfectivo.doubleValue());
														transformacionValorEfectivo.setTrar_valor_modi(""+nuevoValorEfectivo);
														listTransformaciones.add(transformacionValorEfectivo);
														
														TransformacionArchivoRecaudo transformacionValorTotal = new TransformacionArchivoRecaudo();
														transformacionValorTotal.setTrar_aror(detalleArchivo.getDaror_aror());
														transformacionValorTotal.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
														transformacionValorTotal.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPER_VTOT_DPFD_ACT);
														transformacionValorTotal.setTrar_valor_orig(""+valorTotal.doubleValue());
														transformacionValorTotal.setTrar_valor_modi(""+nuevoValorTotal);
														listTransformaciones.add(transformacionValorTotal);
														
														
														if(!detallesCreados){
															DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
															detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
															detalleArchivoSIFI.setDarge_vche(String.format("%016.2f", nuevoValorCheque ).replace(',', '.'));
															detalleArchivoSIFI.setDarge_vefe(String.format("%016.2f", nuevoValorEfectivo ).replace(',', '.'));
															detalleArchivoSIFI.setDarge_vtot(String.format("%016.2f", nuevoValorTotal ).replace(',', '.'));
															
															detallesPorRegistro.add(detalleArchivoSIFI);
															
														}
														
													}
												
													
													if(!detallesCreados){
														detallesCreados=true;
													}
													
												
												
												
											}else{
												ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
												errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
												errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
												errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_PLAN_DPFD_NULO);
												errorArchivoRecaudo.setErar_error_descri("REF: "+referenciaOriginal+"; Estado:"+estadoNOSIFI+"; Proyecto:"+proyecto+";  Formula:"+list.get(0).getPyfd_frdp_descri());
												
												listErrores.add(errorArchivoRecaudo);
												
											}
										
										}
										
									}else if(list.size()>0){
										
										tieneFormulaDistribucion = true;
										//Verificar que no exista en mas de una formula para un mismo proyecto
										for (ProyectoConFormulaDistribucion proyectoConFormulaDistribucion : list) {
											ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
											errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
											errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_PLAN_FRDP_MULTIPLE);
											errorArchivoRecaudo.setErar_error_descri("REF: "+referenciaOriginal+"; Estado:"+estadoNOSIFI+"; Proyecto:"+proyecto+";  Formula:"+proyectoConFormulaDistribucion.getPyfd_frdp_descri());
											
											listErrores.add(errorArchivoRecaudo);
										}
										
										
									}else{
										
										/**
										 * Transfomaciones por tipo de archivo de recaudo
										 * =============================================
										 */
										
										//Si el encargo esta ACT BPC o INA y es de universitas
											//Se debe crear transformacion en la que se completa el numero de encargo con el numero de fondo
										
										//Si es de universitas y no esta en los estados se debe colocar el encargo generico
										
										isEstadoPlanAplicaPlanGenerico = isEstadoPlanAplicaPlanGenerico(estadoPlan);
										
										if(isEstadoPlanAplicaPlanGenerico){
											
											ValidacionArchivoRecaudo validacionDPFD = new ValidacionArchivoRecaudo();
											validacionDPFD.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionDPFD.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionDPFD.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_EPSG_ACT);
											validacionDPFD.setVlar_valor_descri("REF: "+referenciaOriginal+"; Estado:"+estadoPlan);
											listValidaciones.add(validacionDPFD);
											
											referenciaFinal = getEncargoGenericoPorTipoArchivo(archivoRecaudoOriginalPorConvertir.getAror_tpar());
											
											
											TransformacionArchivoRecaudo transformacionPlan = new TransformacionArchivoRecaudo();
											transformacionPlan.setTrar_aror(detalleArchivo.getDaror_aror());
											transformacionPlan.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											transformacionPlan.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_EPSG);
											transformacionPlan.setTrar_valor_orig(""+referenciaOriginal);
											transformacionPlan.setTrar_valor_modi(""+referenciaFinal);
											listTransformaciones.add(transformacionPlan);
											
											if(!detallesCreados){
												DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
												detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
												
												detallesPorRegistro.add(detalleArchivoSIFI);
												
												detallesCreados =true;
											}
											
											
										}else{
											
											
											ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
											validacion.setVlar_aror(detalleArchivo.getDaror_aror());
											validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_EPSG_NOEXISTE);
											validacion.setVlar_valor_descri("REF: "+referenciaOriginal+"; TipoArchivo:"+archivoRecaudoOriginalPorConvertir.getAror_tpar()+" Estado:"+estadoPlan);
											listValidaciones.add(validacion);
											
											referenciaFinal = referenciaOriginal;
											
											if(!detallesCreados){
												DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
												detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
												
												detallesPorRegistro.add(detalleArchivoSIFI);
												
												detallesCreados =true;
											}
										}
										
										
									
									}
									
									
									
								}else{
									
									//TODO crear error porque no tiene ningun estado y es un encargo
									ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
									errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
									errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_PLAN_SIN_ESTADO);
									errorArchivoRecaudo.setErar_error_descri("REF: "+referenciaOriginal+", Estado:"+estadoPlan);
									
									listErrores.add(errorArchivoRecaudo);
								}
							}
							
							
							
							
							
							
							/**
							 * ==========================
							 * 	REFERENCIA ES UN VOLANTE
							 * ==========================
							 * 
							 */
							
							
							//Si la referencia es un volante pero el tipo de archivo dice que lo reemplace por el generico se debe crear transformacion
							if(!esEncargo){
								
								/**
								 * La referencia corresponde a un VOLANTE 	
								 */
								
								Long nuevaReferencia = getReferenciaVolantePorTipoArchivo(referenciaOriginal, archivoRecaudoOriginalPorConvertir.getAror_tpar());
								
								if(!referenciaOriginal.equals(nuevaReferencia)){
									
									ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
									validacion.setVlar_aror(detalleArchivo.getDaror_aror());
									validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_TPAR_MANEJA_VOLANTE_NO);
									validacion.setVlar_valor_descri("REF: "+referenciaOriginal+"; TipoArchivo:"+archivoRecaudoOriginalPorConvertir.getAror_tpar()+" Usa_Volantes_SN: N");
									listValidaciones.add(validacion);
									
									
									referenciaFinal = nuevaReferencia;
									
									TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
									transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
									transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_TPAR_VOL_NO);
									transformacion.setTrar_valor_orig(""+referenciaOriginal);
									transformacion.setTrar_valor_modi(""+referenciaFinal);
									
									listTransformaciones.add(transformacion);
									
									if(!detallesCreados){
										DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
										detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
									
										detallesPorRegistro.add(detalleArchivoSIFI);
										
										detallesCreados =true;
									}
									
									
									
									
								}else{
									
									referenciaFinal = referenciaOriginal;
									
									ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
									validacion.setVlar_aror(detalleArchivo.getDaror_aror());
									validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_TPAR_MANEJA_VOLANTE_SI);
									validacion.setVlar_valor_descri("REF: "+referenciaOriginal+"; TipoArchivo:"+archivoRecaudoOriginalPorConvertir.getAror_tpar()+" Usa_Volantes_SN: S");
									listValidaciones.add(validacion);
									
									if(!detallesCreados){
										DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
										detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
										
										detallesPorRegistro.add(detalleArchivoSIFI);
										
										detallesCreados =true;
									}
									
								}
								
								
							
							}
							
							
							/**
							 * Transfomaciones default encargo generico
							 * =============================================
							 */
							if ((estadoSIFI == null && estadoNOSIFI == null && referenciaFinal==null)  ){
								
								referenciaFinal = getEncargoGenericoPorTipoArchivo(archivoRecaudoOriginalPorConvertir.getAror_tpar());
								
								
								TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
								transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
								transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_REF_NE);
								transformacion.setTrar_valor_orig(""+referenciaOriginal);
								transformacion.setTrar_valor_modi(""+referenciaFinal);
								
								listTransformaciones.add(transformacion);
								
								if(!detallesCreados){
									DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
									detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
									
									detallesPorRegistro.add(detalleArchivoSIFI);
									
									detallesCreados =true;
								}
								
							
							}
							
							
							if((estadoSIFI == EncargoFiduciarioSIFI.ESTADO_CAN && estadoNOSIFI==null && referenciaFinal==null )){
								
								referenciaFinal = getEncargoGenericoPorTipoArchivo(archivoRecaudoOriginalPorConvertir.getAror_tpar());
								
								
								TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
								transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
								transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_PLAN_CA);
								transformacion.setTrar_valor_orig(""+referenciaOriginal);
								transformacion.setTrar_valor_modi(""+referenciaFinal);
								
								listTransformaciones.add(transformacion);
								
								
								if(!detallesCreados){
									DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
									detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
									
									detallesPorRegistro.add(detalleArchivoSIFI);
									
									detallesCreados =true;
								}
								
								
								
							}
							
							
							if((formaRecaudo == "RCHE" && estadoSIFI==EncargoFiduciarioSIFI.ESTADO_PCA) ){
								referenciaFinal = getEncargoGenericoPorTipoArchivo(archivoRecaudoOriginalPorConvertir.getAror_tpar());
								
								
								TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
								transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
								transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_PLAN_PCA_RCHE);
								transformacion.setTrar_valor_orig(""+referenciaOriginal);
								transformacion.setTrar_valor_modi(""+referenciaFinal);
								
								listTransformaciones.add(transformacion);
								
								if(!detallesCreados){
									DetalleArchivoRecaudoGeneradoSIFI detalleArchivoSIFI = new DetalleArchivoRecaudoGeneradoSIFI();
									detalleArchivoSIFI.setDarge_referencia(""+referenciaFinal);
									
									detallesPorRegistro.add(detalleArchivoSIFI);
									
									detallesCreados =true;
								}
								
								
							}
							
							
							//Si al final no tiene encargo asignado se debe generar mensaje de error
							if(referenciaFinal==null){
								
								//TODO Crear error de que no existe referencia
								ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
								errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
								errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_REGLA_NOEXISTE);
								errorArchivoRecaudo.setErar_error_descri("REF: "+referenciaOriginal+"; EstadoSIFI"+estadoSIFI+ "; EstadoNOSIFI:"+"; Proyecto:"+proyecto+
																		"; esProyectoCancelado: "+esProyectoCancelado+"; esProyectoNOSIFI_Activo:"+esProyectoNOSIFI_ACTIVO+
																		"; tieneFormulaDistribucion:"+tieneFormulaDistribucion+"; isEstadoPlanFormulaDistribucion"+isEstadoPlanFormulaDistribucion);
								
								listErrores.add(errorArchivoRecaudo);
							}
							
					
						}else{
							//TODO Crear error de que no existe referencia
							ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
							errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
							errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
							errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_REF_NULO);
							errorArchivoRecaudo.setErar_error_descri("REF: "+referenciaOriginal);
							
							listErrores.add(errorArchivoRecaudo);
							
						}
					
					}else{
						//Crear validacion de que se excluye RNDB
						
						ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
						validacion.setVlar_aror(detalleArchivo.getDaror_aror());
						validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
						validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_RNDB_EXCLUIDO);
						validacion.setVlar_valor_descri("REF: "+referenciaOriginal+"; TipoREcaudo"+formaRecaudo);
						listValidaciones.add(validacion);
						
					}
					
				
					/**
					 * Se complementa los detallesSIFI por registro
					 */
					OficinaRecaudo oficinaRecaudoSIFI = oficinaRecaudoServicio.getOficinaSIFI(oficinaOriginal);
					
					if(oficinaRecaudoSIFI!=null && oficinaRecaudoSIFI.getOfic_sifi()!=null){
						
						oficinaFinal = oficinaRecaudoSIFI.getOfic_sifi();
						TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
						transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
						transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
						transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_OFIC_SIFI_EXISTE);
						transformacion.setTrar_valor_orig(""+oficinaOriginal);
						transformacion.setTrar_valor_modi(""+oficinaFinal);
						
						listTransformaciones.add(transformacion);
						
					}else{
						
						oficinaFinal = oficinaOriginal;
						
						ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
						errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
						errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
						errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_OFIC_SIFI_NOEXISTE);
						errorArchivoRecaudo.setErar_error_descri("REF: "+referenciaOriginal+" Ofic_BSC:"+oficinaOriginal);
						
						listErrores.add(errorArchivoRecaudo);
						
						
					}
					
					//Por cada detalle se debe complear la informacion faltante
					for (DetalleArchivoRecaudoGeneradoSIFI detalleSIFI : detallesPorRegistro) {
						
						
						detalleSIFI.setDarge_arge(archivoRecaudoOriginalPorConvertir.getAror_aror());
						detalleSIFI.setDarge_aportante(detalleArchivo.getDaror_aportante());
						detalleSIFI.setDarge_comp(detalleArchivo.getDaror_comp());
						detalleSIFI.setDarge_cons_bsc_1(detalleArchivo.getDaror_cons_bsc_1());
						detalleSIFI.setDarge_cons_bsc_2(detalleArchivo.getDaror_cons_bsc_2());
						detalleSIFI.setDarge_daror_id_reg(detalleArchivo.getDaror_id_reg());
						detalleSIFI.setDarge_freca(detalleArchivo.getDaror_freca());
						detalleSIFI.setDarge_ofic(oficinaFinal);
						detalleSIFI.setDarge_tipo_reca(detalleArchivo.getDaror_tipo_reca());
						if(detalleSIFI.getDarge_vche()==null){
							
							detalleSIFI.setDarge_vche(detalleArchivo.getDaror_vche());
						}
						
						if(detalleSIFI.getDarge_vefe()==null){
							
							
							
							detalleSIFI.setDarge_vefe(detalleArchivo.getDaror_vefe());
						}
						
						if(detalleSIFI.getDarge_vtot()==null){
							
							
							
							detalleSIFI.setDarge_vtot(detalleArchivo.getDaror_vtot());
						}
						
						listDetallesArchivoGenerado.add(detalleSIFI);
						
					}
					
					
					
				
				} catch (Exception e) {
					e.printStackTrace();
					
					System.out.println("finalizando 3 proceso:"+procesoConversionArchivos.getPrco_prco()+archivoRecaudoOriginalPorConvertir.getAror_nombre());
					return false;
					
				}
				
				
			}
			
			/**
			 * ======================================================
			 * Se registrar erorres, validaciones y transformaciones
			 * Si no hay errores se crea el archivo de recaudo
			 * ======================================================
			 */
			
			SqlSession session = DBManagerFSRecaudos.openSession();
			Boolean sinErrores = true;
			
			if(listErrores.size()==0){
				
				StringBuffer mensajeErrorOut = new StringBuffer();
				try {
					ArchivoRecaudoGeneradoSIFIServicio archivoRecaudoGeneradoSIFIServicio = new ArchivoRecaudoGeneradoSIFIServicio();
					ArchivoRecaudoGeneradoSIFI archivoRecaudoGeneradoSIFI = new ArchivoRecaudoGeneradoSIFI();
					archivoRecaudoGeneradoSIFI.setArge_arge(archivoRecaudoOriginalPorConvertir.getAror_aror());
					archivoRecaudoGeneradoSIFI.setArge_aror(archivoRecaudoOriginalPorConvertir.getAror_aror());
					archivoRecaudoGeneradoSIFI.setArge_bytes(""+listDetallesArchivoGenerado.size());
					archivoRecaudoGeneradoSIFI.setArge_extension(archivoRecaudoOriginalPorConvertir.getAror_extension());
					archivoRecaudoGeneradoSIFI.setArge_nombre(archivoRecaudoOriginalPorConvertir.getAror_nombre());
					archivoRecaudoGeneradoSIFI.setArge_observ("-");
					archivoRecaudoGeneradoSIFI.setArge_prco(procesoConversionArchivos.getPrco_prco());
					archivoRecaudoGeneradoSIFI.setArge_tpar(archivoRecaudoOriginalPorConvertir.getAror_tpar());
					archivoRecaudoGeneradoSIFI.setArge_registros(new Long(listDetallesArchivoGenerado.size()+1));
					archivoRecaudoGeneradoSIFI.setArge_url(rutaArchivosSIFI	+ nombreArchivo);
					archivoRecaudoGeneradoSIFI.setArge_usua(usua_usua);
					archivoRecaudoGeneradoSIFI.setArge_hash(""+listDetallesArchivoGenerado.size());
					archivoRecaudoGeneradoSIFI.setArge_earge(ArchivoRecaudoGeneradoSIFI.GENERADO);
					
					
					
					sinErrores = archivoRecaudoGeneradoSIFIServicio.crearDocumentoTransaccional(session, archivoRecaudoGeneradoSIFI, mensajeErrorOut);
					
					sinErrores = sinErrores&&archivoRecaudoGeneradoSIFIServicio.crearDetallesTransaccional(session, archivoRecaudoGeneradoSIFI, listDetallesArchivoGenerado, mensajeErrorOut);
					
					
					if(sinErrores){
						
						File fileSIFI = generarArchivoSIFI(
								rutaArchivosSIFI,
								procesoConversionArchivos,
								archivoRecaudoOriginalPorConvertir,
								archivoRecaudoGeneradoSIFI,
								listDetallesArchivoGenerado,
								mensajeErrorOut);
						
						
						if(fileSIFI!=null){
							session.commit();
							
						}else{
							session.rollback();
							SimpleLogger.error("Error creando creandoArchivoSIFI. No ha crear registros en base de datos");
							
							
						}	
						
					}else{
						session.rollback();
						SimpleLogger.error("Error creando creandoArchivoSIFI. No ha crear registros en base de datos");
						mensajeErrorOut.append("Error creando creandoArchivoSIFI. No ha crear registros en base de datos");
						
					}
					
				} catch (Exception e) {
					SimpleLogger.error("Error ", e);
					session.rollback();
					mensajeErrorOut.append("Error creandoArchivoSIFI. No ha crear registros en base de datos.");
					
					sinErrores=false;
					
					
				} 	finally {
					session.close();
				}
				
			}
			
			for (ErrorArchivoRecaudo errorArchivoRecaudo : listErrores) {
				System.out.println("Error: "+errorArchivoRecaudo.toString());
			}
			
			for (ValidacionArchivoRecaudo validacion : listValidaciones) {
				System.out.println("Validacion: "+validacion.toString());
			}
			
			for (TransformacionArchivoRecaudo transformacion : listTransformaciones) {
				System.out.println("Transformacion:"+transformacion.toString());
			}
			
			
			
			System.out.println("finalizando 1 proceso:"+procesoConversionArchivos.getPrco_prco()+archivoRecaudoOriginalPorConvertir.getAror_nombre());
			return true;
		
		}else{
			
			System.out.println("finalizando 2 proceso:"+procesoConversionArchivos.getPrco_prco()+archivoRecaudoOriginalPorConvertir.getAror_nombre());
			
			return false;
		}
		
		
		
	}
	

	
	private File generarArchivoSIFI(String rutaArchivosSIFI,
									ProcesoConversionArchivos procesoConversionArchivos,
									ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginal,
									ArchivoRecaudoGeneradoSIFI archivoRecaudoSIFI,
									List<DetalleArchivoRecaudoGeneradoSIFI> detallesSIFI,
									StringBuffer mensajeErrorOut) {

		/***************************************************************************
		 * Informacion del archivo
		 * 
		 * -Primera linea corresponde al encabezado 
		 * -Lineas itermedias corresponden a detalles de recaudo
		 ***************************************************************************/
		File fileSIFI = null;
		
		
		String filename = archivoRecaudoOriginal.getAror_url();
		File fileBSC = new File(filename);
		String[][] registroEncabezado = null;
		
		Boolean hayRegistros = false;
		if (fileBSC != null) {
			Integer[] longitudes = new Integer[] { 81, 13, 18, 5, 8};
			registroEncabezado = LectorArchivoPlanoUtils.leerArchivo(
					fileBSC.getAbsolutePath(), longitudes);
			
			if (registroEncabezado == null || registroEncabezado.length < 1) {
				hayRegistros = false;
			
			}else {
				hayRegistros = true;
			}
			
		}
		
		
		String fechaArchivo;
		String cuentaBancaria;
		String titulo;
		int totalRegistros =0;
		double valorTotal =0;

		String encabezado = null;
		List<String> listRegistros = new ArrayList<String>();
		
				
		if (hayRegistros) {
			
			titulo = registroEncabezado[0][0];
			cuentaBancaria = registroEncabezado[0][1];
			fechaArchivo = registroEncabezado[0][4];
			
			//Con base en los detallesSIFI se calcula el valor total de recaudo y la cantidad
			for (DetalleArchivoRecaudoGeneradoSIFI detalleSIFI : detallesSIFI) {
				
				totalRegistros++;
				
				BigDecimal valor = getValorMoneda(detalleSIFI.getDarge_vtot());
				double valorRecaudo = getRound2Decimals(valor.doubleValue());
				
				valorTotal = valorTotal+valorRecaudo;
				
				String registro = "2"+
								  String.format("%8s",detalleSIFI.getDarge_freca())+
								  String.format("%05d",Integer.parseInt(detalleSIFI.getDarge_ofic()))+
								  String.format("%-24s",detalleSIFI.getDarge_referencia())+
								  String.format("%-30s",detalleSIFI.getDarge_aportante())+
								  String.format("%16s",detalleSIFI.getDarge_vefe())+
								  String.format("%16s",detalleSIFI.getDarge_vche())+
								  String.format("%16s",detalleSIFI.getDarge_vtot())+
								  String.format("%3s",detalleSIFI.getDarge_cons_bsc_1())+
								  String.format("%4s",detalleSIFI.getDarge_tipo_reca())+
								  String.format("%-10s",detalleSIFI.getDarge_comp())+
								  String.format("%9s",detalleSIFI.getDarge_cons_bsc_2());
				listRegistros.add(registro);
				
			}
			
			
			//Se construye el encabezado
			encabezado = 	String.format("%-81s",titulo)+
							String.format("%13s",cuentaBancaria)+
							String.format("%018.2f", valorTotal).replace(',', '.')+
							String.format("%05d",totalRegistros)+
							String.format("%8s",fechaArchivo);
							
			
			//Se crea la carpeta general donde se colocaron los archivo
		    File folder = new File(rutaArchivosSIFI);
			if(!folder.exists()){
		    	folder.mkdirs();
			}
			
			
			FileWriter fichero = null;
	        PrintWriter printerWriter = null;
	        try
	        {
	           fichero = new FileWriter(archivoRecaudoSIFI.getArge_url());
	           printerWriter = new PrintWriter(fichero);
	           
	           printerWriter.println(encabezado);
	           for (String string : listRegistros) {
	        	   printerWriter.println(string);
	           }
	           
	        } catch (Exception e) {

	        	mensajeErrorOut.append("No se puede generar archivo:"+archivoRecaudoSIFI.getArge_nombre()+". "+e.getMessage());
	            e.printStackTrace();
	        } finally {
	           try {
		          
		           if ( fichero != null){
		              fichero.close();
		           }
		           
	           } catch (Exception e2) {
	        	   
	        	   

	        	  mensajeErrorOut.append("No se puede generar archivo:"+archivoRecaudoSIFI.getArge_nombre()+". "+e2.getMessage());
	              e2.printStackTrace();
	           }
	        }
			
	        //Se verifica si el archivo existe
	        File file = new File(archivoRecaudoSIFI.getArge_url());
	        
	        if(file.exists()){
	     	   fileSIFI =  file;
	        }				
							
							
		}	
		
		return fileSIFI;
				
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
	
	private Long getReferenciaVolantePorTipoArchivo(Long referenciaOriginal, String tpar_tpar){
		
		Long nuevaReferencia = null;
		if (tiposArchivoRecaudo != null && tiposArchivoRecaudo.size() > 0) {
			for (TipoArchivoRecaudoConvertidor tipoArchivo : tiposArchivoRecaudo) {

				if (tipoArchivo.getTpar_tpar().equals(tpar_tpar)) {
					if(tipoArchivo.getTpar_usa_vol_sn().equals(TipoArchivoRecaudoConvertidor.VOLANTE_NO)){
						nuevaReferencia= tipoArchivo.getTpar_plan_generico();
						
						
					}else{
						nuevaReferencia = referenciaOriginal;
						
					}	
					break;
				}
			}

		}
		
	
		return nuevaReferencia;
	}
	
	private Long completarReferenciaPorTipoArchivo(Long referenciaOriginal, String tpar_tpar){
		
		Long nuevaReferencia = null;
		
		if(tiposArchivoRecaudo!=null && tiposArchivoRecaudo.size()>0){
			
			for (TipoArchivoRecaudoConvertidor tipoArchivo : tiposArchivoRecaudo) {
				
				if(tipoArchivo.getTpar_tpar().equals(tpar_tpar)){
					if(tipoArchivo.getTpar_comp_rf_sn().equals(TipoArchivoRecaudoConvertidor.COMP_RF_SI)){
						Long  valorFondo= tipoArchivo.getTpar_plan_fondo();
						
						String referenciaCompletada = ""+valorFondo+referenciaOriginal;
						nuevaReferencia = new Long(referenciaCompletada);
						
					}else{
						nuevaReferencia = referenciaOriginal;
						
					}
					
					break;
					
				}
				
			}
			
		}
		
		return nuevaReferencia;
	}
	
	private Long getProyecto(String referencia){
		
		
		if(referencia!=null && !referencia.isEmpty()){
			
			try {
				String proyectoString = referencia.substring(2,6);
				Long proyectoLong = getLong(proyectoString);
				
				return proyectoLong;
			} catch (Exception e) {
				return null;
			}
			
			
		}else{
			return null;
		}
		
	}
	
	private Long getEncargoGenericoPorTipoArchivo(String tpar_tpar){
		
		Long encargoGenerico = null;
		
		if(tiposArchivoRecaudo!=null && tiposArchivoRecaudo.size()>0){
			
			for (TipoArchivoRecaudoConvertidor tipoArchivo : tiposArchivoRecaudo) {
				if(tipoArchivo.getTpar_tpar().equals(tpar_tpar)){
					
					encargoGenerico = tipoArchivo.getTpar_plan_generico();
					break;
					
				}
				
			}
			
		}
		
		
		return encargoGenerico;
		
		
		
	}
	
	private Boolean isEstadoPlanFormulaDistribucion(String estadoPlan){
		
		Boolean isEstadoPlanFormulaDistribucion = false;
		
		if(estadosPlanFormulaDistribucion!=null && estadosPlanFormulaDistribucion.size()>0 ){
			
			for (EstadoPlanFormulaDistribucion estadoPlanFormulaDistribucion : estadosPlanFormulaDistribucion) {
				if(estadoPlanFormulaDistribucion.getEpfd_esta().equals(estadoPlan)){
					isEstadoPlanFormulaDistribucion=true;
					break;
				}
			}
		}
		
		return isEstadoPlanFormulaDistribucion;
		
	}
	
	private Boolean isEstadoPlanAplicaPlanGenerico(String estadoPlan){
		
		Boolean isEstadoPlanAplicaPlanGenerico = false;
		
		if(estadosPlanAplicaPlanGenerico!=null && estadosPlanAplicaPlanGenerico.size()>0 ){
			
			for (EstadoPlanAplicaPlanGenerico estadoPlanAplicaPlanGenerico : estadosPlanAplicaPlanGenerico) {
				if(estadoPlanAplicaPlanGenerico.getEpsg_esta().equals(estadoPlan)){
					isEstadoPlanAplicaPlanGenerico=true;
					break;
				}
			}
		}
		
		return isEstadoPlanAplicaPlanGenerico;
		
	}
	
	public static void main(String[] args) {
		
		String numero = "001245678945612   ";
		System.out.println(new ConvertidorArchivoSIFIPorTipoArchivo().getLong(numero));
		
		
		double valor= 666666.66*0.0650;
		DecimalFormat twoDForm = new DecimalFormat("#.00");
	    double double1 = Double.parseDouble(twoDForm.format(valor).replace(",", "."));
	    
	    System.out.println(double1);
		
	    BigDecimal bigDecimal = new BigDecimal("0000022000000.00");
		
	    System.out.println(String.format("%016.2f", valor ).replace(',', '.'));
	}
	
	
	
	
}
