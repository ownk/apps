/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	  
	FUNCION: Almacena el historial de estados de un archivo original por convertir
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_THAROR
Prompt

CREATE TABLE "FS_RECAUDOS_US"."CO_THAROR" 
(	"HAROR_AROR" 	NUMBER	 				constraint NN_CO_THAROR_AROR not null,
	"HAROR_EAROR" 	VARCHAR2(20 BYTE)		constraint NN_CO_THAROR_EAROR not null, 
	"HAROR_FASIG" 	DATE					constraint NN_CO_THAROR_FASIG not null, 
	"HAROR_OBSER" 	VARCHAR2(2000 BYTE)		constraint NN_CO_THAROR_OBSER not null, 
	"HAROR_USUA" 	VARCHAR2(20 BYTE)		constraint NN_CO_THAROR_USUA not null
)storage( initial 10k  next 10k  pctincrease 0 )
/

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THAROR"."HAROR_AROR" IS 'Identificador del archivo plano de recaudo a convertir';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THAROR"."HAROR_EAROR" IS 'Estado en el que se encuentra el archivo plano';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THAROR"."HAROR_FASIG" IS 'Fecha de asignación del estado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THAROR"."HAROR_OBSER" IS 'Observacion del historial';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THAROR"."HAROR_USUA" IS 'Usuario que crea el historico';

COMMENT ON TABLE "FS_RECAUDOS_US"."CO_THAROR"  IS 'Tabla que almacena el historial de estados de un archivo plano de recaudo a convertir';


prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_THAROR"
add  constraint "PK_CO_THAROR"
   primary key ("HAROR_AROR", "HAROR_EAROR", "HAROR_FASIG", "HAROR_USUA")
   using index 
   tablespace &Indices;
