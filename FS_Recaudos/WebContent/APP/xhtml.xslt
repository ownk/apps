<?xml version="1.0" encoding="UTF-8" ?> 
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" omit-xml-declaration="yes" encoding="UTF-8" indent="yes"/>
	
	<xsl:include href="general/stylesheets/header.xsl"/>
	<xsl:include href="general/stylesheets/nav_bar.xsl"/>
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
				
				
				<meta charset="utf-8"/>
			    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
			
			    <title>INSPINIA | Dashboard</title>
			
			    <link href="{$CONTEXTPATH}/general/css/bootstrap.min.css" rel="stylesheet"/>
			    <link href="{$CONTEXTPATH}/general/font-awesome/css/font-awesome.css" rel="stylesheet"/>
			
			    <!-- Toastr style -->
			    <link href="{$CONTEXTPATH}/general/css/plugins/toastr/toastr.min.css" rel="stylesheet"/>
			
			    <!-- Gritter -->
			    <link href="{$CONTEXTPATH}/general/js/plugins/gritter/jquery.gritter.css" rel="stylesheet"/>
			
			    <link href="{$CONTEXTPATH}/general/css/animate.css" rel="stylesheet"/>
			    <link href="{$CONTEXTPATH}/general/css/style.css" rel="stylesheet"/>
			    <link href="{$CONTEXTPATH}/general/css/ownk.css" rel="stylesheet"/>
				
				
				
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
				
				 <!-- Mainly scripts -->
			    <script src="{$CONTEXTPATH}/general/js/jquery-2.1.1.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/bootstrap.min.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/plugins/metisMenu/jquery.metisMenu.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
			
			    <!-- Flot -->
			    <script src="{$CONTEXTPATH}/general/js/plugins/flot/jquery.flot.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/plugins/flot/jquery.flot.spline.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/plugins/flot/jquery.flot.resize.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/plugins/flot/jquery.flot.pie.js"></script>
			
			    <!-- Peity -->
			    <script src="{$CONTEXTPATH}/general/js/plugins/peity/jquery.peity.min.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/demo/peity-demo.js"></script>
			
			    <!-- Custom and plugin javascript -->
			    <script src="{$CONTEXTPATH}/general/js/inspinia.js"></script>
			    <script src="{$CONTEXTPATH}/general/js/plugins/pace/pace.min.js"></script>
			
			    <!-- jQuery UI -->
			    <script src="{$CONTEXTPATH}/general/js/plugins/jquery-ui/jquery-ui.min.js"></script>
			
			    <!-- GITTER -->
			    <script src="{$CONTEXTPATH}/general/js/plugins/gritter/jquery.gritter.min.js"></script>
			
			    <!-- Sparkline -->
			    <script src="{$CONTEXTPATH}/general/js/plugins/sparkline/jquery.sparkline.min.js"></script>
			
			    <!-- Sparkline demo data  -->
			    <script src="{$CONTEXTPATH}/general/js/demo/sparkline-demo.js"></script>
			
			    <!-- ChartJS-->
			    <script src="{$CONTEXTPATH}/general/js/plugins/chartJs/Chart.min.js"></script>
			
			    <!-- Toastr -->
			    <script src="{$CONTEXTPATH}/general/js/plugins/toastr/toastr.min.js"></script>
				
				
				<!-- UTILS-->
				<script type="text/javascript" src="{$CONTEXTPATH}/general/js/core.js"></script>
				
				
				<!-- Json -->
				<script type="text/javascript" src="{$CONTEXTPATH}/general/js/jsonrpc/jsonrpc.js"></script>
				
					
				<!-- VARIABLES -->
				<script type="text/javascript" src="{$CONTEXTPATH}/general/js/init.js"></script>
			
			
				<script type='text/javascript' src='{$CONTEXTPATH}/general/js/jsbn/base64.js'> </script>
				<script type='text/javascript' src='{$CONTEXTPATH}/general/js/jsbn/jsbn.js'> </script>
				<script type='text/javascript' src='{$CONTEXTPATH}/general/js/jsbn/rsa.js'> </script>
				
				<!-- JavaScript por pagina -->							
	           	<xsl:for-each select="WEB/PAGE//add[@type='script']">
					<script type="text/javascript" src="{$CONTEXTPATH}/{@src}"></script>
				</xsl:for-each>
				
				<xsl:for-each select="WEB/PAGE//add[@type='script_link']">
					<script href="{@src}"/>
				</xsl:for-each>
				
			</head>
			
			
				
					
			<xsl:apply-templates select="WEB/PAGE/*"/>
			
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

	<!-- <xsl:template match="form">
		<form class="{@class}" action="{@destino}" method="post" enctype="{@enctype}" onsubmit="return false;">
			<xsl:if test="string-length(@id)>0">
				<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
				<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates/>
		</form>
	</xsl:template> -->
	
	
	

	
	
</xsl:stylesheet>