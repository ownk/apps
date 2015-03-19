/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		estados posibles de un archivo de recaudo unificado
			
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TEARUN
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TEARUN" 
   (	"EARUN_EARUN" 	VARCHAR2(20 BYTE)			constraint NN_UN_TEARUN_EARUN not null, 
		"EARUN_DESCRI" 	VARCHAR2(2000 BYTE)			constraint NN_UN_TEARUN_DESCRI not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TEARUN"."EARUN_EARUN" IS 'Estado posible de un archivo unificado';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TEARUN"."EARUN_DESCRI" IS 'Descripcion del estado';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TEARUN"  IS 'Tabla donde se almacenan los posibles estados de un archivo unificado';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TEARUN"
  add  constraint "PK_UN_TEARUN"
       primary key ("EARUN_EARUN")
       using index 
       tablespace &Indices;