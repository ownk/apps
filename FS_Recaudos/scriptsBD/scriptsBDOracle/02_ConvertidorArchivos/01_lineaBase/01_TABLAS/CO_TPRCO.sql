/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	  	    
	FUNCION: Proceso de Conversion de archivos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TPRCO
Prompt

CREATE TABLE "FS_RECAUDOS_US"."CO_TPRCO" 
(	"PRCO_PRCO" 	NUMBER 		            constraint NN_CO_TPRCO_PRCO NOT NULL, 
	"PRCO_USUA" 	VARCHAR2(100 BYTE) 		constraint NN_CO_TPRCO_USUA NOT NULL, 
	"PRCO_EPRCO" 	VARCHAR2(20 BYTE)		constraint NN_CO_TPRCO_EPRCO NOT NULL,
	"PRCO_FCREA" 	DATE					constraint NN_CO_TPRCO_FCREA NOT NULL,
	"PRCO_OBSERV" 	VARCHAR2(2000 BYTE)		constraint NN_CO_TPRCO_OBSERV NOT NULL,
	"PRCO_FINI" 	DATE					constraint NN_CO_TPRCO_FINI NOT NULL,
	"PRCO_FFIN" 	DATE					constraint NN_CO_TPRCO_FFIN NOT NULL,
	"PRCO_ARCHIVOS" NUMBER					constraint NN_CO_TPRCO_ARCHIVOS NOT NULL,
    "PRCO_PRUN"     NUMBER					constraint NN_CO_TPRCO_PRUN NOT NULL
)  storage( initial 10k  next 10k  pctincrease 0 )
/  


COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_PRCO" IS 'Identificador unico de proceso de unificacion de archivos';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_USUA" IS 'Codigo de usuario con el que se crea el proceso';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_FCREA" IS 'Fecha de creacion del proceso';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_EPRCO" IS 'Estado actual del proceso';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_OBSERV" IS 'Observacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_FINI" IS 'Fecha desde la cual se hara la unificacion ';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_FFIN" IS 'Fecha fin hasta la cual se hara la unificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_ARCHIVOS" IS 'Cantidad de archivos de recaudo a procesar';

COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCO"."PRCO_PRUN" IS 'Proceso de unificacion asociado';

COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TPRCO"  IS 'Tabla que almacena el proceso de conversion de archivos de recaudo';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TPRCO"
  add  constraint "PK_CO_TPRCO"
       primary key ("PRCO_PRCO") 
       using index 
       tablespace &Indices;