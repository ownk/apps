/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	      
	FUNCION: Tipos de archivo de recaudo a convertir
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TTPAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TTPAR" 
   (	"TPAR_TPAR" 		    VARCHAR2(100 BYTE)			constraint NN_CO_TTPAR_TPAR             not null, 
		"TPAR_DESCRI" 		    VARCHAR2(2000 BYTE)			constraint NN_CO_TTPAR_DESCRI           not null,
        "TPAR_FONDO" 		    NUMBER          			constraint NN_CO_TTPAR_FONDO            not null,
        "TPAR_PLAN_GENERICO" 	NUMBER					    constraint NN_CO_TTPAR_PLAN             not null,
		"TPAR_CTA_RECAUDO" 		VARCHAR2(2000 BYTE)		    constraint NN_CO_TTPAR_CTA              not null, 
        "TPAR_COMP_RF_SN" 	    VARCHAR2(1 BYTE)		    constraint NN_CO_TTPAR_COMP_RF_SN       not null, 
        "TPAR_USA_VOL_SN" 	    VARCHAR2(1 BYTE)		    constraint NN_CO_TTPAR_VOLANTES_SN      not null,
        "TPAR_NOMB_ARGE" 		VARCHAR2(2000)			    constraint NN_CO_TTPAR_NOMB_ARGE        not null,
        "TPAR_USUA" 		    VARCHAR2(100 BYTE)			constraint NN_CO_TTPAR_USUA             not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_TPAR" IS 'Identificador unico de tipo de archivo recaudo';
 
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_DESCRI" IS 'Descripcion del tipo de archivo';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_USUA" IS 'Usuario que crea el tipo de archivo';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_COMP_RF_SN" IS 'Se debe completar numero de referencia con el numero de fondo cuando el la referencia sea menor al parametro co.tam_referencia_recaudo. Valores S o N';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_USA_VOL_SN" IS 'Indica si para el tipo de archivo de recaudo se dejan las referencia de volantes o se deben cambiar por encargo generico. Valores S o N';

   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_FONDO" IS 'Numero del fondo relacionado al tipo de archivo ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_PLAN_GENERICO" IS 'Plan generico SIFI asociado al tipo de archivo ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_CTA_RECAUDO" IS 'Cuenta recaudadora asociada al tipo de archivo ';
   
   COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TTPAR"."TPAR_NOMB_ARGE" IS 'refijo de nombre de archivo convertido. Eje: Cta_NNNNyyyy NNNN:Ultimos Digitos Cuenta, yyyy: año en el cual se esta generando ';
 
   COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TTPAR"  IS 'Tabla donde se almacenan los tipos de archivos de recaudo a convertir';

   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TTPAR"
  add  constraint "PK_CO_TTPAR"
       primary key ("TPAR_TPAR")
       using index 
       tablespace &Indices;
	   
