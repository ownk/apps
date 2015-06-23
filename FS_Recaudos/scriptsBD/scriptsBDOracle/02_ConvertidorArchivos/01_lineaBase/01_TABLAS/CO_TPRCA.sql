/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Proyectos de recaudo cancelados
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TPRCA
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TPRCA" 
   (	"PRCA_PROY" 			NUMBER					constraint NN_CO_TPRCA_PROY         not null,
		"PRCA_PLAN_SIFI" 		NUMBER          		constraint NN_CO_TPRCA_PLAN_SIFI    not null, 
        "PRCA_DESCRI" 			VARCHAR2(2000 BYTE)		constraint NN_CO_TPRCA_DESCRI       not null,
		"PRCA_FCREA" 			DATE					constraint NN_CO_TPRCA_FCREA        not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCA"."PRCA_PROY" IS 'Identificador del proyecto NO SIFI';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCA"."PRCA_FCREA" IS 'Fecha de creacion';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCA"."PRCA_DESCRI" IS 'Descripcion del proyecto';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPRCA"."PRCA_PLAN_SIFI"  IS 'Plan o Encargo SIFI al cual se debe realizar el traslado de los recursos ';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TPRCA"  IS 'Proyectos Recaudo Cancelados';
   
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TPRCA"
  add  constraint "PK_CO_TPRCA"
       primary key ("PRCA_PROY")
       using index 
       tablespace &Indices;