/******************************************************************************
VERSION:		
FECHA:		  
AUTOR:		  
DERECHOS:		
TABLA:	    
FUNCION:		Configuracion general
		
OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IUDTESIS

Prompt 
Prompt Creando tabla GE_TCONFIG
Prompt

CREATE TABLE "UDTESIS"."GE_TCONFIG" 
(	"CONFIG_CONFIG" 	NUMBER					constraint NN_GE_TCONFIG_CONFIG not null, 
	"CONFIG_DESCRI" 	VARCHAR2(4000)			constraint NN_GE_TCONFIG_DESCRI not null,
	"CONFIG_TIPO_DATO"	NUMBER					constraint NN_GE_TCONFIG_TIPO_DATO not null,
	"CONFIG_VALOR"		VARCHAR2(4000)			constraint NN_GE_TCONFIG_VALOR not null
)storage( initial 10k  next 10k  pctincrease 0 )
/  

COMMENT ON COLUMN "UDTESIS"."GE_TCONFIG"."CONFIG_CONFIG" IS 'Identificador unico de la actividad';

COMMENT ON COLUMN "UDTESIS"."GE_TCONFIG"."CONFIG_DESCRI" IS 'Proyecto asociado';

COMMENT ON COLUMN "UDTESIS"."GE_TCONFIG"."CONFIG_TIPO_DATO" IS 'Tipo de dato. 1-Numerico, 2-String, 3-Boolean';

COMMENT ON COLUMN "UDTESIS"."GE_TCONFIG"."CONFIG_VALOR" IS 'Tipo de actividad programada';

COMMENT ON TABLE "UDTESIS"."GE_TCONFIG"  IS 'Tabla que almacena las variables generales del sistema';


prompt Llave Primaria


alter table "UDTESIS"."GE_TCONFIG"
add  constraint "PK_GE_TCONFIG"
   primary key ("CONFIG_CONFIG")
   using index 
   tablespace &Indices;	  
   

prompt CH_GE_TCONFIG_TIPO_DATO

alter table "UDTESIS"."GE_TCONFIG"
add constraint "CH_GE_TCONFIG_TIPO_DATO"
  check (CONFIG_TIPO_DATO in ( 1, 2, 3)) ENABLE;   
