/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Estado Referencia detalle sifi
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TERDS
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TERDS" 
   (	"ERDS_ERDS" 		    VARCHAR2(100 BYTE) 			constraint NN_CO_TERDS_ERDS     not null, 
		"ERDS_DESCRI" 		    VARCHAR2(2000 BYTE)			constraint NN_CO_TERDS_DESCRI   not null,
        "ERDS_COLOR" 	        VARCHAR2(10   BYTE)			constraint NN_CO_TERDS_COLOR     not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERDS"."ERDS_ERDS" IS 'Identificador unico de tipo de error';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERDS"."ERDS_DESCRI" IS 'Descripcion del tipo de error';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERDS"."ERDS_COLOR" IS 'Color para demarcar el tipo de error utilizada';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TERDS"  IS 'Tabla donde se almacenan lo posibles estados de una refencia del archivo de recaudo sifi';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TERDS"
  add  constraint "PK_CO_TERDS"
       primary key ("ERDS_ERDS")
       using index 
       tablespace &Indices;
	   
