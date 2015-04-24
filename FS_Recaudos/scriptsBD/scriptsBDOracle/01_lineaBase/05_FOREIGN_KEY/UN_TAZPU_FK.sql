Prompt 
Prompt Creando FKs en tabla UN_TAZPU

prompt FK_UN_TAZPU_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."UN_TAZPU"
add constraint "FK_UN_TAZPU_AUT_TUSUA" 
  foreign key ("AZPU_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;
  
  
prompt FK_UN_TAZPU_UN_TPRUN 
alter table "FS_RECAUDOS_US"."UN_TAZPU"
add constraint "FK_UN_TAZPU_UN_TPRUN" 
  foreign key ("AZPU_PRUN") 
  references "FS_RECAUDOS_US"."UN_TPRUN" ("PRUN_PRUN") ENABLE;
  
