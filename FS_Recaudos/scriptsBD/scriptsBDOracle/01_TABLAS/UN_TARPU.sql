/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		Archivo plano de recaudo a ser unificado
				
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TARPU
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TARPU" 
   (	"ARPU_ARPU" 		NUMBER					constraint NN_UN_TARPU_ARPU not null,
		"ARPU_TPAR"			VARCHAR2(100 BYTE)		constraint NN_UN_TARPU_TPAR not null,
		"ARPU_USUA" 		VARCHAR2(100 BYTE)		constraint NN_UN_TARPU_USUA not null,
		"ARPU_PRUN" 		NUMBER					constraint NN_UN_TARPU_PRUN not null, 			
		"ARPU_AZPU" 		NUMBER					constraint NN_UN_TARPU_AZPU not null,
		"ARPU_EARPU" 		VARCHAR2(20 BYTE)		constraint NN_UN_TARPU_EARPU not null,		
		"ARPU_URL" 		    VARCHAR2(2000 BYTE)		constraint NN_UN_TARPU_URL not null, 
		"ARPU_HASH" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TARPU_HASH not null, 
		"ARPU_BYTES" 		VARCHAR2(100 BYTE)		constraint NN_UN_TARPU_BYTES not null, 
		"ARPU_NOMBRE" 		VARCHAR2(1000 BYTE)		constraint NN_UN_TARPU_NOMBRE not null, 
		"ARPU_EXTENSION" 	VARCHAR2(100 BYTE)		constraint NN_UN_TARPU_EXTENSION not null,
		"ARPU_REGISTROS" 	NUMBER					constraint NN_UN_TARPU_REGISTROS not null,
		"ARPU_OBSERV" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TARPU_OBSERV not null, 
		"ARPU_FCREA" 		DATE					constraint NN_UN_TARPU_FCREA not null 
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_ARPU" IS 'Identificador de archivo zip que contiene archivos a unificar';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_OBSERV" IS 'Observacion al subir un nuevo documento o version de anteproyecto por los estudiantes';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_FCREA" IS 'Fecha de Creacion del archivo en el sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_USUA" IS 'Usuario que ha subido el archivo al sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_PRUN" IS 'Proceso de unificacion de archivos al que esta asociado el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_AZPU" IS 'Archivo ZIP al que se encuentra asociado el archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_URL" IS 'Ruta de almacenamiento del documento en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_HASH" IS 'Resumen obtenido de una funcion hash del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_BYTES" IS 'Tamanho en bytes del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_NOMBRE" IS 'Nombre del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_EXTENSION" IS 'Extension original del archivo, la cual determina el tipo de documento almacenado';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_TPAR" IS 'Tipo de archivo de recaudo';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_REGISTROS"  IS 'Cantidad de registros que contiene el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPU"."ARPU_EARPU"  IS 'Estado del archivo';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TARPU"  IS 'Tabla que almacena la informacion de los archivos de recaudo que contienen los registros a unificar';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TARPU"
  add  constraint "PK_UN_TARPU"
       primary key ("ARPU_ARPU")
       using index 
       tablespace &Indices;
