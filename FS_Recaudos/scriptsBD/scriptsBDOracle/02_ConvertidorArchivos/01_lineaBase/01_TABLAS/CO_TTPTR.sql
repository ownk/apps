/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Tipos de transformaciones del contenido del archivo de recaudos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TTPTR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TTPTR" 
   (	"TPTR_TPTR" 		    NUMBER          			constraint NN_CO_TTPTR_TPTR     not null, 
		"TPTR_DESCRI" 		    VARCHAR2(2000 BYTE)			constraint NN_CO_TTPTR_DESCRI   not null,
        "TPTR_COLOR" 	        VARCHAR2(10)			    constraint NN_CO_TTPTR_PLAN     not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPTR"."TPTR_TPTR" IS 'Identificador unico de tipo de transformacion';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPTR"."TPTR_DESCRI" IS 'Descripcion del tipo de transformacion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPTR"."TPTR_COLOR" IS 'Color para demarcar el tipo de transformacion utilizada';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TTPTR"  IS 'Tabla donde se almacenan los tipos de transformacion realizadas al archivo de recaudo';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TTPTR"
  add  constraint "PK_CO_TTPTR"
       primary key ("TPTR_TPTR")
       using index 
       tablespace &Indices;
	   
