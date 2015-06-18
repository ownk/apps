/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Distribucion por formula de porcentaje
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TDPFD
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TDPFD" 
   (	"DPFD_FRDP" 			NUMBER          		constraint NN_CO_TDPFD_FRDP    not null, 
        "DPFD_PORC_RECA" 		NUMBER(1,4)				constraint NN_CO_TDPFD_PORC    not null,
        "DPFD_PLAN_DEST" 		NUMBER      			constraint NN_CO_TDPFD_PLAN    not null,
		"DPFD_FCREA" 			DATE					constraint NN_CO_TDPFD_FCREA   not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDPFD"."DPFD_PORC_RECA" IS 'Valor decimal del porcentaje de recaudo a distribuir Ej 87.56% = 0.8756';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDPFD"."DPFD_FCREA" IS 'Fecha de creacion';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDPFD"."DPFD_PLAN_DEST" IS 'Encargo destino al cual se hara la distribucion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDPFD"."DPFD_FRDP"  IS 'Formula a la que esta asociada distribucion de porcentaje';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TDPFD"  IS 'Distribucion por formula de porcentaje';
   
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TDPFD"
  add  constraint "PK_CO_TDPFD"
       primary key ("DPFD_FRDP",  "DPFD_PORC_RECA", "DPFD_PLAN_DEST")
       using index 
       tablespace &Indices;