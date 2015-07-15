/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		Estados posibles de la comparacion
			
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CP_TECPAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CP_TECPAR" 
   (	"ECPAR_ECPAR" 	VARCHAR2(20 BYTE)			constraint NN_CP_TECPAR_ECPAR not null, 
		"ECPAR_DESCRI" 	VARCHAR2(2000 BYTE)			constraint NN_CP_TECPAR_DESCRI not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TECPAR"."ECPAR_ECPAR" IS 'Estado posible de comparacion archivo recaudo original';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TECPAR"."ECPAR_DESCRI" IS 'Descripcion del estado';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."CP_TECPAR"  IS 'Tabla donde se almacenan los posibles estados de una comparacion archivo recaudo original';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CP_TECPAR"
  add  constraint "PK_CP_TECPAR"
       primary key ("ECPAR_ECPAR")
       using index 
       tablespace &Indices;