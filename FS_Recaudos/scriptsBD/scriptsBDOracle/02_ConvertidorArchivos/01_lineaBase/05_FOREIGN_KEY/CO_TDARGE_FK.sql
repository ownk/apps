Prompt 
Prompt Creando FKs en tabla CO_TDARGE

prompt FK_CO_TDARGE_CO_TEARGE 
alter table "FS_RECAUDOS_US"."CO_TDARGE"
add constraint "FK_CO_TDARGE_CO_TARGE" 
  foreign key ("DARGE_ARGE") 
  references "FS_RECAUDOS_US"."CO_TARGE" ("ARGE_ARGE") ENABLE;

