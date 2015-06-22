Prompt 
Prompt Creando FKs en tabla CO_TTRAR

prompt FK_CO_TTRAR_CO_TTPTR
alter table "FS_RECAUDOS_US"."CO_TTRAR"
add constraint "FK_CO_TTRAR_CO_TTPTR" 
  foreign key ("TRAR_TPTR") 
  references "FS_RECAUDOS_US"."CO_TTPTR" ("TPTR_TPTR") ENABLE;

prompt FK_CO_TTRAR_CO_TAROR
alter table "FS_RECAUDOS_US"."CO_TTRAR"
add constraint "FK_CO_TTRAR_CO_TAROR" 
  foreign key ("TRAR_AROR") 
  references "FS_RECAUDOS_US"."CO_TAROR" ("AROR_AROR") ENABLE;
  
