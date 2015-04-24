Prompt 
Prompt Creando FKs en tabla UN_TTPAR


prompt FK_UN_TTPAR_UN_TESTR 
alter table "FS_RECAUDOS_US"."UN_TTPAR"
add constraint "FK_UN_TTPAR_UN_TESTR" 
  foreign key ("TPAR_ESTR") 
  references "FS_RECAUDOS_US"."UN_TESTR" ("ESTR_ESTR") ENABLE;
  
