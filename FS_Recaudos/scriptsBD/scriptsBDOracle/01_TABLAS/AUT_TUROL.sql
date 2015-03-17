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
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla AUT_TUROL
Prompt

CREATE TABLE "FS_RECAUDOS_US"."AUT_TUROL" 
(	"UROL_USUA" VARCHAR2(100 BYTE)	constraint NN_AUT_TUROL_USUA not null, 
"UROL_ROL" VARCHAR2(20 BYTE)	constraint NN_AUT_TUROL_ROL not null
) storage( initial 10k  next 10k  pctincrease 0 )
/  

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TUROL"."UROL_USUA" IS 'Usuario del FS_RECAUDOS';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TUROL"."UROL_ROL" IS 'Rol asociado al usuario';

COMMENT ON TABLE "FS_RECAUDOS_US"."AUT_TUROL"  IS 'Tabla que relaciona los usuarios del FS_RECAUDOS con los roles establecidos en él';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."AUT_TUROL"
add  constraint "PK_AUT_TUROL"
primary key ("UROL_USUA", "UROL_ROL")
using index 
tablespace &Indices;

