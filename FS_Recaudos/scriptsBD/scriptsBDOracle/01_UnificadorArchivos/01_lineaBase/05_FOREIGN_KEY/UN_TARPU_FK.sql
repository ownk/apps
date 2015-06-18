Prompt 
Prompt Creando FKs en tabla UN_TARPU

prompt FK_UN_TARPU_UN_TEARPU 
alter table "FS_RECAUDOS_US"."UN_TARPU"
add constraint "FK_UN_TARPU_UN_TEARPU" 
  foreign key ("ARPU_EARPU") 
  references "FS_RECAUDOS_US"."UN_TEARPU" ("EARPU_EARPU") ENABLE;

prompt FK_UN_TARPU_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."UN_TARPU"
add constraint "FK_UN_TARPU_AUT_TUSUA" 
  foreign key ("ARPU_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;
  
  
prompt FK_UN_TARPU_UN_TPRUN 
alter table "FS_RECAUDOS_US"."UN_TARPU"
add constraint "FK_UN_TARPU_UN_TPRUN" 
  foreign key ("ARPU_PRUN") 
  references "FS_RECAUDOS_US"."UN_TPRUN" ("PRUN_PRUN") ENABLE;
  
  
prompt FK_UN_TARPU_UN_TTPAR 
alter table "FS_RECAUDOS_US"."UN_TARPU"
add constraint "FK_UN_TARPU_UN_TTPAR" 
  foreign key ("ARPU_TPAR") 
  references "FS_RECAUDOS_US"."UN_TTPAR" ("TPAR_TPAR") ENABLE;
  
prompt FK_UN_TARPU_UN_TAZPU
alter table "FS_RECAUDOS_US"."UN_TARPU"
add constraint "FK_UN_TARPU_UN_TAZPU" 
  foreign key ("ARPU_AZPU") 
  references "FS_RECAUDOS_US"."UN_TAZPU" ("AZPU_AZPU") ENABLE;