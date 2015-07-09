PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.CO_TTPER


Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(1, 'TPER_PLAN_SIFI_SIN_ESTADO',  'Plan SIFI sin estado', 'info');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(2, 'TPER_PLAN_SIFI_NULO' 	 ,  'Plan SIFI no encontrado', 'info');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(3, 'TPER_REF_NULO'			 ,  'Referencia nula', 'info');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(4, 'TPER_PRCA_PLAN_SIFI_NULO' ,  'Proyecto cancelado sin encargo SIFI asignado', 'info');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(5, 'TPER_PLAN_SIN_ESTADO' 	 ,  'Plan sin estado encontrado', 'info');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(6, 'TPER_PLAN_FRDP_MULTIPLE'  ,  'Plan presenta mas de una formula de distribucion de porcentaje asignada', 'info');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(7, 'TPER_PLAN_DPFD_NULO' 	 ,  'Plan asociado tiene una formula de distribucion sin porcentajes asignados ', 'info');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(9, 'TPER_OFIC_SIFI_NOEXISTE'  ,  'Oficina BSC no presenta homologacion SIFI', 'info');

Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(10, 'TPER_ERROR_BD'  ,  'Error de base de datos no esperado', 'info'); 
commit;
