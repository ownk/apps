Prompt 
Prompt Creando FKs en tabla UN_TTRAR


prompt FK_UN_TTRAR_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."UN_TTRAR"
add constraint "FK_UN_TTRAR_AUT_TUSUA" 
  foreign key ("TRAR_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;
  
  
prompt FK_UN_TTRAR_UN_TPRUN 
alter table "FS_RECAUDOS_US"."UN_TTRAR"
add constraint "FK_UN_TTRAR_UN_TPRUN" 
  foreign key ("TRAR_PRUN") 
  references "FS_RECAUDOS_US"."UN_TPRUN" ("PRUN_PRUN") ENABLE;
  
  
prompt FK_UN_TTRAR_UN_TTPAR 
alter table "FS_RECAUDOS_US"."UN_TTRAR"
add constraint "FK_UN_TTRAR_UN_TTPAR" 
  foreign key ("TRAR_TPAR") 
  references "FS_RECAUDOS_US"."UN_TTPAR" ("TPAR_TPAR") ENABLE;
