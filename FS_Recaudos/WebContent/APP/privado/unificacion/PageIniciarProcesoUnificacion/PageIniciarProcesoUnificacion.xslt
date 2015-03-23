<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />

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
				src="privado/unificacion/PageIniciarProcesoUnificacion/js/Upload.js" />



			<!-- ================================== -->
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->

			<body>

				<div id="wrapper">


					<div id="page-wrapper" class="gray-bg">
						<div class="row wrapper border-bottom white-bg page-heading">
							<div class="col-lg-10">
								<h2>File upload</h2>
								<ol class="breadcrumb">
									<li>
										<a href="index.html">Home</a>
									</li>
									<li>
										<a>Forms</a>
									</li>
									<li class="active">
										<strong>File upload</strong>
									</li>
								</ol>
							</div>
							<div class="col-lg-2">

							</div>
						</div>
						<div class="wrapper wrapper-content animated fadeIn">
							<div class="row">
								<div class="col-lg-12">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<h5>Carga de Archivos</h5>
											<div class="ibox-tools">
												<a class="collapse-link">
													<i class="fa fa-chevron-up"></i>
												</a>
												<a class="dropdown-toggle" data-toggle="dropdown" href="#">
													<i class="fa fa-wrench"></i>
												</a>
												<ul class="dropdown-menu dropdown-user">
													<li>
														<a href="#">Config option 1</a>
													</li>
													<li>
														<a href="#">Config option 2</a>
													</li>
												</ul>
												<a class="close-link">
													<i class="fa fa-times"></i>
												</a>
											</div>
										</div>
										<div class="ibox-content">
											<form id="my-awesome-dropzone" class="dropzone"
												action="{//contextPath}/unificacion/PageUploadFilesProcesoUnificacion.do"
												method="post" enctype="multipart/form-data">
												<div class="dropzone-previews"></div>
												<button type="submit" class="btn btn-primary pull-right">Cargar Archivos</button>


											</form>
											<div>
																								<a class="btn btn-primary" href="">Limpiar</a>
												<div class="m text-right">
													<small>
														Recurde que el proceso actual presenta un identificador
														unico para su consulta.
														<a href="" target="_blank">
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
											<h5>Proceso Unificacion</h5>

										</div>
										<div class="ibox-content">



											<div class="m text-center">
												<form id="form_unificar"
													action="{//contextPath}/unificacion/PageUnificarArchivos.do"
													method="post">
													<div class="dropzone-previews"></div>
													<button disabled="true" id="btn_unificarArchivos"
														type="submit" class="btn btn-primary pull-right">Cargar Archivos</button>
														
													<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
														value="{//prun_prun}" />
												</form>

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