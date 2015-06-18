Prompt 
Prompt Creando FKs en tabla UN_THPRUN

prompt FK_UN_THPRUN_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."UN_THPRUN"
add constraint "FK_UN_THPRUN_AUT_TUSUA" 
  foreign key ("HPRUN_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;

prompt FK_UN_THPRUN_UN_TEPRUN 
alter table "FS_RECAUDOS_US"."UN_THPRUN"
add constraint "FK_UN_THPRUN_UN_TEPRUN" 
  foreign key ("HPRUN_EPRUN") 
  references "FS_RECAUDOS_US"."UN_TEPRUN" ("EPRUN_EPRUN") ENABLE;

  prompt FK_UN_THPRUN_UN_TPRUN 
alter table "FS_RECAUDOS_US"."UN_THPRUN"
add constraint "FK_UN_THPRUN_UN_TPRUN" 
  foreign key ("HPRUN_PRUN") 
  references "FS_RECAUDOS_US"."UN_TPRUN" ("PRUN_PRUN") ENABLE;
