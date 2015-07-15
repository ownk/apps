/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Tipos de archivo de recaudo a comparar
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CP_TTPAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CP_TTPAR" 
   (	"TPAR_TPAR" 		    VARCHAR2(100 BYTE)			constraint NN_CP_TTPAR_TPAR             not null, 
		"TPAR_DESCRI" 		    VARCHAR2(2000 BYTE)			constraint NN_CP_TTPAR_DESCRI           not null,
        "TPAR_NOMB_CPAR" 		VARCHAR2(2000)			    constraint NN_CP_TTPAR_NOMB_CPAR        not null,
        "TPAR_USUA" 		    VARCHAR2(100 BYTE)			constraint NN_CP_TTPAR_USUA             not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TTPAR"."TPAR_TPAR" IS 'Identificador unico de tipo de archivo recaudo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TTPAR"."TPAR_DESCRI" IS 'Descripcion del tipo de archivo';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TTPAR"."TPAR_USUA" IS 'Usuario que crea el tipo de archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TTPAR"."TPAR_NOMB_CPAR" IS 'refijo de nombre de archivo convertido. Eje: Cta_NNNNyyyy NNNN:Ultimos Digitos Cuenta, yyyy: año en el cual se esta generando ';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."CP_TTPAR"  IS 'Tabla donde se almacenan los tipos de archivos de recaudo a comparar';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CP_TTPAR"
  add  constraint "PK_CP_TTPAR"
       primary key ("TPAR_TPAR")
       using index 
       tablespace &Indices;
	   
