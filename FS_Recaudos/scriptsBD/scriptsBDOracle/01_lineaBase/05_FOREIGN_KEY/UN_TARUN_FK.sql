Prompt 
Prompt Creando FKs en tabla UN_TARUN

prompt FK_UN_TARUN_UN_TEARUN 
alter table "FS_RECAUDOS_US"."UN_TARUN"
add constraint "FK_UN_TARUN_UN_TEARUN" 
  foreign key ("ARUN_EARUN") 
  references "FS_RECAUDOS_US"."UN_TEARUN" ("EARUN_EARUN") ENABLE;

prompt FK_UN_TARUN_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."UN_TARUN"
add constraint "FK_UN_TARUN_AUT_TUSUA" 
  foreign key ("ARUN_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;
  
  
prompt FK_UN_TARUN_UN_TPRUN 
alter table "FS_RECAUDOS_US"."UN_TARUN"
add constraint "FK_UN_TARUN_UN_TPRUN" 
  foreign key ("ARUN_PRUN") 
  references "FS_RECAUDOS_US"."UN_TPRUN" ("PRUN_PRUN") ENABLE;
  
  
prompt FK_UN_TARUN_UN_TTPAR 
alter table "FS_RECAUDOS_US"."UN_TARUN"
add constraint "FK_UN_TARUN_UN_TTPAR" 
  foreign key ("ARUN_TPAR") 
  references "FS_RECAUDOS_US"."UN_TTPAR" ("TPAR_TPAR") ENABLE;
  
