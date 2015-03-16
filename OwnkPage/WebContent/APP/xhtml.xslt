<?xml version="1.0" encoding="UTF-8" ?> 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" omit-xml-declaration="yes" encoding="UTF-8" indent="yes"/>
	
	<xsl:include href="general/stylesheets/header.xsl"/>
	<xsl:include href="general/stylesheets/plugins.xsl"/>
	<xsl:include href="general/stylesheets/footer.xsl"/>
	
<!-- ========================================================= -->	
<!-- ========================================================== -->
<!-- 					  HTML PRINCIPAL  						-->
<!-- ========================================================== -->
<!-- ========================================================== -->
	
	<xsl:template match="/">
	 	<html>
			<head>
				
				<META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
				
				<xsl:variable name="CONTEXTPATH" select="WEB/PAGE/GENERAL/contextPath"/>
				
				<!-- ================================== -->
				<!-- CSS ============================== -->
				<!-- ================================== -->
					<link rel="stylesheet" href="{$CONTEXTPATH}/general/css/general.css" type="text/css"/>
					
					
				
					<!-- Bootstrap -->
					<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
					<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"/>
					
					
					<!-- CSS por pagina -->
					<xsl:for-each select="WEB/PAGE//add[@type='css']">
						<link rel="stylesheet" href="{$CONTEXTPATH}/{@src}" type="text/css"/>
					</xsl:for-each>
					
					<xsl:for-each select="WEB/PAGE//add[@type='css_link']">
						<link rel="stylesheet" href="{@src}"/>
					</xsl:for-each>
            	
            	
            	<!-- ================================== -->
				<!-- JavaScript ======================= -->
				<!-- ================================== -->
				
					
					<script> var GLOBAL_CONTEXTPATH = '<xsl:value-of select="$CONTEXTPATH"/>'; <!-- window.history.forward(0); --></script>
					
					<!-- Jquery-->
					<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
					
					<!-- general -->
					<script type="text/javascript" src="{$CONTEXTPATH}/general/js/osm-core.js"></script>
					<script type="text/javascript" src="{$CONTEXTPATH}/general/js/ui-jcore.js"></script>
					<script type="text/javascript" src="{$CONTEXTPATH}/general/js/si.files.js"></script>
					
					
					
					<!-- bootstrap -->
					<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
					
					
					<!-- Json -->
					<script type="text/javascript" src="{$CONTEXTPATH}/general/js/jsonrpc/jsonrpc.js"></script>
					
			
				
					<!-- VARIABLES -->
					<script type="text/javascript" src="{$CONTEXTPATH}/general/js/init.js"></script>
				
					
					<!-- JavaScript por pagina -->							
	            	<xsl:for-each select="WEB/PAGE//add[@type='script']">
						<script type="text/javascript" src="{$CONTEXTPATH}/{@src}"></script>
					</xsl:for-each>
					
					<xsl:for-each select="WEB/PAGE//add[@type='script_link']">
						<script href="{@src}"/>
					</xsl:for-each>
				
			</head>
			
			<body>
				
				<!-- SE MUESTRA EL CONTENIDO SEGUN EL ESQUEMA ESPECIFICADO -->
				<xsl:choose>
					<xsl:when test="count(//esquemaC)>0">
						<xsl:call-template name="esquemaC"/>
					</xsl:when>
					
					<xsl:otherwise>
						<xsl:call-template name="esquemaC"/>
					</xsl:otherwise>
				
				
				</xsl:choose>		
			 	
				
			</body>
		</html>
		
	</xsl:template>
	
<!-- ========================================================== -->
<!-- = ESQUEMAS DE VISUALIZACION ==============================	-->
<!-- ========================================================== -->


<!-- = solo el contenido ====================================== -->	
	<xsl:template name="esquemaC">
		<!-- contenido -->
		<div style="" >
					
			<xsl:apply-templates select="WEB/PAGE/*"/>
		</div>
		
	</xsl:template>
	
	
<!-- ========================================================== -->
<!-- = EXCLUYENTES DE XML =====================================	-->
<!-- ========================================================== -->
	<xsl:template match="add">
	</xsl:template>
	
	<xsl:template match="esquemaHMPCF">
	</xsl:template>
	
	<xsl:template match="esquemaHMCPF">
	</xsl:template>
	
	<xsl:template match="esquemaHMCF">
	</xsl:template>
	
	<xsl:template match="esquemaHCF">
	</xsl:template>
	
	<xsl:template match="esquemaC">
	</xsl:template>
	
	<xsl:template match="GENERAL">
	</xsl:template>
	
	<xsl:template match="ACCESS_INFO">
	</xsl:template>
	
	<xsl:template match="PARAMETERS">
	</xsl:template>
	
	<xsl:template match="ATTRIBUTES">
	</xsl:template>
	
	<xsl:template match="SESSION">
	</xsl:template>
	
	<xsl:template match="PAGEDATA">
	</xsl:template>
	
<!-- ========================================================== -->
<!-- = TRANSFORMADORES ========================================	-->
<!-- ========================================================== -->
	<xsl:template match="@*|node()" priority="-1">
	    <xsl:copy>
	        <xsl:apply-templates select="@*|node()"/>
	    </xsl:copy>
	</xsl:template>

	<xsl:template match="form">
		<form action="{@destino}" method="post" enctype="{@enctype}" onsubmit="return false;">
			<xsl:if test="string-length(@id)>0">
				<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
				<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates/>
		</form>
	</xsl:template>
	
	
	

	
	
</xsl:stylesheet>