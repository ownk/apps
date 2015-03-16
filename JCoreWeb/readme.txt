Configuracion:

Configuración del Tomcat

1. En la parte del servidor también tenemos que especificar el encoding a utilizar. En Tomcat por defecto se especifica el formato de codificación ISO-8859-1. Para cambiar la codificación tenemos que modificar el archivo server.xml que se encuentra en DIRECTORIO_INSTALACION_TOMCAT\conf\server.xml.
Añadimos el atributo URIEncoding=“UTF-8” en la etiqueta <Connector port=”8080” … /> ya que es aquí donde se configura el comportamiento que sigue Tomcat cuando recibe peticiones a través del puerto 8080.

<Connector port="8080" maxHttpHeaderSize="8192" maxThreads="150" minSpareThreads="25"     maxSpareThreads="75" enableLookups="false" redirectPort="8443" acceptCount="100"     connectionTimeout="20000" disableUploadTimeout="true" URIEncoding="UTF-8"/>

2. Podría ser necesario especificar al contenedor de servlets Catalina la codificación a utilizar indicándole en el arranque que los caracteres siempre van en UTF-8. Para ello editamos el fichero catalina.bat (Windows) o el catalina.sh (Linux) y añadimos lo siguiente:

    (Windows) set CATALINA_OPTS=-Dfile.encoding="UTF-8"
    (Linux) export CATALINA_OPTS=-Dfile.encoding="UTF-8"

	A la vez indicamos las opciones del arranque de la JVM en UTF-8 por lo que añadimos al mismo fichero.

    (Windows) set JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding="UTF-8"
    (Linux) export JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding="UTF-8"
    
    