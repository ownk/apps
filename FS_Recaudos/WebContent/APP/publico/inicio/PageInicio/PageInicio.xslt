<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />

	<xsl:include href="../../../general/stylesheets/nav_bar.xsl"/>
	<xsl:include href="../../../general/stylesheets/footer.xsl"/>
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

	<xsl:template match="/">

		<PAGE>

			<xsl:apply-templates select="//XMLPAGE/*" />
			
			<!-- ================================== -->
			<!-- RECUERSOS ESPECIFICOS============= -->
			<!-- ================================== -->
			
			<!-- 
			<add type='css' 	src="publico/inicio/PageInicio/css/animate.min.css" />
			<add type='css' 	src="publico/inicio/PageInicio/css/inicio.css" />
			
			<add type='script' 	src="publico/inicio/PageInicio/js/classie.js" />
			<add type='script' 	src="publico/inicio/PageInicio/js/cbpAnimatedHeader.js" />
			<add type='script' 	src="publico/inicio/PageInicio/js/wow.min.js" />
			<add type='script' 	src="publico/inicio/PageInicio/js/inspinia.js" />
			 -->
			 
			 <add type='script' 	src="publico/inicio/PageInicio/PageInicio.js" />
					

			<!-- ================================== -->
			<!-- CONTENIDO GENERAL================= -->
			<!-- ================================== -->
			<body class="gray-bg">
			
			    <div class="middle-box text-center loginscreen  animated fadeInDown">
			        <div>
			            <div>
			
			                <h1 class="logo-name">fs</h1>
			
			            </div>
			            <h3>Bienvenido</h3>
			            <p>	Fundación Social - ArchivoRecaudos - App.
			            
			            </p>
			            <p>Empecemos. Pruebalo!!!.</p>
			            <form class="m-t" role="form" id="form_login" destino="">
			                <div class="form-group">
			                    <input id="login" name="login" type="text" class="form-control" placeholder="Username" required=""/>
			                </div>
			                <div class="form-group">
			                    <input id="pass" name="pass" type="password" class="form-control" placeholder="Password" required=""/>
			                </div>
			                <button  class="btn btn-primary block full-width m-b" onclick="enviarFormulario();">Login</button>
			
			                <a href="#"><small>Olvidaste tu password?</small></a>
			                <p class="text-muted text-center"><small>Aun no tienes ingreso?</small></p>
			                <a class="btn btn-sm btn-white btn-block" href="register.html">Crea tu usuario</a>
			            </form>
			            
			            
			            <form id="login_form" action="{//contextPath}/inicio/PageLogin.do" method="post">
							<input type="hidden" id="documento_usuario" name="documento_usuario"/>
							<input type="hidden" id="password" name="password"/>
							
						</form>
			            
			            <p class="m-t"> <small>Fundación Social 2015</small> </p>
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