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
			        <h3 class="font-bold">Acceso Denegado</h3>
			
			        <div class="error-desc">
			            Lo sentimos, pero la página a la que estas tratando de ingresar no puede ser accedida. Intenta comprobar la URL de error, después has clic en el botón de regenerar de su navegador o probar encontrado algo más en nuestra aplicación.
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