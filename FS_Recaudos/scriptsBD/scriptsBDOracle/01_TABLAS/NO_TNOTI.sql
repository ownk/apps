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
Prompt Creando tabla NO_TNOTI
Prompt

CREATE TABLE "FS_RECAUDOS_US"."NO_TNOTI" 
(	"NOTI_NOTI" NUMBER							CONSTRAINT NN_NO_TNOTI_NOTI NOT NULL, 
	"NOTI_USUA_RECEPTOR" VARCHAR2(100 BYTE)		CONSTRAINT NN_NO_TNOTI_USUA_RECEP NOT NULL, 
	"NOTI_TITU" VARCHAR2(250 BYTE)				CONSTRAINT NN_NO_TNOTI_TITU NOT NULL, 
	"NOTI_CONT" CLOB							CONSTRAINT NN_NO_TNOTI_CONT NOT NULL, 
	"NOTI_FCREA" DATE							CONSTRAINT NN_NO_TNOTI_FCREA NOT NULL, 
	"NOTI_ESTA" VARCHAR2(1 BYTE) DEFAULT 'N'	CONSTRAINT NN_NO_TNOTI_ESTA NOT NULL, 
	"NOTI_USUA_EMISOR" VARCHAR2(50 BYTE)        CONSTRAINT NN_NO_TNOTI_USUA_EMIS NOT NULL
)   storage( initial 10k  next 10k  pctincrease 0 )
/  
 


COMMENT ON COLUMN "FS_RECAUDOS_US"."NO_TNOTI"."NOTI_NOTI" IS 'Llave primaria de la notificación';

COMMENT ON COLUMN "FS_RECAUDOS_US"."NO_TNOTI"."NOTI_USUA_RECEPTOR" IS 'Usuario al que se le envia la notificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."NO_TNOTI"."NOTI_TITU" IS 'Titulo de la notificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."NO_TNOTI"."NOTI_CONT" IS 'Contenido de la notificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."NO_TNOTI"."NOTI_FCREA" IS 'Fecha de creacion de la notificacion';

COMMENT ON COLUMN "FS_RECAUDOS_US"."NO_TNOTI"."NOTI_ESTA" IS 'Estado (N: nueva, L: leida, E: Eliminada, P: Pendiente de lectura)';

COMMENT ON COLUMN "FS_RECAUDOS_US"."NO_TNOTI"."NOTI_USUA_EMISOR" IS 'Usuario creador de la notificacion, _SYSTEM_ si la crea el sistema';

COMMENT ON TABLE "FS_RECAUDOS_US"."NO_TNOTI"  IS 'Notificaciones por usuario';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."NO_TNOTI"
  add  constraint "PK_NO_TNOTI"
       primary key ("NOTI_NOTI")
       using index 
       tablespace &Indices;
	   
prompt Indices

create index "FS_RECAUDOS_US"."NO_INOTI_ESTA"
    on "FS_RECAUDOS_US"."NO_TNOTI"   ("NOTI_ESTA")
       tablespace &Indices
       storage ( initial 100k   next 100k   pctincrease 0 )
/

create index "FS_RECAUDOS_US"."NO_INOTI_TITU"
    on "FS_RECAUDOS_US"."NO_TNOTI"   ("NOTI_TITU")
       tablespace &Indices
       storage ( initial 100k   next 100k   pctincrease 0 )
/

prompt CHECKS

alter table "FS_RECAUDOS_US"."NO_TNOTI"
  add constraint "CH_NO_TNOTI_ESTA" 
      check ( "NOTI_ESTA" in ('N', 'L', 'E','P'))
/
