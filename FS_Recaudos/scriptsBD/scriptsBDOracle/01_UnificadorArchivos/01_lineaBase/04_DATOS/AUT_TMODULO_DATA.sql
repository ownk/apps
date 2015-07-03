PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.AUT_TMODULO


Insert into FS_RECAUDOS_US.AUT_TMODULO (MODULO_MODULO,MODULO_DESCRI,MODULO_NOMB,MODULO_ORDEN) values ('GE','Módulo de generalidades y funcionalidades extras del SGPG-UD que no están involucradas directamente con el proceso de trabajo de grado','General',1);
Insert into FS_RECAUDOS_US.AUT_TMODULO (MODULO_MODULO,MODULO_DESCRI,MODULO_NOMB,MODULO_ORDEN) values ('UN','Módulo de Unificacion de archivos de recaudo en el cual se construye un solo archivo por tipo de archivo de racaudo ','UnificacionArchivos',2);

Insert into FS_RECAUDOS_US.AUT_TMODULO (MODULO_MODULO,MODULO_DESCRI,MODULO_NOMB,MODULO_ORDEN) values ('CO','Módulo de Conversion de archivos de recaudo en el cual se construye el archivo SIFI ','ConversionArchivos',3);

Insert into FS_RECAUDOS_US.AUT_TMODULO (MODULO_MODULO,MODULO_DESCRI,MODULO_NOMB,MODULO_ORDEN) values ('ADM','Módulo de administracion','Administración',99);






commit;