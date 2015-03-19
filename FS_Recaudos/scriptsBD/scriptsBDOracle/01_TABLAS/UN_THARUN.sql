/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	  
	FUNCION: Almacena el historial de estados de un archivo ya unificado
		
******************************************************************************/

set define '&'
define Indices=TS_IFS_RECAUDOS_US

Prompt 
Prompt Creando tabla UN_THARUN
Prompt

CREATE TABLE "FS_RECAUDOS_US"."UN_THARUN" 
(	"HARUN_ARUN" 	NUMBER	 				constraint NN_UN_THARUN_ARUN not null,
	"HARUN_EARUN" 	VARCHAR2(20 BYTE)		constraint NN_UN_THARUN_EARUN not null, 
	"HARUN_FASIG" 	DATE					constraint NN_UN_THARUN_FASIG not null, 
	"HARUN_OBSER" 	VARCHAR2(2000 BYTE)		constraint NN_UN_THARUN_OBSER not null, 
	"HARUN_USUA" 	VARCHAR2(20 BYTE)		constraint NN_UN_THARUN_USUA not null
)storage( initial 10k  next 10k  pctincrease 0 )
/

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARUN"."HARUN_ARUN" IS 'Identificador del archivo plano de recaudo unificado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARUN"."HARUN_EARUN" IS 'Estado en el que se encuentra el archivo plano';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARUN"."HARUN_FASIG" IS 'Fecha de asignación del estado al proceso de unificacion relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARUN"."HARUN_OBSER" IS 'Observacion del historial de proceso de unificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARUN"."HARUN_USUA" IS 'Usuario que crea el historico de proceso de unificacion';

COMMENT ON TABLE "FS_RECAUDOS_US"."UN_THARUN"  IS 'Tabla que almacena el historial de estados de un archivo plano de recaudo unificado';


prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_THARUN"
add  constraint "PK_UN_THARUN"
   primary key ("HARUN_ARUN", "HARUN_EARUN", "HARUN_FASIG", "HARUN_USUA")
   using index 
   tablespace &Indices;
