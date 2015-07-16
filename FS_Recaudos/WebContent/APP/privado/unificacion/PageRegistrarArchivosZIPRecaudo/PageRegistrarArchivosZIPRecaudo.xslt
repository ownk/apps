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
															Registro de Archivos de Recaudo
														</h2>
														<ol class="breadcrumb">
															<li>
																<a>Consolidación Archivos</a>
															</li>
															<li class="active">
																<strong>Registrar Archivos ZIP </strong>
															</li>
														</ol>
														<br />

														<span>
															En esta sección podrás registrar los archivos
															<strong>.ZIP</strong>
															entregados por el banco y que deben ser
															unificados y
															convertidos a archivos SIFI.
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


									<div class="row">

										<!-- SIFI29 -->
										<div class="col-xs-6">
											<div class="ibox float-e-margins">
												

												<div class="ibox-content">
													<div class="row">

														<div class="col-xs-12">

															<xsl:choose>
																<xsl:when test="count(//totalEncargosSIFI29)>0">
																	<div class="alert alert-success ownk-alert">


																		<div class="feed-element">
																			<div class="pull-left">
																				<i class="fa fa-plug"></i>
																			</div>
																			<div class="media-body ">
																				<small class="pull-right">1 min </small>
																				<strong>Conexion SIFI29 Exitosa</strong>
																				<br />
																				<small class="text-muted">
																					Se han consultado
																					<b>
																						<xsl:value-of select="//totalEncargosSIFI29"></xsl:value-of>
																					</b>
																					encargos
																				</small>
																			</div>
																		</div>

																	</div>

																</xsl:when>
																<xsl:otherwise>
																	<div class="alert alert-warning ownk-alert">
																		
																		<div class="feed-element">
																			<div class="pull-left">
																				<i class="fa fa-plug"></i>
																			</div>
																			<div class="media-body ">
																				<small class="pull-right">1 min </small>
																				<strong>Conexion SIFI29 Fallida</strong>
																				<br />
																				<small class="text-muted">
																					¡Lo sentimos!.
																						<b>NO</b>
																						hemos podido establecer
																						conexión.
																				</small>
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


										<!-- SIFI43 -->
										<div class="col-xs-6">
											<div class="ibox float-e-margins">
					

												<div class="ibox-content">
													<div class="row">

														<div class="col-xs-12">

															<xsl:choose>
																<xsl:when test="count(//totalEncargosSIFI43)>0">
																	<div class="alert alert-success ownk-alert">


																		<div class="feed-element">
																			<div class="pull-left">
																				<i class="fa fa-plug"></i>
																			</div>
																			<div class="media-body ">
																				<small class="pull-right">1 min </small>
																				<strong>Conexion SIFI43 Exitosa</strong>
																				<br />
																				<small class="text-muted">
																					Se han consultado
																					<b>
																						<xsl:value-of select="//totalEncargosSIFI43"></xsl:value-of>
																					</b>
																					encargos
																				</small>
																			</div>
																		</div>

																	</div>

																</xsl:when>
																<xsl:otherwise>
																	<div class="alert alert-warning ownk-alert">
																		
																		<div class="feed-element">
																			<div class="pull-left">
																				<i class="fa fa-plug"></i>
																			</div>
																			<div class="media-body ">
																				<small class="pull-right">1 min </small>
																				<strong>Conexion SIFI43 Fallida</strong>
																				<br />
																				<small class="text-muted">
																					¡Lo sentimos!.
																						<b>NO</b>
																						hemos podido establecer
																						conexión.
																				</small>
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
									</div>

									<xsl:call-template name="popup_intrucciones" />




									<div class="row">
										<div class="col-lg-12">
											<div class="ibox float-e-margins">
												<div class="ibox-title">

													<h5>Cargar archivos ZIP</h5>
													<div class="ibox-tools">
														<a onclick="showInstructions()">
															<i class="fa fa-info-circle"></i>
														</a>
														<a
															href="{//contextPath}/unificacion/PageRegistrarArchivosZIPRecaudo.do">
															<i class="fa fa-undo"></i>
														</a>
													</div>

												</div>
												<div class="ibox-content">

													<label class="font-noraml">
														Selecciona los archivos ZIP que quieres asociar al
														proceso. Puedes arrastrar tus archivos o dar click sobre
														el área para eligirlos. Una vez identifiques los archivos
														da click en botón
														<b>Registrar Archivos</b>
													</label>

													<div class="hr-line-dashed"></div>


													<form id="my-awesome-dropzone" class="dropzone"
														action="{//contextPath}/unificacion/PageUploadFilesProcesoUnificacion.do"
														method="post" enctype="multipart/form-data">
														<div class="dropzone-previews"></div>
														<button disabled="true" type="submit"
															class="btn btn-primary btn-sm pull-right ownk_btn_shadow"
															id="btn_registrarArchivos">
															<i class="fa fa-cloud-upload" />

															Registrar Archivos
														</button>
														<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
															value="{//prun_prun}" />


													</form>
													<div>

														<div class="m text-right">

															<small>
																Recuerda que el proceso actual presenta un identificador
																único para su consulta.
																<b>
																	Proceso de unificación de archivos No.
																	<xsl:value-of select="//prun_prun"></xsl:value-of>
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
													<h5>Crear proceso</h5>
													<div class="ibox-tools">
														<a onclick="showInstructions()">
															<i class="fa fa-info-circle"></i>
														</a>

													</div>

												</div>
												<div class="ibox-content">

													<div class="" id="form_iniciarProceso">
														<form id="form_unificar"
															action="{//contextPath}/unificacion/PageIniciarProcesoUnificacionArchivos.do"
															method="post">


															<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
																value="{//prun_prun}" />

															<label class="font-noraml">
																Por favor selecciona las
																<b>fechas de inicio y fin </b>
																con las cuales quieres crear el proceso de unificación
																de archivos. Recuerda que las fechas se utilizarán para
																generar el archivo consolidado por cada cuenta.
															</label>

															<div class="hr-line-dashed"></div>
															<div class="form-group" id="datepicker_1">
																<label class=" control-label">Fecha de Inicio de Proceso *</label>
																<div class=" input-group date">
																	<span class="input-group-addon">
																		<i class="fa fa-calendar"></i>
																	</span>
																	<input type="text" class="form-control" id="prun_fini"
																		name="ProcesoUnificacionArchivos.prun_fini" value="{//prun_fini}" />
																</div>
															</div>

															<div class="hr-line-dashed"></div>
															<div class="form-group" id="datepicker_2">
																<label class=" control-label">Fecha fin de Proceso *</label>
																<div class=" input-group date">
																	<span class="input-group-addon">
																		<i class="fa fa-calendar"></i>
																	</span>
																	<input type="text" class="form-control" id="prun_ffin"
																		name="ProcesoUnificacionArchivos.prun_ffin" value="{//prun_fini}" />
																</div>
															</div>

															<div class="ownk_hide" id="div_prun_observ">
																<div class="hr-line-dashed"></div>
																<div class="form-group ">
																	<label class=" control-label">Observación creación *</label>
																	<div class="">
																		<input type="text" class="input-sm form-control"
																			id="prun_observ_anul" name="ProcesoUnificacionArchivos.prun_observ"
																			maxlength="200" />

																	</div>
																	<label class="font-noraml">
																		*Existen procesos ya creados para
																		las fecha indicadas. Si
																		deseas crear
																		el proceso es
																		necesario que coloques una observación
																		de creación del
																		nuevo proceso.
																	</label>

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

															<button disabled="true" id="btn_unificarArchivos"
																onclick="validarEnviar('form_unificar')" class=" btn  btn-primary btn-sm ownk_btn_shadow">


																<i class="fa fa-save" />
																Crear
																Proceso
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
							Selecciona los archivos ZIP que quieres asociar al
							proceso. Puedes
							arrastrar tus archivos o dar click sobre
							el area para eligirlos.
							Una vez identifiques los archivos
							da click en botón
							<b>Registrar Archivos</b>
							.
						</p>
						<BR />
						<p>
							Luego de que hayas elegido los archivos, por favor selecciona las
							<b>fechas de inicio y fin </b>
							con las cuales quieres crear el proceso de unificación
							de
							archivos. Recuerda que las fechas se utilizarán para generar el
							archivo consolidado
							por cada cuenta.
							<b> La fecha de fin no puede ser inferior a la fecha
								de inicio.</b>

						</p>

						<p>
							Por último, recuerda dilengiciar todos los campos obligatorios
							<b>(*)</b>
							requeridos
							para la creación del proceso.

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