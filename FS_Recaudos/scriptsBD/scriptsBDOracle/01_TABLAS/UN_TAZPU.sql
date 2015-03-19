/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		Archivo zip asociado a cada proceso de unificacion de archivos
			
  OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TAZPU
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TAZPU" 
   (	"AZPU_AZPU" 		NUMBER					constraint NN_UN_TAZPU_AZPU not null,
		"AZPU_OBSERV" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TAZPU_OBSERV not null, 
		"AZPU_FCREA" 		DATE					constraint NN_UN_TAZPU_FCREA not null, 
		"AZPU_USUA" 		VARCHAR2(100 BYTE)		constraint NN_UN_TAZPU_USUA not null, 
		"AZPU_PRUN" 		NUMBER					constraint NN_UN_TAZPU_PRUN not null, 
		"AZPU_URL" 		    VARCHAR2(200 BYTE)		constraint NN_UN_TAZPU_URL not null, 
		"AZPU_HASH" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TAZPU_HASH not null, 
		"AZPU_BYTES" 		VARCHAR2(100 BYTE)		constraint NN_UN_TAZPU_BYTES not null, 
		"AZPU_NOMBRE" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TAZPU_NOMBRE not null, 
		"AZPU_EXTENSION" 	VARCHAR2(2000 BYTE)		constraint NN_UN_TAZPU_EXTENSION not null,
        "AZPU_ARCHIVOS" 	VARCHAR2(2000 BYTE)		constraint NN_UN_TAZPU_ARCHIVOS not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_AZPU" IS 'Identificador de archivo zip que contiene archivos a unificar';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_OBSERV" IS 'Observacion al subir un nuevo documento o version de anteproyecto por los estudiantes';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_FCREA" IS 'Fecha de Creacion del archivo en el sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_USUA" IS 'Usuario que ha subido el archivo al sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_PRUN" IS 'Proceso de unificacion de archivos al que esta asociado el archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_URL" IS 'Ruta de almacenamiento del documento en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_HASH" IS 'Resumen obtenido de una funcion hash del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_BYTES" IS 'Tamanho en bytes del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_NOMBRE" IS 'Nombre del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_EXTENSION" IS 'Extension original del archivo, la cual determina el tipo de documento almacenado';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TAZPU"."AZPU_ARCHIVOS"  IS 'Cantidad de archivos que contiene el archivo zip';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TAZPU"  IS 'Tabla que almacena la informacion de los archivos ZIP que contienen los archivos a unificar';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TAZPU"
  add  constraint "PK_UN_TAZPU"
       primary key ("AZPU_AZPU")
       using index 
       tablespace &Indices;


prompt Creacion de indice, llave unica

  
/