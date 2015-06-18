/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Proyectos asociados a formulas de distribucion de porcentaje de recaudo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TPYFD
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TPYFD" 
   (	"PYFD_PROY" 			NUMBER					constraint NN_CO_TPYFD_PROY    not null,
		"PYFD_FRDP" 			NUMBER          		constraint NN_CO_TPYFD_FRDP    not null, 
		"PYFD_FCREA" 			DATE					constraint NN_CO_TPYFD_FCREA   not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPYFD"."PYFD_PROY" IS 'Identificador del proyecto';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPYFD"."PYFD_FCREA" IS 'Fecha de creacion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPYFD"."PYFD_FRDP"  IS 'Identificador de la formula de distrbuciona al que esta asociado el proyecto';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TPYFD"  IS 'Proyectos asociados a formulas de distribucion de porcentaje de recaudo';
   
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TPYFD"
  add  constraint "PK_CO_TPYFD"
       primary key ("PYFD_PROY",  "PYFD_FRDP")
       using index 
       tablespace &Indices;