<?xml version="1.0" encoding="UTF-8" ?> 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->	

	<xsl:template match="/">
	
		<PAGE>
			
			<add type="stylesheet" src="privado/inicio/PageBienvenida.css"/>
			<esquemaHMCF/>
			<xsl:apply-templates select="//XMLPAGE/*"/>
			
			<!-- TRANSFORMACIONES ESPECIFICAS -->
			<div class="corner ">
				
				<div class="bg-home corner" style="float:right;">
					<div class="info-home">
						El proceso de gestión 
						de los recursos de información 
						dentro de la <b>UD</b> debe
						entenderse como el manejo de la 
						inteligencia corporativa a objeto de incrementar 
						sus niveles de eficacia,
						eficiencia y efectividad en el cumplimiento
						de su misión social.
					</div>
					
					<div class="info-home resaltado">
						Universidad Distrital FJC.
					</div>
					
					
				</div>
				
			</div>
			
					
			
		</PAGE>
		
	</xsl:template>

<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	
	<xsl:template match="@*|node()" priority="-1">
	    <xsl:copy>
	        <xsl:apply-templates select="@*|node()"/>
	    </xsl:copy>
	</xsl:template>

<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->	
	
</xsl:stylesheet>