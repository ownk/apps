/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Detalle de transformaciones realizadas en el archivo de recaudo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TVLAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TVLAR" 
   (	"VLAR_VLAR" 		    NUMBER					constraint NN_CO_TVLAR_VLAR not null,
        "VLAR_TPVL" 		    NUMBER					constraint NN_CO_TVLAR_TPVL not null,
   		"VLAR_AROR"             NUMBER		            constraint NN_CO_TVLAR_AROR not null,
		"VLAR_DAROR_ID_REG"     NUMBER		            constraint NN_CO_TVLAR_DAROR_ID_REG not null,
        "VLAR_VALOR_DESCRI"     VARCHAR2(2000 BYTE)		constraint NN_CO_TVLAR_VALOR_DESCRI not null,
        "VLAR_FCREA" 		    DATE					constraint NN_CO_TVLAR_FCREA not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
    
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TVLAR"."VLAR_VLAR" 		    IS 'Identificador unico de la validacion';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TVLAR"."VLAR_TPVL" 		    IS 'Tipo de validacion de archivo de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TVLAR"."VLAR_AROR"           IS 'Consecutivo de archivo de recaudo original ';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TVLAR"."VLAR_DAROR_ID_REG"   IS 'Id de registro modificado del archivo original';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TVLAR"."VLAR_VALOR_DESCRI"   IS 'Descripcion de la validacion';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TVLAR"."VLAR_FCREA" 		    IS 'Fecha de creacion de la validacion';
    
    COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TVLAR"  IS 'Tabla que almacena el detalle de las validaciones realizadas en el archivo de recaudo';
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TVLAR"
  add  constraint "PK_CO_TVLAR"
       primary key ("VLAR_VLAR")
       using index 
       tablespace &Indices;