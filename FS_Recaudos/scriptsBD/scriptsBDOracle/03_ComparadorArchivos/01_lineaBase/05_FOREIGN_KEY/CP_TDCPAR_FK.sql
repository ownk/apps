Prompt 
Prompt Creando FKs en tabla CP_TDCPAR

prompt FK_CP_TDCPAR_CP_TECPAR 
alter table "FS_RECAUDOS_US"."CP_TDCPAR"
add constraint "FK_CP_TDCPAR_CP_TCPAR" 
  foreign key ("DCPAR_CPAR") 
  references "FS_RECAUDOS_US"."CP_TCPAR" ("CPAR_CPAR") ENABLE;

