/******************************************************************************
VERSION:		
FECHA:		  
AUTOR:		  
DERECHOS:		
TABLA:	    
FUNCION:		
	
OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IUDTESIS

Prompt 
Prompt Creando tabla AUT_TUROL
Prompt

CREATE TABLE "UDTESIS"."AUT_TUROL" 
(	"UROL_USUA" VARCHAR2(100 BYTE)	constraint NN_AUT_TUROL_USUA not null, 
"UROL_ROL" VARCHAR2(20 BYTE)	constraint NN_AUT_TUROL_ROL not null
) storage( initial 10k  next 10k  pctincrease 0 )
/  

COMMENT ON COLUMN "UDTESIS"."AUT_TUROL"."UROL_USUA" IS 'Usuario del SGPG-UD';

COMMENT ON COLUMN "UDTESIS"."AUT_TUROL"."UROL_ROL" IS 'Rol asociado al usuario';

COMMENT ON TABLE "UDTESIS"."AUT_TUROL"  IS 'Tabla que relaciona los usuarios del SGPG-UD con los roles establecidos en él';

prompt Llave Primaria

alter table "UDTESIS"."AUT_TUROL"
add  constraint "PK_AUT_TUROL"
primary key ("UROL_USUA", "UROL_ROL")
using index 
tablespace &Indices;

