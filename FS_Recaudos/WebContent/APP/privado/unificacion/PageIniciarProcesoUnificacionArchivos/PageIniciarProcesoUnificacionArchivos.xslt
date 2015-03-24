<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<xsl:template match="/">

		<PAGE>
			
			<xsl:apply-templates select="//XMLPAGE/*" />
			
			<body>
			<xsl:choose>
				<xsl:when test="count(//MensajeErrorWeb)>0">
					<div>
							Lo sentimos, no se pudo Iniciar el proceso de unificacion de archivos. Por favor intentelo nuevamente.
							
							
							<div>
							Error: <xsl:value-of select="//MensajeErrorWeb/mensajeError"></xsl:value-of>
							
							</div>
							
							
							<div style="text-align:right">
							
								<div id="wrapper">
									<a href ="{//contextPath}/unificacion/PageRegistrarArchivosZIPRecaudo.do" >
									Registrar Archivos ZIP Recaudo
									</a>
									
								</div>
							</div>
					</div>
				</xsl:when>
				
				<xsl:otherwise>
					<div>
							
							<div>
							El proceso <b><xsl:value-of select="//ProcesoUnificacionArchivos/prun_prun"></xsl:value-of></b> se ha creado exitosamente!.
							</div>
							
							<form action="{//contextPath}/unificacion/PageProcesoUnificacionArchivos.do" method="post">
								
								<button type="submit" class="btn btn-primary pull-right">Consultar Proceso</button>
								<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
									value="{//ProcesoUnificacionArchivos/prun_prun}" />


							</form>
					</div>	
					
					
				
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