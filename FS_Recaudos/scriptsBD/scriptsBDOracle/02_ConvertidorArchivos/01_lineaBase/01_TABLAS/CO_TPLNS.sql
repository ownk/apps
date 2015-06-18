/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Planes no SIFI
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TPLNS
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TPLNS" 
   (	"PLNS_PLAN" 			NUMBER					constraint NN_CO_TPLNS_PLAN    not null,
		"PLNS_ESTA" 			VARCHAR2(10 BYTE)	    constraint NN_CO_TPLNS_ESTA     not null, 
		"PLNS_FCREA" 			DATE					constraint NN_CO_TPLNS_FCREA   not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLNS"."PLNS_PLAN" IS 'Identificador del tipo de formula soportada por el convertidor ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLNS"."PLNS_ESTA" IS 'Cuenta recaudadora ';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLNS"."PLNS_FCREA" IS 'Fecha de creacion';

   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TPLNS"  IS 'Planes no SIFI. Tabla con informacion permanente';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TPLNS"
  add  constraint "PK_CO_TPLNS"
       primary key ("PLNS_PLAN")
       using index 
       tablespace &Indices;