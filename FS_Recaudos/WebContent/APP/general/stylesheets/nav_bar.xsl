<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- TEMPLATE PRINCIPAL -->

	<xsl:template name="nav-bar-left">
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span>
								<img alt="image" class=""
									src="{//contextPath}/general/img/logo_fidu.png" />
							</span>
							

						</div>
						<div class="logo-element">
							<img alt="image" class=""
								src="{//contextPath}/general/img/icono_fidu.png" />
						</div>


					</li>
					<li class="active">
						<a href="{//contextPath}/inicio/PageBienvenida.do">
							<i class="fa fa-star"></i>
							<span class="nav-label">Inicio </span>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa fa-files-o"></i>
							<span class="nav-label">Operativo</span>
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li>
								<a
									href="{//contextPath}/unificacion/PageRegistrarArchivosZIPRecaudo.do">Registrar Archivos BSC </a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">
							<i class="fa fa-bar-chart-o"></i>
							<span class="nav-label">Consultas</span>
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li>
								<a href="{//contextPath}/unificacion/PageConsultarProcesos.do">Consultar Procesos</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">
							<i class="fa fa-bar-chart-o"></i>
							<span class="nav-label">Administrativo</span>
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li>
								<a href="">Oficinas</a>
							</li>
						</ul>
					</li>

				</ul>

			</div>
		</nav>

	</xsl:template>

	<xsl:template name="nav-bar-top">
		<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
			<div class="navbar-header">
				<a
					class="navbar-minimalize minimalize-styl-2 btn btn-primary ownk_btn_shadow"
					href="#">
					<i class="fa fa-bars"></i>
				</a>
				<form role="search" class="navbar-form-custom" method="post"
					action="search_results.html">
					<div class="form-group">
						<input type="text" placeholder="Men� principal" class="form-control"
							name="top-search" id="top-search" />
					</div>
				</form>
			</div>
			<ul class="nav navbar-top-links navbar-right">
				<li>
					<span class="m-r-sm text-muted welcome-message">
						Bienvenido:
						<xsl:value-of select="//Persona/pern_nomb" />
					</span>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle count-info" data-toggle="dropdown"
						href="#">
						<i class="fa fa-envelope"></i>
						<span class="label label-warning">16</span>
					</a>

				</li>
				<li class="dropdown">
					<a class="dropdown-toggle count-info" data-toggle="dropdown"
						href="#">
						<i class="fa fa-bell"></i>
						<span class="label label-primary">8</span>
					</a>

				</li>


				<li>
					<a href="{//contextPath}/inicio/PageInicio.pub">
						<i class="fa fa-sign-out"></i>
						Salir
					</a>
				</li>
			</ul>

		</nav>


	</xsl:template>





</xsl:stylesheet>