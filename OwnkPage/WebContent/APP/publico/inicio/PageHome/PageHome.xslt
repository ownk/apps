<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />


	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<xsl:template match="/">

		<PAGE>

			
			
			<!-- Menu Circular -->
			<add type='css' src="publico/inicio/PageHome/css/normalize.css" />
			<add type='css' src="publico/inicio/PageHome/css/demo.css" />
			<add type='css' src="publico/inicio/PageHome/css/component2.css" />
			
			<add type='script' src="publico/inicio/PageHome/js/modernizr-2.6.2.min.js"/>
			<add type='script' src="publico/inicio/PageHome/js/polyfills.js"/>

			<add type='script' src="publico/inicio/PageHome/PageHome.js" />
			<add type='css' src="publico/inicio/PageHome/PageHome.css" />
			



			<esquemaC />
			<xsl:apply-templates select="//XMLPAGE/*" />

			<!-- ================================== -->
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->

			
				
				<!-- APP -->
				<div class="page-header">
				  <h3>
				  
						<span class="label label-warning">O</span>
						<span class="label label-warning">W</span>
						<span class="label label-warning">N</span>
						<span class="label label-warning">K</span>
						
					</h3>
				</div>
						
				<div>
					<div class="ownk_logo" style="float:left; width:60%; " >
						<img  src="{//contextPath}/publico/inicio/PageHome/images/ownk_vector.svg"
											width="100%" height="300px" alt="Apuesta Mundialista" />
						
					</div>
					
					<!-- CIRCULAR NAVIGATOR -->
					<div class="menu" style="float:left; width:40%; ">
						<div class="component" >
					
						
							<button class="cn-button shadow" id="cn-button">Ownk</button>
							<div class="cn-wrapper" id="cn-wrapper">
								<ul>
									<li>
										<a href="{//contextPath}/visitante/PageVisitante.pub">
											<span class="glyphicon glyphicon-user"/>
										</a>
									</li>
									<li>
										<a href="{//contextPath}/visitante/PageVisitante.pub">
											<span class="glyphicon glyphicon-phone-alt"/>
										</a>
									</li>
									<li>
										<a href="{//contextPath}/visitante/PageVisitante.pub">
											<span class="glyphicon glyphicon-flash"/>
										</a>
									</li>
									<li>
										<a href="{//contextPath}/visitante/PageVisitante.pub">
											<span class="glyphicon glyphicon-calendar"/>
										</a>
									</li>
									<li>
										<a href="{//contextPath}/visitante/PageVisitante.pub">
											<span class="glyphicon glyphicon-fullscreen"/>
										</a>
									</li>
									<li>
										<a href="{//contextPath}/visitante/PageVisitante.pub">
											<span class="glyphicon glyphicon-info-sign"/>
										</a>
									</li>
									<li>
										<a href="{//contextPath}/visitante/PageVisitante.pub">
											<span class="glyphicon glyphicon-picture"/>
										</a>
									</li>
								
									
								 </ul>
							</div>
							<!-- End of Nav Structure -->
						</div>
					</div>
						
				</div>
						
				<xsl:call-template name="social-media"/>
					
				
				<!-- SOCIAL NETWORKS -->
				

				
			
					
			


		</PAGE>


	</xsl:template>

	<xsl:template name="banner">
	
		<!-- JUMBOTRON -->
				<div class="row">
					<div class="col-xs-12">
						<div class="wg_back_futbolista ">
							
						<div class="jumbotron">
							<div class="seccion">
								<div>
									<img
										src="{//contextPath}/css/public/inicio/PageHome/images/Brazil400.png"
										width="301" height="143" alt="Apuesta Mundialista" />
								</div>
								<h1>Apuesta Mundialista</h1>
								<p class="lead">Sé el primero en apostar!</p>
								<p>
									<a href="{//contextPath}/visitante/PageVisitante.pub" id="wg_popover" class="btn btn-lg btn-success"
										role="button">Participa</a>
								</p>

							</div>
						</div>
					
						</div>
					</div>
				</div>
	</xsl:template>


	<xsl:template name="social-media">
		<div class="row">
					
					
				    <div class="col-xs-4">
				    
				    	<div class="shadow">
							  <div class="panel panel-default ">
								  <div class="panel-heading">Contenido</div>	
								  <div class="panel-body">
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
						</div>
				    </div>
				    	
					
					 
					 
					<div class="col-xs-4">
						<div class="shadow">
							  <div class="panel panel-default ">
								  <div class="panel-heading">Contenido</div>	
								  <div class="panel-body">
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
						</div>
					</div>
					  
					<div class="col-xs-4">
						<div class="shadow">
							  <div class="panel panel-default ">
								  <div class="panel-heading">Contenido</div>	
								  <div class="panel-body">
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