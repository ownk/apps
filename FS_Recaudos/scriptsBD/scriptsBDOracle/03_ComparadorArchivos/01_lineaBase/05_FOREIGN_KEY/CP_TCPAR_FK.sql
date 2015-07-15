Prompt 
Prompt Creando FKs en tabla CP_TCPAR

prompt FK_CP_TCPAR_CP_TECPAR 
alter table "FS_RECAUDOS_US"."CP_TCPAR"
add constraint "FK_CP_TCPAR_CP_TECPAR" 
  foreign key ("CPAR_ECPAR") 
  references "FS_RECAUDOS_US"."CP_TECPAR" ("ECPAR_ECPAR") ENABLE;

prompt FK_CP_TCPAR_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."CP_TCPAR"
add constraint "FK_CP_TCPAR_AUT_TUSUA" 
  foreign key ("CPAR_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;
  
  
prompt FK_CP_TCPAR_CP_TIBSC 
alter table "FS_RECAUDOS_US"."CP_TCPAR"
add constraint "FK_CP_TCPAR_CP_TIBSC" 
  foreign key ("CPAR_IBSC") 
  references "FS_RECAUDOS_US"."CP_TIBSC" ("IBSC_IBSC") ENABLE;
  
  
prompt FK_CP_TCPAR_CP_TTPAR 
alter table "FS_RECAUDOS_US"."CP_TCPAR"
add constraint "FK_CP_TCPAR_CP_TTPAR" 
  foreign key ("CPAR_TPAR") 
  references "FS_RECAUDOS_US"."CP_TTPAR" ("TPAR_TPAR") ENABLE;
  
  
prompt FK_CP_TCPAR_UN_TARUN
alter table "FS_RECAUDOS_US"."CP_TCPAR"
add constraint "FK_CP_TCPAR_UN_TARUN" 
  foreign key ("CPAR_ARUN") 
  references "FS_RECAUDOS_US"."UN_TARUN" ("ARUN_ARUN") ENABLE;