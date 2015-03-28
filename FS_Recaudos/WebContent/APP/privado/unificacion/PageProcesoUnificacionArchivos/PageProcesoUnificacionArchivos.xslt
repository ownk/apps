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

			



			<!-- ================================== -->
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->

			<body>
			<xsl:choose>
				<xsl:when test="count(//ProcesoUnificacionArchivos)>0">
				<div id="wrapper">
					<div id="page-wrapper" class="gray-bg">
						<div class="row wrapper border-bottom white-bg page-heading">
							<div class="col-lg-10">
								<h2>Proceso Unificacion <xsl:value-of select="//ProcesoUnificacionArchivos/prun_prun"></xsl:value-of></h2>
								<ol class="breadcrumb">
									<li>
										<a href="">Home</a>
									</li>
									<li>
										<a>Forms</a>
									</li>
									<li class="active">
										<strong>Consulta</strong>
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
										<form action="{//contextPath}/unificacion/PageUnificarArchivosPorProceso.do" method="post">
								
											<button type="submit" class="btn btn-primary pull-right">Unificar Archivos</button>
											<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
												value="{//ProcesoUnificacionArchivos/prun_prun}" />
			
			
										</form>
									
									</div>
								</div>	
										
							</div>
					

						</div>


					</div>
				</div>
				</xsl:when>
				
				<xsl:when test="count(//MensajeErrorWeb)>0">
					<div>
							Lo sentimos, no se puede consultar la informacion del proceso de unificacion de archivos. Por favor intentelo nuevamente.
							
							
							<div>
							Error: <xsl:value-of select="//MensajeErrorWeb/mensajeError"></xsl:value-of>
							
							</div>
							
							
							<div style="text-align:right">
							
								<div id="wrapper">
									<a href ="{//contextPath}/inicio/PageBienvenida.do" >
									Inicio
									</a>
									
								</div>
							</div>
					</div>
				</xsl:when>
				<xsl:otherwise>
				
				</xsl:otherwise>
			</xsl:choose>	
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