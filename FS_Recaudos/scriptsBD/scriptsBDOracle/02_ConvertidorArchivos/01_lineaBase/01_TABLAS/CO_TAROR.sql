/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Archivo plano de recaudo original por convertir
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TAROR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TAROR" 
   (	"AROR_AROR" 		NUMBER					constraint NN_CO_TAROR_AROR not null,
		"AROR_USUA" 		VARCHAR2(100 BYTE)		constraint NN_CO_TAROR_USUA not null,
		"AROR_PRCO" 		NUMBER					constraint NN_CO_TAROR_PRCO not null, 			
		"AROR_TPAR" 		VARCHAR2(100 BYTE)		constraint NN_CO_TAROR_TPAR not null,
		"AROR_EAROR" 		VARCHAR2(20 BYTE)		constraint NN_CO_TAROR_EAROR not null,
		"AROR_URL" 		    VARCHAR2(1000 BYTE)		constraint NN_CO_TAROR_URL not null, 
		"AROR_HASH" 		VARCHAR2(2000 BYTE)		constraint NN_CO_TAROR_HASH not null, 
		"AROR_BYTES" 		VARCHAR2(100 BYTE)		constraint NN_CO_TAROR_BYTES not null, 
		"AROR_NOMBRE" 		VARCHAR2(1000 BYTE)		constraint NN_CO_TAROR_NOMBRE not null, 
		"AROR_OBSERV" 		VARCHAR2(2000 BYTE)		constraint NN_CO_TAROR_OBSERV not null, 
		"AROR_FCREA" 		DATE					constraint NN_CO_TAROR_FCREA not null, 
		"AROR_EXTENSION" 	VARCHAR2(100 BYTE)		constraint NN_CO_TAROR_EXTENSION not null,
        "AROR_REGISTROS" 	NUMBER					constraint NN_CO_TAROR_REGISTROS not null,
		"AROR_ARUN" 	    NUMBER					constraint NN_CO_TAROR_ARUN not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_AROR" IS 'Identificador de archivo unificado que contiene registros de recaudo unificados por tipo de archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_OBSERV" IS 'Observacion al subir un nuevo documento o version de anteproyecto por los estudiantes';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_FCREA" IS 'Fecha de Creacion del archivo en el sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_USUA" IS 'Usuario que ha subido el archivo al sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_PRCO" IS 'Proceso de unificacion de archivos al que esta asociado el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_EAROR" IS 'Estado del archivo unificado';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_TPAR" IS 'Tipo de archivo de recaudo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_URL" IS 'Ruta de almacenamiento del documento en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_HASH" IS 'Resumen obtenido de una funcion hash del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_BYTES" IS 'Tamanho en bytes del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_NOMBRE" IS 'Nombre del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_EXTENSION" IS 'Extension original del archivo, la cual determina el tipo de documento almacenado';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_REGISTROS"  IS 'Cantidad de registros que contiene el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TAROR"."AROR_ARUN"  IS 'Archivo Unificado del Modulo Unificador';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TAROR"  IS 'Tabla que almacena el archivo original por proceso y por tipo de archivo de recaudo';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TAROR"
  add  constraint "PK_CO_TAROR"
       primary key ("AROR_AROR")
       using index 
       tablespace &Indices;