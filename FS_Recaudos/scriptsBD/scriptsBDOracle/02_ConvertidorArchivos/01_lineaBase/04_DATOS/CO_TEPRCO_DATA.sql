PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.CO_TEPRCO

Insert into FS_RECAUDOS_US.CO_TEPRCO (EPRCO_EPRCO,EPRCO_DESCRI) values ('INICIADO','Proceso sin generar archivos sifi por tipo de archivo de recaudo');
Insert into FS_RECAUDOS_US.CO_TEPRCO (EPRCO_EPRCO,EPRCO_DESCRI) values ('EN_PROCESO','Proceso que se encuentra generando archivos sifi');
Insert into FS_RECAUDOS_US.CO_TEPRCO (EPRCO_EPRCO,EPRCO_DESCRI) values ('FINALIZADO','Proceso en que el que ya se han generado archivos sifi');
Insert into FS_RECAUDOS_US.CO_TEPRCO (EPRCO_EPRCO,EPRCO_DESCRI) values ('ANULADO','Proceso anulado');

commit;
