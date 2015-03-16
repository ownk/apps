<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />
		
	<xsl:include href="../stylesheet/StyleSheetComun.xsl" />
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<xsl:template match="/">

		<PAGE>

			<add type='script' src="publico/visitante/PageFaseGrupos/js/PageFaseGrupos.js" />
			<add type='script' src="publico/visitante/PageFaseGrupos/js/selectFx.js" />
			<add type='script' src="publico/visitante/PageFaseGrupos/js/classie.js" />
			
			<add type='css' src="publico/visitante/PageFaseGrupos/css/cs-select.css" />
			<add type='css' src="publico/visitante/PageFaseGrupos/css/cs-skin-circular.css" />
			<add type='css' src="publico/visitante/PageFaseGrupos/css/demo.css" />
			<add type='css' src="publico/visitante/PageFaseGrupos/css/normalize.css" />


			<esquemaC />
			<xsl:apply-templates select="//XMLPAGE/*" />

			<!-- ================================== -->
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->

			<div class="container">
				
				<xsl:call-template name="header" />
				
				<div class="row">
				
					<!-- PANEL IZQUIERDO -->
					<div class="col-xs-3 ">
						
						<!-- Logo -->
						<div class="row">
							<div class="col-xs-12 ">
								<xsl:call-template name="logo" />
							</div>
						</div>	
						
						<!-- Mis Apuestas -->
						<div class="row">
							<div class="col-xs-12 ">
								<xsl:call-template name="mis_apuestas" />
							</div>
						</div>
					
					</div>
					
					<!-- PANEL DERECHO -->
					<div class="col-xs-9 ">
						<div class="shadow">
						  <div class="panel panel-default ">
							  <div class="panel-heading">Contenido</div>	
							  <div class="panel-body">
			
							  	<div >
									<section>
										<select class="cs-select cs-skin-circular" id="selector">
											<option value="" disabled="" selected="">Select an activity</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
										</select>
									</section>
									
								</div><!-- /container -->
							  	
						    
						  	  </div>
					 	</div>
					</div>
					
					</div>
				
				</div>
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