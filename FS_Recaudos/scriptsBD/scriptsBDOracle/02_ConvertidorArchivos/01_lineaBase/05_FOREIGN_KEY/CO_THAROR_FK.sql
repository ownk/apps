Prompt 
Prompt Creando FKs en tabla CO_THAROR

prompt FK_CO_THAROR_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."CO_THAROR"
add constraint "FK_CO_THAROR_AUT_TUSUA" 
  foreign key ("HAROR_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;

prompt FK_CO_THAROR_CO_TEAROR 
alter table "FS_RECAUDOS_US"."CO_THAROR"
add constraint "FK_CO_THAROR_CO_TEAROR" 
  foreign key ("HAROR_EAROR") 
  references "FS_RECAUDOS_US"."CO_TEAROR" ("EAROR_EAROR") ENABLE;

  prompt FK_CO_THAROR_CO_TAROR 
alter table "FS_RECAUDOS_US"."CO_THAROR"
add constraint "FK_CO_THAROR_CO_TAROR" 
  foreign key ("HAROR_AROR") 
  references "FS_RECAUDOS_US"."CO_TAROR" ("AROR_AROR") ENABLE;
