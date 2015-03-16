<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<!-- TEMPLATE PRINCIPAL -->
	<xsl:template name="footer">
		<xsl:param name="visible"/>
		
		<xsl:if test="$visible='true'">
			<div class="msg-footer corner" >
				<div>Ownk © 2015 - Todos los derechos reservados</div>
				<div>Design by Ownk Team</div>
				
			</div>
			
		</xsl:if>
		
		
		
	</xsl:template>
	
	
	
</xsl:stylesheet>