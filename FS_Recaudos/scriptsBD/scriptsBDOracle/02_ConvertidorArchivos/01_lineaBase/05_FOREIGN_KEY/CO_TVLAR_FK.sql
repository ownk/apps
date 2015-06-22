Prompt 
Prompt Creando FKs en tabla CO_TVLAR

prompt FK_CO_TVLAR_CO_TTPVL
alter table "FS_RECAUDOS_US"."CO_TVLAR"
add constraint "FK_CO_TVLAR_CO_TTPVL" 
  foreign key ("VLAR_TPVL") 
  references "FS_RECAUDOS_US"."CO_TTPVL" ("TPVL_TPVL") ENABLE;

prompt FK_CO_TVLAR_CO_TAROR
alter table "FS_RECAUDOS_US"."CO_TVLAR"
add constraint "FK_CO_TVLAR_CO_TAROR" 
  foreign key ("VLAR_AROR") 
  references "FS_RECAUDOS_US"."CO_TAROR" ("AROR_AROR") ENABLE;
  
