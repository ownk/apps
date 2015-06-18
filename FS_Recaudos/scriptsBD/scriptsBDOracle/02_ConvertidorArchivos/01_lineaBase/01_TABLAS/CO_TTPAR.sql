/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Tipos de archivo de recaudo a convertir
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TTPAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TTPAR" 
   (	"TPAR_TPAR" 		    VARCHAR2(100 BYTE)			constraint NN_CO_TTPAR_TPAR     not null, 
		"TPAR_DESCRI" 		    VARCHAR2(2000 BYTE)			constraint NN_CO_TTPAR_DESCRI   not null,
        "TPAR_PLAN_GENERICO" 	NUMBER					    constraint NN_CO_TTPAR_PLAN     not null,
		"TPAR_CTA_RECAUDO" 		VARCHAR2(2000 BYTE)		    constraint NN_CO_TTPAR_CTA      not null, 
		"TPAR_USUA" 		    VARCHAR2(100 BYTE)			constraint NN_CO_TTPAR_USUA     not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_TPAR" IS 'Identificador unico de tipo de archivo recaudo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_DESCRI" IS 'Descripcion del tipo de archivo';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_USUA" IS 'Usuario que crea el tipo de archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_PLAN_GENERICO" IS 'Plan generico SIFI asociado al tipo de archivo ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_CTA_RECAUDO" IS 'Cuenta recaudadora asociada al tipo de archivo ';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TTPAR"  IS 'Tabla donde se almacenan los tipos de archivos de recaudo a convertir';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TTPAR"
  add  constraint "PK_CO_TTPAR"
       primary key ("TPAR_TPAR")
       using index 
       tablespace &Indices;
	   
