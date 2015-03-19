/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	  
	FUNCION: Almacena el historial de estados de un archivo por unificar
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_THARPU
Prompt

CREATE TABLE "FS_RECAUDOS_US"."UN_THARPU" 
(	"HARPU_ARPU" 	NUMBER	 				constraint NN_UN_THARPU_ARPU not null,
	"HARPU_EARPU" 	VARCHAR2(20 BYTE)		constraint NN_UN_THARPU_EARPU not null, 
	"HARPU_FASIG" 	DATE					constraint NN_UN_THARPU_FASIG not null, 
	"HARPU_OBSER" 	VARCHAR2(2000 BYTE)		constraint NN_UN_THARPU_OBSER not null, 
	"HARPU_USUA" 	VARCHAR2(20 BYTE)		constraint NN_UN_THARPU_USUA not null
)storage( initial 10k  next 10k  pctincrease 0 )
/

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARPU"."HARPU_ARPU" IS 'Identificador del archivo plano de recaudo a unificar';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARPU"."HARPU_EARPU" IS 'Estado en el que se encuentra el archivo plano';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARPU"."HARPU_FASIG" IS 'Fecha de asignación del estado al proceso de unificacion relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARPU"."HARPU_OBSER" IS 'Observacion del historial de proceso de unificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THARPU"."HARPU_USUA" IS 'Usuario que crea el historico de proceso de unificacion';

COMMENT ON TABLE "FS_RECAUDOS_US"."UN_THARPU"  IS 'Tabla que almacena el historial de estados de un archivo plano de recaudo';


prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_THARPU"
add  constraint "PK_UN_THARPU"
   primary key ("HARPU_ARPU", "HARPU_EARPU", "HARPU_FASIG", "HARPU_USUA")
   using index 
   tablespace &Indices;
