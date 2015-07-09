/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Tipos de recaudo que se debe excluir por tipo de archivo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TTREX
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TTREX" 
   (	"TREX_TREX" 		    VARCHAR2(100 BYTE)			constraint NN_CO_TTREX_TREX     not null, 
        "TREX_TPAR" 		    VARCHAR2(100 BYTE)          constraint NN_CO_TTREX_TPAR     not null,
		"TREX_DESCRI" 		    VARCHAR2(2000 BYTE)			constraint NN_CO_TTREX_DESCRI   not null,
        "TREX_COLOR" 	        VARCHAR2(100  BYTE)			constraint NN_CO_TTREX_COLOR     not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTREX"."TREX_TREX" IS 'Identificador unico de tipo de recaudo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTREX"."TREX_TPAR" IS 'Identificador unico de tipo de archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTREX"."TREX_DESCRI" IS 'Descripcion';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTREX"."TREX_COLOR" IS 'Color para demarcar';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TTREX"  IS 'Tipos de recaudo que se debe excluir del archivo';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TTREX"
  add  constraint "PK_CO_TTREX"
       primary key ("TREX_TREX", "TREX_TPAR")
       using index 
       tablespace &Indices;
	   
