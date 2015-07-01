package com.developer.logic.modulo.conversion.modelo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.conversion.dto.ArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoGeneradoSIFI;
import com.developer.logic.modulo.conversion.dto.DetalleArchivoRecaudoOriginalPorConvertir;
import com.developer.logic.modulo.conversion.dto.DistribucionPorFormulaPorcentaje;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioNoSIFI;
import com.developer.logic.modulo.conversion.dto.EncargoFiduciarioSIFI;
import com.developer.logic.modulo.conversion.dto.ErrorArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.EstadoPlanAplicaPlanGenerico;
import com.developer.logic.modulo.conversion.dto.EstadoPlanFormulaDistribucion;
import com.developer.logic.modulo.conversion.dto.ParametroGeneralConversion;
import com.developer.logic.modulo.conversion.dto.ProyectoCancelado;
import com.developer.logic.modulo.conversion.dto.ProyectoConFormulaDistribucion;
import com.developer.logic.modulo.conversion.dto.ProyectoNoSIFIActivo;
import com.developer.logic.modulo.conversion.dto.TipoArchivoRecaudoConvertidor;
import com.developer.logic.modulo.conversion.dto.TipoErrorArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.TipoTransformacionArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.TipoValidacionArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.TransformacionArchivoRecaudo;
import com.developer.logic.modulo.conversion.dto.ValidacionArchivoRecaudo;
import com.developer.logic.modulo.unificacion.dto.ProcesoUnificacionArchivos;
import com.developer.logic.modulo.utils.StringOsmoUtils;

