Prompt 
Prompt Creando FKs en tabla GE_TPERN

prompt FK_GE_TPERN_GE_TTDOC

ALTER TABLE "FS_RECAUDOS_US"."GE_TPERN"
ADD CONSTRAINT "FK_GE_TPERN_GE_TTDOC" 
FOREIGN KEY ("PERN_TDOC")
REFERENCES "FS_RECAUDOS_US"."GE_TTDOC" ("TDOC_TDOC") ENABLE;