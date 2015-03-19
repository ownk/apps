/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk		
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla AUT_TSURL
Prompt

CREATE TABLE "FS_RECAUDOS_US"."AUT_TSURL" 
(	"SURL_URL" VARCHAR2(2000 BYTE) 				constraint NN_AUT_TSURL_URL NOT NULL, 
	"SURL_SERVICIO" NUMBER 					constraint NN_AUT_TSURL_SERVICIO NOT NULL, 
	"SURL_TIPO" VARCHAR2(1 BYTE) DEFAULT 'S'	constraint NN_AUT_TSURL_TIPO NOT NULL
)
   storage( initial 10k  next 10k  pctincrease 0 )
/   

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSURL"."SURL_URL" IS 'Id y URL del servicio o archivo que se esta ubicando';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSURL"."SURL_SERVICIO" IS 'Servicio que se presta y es ubicado en un archivo en la URL relacionada';

COMMENT ON COLUMN "FS_RECAUDOS_US"."AUT_TSURL"."SURL_TIPO" IS 'Tipo de URL P-Principal, S-Secundaria';

COMMENT ON TABLE "FS_RECAUDOS_US"."AUT_TSURL"  IS 'Tabla que almacena las rutas únicas de localización (URL) de las páginas pertenecientes al FS_RECAUDOS así como su relación con el servicio al que pertenece y si es un archivo de tipo primario o secundario';

prompt Llave Primaria

alter table "FS_RECAUDOS_US"."AUT_TSURL"
  add  constraint "PK_AUT_TSURL"
       primary key ("SURL_URL", "SURL_SERVICIO") 
       using index 
       tablespace &Indices;

prompt CHECKS

alter table "FS_RECAUDOS_US"."AUT_TSURL"
  add constraint "CH_AUT_TSURL_TIPO" 
      check ( "SURL_TIPO" in ('P', 'S'))
/


