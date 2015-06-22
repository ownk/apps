Prompt 
Prompt Creando FKs en tabla CO_TDAROR

prompt FK_CO_TDAROR_CO_TEAROR 
alter table "FS_RECAUDOS_US"."CO_TDAROR"
add constraint "FK_CO_TDAROR_CO_TAROR" 
  foreign key ("DAROR_AROR") 
  references "FS_RECAUDOS_US"."CO_TAROR" ("AROR_AROR") ENABLE;

