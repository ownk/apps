Prompt 
Prompt Creando FKs en tabla UN_THARPU

prompt FK_UN_THARPU_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."UN_THARPU"
add constraint "FK_UN_THARPU_AUT_TUSUA" 
  foreign key ("HARPU_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;

prompt FK_UN_THARPU_UN_TEARPU 
alter table "FS_RECAUDOS_US"."UN_THARPU"
add constraint "FK_UN_THARPU_UN_TEARPU" 
  foreign key ("HARPU_EARPU") 
  references "FS_RECAUDOS_US"."UN_TEARPU" ("EARPU_EARPU") ENABLE;

  prompt FK_UN_THARPU_UN_TARPU 
alter table "FS_RECAUDOS_US"."UN_THARPU"
add constraint "FK_UN_THARPU_UN_TARPU" 
  foreign key ("HARPU_ARPU") 
  references "FS_RECAUDOS_US"."UN_TARPU" ("ARPU_ARPU") ENABLE;
