Prompt 
Prompt Creando FKs en tabla CO_TTREX


prompt FK_CO_TTREX_CO_TTPAR 
alter table "FS_RECAUDOS_US"."CO_TTREX"
add constraint "FK_CO_TTREX_CO_TTPAR" 
  foreign key ("TREX_TPAR") 
  references "FS_RECAUDOS_US"."CO_TTPAR" ("TPAR_TPAR") ENABLE;
  