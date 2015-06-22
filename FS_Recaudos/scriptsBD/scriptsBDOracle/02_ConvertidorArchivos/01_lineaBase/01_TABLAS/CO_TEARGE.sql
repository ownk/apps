/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		estados posibles de un archivo de recaudo ya convertido
			
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TEARGE
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TEARGE" 
   (	"EARGE_EARGE" 	VARCHAR2(20 BYTE)			constraint NN_CO_TEARGE_EARGE not null, 
		"EARGE_DESCRI" 	VARCHAR2(2000 BYTE)			constraint NN_CO_TEARGE_DESCRI not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEARGE"."EARGE_EARGE" IS 'Estado posible de un archivo recaudo original';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEARGE"."EARGE_DESCRI" IS 'Descripcion del estado';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TEARGE"  IS 'Tabla donde se almacenan los posibles estados de un archivo recaudo original';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TEARGE"
  add  constraint "PK_CO_TEARGE"
       primary key ("EARGE_EARGE")
       using index 
       tablespace &Indices;