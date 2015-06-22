/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	  
	FUNCION: Almacena el historial de estados de un archivo convertidos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_THARGE
Prompt

CREATE TABLE "FS_RECAUDOS_US"."CO_THARGE" 
(	"HARGE_ARGE" 	NUMBER	 				constraint NN_CO_THARGE_ARGE not null,
	"HARGE_EARGE" 	VARCHAR2(20 BYTE)		constraint NN_CO_THARGE_EARGE not null, 
	"HARGE_FASIG" 	DATE					constraint NN_CO_THARGE_FASIG not null, 
	"HARGE_OBSER" 	VARCHAR2(2000 BYTE)		constraint NN_CO_THARGE_OBSER not null, 
	"HARGE_USUA" 	VARCHAR2(20 BYTE)		constraint NN_CO_THARGE_USUA not null
)storage( initial 10k  next 10k  pctincrease 0 )
/

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THARGE"."HARGE_ARGE" IS 'Identificador del archivo plano de recaudo convertido';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THARGE"."HARGE_EARGE" IS 'Estado en el que se encuentra el archivo plano';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THARGE"."HARGE_FASIG" IS 'Fecha de asignación del estado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THARGE"."HARGE_OBSER" IS 'Observacion del historial';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THARGE"."HARGE_USUA" IS 'Usuario que crea el historico';

COMMENT ON TABLE "FS_RECAUDOS_US"."CO_THARGE"  IS 'Tabla que almacena el historial de estados de un archivo plano de recaudo convertidos';


prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_THARGE"
add  constraint "PK_CO_THARGE"
   primary key ("HARGE_ARGE", "HARGE_EARGE", "HARGE_FASIG", "HARGE_USUA")
   using index 
   tablespace &Indices;
