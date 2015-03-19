/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		Archivo plano de recaudo a ser unificado
			
  OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TARUN
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TARUN" 
   (	"ARUN_ARUN" 		NUMBER					constraint NN_UN_TARUN_ARUN not null,
		"ARUN_OBSERV" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TARUN_OBSERV not null, 
		"ARUN_FCREA" 		DATE					constraint NN_UN_TARUN_FCREA not null, 
		"ARUN_USUA" 		VARCHAR2(100 BYTE)		constraint NN_UN_TARUN_USUA not null,
		"ARUN_PRUN" 		NUMBER					constraint NN_UN_TARUN_PRUN not null, 			
		"ARUN_AZPU" 		NUMBER					constraint NN_UN_TARUN_AZPU not null, 
		"ARUN_URL" 		    VARCHAR2(1000 BYTE)		constraint NN_UN_TARUN_URL not null, 
		"ARUN_HASH" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TARUN_HASH not null, 
		"ARUN_BYTES" 		VARCHAR2(100 BYTE)		constraint NN_UN_TARUN_BYTES not null, 
		"ARUN_NOMBRE" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TARUN_NOMBRE not null, 
		"ARUN_EXTENSION" 	VARCHAR2(100 BYTE)		constraint NN_UN_TARUN_EXTENSION not null,
		"ARUN_TPAR"			VARCHAR2(100 BYTE)		constraint NN_UN_TARUN_TPAR not null,
        "ARUN_REGISTROS" 	NUMBER					constraint NN_UN_TARUN_REGISTROS not null,
		"ARUN_EARUN" 		NUMBER					constraint NN_UN_TARUN_EARUN not null
		
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_ARUN" IS 'Identificador de archivo zip que contiene archivos a unificar';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_OBSERV" IS 'Observacion al subir un nuevo documento o version de anteproyecto por los estudiantes';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_FCREA" IS 'Fecha de Creacion del archivo en el sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_USUA" IS 'Usuario que ha subido el archivo al sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_PRUN" IS 'Proceso de unificacion de archivos al que esta asociado el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_AZPU" IS 'Archivo ZIP al que se encuentra asociado el archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_URL" IS 'Ruta de almacenamiento del documento en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_HASH" IS 'Resumen obtenido de una funcion hash del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_BYTES" IS 'Tamanho en bytes del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_NOMBRE" IS 'Nombre del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_EXTENSION" IS 'Extension original del archivo, la cual determina el tipo de documento almacenado';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_TPAR" IS 'Tipo de archivo de recaudo';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_REGISTROS"  IS 'Cantidad de registros que contiene el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARUN"."ARUN_EARUN"  IS 'Estado del archivo';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TARUN"  IS 'Tabla que almacena la informacion de los archivos de recaudo que contienen los registros a unificar';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TARUN"
  add  constraint "PK_UN_TARUN"
       primary key ("ARUN_ARUN")
       using index 
       tablespace &Indices;


prompt Creacion de indice, llave unica

  
/