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
Prompt Creando tabla AUT_TUSUA
Prompt

CREATE TABLE "UDTESIS"."AUT_TUSUA" 
(	"USUA_USUA" VARCHAR2(100 BYTE) 		constraint NN_AUT_TUSUA_USUA NOT NULL, 
	"USUA_CLAVE" VARCHAR2(2000 BYTE)	constraint NN_AUT_TUSUA_CLAVE NOT NULL, 
	"USUA_MAIL" VARCHAR2(100 BYTE)		constraint NN_AUT_TUSUA_MAIL NOT NULL
)  storage( initial 10k  next 10k  pctincrease 0 )
/  


COMMENT ON COLUMN "UDTESIS"."AUT_TUSUA"."USUA_USUA" IS 'nombre de usuario con el que se puede ingresar al SGPG-UD';

COMMENT ON COLUMN "UDTESIS"."AUT_TUSUA"."USUA_CLAVE" IS 'Clave encriptada (RSA) asociada al usuario';

COMMENT ON COLUMN "UDTESIS"."AUT_TUSUA"."USUA_MAIL" IS 'correo electronica en el que se enviara las notificaciones del sistema';

COMMENT ON TABLE "UDTESIS"."AUT_TUSUA"  IS 'Tabla que almacena el nombre de Usuario y clave (encriptada RSA) que controla el acceso al SGPG-UD';

prompt Llave Primaria

alter table "UDTESIS"."AUT_TUSUA"
  add  constraint "PK_AUT_TUSUA"
       primary key ("USUA_USUA") 
       using index 
       tablespace &Indices;