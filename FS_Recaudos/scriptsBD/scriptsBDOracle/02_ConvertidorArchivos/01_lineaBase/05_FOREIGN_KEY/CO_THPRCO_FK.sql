Prompt 
Prompt Creando FKs en tabla CO_THPRCO

prompt FK_CO_THPRCO_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."CO_THPRCO"
add constraint "FK_CO_THPRCO_AUT_TUSUA" 
  foreign key ("HPRCO_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;

prompt FK_CO_THPRCO_CO_TEPRCO 
alter table "FS_RECAUDOS_US"."CO_THPRCO"
add constraint "FK_CO_THPRCO_CO_TEPRCO" 
  foreign key ("HPRCO_EPRCO") 
  references "FS_RECAUDOS_US"."CO_TEPRCO" ("EPRCO_EPRCO") ENABLE;

  prompt FK_CO_THPRCO_CO_TPRCO 
alter table "FS_RECAUDOS_US"."CO_THPRCO"
add constraint "FK_CO_THPRCO_CO_TPRCO" 
  foreign key ("HPRCO_PRCO") 
  references "FS_RECAUDOS_US"."CO_TPRCO" ("PRCO_PRCO") ENABLE;
