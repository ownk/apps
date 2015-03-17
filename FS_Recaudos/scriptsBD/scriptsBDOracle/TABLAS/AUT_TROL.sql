/******************************************************************************
VERSION:		
FECHA:		  
AUTOR:		  
DERECHOS:		
MODULO:	    
FUNCION:		

OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IUDTESIS

Prompt 
Prompt Creando tabla AUT_TROL
Prompt


CREATE TABLE "UDTESIS"."AUT_TROL" 
(	"ROL_ROL" VARCHAR2(20 BYTE)			constraint NN_AUT_TROL_ROL		not null, 
"ROL_DESCRI" VARCHAR2(2000 BYTE)	constraint NN_AUT_TROL_DESCRI	not null
) storage( initial 10k  next 10k  pctincrease 0 )
/



COMMENT ON COLUMN "UDTESIS"."AUT_TROL"."ROL_ROL" IS 'identificador del rol';

COMMENT ON COLUMN "UDTESIS"."AUT_TROL"."ROL_DESCRI" IS 'Descripcion del rol';

COMMENT ON TABLE "UDTESIS"."AUT_TROL"  IS 'Tabla que almacena los roles que pueden desempeñar los usuarios dentro del Sistema de Gestión de Proyectos de Grado de la Universidad Distrital (SGPG-UD)';

prompt Llave Primaria

alter table "UDTESIS"."AUT_TROL"
add  constraint "PK_AUT_TROL"
primary key ("ROL_ROL")
using index 
tablespace &Indices;
