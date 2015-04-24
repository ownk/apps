/******************************************************************************
  VERSION:		
  FECHA:		  
  AUTOR:		  
  DERECHOS:		
  TABLA:	    
  FUNCION:		Archivo plano de recaudo a ser unificado repetido
				
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TARPR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."UN_TARPR" 
   (	"ARPR_ARPR" 		NUMBER					constraint NN_UN_TARPR_ARPR not null,
		"ARPR_PRUN" 		NUMBER					constraint NN_UN_TARPR_PRUN not null, 			
		"ARPR_ARUN" 		NUMBER					constraint NN_UN_TARPR_ARPU not null,
        "ARPR_ARPU" 		NUMBER					constraint NN_UN_TARPR_AZPU not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPR"."ARPR_ARPR" IS 'Identificador de archivo repetido';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPR"."ARPR_PRUN" IS 'Proceso de unificacion de archivos al que esta asociado el archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPR"."ARPR_ARUN" IS 'Archivo Unificado en el cual se identifico el archivo repetido';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TARPR"."ARPR_ARPU" IS 'Archivo Por Unificar al cual hace referencia';
      
   COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TARPR"  IS 'Tabla que almacena la informacion de los archivos repetidos de recaudo por proceso';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TARPR"
  add  constraint "PK_UN_TARPR"
       primary key ("ARPR_ARPR")
       using index 
       tablespace &Indices;
