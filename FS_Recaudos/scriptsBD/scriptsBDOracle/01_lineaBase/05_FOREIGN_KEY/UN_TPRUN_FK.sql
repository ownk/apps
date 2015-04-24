Prompt 
Prompt Creando FKs en tabla UN_TPRUN

prompt FK_UN_TPRUN_UN_TEPRUN 
alter table "FS_RECAUDOS_US"."UN_TPRUN"
add constraint "FK_UN_TPRUN_UN_TEPRUN" 
  foreign key ("PRUN_EPRUN") 
  references "FS_RECAUDOS_US"."UN_TEPRUN" ("EPRUN_EPRUN") ENABLE;

prompt FK_UN_TPRUN_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."UN_TPRUN"
add constraint "FK_UN_TPRUN_AUT_TUSUA" 
  foreign key ("PRUN_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;