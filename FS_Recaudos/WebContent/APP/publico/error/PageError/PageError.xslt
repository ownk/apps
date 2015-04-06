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
			        <h3 class="font-bold">Página NO Encontrada</h3>
			
			        <div class="error-desc">
			            Lo sentimos,  pero la página que estas buscando no ha sido encontrada. Puedes revisar la URL e identicar algun error. Tambien puedes, si así lo consideras, hacer clic en el botón de INICIO e iniciar de nuevo en nuestra aplicación.
			            <form class="form-inline m-t" role="form" action="{//contextPath}/inicio/PageInicio.pub">
			                
			                <button type="submit" class="btn btn-primary ownk_btn_shadow">Inicio</button>
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