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
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla AUT_TSERVICIO
Prompt

CREATE TABLE "FS_RECAUDOS_US"."AUT_TSERVICIO" 
(	"SERVICIO_SERVICIO" NUMBER			constraint NN_AUT_TSERVICIO_SERVICIO not null, 
	"SERVICIO_MODULO" VARCHAR2(20 BYTE)		constraint NN_AUT_TSERVICIO_MODULO not null, 
	"SERVICIO_DESCRI" VARCHAR2(200 BYTE)	constraint NN_AUT_TSERVICIO_DESCRI not null, 
	"SERVICIO_NOMB" VARCHAR2(100 BYTE)		constraint NN_AUT_TSERVICIO_NOMB not null,
	"SERVICIO_TIPO" VARCHAR2(2 BYTE)		constraint NN_AUT_TSERVICIO_TIPO not null,
	"SERVICIO_ORDEN" NUMBER					constraint NN_AUT_TSERVICIO_ORDEN not null
)  storage( initial 10k  next 10k  pctincrease 0 )
/  


COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSERVICIO"."SERVICIO_SERVICIO" IS 'Id del servicio prestado en el sistema';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSERVICIO"."SERVICIO_MODULO" IS 'ID del Modulo al que pertenece el servici� relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSERVICIO"."SERVICIO_DESCRI" IS 'Descripci�n del servicio relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSERVICIO"."SERVICIO_NOMB" IS 'Nombre del servicio relacionado';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSERVICIO"."SERVICIO_TIPO" IS 'Tipo servicio. L=Listado, O=Operativo';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSERVICIO"."SERVICIO_ORDEN" IS 'Orden del servicio dentro del modulo';

COMMENT ON TABLE "FS_RECAUDOS_US"."AUT_TSERVICIO"  IS 'Tabla que almacena los servicios prestados dentro del FS_RECAUDOS, así como los modulos a los que pertenecen';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."AUT_TSERVICIO"
add  constraint "PK_AUT_TSERVICIO"
   primary key ("SERVICIO_SERVICIO")
   using index 
   tablespace &Indices;
   
prompt CH_AUT_TSERVICIO_TIPO
alter table "FS_RECAUDOS_US"."AUT_TSERVICIO"
add constraint "CH_AUT_TSERVICIO_TIPO"
  check (SERVICIO_TIPO in ( 'L', 'O')) ENABLE;   