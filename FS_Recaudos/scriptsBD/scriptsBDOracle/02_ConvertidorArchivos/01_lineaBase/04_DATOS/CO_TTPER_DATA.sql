PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.CO_TTPER


Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(1, 'TPER_PLAN_SIFI_SIN_ESTADO',  'Plan SIFI sin estado', 'ninguno');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(2, 'TPER_PLAN_SIFI_NULO' 	 ,  'Plan SIFI no encontrado', 'ninguno');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(3, 'TPER_REF_NULO'			 ,  'Referencia nula', 'ninguno');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(4, 'TPER_PRCA_PLAN_SIFI_NULO' ,  'Proyecto cancelado sin encargo SIFI asignado', 'ninguno');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(5, 'TPER_PLAN_SIN_ESTADO' 	 ,  'Plan sin estado encontrado', 'ninguno');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(6, 'TPER_PLAN_FRDP_MULTIPLE'  ,  'Plan presenta mas de una formula de distribucion de porcentaje asignada', 'ninguno');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(7, 'TPER_PLAN_DPFD_NULO' 	 ,  'Plan asociado tiene una formula de distribucion sin porcentajes asignados ', 'ninguno');
Insert into FS_RECAUDOS_US.CO_TTPER (TPER_TPER,TPER_CODIGO,TPER_DESCRI, TPER_COLOR) values(9, 'TPER_OFIC_SIFI_NOEXISTE'  ,  'Oficina BSC no presenta homologacion SIFI', 'ninguno');
 
commit;
