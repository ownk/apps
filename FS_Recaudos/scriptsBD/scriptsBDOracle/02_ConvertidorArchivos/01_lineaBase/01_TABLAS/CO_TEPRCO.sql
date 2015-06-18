/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	  
	FUNCION: Estados posibles de proceso de conversion de archivos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TEPRCO
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TEPRCO" 
   (	"EPRCO_EPRCO" 	VARCHAR2(20 BYTE)			constraint NN_CO_TEPRCO_EPRCO not null, 
		"EPRCO_DESCRI" 	VARCHAR2(500 BYTE)			constraint NN_CO_TEPRCO_DESCRI not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEPRCO"."EPRCO_EPRCO" IS 'Estado posible de un proceso de conversion de archivos';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEPRCO"."EPRCO_DESCRI" IS 'Descripcion del estado del proceso de conversion de archivos';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TEPRCO"  IS 'Tabla donde se almacenan los posibles  estados de un proceso de conversion de archivos';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TEPRCO"
  add  constraint "PK_CO_TEPRCO"
       primary key ("EPRCO_EPRCO")
       using index 
       tablespace &Indices;