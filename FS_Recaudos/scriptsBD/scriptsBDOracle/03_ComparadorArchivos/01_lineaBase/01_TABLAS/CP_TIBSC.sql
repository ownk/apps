/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		Archivo Internet BSC
				
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CP_TIBSC
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CP_TIBSC" 
   (	"IBSC_IBSC" 		NUMBER					constraint NN_CP_TIBSC_IBSC not null,
		"IBSC_USUA" 		VARCHAR2(100 BYTE)		constraint NN_CP_TIBSC_USUA not null,
		"IBSC_ARUN" 		NUMBER					constraint NN_CP_TIBSC_ARUN not null,
		"IBSC_URL" 		    VARCHAR2(2000 BYTE)		constraint NN_CP_TIBSC_URL not null, 
		"IBSC_HASH" 		VARCHAR2(2000 BYTE)		constraint NN_CP_TIBSC_HASH not null, 
		"IBSC_BYTES" 		VARCHAR2(100 BYTE)		constraint NN_CP_TIBSC_BYTES not null, 
		"IBSC_NOMBRE" 		VARCHAR2(1000 BYTE)		constraint NN_CP_TIBSC_NOMBRE not null, 
		"IBSC_EXTENSION" 	VARCHAR2(100 BYTE)		constraint NN_CP_TIBSC_EXTENSION not null,
		"IBSC_OBSERV" 		VARCHAR2(2000 BYTE)		constraint NN_CP_TIBSC_OBSERV not null, 
		"IBSC_FCREA" 		DATE					constraint NN_CP_TIBSC_FCREA not null 
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_IBSC" IS 'Identificador de archivo zip que contiene archivos a unificar';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_OBSERV" IS 'Observacion al subir un nuevo documento o version de anteproyecto por los estudiantes';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_FCREA" IS 'Fecha de Creacion del archivo en el sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_USUA" IS 'Usuario que ha subido el archivo al sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_ARUN" IS 'Archivo Unificado al cual esta asociado';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_URL" IS 'Ruta de almacenamiento del documento en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_HASH" IS 'Resumen obtenido de una funcion hash del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_BYTES" IS 'Tamanho en bytes del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_NOMBRE" IS 'Nombre del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TIBSC"."IBSC_EXTENSION" IS 'Extension original del archivo, la cual determina el tipo de documento almacenado';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CP_TIBSC"  IS 'Archivo Internet BSC';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CP_TIBSC"
  add  constraint "PK_CP_TIBSC"
       primary key ("IBSC_IBSC")
       using index 
       tablespace &Indices;
