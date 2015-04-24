Prompt 
Prompt Creando FKs en tabla UN_TARPR


prompt FK_UN_TARPR_UN_TPRUN 
alter table "FS_RECAUDOS_US"."UN_TARPR"
add constraint "FK_UN_TARPR_UN_TPRUN" 
  foreign key ("ARPR_PRUN") 
  references "FS_RECAUDOS_US"."UN_TPRUN" ("PRUN_PRUN") ENABLE;
  
  
prompt FK_UN_TARPR_UN_TARUN
alter table "FS_RECAUDOS_US"."UN_TARPR"
add constraint "FK_UN_TARPR_UN_TARUN" 
  foreign key ("ARPR_ARUN") 
  references "FS_RECAUDOS_US"."UN_TARUN" ("ARUN_ARUN") ENABLE;

prompt FK_UN_TARPR_UN_TARPU
alter table "FS_RECAUDOS_US"."UN_TARPR"
add constraint "FK_UN_TARPR_UN_TARPU" 
  foreign key ("ARPR_ARPU") 
  references "FS_RECAUDOS_US"."UN_TARPU" ("ARPU_ARPU") ENABLE;

