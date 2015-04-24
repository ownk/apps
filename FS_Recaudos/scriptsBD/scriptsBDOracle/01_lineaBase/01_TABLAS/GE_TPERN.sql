/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla GE_TPERN
Prompt

CREATE TABLE "FS_RECAUDOS_US"."GE_TPERN" 
(	"PERN_PERN" NUMBER					CONSTRAINT NN_GE_TPERN_PERN NOT NULL, 
	"PERN_NOMB" VARCHAR2(50 BYTE)		CONSTRAINT NN_GE_TPERN_NOMB NOT NULL, 
	"PERN_PAPELL" VARCHAR2(20 BYTE)		CONSTRAINT NN_GE_TPERN_PAPELL NOT NULL, 
	"PERN_SAPELL" VARCHAR2(20 BYTE)		, 
	"PERN_TDOC" NUMBER					CONSTRAINT NN_GE_TPERN_TDOC NOT NULL, 
	"PERN_DOC" VARCHAR2(50 BYTE)        CONSTRAINT NN_GE_TPERN_DOC NOT NULL
)storage( initial 10k  next 10k  pctincrease 0 )
/  


COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TPERN"."PERN_PERN" IS 'identificacion de una persona registrada en el sistema';

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TPERN"."PERN_NOMB" IS 'nombres de la persona que se inscribira en el sistema';

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TPERN"."PERN_PAPELL" IS 'primer apellido de la persona que se inscribira en el sistema';

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TPERN"."PERN_SAPELL" IS 'segundo apellido de la persona que se inscribira en el sistema';

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TPERN"."PERN_TDOC" IS 'id del tipo de documento de la persona que se inscribira en el sistema';

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TPERN"."PERN_DOC" IS 'numero de documento de identificacion de la persona que se inscribira en el sistema';

COMMENT ON TABLE "FS_RECAUDOS_US"."GE_TPERN"  IS 'Informacion basica de las personas que sean usuarios del FS_RECAUDOS';


prompt Llave Primaria

alter table "FS_RECAUDOS_US"."GE_TPERN"
  add  constraint "PK_GE_TPERN"
       primary key ("PERN_PERN")
       using index 
       tablespace &Indices;
