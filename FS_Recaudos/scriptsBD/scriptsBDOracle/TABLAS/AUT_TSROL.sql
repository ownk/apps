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
Prompt Creando tabla AUT_TSROL
Prompt

CREATE TABLE "UDTESIS"."AUT_TSROL" 
(	"SROL_SERVICIO" NUMBER							constraint NN_AUT_TSROL_SERVICIO not null, 
	"SROL_ROL" VARCHAR2(20 BYTE)					constraint NN_AUT_TSROL_ROL not null, 
	"SROL_VISIBLE" VARCHAR2(1 BYTE) DEFAULT 'N'		constraint NN_AUT_TSROL_VISIBLE not null
)
storage( initial 10k  next 10k  pctincrease 0 )
/  
  


COMMENT ON COLUMN "UDTESIS"."AUT_TSROL"."SROL_SERVICIO" IS 'Servicio asociado al rol';

COMMENT ON COLUMN "UDTESIS"."AUT_TSROL"."SROL_ROL" IS 'Rol que tiene asociado servicios';

COMMENT ON COLUMN "UDTESIS"."AUT_TSROL"."SROL_VISIBLE" IS 'Sera visible el servicio en el menú del rol correspondiente ''S'' si o ''N'' no';

COMMENT ON TABLE "UDTESIS"."AUT_TSROL"  IS 'Tabla que relaciona los servicios prestados en el sistema con los roles de usuario que pueden acceder a ellos ';

prompt Llave Primaria

alter table "UDTESIS"."AUT_TSROL"
  add  constraint "PK_AUT_TSROL"
       primary key ("SROL_SERVICIO", "SROL_ROL")
       using index 
       tablespace &Indices;


--------------------------------------------------------
--  Ref Constraints for Table AUT_TSROL
--------------------------------------------------------
prompt CHECKS
alter table "UDTESIS"."AUT_TSROL" 
  add constraint "CH_PI_TAUT_TSROL_VISIBLE"
      check ( "SROL_VISIBLE" in ('S', 'N'))
/
