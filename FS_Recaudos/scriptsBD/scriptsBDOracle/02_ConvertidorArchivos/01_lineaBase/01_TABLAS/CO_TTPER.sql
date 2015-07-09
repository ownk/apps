/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Tipos de errores del contenido del archivo de recaudos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TTPER
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TTPER" 
   (	"TPER_TPER" 		    NUMBER          			constraint NN_CO_TTPER_TPER     not null, 
		"TPER_DESCRI" 		    VARCHAR2(2000 BYTE)			constraint NN_CO_TTPER_DESCRI   not null,
        "TPER_CODIGO" 	        VARCHAR2(100  BYTE)			constraint NN_CO_TTPER_CODIGO   not null,
        "TPER_COLOR" 	        VARCHAR2(100   BYTE)			constraint NN_CO_TTPER_PLAN     not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPER"."TPER_TPER" IS 'Identificador unico de tipo de error';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPER"."TPER_DESCRI" IS 'Descripcion del tipo de error';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPER"."TPER_CODIGO" IS 'Codigo interno asignado por la aplicacion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPER"."TPER_COLOR" IS 'Color para demarcar el tipo de error utilizada';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TTPER"  IS 'Tabla donde se almacenan los tipos de error encontrados en el archivo de recaudo';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TTPER"
  add  constraint "PK_CO_TTPER"
       primary key ("TPER_TPER")
       using index 
       tablespace &Indices;
	   
