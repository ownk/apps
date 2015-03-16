<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<!-- TEMPLATE PRINCIPAL -->
	<xsl:template name="header">
		<!-- HEADER  -->
		<div class="page-header">
		  <h3>
		  		
		  		<div class="row">
					<div class="col-xs-8 ">
					<span class="label label-default">Apuesta Mundilista</span>
					<span class="label label-success">2	</span>
					<span class="label label-primary">0</span>
					<span class="label label-warning">1</span>
					<span class="label label-danger">4</span>
					</div>
					
					
					<div class="col-xs-4 ">
						<div class="info-acceso">
							<div class="info-usuario">
								<div class="">
									<span>Bienvenido(a): 
									visitante
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
		  </h3>
		</div>
						
				
	</xsl:template>
	
	
	<xsl:template name="banner_sign_up">
		<!-- BANNER -->
		<div class="wg_banner">
			<div class="row">
				<div class="col-xs-3 ">
					<xsl:call-template name="logo"/>
				</div>
				  
				<div class="col-xs-9 ">
					<div class="thumbnail shadow">
						<div class="wg_form_login ">
							
						    	<h3>Sign up</h3>
						    	<p>
						    		Ingreso como usuario a traves de usuario o password. Si prefieres puedes realizarlo a traves de redes sociales
						    		<button type="button" class="btn btn-success">Ingresar</button> 
						    	</p>
	
						</div>
					</div>
				
				</div>
			</div>
		</div>
	</xsl:template>
	
	<xsl:template name="logo">
		<!-- BANNER -->
		<div class="thumbnail shadow">
			<div class="wg_img_logo "/>
		</div>
	</xsl:template>
	
	
	<xsl:template name="mis_apuestas">
		<div class="shadow">
			<div class="panel panel-info ">
				<div class="panel-heading">Mis Apuestas</div>
				<div class="panel-body">
				
				
					<!-- Grupo de apuestas -->
					<div class="panel-group" id="accordion">
					  <div class="panel panel-default">
					    <div class="panel-heading">
					      <div>
					      	<div class="row">
								<div class="col-xs-9 ">
							        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
							          Fase de grupos
							        </a>
						        </div>
						        
						        <div class="col-xs-3 ">
						        	<span class="badge">3</span>
						        </div>
					        </div>
					      </div>
					    </div>
					    <div id="collapseOne" class="panel-collapse collapse">
					      <div class="panel-body">
					        
					        		<div class="mis_apuestas-partidos">
							    		<div class="mis_apuestas-fecha" >12 de junio 2014</div>
							    	
							    		<!-- Partido 1 -->
							    		<div class="mis_apuestas-marcador">
								    		<div class="row">
													<div class="col-xs-4">
													        <img src="{//contextPath}/css/general/images/banderas/gha(1).png" alt="Ghana" class="flag-min"/>
												        </div>
												        
												        <div class="col-xs-2 ">
													        <span class="label label-default">0</span>
													    </div>
													   
													    <div class="col-xs-2 ">    
															<span class="label label-default">1</span>
												        </div>
												        
												        <div class="col-xs-4">
													        <img src="{//contextPath}/css/general/images/banderas/col(1).png" alt="Colombia" class="flag-min"/>
												        </div>
											</div>
							    		</div>
							    		
							    		<!-- Partido 2 -->
							    		<div class="mis_apuestas-marcador">
								    		<div class="row">
													<div class="col-xs-4">
													        <img src="{//contextPath}/css/general/images/banderas/arg(1).png" alt="Argentina" class="flag-min"/>
												        </div>
												        
												        <div class="col-xs-2 ">
													        <span class="label label-default">0</span>
													    </div>
													   
													    <div class="col-xs-2 ">    
															<span class="label label-default">1</span>
												        </div>
												        
												        <div class="col-xs-4">
													        <img src="{//contextPath}/css/general/images/banderas/bra(1).png" alt="Brasil" class="flag-min"/>
												        </div>
											</div>
							    		</div>
							    	
							  		</div>
									<div class="mis_apuestas-partidos">			        
					        			<div class="mis_apuestas-fecha" >16 de junio 2014</div>
							    		
							    		<!-- Partido 1 -->
							    		<div class="mis_apuestas-marcador">
								    		<div class="row">
													<div class="col-xs-4">
													        <img src="{//contextPath}/css/general/images/banderas/eng(1).png" alt="Inglaterra" class="flag-min"/>
												        </div>
												        
												        <div class="col-xs-2 ">
													        <span class="label label-default">0</span>
													    </div>
													   
													    <div class="col-xs-2 ">    
															<span class="label label-default">1</span>
												        </div>
												        
												        <div class="col-xs-4">
													        <img src="{//contextPath}/css/general/images/banderas/ger(1).png" alt="Alemania" class="flag-min"/>
												        </div>
											</div>
							    		</div>
							    	
							  		</div>
					        
					        
					        
					        	
					        
					        
					      </div>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading">
					       <div>
					      	<div class="row">
								<div class="col-xs-9 ">
							        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
							          Fase de octavos
							        </a>
						        </div>
						        
						        <div class="col-xs-3 ">
						        	<span class="badge">1</span>
						        </div>
					        </div>
					      </div>
					    </div>
					    <div id="collapseTwo" class="panel-collapse collapse">
					      <div class="panel-body">
					        		<div class="mis_apuestas-partidos">			        
					        			<div class="mis_apuestas-fecha" >16 de julio 2014</div>
							    		
							    		<!-- Partido 1 -->
							    		<div class="mis_apuestas-marcador">
								    		<div class="row">
													<div class="col-xs-4">
													        <img src="{//contextPath}/css/general/images/banderas/col(1).png" alt="Colombia" class="flag-min"/>
												        </div>
												        
												        <div class="col-xs-2 ">
													        <span class="label label-default">2</span>
													    </div>
													   
													    <div class="col-xs-2 ">    
															<span class="label label-default">1</span>
												        </div>
												        
												        <div class="col-xs-4">
													        <img src="{//contextPath}/css/general/images/banderas/ger(1).png" alt="Alemania" class="flag-min"/>
												        </div>
											</div>
							    		</div>
							    	
							  		</div>
					      </div>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading">
					       <div>
					      	<div class="row">
								<div class="col-xs-9 ">
							        <a data-toggle="collapse" data-parent="#accordion" href="collapseThree">
							          Fase de cuartos
							        </a>
						        </div>
						        
						        <div class="col-xs-3 ">
						        	<span class="badge">0</span>
						        </div>
					        </div>
					      </div>
					    </div>
					    <div id="collapseThree" class="panel-collapse collapse">
					      <div class="panel-body">
					        
					      </div>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading">
					       <div>
					      	<div class="row">
								<div class="col-xs-9 ">
							        <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
							          Fase de final
							        </a>
						        </div>
						        
						        <div class="col-xs-3 ">
						        	<span class="badge">0</span>
						        </div>
					        </div>
					      </div>
					    </div>
					    <div id="collapseThree" class="panel-collapse collapse">
					      <div class="panel-body">
					        
					      </div>
					    </div>
					  </div>
					</div>
			
				</div>
			
			</div>
		</div>
	</xsl:template>
	
	
	
	<xsl:template name="footer">
		<div class="footer">
					Todos los derechos reservados. © 2014
		</div>	
	</xsl:template>
	
	
	
</xsl:stylesheet>