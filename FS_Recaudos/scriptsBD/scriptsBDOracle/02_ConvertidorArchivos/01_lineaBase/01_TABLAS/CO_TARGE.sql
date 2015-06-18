/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Archivo plano de recaudo generado
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TARGE
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TARGE" 
   (	"ARGE_ARGE" 		NUMBER					constraint NN_CO_TARGE_ARGE not null,
		"ARGE_USUA" 		VARCHAR2(100 BYTE)		constraint NN_CO_TARGE_USUA not null,
		"ARGE_PRCO" 		NUMBER					constraint NN_CO_TARGE_PRCO not null, 			
		"ARGE_TPAR" 		VARCHAR2(100 BYTE)		constraint NN_CO_TARGE_TPAR not null,
		"ARGE_EARGE" 		VARCHAR2(20 BYTE)		constraint NN_CO_TARGE_EARGE not null,
		"ARGE_URL" 		    VARCHAR2(1000 BYTE)		constraint NN_CO_TARGE_URL not null, 
		"ARGE_HASH" 		VARCHAR2(2000 BYTE)		constraint NN_CO_TARGE_HASH not null, 
		"ARGE_BYTES" 		VARCHAR2(100 BYTE)		constraint NN_CO_TARGE_BYTES not null, 
		"ARGE_NOMBRE" 		VARCHAR2(1000 BYTE)		constraint NN_CO_TARGE_NOMBRE not null, 
		"ARGE_OBSERV" 		VARCHAR2(2000 BYTE)		constraint NN_CO_TARGE_OBSERV not null, 
		"ARGE_FCREA" 		DATE					constraint NN_CO_TARGE_FCREA not null, 
		"ARGE_EXTENSION" 	VARCHAR2(100 BYTE)		constraint NN_CO_TARGE_EXTENSION not null,
        "ARGE_REGISTROS" 	NUMBER					constraint NN_CO_TARGE_REGISTROS not null,
		"ARGE_AROR" 	    NUMBER					constraint NN_CO_TARGE_ARUN not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_ARGE" IS 'Identificador de archivo unificado que contiene registros de recaudo unificados por tipo de archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_OBSERV" IS 'Observacion al subir un nuevo documento o version de anteproyecto por los estudiantes';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_FCREA" IS 'Fecha de Creacion del archivo en el sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_USUA" IS 'Usuario que ha subido el archivo al sistema';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_PRCO" IS 'Proceso de unificacion de archivos al que esta asociado el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_EARGE" IS 'Estado del archivo unificado';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_TPAR" IS 'Tipo de archivo de recaudo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_URL" IS 'Ruta de almacenamiento del documento en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_HASH" IS 'Resumen obtenido de una funcion hash del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_BYTES" IS 'Tamanho en bytes del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_NOMBRE" IS 'Nombre del archivo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_EXTENSION" IS 'Extension original del archivo, la cual determina el tipo de documento almacenado';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_REGISTROS"  IS 'Cantidad de registros que contiene el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TARGE"."ARGE_AROR"  IS 'Archivo Original de Recaudo Asociado';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TARGE"  IS 'Tabla que almacena el archivo original por proceso y por tipo de archivo de recaudo';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TARGE"
  add  constraint "PK_CO_TARGE"
       primary key ("ARGE_ARGE")
       using index 
       tablespace &Indices;