/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Estados de encargos que se deben sistuir por encargo generico acorde al tipo de archivo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TEPSG
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TEPSG" 
   (	"EPSG_TPAR" 			NUMBER          		constraint NN_CO_TEPSG_TPAR    not null, 
        "EPSG_ESTA" 		    VARCHAR(10)				constraint NN_CO_TEPSG_ESTA    not null,
        "EPSG_FCREA" 			DATE					constraint NN_CO_TEPSG_FCREA   not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEPSG"."EPSG_FCREA" IS 'Fecha de creacion';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEPSG"."EPSG_ESTA" IS 'Estado de de encargo al cual aplica formula';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEPSG"."EPSG_TPAR"  IS 'Formula a la que esta asociada distribucion de porcentaje';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TEPSG"  IS 'Estados de encargos que se deben sistuir por encargo generico acorde al tipo de archivo';
   
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TEPSG"
  add  constraint "PK_CO_TEPSG"
       primary key ("EPSG_TPAR",  "EPSG_ESTA")
       using index 
       tablespace &Indices;