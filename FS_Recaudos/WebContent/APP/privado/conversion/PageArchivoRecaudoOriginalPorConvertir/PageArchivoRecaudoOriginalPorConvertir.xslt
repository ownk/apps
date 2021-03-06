<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />


	<xsl:include href="../../../general/stylesheets/nav_bar.xsl" />
	<xsl:include href="../../../general/stylesheets/footer.xsl" />
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<xsl:template match="/">

		<PAGE>

			<xsl:apply-templates select="//XMLPAGE/*" />

			<!-- ================================== -->
			<!-- RECUERSOS ESPECIFICOS============= -->
			<!-- ================================== -->

			<add type='script'
				src="privado/conversion/PageArchivoRecaudoOriginalPorConvertir/PageArchivoRecaudoOriginalPorConvertir.js"></add>

			<!-- ================================== -->
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->

			<body class="top-navigation">
				<div id="wrapper">
					<div id="page-wrapper" class="gray-bg">

						<!-- MENU -->
						<xsl:call-template name="nav_bar-top-light" />


						<!-- CONTENIDO -->
						<div class="wrapper wrapper-content animated fadeInUp">

							<!-- TITULO -->
							<div class="row ">
								<div class="col-lg-12 ">
									<div class="ibox float-e-margins ">
										<div class="ibox-content">
											<div class="row">
												<div class="col-sm-12">
													<div class="pull-left m-r-md">
														<i class="fa fa-file-text  mid-icon ownk-color-naranja"></i>
													</div>
													<div class="p-xs">

														<h2>
															Consulta de Archivo BSC
															<xsl:value-of select="//aror_aror" />
														</h2>
														<ol class="breadcrumb">
															<li>
																<a>Consultas</a>
															</li>
															<li class="active">
																<strong>Consultar Archivos BSC Unificado </strong>
															</li>
														</ol>
														<br />

														<span>
															En esta sección podrás consultar la
															<strong>información general
																del archivo</strong>
															: validaciones, transformaciones, errores
															y archivo SIFI
															generado.
														</span>


													</div>





												</div>
											</div>
										</div>
									</div>
								</div>
							</div>


							<!-- Detallle -->

							<div class="row">
								<div class="col-xs-12">



									<div class="row">

										<!-- INFORMACION GENERAL -->
										<div class="col-xs-6">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>Información general Archivo BSC  </h5>

												</div>

												<div class="ibox-content">
													<div class="row">

														<div class="col-xs-12">
															Informacion básica del archivo
														</div>

													</div>

													<div class="row">
														<div class="ownk_separador_h" />

													</div>

													<div class="row">
														<div class="col-md-12">



															<div class="col-md-12">

																<div class="row">
																	<div>
																		<dl class="dl-horizontal">
																			<dt>Tipo de archivo</dt>
																			<dd>
																				<span class="label label-default">
																					<xsl:value-of select="//aror_tpar" />
																				</span>
																			</dd>
																		</dl>
																	</div>
																</div>

																<div class="row">
																	<div>
																		<dl class="dl-horizontal">



																			<dt>Proceso Conversión</dt>
																			<dd>
																				
																				<form id="form_prun_{//aror_prco}"
																						action="{//contextPath}/unificacion/PageProcesoUnificacionArchivos.do"
																						method="post">
																						<input type="hidden"
																							name="ProcesoUnificacionArchivos.prun_prun"
																							value="{//aror_prco}" />
																						<input type="hidden"
																							name="tabActive"
																							value="5" />	

																				</form>
																				
																				
																				<a class="ownk_link"
																					onclick="osm_enviarFormulario('form_prun_{//aror_prco}');">
																					No. <xsl:value-of select="//aror_prco" />
																				</a>
																				
																				
																			
																				
																			</dd>


																			<dt>Nombre archivo BSC</dt>
																			<dd>

																				<xsl:value-of select="//aror_nombre" />

																			</dd>

																			<dt>Creado por</dt>
																			<dd>
																				<xsl:value-of select="//aror_usua" />
																			</dd>



																			<dt>Fecha de creación</dt>
																			<dd>
																				<xsl:value-of select="//aror_fcrea" />
																			</dd>




																		</dl>
																	</div>
																</div>

																<div class="row">

																	<dl class="dl-horizontal">
																		<dt>Estado archivo:</dt>
																		<dd>

																			<div class="">
																				<button type="button"
																					class="btn btn-sm btn-primary ownk_btn_shadow" id="btn_popup_historico"
																					data-toggle="modal" data-target="#myModalHistoricoAror">
																					<xsl:value-of select="//aror_earor" />
																				</button>

																				<xsl:call-template name="popup_historico_aror" />
																			</div>
																		</dd>



																	</dl>

																</div>

															</div>



														</div>
													</div>


												</div>


											</div>
										</div>


										<!-- INDICADORES -->

										<div class="col-xs-6">
											<div class="row">
												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-info pull-right">ERAR</span>
															<h5>
																<small>Errores</small>
															</h5>
														</div>
														<div class="ibox-content">

															<h1 class="no-margins">
																<i class="fa fa-frown-o modal-icon">
																</i>
																<xsl:value-of
																	select="concat('  ', count(//ErrorArchivoRecaudo))" />
															</h1>

															<div class="stat-percent font-bold text-warning">
																100%
																<i class="fa fa-bolt"></i>
															</div>
															<small>Total </small>
															<br />
														</div>
													</div>
												</div>

												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-info pull-right">VLAR</span>
															<h5>
																<small>Valida...</small>
															</h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">

																<i class="fa fa-check-square-o modal-icon">
																</i>
																<xsl:value-of
																	select="concat('  ',count(//ValidacionArchivoRecaudo ))" />
															</h1>
															<div class="stat-percent font-bold text-warning">
																100%
																<i class="fa fa-bolt"></i>
															</div>
															<small>Total</small>
															<br />
														</div>
													</div>
												</div>
											</div>


											<div class="row">
												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-info pull-right">TRAR</span>
															<h5>
																<small>Transfo...</small>
															</h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">
																<i class="fa fa-exchange modal-icon">
																</i>

																<xsl:value-of
																	select="concat('  ',count(//TransformacionArchivoRecaudo))" />
															</h1>
															<div class="stat-percent font-bold text-warning">
																100%
																<i class="fa fa-bolt"></i>
															</div>
															<small>Total</small>
															<br />
														</div>
													</div>
												</div>
												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-info pull-right">ARGE</span>
															<h5>
																<small>SIFI</small>
															</h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">
																<i class="fa fa-list-ol modal-icon">
																</i>
																<xsl:value-of
																	select="concat('  ',//ArchivoRecaudoGeneradoSIFI/arge_registros)" />
															</h1>
															<div class="stat-percent font-bold text-warning">

																<xsl:choose>
																	<xsl:when
																		test="count(//DetalleArchivoRecaudoGeneradoSIFI)>0">
																		100%
																	</xsl:when>
																	<xsl:otherwise>
																		0%
																	</xsl:otherwise>


																</xsl:choose>


																<i class="fa fa-bolt"></i>
															</div>
															<small>Registros</small>
															<br />
														</div>
													</div>
												</div>
											</div>

										</div>
									</div>

									<!-- ARCHIVOS -->
									<div class="row">
										<div class="col-xs-12">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>Validaciones y Transformaciones  </h5>
													
													
													<div class="ibox-tools">
														
													
														<form id="form_prun_{//aror_prco}"
																action="{//contextPath}/unificacion/PageProcesoUnificacionArchivos.do"
																method="post">
																<input type="hidden"
																	name="ProcesoUnificacionArchivos.prun_prun"
																	value="{//aror_prco}" />
																<input type="hidden"
																	name="tabActive"
																	value="5" />	

														</form>
														
														
														<a class="ownk_link"
															onclick="osm_enviarFormulario('form_prun_{//aror_prco}');">
															Regresar a consulta de proceso
														</a>
	
													</div>


												</div>
												<div class="ibox-content">
													<div class="row m-t-sm">
														<div class="col-xs-12">
															<div class="panel blank-panel">
																<div class="panel-heading">
																	<div class="panel-options">
																		<ul class="nav nav-tabs">
																			<li class="">
																				<a href="#tab-1" data-toggle="tab">
																					<b>Detalle Archivo BSC</b>
																				</a>
																			</li>
																			<li class="">
																				<a href="#tab-2" data-toggle="tab">
																					<b>
																						Errores (
																						<xsl:value-of select="count(//ErrorArchivoRecaudo)" />
																						)
																					</b>
																				</a>
																			</li>


																			<li class="active">
																				<a href="#tab-3" data-toggle="tab">
																					<b>Detalle Archivo SIFI</b>
																				</a>
																			</li>

																		</ul>
																	</div>
																</div>

																<div class="panel-body">

																	<div class="tab-content">
																		<div class="tab-pane " id="tab-1">
																			<xsl:call-template name="detalleArchivoOriginal" />

																		</div>


																		<div class="tab-pane" id="tab-2">
																			<xsl:call-template name="detalleErrores" />



																		</div>



																		<div class="tab-pane active" id="tab-3">
																			<xsl:call-template name="detalleArchivoSIFI" />
																			<xsl:call-template name="detalleResumenConversionSIFI" />

																		</div>
																	</div>

																</div>

															</div>
														</div>
													</div>
												</div>
											</div>

										</div>
									</div>

								</div>
							</div>

						</div>



					</div>
				</div>



			</body>

		</PAGE>

	</xsl:template>

	<xsl:template name="detalleArchivoOriginal">
		<xsl:choose>

			<xsl:when
				test="count(//ArchivoRecaudoOriginalPorConvertir/detalles/DetalleArchivoRecaudoOriginalPorConvertir)>0 and 1=1">

				<div class="row">
					<div class="col-sm-12 m-b-xs">
						<div style="font-size:10px">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Id registro</th>
											<th>Forma Recaudo</th>
											<th>Fecha recaudo</th>
											<th>Referencia </th>
											<th>Aportante </th>
											<th>Oficina BSC</th>
											<th>V. Efectivo</th>
											<th>V. Cheque</th>
											<th>V. Recaudo</th>
											


										</tr>
									</thead>
									<tbody>
										<xsl:for-each
											select="//ArchivoRecaudoOriginalPorConvertir/detalles/DetalleArchivoRecaudoOriginalPorConvertir">

											<tr>
												<td class=" align-center">
													<xsl:value-of select="daror_id_reg" />
												</td>

												<td class=" align-center">
													<xsl:variable name="trex" select="daror_tipo_reca"/>
													<xsl:choose>
														<xsl:when
															test="count(//TipoRecaudoExcluir[trex_trex = $trex])>0">
															<span class="label label-danger">
																<xsl:value-of select="daror_tipo_reca" />
															</span>
														</xsl:when>

														<xsl:otherwise>
															<xsl:value-of select="daror_tipo_reca" />
														</xsl:otherwise>

													</xsl:choose>

												</td>


												<td class=" align-center">
													<xsl:value-of select="daror_freca" />
												</td>
												<td class=" align-center">
													<xsl:value-of select="daror_referencia" />
												</td>
												<td class=" align-center">
													<xsl:value-of select="daror_aportante" />
												</td>
												<td class=" align-center">
													<xsl:value-of select="daror_ofic" />
												</td>
												<td class=" align-center">
													<xsl:value-of select="daror_vefe_format" />
												</td>
												<td class=" align-center">
													<xsl:value-of select="daror_vche_format" />
												</td>
												<td class=" align-center">
													
													<xsl:variable name="trex" select="daror_tipo_reca"/>
													
													
													<xsl:choose>
														<xsl:when
															test="count(//TipoRecaudoExcluir[trex_trex = $trex])>0">
															<span class="label label-danger">
																<xsl:value-of select="daror_vtot_format" />
															</span>
														</xsl:when>

														<xsl:otherwise>
															<xsl:value-of select="daror_vtot_format" />
														</xsl:otherwise>

													</xsl:choose>


												</td>

												




											</tr>

										</xsl:for-each>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>



			</xsl:when>

			<xsl:otherwise>


				<div class="alert alert-warning">
					No existen detalles del archivo Original
				</div>


			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>

	<xsl:template name="detalleErrores">
		<xsl:choose>

			<xsl:when test="count(//ArrayList/ErrorArchivoRecaudo)>0 and 1=1">

				<div class="row">
					<div class="col-sm-12 m-b-xs">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Criticidad</th>
										<th>Id registro Original</th>
										<th>Tipo de Error</th>
										<th>Detalle</th>
									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//ArrayList/ErrorArchivoRecaudo">

										<tr>
											<td class=" align-center">
												<div>
													<span class="label label-{tper_color}">
														o
													</span>
												</div>
											</td>
											<td class=" align-center">
												<xsl:value-of select="erar_daror_id_reg" />
											</td>

											<td class=" align-left">
												<xsl:value-of select="tper_descri" />
											</td>

											<td class=" align-center">
												<span class="label label-default">
													<xsl:value-of select="erar_error_descri" />
												</span>
											</td>

										</tr>

									</xsl:for-each>
								</tbody>
							</table>
						</div>
					</div>
				</div>



			</xsl:when>

			<xsl:otherwise>


				<div class="alert alert-warning">
					No existen errores para el archivo.
				</div>


			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>

	<xsl:template name="detalleArchivoSIFI">
		<xsl:choose>

			<xsl:when test="count(//ArchivoRecaudoGeneradoSIFI)>0 and 1=1">

		

				<div class="row">
					<div class="col-sm-12 m-b-xs">
					
				
					<h5 class="ownk-subrayado">
						<b>Archivo SIFI</b>
					</h5>
					
					<div class="alert alert-success">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th class=" ownk_align_left">Estado</th>
										<th class=" ownk_align_center">Nombre Archivo</th>
										<th class=" ownk_align_center">Registros</th>
										<th class=" ownk_align_center">Bytes</th>
										<th class=" ownk_align_center">Fecha Creación</th>
										<th class=" ownk_align_center">Usuario</th>
										<th class=" ownk_align_right">Opciones</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//ArchivoRecaudoGeneradoSIFI">
										<form id="form_arge_{arge_arge}" action="{//contextPath}/conversion/download.darge"
											method="post">
											<input type="hidden" name="darge" value="{arge_arge}" />

										</form>

										<tr>
											<td class=" ownk_align_left">
												<span class="badge badge-primary">
													<xsl:value-of select="arge_earge" />
												</span>
											</td>

											<td class=" ownk_align_center">
												<xsl:value-of select="arge_nombre" />
											</td>

											<td class=" ownk_align_center">
												<xsl:value-of select="arge_registros" />
											</td>

											<td class=" ownk_align_center">
												<xsl:value-of select="arge_bytes" />
											</td>

											<td class=" ownk_align_center">
												<xsl:value-of select="arge_fcrea" />
											</td>

											<td class=" ownk_align_center">
												<xsl:value-of select="arge_usua" />
											</td>

											<td class=" ownk_right">
												<a class="btn btn-white btn-circle btn-sm ownk_btn_shadow"
													onclick="osm_enviarFormulario('form_arge_{arge_arge}');">
													<i class="fa fa-download text-navy"></i>
												</a>
											</td>




										</tr>

									</xsl:for-each>
								</tbody>
							</table>
						</div>
					</div>
					</div>

				</div>


				



			</xsl:when>

			<xsl:when test="count(//ArrayList/ErrorArchivoRecaudo)>0 and 1=1">



				<div class="alert alert-danger">
					Lo sentimos. No existen archivo SIFI por generar. Se encontraron
					<b>
						(
						<xsl:value-of select="count(//ArrayList/ErrorArchivoRecaudo)" />
						) errores
					</b>
					en el proceso de
					conversión. Porfavor consulta la sección de errores
					para ampliar el detalle.
				</div>




			</xsl:when>

			<xsl:otherwise>


				<div class="alert alert-warning">
					No existen archivo SIFI aun generados. Por favor
					consulta luego de unos minutos. Estamos procesando
					el archivo
				</div>




			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>

	<xsl:template name="detalleResumenConversionSIFI">
		<xsl:choose>

			<xsl:when test="count(//ArrayList/DetalleResumenConversionSIFI)>0 and 1=1">

			<div class="row">
			<div class="col-sm-12 m-b-xs">

				<div class="row">
					<div class="col-sm-12 m-b-xs">	
	


					<h5 class="ownk-subrayado">
						<b>Remumen general de conversión </b>
					</h5>

					<div class="alert alert-warning">
						<div style="">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th class="ownk_align_left">Total Recaudos BSC</th>
											<th class="ownk_align_center">Recaudo total Archivo BSC </th>
											<th class="ownk_align_center">-</th>
											<th class="ownk_align_center">Total Recaudos SIFI</th>
											<th class="ownk_align_center">Recaudo total Archivo SIFI</th>
											<th class="ownk_align_center">-</th>
											<th class="ownk_align_center">Diferencia Recaudo</th>
											<th class="ownk_right">Opciones</th>
										</tr>
									</thead>
									<tbody>
									
										<form id="form_excel_cpar_{//aror_aror}" action="{//contextPath}/conversion/download.dcpar_excel"
											method="post">
											<input type="hidden" name="dcpar" value="{//aror_aror}" />
		
										</form>
										<tr>
											<td class=" ownk_align_left">
												<xsl:value-of
													select="count(//DetalleArchivoRecaudoOriginalPorConvertir)" />
											</td>
											<td class=" ownk_align_center">
												<xsl:value-of select="//totalRecaudoOriginal" />
											</td>

											<td class=" ownk_align_center">
												-
											</td>
											<td class=" ownk_align_center">
												<xsl:value-of select="count(//DetalleArchivoRecaudoGeneradoSIFI)" />
											</td>

											<td class=" ownk_align_center">
												<xsl:value-of select="//totalRecaudoFinal" />
											</td>

											<td class=" ownk_align_center">
												-
											</td>

											<td class=" ownk_align_center">
												<xsl:value-of select="//totalRecaudoDiferencia" />
											</td>
											
											
											<td class=" ownk_right">
												
												<a class="btn btn-white btn-circle btn-sm ownk_btn_shadow"
													onclick="osm_enviarFormulario('form_excel_cpar_{//aror_aror}');">
													<i class="fa fa-download text-navy"></i>
												</a>
											

											</td>
											
						

										</tr>


									</tbody>
								</table>
							</div>
						</div>
					</div>
					</div>
				</div>


				<div class="row">
					<div class="col-sm-12 m-b-xs">
					
					<h5 class="ownk-subrayado">
						<b>Detalle de validaciones y transformaciones</b>
					</h5>
					<div style="font-size:10px">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th class="ownk_align_center">Reg. </th>
										<th class="ownk_align_center">Recaudo</th>
										<th class="ownk_align_center">Estado</th>
										<th class="ownk_align_center">Ref. Original</th>
										<th class="ownk_align_center">Ref. Final</th>
										<th class="ownk_align_center">Aportante</th>
										<th class="ownk_align_center">V. Tot final</th>
										<th class="ownk_align_center">
											<i class="fa fa-exchange modal-icon" />
										</th>
										<th class="ownk_align_center">
											<i class="fa fa-check-square-o modal-icon" />
										</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//DetalleResumenConversionSIFI">
										<xsl:sort order="ascending" data-type="text" select="daror_referencia"/>

										<tr>
											<td class=" ownk_align_center">
												<xsl:value-of select="daror_id_reg" />
											</td>

											<td class=" ownk_align_center">


												<xsl:variable name="trex" select="daror_tipo_reca"/>
												<xsl:choose>
													<xsl:when
														test="count(//TipoRecaudoExcluir[trex_trex = $trex])>0">
														<span class="label label-danger">
															<xsl:value-of select="daror_tipo_reca" />
														</span>
													</xsl:when>

													<xsl:otherwise>
														<xsl:value-of select="daror_tipo_reca" />
													</xsl:otherwise>

												</xsl:choose>
											</td>

											<td class=" ownk_align_center">
												<span class="label label-{erds_color}">
													<xsl:value-of select="darge_erds" />
												</span>
											</td>

											<td class=" ownk_align_center">
												<xsl:value-of select="daror_referencia" />
											</td>

											<td class=" ownk_align_center">
												<xsl:choose>
													<xsl:when test="darge_trrf_sn='S'">
														<span class="label label-warning">
															<xsl:value-of select="darge_referencia" />
														</span>
													</xsl:when>

													<xsl:otherwise>
														<xsl:value-of select="darge_referencia" />
													</xsl:otherwise>

												</xsl:choose>

											</td>

											<td class=" ownk_align_center">

												<xsl:choose>
													<xsl:when test="darge_titular_sn='S'">
														<span class="label label-success">
															<xsl:value-of select="daror_aportante" />
														</span>
													</xsl:when>

													<xsl:when test="string-length(daror_aportante)=0">
														<xsl:value-of select="daror_aportante" />
													</xsl:when>

													<xsl:otherwise>
														<span class="label label-danger">
															<xsl:value-of select="daror_aportante" />
														</span>
													</xsl:otherwise>

												</xsl:choose>



											</td>

											<td class=" ownk_align_center">
												<xsl:choose>
													<xsl:when test="darge_frdp_sn='S'">
														<span class="label label-warning">
															<xsl:value-of select="darge_vtot_format" />
														</span>
													</xsl:when>

													<xsl:when test="not(darge_vtot)">
														<span class="label label-danger">
															<xsl:value-of select="darge_vtot_format" />
														</span>
													</xsl:when>

													<xsl:otherwise>

														<xsl:value-of select="darge_vtot_format" />

													</xsl:otherwise>
												</xsl:choose>
											</td>

											<td class=" ownk_align_center">
												<div class="">
													<button type="button"
														class="btn btn-sm btn-primary ownk_btn_shadow" id="btn_popup_trar_darge"
														data-toggle="modal" data-target="#myModalTrarDarge_{daror_id_reg}">
														<xsl:variable name="id_reg"  select="daror_id_reg" />
														<xsl:value-of select="count(//ArrayList/TransformacionArchivoRecaudo[trar_daror_id_reg=$id_reg])" />
													</button>

													<xsl:call-template name="popup_transformaciones_darge">
														<xsl:with-param name="id_reg">
															<xsl:value-of select="daror_id_reg" />
														</xsl:with-param>
													</xsl:call-template>
												</div>

											</td>

											<td class=" ownk_align_center">

												<div class="">
													<button type="button"
														class="btn btn-sm btn-primary ownk_btn_shadow" id="btn_popup_vlar_darge"
														data-toggle="modal" data-target="#myModalVlarDarge_{daror_id_reg}">
														<xsl:variable name="id_reg"  select="daror_id_reg" />
														<xsl:value-of select="count(//ArrayList/ValidacionArchivoRecaudo[vlar_daror_id_reg=$id_reg])" />
													</button>

													<xsl:call-template name="popup_validaciones_darge">
														<xsl:with-param name="id_reg">
															<xsl:value-of select="daror_id_reg" />
														</xsl:with-param>
													</xsl:call-template>
												</div>
											</td>


										</tr>

									</xsl:for-each>
								</tbody>
							</table>
						</div>
						
						</div>
					</div>

				</div>
				</div>
			</div>	



			</xsl:when>



			<xsl:otherwise>



			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>


	<xsl:template name="popup_transformaciones_darge">
		<xsl:param name="id_reg" />

		<div class="modal inmodal" id="myModalTrarDarge_{$id_reg}"
			tabindex="-1" role="dialog" aria-hidden="false" data-show="true">
			<div class="modal-dialog" data-show="true">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true"></span>
							<span class="sr-only">Close</span>
						</button>
						<i class="fa fa-list-ul modal-icon"></i>
						<h4 class="modal-title">Transformaciones</h4>
						<small class="font-bold">MOdificaciones de información por registro</small>
					</div>
					<div class="modal-body">
						<p>
							A continuación podrás consultar las transformaciones por
							registro.

						</p>

						<br />

						<div class="row">
							<div class="col-sm-12 m-b-xs">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Criticidad</th>
												<th>Id registro Original</th>
												<th>Tipo de transformacion</th>
												<th>Valor Original</th>
												<th>Valor Final</th>
											</tr>
										</thead>
										<tbody>
											<xsl:for-each
												select="//ArrayList/TransformacionArchivoRecaudo[trar_daror_id_reg = $id_reg]">

												<tr>

													<td class=" align-center">
														<div>
															<span class="label label-{tptr_color}">
																o
															</span>
														</div>
													</td>
													<td class=" align-center">
														<xsl:value-of select="trar_daror_id_reg" />
													</td>

													<td class=" align-left">
														<xsl:value-of select="tptr_descri" />
													</td>

													<td class=" align-center">
														<xsl:value-of select="trar_valor_orig" />
													</td>

													<td class=" align-center">
														<xsl:value-of select="trar_valor_modi" />
													</td>

												</tr>

											</xsl:for-each>
										</tbody>
									</table>
								</div>
							</div>
						</div>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white ownk_btn_shadow"
							data-dismiss="modal">Retornar</button>

					</div>
				</div>
			</div>
		</div>



	</xsl:template>

	<xsl:template name="popup_validaciones_darge">
		<xsl:param name="id_reg" />

		<div class="modal inmodal" id="myModalVlarDarge_{$id_reg}"
			tabindex="-1" role="dialog" aria-hidden="false" data-show="true">
			<div class="modal-dialog" data-show="true">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true"></span>
							<span class="sr-only">Close</span>
						</button>
						<i class="fa fa-list-ul modal-icon"></i>
						<h4 class="modal-title">Validaciones</h4>
						<small class="font-bold">Revisiones realizadas por registro</small>
					</div>
					<div class="modal-body">
						<p>
							A continuación podrás consultar las transformaciones por
							registro.

						</p>

						<br />

						<div class="row">
							<div class="col-sm-12 m-b-xs">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Criticidad</th>
												<th>Id registro Original</th>
												<th>Tipo de Validacion</th>
												<th>Detalle</th>
											</tr>
										</thead>
										<tbody>
											<xsl:for-each
												select="//ArrayList/ValidacionArchivoRecaudo[vlar_daror_id_reg=$id_reg]">

												<tr>
													<td class=" align-center">
														<div>
															<span class="label label-{tpvl_color}">
																o
															</span>
														</div>
													</td>

													<td class=" align-center">
														<xsl:value-of select="vlar_daror_id_reg" />
													</td>

													<td class=" align-left">
														<xsl:value-of select="tpvl_descri" />
													</td>

													<td class=" align-center">
														<xsl:value-of select="vlar_valor_descri" />
													</td>

												</tr>

											</xsl:for-each>
										</tbody>
									</table>
								</div>
							</div>
						</div>



					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white ownk_btn_shadow"
							data-dismiss="modal">Retornar</button>

					</div>
				</div>
			</div>
		</div>



	</xsl:template>


	<xsl:template name="popup_historico_aror">


		<div class="modal inmodal" id="myModalHistoricoAror" tabindex="-1"
			role="dialog" aria-hidden="false" data-show="true">
			<div class="modal-dialog" data-show="true">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true"></span>
							<span class="sr-only">Close</span>
						</button>
						<i class="fa fa-list-ul modal-icon"></i>
						<h4 class="modal-title">Histórico</h4>
						<small class="font-bold">Estados del archivo</small>
					</div>
					<div class="modal-body">
						<p>
							A continuación podrás consultar todos los estados del archivo.
							Revisar la fecha y hora del modificación y el usuario quien lo
							realizó.
						</p>

						<br />

						<div class="row">
							<div class="col-sm-12 m-b-xs">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>id</th>
												<th>Estado</th>
												<th>Fecha</th>
												<th>Observación</th>
												<th>Usuario</th>

											</tr>
										</thead>
										<tbody>
											<xsl:for-each select="//HistoricoArchivoRecaudoOriginalPorConvertir">
												<tr>
													<td class=" align-center">
														<xsl:value-of select="position()" />
													</td>
													<td class=" align-center">
														<xsl:value-of select="haror_earor" />
													</td>

													<td class=" align-center">
														<span class="label label-default">
															<xsl:value-of select="haror_fasig" />
														</span>

													</td>

													<td class=" align-center">
														<xsl:value-of select="haror_obser" />
													</td>

													<td class=" align-center">
														<xsl:value-of select="haror_usua" />
													</td>
												</tr>

											</xsl:for-each>
										</tbody>
									</table>
								</div>
							</div>
						</div>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white ownk_btn_shadow"
							data-dismiss="modal">Retornar</button>

					</div>
				</div>
			</div>
		</div>

	</xsl:template>




	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->


	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

</xsl:stylesheet>