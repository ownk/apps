/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	     
	FUNCION: Parametros generales del modulo convertidor
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TPARA
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TPARA" 
   (	"PARA_PARA" 			VARCHAR2(100)			constraint NN_CO_TPARA_PARA    not null,
		"PARA_DESCRI" 			VARCHAR2(2000 BYTE)		constraint NN_CO_TPARA_DESCRI  not null, 
		"PARA_FCREA" 			DATE					constraint NN_CO_TPARA_FCREA   not null, 
        "PARA_VALOR"            VARCHAR2(2000 BYTE)		constraint NN_CO_TPARA_VALOR   not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
 

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPARA"."PARA_PARA" IS 'Identificador unico del tipo del parametro ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPARA"."PARA_DESCRI" IS 'Descripcion del parametro ';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPARA"."PARA_FCREA" IS 'Fecha de creacion';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TPARA"."PARA_VALOR"  IS 'Valor del parametro';
   
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TPARA"  IS 'Contiene los parámetros generales utilizados por la aplicación en el modulo Convertidor';
   
   

   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TPARA"
  add  constraint "PK_CO_TPARA"
       primary key ("PARA_PARA" )
       using index 
       tablespace &Indices;