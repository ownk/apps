/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		estados posibles de un archivo de recaudo original a convertir
			
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TEAROR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TEAROR" 
   (	"EAROR_EAROR" 	VARCHAR2(20 BYTE)			constraint NN_CO_TEAROR_EAROR not null, 
		"EAROR_DESCRI" 	VARCHAR2(2000 BYTE)			constraint NN_CO_TEAROR_DESCRI not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEAROR"."EAROR_EAROR" IS 'Estado posible de un archivo recaudo original';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEAROR"."EAROR_DESCRI" IS 'Descripcion del estado';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TEAROR"  IS 'Tabla donde se almacenan los posibles estados de un archivo recaudo original';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TEAROR"
  add  constraint "PK_CO_TEAROR"
       primary key ("EAROR_EAROR")
       using index 
       tablespace &Indices;