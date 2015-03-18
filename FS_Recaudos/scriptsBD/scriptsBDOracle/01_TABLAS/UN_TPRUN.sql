/******************************************************************************
  FECHA:		  
  AUTOR:		  
  TABLA:	    
  FUNCION:		
			
  OBSERVACIONES:	
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla UN_TPRUN
Prompt

CREATE TABLE "FS_RECAUDOS_US"."UN_TPRUN" 
(	"PRUN_PRUN" NUMBER 		            constraint NN_UN_TPRUN_PRUN NOT NULL, 
	"PRUN_USUA" VARCHAR2(100 BYTE) 		constraint NN_UN_TPRUN_USUA NOT NULL, 
	"PRUN_FCREA" VARCHAR2(100 BYTE)		constraint NN_UN_TPRUN_MAIL NOT NULL
)  storage( initial 10k  next 10k  pctincrease 0 )
/  


COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_PRUN" IS 'Identificador unico de proceso de unificacion de archivos';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."PRUN_USUA" IS 'Codigo de usuario con el que se crea el proceso';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."USUA_CLAVE" IS 'Clave encriptada (RSA) asociada al usuario';

COMMENT ON COLUMN "FS_RECAUDOS_US"."UN_TPRUN"."USUA_MAIL" IS 'correo electronica en el que se enviara las notificaciones del sistema';

COMMENT ON TABLE "FS_RECAUDOS_US"."UN_TPRUN"  IS 'Tabla que almacena el nombre de Usuario y clave (encriptada RSA) que controla el acceso al FS_RECAUDOS';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."UN_TPRUN"
  add  constraint "PK_UN_TPRUN"
       primary key ("USUA_USUA") 
       using index 
       tablespace &Indices;