/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		
			
  OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla GE_TOPER
Prompt

CREATE TABLE "FS_RECAUDOS_US"."GE_TOPER" 
(	"OPER_OPER" VARCHAR2(20 BYTE)	CONSTRAINT NN_GE_TOPER_OPER NOT NULL, 
	"OPER_PERN" NUMBER				CONSTRAINT NN_GE_TOPER_PERN NOT NULL, 
	"OPER_USUA" VARCHAR2(100 BYTE)  
)    storage( initial 10k  next 10k  pctincrease 0 )
/  


COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TOPER"."OPER_OPER" IS 'es el valor del código en al app';

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TOPER"."OPER_PERN" IS 'identificador como persona en el sistema';

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TOPER"."OPER_USUA" IS 'Usuario asociado al persona operativa';

COMMENT ON TABLE "FS_RECAUDOS_US"."GE_TOPER"  IS 'Tabla que almacena la información propia de un usuario que cumple con las caracteristicas del ROL de OPERATIVO';


prompt Llave Primaria

alter table "FS_RECAUDOS_US"."GE_TOPER"
  add  constraint "PK_GE_TOPER"
       primary key ("OPER_OPER")
       using index 
       tablespace &Indices;
