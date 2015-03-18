/******************************************************************************
VERSION:		
FECHA:		  
AUTOR:		  
DERECHOS:		
TABLA:	    
FUNCION:		almacena el historial de estados proceso unificacion de archivos
		
OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IFS_RECAUDOS_US

Prompt 
Prompt Creando tabla UN_THPRUN
Prompt

CREATE TABLE "FS_RECAUDOS_US"."UN_THPRUN" 
(	"HPRUN_PRUN" 	NUMBER	 				constraint NN_UN_THPRUN_HPRUN not null,
	"HPRUN_EPRUN" 	VARCHAR2(20 BYTE)		constraint NN_UN_THPRUN_EPRUN not null, 
	"HPRUN_FASIG" 	DATE					constraint NN_UN_THPRUN_FASIG not null, 
	"HPRUN_OBSER" 	VARCHAR2(2000 BYTE)		constraint NN_UN_THPRUN_OBSER not null, 
	"HPRUN_USUA" 	VARCHAR2(20 BYTE)		constraint NN_UN_THPRUN_USUA not null
)storage( initial 10k  next 10k  pctincrease 0 )
/

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THPRUN"."HPRUN_PRUN" IS 'Identificador deL proceso de unificacion relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THPRUN"."HPRUN_EPRUN" IS 'estado en el que se encuentra el proceso de unificacion relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THPRUN"."HPRUN_FASIG" IS 'Fecha de asignación del estado al proceso de unificacion relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THPRUN"."HPRUN_OBSER" IS 'Observacion del historial de proceso de unificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_THPRUN"."HPRUN_USUA" IS 'Usuario que crea el historico de proceso de unificacion';

COMMENT ON TABLE "FS_RECAUDOS_US"."UN_THPRUN"  IS 'Tabla que almacena el historial de proceso de unificacion de archivos con el estado en el que se encuentra cada uno de ellos';


prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_THPRUN"
add  constraint "PK_UN_THPRUN"
   primary key ("HPRUN_PRUN", "HPRUN_EPRUN", "HPRUN_FASIG", "HPRUN_USUA")
   using index 
   tablespace &Indices;
