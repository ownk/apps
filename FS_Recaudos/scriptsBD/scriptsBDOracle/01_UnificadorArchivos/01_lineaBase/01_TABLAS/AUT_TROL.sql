/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	  		
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla AUT_TROL
Prompt


CREATE TABLE "FS_RECAUDOS_US"."AUT_TROL" 
(	"ROL_ROL" VARCHAR2(20 BYTE)			constraint NN_AUT_TROL_ROL		not null, 
"ROL_DESCRI" VARCHAR2(2000 BYTE)	constraint NN_AUT_TROL_DESCRI	not null
) storage( initial 10k  next 10k  pctincrease 0 )
/



COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TROL"."ROL_ROL" IS 'identificador del rol';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TROL"."ROL_DESCRI" IS 'Descripcion del rol';

COMMENT ON TABLE "FS_RECAUDOS_US"."AUT_TROL"  IS 'Tabla que almacena los roles que pueden desempeñar los usuarios dentro del Sistema de Gestión de Proyectos de Grado de la Universidad Distrital (FS_RECAUDOS)';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."AUT_TROL"
add  constraint "PK_AUT_TROL"
primary key ("ROL_ROL")
using index 
tablespace &Indices;
