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

			<add type='script' src="privado/inicio/PageBienvenida/PageBienvenida.js"></add>

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
						<div class="wrapper wrapper-content">
							<div class="row">
								<div class="col-md-3">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<span class="label label-success pull-right">Diario</span>
											<h5>Procesos</h5>
										</div>
										<div class="ibox-content">
											<h1 class="no-margins">1,2</h1>
											<div class="stat-percent font-bold text-success">
												100%
												<i class="fa fa-bolt"></i>
											</div>
											<small>Procesados</small>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<span class="label label-info pull-right">Semanal</span>
											<h5>Archivos</h5>
										</div>
										<div class="ibox-content">
											<h1 class="no-margins">73</h1>
											<div class="stat-percent font-bold text-info">
												100%
												<i class="fa fa-level-up"></i>
											</div>
											<small>Procesados </small>
										</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<span class="label label-primary pull-right">Mensual</span>
											<h5>Generados</h5>
										</div>
										<div class="ibox-content">

											<div class="row">
												<div class="col-md-12">
													<h1 class="no-margins">160</h1>
													<div class="font-bold text-navy">
														100%
														<i class="fa fa-level-up"></i>
														<small>Procesados</small>
													</div>
												</div>

											</div>


										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<h5></h5>
											<div class="ibox-tools">
												<span class="label label-primary">Actualizado 2015</span>
											</div>
										</div>
										<div class="ibox-content no-padding">
											<div class="flot-chart m-t-lg" style="height: 55px;">
												<div class="flot-chart-content" id="flot-chart1"></div>
											</div>
										</div>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="ibox float-e-margins">
										<div class="ibox-content">
											<div>
												<span class="pull-right text-right">
													<small>
														Promedio de archivos cargados mes :
														<strong>Abril</strong>
													</small>
													<br />
													Archivos:
													<xsl:value-of select="count(//totalSize)" />
												</span>
												<h3 class="font-bold no-margins">Cantidad de archivos cargados por mes
												</h3>
												<small>Archivos de recaudo</small>
											</div>

											<div class="m-t-sm">

												<div class="row">
													<div class="col-md-8">
														<div>
															<canvas id="lineChart" height="114"></canvas>
														</div>
													</div>
													<div class="col-md-4">
														<ul class="stat-list m-t-lg">
															<li>
																<h2 class="no-margins">30</h2>
																<small>Promedio de
																	procesos por periodo</small>
																<div class="progress progress-mini">
																	<div class="progress-bar" style="width: 48%;"></div>
																</div>
															</li>
															<li>
																<h2 class="no-margins ">15</h2>
																<small>Archivos Por Proceso</small>
																<div class="progress progress-mini">
																	<div class="progress-bar" style="width: 60%;"></div>
																</div>
															</li>
														</ul>
													</div>
												</div>

											</div>

											<div class="m-t-md">
												<small class="pull-right">
													<i class="fa fa-clock-o">
													</i>
													Update
													<xsl:value-of select="//dateApp"></xsl:value-of>
												</small>
												<small>
													<strong>Analisis de Archivos</strong>
													La cantidad de archivos varia con respecto al tiempo, en el
													ultimo
													mes se cargaron 60.
												</small>
											</div>

										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<span class="label label-warning pull-right">La información ha cambiado</span>
											<h5>User activity</h5>
										</div>
										<div class="ibox-content">
											<div class="row">
												<div class="col-xs-4">
													<small class="stats-label">Paginas / Visitadas</small>
													<h4>236 321.80</h4>
												</div>

												<div class="col-xs-4">
													<small class="stats-label">% Nuevas Visitas</small>
													<h4>46.11%</h4>
												</div>
												<div class="col-xs-4">
													<small class="stats-label">Última Semana</small>
													<h4>56</h4>
												</div>
											</div>
										</div>
										<div class="ibox-content">
											<div class="row">
												<div class="col-xs-4">
													<small class="stats-label">Paginas / Visitadas</small>
													<h4>236 321.80</h4>
												</div>

												<div class="col-xs-4">
													<small class="stats-label">% Nuevas Visitas</small>
													<h4>46.11%</h4>
												</div>
												<div class="col-xs-4">
													<small class="stats-label">Última Semana</small>
													<h4>56</h4>
												</div>
											</div>
										</div>
										<div class="ibox-content">
											<div class="row">
												<div class="col-xs-4">
													<small class="stats-label">Paginas / Visitadas</small>
													<h4>236 321.80</h4>
												</div>

												<div class="col-xs-4">
													<small class="stats-label">% Nuevas Visitas</small>
													<h4>46.11%</h4>
												</div>
												<div class="col-xs-4">
													<small class="stats-label">Última Semana</small>
													<h4>56</h4>
												</div>
											</div>
										</div>

									</div>
								</div>

							</div>
							<div class="row">

								<div class="col-lg-12">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<h5>Ultimos procesos  </h5>
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
														ultimos 10 procesos creados. Los procesos listados estan
														organizados desde el más reciente al más antiguo. Si
														deseas consultar alguno en particular debes hacer click
														sobre el registro.</label>

													<div class="hr-line-dashed"></div>
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
																			<th>Total ZIP </th>
																			<th>Fecha Inicio</th>
																			<th>Fecha FIn</th>
																			<th>Estado</th>
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
																				
																				<td class=" align-center">
																					<xsl:value-of select="prun_eprun" />
																				</td>

																				<td>

																					<a class="btn btn-white btn-circle"
																						onclick="osm_enviarFormulario('form_prun_{prun_prun}');">
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
																	role="status" aria-live="polite">
																	Mostrando
																	<xsl:value-of select="//PaginatorWeb/sizeIni" />
																	de
																	<xsl:value-of select="//PaginatorWeb/sizeFin" />
																	de
																	<xsl:value-of select="//PaginatorWeb/totalSize" />
																	registros
																</div>
															</div>
														</div>
														<div class="col-sm-9 text-right">
															<div class="btn-group">
																<div style="">
																	<a class="btn btn-primary btn-sm"
																		href="{//contextPath}/unificacion/PageConsultarProcesos.do">
																		Ver todos
																	</a>
																</div>


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