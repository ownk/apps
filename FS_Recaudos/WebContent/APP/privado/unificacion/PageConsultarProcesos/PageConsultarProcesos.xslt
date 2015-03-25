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

					<xsl:when test="count(//ArrayList/ProcesoUnificacionArchivos)>0 and 1=1">
						<div>
							<table class="table" id="">
								<tr class="table-tittle">
									<td class="">Fecha Radicación</td>
									<td class="w10pto">No. Proceso</td>
									<td class="">Usuario</td>

									<td class="">Estado</td>
								</tr>

								<xsl:for-each select="//ArrayList/ProcesoUnificacionArchivos">
									<form id="form_prun_{prun_prun}"
										action="{//contextPath}/unificacion/PageProcesoUnificacionArchivos.do"
										method="post">
										<input type="hidden" name="ProcesoUnificacionArchivos.prun_prun"
											value="{prun_prun}" />

									</form>

									<tr onclick="osm_enviarFormulario('form_prun_{prun_prun}');">
										<xsl:if test="(position() mod 2) > 0 ">
											<xsl:attribute name="class">table-fila-body table-tr-impar  </xsl:attribute>
										</xsl:if>
										<xsl:if test="(position() mod 2) = 0 ">
											<xsl:attribute name="class">table-fila-body table-tr-par</xsl:attribute>
										</xsl:if>

										<td class="w20pto align-center">
											<xsl:value-of select="prun_fcrea" />
										</td>
										<td class="w20pto align-center">
											<xsl:value-of select="prun_prun" />
										</td>
										<td class="w20pto align-center">
											<xsl:value-of select="prun_usua" />
										</td>
										<td class="w20pto align-center">
											<xsl:value-of select="prun_eprun" />
										</td>
									</tr>

								</xsl:for-each>

							</table>


							<div class="col-sm-9 m-b-xs">
								<div data-toggle="buttons" class="btn-group">
									<xsl:for-each select="//PaginatorWeb/pages/PagePaginatorWeb">
										<xsl:choose>
											<xsl:when test="isActive=true">
												<label class="btn btn-sm btn-white active">

													<a onclick="osm_enviarFormulario('paginatorWeb_{id}');">
														<xsl:value-of select="number" />
													</a>
												</label>
											</xsl:when>
											<xsl:otherwise>
												<label class="btn btn-sm btn-white">
													<a onclick="osm_enviarFormulario('paginatorWeb_{id}');">
														<xsl:value-of select="number" />
													</a>
												</label>
											</xsl:otherwise>
										</xsl:choose>

										<form id="paginatorWeb_{id}"
											action="{//contextPath}/unificacion/PageConsultarProcesos.do"
											method="post">
											<input type="hidden" name="pageNumber" value="{number}" />
											<input type="hidden" name="pageSize" value="{//PaginatorWeb/pageSize}" />
										</form>

									</xsl:for-each>
								</div>
							</div>

						</div>
					</xsl:when>

					<xsl:otherwise>
						<mensaje tipo="info">
							<titulo>Información</titulo>
							<contenido>
								No existen procesos en curso

								<div style="text-align:right">
									<a href="{//contextPath}/inicio/PageBienvenida.do">
										Consultar Procesos
									</a>
								</div>
							</contenido>
						</mensaje>

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


