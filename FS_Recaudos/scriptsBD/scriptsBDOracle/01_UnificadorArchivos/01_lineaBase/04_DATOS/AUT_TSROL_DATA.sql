PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.AUT_TSROL

PROMPT SERVICIOS ADMINISTRADOR

Insert into FS_RECAUDOS_US.AUT_TSROL (SROL_SERVICIO,SROL_ROL,SROL_VISIBLE) values (1,'ADMIN','S');
Insert into FS_RECAUDOS_US.AUT_TSROL (SROL_SERVICIO,SROL_ROL,SROL_VISIBLE) values (2,'ADMIN','S');

PROMPT SERVICIOS OPERADOR


PROMPT SERVICIOS GENERALES
Insert into FS_RECAUDOS_US.AUT_TSROL (SROL_SERVICIO,SROL_ROL,SROL_VISIBLE) values (1,'OPER','S');
Insert into FS_RECAUDOS_US.AUT_TSROL (SROL_SERVICIO,SROL_ROL,SROL_VISIBLE) values (2,'OPER','S');

	
PROMPT SERVICIOS UNIFICACION ARCHIVOS
Insert into FS_RECAUDOS_US.AUT_TSROL (SROL_SERVICIO,SROL_ROL,SROL_VISIBLE) values (3,'OPER','S');


PROMPT SERVICIOS CONVERSION ARCHIVOS
Insert into FS_RECAUDOS_US.AUT_TSROL (SROL_SERVICIO,SROL_ROL,SROL_VISIBLE) values (4,'OPER','S');

commit;