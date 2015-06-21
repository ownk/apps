/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Tipos de validaciones del contenido del archivo de recaudos
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TTPVL
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TTPVL" 
   (	"TPVL_TPVL" 		    NUMBER          			constraint NN_CO_TTPVL_TPVL     not null, 
		"TPVL_DESCRI" 		    VARCHAR2(2000 BYTE)			constraint NN_CO_TTPVL_DESCRI   not null,
        "TPVL_COLOR" 	        VARCHAR2(10)			    constraint NN_CO_TTPVL_PLAN     not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPVL"."TPVL_TPVL" IS 'Identificador unico de tipo de validacion';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPVL"."TPVL_DESCRI" IS 'Descripcion del tipo de validacion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPVL"."TPVL_COLOR" IS 'Color para demarcar el tipo de validacion utilizada';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TTPVL"  IS 'Tabla donde se almacenan los tipos de validacion realizadas al archivo de recaudo';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TTPVL"
  add  constraint "PK_CO_TTPVL"
       primary key ("TPVL_TPVL")
       using index 
       tablespace &Indices;
	   
