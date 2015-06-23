/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Homologacion de oficinas
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TOFIC
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TOFIC" 
   (	"OFIC_BSC" 		       VARCHAR2(10 BYTE)		constraint NN_CO_TOFIC_BSC not null,
		"OFIC_SIFI"            VARCHAR2(10 BYTE)		constraint NN_CO_TOFIC_SIFI not null,
        "OFIC_DESCRI"          VARCHAR2(2000 BYTE)		constraint NN_CO_TOFIC_DESCRI not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
    
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TOFIC"."OFIC_BSC" 		IS 'Codigo de oficina enviado por el banco';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TOFIC"."OFIC_SIFI"       IS 'Codigo de oficina equivalente en SIFI';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TOFIC"."OFIC_DESCRI" 	IS 'Descripcion de  oficina';
  
    
    COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TOFIC"  IS 'Tabla que almacena el detalle archivo original por convertir';
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TOFIC"
  add  constraint "PK_CO_TOFIC"
       primary key ("OFIC_BSC", "OFIC_SIFI")
       using index 
       tablespace &Indices;