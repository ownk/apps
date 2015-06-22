Prompt 
Prompt Creando FKs en tabla CO_TAROR

prompt FK_CO_TAROR_CO_TEAROR 
alter table "FS_RECAUDOS_US"."CO_TAROR"
add constraint "FK_CO_TAROR_CO_TEAROR" 
  foreign key ("AROR_EAROR") 
  references "FS_RECAUDOS_US"."CO_TEAROR" ("EAROR_EAROR") ENABLE;

prompt FK_CO_TAROR_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."CO_TAROR"
add constraint "FK_CO_TAROR_AUT_TUSUA" 
  foreign key ("AROR_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;
  
  
prompt FK_CO_TAROR_CO_TPRCO 
alter table "FS_RECAUDOS_US"."CO_TAROR"
add constraint "FK_CO_TAROR_CO_TPRCO" 
  foreign key ("AROR_PRCO") 
  references "FS_RECAUDOS_US"."CO_TPRCO" ("PRCO_PRCO") ENABLE;
  
  
prompt FK_CO_TAROR_CO_TTPAR 
alter table "FS_RECAUDOS_US"."CO_TAROR"
add constraint "FK_CO_TAROR_CO_TTPAR" 
  foreign key ("AROR_TPAR") 
  references "FS_RECAUDOS_US"."CO_TTPAR" ("TPAR_TPAR") ENABLE;
  
  
prompt FK_CO_TAROR_CO_TARUN
alter table "FS_RECAUDOS_US"."CO_TAROR"
add constraint "FK_CO_TAROR_UN_TARUN" 
  foreign key ("AROR_ARUN") 
  references "FS_RECAUDOS_US"."UN_TARUN" ("ARUN_ARUN") ENABLE;