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
   (	"TRAR_TRAR" 			NUMBER					constraint NN_UN_TTRAR_TRAR not null,
		"TRAR_TPAR"				VARCHAR2(100 BYTE)		constraint NN_UN_TTRAR_TPAR not null,
		"TRAR_USUA" 			VARCHAR2(100 BYTE)		constraint NN_UN_TTRAR_USUA not null,
		"TRAR_PRUN" 			NUMBER					constraint NN_UN_TTRAR_PRUN not null, 			
		"TRAR_URL_FILE_INI" 	VARCHAR2(2000 BYTE)		constraint NN_UN_TTRAR_URL_INI not null, 
		"TRAR_URL_FILE_FIN" 	VARCHAR2(2000 BYTE)		constraint NN_UN_TTRAR_URL_FIN not null, 
		"TRAR_BYTES_FILE_INI" 	NUMBER					constraint NN_UN_TTRAR_BYTES_I not null,
		"TRAR_BYTES_FILE_FIN" 	NUMBER					constraint NN_UN_TTRAR_BYTES_F not null,
		"TRAR_REG_FILE_INI" 	NUMBER					constraint NN_UN_TTRAR_REG_I not null,
		"TRAR_REG_FILE_FIN" 	NUMBER					constraint NN_UN_TTRAR_REG_F not null,
		"TRAR_OBSERV" 			VARCHAR2(2000 BYTE)		constraint NN_UN_TTRAR_OBSERV not null, 
		"TRAR_FCREA" 			DATE					constraint NN_UN_TTRAR_FCREA not null 
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_TRAR" IS 'Identificador de la transformacion realizada ';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_TPAR" IS 'Tipo de archivo recaudo Transformado';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_USUA" IS 'Usuario que realiza el proceso';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_PRUN" IS 'Proceso de unificacion de archivos al que esta asociado la trasnformacion';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_URL_FILE_INI" IS 'Ruta de almacenamiento del documento inicial en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_URL_FILE_FIN" IS 'Ruta de almacenamiento del documento transformado en el servidor';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_BYTES_FILE_INI" IS 'Tamanho en bytes del archivo inicial';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_BYTES_FILE_FIN" IS 'Tamanho en bytes del archivo transformado';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_REG_FILE_INI" IS 'Total del registros del archivo origin';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_REG_FILE_FIN" IS 'Total de registros del archivo transformado';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_OBSERV" IS 'Observacion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TTRAR"."TRAR_FCREA"  IS 'Fecha en la que se realiza la transformacion';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TTRAR"  IS 'Tabla que almacena la informacion de las transformaciones  de archivos realizadas ';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TTRAR"
  add  constraint "PK_UN_TTRAR"
       primary key ("TRAR_TRAR")
       using index 
       tablespace &Indices;