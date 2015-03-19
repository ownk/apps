/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla AUT_TMODULO
Prompt

CREATE TABLE "FS_RECAUDOS_US"."AUT_TMODULO" 
   (	"MODULO_MODULO" 	VARCHAR2(20 BYTE)		constraint NN_AUT_TMODULO_MODULO not null,
		"MODULO_DESCRI" 	VARCHAR2(200 BYTE)		constraint NN_AUT_TMODULO_DESCRI not null, 
		"MODULO_NOMB" 		VARCHAR2(100 BYTE)		constraint NN_AUT_TMODULO_NOMB not null, 
		"MODULO_ORDEN" 		NUMBER					constraint NN_AUT_TMODULO_ORDEN not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TMODULO"."MODULO_MODULO" IS 'Identificador modulo';
 
COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TMODULO"."MODULO_DESCRI" IS 'Descripcion del modulo';
 
COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TMODULO"."MODULO_NOMB" IS 'Nombre del modulo';
 
COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TMODULO"."MODULO_ORDEN" IS 'Orden que tendra el modulo';
 
COMMENT ON TABLE "FS_RECAUDOS_US"."AUT_TMODULO"  IS 'Modulos del sistema';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."AUT_TMODULO"
  add  constraint "PK_AUT_TMODULO"
       primary key ("MODULO_MODULO")
       using index 
       tablespace &Indices;
