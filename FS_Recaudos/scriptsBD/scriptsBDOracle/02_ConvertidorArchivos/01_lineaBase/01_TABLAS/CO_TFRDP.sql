/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Formula de distribucion de porcentaje de recaudo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TFRDP
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TFRDP" 
   (	"FRDP_FRDP" 			NUMBER					constraint NN_CO_TFRDP_FRDP    not null,
		"FRDP_DESCRI" 			VARCHAR2(2000 BYTE)		constraint NN_CO_TFRDP_DESCRI  not null, 
		"FRDP_FCREA" 			DATE					constraint NN_CO_TFRDP_FCREA   not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TFRDP"."FRDP_FRDP" IS 'Identificador del tipo de formula soportada por el convertidor ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TFRDP"."FRDP_FCREA" IS 'Fecha de creacion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TFRDP"."FRDP_DESCRI"  IS 'Descripcion de la formula';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TFRDP"  IS 'Tabla que almacena identificadores de formulas de distribucion de porcentaje de recaudo';
   
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TFRDP"
  add  constraint "PK_CO_TFRDP"
       primary key ("FRDP_FRDP" )
       using index 
       tablespace &Indices;