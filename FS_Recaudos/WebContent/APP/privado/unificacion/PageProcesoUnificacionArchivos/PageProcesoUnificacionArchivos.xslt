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
				src="privado/unificacion/PageProcesoUnificacionArchivos/PageProcesoUnificacionArchivos.js"></add>

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
														<i class="fa fa-gears  mid-icon ownk-color-naranja"></i>
													</div>
													<div class="p-xs">

														<h2>
															Consulta de Proceso No.
															<xsl:value-of select="//prun_prun" />
														</h2>
														<ol class="breadcrumb">
															<li>
																<a>Consultas</a>
															</li>
															<li class="active">
																<strong>Consultar Proceso </strong>
															</li>
														</ol>
														<br />

														<span>
															En esta sección podrás consultar la
															<strong>información general
																del proceso</strong>
															: histórico de estados,
															fechas y archivos
															cargados
															y
															generados.
														</span>


													</div>





												</div>
											</div>
										</div>
									</div>
								</div>
							</div>




							<!-- CONTENIDO -->

							<div class="row">
								<div class="col-xs-12">
									<div class="row">

										<!-- INFORMACION GENERAL -->
										<div class="col-xs-6">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>Detalle Proceso  </h5>

												</div>

												<div class="ibox-content">
													<div class="row">

														<div class="col-xs-12">
															Informacion básica del proceso
														</div>

													</div>

													<div class="row">
														<div class="ownk_separador_h" />

													</div>

													<div class="row">
														<div class="col-md-12">
															<div class="row">
																<div>
																	<dl class="dl-horizontal">

																		<dt>Creado por</dt>
																		<dd>
																			<xsl:value-of select="//prun_usua" />
																		</dd>

																		<dt>Fecha de creación</dt>
																		<dd>
																			<xsl:value-of select="//prun_fcrea" />
																		</dd>


																		<dt>Fecha Inicio</dt>
																		<dd>
																			<xsl:value-of select="//prun_fini" />
																		</dd>
																		<dt>Fecha Fin</dt>
																		<dd>
																			<xsl:value-of select="//prun_ffin" />
																		</dd>


																		<dt>Observación</dt>
																		<dd>
																			<xsl:value-of select="//prun_observ" />
																		</dd>


																	</dl>
																</div>
															</div>

															<div class="row">

																<dl class="dl-horizontal">
																	<dt>Unificación:</dt>
																	<dd>

																		<div class="">
																			<button type="button"
																				class="btn btn-sm btn-primary ownk_btn_shadow" id="btn_popup_historico"
																				data-toggle="modal" data-target="#myModalHistoricoPrun">
																				<xsl:value-of select="//prun_eprun" />
																			</button>

																			<xsl:call-template name="popup_historico_prun" />
																		</div>
																	</dd>

																	<xsl:if test="count(//ProcesoConversionArchivos)>0">
																		<dt>Conversión:</dt>
																		<dd>

																			<div class="">
																				<button type="button"
																					class="btn btn-sm btn-primary ownk_btn_shadow" id="btn_popup_historico_prco"
																					data-toggle="modal" data-target="#myModalHistoricoPrco">
																					<xsl:value-of select="//prco_eprco" />
																				</button>

																				<xsl:call-template name="popup_historico_prco" />
																			</div>
																		</dd>
																	</xsl:if>

																</dl>

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
															<span class="label label-info pull-right">AZPU</span>
															<h5>
																<small>ZIP</small>
															</h5>
														</div>
														<div class="ibox-content">

															<h1 class="no-margins">
																<i class="fa fa-file-zip-o modal-icon">
																</i>
																<xsl:value-of
																	select="concat('  ', count(//ArchivoZIPProcesoUnificacion))" />
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
															<span class="label label-info pull-right">ARPU</span>
															<h5>
																<small>Planos</small>
															</h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">

																<i class="fa fa-files-o modal-icon">
																</i>
																<xsl:value-of
																	select="concat('  ',count(//ArchivoRecaudoPorUnificar ))" />
															</h1>
															<div class="stat-percent font-bold text-warning">
																100%
																<i class="fa fa-bolt"></i>
															</div>
															<small>archivos</small>
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
																<small>Asoban...</small>
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
															<small>Creados</small>
															<br />
														</div>
													</div>
												</div>
												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-info pull-right">ARUN</span>
															<h5>
																<small>Unifi...</small>
															</h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">
																<i class="fa fa-file-text modal-icon">
																</i>
																<xsl:value-of
																	select="concat('  ',count(//ArchivoRecaudoUnificado))" />
															</h1>
															<div class="stat-percent font-bold text-warning">

																<xsl:choose>
																	<xsl:when test="count(//ArchivoRecaudoUnificado)>0">
																		100%
																	</xsl:when>
																	<xsl:otherwise>
																		0%
																	</xsl:otherwise>


																</xsl:choose>


																<i class="fa fa-bolt"></i>
															</div>
															<small>Total</small>
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
													<h5>Detalle Archivos  </h5>



												</div>
												<div class="ibox-content">
													<div class="row m-t-sm">
														<div class="col-xs-12">
															<div class="panel blank-panel">
																<div class="panel-heading">
																	<div class="panel-options">
																		<ul class="nav nav-tabs">
																			<li class="">
																				<a href="#tab-1" data-toggle="tab">ZIP</a>
																			</li>
																			<li class="">
																				<a href="#tab-2" data-toggle="tab">Por
																					Unificar</a>
																			</li>
																			<li class="">
																				<a href="#tab-3" data-toggle="tab">
																					Asobancaria</a>
																			</li>
																			<li class="active">
																				<a href="#tab-4" data-toggle="tab">Unificados</a>
																			</li>

																			<li class="">
																				<a href="#tab-5" data-toggle="tab">Archivos SIFI</a>
																			</li>
																		</ul>
																	</div>
																</div>

																<div class="panel-body">

																	<div class="tab-content">
																		<div class="tab-pane " id="tab-1">
																			<xsl:call-template name="archivosAZPU" />

																		</div>


																		<div class="tab-pane" id="tab-2">
																			<xsl:call-template name="archivosARPU" />



																		</div>

																		<div class="tab-pane" id="tab-3">
																			<xsl:call-template name="archivosTRAR" />

																		</div>

																		<div class="tab-pane active" id="tab-4">
																			<xsl:call-template name="archivosARUN" />


																		</div>

																		<div class="tab-pane " id="tab-5">
																			<xsl:call-template name="archivosSIFI" />


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

	<xsl:template name="archivosAZPU">
		<xsl:choose>

			<xsl:when test="count(//ArchivoZIPProcesoUnificacion)>0 and 1=1">

				<div class="row">
					<div class="col-sm-12 m-b-xs">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Id</th>
										<th>Nombre</th>
										<th>Bytes </th>
										<th>Sub archivos Recaudo </th>
										<th>Fecha de creación</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//ArchivoZIPProcesoUnificacion">

										<tr>
											<td class=" align-center">
												<xsl:value-of select="position()" />
											</td>

											<td class=" align-center">
												<span class="label label-default">
													<xsl:value-of select="azpu_nombre" />
												</span>
											</td>

											<td class=" align-center">
												<xsl:value-of select="azpu_bytes" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="azpu_archivos" />
											</td>
											<td class=" align-center">
												<xsl:value-of select="azpu_fcrea" />
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
					No existen archivos ZIP asociados al proceso.
				</div>


			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>

	<xsl:template name="archivosARPU">
		<xsl:choose>

			<xsl:when test="count(//ArchivoRecaudoPorUnificar)>0 and 1=1">

				<div class="row">
					<div class="col-sm-12 m-b-xs">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Id</th>
										<th>Nombre</th>
										<th>Tipo</th>
										<th>Bytes </th>
										<th>Registros </th>
										<th>Fecha de creación</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//ArchivoRecaudoPorUnificar">

										<tr>
											<td class=" align-center">
												<xsl:value-of select="position()" />
											</td>
											<td class=" align-center">
												<xsl:value-of select="arpu_nombre" />
											</td>

											<td class=" align-center">
												<span class="label label-default">
													<xsl:value-of select="arpu_tpar" />
												</span>
											</td>

											<td class=" align-center">
												<xsl:value-of select="arpu_bytes" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="arpu_registros" />
											</td>
											<td class=" align-center">
												<xsl:value-of select="arpu_fcrea" />
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
					No existen archivos por unificar registrados.
				</div>


			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>

	<xsl:template name="archivosTRAR">
		<xsl:choose>

			<xsl:when test="count(//TransformacionArchivoRecaudo)>0 and 1=1">

				<div class="row">
					<div class="col-sm-12 m-b-xs">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Id</th>
										<th>Tipo</th>
										<th>Nombre archivo Origen</th>
										<th>Total Registros Origen</th>
										<th>Nombre archivo Destino</th>
										<th>Total Registros Destino</th>
										<th>Fecha de creación</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//TransformacionArchivoRecaudo">

										<tr>
											<td class=" align-center">
												<xsl:value-of select="position()" />
											</td>
											<td class=" align-center">
												<span class="label label-default">
													<xsl:value-of select="trar_tpar" />
												</span>
											</td>

											<td class=" align-center">
												<xsl:value-of select="nombreOrigen" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="trar_reg_file_ini" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="nombreDestino" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="trar_reg_file_fin" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="trar_fcrea" />
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
					No existen archivos transformados.
				</div>


			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>

	<xsl:template name="archivosARUN">
		<xsl:choose>

			<xsl:when test="count(//ArchivoRecaudoUnificado)>0 and 1=1">

				<div class="row">
					<div class="col-sm-12 m-b-xs">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Id</th>
										<th>Nombre</th>
										<th>Tipo</th>
										<th>Bytes </th>
										<th>Registros </th>
										<th>Consolidados</th>
										<th>Repetidos</th>
										<th>Fecha de creación</th>
										<th class="ownk_align_center">Descarga</th>
										<th class="ownk_align_center">Comparaciones Internet</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//ArchivoRecaudoUnificado">
										<form id="form_arun_{arun_arun}" action="{//contextPath}/unificacion/download.darun"
											method="post">
											<input type="hidden" name="darun" value="{arun_arun}" />

										</form>
										
										<tr>
											<td class=" align-center">
												<xsl:value-of select="position()" />
											</td>
											<td class=" align-center">
												<xsl:value-of select="arun_nombre" />
											</td>

											<td class=" align-center">
												<span class="label label-default">
													<xsl:value-of select="arun_tpar" />
												</span>

											</td>

											<td class=" align-center">
												<xsl:value-of select="arun_bytes" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="arun_registros" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="arun_archivos" />
											</td>

											<td class=" align-center">

												<xsl:choose>
													<xsl:when test="arun_archivos_repetidos>0">
														<xsl:variable name="idpopup">myModalArchivoRepetido_<xsl:value-of select="arun_arun"/></xsl:variable>

														<div class="">
															<button type="button"
																class="btn btn-sm btn-primary ownk_btn_shadow" id="btn_popup_archivo_repetido"
																data-toggle="modal" data-target="#{$idpopup}">
																<xsl:value-of select="arun_archivos_repetidos" />
															</button>

															<xsl:call-template name="popup_archivo_repetido">
																<xsl:with-param name="idpopup" select="$idpopup" />
															</xsl:call-template>


														</div>

													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="arun_archivos_repetidos" />
													</xsl:otherwise>


												</xsl:choose>

											</td>

											<td class=" align-center">
												<xsl:value-of select="arun_fcrea" />
											</td>

											<td class=" ownk_align_center">
												<a class="btn btn-white btn-circle btn-sm ownk_btn_shadow"
													onclick="osm_enviarFormulario('form_arun_{arun_arun}');">
													<i class="fa fa-download text-navy"></i>
												</a>
											</td>



											<td class=" ownk_align_center">

												<xsl:variable name="idpopup">myModalComparacionesARUN_<xsl:value-of select="arun_arun" />
												</xsl:variable>

												<div class="">
													<button type="button"
														class="btn btn-sm btn-primary ownk_btn_shadow" id="btn_popup_historico"
														data-toggle="modal" data-target="#{$idpopup}">
														<xsl:value-of
															select="count(comparacionesArchivoRecaudos/ComparacionArchivoRecaudo)" />
													</button>


													<xsl:call-template name="popup_comparaciones_arun">
														<xsl:with-param name="idpopup" select="$idpopup" />
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



			</xsl:when>

			<xsl:otherwise>


				<div class="alert alert-warning">
					No existen archivos por unificar registrados. Puedes
					<b>realizar la unificación de archivos</b>
					haciendo click en la siguiente opción
				</div>

				<form
					action="{//contextPath}/unificacion/PageUnificarArchivosPorProceso.do"
					method="post">

					<button type="submit" class="btn btn-primary pull-right ownk_btn_shadow">Generar Archivos Unificados</button>
					<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
						value="{//ProcesoUnificacionArchivos/prun_prun}" />


				</form>


			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>

	<xsl:template name="archivosSIFI">
		<xsl:choose>

			<xsl:when test="count(//ArchivoRecaudoOriginalPorConvertir)>0 and 1=1">

				<div class="row">
					<div class="col-sm-12 m-b-xs">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Id</th>
										<th>Tipo Archivo</th>
										<th>Nombre Archivo Unificado</th>
										<th>Registros BSC</th>
										<th>Estado</th>
										<th>Nombre Archivo SIFI</th>
										<th>Registros SIFI</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//ArchivoRecaudoOriginalPorConvertir">

										<tr>
											<td class=" align-center">
												<xsl:value-of select="position()" />
											</td>

											<td class=" align-center">
												<span class="label label-default">
													<xsl:value-of select="aror_tpar" />
												</span>

											</td>

											<td class=" align-center">
												<xsl:value-of select="aror_nombre" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="aror_registros" />
											</td>

											<td class=" align-center">

												<xsl:choose>
													<xsl:when test="aror_earor='CONVERTIDO'">
														<span class="badge badge-info">

															<xsl:value-of select="aror_earor" />
														</span>
													</xsl:when>

													<xsl:when test="aror_earor='CON_ERRORES'">
														<span class="badge badge-danger">

															<xsl:value-of select="aror_earor" />
														</span>
													</xsl:when>
													<xsl:otherwise>
														<span class="badge badge-warning">

															<xsl:value-of select="aror_earor" />
														</span>
													</xsl:otherwise>

												</xsl:choose>

											</td>


											<td class=" align-center">
												<xsl:choose>
													<xsl:when test="count(archivoRecaudoGeneradoSIFI)>0">
														<xsl:value-of select="archivoRecaudoGeneradoSIFI/arge_nombre" />
													</xsl:when>
													<xsl:otherwise>
														-
													</xsl:otherwise>

												</xsl:choose>

											</td>

											<td class=" align-center">
												<xsl:choose>
													<xsl:when test="count(archivoRecaudoGeneradoSIFI)>0">
														<xsl:value-of select="archivoRecaudoGeneradoSIFI/arge_registros" />
													</xsl:when>
													<xsl:otherwise>
														-
													</xsl:otherwise>

												</xsl:choose>

											</td>



											<td class=" align-center">


												<form id="form_aror_{aror_aror}"
													action="{//contextPath}/conversion/PageArchivoRecaudoOriginalPorConvertir.do"
													method="post">
													<input type="hidden" name="aror_aror" value="{aror_aror}" />

												</form>

												<a class="btn btn-white btn-circle btn-sm ownk_btn_shadow"
													onclick="osm_enviarFormulario('form_aror_{aror_aror}');">
													<i class="fa fa-search text-navy"></i>
												</a>



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
					No existen archivos SIFI aun generados. Por favor
					consulta luego de unos minutos. Estamos procesando
					los archivos.
				</div>




			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>

	<xsl:template name="avanceProceso">
		<div class="row">
			<div class="col-xs-12">
				<dl class="dl-horizontal">
					<dt>Completado:</dt>
					<dd>

						<div class="progress progress-striped active">



							<xsl:choose>
								<xsl:when test="count(//archivosARUN/ArchivoRecaudoUnificado)>0">
									<div style="width: 100%" class="progress-bar progress-bar-active">
										<span class="sr-only">60% Complete (danger)</span>
									</div>
								</xsl:when>
								<xsl:otherwise>

									<div style="width: 40%" class="progress-bar progress-bar-warning">
										<span class="sr-only">40% Complete (success)</span>
									</div>
								</xsl:otherwise>

							</xsl:choose>

						</div>
						<small>

							<xsl:choose>
								<xsl:when test="count(//archivosARUN/ArchivoRecaudoUnificado)>0">
									Proceso completado al
									<strong>100%</strong>
									. Ya es posible descargar los archivos unificados
								</xsl:when>
								<xsl:otherwise>
									Proceso completado al
									<strong>40%</strong>
									. Se requiere la unificacion de archivos para
									finalizar.
								</xsl:otherwise>
							</xsl:choose>


						</small>


					</dd>
				</dl>
			</div>
		</div>

	</xsl:template>

	<xsl:template name="popup_historico_prun">


		<div class="modal inmodal" id="myModalHistoricoPrun" tabindex="-1"
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
						<small class="font-bold">Estados del proceso de unificación de archivos</small>
					</div>
					<div class="modal-body">
						<p>
							A continuación podrás consultar todos los estados del proceso.
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
											<xsl:for-each select="//HistoricoProcesoUnificacionArchivos">
												<tr>
													<td class=" align-center">
														<xsl:value-of select="position()" />
													</td>
													<td class=" align-center">
														<xsl:value-of select="hprun_eprun" />
													</td>

													<td class=" align-center">
														<span class="label label-default">
															<xsl:value-of select="hprun_fasig" />
														</span>

													</td>

													<td class=" align-center">
														<xsl:value-of select="hprun_obser" />
													</td>

													<td class=" align-center">
														<xsl:value-of select="hprun_usua" />
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

	<xsl:template name="popup_historico_prco">


		<div class="modal inmodal" id="myModalHistoricoPrco" tabindex="-1"
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
						<small class="font-bold">Estados del proceso de conversión de archivos</small>
					</div>
					<div class="modal-body">
						<p>
							A continuación podrás consultar todos los estados del proceso.
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
											<xsl:for-each select="//HistoricoProcesoConversionArchivos">
												<tr>
													<td class=" align-center">
														<xsl:value-of select="position()" />
													</td>
													<td class=" align-center">
														<xsl:value-of select="hprco_eprco" />
													</td>

													<td class=" align-center">
														<span class="label label-default">
															<xsl:value-of select="hprco_fasig" />
														</span>

													</td>

													<td class=" align-center">
														<xsl:value-of select="hprco_obser" />
													</td>

													<td class=" align-center">
														<xsl:value-of select="hprco_usua" />
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

	<xsl:template name="popup_archivo_repetido">
		<xsl:param name="idpopup" />


		<div class="modal inmodal" id="{$idpopup}" tabindex="-1" role="dialog"
			aria-hidden="false" data-show="true">
			<div class="modal-dialog" data-show="true">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true"></span>
							<span class="sr-only">Close</span>
						</button>
						<i class="fa fa-list-ul modal-icon"></i>
						<h4 class="modal-title">Archivos Repetidos </h4>
						<small class="font-bold">Listado de archivos encontrados</small>
					</div>
					<div class="modal-body">
						<p>
							A continuación podrás consultar los archivos repetidos
							que se
							encontraron en la unificacion de archivos
							para el tipo de archivo
							<b>
								.
								<xsl:value-of select="arun_tpar" />
							</b>
						</p>

						<br />

						<div class="row">
							<div class="col-sm-12 m-b-xs">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>id</th>
												<th>Nombre Archivo</th>
												<th>Extension</th>
												<th>Cantidad Registros</th>
												<th>Archivo ZIP</th>
											</tr>
										</thead>
										<tbody>
											<xsl:for-each
												select="archivosPorUnificarRepetidos/ArchivoRecaudoPorUnificarRepetido">
												<tr>
													<td class=" align-center">
														<xsl:value-of select="position()" />
													</td>
													<td class=" align-center">
														<xsl:value-of select="archivoRecaudoPorUnificar/arpu_nombre" />
													</td>

													<td class=" align-center">
														<span class="label label-default">
															<xsl:value-of select="archivoRecaudoPorUnificar/arpu_extension" />
														</span>

													</td>

													<td class=" align-center">
														<xsl:value-of select="archivoRecaudoPorUnificar/arpu_registros" />
													</td>

													<td class=" align-center">
														<xsl:value-of
															select="archivoRecaudoPorUnificar/archivoAZPU/azpu_nombre" />
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

	<xsl:template name="popup_comparaciones_arun">
		<xsl:param name="idpopup" />


		<div class="modal inmodal" id="{$idpopup}" tabindex="-1" role="dialog"
			aria-hidden="false" data-show="true">
			<div class="modal-dialog" data-show="true">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true"></span>
							<span class="sr-only">Close</span>
						</button>
						<i class="fa fa-list-ul modal-icon"></i>
						<h4 class="modal-title">Comparaciones Archivos Internet</h4>
						<small class="font-bold">Listado de comparaciones encontradas</small>
					</div>
					<div class="modal-body">
						<p>
							A continuación podrás consultar las comparaciones (<xsl:value-of select="count(comparacionesArchivoRecaudos/ComparacionArchivoRecaudo)"/>)
							realizadas sobre el archivo unificado 
							<b>
								<span class="label label-default">
								.
								<xsl:value-of select="arun_tpar" />
								</span>
							</b>
							Si deseas crear una nueva comparación por favor selecciona
									la opción <b>Nueva Comparación</b>.
						</p>

						<br />

						<xsl:if test="count(comparacionesArchivoRecaudos/ComparacionArchivoRecaudo)>0">
							<div class="row">
								<div class="col-sm-12 m-b-xs">
									<div class="table-responsive">
										<table class="table table-striped">
											<thead>
												<tr>
													<th class="ownk_align_center"></th>
													<th class="ownk_align_center">id</th>
													<th class="ownk_align_center">Fecha de creación</th>
													<th class="ownk_align_center">Validación Cuenta Bancaria</th>
													<th class="ownk_align_center">Consultar</th>
	
												</tr>
											</thead>
											<tbody>
												<xsl:for-each
													select="comparacionesArchivoRecaudos/ComparacionArchivoRecaudo">
													<tr>
														<td class=" ownk_align_center">
															<xsl:value-of select="position()" />
														</td>
														<td class=" ownk_align_center">
															<xsl:value-of select="cpar_cpar" />
														</td>
	
														<td class=" ownk_align_center">
															<span class="label label-default">
																<xsl:value-of select="cpar_fcrea" />
															</span>
	
														</td>
	
														<td class=" ownk_align_center">
															<xsl:choose>
																<xsl:when test="cpar_arun_cta = cpar_ibsc_cta">
																	<span class="ownk_text_verde">
																		<b>EXITOSA</b>
																	</span>
																</xsl:when>

																<xsl:otherwise>
																	<span class="ownk_text_rojo">
																		<b>ERRONEA</b>
																	</span>
																</xsl:otherwise>

															</xsl:choose>


														</td>
														
																
														<td class=" ownk_align_center">
															
															<form id="form_cpar_{cpar_cpar}"
																action="{//contextPath}/compara/PageComparacionArchivoRecaudo.do"
																method="post">
																<input type="hidden" name="cpar_cpar" value="{cpar_cpar}" />
			
															</form>
			
															<a class="btn btn-white btn-circle btn-sm ownk_btn_shadow"
																onclick="osm_enviarFormulario('form_cpar_{cpar_cpar}');">
																<i class="fa fa-search text-navy"></i>
															</a>

															
															
														</td>
	
	
													</tr>
	
												</xsl:for-each>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</xsl:if>
						
						<div class="row">
							<div class="col-sm-12 m-b-xs">
								<form id="form_internet_{arun_arun}"
									action="{//contextPath}/compara/PageRegistrarArchivoInternetBSC.do"
									method="post">
									<input type="hidden" name="arun_arun" value="{arun_arun}" />
								</form>
								
								<div class="btn-group">
							
									<button id="btn_compararArchivos" onclick="osm_enviarFormulario('form_internet_{arun_arun}');"
										class="btn btn-primary btn-sm ownk_btn_shadow">
										<i class="fa fa-save" />
										Nueva
										Comparación
									</button>
							
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