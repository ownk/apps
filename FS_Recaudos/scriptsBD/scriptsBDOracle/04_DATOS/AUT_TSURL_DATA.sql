PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.AUT_TSURL


PROMPT SERVICIOS GENERALES

Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('inicio/PageInicio.pub',1,'P');
Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('inicio/PageBienvenida.do',2,'P');

PROMPT SERVICIOS UNIFICACION ARCHIVOS
Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('unificacion/PageRegistrarArchivosZIPRecaudo.do',3,'P');
Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('unificacion/PageIniciarProcesoUnificacionArchivos.do',3,'P');
Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('unificacion/PageUploadFilesProcesoUnificacion.do',3,'S');
Insert into FS_RECAUDOS_US.AUT_TSURL (SURL_URL,SURL_SERVICIO,SURL_TIPO) values ('unificacion/PageProcesoUnificacionArchivos.do',3,'S');

commit;