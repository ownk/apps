<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- TEMPLATE PRINCIPAL -->
	<xsl:template name="nav_bar-top-light">

		<div class="row white-bg ownk_shadow">
			<nav class="navbar navbar-static-top " role="navigation">
				<div class="navbar-header">
					<button aria-controls="navbar" aria-expanded="false"
						data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed"
						type="button">
						<i class="fa fa-reorder"></i>
					</button>
					<div class="ownk-logo-colmena">
						<img alt="image" class=""
							src="{//contextPath}/general/img/logo_colmena.png" />
					</div>

				</div>
				<div class="navbar-collapse collapse ownk_shadow" id="navbar">
					<ul class="nav navbar-nav">

						<li class="dropdown">


							<a aria-expanded="false" role="button"
								href="" class="dropdown-toggle"
								data-toggle="dropdown">
								<i class="fa fa-home"></i>
								Inicio
								<span class="caret"></span>
							</a>
							<ul role="menu" class="dropdown-menu">
								<li>
									<a href="{//contextPath}/inicio/PageBienvenida.do">Bienvenida</a>
								</li>
								
							</ul>
							
						</li>
						<li class="dropdown">
							<a aria-expanded="false" role="button"
								href="" class="dropdown-toggle"
								data-toggle="dropdown">
								<i class="fa fa-gears"></i>
								Operativo
								<span class="caret"></span>
							</a>
							<ul role="menu" class="dropdown-menu">
								<li>
									<a href="{//contextPath}/unificacion/PageRegistrarArchivosZIPRecaudo.do">Registrar Archivo ZIP</a>
								</li>
								
							</ul>
						</li>
						<li class="dropdown">
							<a aria-expanded="false" role="button"
								href="" class="dropdown-toggle"
								data-toggle="dropdown">
								<i class="fa fa-list"></i>
								Consultas
								<span class="caret"></span>
							</a>
							<ul role="menu" class="dropdown-menu">
								<li>
									<a href="{//contextPath}/unificacion/PageConsultarProcesos.do">Consultar Procesos Creados</a>
								</li>
								
							</ul>
						</li>
						

					</ul>
					<ul class="nav navbar-top-links navbar-right">
						<li>
							<a href="{//contextPath}/inicio/PageInicio.pub">
								<i class="fa fa-sign-out"></i>
								Salir
							</a>
						</li>
					</ul>
				</div>
			</nav>
		</div>


	</xsl:template>



</xsl:stylesheet>