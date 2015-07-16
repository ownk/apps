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
															Inicio de proceso de comparación
														</h2>
														<ol class="breadcrumb">
															<li>
																<a>Comparación de Archivos</a>
															</li>
															<li class="active">
																<strong>Inicio de Proceso  </strong>
															</li>
														</ol>
														<br />

														<span>
															En esta sección podrás consultar el resultado
															de	inicio de proceso de comparación 
															de archivo de recaudo BSC
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
										<xsl:when test="count(//MensajeErrorWeb)>0">



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
																		comparacón de
																		archivos. Por favor intentelo
																		nuevamente.


																		<div>
																			<xsl:value-of select="//MensajeErrorWeb/mensajeError"></xsl:value-of>

																		</div>


																	</p>
																</div>
																



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

															<xsl:if test="count(//ComparacionArchivoRecaudo)>0">
																<div class="vertical-timeline-content">
																	<h2>Resultado Proceso Comparación</h2>

																	<div class="alert alert-success">
																		<p>
																			La comparación de archivo No.
																			<b>
																				<xsl:value-of
																					select="//ComparacionArchivoRecaudo/cpar_cpar"></xsl:value-of>
																			</b>
																			se ha creado exitosamente!.
																		</p>
																	</div>

																</div>
															</xsl:if>




														</div>

														

														<div class="vertical-timeline-block">
															<div class="vertical-timeline-icon navy-bg">
																<i class="fa fa-folder-open"></i>
															</div>

															<div class="vertical-timeline-content">
																<h2>Consulta de comparación</h2>

																<div class="alert alert-info">
																	<p>
																		Para mas detalle puedes realizar la consulta del
																		comparación.

																	</p>
																</div>

																<form
																	action="{//contextPath}/compara/PageComparacionArchivoRecaudo.do"
																	method="post">

																	<button type="submit"
																		class="btn btn-primary btn-sm pull-right ownk_btn_shadow">Consultar Comparación</button>
																	<input type="hidden"
																		name="ComparacionArchivoRecaudo.cpar_cpar" value="{//ComparacionArchivoRecaudo/cpar_cpar}" />


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