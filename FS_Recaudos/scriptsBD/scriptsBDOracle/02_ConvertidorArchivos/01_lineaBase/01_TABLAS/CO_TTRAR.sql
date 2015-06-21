/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Detalle de transformaciones realizadas en el archivo de recaudo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TTRAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TTRAR" 
   (	"TRAR_TRAR" 		    NUMBER					constraint NN_CO_TTRAR_TRAR not null,
        "TRAR_TPTR" 		    NUMBER					constraint NN_CO_TTRAR_TPTR not null,
   		"TRAR_AROR"             NUMBER		            constraint NN_CO_TTRAR_AROR not null,
		"TRAR_DAROR_ID_REG"     NUMBER		            constraint NN_CO_TTRAR_DAROR_ID_REG not null,
        "TRAR_VALOR_ORIG"       VARCHAR2(100 BYTE)		constraint NN_CO_TTRAR_VALOR_ORIG not null,
        "TRAR_VALOR_MODI"       VARCHAR2(100 BYTE)		constraint NN_CO_TTRAR_VALOR_MODI not null,
        "TRAR_VALOR_DESCRI"     VARCHAR2(2000 BYTE)		constraint NN_CO_TTRAR_VALOR_DESCRI not null,
        "TRAR_FCREA" 		    DATE					constraint NN_CO_TTRAR_FCREA not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
    
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTRAR"."TRAR_TRAR" 		    IS 'Identificador unico de la transformacion';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTRAR"."TRAR_TPTR" 		    IS 'Tipo de transformacion de archivo de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTRAR"."TRAR_AROR"           IS 'Consecutivo de archivo de recaudo original ';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTRAR"."TRAR_DAROR_ID_REG"   IS 'Id de registro modificado del archivo original';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTRAR"."TRAR_VALOR_ORIG"     IS 'Valor original del registro';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTRAR"."TRAR_VALOR_MODI"     IS 'Valor modificado';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTRAR"."TRAR_VALOR_DESCRI"   IS 'Descripcion de la transformacion';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTRAR"."TRAR_FCREA" 		    IS 'Fecha de creacion de la transformacion';
    
    COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TTRAR"  IS 'Tabla que almacena el detalle de transformaciones realizadas en el archivo de recaudo';
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TTRAR"
  add  constraint "PK_CO_TTRAR"
       primary key ("TRAR_TRAR")
       using index 
       tablespace &Indices;