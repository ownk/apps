/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Planes SIFI especiales que fueron cancelados y que aun llegan en archivos de recaudo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TPLEC
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TPLEC" 
   (	"PLEC_PLAN" 			NUMBER					constraint NN_CO_TPLEC_PLAN         not null,
		"PLEC_ESTA" 			VARCHAR2(10 BYTE)	    constraint NN_CO_TPLEC_ESTA         not null, 
        "PLEC_FDEI" 			NUMBER                  constraint NN_CO_TPLEC_FDEI         not null, 
        "PLEC_FDEI_TIPO" 		VARCHAR2(1)	            constraint NN_CO_TPLEC_FDEI_TIPO    not null, 
        "PLEC_FCREA" 			DATE					constraint NN_CO_TPLEC_FCREA        not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLEC"."PLEC_PLAN" IS 'Identificador del tipo de formula soportada por el convertidor ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLEC"."PLEC_ESTA" IS 'Cuenta recaudadora ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLEC"."PLEC_FDEI" IS 'Nro ID Titular del encargo ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLEC"."PLEC_FDEI_TIPO" IS 'Tipo de titular P.Principal o S.Secundario  ';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPLEC"."PLEC_FCREA" IS 'Fecha de creacion';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TPLEC"  IS 'Planes SIFI especiales que fueron cancelados y que aun llegan en archivos de recaudo';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TPLEC"
  add  constraint "PK_CO_TPLEC"
       primary key ("PLEC_PLAN")
       using index 
       tablespace &Indices;