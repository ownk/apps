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
														<i class="fa fa-gears  mid-icon ownk-color-naranja"></i>
													</div>
													<div class="p-xs">

														<h2>Consolidación Archivos</h2>
														<ol class="breadcrumb">
															<li>
																<a>Consolidación Archivos</a>
															</li>
															<li class="active">
																<strong>Iniciar Proceso Unificación </strong>
															</li>
														</ol>
														<br />



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
														<i class="fa fa-briefcase"></i>
													</div>

													<div class="vertical-timeline-content">
														<h2>Resultado</h2>
														<div class="alert alert-danger">
															<p>
																Lo sentimos, no se pudo generar la unificación de
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

														<!-- <span class="vertical-date"> Today <br /> <small>Dec 24</small> 
															</span> -->



													</div>
												</div>


											</div>




										</xsl:when>

										<xsl:otherwise>



											<div id="vertical-timeline"
												class="vertical-container light-timeline left-orientation">
												<div class="vertical-timeline-block">
													<div class="vertical-timeline-icon navy-bg">
														<i class="fa fa-briefcase"></i>
													</div>

													<div class="vertical-timeline-content">
														<h2>Resultado</h2>

														<div class="alert alert-success">
															<p>

																Se ha realizado la unificación de archivos para el
																proceso
																<b>
																	<xsl:value-of select="//ProcesoUnificacionArchivos/prun_prun"></xsl:value-of>
																</b>
																de forma exitosa!.



															</p>
														</div>
														<form
															action="{//contextPath}/unificacion/PageProcesoUnificacionArchivos.do"
															method="post">

															<button type="submit"
																class="btn btn-primary btn-sm pull-right ownk_btn_shadow">Consultar Proceso</button>
															<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
																value="{//ProcesoUnificacionArchivos/prun_prun}" />


														</form>
														<!-- <span class="vertical-date"> Today <br /> <small>Dec 24</small> 
															</span> -->



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