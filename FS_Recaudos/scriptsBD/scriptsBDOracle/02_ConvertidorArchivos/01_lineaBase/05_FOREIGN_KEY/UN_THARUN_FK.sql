Prompt 
Prompt Creando FKs en tabla UN_THARUN

prompt FK_UN_THARUN_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."UN_THARUN"
add constraint "FK_UN_THARUN_AUT_TUSUA" 
  foreign key ("HARUN_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;

prompt FK_UN_THARUN_UN_TEARUN 
alter table "FS_RECAUDOS_US"."UN_THARUN"
add constraint "FK_UN_THARUN_UN_TEARUN" 
  foreign key ("HARUN_EARUN") 
  references "FS_RECAUDOS_US"."UN_TEARUN" ("EARUN_EARUN") ENABLE;

  prompt FK_UN_THARUN_UN_TARUN 
alter table "FS_RECAUDOS_US"."UN_THARUN"
add constraint "FK_UN_THARUN_UN_TARUN" 
  foreign key ("HARUN_ARUN") 
  references "FS_RECAUDOS_US"."UN_TARUN" ("ARUN_ARUN") ENABLE;
