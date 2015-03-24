PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.UN_TEPRUN

Insert into FS_RECAUDOS_US.UN_TEPRUN (EPRUN_EPRUN,EPRUN_DESCRI) values ('INICIADO','Proceso sin generar archivo unificado por tipo de archivo de recaudo');
Insert into FS_RECAUDOS_US.UN_TEPRUN (EPRUN_EPRUN,EPRUN_DESCRI) values ('UNIFICANDO_ARCHIVOS','Proceso que se encuentra generando archivos unificados');
Insert into FS_RECAUDOS_US.UN_TEPRUN (EPRUN_EPRUN,EPRUN_DESCRI) values ('FINALIZADO','Proceso en que el que ya se han generado archivos unificados');

commit;
