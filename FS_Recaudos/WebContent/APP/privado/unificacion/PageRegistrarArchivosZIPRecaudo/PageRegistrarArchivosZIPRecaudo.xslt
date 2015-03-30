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

			<!-- <add type='css' src="publico/inicio/PageInicio/css/animate.min.css" 
				/> <add type='css' src="publico/inicio/PageInicio/css/inicio.css" /> <add 
				type='script' src="publico/inicio/PageInicio/js/classie.js" /> <add type='script' 
				src="publico/inicio/PageInicio/js/cbpAnimatedHeader.js" /> <add type='script' 
				src="publico/inicio/PageInicio/js/wow.min.js" /> <add type='script' src="publico/inicio/PageInicio/js/inspinia.js" 
				/> -->

			<add type='script'
				src="privado/unificacion/PageRegistrarArchivosZIPRecaudo/js/PageRegistrarArchivosZIPRecaudo.js" />



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
								<h2>Registro de Archivos de Recaudo </h2>
								<ol class="breadcrumb">
									<li>
										<a>Consolidación Archivos</a>
									</li>
									<li class="active">
										<strong>Registrar Archivos ZIP </strong>
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
													
													<h5>Cargar archivos ZIP</h5>
													<div class="ibox-tools">
														<a class="dropdown-toggle" data-toggle="dropdown" href="#">
															<i class="fa fa-wrench"></i>
														</a>
														<ul class="dropdown-menu dropdown-user">
															<li>
																<a href="">Limpiar formulario</a>
															</li>

														</ul>

													</div>
													<span class="label label-warning pull-right">Revisar Opciones</span>
												</div>
												<div class="ibox-content">
													
													<label class="font-noraml">Selecciona los archivos ZIP que quieres asociar al proceso. Puedes arrastrar tus archivos o dar click sobre el area para eligirlos. Una vez identifiques los archivos da click en botón <b>Registrar Archivos</b> </label>

													<div class="hr-line-dashed"></div>
						
												
													<form id="my-awesome-dropzone" class="dropzone"
														action="{//contextPath}/unificacion/PageUploadFilesProcesoUnificacion.do"
														method="post" enctype="multipart/form-data">
														<div class="dropzone-previews"></div>
														<button type="submit" class="btn btn-primary btn-sm pull-right"
															id="btn_registrarArchivos">Registrar Archivos</button>
														<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
															value="{//prun_prun}" />


													</form>
													<div>

														<div class="m text-right">

															<small>
																Recurde que el proceso actual presenta un identificador
																unico para su consulta.
																<a>
																	Proceso de unificacion de archivos No.
																	<xsl:value-of select="//prun_prun"></xsl:value-of>
																</a>

															</small>
														</div>



													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-12">
											<div class="ibox ">
												<div class="ibox-title">
													<h5>Crear proceso</h5>

												</div>
												<div class="ibox-content">

													<div class="form-group form-horizontal" id="form_iniciarProceso">
														<form id="form_unificar"
															action="{//contextPath}/unificacion/PageIniciarProcesoUnificacionArchivos.do"
															method="post">


															<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
																value="{//prun_prun}" />

															<label class="font-noraml">Por favor selecciona las <b>fechas de inicio y fin </b> con las cuales quieres crear el proceso de unificación de archivos. Recuerda que las fechas se utilizarán para generar el archivo consolidado por cada cuenta.</label>

															<div class="hr-line-dashed"></div>
															<div class="input-daterange input-group" id="datepicker">
																<span class="input-group-addon">Fecha Inicio</span>

																<span class="input-group-addon">
																	<i class="fa fa-calendar"></i>
																</span>
																<input type="text" class="input-sm form-control"
																	name="ProcesoUnificacionArchivos.prun_fini" value="{//prun_fini}" />

																<span class="input-group-addon">Fecha fin</span>
																<span class="input-group-addon">
																	<i class="fa fa-calendar"></i>
																</span>
																<input type="text" class="input-sm form-control"
																	name="ProcesoUnificacionArchivos.prun_ffin" value="{//prun_fini}" />

															</div>




															<div class="hr-line-dashed"></div>
															<div class="form-group">
																<div class="col-sm-1">
																	<button disabled="true" id="btn_unificarArchivos"
																		type="submit" class="btn-outline btn btn-primary btn-sm">Crear Proceso</button>
																</div>

															</div>


														</form>
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

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->


	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

</xsl:stylesheet>