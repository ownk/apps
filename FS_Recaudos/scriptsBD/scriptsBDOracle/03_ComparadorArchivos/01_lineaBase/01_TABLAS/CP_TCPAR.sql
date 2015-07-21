/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Comparacion de Archivos de Recaudo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CP_TCPAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CP_TCPAR" 
   (	"CPAR_CPAR" 		NUMBER					constraint NN_CP_TCPAR_CPAR not null,
		"CPAR_USUA" 		VARCHAR2(100 BYTE)		constraint NN_CP_TCPAR_USUA not null,
		"CPAR_TPAR" 		VARCHAR2(100 BYTE)		constraint NN_CP_TCPAR_TPAR not null,
        "CPAR_OBSERV" 		VARCHAR2(2000 BYTE)		constraint NN_CP_TCPAR_OBSERV not null,
		"CPAR_ECPAR" 		VARCHAR2(20 BYTE)		constraint NN_CP_TCPAR_ECPAR not null,
		"CPAR_FCREA" 		DATE					constraint NN_CP_TCPAR_FCREA not null, 
        "CPAR_FINI" 		DATE					constraint NN_CP_TCPAR_FINI not null, 
        "CPAR_FFIN" 		DATE					constraint NN_CP_TCPAR_FFIN not null, 
        "CPAR_ARUN" 	    NUMBER					constraint NN_CP_TCPAR_ARUN not null,
        "CPAR_IBSC" 	    NUMBER					constraint NN_CP_TCPAR_IBSC not null,
        "CPAR_ARUN_CTA" 	VARCHAR2(100 BYTE)  	constraint NN_CP_TCPAR_ARUN_CTA not null,
        "CPAR_IBSC_CTA" 	VARCHAR2(100 BYTE)		constraint NN_CP_TCPAR_IBSC_CTA not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_CPAR" IS 'Identificador de comparacion que contiene registros de recaudo a comparar';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_OBSERV" IS 'Observacion al generar la comparacion';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_FCREA" IS 'Fecha de Creacion del archivo en el sistema';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_FINI" IS 'Fecha de Inicio de comparacion';
      
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_FFIN" IS 'Fecha de Fin de comparacion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_USUA" IS 'Usuario que ha subido el archivo al sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_TPAR" IS 'Tipo de archivo de recaudo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_ARUN"  IS 'Archivo Unificado del Modulo Unificador';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_IBSC"  IS 'Archivo Internet Banco Caja Social';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_ARUN_CTA"  IS 'Ultimos 4 digitos Cuenta bancaria asociada al archivo unificado';
      
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TCPAR"."CPAR_IBSC_CTA"  IS 'Ultimos 4 digitos Cuenta bancaria asociada al archivo internet';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CP_TCPAR"  IS 'Comparacion de Archivos de Recaudo';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CP_TCPAR"
  add  constraint "PK_CP_TCPAR"
       primary key ("CPAR_CPAR")
       using index 
       tablespace &Indices;