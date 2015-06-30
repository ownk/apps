Prompt 
Prompt Creando FKs en tabla CO_TERAR

prompt FK_CO_TERAR_CO_TTPER
alter table "FS_RECAUDOS_US"."CO_TERAR"
add constraint "FK_CO_TERAR_CO_TTPER" 
  foreign key ("ERAR_TPER") 
  references "FS_RECAUDOS_US"."CO_TTPER" ("TPER_TPER") ENABLE;

prompt FK_CO_TERAR_CO_TAROR
alter table "FS_RECAUDOS_US"."CO_TERAR"
add constraint "FK_CO_TERAR_CO_TAROR" 
  foreign key ("ERAR_AROR") 
  references "FS_RECAUDOS_US"."CO_TAROR" ("AROR_AROR") ENABLE;
  
