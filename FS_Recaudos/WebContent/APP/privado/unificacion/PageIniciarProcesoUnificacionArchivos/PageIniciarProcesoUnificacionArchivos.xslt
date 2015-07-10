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

			<body class="top-navigation">
				<div id="wrapper">
					<div id="page-wrapper" class="gray-bg">


						<!-- MENU -->
						<xsl:call-template name="nav_bar-top-light" />


						<div class="wrapper wrapper-content animated fadeInUp">


							<!-- TITULO -->
							<div class="row ">
								<div class="col-lg-12 ">
									<div class="ibox float-e-margins ">
										<div class="ibox-content">
											<div class="row">
												<div class="col-sm-12">
													<div class="pull-left m-r-md">
														<i class="fa fa-flag  mid-icon ownk-color-naranja"></i>
													</div>
													<div class="p-xs">

														<h2>
															Inicio de proceso de unificación y conversión
														</h2>
														<ol class="breadcrumb">
															<li>
																<a>Consolidación Archivos</a>
															</li>
															<li class="active">
																<strong>Inicio de Proceso  </strong>
															</li>
														</ol>
														<br />

														<span>
															En esta sección podrás consultar el resultado
															de
															inicio de proceso de unificación y
															conversión de archivos de recaudo BSC
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
								<div class="col-lg-12">

									<xsl:choose>
										<xsl:when test="count(//MensajeErrorWeb[error='1'])>0">



													<div id="vertical-timeline"
														class="vertical-container light-timeline left-orientation">
														<div class="vertical-timeline-block">
															<div class="vertical-timeline-icon navy-bg">
																<i class="fa fa-chain"></i>
															</div>

															<div class="vertical-timeline-content">
																<h2>Resultado</h2>
																<div class="alert alert-danger">
																	<p>
																		Lo sentimos, no se pudo iniciar el proceso de
																		unificación de
																		archivos. Por favor intentelo
																		nuevamente.


																		<div>
																			<xsl:value-of select="//MensajeErrorWeb/mensajeError"></xsl:value-of>

																		</div>


																	</p>
																</div>
																<div style="text-align:right">


																	<a class="btn btn-primary btn-sm ownk_btn_shadow"
																		href="{//contextPath}/unificacion/PageRegistrarArchivosZIPRecaudo.do">
																		Registrar Archivos ZIP Recaudo
																	</a>

																</div>

																<!-- <span class="vertical-date"> Today <br /> <small>Dec 
																	24</small> </span> -->



															</div>
														</div>


											</div>



										</xsl:when>

										<xsl:otherwise>


											
													<div id="vertical-timeline"
														class="vertical-container light-timeline left-orientation">
														<div class="vertical-timeline-block">
															<div class="vertical-timeline-icon navy-bg">
																<i class="fa fa-chain"></i>
															</div>

															<xsl:if test="count(//ProcesoUnificacionArchivos)>0">
																<div class="vertical-timeline-content">
																	<h2>Resultado Proceso Unificación</h2>

																	<div class="alert alert-success">
																		<p>
																			El proceso de unificación
																			<b>
																				<xsl:value-of
																					select="//ProcesoUnificacionArchivos/prun_prun"></xsl:value-of>
																			</b>
																			se ha creado exitosamente!.
																		</p>
																	</div>

																</div>
															</xsl:if>




														</div>

														<xsl:if test="count(//ProcesoConversionArchivos)>0">
															<div class="vertical-timeline-block">
																<div class="vertical-timeline-icon navy-bg">
																	<i class="fa fa-dollar"></i>
																</div>

																<div class="vertical-timeline-content">
																	<h2>Resultado Proceso Conversion</h2>

																	<div class="alert alert-warning">
																		<p>
																			El proceso conversión de archivos SIFI
																			<b>
																				<xsl:value-of
																					select="//ProcesoConversionArchivos/prco_prco"></xsl:value-of>
																			</b>
																			se ha creado y se encuentra en progreso!. Por favor
																			consulte periódicamente
																			para obtener detalle del
																			avance.
																		</p>
																	</div>

																</div>

															</div>
														</xsl:if>

														<xsl:if test="count(//MensajeErrorWeb[error='1'])>0">
															<div class="vertical-timeline-block">
																<div class="vertical-timeline-icon navy-bg">
																	<i class="fa fa-dollar"></i>
																</div>

																<div class="vertical-timeline-content">
																	<h2>Resultado Proceso Conversion</h2>

																	<div class="alert alert-danger">
																		<p>
																			El proceso conversión de archivos SIFI
																			no se ha podido
																			crear correctamente. Se presentado el siguiente
																			error:
																			<xsl:value-of
																				select="//MensajeErrorWeb[error='1']/mensajeError" />
																		</p>
																	</div>

																</div>

															</div>
														</xsl:if>

														<div class="vertical-timeline-block">
															<div class="vertical-timeline-icon navy-bg">
																<i class="fa fa-folder-open"></i>
															</div>

															<div class="vertical-timeline-content">
																<h2>Consulta de proceso</h2>

																<div class="alert alert-info">
																	<p>
																		Para mas detalle puedes realizar la consulta del
																		proceso creado.

																	</p>
																</div>

																<form
																	action="{//contextPath}/unificacion/PageProcesoUnificacionArchivos.do"
																	method="post">

																	<button type="submit"
																		class="btn btn-primary btn-sm pull-right ownk_btn_shadow">Consultar Proceso</button>
																	<input type="hidden"
																		name="ProcesoUnificacionArchivos.prun_prun" value="{//ProcesoUnificacionArchivos/prun_prun}" />


																</form>

															</div>

														</div>




											</div>

										</xsl:otherwise>

									</xsl:choose>


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