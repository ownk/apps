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

							<div class="col-lg-10">
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
							<div class="col-lg-12">
								<div class="wrapper wrapper-content">
									<div class="col-lg-9">
										<div class="wrapper wrapper-content animated fadeInUp">
											<div class="ibox">
												<div class="ibox-content">
													<div class="row">
														<div class="col-lg-12">
															<div class="m-b-md">

																<h2>
																	Proceso de Unificacion Archivos No. No.
																	<xsl:value-of select="//prun_prun" />
																</h2>
															</div>
															<dl class="dl-horizontal">
																<dt>Status:</dt>
																<dd>
																	<span class="label label-primary">
																		<xsl:value-of select="//prun_eprun" />
																	</span>
																</dd>
															</dl>
														</div>
													</div>
													<div class="row">
														<div class="col-lg-5">
															<dl class="dl-horizontal">

																<dt>Creado por</dt>
																<dd>
																	<xsl:value-of select="//prun_usua" />
																</dd>

																<dt>Fecha de creación</dt>
																<dd>
																	<xsl:value-of select="//prun_fcrea" />
																</dd>


															</dl>
														</div>
														<div class="col-lg-7" id="cluster_info">
															<dl class="dl-horizontal">

																<dt>Archivos ZIP:</dt>
																<dd>
																	<xsl:value-of select="//prun_archivos" />
																</dd>
																<dt>Fecha Inicio</dt>
																<dd>
																	<xsl:value-of select="//prun_fini" />
																</dd>
																<dt>Fecha Fin</dt>
																<dd>
																	<xsl:value-of select="//prun_ffin" />
																</dd>
															</dl>
														</div>
													</div>
													<div class="row">
														<div class="col-lg-12">
															<dl class="dl-horizontal">
																<dt>Completado:</dt>
																<dd>

																	<div class="progress progress-striped active">

																		

																		<xsl:choose>
																			<xsl:when
																				test="count(//archivosARUN/ArchivoRecaudoUnificado)>0">
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
																			<xsl:when
																				test="count(//archivosARUN/ArchivoRecaudoUnificado)>0">
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
													<div class="row m-t-sm">
														<div class="col-lg-12">
															<div class="panel blank-panel">
																<div class="panel-heading">
																	<div class="panel-options">
																		<ul class="nav nav-tabs">
																			<li class="active">
																				<a href="#tab-1" data-toggle="tab">Archivos ZIP</a>
																			</li>
																			<li class="">
																				<a href="#tab-2" data-toggle="tab">Archivos Por Unificar</a>
																			</li>
																			<li class="">
																				<a href="#tab-3" data-toggle="tab">Archivos
																					Transformados</a>
																			</li>
																			<li class="">
																				<a href="#tab-4" data-toggle="tab">Archivos Unificados</a>
																			</li>
																		</ul>
																	</div>
																</div>

																<div class="panel-body">

																	<div class="tab-content">
																		<div class="tab-pane active" id="tab-1">
																			<xsl:call-template name="archivosAZPU" />

																		</div>


																		<div class="tab-pane" id="tab-2">
																			<xsl:call-template name="archivosARPU" />



																		</div>

																		<div class="tab-pane" id="tab-3">
																			<xsl:call-template name="archivosTRAR" />

																		</div>

																		<div class="tab-pane" id="tab-4">
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
									<div class="col-lg-3">
										<div class="wrapper wrapper-content project-manager">
											<h4>Project description</h4>
											<img src="{//contextPath}/general/img/zender_logo.png"
												class="img-responsive" />
											<p class="small">
												There are many variations of passages of Lorem
												Ipsum available,
												but the majority have suffered alteration in
												some form, by
												injected humour, or randomised words which
												don't look
												even slightly believable. If you are going to use
												a passage of
												Lorem Ipsum, you need to be sure there isn't
												anything
												embarrassing
											</p>
											<p class="small font-bold">
												<span>
													<i class="fa fa-circle text-warning"></i>
													High priority
												</span>
											</p>
											<h5>Project tag</h5>
											<ul class="tag-list" style="padding: 0">
												<li>
													<a href="">
														<i class="fa fa-tag"></i>
														Zender
													</a>
												</li>
												<li>
													<a href="">
														<i class="fa fa-tag"></i>
														Lorem ipsum
													</a>
												</li>
												<li>
													<a href="">
														<i class="fa fa-tag"></i>
														Passages
													</a>
												</li>
												<li>
													<a href="">
														<i class="fa fa-tag"></i>
														Variations
													</a>
												</li>
											</ul>
											<h5>Project files</h5>
											<ul class="list-unstyled project-files">
												<li>
													<a href="">
														<i class="fa fa-file"></i>
														Project_document.docx
													</a>
												</li>
												<li>
													<a href="">
														<i class="fa fa-file-picture-o"></i>
														Logo_zender_company.jpg
													</a>
												</li>
												<li>
													<a href="">
														<i class="fa fa-stack-exchange"></i>
														Email_from_Alex.mln
													</a>
												</li>
												<li>
													<a href="">
														<i class="fa fa-file"></i>
														Contract_20_11_2014.docx
													</a>
												</li>
											</ul>
											<div class="text-center m-t-md">
												<a href="#" class="btn btn-xs btn-primary">Add files</a>
												<a href="#" class="btn btn-xs btn-primary">Report contact</a>

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
												<xsl:value-of select="azpu_nombre" />
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

										<th>Nombre</th>
										<th>Tipo</th>
										<th>Bytes </th>
										<th>Sub archivos Recaudo </th>
										<th>Fecha de creación</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//ArchivoRecaudoPorUnificar">

										<tr>
											<td class=" align-center">
												<xsl:value-of select="arpu_nombre" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="arpu_tpar" />
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
												<xsl:value-of select="trar_tpar" />
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

										<th>Nombre</th>
										<th>Tipo</th>
										<th>Bytes </th>
										<th>Registros </th>
										<th>Archivos consolidados</th>
										<th>Fecha de creación</th>
										<th>Descarga</th>

									</tr>
								</thead>
								<tbody>
									<xsl:for-each select="//ArchivoRecaudoUnificado">
										<form id="form_arun_{arun_arun}"
											action="{//contextPath}/unificacion/download.darun"
											method="post">
											<input type="hidden" name="darun"
												value="{arun_arun}" />

										</form>
										<tr>
											<td class=" align-center">
												<xsl:value-of select="arun_nombre" />
											</td>

											<td class=" align-center">
												<xsl:value-of select="arun_tpar" />
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
												<a onclick="osm_enviarFormulario('form_arun_{arun_arun}');">
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
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->


	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

</xsl:stylesheet>