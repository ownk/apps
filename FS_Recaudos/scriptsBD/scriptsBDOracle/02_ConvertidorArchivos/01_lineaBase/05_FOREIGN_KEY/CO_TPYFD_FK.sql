Prompt 
Prompt Creando FKs en tabla CO_TPYFD

prompt Llave Foranea
prompt FK_CO_TPYFD_CO_TFRDP

ALTER TABLE "FS_RECAUDOS_US"."CO_TPYFD" 
ADD CONSTRAINT "FK_CO_TPYFD_CO_TFRDP" 
FOREIGN KEY ("PYFD_FRDP")
REFERENCES "FS_RECAUDOS_US"."CO_TFRDP" ("FRDP_FRDP") ENABLE;