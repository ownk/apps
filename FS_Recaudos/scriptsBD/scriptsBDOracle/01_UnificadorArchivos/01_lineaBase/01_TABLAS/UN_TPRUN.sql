/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	  	    
	FUNCION: Proceso de Unificacion de archivos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TPRUN
Prompt

CREATE TABLE "FS_RECAUDOS_US"."UN_TPRUN" 
(	"PRUN_PRUN" 	NUMBER 		            constraint NN_UN_TPRUN_PRUN NOT NULL, 
	"PRUN_USUA" 	VARCHAR2(100 BYTE) 		constraint NN_UN_TPRUN_USUA NOT NULL, 
	"PRUN_EPRUN" 	VARCHAR2(20 BYTE)		constraint NN_UN_TPRUN_EPRUN NOT NULL,
	"PRUN_FCREA" 	DATE					constraint NN_UN_TPRUN_FCREA NOT NULL,
	"PRUN_OBSERV" 	VARCHAR2(2000 BYTE)		constraint NN_UN_TPRUN_OBSERV NOT NULL,
	"PRUN_FINI" 	DATE					constraint NN_UN_TPRUN_FINI NOT NULL,
	"PRUN_FFIN" 	DATE					constraint NN_UN_TPRUN_FFIN NOT NULL,
	"PRUN_ARCHIVOS" NUMBER					constraint NN_UN_TPRUN_ARCHIVOS NOT NULL
)  storage( initial 10k  next 10k  pctincrease 0 )
/  


COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_PRUN" IS 'Identificador unico de proceso de unificacion de archivos';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_USUA" IS 'Codigo de usuario con el que se crea el proceso';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_FCREA" IS 'Fecha de creacion del proceso';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_EPRUN" IS 'Estado actual del proceso';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_OBSERV" IS 'Observacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_FINI" IS 'Fecha desde la cual se hara la unificacion ';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_FFIN" IS 'Fecha fin hasta la cual se hara la unificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_ARCHIVOS" IS 'Cantidad de archivos zip a procesar';

COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TPRUN"  IS 'Tabla que almacena el proceso de unificacion de archivos de recaudo';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TPRUN"
  add  constraint "PK_UN_TPRUN"
       primary key ("PRUN_PRUN") 
       using index 
       tablespace &Indices;