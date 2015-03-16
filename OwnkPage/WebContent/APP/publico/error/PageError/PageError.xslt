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
			        <h1>404</h1>
			        <h3 class="font-bold">Page Not Found</h3>
			
			        <div class="error-desc">
			            Sorry, but the page you are looking for has note been found. Try checking the URL for error, then hit the refresh button on your browser or try found something else in our app.
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




	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<xsl:template match="@*|node()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>


</xsl:stylesheet>