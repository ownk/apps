/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Homologacion Tipos de recaudo del comparador
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CP_THTPR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CP_THTPR" 
   (	"HTPR_FRECA_ORIG" 		    VARCHAR2(100 BYTE)			constraint NN_CP_THTPR_FRECA_ORIG   not null, 
        "HTPR_FRECA_NORM" 		    VARCHAR2(100 BYTE)          constraint NN_CP_THTPR_FRECA_NORM   not null,
		"HTPR_DESCRI" 		        VARCHAR2(2000 BYTE)			constraint NN_CP_THTPR_DESCRI       not null,
        "HTPR_COLOR" 	            VARCHAR2(100  BYTE)			constraint NN_CP_THTPR_COLOR        not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_THTPR"."HTPR_FRECA_ORIG" IS 'Identificador unico de tipo de recaudo de archivo internet';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_THTPR"."HTPR_FRECA_NORM" IS 'Identificador unico de tipo de recaudo de archivo plano';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_THTPR"."HTPR_DESCRI" IS 'Descripcion';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_THTPR"."HTPR_COLOR" IS 'Color para demarcar';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CP_THTPR"  IS 'Tipos de recaudo utilizados por el comparador';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CP_THTPR"
  add  constraint "PK_CP_THTPR"
       primary key ("HTPR_FRECA_ORIG")
       using index 
       tablespace &Indices;
	   
