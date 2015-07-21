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

			<add type='script'	src="privado/compara/PageRegistrarArchivoInternetBSC/js/PageRegistrarArchivoInternetBSC.js" />



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
														<i class="fa fa-file-text  mid-icon ownk-color-naranja"></i>
													</div>
													<div class="p-xs">

														<h2>
															Registro de Archivo Internet BSC
														</h2>
														<ol class="breadcrumb">
															<li>
																<a>Comparacion Archivos</a>
															</li>
															<li class="active">
																<strong>Registrar Archivos Internet </strong>
															</li>
														</ol>
														<br />

														<span>
															En esta sección podrás registrar el archivo
															<strong>EXCEL</strong>
															descargado desde el portal del banco.
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


									
									<xsl:call-template name="popup_intrucciones" />




									<div class="row">
										<div class="col-lg-12">
											<div class="ibox float-e-margins">
												<div class="ibox-title">

													<h5>Cargar archivo EXCEL</h5>
													<div class="ibox-tools">
														<a onclick="showInstructions()">
															<i class="fa fa-info-circle"></i>
														</a>
													</div>

												</div>
												<div class="ibox-content">

													<label class="font-noraml">
														Selecciona el archivo EXCEL con el cual quieres
														comparar el archivo unificado. Puedes arrastrar tus archivo o dar click sobre
														el área para elegirlo. Una vez identifiques el archivo
														da click en botón: 
														<b>Registrar Archivo</b>.
														
													</label>
														
														<br/>
														<br/>
													<div class="alert alert-warning">
					
				
													<label class="font-noraml">
														Recuerda que el archivo debe corresponder al archivo unificado <b><xsl:value-of select="//arun_nombre"></xsl:value-of></b> asociado al tipo de archivo  <b>.<xsl:value-of select="//arun_tpar"></xsl:value-of></b>.
													</label>
													</div>		
													

													<div class="hr-line-dashed"></div>


													<form id="my-awesome-dropzone" class="dropzone"
														action="{//contextPath}/compara/PageUploadFileArchivoBSC.do"
														method="post" enctype="multipart/form-data">
														<div class="dropzone-previews"></div>
														<button disabled="true" type="submit"
															class="btn btn-primary btn-sm pull-right ownk_btn_shadow"
															id="btn_registrarArchivos">
															<i class="fa fa-cloud-upload" />

															Registrar Archivo EXCEL
														</button>
														
														<input type="hidden" name="ComparacionArchivoRecaudo.cpar_cpar"
															value="{//cpar_cpar}" />
															
														<input type="hidden" name="ComparacionArchivoRecaudo.cpar_arun"
															value="{//arun_arun}" />	


													</form>
													<div>

														<div class="m text-right">

															<small>
																Recuerda que la comparación actual presenta un identificador
																único para su consulta.
																<b>
																	Comparación de archivos No.
																	<xsl:value-of select="//cpar_cpar"></xsl:value-of>
																</b>

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
													<h5>Crear Comparación</h5>
													<div class="ibox-tools">
														<a onclick="showInstructions()">
															<i class="fa fa-info-circle"></i>
														</a>

													</div>

												</div>
												<div class="ibox-content">

													<div class="" id="form_iniciarComparacion">
														<form id="form_comparar"
															action="{//contextPath}/compara/PageIniciarComparacionArchivos.do"
															method="post">


															<input type="hidden" name="ComparacionArchivoRecaudo.cpar_cpar"
																value="{//cpar_cpar}" />

															<input type="hidden" name="ComparacionArchivoRecaudo.cpar_arun"
																value="{//arun_arun}" />
																
																
															<label class="font-noraml">
																Por favor coloca una observación para dar inicio a la comparación
																de archivos. 
															</label>

															<div id="div_cpar_observ">
																<div class="hr-line-dashed"></div>
																<div class="form-group ">
																	<label class=" control-label">Observación *</label>
																	<div class="">
																		<input type="text" class="input-sm form-control"
																			id="cpar_observ_ini" name="ComparacionArchivoRecaudo.cpar_observ"
																			maxlength="200" />

																	</div>
																	
																</div>
															</div>

															<div>
																<small>
																	Recuerda que los campos marcados con
																	<b>*</b>
																	son requeridos como obligatorios.

																</small>
															</div>

														</form>

														<div class="hr-line-dashed"></div>
														<div class="btn-group">

															<button disabled="true" id="btn_compararArchivos"
																onclick="validarEnviar('form_comparar')" class=" btn  btn-primary btn-sm ownk_btn_shadow">


																<i class="fa fa-save" />
																Crear
																Comparación
															</button>


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

	<xsl:template name="popup_intrucciones">
		<div class="">
			<button type="button" class="ownk_btn_hide" id="btn_popup_msg_obl"
				data-toggle="modal" data-target="#myModal">

			</button>
		</div>
		<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true"></span>
							<span class="sr-only">Close</span>
						</button>
						<i class="fa fa-laptop modal-icon"></i>
						<h4 class="modal-title">Instrucciones</h4>
						<small class="font-bold">Mensaje importante para tener en cuenta.</small>
					</div>
					<div class="modal-body">
						<p>
							Selecciona el archivo EXCEL con el cual quieres
							comparar el archivo unificado. Puedes
							arrastrar tu archivo o dar click sobre
							el área para eligirlo.
							Una vez identifiques el archivo
							da click en botón
							<b>Registrar Archivo</b>
							.
						</p>
						<BR />
						<p>
							Luego de que hayas elegido el archivo, por favor dilengiciar todos 
							los campos obligatorios
							<b>(*)</b>
							requeridos
							para la creación de la comparación.

						</p>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white" data-dismiss="modal">Entendido</button>

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