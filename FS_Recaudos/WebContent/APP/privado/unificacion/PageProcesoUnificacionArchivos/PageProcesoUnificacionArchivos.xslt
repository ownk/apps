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

			<body>
				<div id="wrapper">

					<!-- MENU -->
					<xsl:call-template name="nav-bar-left" />

					<div id="page-wrapper" class="gray-bg dashbard-1">
						<div class="row border-bottom">
							<xsl:call-template name="nav-bar-top" />
						</div>


						<!-- TITULO -->
						<div class="row  border-bottom white-bg dashboard-header">

							<div class="col-xs-10">
								<h2>Consulta de Proceso </h2>
								<ol class="breadcrumb">
									<li>
										<a>Consultas</a>
									</li>
									<li class="active">
										<strong>Consultar Proceso </strong>
									</li>
								</ol>
							</div>

						</div>


						<!-- CONTENIDO -->

						<div class="row">
							<div class="col-xs-12">
								<div class="wrapper wrapper-content animated fadeInUp">

									<!-- INFORMACION GENERAL -->
									<div class="row">
										<div class="col-md-7">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>Detalle Proceso  </h5>

												</div>

												<div class="ibox-content">
													<div class="row">
														<div class="col-lg-12">
															<div class="m-b-md">

																<h2>
																	Proceso de Unificación No.
																	<xsl:value-of select="//prun_prun" />
																</h2>
															</div>
															<dl class="dl-horizontal">
																<dt>Estado:</dt>
																<dd>
																	<span class="label label-primary">
																		<xsl:value-of select="//prun_eprun" />
																	</span>
																</dd>
																<dt></dt>
																<dd>	
																	<br/>
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
													
													
													<div class="row">
														<div class="col-lg-12">
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
													
													

												</div>

											</div>
										</div>


										<!-- INDICADORES -->

										<div class="col-md-5">
											<div class="row">
												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-success pull-right">AZPU</span>
															<h5>ZIP</h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">
																<xsl:value-of select="count(//ArchivoZIPProcesoUnificacion)" />
															</h1>
															
															<div class="stat-percent font-bold text-success">
																100%
																<i class="fa fa-bolt"></i>
															</div>
															<small>Total archivos</small>
															<br/>
														</div>
													</div>
												</div>

												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-success pull-right">ARPU</span>
															<h5>Planos</h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">
																<xsl:value-of select="count(//ArchivoRecaudoPorUnificar )" />
															</h1>
															<div class="stat-percent font-bold text-success">
																100%
																<i class="fa fa-bolt"></i>
															</div>
															<small>Total archivos</small>
															<br/>
														</div>
													</div>
												</div>
											</div>
											

											<div class="row">
												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-success pull-right">TRAR</span>
															<h5><small>Asobancaria</small></h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">
																<xsl:value-of select="count(//TransformacionArchivoRecaudo)" />
															</h1>
															<div class="stat-percent font-bold text-success">
																100%
																<i class="fa fa-bolt"></i>
															</div>
															<small>Archivos Creados</small>
															<br/>
														</div>
													</div>
												</div>
												<div class="col-xs-6">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<span class="label label-success pull-right">ARUN</span>
															<h5><small>Unificados</small></h5>
														</div>
														<div class="ibox-content">
															<h1 class="no-margins">
																<xsl:value-of select="count(//ArchivoRecaudoUnificado)" />
															</h1>
															<div class="stat-percent font-bold text-success">

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
															<small>Total archivos</small>
															<br/>
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
																				<a href="#tab-1" data-toggle="tab">Archivos ZIP</a>
																			</li>
																			<li class="">
																				<a href="#tab-2" data-toggle="tab">Archivos Por
																					Unificar</a>
																			</li>
																			<li class="">
																				<a href="#tab-3" data-toggle="tab">Archivos
																					Transformados</a>
																			</li>
																			<li class="active">
																				<a href="#tab-4" data-toggle="tab">Archivos Unificados</a>
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
												<span class="label label-default"><xsl:value-of select="azpu_nombre" /></span>
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
												<span class="label label-default"><xsl:value-of select="arpu_tpar" /></span>
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
												<span class="label label-default"><xsl:value-of select="trar_tpar" /></span>
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
										<th>Fecha de creación</th>
										<th>Descarga</th>

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
												<span class="label label-default"><xsl:value-of select="arun_tpar" /></span>
												
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
												<xsl:value-of select="arun_fcrea" />
											</td>

											<td class=" align-center">
												<a class="btn btn-white btn-circle btn-sm" onclick="osm_enviarFormulario('form_arun_{arun_arun}');">
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

					<button type="submit" class="btn btn-primary pull-right">Generar Archivos Unificados</button>
					<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
						value="{//ProcesoUnificacionArchivos/prun_prun}" />


				</form>


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



	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->


	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

</xsl:stylesheet>