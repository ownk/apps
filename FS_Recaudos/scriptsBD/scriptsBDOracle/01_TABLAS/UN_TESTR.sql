/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Tipos de estructura de archivo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TESTR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TESTR" 
   (	"ESTR_ESTR" 		NUMBER						constraint NN_UN_TESTR_ESTR not null, 
		"ESTR_DESCRI" 		VARCHAR2(2000 BYTE)			constraint NN_UN_TESTR_DESCRI not null,
		"ESTR_USUA" 		VARCHAR2(100 BYTE)			constraint NN_UN_TESTR_USUA not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TESTR"."ESTR_ESTR" IS 'Identificador unico de tipo de estructura archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TESTR"."ESTR_DESCRI" IS 'Descripcion del tipo de estructura';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TESTR"."ESTR_USUA" IS 'Usuario que crea el tipo de estructura';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TESTR"  IS 'Tabla donde se almacenan los tipos de estructuras de archivo';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TESTR"
  add  constraint "PK_UN_TESTR"
       primary key ("ESTR_ESTR")
       using index 
       tablespace &Indices;
	   