public class ConvertidorArchivoSIFIPorTipoArchivo {
	
	
	int tamMaximoReferencia;
	List<EstadoPlanFormulaDistribucion> estadosPlanFormulaDistribucion;
	List<EstadoPlanAplicaPlanGenerico> estadosPlanAplicaPlanGenerico;
	
	
	public Boolean createARGE(	String rutaArchivosSIFI,
								String nombreArchivo,
								ProcesoUnificacionArchivos procesoUnificacionArchivos,
								ArchivoRecaudoOriginalPorConvertir archivoRecaudoOriginalPorConvertir, 
								String usua_usua){
		
		
		
		ArchivoRecaudoOriginalPorConvertirServicio archivoServicio = ArchivoRecaudoOriginalPorConvertirServicio.getInstance();
		List<DetalleArchivoRecaudoOriginalPorConvertir>	listDetallesArchivoOriginal = archivoServicio.getAllDetallesAROR(archivoRecaudoOriginalPorConvertir.getAror_aror());
		List<DetalleArchivoRecaudoGeneradoSIFI> listDetallesArchivoGenerado = new ArrayList<DetalleArchivoRecaudoGeneradoSIFI>();
		
		
		Double totalCheque = new Double(0);
		Double totalEfectivo = new Double(0);
		Double totalArchivo = new Double(0);
		
		ParametroGeneralConversion parametroTamMax =  ParametroGeneralConversionServicio.getInstance().getParametroGeneral(ParametroGeneralConversionServicio.TAMANHO_MAX_REFERENCIA);
		ParametroGeneralConversion parametroPrefVolante =  ParametroGeneralConversionServicio.getInstance().getParametroGeneral(ParametroGeneralConversionServicio.PREFIJO_VOLANTE);
		
		
		SIFIServicio sifiServicio = SIFIServicio.getInstance();
		EncargoFiduciarioNoSIFIServicio noSIFIServicio = EncargoFiduciarioNoSIFIServicio.getInstance();
		TipoArchivoRecaudoConvertidorServicio tipoArchivoServicio = TipoArchivoRecaudoConvertidorServicio.getInstance();
		ProyectoRecaudoServicio proyectoRecaudoServicio = ProyectoRecaudoServicio.getInstance();
		FormulaDistribucionPorcentajeServicio formulaDistribucionPorcentajeServicio = FormulaDistribucionPorcentajeServicio.getInstance();
		
		estadosPlanFormulaDistribucion = formulaDistribucionPorcentajeServicio.getAllEstadosAplicaFormula();
		estadosPlanAplicaPlanGenerico = tipoArchivoServicio.getEstadosAplicaPlanGenericoPorTPAR(archivoRecaudoOriginalPorConvertir.getAror_tpar());
		
		if(parametroTamMax!=null && parametroTamMax.getPara_valor()!=null && parametroPrefVolante!=null && parametroPrefVolante.getPara_valor()!=null){
			
			List<TransformacionArchivoRecaudo> 	listTransformaciones = new ArrayList<TransformacionArchivoRecaudo>();
			List<ValidacionArchivoRecaudo> 		listValidaciones 	 = new ArrayList<ValidacionArchivoRecaudo>();
			List<ErrorArchivoRecaudo> 			listErrores 		 = new ArrayList<ErrorArchivoRecaudo>();
			
			tamMaximoReferencia = Integer.parseInt(parametroTamMax.getPara_valor());
			
			
			for (DetalleArchivoRecaudoOriginalPorConvertir detalleArchivo : listDetallesArchivoOriginal) {
				
				
				
				String estadoSIFI = null;
				String estadoNOSIFI = null;
				
				Long   referenciaOriginal = null;
				Long   referenciaFinal = null;;
				
				Boolean esEncargo = true;
				String formaRecaudo = null;
				Long aportante = null;
				
				Long proyecto = null;
				
				
				/**
				 * Validaciones generales
				 * =============================================
				 */
				try{
					
					formaRecaudo = detalleArchivo.getDaror_tipo_reca();
					aportante = getLong(detalleArchivo.getDaror_aportante());
					proyecto = getProyecto(""+referenciaOriginal);
					
					//Tipo de recaudo -Excluir por forma de recaudo. Crear validacion
					if(!formaRecaudo.equals("RNDB")){
						
						
						
						//Validar el tamaño del numero de referencia. Crear validacion
						if(detalleArchivo.getDaror_referencia()!=null){
							
							//Se eliminan los espacios y ceros a la izquierda
							referenciaOriginal = getLong(detalleArchivo.getDaror_referencia());
							
							
							//Completar el tamaño con numero de fondo por tipo de archivo. Crear transformacion y validacion
							if(referenciaOriginal.toString().length()<tamMaximoReferencia){
								
								referenciaFinal = completarReferenciaPorTipoArchivo(referenciaOriginal, archivoRecaudoOriginalPorConvertir.getAror_tpar());
								
								
								//TODO Crear validacion
								ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
								validacion.setVlar_aror(detalleArchivo.getDaror_aror());
								validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_TAM_REF_MENOR);
								
								listValidaciones.add(validacion);
								
								//Se crea transformacion de referencia final
								
								
								TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
								transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
								transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_COMP_RF_FOND);
								transformacion.setTrar_valor_orig(""+referenciaOriginal);
								transformacion.setTrar_valor_modi(""+referenciaFinal);
								
																								
								listTransformaciones.add(transformacion);
								
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
							}
							
							
							
							//Se verifica si es volante de referencia 
							if(referenciaOriginal.toString().startsWith(parametroPrefVolante.getPara_valor())){
								
								esEncargo = false;
								referenciaFinal = referenciaOriginal;
								
								//Si la referencia es un volante se debe crear una validacion
								ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
								validacion.setVlar_aror(detalleArchivo.getDaror_aror());
								validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
								validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_REF_VOLANTE);
								
								listValidaciones.add(validacion);
								
								
							}else{
								esEncargo = true;
							}
							
							
							//Determinar estado en encargo SIFI
							if(esEncargo){
								
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
										
										listErrores.add(errorArchivoRecaudo);
									}else{
										
										//1. Si el estado esta cancelado se debe crear una validacion especifica
										if(estadoSIFI.equals(EncargoFiduciarioSIFI.ESTADO_CAN)){
											ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
											validacion.setVlar_aror(detalleArchivo.getDaror_aror());
											validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_CAN);
											
											listValidaciones.add(validacion);
											
											
										}
										
										//2. Si el estado esta PCA y el recaudo es RCHE se debe crear validacion especifica
										if(estadoSIFI.equals(EncargoFiduciarioSIFI.ESTADO_PCA) && formaRecaudo.equals("RCHE")){
											ValidacionArchivoRecaudo validacionPCA = new ValidacionArchivoRecaudo();
											validacionPCA.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionPCA.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionPCA.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_PCA);
											
											listValidaciones.add(validacionPCA);
											
											ValidacionArchivoRecaudo validacionRCHE = new ValidacionArchivoRecaudo();
											validacionRCHE.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionRCHE.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionRCHE.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_TIPO_RECA_RCHE);
											
											listValidaciones.add(validacionRCHE);
											
										}
											
										//Validar Identificacion de aportante con los titulares del encargo
										Boolean esTitular = false;
										
										if(aportante!=null){
										
											for (EncargoFiduciarioSIFI encargoConTitular : encargosConTitulares) {
												if(encargoConTitular.getPlts_fdei()!=null){
													
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
												
												listValidaciones.add(validacion);
												
											}
											
										}else{
											
											//TODO crear validacion que aportante es vacio
											ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
											validacion.setVlar_aror(detalleArchivo.getDaror_aror());
											validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_APOR_VACIO);
											
											listValidaciones.add(validacion);
											
											
										}
										
											
										
									}
								
								}else{
									//1. Si el encargo no tiene titulares. Crear validacion de que no tiene titular contra quien comparar
									
									//TODO crear error que el encargo no tiene estado SIFI
									ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
									errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
									errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_PLAN_SIFI_NULO);
									
									listErrores.add(errorArchivoRecaudo);
								}
								
								
								
							
							
							}
							
							
							
							
							
							/**
							 * Transformaciones segun el estado o proyecto
							 * =============================================
							 */	
							
							if(estadoSIFI.equals(EncargoFiduciarioSIFI.ESTADO_CAN)){
								//Se busca el proyecto en los proyectos cancelados solo si esta cancelado. Si lo encuentra coloca el numero de encargo asignado
								//En caso de no encontrarlo lo deja vacio.
								
								ProyectoCancelado proyectoCancelado = proyectoRecaudoServicio.getProyectoCancelado(proyecto);
								
								if(proyectoCancelado!=null && proyectoCancelado.getPrca_proy()!=null){
									
									
									ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
									validacion.setVlar_aror(detalleArchivo.getDaror_aror());
									validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PROY_CAN);
									
									listValidaciones.add(validacion);
									
									
									
									if(proyectoCancelado.getPrca_plan_sifi()!=null){
										
										
										referenciaFinal = proyectoCancelado.getPrca_plan_sifi();
										
										TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
										transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
										transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
										transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_TAM_REF_MAX);
										transformacion.setTrar_valor_orig(""+referenciaOriginal);
										transformacion.setTrar_valor_modi(""+referenciaFinal);
										
										listTransformaciones.add(transformacion);
										
										
									}else{
										
										ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
										errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
										errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
										errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_PRCA_PLAN_SIFI_NULO);
										
										listErrores.add(errorArchivoRecaudo);
										
									}
									
									
									
									
									
								}
								
								
								
							}
							
							
							
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
									
									listValidaciones.add(validacion);
									
									
									referenciaFinal = nuevaReferencia;
									
									TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
									transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
									transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_TPAR_VOL_NO);
									transformacion.setTrar_valor_orig(""+referenciaOriginal);
									transformacion.setTrar_valor_modi(""+referenciaFinal);
									
									listTransformaciones.add(transformacion);
									
									
									
									
									
								}else{
									
									ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
									validacion.setVlar_aror(detalleArchivo.getDaror_aror());
									validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_TPAR_MANEJA_VOLANTE_SI);
									
									listValidaciones.add(validacion);
								}
								
								
							
							}else{
								/**
								 * La referencia corresponde a un ENCARGO 	
								 */
								
							
								//Se deben consular contra los encargos no sifi
								EncargoFiduciarioNoSIFI encargoFiduciarioNoSIFI = new EncargoFiduciarioNoSIFI();
								encargoFiduciarioNoSIFI.setPlns_plan(referenciaOriginal);
								encargoFiduciarioNoSIFI = noSIFIServicio.getEncargoNoSIFI(encargoFiduciarioNoSIFI);
								
								
								if(encargoFiduciarioNoSIFI!=null && encargoFiduciarioNoSIFI.getPlns_esta()!=null){
									
									estadoNOSIFI = encargoFiduciarioNoSIFI.getPlns_esta();
									
									
									if(estadoNOSIFI.equals(EncargoFiduciarioNoSIFI.PLAN_NOSIFI_ACT)){
										
										//1. Si el encargo es un NO_SIFI_ACT y pertenece a un proyecto No SIFI Activo se debe colocar el numero de encargo que se especifique en el proyecto. Crear Transformacion
										ProyectoNoSIFIActivo proyectoNoSIFIActivo = proyectoRecaudoServicio.getProyectoNoSIFIActivo(proyecto);
										
										if(proyectoNoSIFIActivo!=null && proyectoNoSIFIActivo.getPnsa_plan_sifi()!=null){
											
	
											ValidacionArchivoRecaudo validacionPlan = new ValidacionArchivoRecaudo();
											validacionPlan.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionPlan.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionPlan.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_NOSIFI_ACT);
											validacionPlan.setVlar_valor_descri(referenciaOriginal+" - "+estadoNOSIFI);
											
											
											listValidaciones.add(validacionPlan);
											
											
											ValidacionArchivoRecaudo validacionProy = new ValidacionArchivoRecaudo();
											validacionProy.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionProy.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionProy.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PROY_RECA_NOSIFI_ACT);
											validacionProy.setVlar_valor_descri(""+proyecto);
											
											listValidaciones.add(validacionProy);
											
											
											referenciaFinal = proyectoNoSIFIActivo.getPnsa_plan_sifi();
											
											TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
											transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
											transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_PROY_NOSIFI_ACT);
											transformacion.setTrar_valor_orig(""+referenciaOriginal);
											transformacion.setTrar_valor_modi(""+referenciaFinal);
											
											listTransformaciones.add(transformacion);
											
											
											
											
											
										}else{
											
											ValidacionArchivoRecaudo validacionProy = new ValidacionArchivoRecaudo();
											validacionProy.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionProy.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionProy.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PROY_RECA_NOSIFI_ACT);
											validacionProy.setVlar_valor_descri(""+proyecto);
											
											listValidaciones.add(validacionProy);
											
											
											
											
										}
										
										
										
									}else{
										//2. Si el encargo es un NO_SIFI_INACT se debe mandar al generico de cada tipo de archivo	
										
										ValidacionArchivoRecaudo validacionPlan = new ValidacionArchivoRecaudo();
										validacionPlan.setVlar_aror(detalleArchivo.getDaror_aror());
										validacionPlan.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
										validacionPlan.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_PLAN_NOSIFI_INACT);
										validacionPlan.setVlar_valor_descri(referenciaOriginal+" - "+estadoNOSIFI);
										
										
										listValidaciones.add(validacionPlan);
										
										
										referenciaFinal = getEncargoGenericoPorTipoArchivo(archivoRecaudoOriginalPorConvertir.getAror_tpar());
										
										TransformacionArchivoRecaudo transformacion = new TransformacionArchivoRecaudo();
										transformacion.setTrar_aror(detalleArchivo.getDaror_aror());
										transformacion.setTrar_daror_id_reg(detalleArchivo.getDaror_id_reg());
										transformacion.setTrar_tptr(TipoTransformacionArchivoRecaudo.TPTR_PLAN_GENERICO_NOSIFI_INACT);
										transformacion.setTrar_valor_orig(""+referenciaOriginal);
										transformacion.setTrar_valor_modi(""+referenciaFinal);
										
										listTransformaciones.add(transformacion);
										
										
										
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
									Boolean isEstadoPlanFormulaDistribucion = isEstadoPlanFormulaDistribucion(estadoPlan);
									
									List<ProyectoConFormulaDistribucion> list = proyectoRecaudoServicio.getProyectoConFormulaDistribucion(proyecto);
									
									
									
									
									if( list!=null && list.size()==1){
										
										//1. Si estadoNOSIFI es NO_SIFI_ACT  o estadoSIFI es CAN y PCA y el proyecto pertenece a formula de distribucion
										if( isEstadoPlanFormulaDistribucion){
										
											ValidacionArchivoRecaudo validacionPlan = new ValidacionArchivoRecaudo();
											validacionPlan.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionPlan.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionPlan.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPER_PLAN_FRDP_ACT);
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
														validacionDPFD.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPER_PLAN_DPFD_ACT);
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
														
														
														BigDecimal valorCheque = getMoneda2Decimals(detalleArchivo.getDaror_vche());
														double nuevoValorCheque = getRound2Decimals(valorCheque.multiply(new BigDecimal(distribucionPorFormulaPorcentaje.getDpfd_porc_reca())).doubleValue());
														
														BigDecimal valorEfectivo = getMoneda2Decimals(detalleArchivo.getDaror_vefe());
														double nuevoValorEfectivo = getRound2Decimals(valorEfectivo.multiply(new BigDecimal(distribucionPorFormulaPorcentaje.getDpfd_porc_reca())).doubleValue());
														
														BigDecimal valorTotal = getMoneda2Decimals(detalleArchivo.getDaror_vefe());
														double nuevoValorTotal = getRound2Decimals(valorTotal.multiply(new BigDecimal(distribucionPorFormulaPorcentaje.getDpfd_porc_reca())).doubleValue());
														
														valorTotalDistribuido = valorTotalDistribuido+nuevoValorTotal;
														
														if(totalDistribuciones== distribuciones.size()){
															
															
															
															Double valorDiferencia = valorTotal.doubleValue() - valorTotalDistribuido;
															
															//Ajusta el valor de diferencia
															if(valorDiferencia!=0){
																
																if(nuevoValorEfectivo>0){
																	nuevoValorEfectivo=nuevoValorEfectivo+valorDiferencia;
																	
																}else if(nuevoValorCheque>0){
																	nuevoValorCheque=nuevoValorCheque+valorDiferencia;
																	
																}
																
																nuevoValorTotal = nuevoValorTotal+valorDiferencia;	
																	
																
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
										
										
										
										if(isEstadoPlanAplicaPlanGenerico(estadoPlan)){
											
											ValidacionArchivoRecaudo validacionDPFD = new ValidacionArchivoRecaudo();
											validacionDPFD.setVlar_aror(detalleArchivo.getDaror_aror());
											validacionDPFD.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
											validacionDPFD.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPER_EPSG_ACT);
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
											
											
										}else{
											
											
											referenciaFinal = referenciaOriginal;
										}
										
										
									
									}
									
									
									
								}else{
									
									//TODO crear error porque no tiene ningun estado y es un encargo
									ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
									errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
									errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
									errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_PLAN_SIN_ESTADO);
									
									listErrores.add(errorArchivoRecaudo);
								}
								
								
								
								/**
								 * Transfomaciones default encargo generico
								 * =============================================
								 */
								if ((estadoSIFI == null && estadoNOSIFI == null && referenciaFinal==null) || (estadoSIFI == EncargoFiduciarioSIFI.ESTADO_CAN && estadoNOSIFI==null && referenciaFinal==null ) || (formaRecaudo == "RCHE" && estadoSIFI==EncargoFiduciarioSIFI.ESTADO_PCA) ){
									//1.if Estado_SIFI = "___" And Estado_NO_SIFI = "" And Range("G" & Registro).Value = "") Then 'Nov 5 2014: FAB Se incluye esta línea para que si no encuentra estado no cambie el número del encargo al genérico en Rentafácil
										//Crear validacion de que es volante
									
									//2.else En otro caso se valida que no por cuenta que no es universitas y coloca el generico especifico
								
								
									//3 if si el tipo de archivo es de universitas asignar el encargo generico 
								
								}	
								
								
								
							}		
							
							
							
							//Si al final no tiene encargo asignado se debe generar mensaje de error
							if(referenciaFinal==null){
								
								referenciaFinal = referenciaOriginal;
							}
							
					
						}else{
							//TODO Crear error de que no existe referencia
							ErrorArchivoRecaudo errorArchivoRecaudo = new ErrorArchivoRecaudo();
							errorArchivoRecaudo.setErar_aror(detalleArchivo.getDaror_aror());
							errorArchivoRecaudo.setErar_daror_id_reg(detalleArchivo.getDaror_id_reg());
							errorArchivoRecaudo.setErar_tper(TipoErrorArchivoRecaudo.TPER_REF_NULO);
							
							listErrores.add(errorArchivoRecaudo);
							
						}
					
					}else{
						//Crear validacion de que se excluye RNDB
						
						ValidacionArchivoRecaudo validacion = new ValidacionArchivoRecaudo();
						validacion.setVlar_aror(detalleArchivo.getDaror_aror());
						validacion.setVlar_daror_id_reg(detalleArchivo.getDaror_id_reg());
						validacion.setVlar_tpvl(TipoValidacionArchivoRecaudo.TPVL_RNDB_EXCLUIDO);
						
						listValidaciones.add(validacion);
						
					}
					
				
				} catch (Exception e) {
					return false;
					
				}
				
				
			}
			
			
			
			
			for (ErrorArchivoRecaudo errorArchivoRecaudo : listErrores) {
				System.out.println(errorArchivoRecaudo.toString());
			}
			
			for (ValidacionArchivoRecaudo validacion : listValidaciones) {
				System.out.println(validacion.toString());
			}
			
			for (TransformacionArchivoRecaudo transformacion : listTransformaciones) {
				System.out.println(transformacion.toString());
			}
			
			
			
			return true;
		
		}else{
			
			return false;
		}
		
		
		
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
	
	
	private double getRound2Decimals(double valor) {

		DecimalFormat twoDForm = new DecimalFormat("#.##");
	    return Double.valueOf(twoDForm.format(valor));

		

	}
	
	private Long getReferenciaVolantePorTipoArchivo(Long referenciaOriginal, String tpar_tpar){
	
		TipoArchivoRecaudoConvertidorServicio tipoArchivoServicio = TipoArchivoRecaudoConvertidorServicio.getInstance();
		TipoArchivoRecaudoConvertidor tipoArchivoRecaudoConvertidor = tipoArchivoServicio.getTipoArchivo(tpar_tpar);
		Long nuevaReferencia = null;
		
		if(tipoArchivoRecaudoConvertidor.getTpar_usa_vol_sn().equals(TipoArchivoRecaudoConvertidor.VOLANTE_NO)){
			nuevaReferencia= tipoArchivoRecaudoConvertidor.getTpar_plan_generico();
			
			
		}else{
			nuevaReferencia = referenciaOriginal;
			
		}
		return nuevaReferencia;
	}
	
	
	private Long completarReferenciaPorTipoArchivo(Long referenciaOriginal, String tpar_tpar){
		
		TipoArchivoRecaudoConvertidorServicio tipoArchivoServicio = TipoArchivoRecaudoConvertidorServicio.getInstance();
		TipoArchivoRecaudoConvertidor tipoArchivoRecaudoConvertidor = tipoArchivoServicio.getTipoArchivo(tpar_tpar);
		Long nuevaReferencia = null;
		
		if(tipoArchivoRecaudoConvertidor.getTpar_comp_rf_sn().equals(TipoArchivoRecaudoConvertidor.COMP_RF_SI)){
			Long  valorFondo= tipoArchivoRecaudoConvertidor.getTpar_plan_fondo();
			
			String referenciaCompletada = ""+valorFondo+referenciaOriginal;
			nuevaReferencia = new Long(referenciaCompletada);
			
		}else{
			nuevaReferencia = referenciaOriginal;
			
		}
		return nuevaReferencia;
	}
	
	
	private Long getEncargoGenericoPorTipoArchivo(String tpar_tpar){
		
		TipoArchivoRecaudoConvertidorServicio tipoArchivoServicio = TipoArchivoRecaudoConvertidorServicio.getInstance();
		TipoArchivoRecaudoConvertidor tipoArchivoRecaudoConvertidor = tipoArchivoServicio.getTipoArchivo(tpar_tpar);
		Long encargoGenerico = tipoArchivoRecaudoConvertidor.getTpar_plan_generico();
		
		return encargoGenerico;
		
		
		
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
	
	private Boolean isEstadoPlanFormulaDistribucion(String estadoPlan){
		
		Boolean isEstadoPlanFormulaDistribucion = false;
		
		if(estadosPlanFormulaDistribucion!=null && estadosPlanFormulaDistribucion.size()>0 ){
			
			for (EstadoPlanFormulaDistribucion estadoPlanFormulaDistribucion : estadosPlanFormulaDistribucion) {
				if(estadoPlanFormulaDistribucion.equals(estadoPlan)){
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
				if(estadoPlanAplicaPlanGenerico.equals(estadoPlan)){
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
		
	}
	
	
	
	
}
