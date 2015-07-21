PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.AUT_T*

Insert into FS_RECAUDOS_US.AUT_TMODULO (MODULO_MODULO,MODULO_DESCRI,MODULO_NOMB,MODULO_ORDEN) values ('CP','Módulo de Comparacion de archivos de recaudo BSC vs Movimientos de Internet ','ComparacionArchivos',4);

Insert into FS_RECAUDOS_US.AUT_TSERVICIO (SERVICIO_TIPO,SERVICIO_SERVICIO,SERVICIO_MODULO,SERVICIO_DESCRI,SERVICIO_NOMB,SERVICIO_ORDEN) values ('O',5,'CP','Comparación Archivos Recaudo','Comparación Archivos Recaudo',1);

Insert into FS_RECAUDOS_US.AUT_TSROL (SROL_SERVICIO,SROL_ROL,SROL_VISIBLE) values (5,'OPER','S');

Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('compara/PageRegistrarArchivoInternetBSC.do',5,'P');
Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('compara/PageIniciarComparacionArchivos.do',5,'S');
Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('compara/PageUploadFileArchivoBSC.do',5,'S');
Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('compara/PageComparacionArchivoRecaudo.do',5,'S');



commit;

		