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

			<add type='script'
				src="privado/compara/PageComparacionArchivoRecaudo/PageComparacionArchivoRecaudo.js"></add>

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
															Comparación Archivo BSC vs Internet
															
														</h2>
														<ol class="breadcrumb">
															<li>
																<a>Consultas</a>
															</li>
															<li class="active">
																<strong>Consultar Comparacion BSC vs Internet</strong>
															</li>
														</ol>
														<br />

														<span>
															En esta sección podrás consultar la
															<strong>comparación</strong>
															entre el archivo unificado del banco vs
															el archivo
															Internet.
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
								<div class="col-xs-12">


									<!-- INFORMACION GENERAL -->
									<div class="row">


										<div class="col-xs-6">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>Información general Comparación </h5>

												</div>

												<div class="ibox-content">
													<div class="row">

														<div class="col-xs-12">
															Informacion básica de la comparación
														</div>

													</div>

													<div class="row">
														<div class="ownk_separador_h" />

													</div>

													<div class="row">
														<div class="col-md-12">



															<div class="col-md-12">

																<div class="row">
																	<div>
																		<dl class="dl-horizontal">
																			<dt>Tipo de archivo</dt>
																			<dd>
																				<span class="label label-default">
																					<xsl:value-of select="//cpar_tpar" />
																				</span>
																			</dd>
																		</dl>
																	</div>
																</div>

																<div class="row">
																	<div>
																		<dl class="dl-horizontal">



																			<dt>Proceso Comparación</dt>
																			<dd>
																				<xsl:value-of select="//cpar_cpar" />
																			</dd>


																			<dt>Fecha Inicio Comparación</dt>
																			<dd>

																				<xsl:value-of select="//cpar_fini_string" />

																			</dd>

																			<dt>Fecha Fin Comparación</dt>
																			<dd>

																				<xsl:value-of select="//cpar_fini_string" />

																			</dd>




																		</dl>
																	</div>
																</div>

																<div class="row">
																	<div>
																		<dl class="dl-horizontal">


																			<dt>Fecha Creación</dt>
																			<dd>
																				<xsl:value-of select="//cpar_fcrea" />
																			</dd>


																			<dt>Creado por</dt>
																			<dd>
																				<xsl:value-of select="//cpar_usua" />
																			</dd>



																			<dt>Observación</dt>
																			<dd>
																				<xsl:value-of select="//cpar_observ" />
																			</dd>




																		</dl>
																	</div>
																</div>

																<div class="row">

																	<dl class="dl-horizontal">
																		<dt>Estado comparación:</dt>
																		<dd>

																			<xsl:value-of select="//cpar_ecpar" />
																		</dd>



																	</dl>

																</div>



															</div>




														</div>
													</div>


												</div>


											</div>
										</div>


										<!-- ARCHIVOS COMPARADOS -->

										<div class="col-xs-6">
											<div class="row">
												<div class="col-xs-12">
													<div class="ibox float-e-margins">
														<div class="ibox-title">
															<h5>Archivos Comparados</h5>

														</div>

														<div class="ibox-content">

															<div class="row">

																<div class="col-xs-12">
																	Validación de cuenta bancaria entre archivos
																</div>

															</div>

															<div class="row">
																<div class="col-md-12">
																	<div style="font-size:11px" class="">
																		<div class="table-responsive">
																			<table class="table table-striped">
																				<thead>
																					<tr>

																						<th class=" ownk_align_center">Cuenta Internet</th>
																						<th class=" ownk_align_center">Cuenta Plano</th>
																						<th class=" ownk_align_center">Validación Cuenta</th>

																					</tr>
																				</thead>
																				<tbody>
																					<xsl:for-each select="//ComparacionArchivoRecaudo">
																						<tr>


																							<td class="ownk_align_center ">
																								***
																								<xsl:value-of select="cpar_ibsc_cta" />
																							</td>

																							<td class=" ownk_align_center">
																								***
																								<xsl:value-of select="cpar_arun_cta" />
																							</td>

																							<td class=" ownk_align_center">
																								<xsl:choose>
																									<xsl:when test="cpar_arun_cta = cpar_ibsc_cta">
																										<span class="ownk_text_verde">
																											<b>EXITOSA</b>
																										</span>
																									</xsl:when>

																									<xsl:otherwise>
																										<span class="ownk_text_rojo">
																											<b>ERRONEA</b>
																										</span>
																									</xsl:otherwise>

																								</xsl:choose>


																							</td>




																						</tr>

																					</xsl:for-each>
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
															
															
															
															<div class="hr-line-dashed"></div>
															
														

															<div class="row">

																<div class="col-xs-12">
																	Información de archivos
																</div>

															</div>

															<div class="row">
																<div class="col-md-12">
																	<div style="font-size:11px " class="">
																		<div class="table-responsive">
																			<table class="table table-striped">
																				<thead>
																					<tr>
																						<th class="ownk_align_center">Fuente </th>
																						<th class="ownk_align_center">Nombre </th>
																						<th class="ownk_align_center">Extensión</th>
																						<th class="ownk_align_center">Bytes</th>



																					</tr>
																				</thead>
																				<tbody>
																					<xsl:for-each select="//ArchivoInternetBSC">

																						<tr>




																							<td class=" ownk_align_center">
																								INTERNET
																							</td>

																							<td class=" ownk_align_center">
																								<xsl:value-of select="ibsc_nombre" />

																							</td>


																							<td class=" ownk_align_center">
																								<xsl:value-of select="ibsc_extension" />
																							</td>

																							<td class=" ownk_align_center">
																								<xsl:value-of select="ibsc_bytes" />
																							</td>




																						</tr>


																					</xsl:for-each>

																					<xsl:for-each select="//ArchivoRecaudoUnificado">

																						<tr>

																							<td class=" ownk_align_center">
																								PLANO
																							</td>

																							<td class=" ownk_align_center">
																								<xsl:value-of select="arun_nombre" />

																							</td>


																							<td class=" ownk_align_center">
																								<xsl:value-of select="arun_extension" />
																							</td>

																							<td class=" ownk_align_center">
																								<xsl:value-of select="arun_bytes" />
																							</td>




																						</tr>


																					</xsl:for-each>
																				</tbody>
																			</table>
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

									<!-- RESUMEN -->
									<div class="row">
										<div class="col-xs-12">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>Resumen</h5>



												</div>
												<div class="ibox-content">
													<div class="row">

														<div class="col-xs-12">
															Informacion resumida de la comparación
															realizada
														</div>

													</div>

													<div class="row">
														<div class="ownk_separador_h" />

													</div>

													<div class="row">
														<div class="col-md-12">


															<div class="row">
																<div class="col-md-12">
																	<div style="font-size:11px " class="alert alert-success">
																		<div class="table-responsive">
																			<table class="table table-striped">
																				<thead>
																					<tr>
																						<th class="ownk_align_center">Descripción </th>
																						<th class="ownk_align_center">Datos</th>
																						<th class="ownk_align_center">Internet</th>
																						<th class="ownk_align_center">Plano</th>
																						<th class="ownk_align_center">Diferencia</th>


																					</tr>
																				</thead>
																				<tbody>
																					<xsl:for-each select="//ResumenCompara/Detalle">

																						<tr>

																							<xsl:if test="(position() mod 2) > 0">
																								<xsl:attribute name="class">ownk_tr_bg_gray</xsl:attribute>
																							</xsl:if>
																							<xsl:if test="(position() mod 2) = 0 ">
																								<xsl:attribute name="class">ownk_tr_bg_verde</xsl:attribute>
																							</xsl:if>



																							<td class=" ownk_align_center">
																								<xsl:value-of select="FuenteRecaudo" />
																							</td>

																							<td class=" ownk_align_center">
																								Valor_Tx

																							</td>


																							<td class=" ownk_align_center">
																								<xsl:value-of select="ValorArchivoInternet" />
																							</td>

																							<td class=" ownk_align_center">
																								<xsl:value-of select="ValorArchivoPlano" />
																							</td>

																							<td class=" ownk_align_center">



																								<xsl:choose>
																									<xsl:when test="ValorDiferencia = '$0,00'">
																										<xsl:value-of select="ValorDiferencia" />

																									</xsl:when>

																									<xsl:otherwise>
																										<span class="ownk_text_rojo">
																											<xsl:value-of select="ValorDiferencia" />
																										</span>
																									</xsl:otherwise>

																								</xsl:choose>
																							</td>


																						</tr>

																						<tr>

																							<xsl:if test="(position() mod 2) > 0">
																								<xsl:attribute name="class">ownk_tr_bg_gray</xsl:attribute>
																							</xsl:if>
																							<xsl:if test="(position() mod 2) = 0 ">
																								<xsl:attribute name="class">ownk_tr_bg_verde</xsl:attribute>
																							</xsl:if>



																							<td class=" ownk_align_center">

																							</td>

																							<td class=" ownk_align_center">
																								No._Tx
																							</td>


																							<td class=" ownk_align_center">
																								<xsl:value-of select="RegistrosArchivoInternet" />
																							</td>

																							<td class=" ownk_align_center">
																								<xsl:value-of select="RegistrosArchivoPlano" />
																							</td>

																							<td class=" ownk_align_center">
																								<xsl:choose>
																									<xsl:when test="RegistrosDiferencia = 0">
																										<xsl:value-of select="RegistrosDiferencia" />

																									</xsl:when>

																									<xsl:otherwise>
																										<span class="ownk_text_rojo">
																											<xsl:value-of select="RegistrosDiferencia" />
																										</span>
																									</xsl:otherwise>

																								</xsl:choose>



																							</td>


																						</tr>

																					</xsl:for-each>
																				</tbody>
																			</table>
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

									<!-- DETALLE COMPARACION -->
									<div class="row">
										<div class="col-xs-12">
											<div class="ibox float-e-margins">


												<div class="ibox-title">
													<h5>Detalle comparación</h5>



												</div>
												<div class="ibox-content">
													<div class="row">

														<div class="col-xs-12">
															Informacion detallada de la comparación
															realizada
														</div>

													</div>

													<div class="row">
														<div class="ownk_separador_h" />

													</div>
													<div class="row">
														<div class="col-md-12">

															<div class="row">
																<div class="col-md-12">
																	<xsl:call-template name="detalleComparacion" />
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



					</div>
				</div>
			</body>

		</PAGE>

	</xsl:template>



	<xsl:template name="detalleComparacion">
		<xsl:choose>

			<xsl:when test="count(//DetalleComparacionArchivoRecaudo)>0 and 1=1">

				<div class="row">
					<div class="col-md-12">

						<div style="font-size:11px">
							<div class="table-re	sponsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th class="ownk_align_center">No. </th>
											<th class="ownk_align_center">Cons. </th>
											<th class="ownk_align_center">Fuente</th>
											<th class="ownk_align_center">Fecha</th>
											<th class="ownk_align_center">Forma de Recaudo</th>
											<th class="ownk_align_center">Oficina</th>
											<th class="ownk_align_center">Valor</th>
											<th class="ownk_align_center">Referencia</th>
											<th class="ownk_align_center">Info. Adicional</th>


										</tr>
									</thead>
									<tbody>
										<xsl:for-each select="//DetalleComparacionArchivoRecaudo">

											<tr>

												<xsl:if test="dcpar_fuente = 'PLANO' ">
													<xsl:attribute name="class">ownk_tr_bg_gray</xsl:attribute>
												</xsl:if>
												<xsl:if test="dcpar_fuente = 'INTERNET' ">
													<xsl:attribute name="class">ownk_tr_bg_white</xsl:attribute>
												</xsl:if>

												<td class=" ownk_align_center">
													<xsl:value-of select="position()" />
												</td>


												<td class=" ownk_align_center">
													<xsl:value-of select="dcpar_id_reg_orig" />
												</td>

												<td class=" ownk_align_center">
													<xsl:value-of select="dcpar_fuente" />
												</td>

												<td class=" ownk_align_center">
													<xsl:value-of select="dcpar_freca_norm_string" />
												</td>
												<td class=" ownk_align_center">
													<xsl:variable name="htpr" select="dcpar_treca_norm" />
													<xsl:choose>
														<xsl:when
															test="count(//HomologacionTipoRecaudoComparador[htpr_freca_norm = $htpr])>0">
															<span
																class="label label-{//HomologacionTipoRecaudoComparador[htpr_freca_norm = $htpr]/htpr_color}">
																<xsl:value-of select="dcpar_treca_norm" />
															</span>
														</xsl:when>

														<xsl:otherwise>
															<span class="label label-default">

																<xsl:value-of select="dcpar_treca_norm" />
															</span>
														</xsl:otherwise>

													</xsl:choose>


												</td>

												<td class=" ownk_align_center">
													<xsl:value-of select="dcpar_ofic_norm" />
												</td>

												<xsl:choose>
													<xsl:when test="contains(dcpar_valor,'-')">
														<td class=" ownk_align_center">
															<span class="label label-warning">
																<xsl:value-of select="dcpar_valor_format" />
															</span>
														</td>
													</xsl:when>
													<xsl:otherwise>
														<td class=" ownk_align_center">
															<xsl:value-of select="dcpar_valor_format" />
														</td>
													</xsl:otherwise>

												</xsl:choose>



												<td class=" ownk_align_center">
													<xsl:value-of select="dcpar_referencia" />
												</td>

												<td class=" ownk_align_center">
													<xsl:value-of select="dcpar_observ" />
												</td>



											</tr>

										</xsl:for-each>
									</tbody>
								</table>
							</div>
						</div>

					</div>

				</div>


			</xsl:when>



			<xsl:otherwise>



			</xsl:otherwise>

		</xsl:choose>


	</xsl:template>




	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->


	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

</xsl:stylesheet>