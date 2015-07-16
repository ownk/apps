Prompt 
Prompt Creando FKs en tabla CP_TIBSC


prompt FK_CP_TIBSC_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."CP_TIBSC"
add constraint "FK_CP_TIBSC_AUT_TUSUA" 
  foreign key ("IBSC_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;
  
  
prompt FK_CP_TIBSC_CP_TARUN
alter table "FS_RECAUDOS_US"."CP_TIBSC"
add constraint "FK_CP_TIBSC_UN_TARUN" 
  foreign key ("IBSC_ARUN") 
  references "FS_RECAUDOS_US"."UN_TARUN" ("ARUN_ARUN") ENABLE;