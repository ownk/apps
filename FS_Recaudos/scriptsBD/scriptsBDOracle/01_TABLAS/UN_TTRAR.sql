/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	     
	FUNCION: Transformacionaciones de archivo realizada 
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TTRAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TTRAR" 
   (	"TRAR_TRAR" 		NUMBER					constraint NN_UN_TTRAR_TRAR not null,
		"TRAR_USUA" 		VARCHAR2(100 BYTE)		constraint NN_UN_TTRAR_USUA not null, 
		"TRAR_PRUN" 		NUMBER					constraint NN_UN_TTRAR_PRUN not null, 
		"TRAR_URL" 		    VARCHAR2(2000 BYTE)		constraint NN_UN_TTRAR_URL not null, 
		"TRAR_HASH" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TTRAR_HASH not null, 
		"TRAR_BYTES" 		VARCHAR2(100 BYTE)		constraint NN_UN_TTRAR_BYTES not null, 
		"TRAR_NOMBRE" 		VARCHAR2(1000 BYTE)		constraint NN_UN_TTRAR_NOMBRE not null, 
		"TRAR_OBSERV" 		VARCHAR2(2000 BYTE)		constraint NN_UN_TTRAR_OBSERV not null, 
		"TRAR_FCREA" 		DATE					constraint NN_UN_TTRAR_FCREA not null, 
		"TRAR_EXTENSION" 	VARCHAR2(2000 BYTE)		constraint NN_UN_TTRAR_EXTENSION not null,
        "TRAR_ARCHIVOS" 	NUMBER					constraint NN_UN_TTRAR_ARCHIVOS not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_TRAR" IS 'Identificador de archivo zip que contiene archivos a unificar';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_OBSERV" IS 'Observacion al subir un nuevo documento o version de anteproyecto por los estudiantes';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_FCREA" IS 'Fecha de Creacion del archivo en el sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_USUA" IS 'Usuario que ha subido el archivo al sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_PRUN" IS 'Proceso de unificacion de archivos al que esta asociado el archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_URL" IS 'Ruta de almacenamiento del documento en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_HASH" IS 'Resumen obtenido de una funcion hash del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_BYTES" IS 'Tamanho en bytes del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_NOMBRE" IS 'Nombre del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_EXTENSION" IS 'Extension original del archivo, la cual determina el tipo de documento almacenado';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_ARCHIVOS"  IS 'Cantidad de archivos que contiene el archivo zip';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TTRAR"  IS 'Tabla que almacena la informacion de los archivos ZIP que contienen los archivos a unificar';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TTRAR"
  add  constraint "PK_UN_TTRAR"
       primary key ("TRAR_TRAR")
       using index 
       tablespace &Indices;