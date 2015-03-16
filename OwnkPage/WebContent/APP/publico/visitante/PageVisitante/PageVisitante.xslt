<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />

	<xsl:include href="../stylesheet/StyleSheetComun.xsl" />
	
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<xsl:template match="/">

		<PAGE>

		<!-- Page Loader -->
		<add type='script' 	src="publico/visitante/PageVisitante/js/snap.svg-min.js" />
		<add type='script' 	src="publico/visitante/PageVisitante/js/classie.js" />
		<add type='script' 	src="publico/visitante/PageVisitante/js/svgLoader.js" />
	
		<add type='css'  	src="publico/visitante/PageVisitante/css/normalize.css" />
		<add type='css'  	src="publico/visitante/PageVisitante/css/demo.css" />
		<add type='css' 	src="publico/visitante/PageVisitante/css/component.css" />
			
		<add type='css' 	src="publico/visitante/PageVisitante/PageVisitante.css" />
		<add type='script' 	src="publico/visitante/PageVisitante/PageVisitante.js" />

		



			<esquemaC />
			<xsl:apply-templates select="//XMLPAGE/*" />

			<!-- ================================== -->
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->
			<div id="pagewrap" class="pagewrap">
				<div class="contenedor show" >
					
					
					
					
					<xsl:call-template name="header" />
					<xsl:call-template name="banner_sign_up" />
					
					
					<!-- FASES -->
					<div class="shadow">
						
						<div class="panel panel-success ">
						  <div class="panel-heading">Grupo de apuestas</div>	
						  <div class="panel-body">
						    <div class="row">
							
							
						    <div class="col-xs-3">
								<div class="wg_span_14px">
									<span class="label label-default">Grupos</span>
								</div>	
								<div class="thumbnail ">
									<div class="wg_img_example"/>
									
									<div class="progress progress-striped active">
									  <div class="progress-bar progress-bar-info"  role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
									    <span class="sr-only">45% Complete</span>
									  </div>
									</div>
									
									<div class="caption">
										<h4>Fase de grupos</h4>
										<p>Apuestas para todos cada uno de los partidos de los grupos (A-H)</p>
										<p>
											<div class="btn-group ">
											   <a class="btn btn-primary pageload-link" href="{//contextPath}/visitante/PageFaseGrupos.pub" role="button">
												
												<span class="glyphicon glyphicon-usd"> </span>Ingresar
												</a>
											    
											   
											  
											</div>
										</p>
									
									</div>		
								</div>
							</div>
							  
							<div class="col-xs-3">
								<div class="wg_span_14px">
									<span class="label label-default">Octavos Final</span>
								</div>	
								<div class="thumbnail ">
									<div class="wg_img_example"/>
									<div class="progress progress-striped active">
									  <div class="progress-bar progress-bar-warning"  role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
									    <span class="sr-only">45% Complete</span>
									  </div>
									</div>
									<div class="caption">
										<h4>Fase de Octavos</h4>
										<p>Apuestas para todos los partidos los ganadores de la fase de grupos</p>
										<p>
											<div class="btn-group ">
											   <a class="btn btn-primary" href="{//contextPath}/visitante/PageFaseGrupos.pub" role="button">
												
												<span class="glyphicon glyphicon-usd"> </span>Ingresar
												</a>
											  
											</div>
										</p>
									
									</div>		
								</div>
							</div>
							  
							  
							<div class="col-xs-3">
								<div class="wg_span_14px">
								<span class="label label-default">Cuartos final</span>
								</div>
								<div class="thumbnail ">
									<div class="wg_img_example"/>
									<div class="progress progress-striped active">
									  <div class="progress-bar progress-bar-danger"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
									    <span class="sr-only">45% Complete</span>
									  </div>
									</div>
									<div class="caption">
										<h4>Fase de Cuartos</h4>
										<p>Apuestas para los 8 mejores equipos del mundial</p>
										<p>
											<div class="btn-group ">
											  <a class="btn btn-primary" href="{//contextPath}/visitante/PageFaseGrupos.pub" role="button">
												
												<span class="glyphicon glyphicon-usd"> </span>Ingresar
												</a>
											  
											</div>
										</p>
									
									</div>		
								</div>
							</div>
							
							<div class="col-xs-3">
						    	<div class="wg_span_14px">
						    	<span class="label label-default">Final</span>
						    	</div>
								<div class="thumbnail ">
									<div class="wg_img_example"/>
									<div class="progress progress-striped active">
									  <div class="progress-bar progress-bar-success"  role="progressbar" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100" style="width: 15%">
									    <span class="sr-only">45% Complete</span>
									  </div>
									</div>
									<div class="caption">
										<h4>Fase de Final</h4>
										<p>Apuestas para todos los partidos de la fase final (Semifinal, Final)</p>
										<p>
											<div class="btn-group ">
											   <a class="btn btn-primary" href="{//contextPath}/visitante/PageFaseGrupos.pub" role="button">
												
												<span class="glyphicon glyphicon-usd"> </span>Ingresar
												</a>
											  
											</div>
										</p>
									
									</div>		
								</div>
								
							</div>
							 
							</div>	<!-- end row -->
						  </div>
						  
						</div>
					</div>	
					
					
				</div>
			</div>
			<div id="loader" class="pageload-overlay" data-opening="M 40 -21.875 C 11.356078 -21.875 -11.875 1.3560784 -11.875 30 C -11.875 58.643922 11.356078 81.875 40 81.875 C 68.643922 81.875 91.875 58.643922 91.875 30 C 91.875 1.3560784 68.643922 -21.875 40 -21.875 Z">
				<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 80 60" preserveAspectRatio="xMidYMid slice">
					<path d="M40,30 c 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 Z"/>
				</svg>
			</div>
			
			


		</PAGE>


	</xsl:template>



	<xsl:template name="template">

	</xsl:template>




	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>


</xsl:stylesheet>