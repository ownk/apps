/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Estados posibles de un archivo de recaudo para unificar
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TEARPU
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TEARPU" 
   (	"EARPU_EARPU" 	VARCHAR2(20 BYTE)			constraint NN_UN_TEARPU_EARPU not null, 
		"EARPU_DESCRI" 	VARCHAR2(2000 BYTE)			constraint NN_UN_TEARPU_DESCRI not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TEARPU"."EARPU_EARPU" IS 'Estados posibles de un archivos de recaudo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TEARPU"."EARPU_DESCRI" IS 'Descripcion del estado de un archivos de recaudo';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TEARPU"  IS 'Tabla donde se almacenan los posibles estados de un archivo de recaudo';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TEARPU"
  add  constraint "PK_UN_TEARPU"
       primary key ("EARPU_EARPU")
       using index 
       tablespace &Indices;