/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Estados de encargos que aplican formula Distribucion por porcentaje
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TEPFD
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TEPFD" 
   (	"EPFD_FRDP" 			NUMBER          		constraint NN_CO_TEPFD_FRDP    not null, 
        "EPFD_ESTA" 		    VARCHAR(10)				constraint NN_CO_TEPFD_ESTA    not null,
        "EPFD_FCREA" 			DATE					constraint NN_CO_TEPFD_FCREA   not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEPFD"."EPFD_FCREA" IS 'Fecha de creacion';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEPFD"."EPFD_ESTA" IS 'Estado de de encargo al cual aplica formula';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TEPFD"."EPFD_FRDP"  IS 'Formula a la que esta asociada distribucion de porcentaje';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TEPFD"  IS 'Estados de encargos que aplican formula Distribucion por porcentaje';
   
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TEPFD"
  add  constraint "PK_CO_TEPFD"
       primary key ("EPFD_FRDP",  "EPFD_ESTA")
       using index 
       tablespace &Indices;