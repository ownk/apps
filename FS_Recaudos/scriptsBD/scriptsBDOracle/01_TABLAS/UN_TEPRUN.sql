/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		estados de proceso de unificacion de archivos
			
  OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TEPRUN
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TEPRUN" 
   (	"EPRUN_EPRUN" 	VARCHAR2(20 BYTE)			constraint NN_UN_TEPRUN_EPRUN not null, 
		"EPRUN_DESCRI" 	VARCHAR2(500 BYTE)			constraint NN_UN_TEPRUN_DESCRI not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TEPRUN"."EPRUN_EPRUN" IS 'Estados posibles de un proceso de unificacion de archivos';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TEPRUN"."EPRUN_DESCRI" IS 'Descripcion del estado del proceso de unificacion de archivos';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TEPRUN"  IS 'Tabla donde se almacenan los estados de proceso de unificacion de archivos';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TEPRUN"
  add  constraint "PK_UN_TEPRUN"
       primary key ("EPRUN_EPRUN")
       using index 
       tablespace &Indices;