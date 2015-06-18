/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Proyectos NO SIFI Activos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TPNSA
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TPNSA" 
   (	"PNSA_PROY" 			NUMBER					constraint NN_CO_TPNSA_PROY         not null,
		"PNSA_PLAN_SIFI" 		NUMBER          		constraint NN_CO_TPNSA_PLAN_SIFI    not null, 
        "PNSA_DESCRI" 			VARCHAR2(2000 BYTE)		constraint NN_CO_TPNSA_DESCRI       not null,
		"PNSA_FCREA" 			DATE					constraint NN_CO_TPNSA_FCREA        not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPNSA"."PNSA_PROY" IS 'Identificador del proyecto NO SIFI';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPNSA"."PNSA_FCREA" IS 'Fecha de creacion';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPNSA"."PNSA_DESCRI" IS 'Descripcion del proyecto';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPNSA"."PNSA_PLAN_SIFI"  IS 'Plan o Encargo SIFI al cual se debe realizar el traslado de los recursos ';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TPNSA"  IS 'Proyectos NO SIFI Activos';
   
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TPNSA"
  add  constraint "PK_CO_TPNSA"
       primary key ("PNSA_PROY")
       using index 
       tablespace &Indices;