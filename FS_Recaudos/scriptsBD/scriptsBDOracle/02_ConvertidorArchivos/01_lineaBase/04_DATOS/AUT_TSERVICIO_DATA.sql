PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.AUT_T*

Insert into FS_RECAUDOS_US.AUT_TMODULO (MODULO_MODULO,MODULO_DESCRI,MODULO_NOMB,MODULO_ORDEN) values ('CO','Módulo de Conversion de archivos de recaudo en el cual se construye el archivo SIFI ','ConversionArchivos',3);

Insert into FS_RECAUDOS_US.AUT_TSERVICIO (SERVICIO_TIPO,SERVICIO_SERVICIO,SERVICIO_MODULO,SERVICIO_DESCRI,SERVICIO_NOMB,SERVICIO_ORDEN) values ('O',4,'CO','Conversion Archivos Recaudo','Conversion Archivos Recaudo',1);

Insert into FS_RECAUDOS_US.AUT_TSROL (SROL_SERVICIO,SROL_ROL,SROL_VISIBLE) values (4,'OPER','S');

Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('conversion/PageArchivoRecaudoOriginalPorConvertir.do',4,'P');



commit;

		