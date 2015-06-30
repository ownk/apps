/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Detalle de los errores  encontrados en el archivo de recaudo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TERAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TERAR" 
   (	"ERAR_ERAR" 		    NUMBER					constraint NN_CO_TERAR_ERAR not null,
        "ERAR_TPER" 		    NUMBER					constraint NN_CO_TERAR_TPER not null,
   		"ERAR_AROR"             NUMBER		            constraint NN_CO_TERAR_AROR not null,
		"ERAR_DAROR_ID_REG"     NUMBER		            constraint NN_CO_TERAR_DAROR_ID_REG not null,
        "ERAR_ERROR_DESCRI"     VARCHAR2(2000 BYTE)		constraint NN_CO_TERAR_ERROR_DESCRI not null,
        "ERAR_FCREA" 		    DATE					constraint NN_CO_TERAR_FCREA not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
    
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERAR"."ERAR_ERAR" 		    IS 'Identificador unico del error';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERAR"."ERAR_TPER" 		    IS 'Tipo de error de archivo de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERAR"."ERAR_AROR"           IS 'Consecutivo de archivo de recaudo original ';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERAR"."ERAR_DAROR_ID_REG"   IS 'Id de registro identificado del archivo original';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERAR"."ERAR_ERROR_DESCRI"   IS 'Descripcion del error';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TERAR"."ERAR_FCREA" 		    IS 'Fecha de creacion de la validacion';
    
    COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TERAR"  IS 'Tabla que almacena el detalle de los errores encontrados en el archivo de recaudo';
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TERAR"
  add  constraint "PK_CO_TERAR"
       primary key ("ERAR_ERAR")
       using index 
       tablespace &Indices;