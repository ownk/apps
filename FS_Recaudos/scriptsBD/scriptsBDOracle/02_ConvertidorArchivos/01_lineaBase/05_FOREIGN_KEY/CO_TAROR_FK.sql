Prompt 
Prompt Creando FKs en tabla CO_TARGE

prompt FK_CO_TARGE_CO_TEARGE 
alter table "FS_RECAUDOS_US"."CO_TARGE"
add constraint "FK_CO_TARGE_CO_TEARGE" 
  foreign key ("ARGE_EARGE") 
  references "FS_RECAUDOS_US"."CO_TEARGE" ("EARGE_EARGE") ENABLE;

prompt FK_CO_TARGE_AUT_TUSUA 
alter table "FS_RECAUDOS_US"."CO_TARGE"
add constraint "FK_CO_TARGE_AUT_TUSUA" 
  foreign key ("ARGE_USUA") 
  references "FS_RECAUDOS_US"."AUT_TUSUA" ("USUA_USUA") ENABLE;
  
  
prompt FK_CO_TARGE_CO_TPRCO 
alter table "FS_RECAUDOS_US"."CO_TARGE"
add constraint "FK_CO_TARGE_CO_TPRCO" 
  foreign key ("ARGE_PRCO") 
  references "FS_RECAUDOS_US"."CO_TPRCO" ("PRCO_PRCO") ENABLE;
  
  
prompt FK_CO_TARGE_CO_TTPAR 
alter table "FS_RECAUDOS_US"."CO_TARGE"
add constraint "FK_CO_TARGE_CO_TTPAR" 
  foreign key ("ARGE_TPAR") 
  references "FS_RECAUDOS_US"."CO_TTPAR" ("TPAR_TPAR") ENABLE;
  
  
prompt FK_CO_TARGE_CO_TAROR
alter table "FS_RECAUDOS_US"."CO_TARGE"
add constraint "FK_CO_TARGE_CO_TAROR" 
  foreign key ("ARGE_AROR") 
  references "FS_RECAUDOS_US"."CO_TAROR" ("AROR_AROR") ENABLE;