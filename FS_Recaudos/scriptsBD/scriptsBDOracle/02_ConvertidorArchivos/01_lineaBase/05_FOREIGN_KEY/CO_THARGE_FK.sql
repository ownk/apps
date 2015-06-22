Prompt 
Prompt Creando FKs en tabla CO_THARGE

prompt FK_CO_THARGE_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."CO_THARGE"
add constraint "FK_CO_THARGE_AUT_TUSUA" 
  foreign key ("HARGE_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;

prompt FK_CO_THARGE_CO_TEARGE 
alter table "FS_RECAUDOS_US"."CO_THARGE"
add constraint "FK_CO_THARGE_CO_TEARGE" 
  foreign key ("HARGE_EARGE") 
  references "FS_RECAUDOS_US"."CO_TEARGE" ("EARGE_EARGE") ENABLE;

  prompt FK_CO_THARGE_CO_TARGE 
alter table "FS_RECAUDOS_US"."CO_THARGE"
add constraint "FK_CO_THARGE_CO_TARGE" 
  foreign key ("HARGE_ARGE") 
  references "FS_RECAUDOS_US"."CO_TARGE" ("ARGE_ARGE") ENABLE;
