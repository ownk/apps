/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Planes SIFI. Tabla con informacion temporal por fecha de proceso de recaudo consultados de SIFI 43 y SIFI 29 
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TPLTS
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TPLTS" 
   (	
        "PLTS_FOND" 			NUMBER					constraint NN_CO_TPLTS_FOND         not null,    
        "PLTS_PLAN" 			NUMBER					constraint NN_CO_TPLTS_PLAN         not null,
		"PLTS_ESTA" 			VARCHAR2(10 BYTE)	    constraint NN_CO_TPLTS_ESTA         not null, 
        "PLTS_FDEI" 			NUMBER                  constraint NN_CO_TPLTS_FDEI         not null, 
        "PLTS_FDEI_TITU" 		VARCHAR2(1)             constraint NN_CO_TPLTS_FDEI_TITU    not null, 
        "PLTS_FDEI_TPID" 		VARCHAR2(1)	            constraint NN_CO_TPLTS_FDEI_TPID    not null, 
        "PLTS_FCREA" 			DATE					constraint NN_CO_TPLTS_FCREA        not null,
        "PLTS_FRECA" 			DATE					constraint NN_CO_TPLTS_FRECA        not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLTS"."PLTS_FOND" IS 'Identificador del fondo';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLTS"."PLTS_PLAN" IS 'Identificador del plan ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLTS"."PLTS_ESTA" IS 'Estado del plan ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLTS"."PLTS_FDEI" IS 'Nro ID Titular del encargo ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLTS"."PLTS_FDEI_TPID" IS 'Tipo de identificacion  ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLTS"."PLTS_FDEI_TITU" IS 'Tipo de titular P.Principal o S.Secundario  ';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLTS"."PLTS_FCREA" IS 'Fecha de creacion';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLTS"."PLTS_FRECA" IS 'Fecha de recaudo';

   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TPLTS"  IS 'Planes SIFI. Tabla con informacion temporal por fecha de proceso de recaudo consultados de SIFI 43 y SIFI 29';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TPLTS"
  add  constraint "PK_CO_TPLTS"
       primary key ("PLTS_PLAN")
       using index 
       tablespace &Indices;