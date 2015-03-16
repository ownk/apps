<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<!-- TEMPLATE PRINCIPAL -->
	<xsl:template name="header">
		<xsl:param name="visible"/>
		
		<xsl:if test="$visible='true'">
			<div class="header" >
				<div class="clearboth">&#160;</div>
			</div>
		</xsl:if>
		
		
		
	</xsl:template>
	
	<xsl:template match="infoUsuario">
	</xsl:template>
	
	
</xsl:stylesheet>