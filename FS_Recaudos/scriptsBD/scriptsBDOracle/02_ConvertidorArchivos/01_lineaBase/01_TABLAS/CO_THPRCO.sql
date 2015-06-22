/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Almacena el historial de estados de proceso conversion de archivos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_THPRCO
Prompt

CREATE TABLE "FS_RECAUDOS_US"."CO_THPRCO" 
(	"HPRCO_PRCO" 	NUMBER	 				constraint NN_CO_THPRCO_PRCO not null,
	"HPRCO_EPRCO" 	VARCHAR2(20 BYTE)		constraint NN_CO_THPRCO_EPRCO not null, 
	"HPRCO_FASIG" 	DATE					constraint NN_CO_THPRCO_FASIG not null, 
	"HPRCO_OBSER" 	VARCHAR2(2000 BYTE)		constraint NN_CO_THPRCO_OBSER not null, 
	"HPRCO_USUA" 	VARCHAR2(20 BYTE)		constraint NN_CO_THPRCO_USUA not null
)storage( initial 10k  next 10k  pctincrease 0 )
/

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THPRCO"."HPRCO_PRCO" IS 'Identificador deL proceso de conversion relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THPRCO"."HPRCO_EPRCO" IS 'Estado en el que se encuentra el proceso de conversion relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THPRCO"."HPRCO_FASIG" IS 'Fecha de asignación del estado al proceso de conversion relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THPRCO"."HPRCO_OBSER" IS 'Observacion del historial de proceso de conversion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_THPRCO"."HPRCO_USUA" IS 'Usuario que crea el historico de proceso de conversion';

COMMENT ON TABLE "FS_RECAUDOS_US"."CO_THPRCO"  IS 'Tabla que almacena el historial de proceso de conversion de archivos con el estado en el que se encuentra cada uno de ellos';


prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_THPRCO"
add  constraint "PK_CO_THPRCO"
   primary key ("HPRCO_PRCO", "HPRCO_EPRCO", "HPRCO_FASIG", "HPRCO_USUA")
   using index 
   tablespace &Indices;
