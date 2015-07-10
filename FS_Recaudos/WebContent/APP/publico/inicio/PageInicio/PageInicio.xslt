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

			<add type='script' src="publico/inicio/PageInicio/PageInicio.js" />


			<!-- ================================== -->
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->




			<body class="gray-bg">

				<div class="middle-box text-center loginscreen  animated fadeInDown">
					
					<div style="margin:50px 0px 10px 0px">

						<div class="ownk-logo-login">
							<img alt="image" class=""
								src="{//contextPath}/general/img/logo_fidu.png" />
						</div>

					</div>
					
					<div class="ibox float-e-margins ">
						<div class="ibox-content">
							<div class="row">
								<div class="col-sm-12">
									<div>
										


										<form class="m-t" role="form">
											<div class="form-group">
												<input id="login" name="login" type="text" class="form-control"
													placeholder="Usuario" required="" />
											</div>
											<div class="form-group">
												<input id="pass" name="pass" type="password" class="form-control"
													placeholder="Password" required="" />
											</div>
											<button
												class="btn btn-primary block full-width m-b ownk_btn_shadow"
												onclick="enviarFormulario();">Ingresar</button>

											<!-- <a href="">
												<small>Olvidaste tu password?</small>
											</a>
											<p class="text-muted text-center">
												<small>Aun no tienes ingreso?</small>
											</p>
											<a class="btn btn-sm btn-white btn-block" href="">Crea tu
												usuario</a> -->
										</form>


										<form id="login_form" action="{//contextPath}/inicio/PageLogin.do"
											method="post">
											<input type="hidden" id="documento_usuario" name="documento_usuario" />
											<input type="hidden" id="password" name="password" />

										</form>

										<p class="m-t">
										
											<small>Todos los derechos reservados</small>
											<br/>
											<small>Fundación Social 2015</small>
										</p>
									</div>





								</div>
							</div>
						</div>
					</div>




					<xsl:choose>
						<xsl:when test="//MensajeErrorWeb/error='ERROR_AUTH'">
							<input type="hidden" id="show_popup_error_auth" value="true" />
						</xsl:when>

						<xsl:otherwise>
							<input type="hidden" id="show_popup_error_auth" value="false" />
						</xsl:otherwise>

					</xsl:choose>

					<xsl:call-template name="popup_error_auth"></xsl:call-template>



				</div>


			</body>




		</PAGE>


	</xsl:template>


	<xsl:template name="popup_error_auth">


		<div class="">
			<button type="button" class="ownk_btn_hide" id="btn_popup_error_auth"
				data-toggle="modal" data-target="#myModal">

			</button>
		</div>

		<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
			aria-hidden="false" data-show="true">
			<div class="modal-dialog" data-show="true">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true"></span>
							<span class="sr-only">Close</span>
						</button>
						<i class="fa fa-laptop modal-icon"></i>
						<h4 class="modal-title">Error de autenticación</h4>
						<small class="font-bold">Mensaje importante para tener en cuenta.</small>
					</div>
					<div class="modal-body">
						<p>
							La información de nombre de usuario o contraseña que has
							registrado no es correcta.
							Ingresa nuevamente la información de acceso y vuelve a intentarlo.
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


</xsl:stylesheet>