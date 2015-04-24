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
Prompt Creando tabla GE_TTDOC
Prompt

CREATE TABLE "FS_RECAUDOS_US"."GE_TTDOC" 
(	"TDOC_TDOC" NUMBER				CONSTRAINT NN_GE_TTDOC_TDOC NOT NULL, 
	"TDOC_DOC" VARCHAR2(20 BYTE)    CONSTRAINT NN_GE_TTDOC_DOC NOT NULL
) 
storage( initial 10k  next 10k  pctincrease 0 )
/

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TTDOC"."TDOC_TDOC" IS 'identificador del tipo de documento de identificación inscrito';

COMMENT ON COLUMN "FS_RECAUDOS_US"."GE_TTDOC"."TDOC_DOC" IS 'Nombre del tipo de documento inscrito: Tarjeta de Identidad, Cedula de Ciudadanía, Cedula de Extranjería';

COMMENT ON TABLE "FS_RECAUDOS_US"."GE_TTDOC"  IS 'Tabla que almacena los diferentes tipos de documentos con que se puede registrar un usuario';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."GE_TTDOC"
  add  constraint "PK_GE_TTDOC"
       primary key ("TDOC_TDOC") 
       using index 
       tablespace &Indices;
