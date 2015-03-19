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
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->

			<body class="gray-bg">
			
			
			    <div class="middle-box text-center animated fadeInDown">
			        <h1>!</h1>
			        <h3 class="font-bold">No Access</h3>
			
			        <div class="error-desc">
			            Sorry, but the page you are try to inside can be access. Try checking the URL for error, then hit the refresh button on your browser or try found something else in our app.
			            <form class="form-inline m-t" role="form">
			                <div class="form-group">
			                    <input type="text" class="form-control" placeholder="Search for page"/>
			                </div>
			                <button type="submit" class="btn btn-primary">Search</button>
			            </form>
			        </div>
			    </div>
			
			   
			
			</body>
				
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