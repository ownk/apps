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

			<add type='script'
				src="privado/unificacion/PageConsultarProcesos/PageConsultarProcesos.js"></add>

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
								<h2>Consulta de Procesos de Unificación de Archivos </h2>
								<ol class="breadcrumb">
									<li>
										<a>Consultas</a>
									</li>
									<li class="active">
										<strong>Consultar Procesos </strong>
									</li>
								</ol>
							</div>


						</div>


						<!-- CONTENIDO -->

						<div class="row">
							<div class="col-lg-12">
								<div class="wrapper wrapper-content animated fadeInUp">


									<div class="row">

										<div class="col-lg-12">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>Procesos  </h5>
													<div class="ibox-tools">
														<a class="collapse-link">
															<i class="fa fa-chevron-up"></i>
														</a>
														<a class="dropdown-toggle" data-toggle="dropdown" href="#">
															<i class="fa fa-wrench"></i>
														</a>
														<ul class="dropdown-menu dropdown-user">
															<li>
																<a href="">Recargar</a>
															</li>

														</ul>

													</div>



												</div>
												<div class="ibox-content">

													<div class="row">
														<div class="col-sm-12 m-b-xs">
															<label class="font-noraml">En esta sección podrás consultar los
																procesos ya creados. Los procesos listados estan
																organizados desde el más reciente al más antiguo. Se
																deseas consultar alguno en particular debes hacer click
																sobre el registro deseado.</label>

															<div class="hr-line-dashed"></div>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-9 m-b-xs">
															<div data-toggle="buttons" class="btn-group">
																<label class="btn btn-sm btn-white">
																	<input type="radio" id="option1" name="options" />
																	Diario
																</label>
																<label class="btn btn-sm btn-white active">
																	<input type="radio" id="option2" name="options" />
																	Semanal
																</label>
																<label class="btn btn-sm btn-white">
																	<input type="radio" id="option3" name="options" />
																	Mensual
																</label>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="input-group">
																<input type="text" placeholder="Search"
																	class="input-sm form-control" />
																<span class="input-group-btn">
																	<button type="button" class="btn btn-sm btn-primary"> Ir!</button>
																</span>
															</div>
														</div>
													</div>

													<xsl:choose>

														<xsl:when
															test="count(//ArrayList/ProcesoUnificacionArchivos)>0 and 1=1">
															
															<div class="row">
															<div class="col-sm-12 m-b-xs">
															<div class="table-responsive">
																<table class="table table-striped">
																	<thead>
																		<tr>

																			<th>No.</th>
																			<th>Fecha de radicación </th>
																			<th>Usuario </th>
																			<th>ZIP </th>
																			<th>Fecha Inicio</th>
																			<th>Fecha FIn</th>
																			<th>Action</th>
																		</tr>
																	</thead>
																	<tbody>
																		<xsl:for-each select="//ArrayList/ProcesoUnificacionArchivos">
																			<form id="form_prun_{prun_prun}"
																				action="{//contextPath}/unificacion/PageProcesoUnificacionArchivos.do"
																				method="post">
																				<input type="hidden"
																					name="ProcesoUnificacionArchivos.prun_prun" value="{prun_prun}" />

																			</form>

																			<tr onclick="osm_enviarFormulario('form_prun_{prun_prun}');">
																				<td class=" align-center">
																					<xsl:value-of select="prun_prun" />
																				</td>

																				<td class=" align-center">
																					<xsl:value-of select="prun_fcrea" />
																				</td>

																				<td class=" align-center">
																					<xsl:value-of select="prun_usua" />
																				</td>
																				<td class=" align-center">
																					<xsl:value-of select="prun_archivos" />
																				</td>
																				<td class=" align-center">
																					<xsl:value-of select="prun_fini" />
																				</td>
																				<td class=" align-center">
																					<xsl:value-of select="prun_ffin" />
																				</td>

																				<td>

																					<a onclick="osm_enviarFormulario('form_prun_{prun_prun}');">
																						<i class="fa fa-check text-navy"></i>
																					</a>

																				</td>
																			</tr>

																		</xsl:for-each>
																	</tbody>
																</table>
																</div>
																</div>
																</div>
																
															
																<div class="row">
																	<div class="col-sm-3">
																		<div class="input-group">
																		<div class="dataTables_info" id="editable_info"
																			role="status" aria-live="polite">Mostrando <xsl:value-of select="//PaginatorWeb/sizeIni"/> de <xsl:value-of select="//PaginatorWeb/sizeFin"/> de <xsl:value-of select="//PaginatorWeb/totalSize"/> registros</div>
																		</div>
																	</div>
																	<div class="col-sm-9 text-right">
																		<div class="btn-group">
																			<button type="button" class="btn btn-white">
																				<i class="fa fa-chevron-left"></i>
																			</button>

																			<xsl:for-each select="//pages/PagePaginatorWeb">
																				<xsl:choose>
																					<xsl:when test="isActive='true'">
																						<button class="btn btn-white active"
																							onclick="osm_enviarFormulario('paginatorWeb_{id}');">
																							<xsl:value-of select="id" />
																						</button>

																					</xsl:when>

																					<xsl:otherwise>
																						<button class="btn btn-white"
																							onclick="osm_enviarFormulario('paginatorWeb_{id}');">
																							<xsl:value-of select="id" />
																						</button>
																					</xsl:otherwise>
																				</xsl:choose>



																			</xsl:for-each>

																			<button type="button" class="btn btn-white">
																				<i class="fa fa-chevron-right"></i>
																			</button>


																		</div>

																	</div>
																</div>

																<xsl:for-each select="//pages/PagePaginatorWeb">

																	<form id="paginatorWeb_{id}"
																		action="{//contextPath}/unificacion/PageConsultarProcesos.do"
																		method="post">
																		<input type="hidden" name="pageNumber" value="{number}" />
																		<input type="hidden" name="pageSize"
																			value="{//PaginatorWeb/pageSize}" />
																	</form>
																</xsl:for-each>




														</xsl:when>

														<xsl:otherwise>


															<div class="alert alert-warning">
																No existen procesos en curso aun
																registrados.
															</div>
															<div style="text-align:right">
																<a class="btn btn-primary btn-sm"
																	href="{//contextPath}/unificacion/PageRegistrarArchivosZIPRecaudo.do">
																	Registrar nuevo proceso
																</a>
															</div>


														</xsl:otherwise>

													</xsl:choose>

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

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->


	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

</xsl:stylesheet>


