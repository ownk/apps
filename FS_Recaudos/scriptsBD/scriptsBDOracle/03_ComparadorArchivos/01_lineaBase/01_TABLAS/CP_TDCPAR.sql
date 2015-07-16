/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Detalle Comparacion Archivo Recaudo
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CP_TDCPAR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CP_TDCPAR" 
   (	"DCPAR_CPAR" 		NUMBER					constraint NN_CP_TDCPAR_CPAR not null,
		"DCPAR_ID_REG_ORIG" NUMBER		            constraint NN_CP_TDCPAR_ID_REG_ORIG not null,
		"DCPAR_FUENTE" 		VARCHAR2(10 BYTE)		constraint NN_CP_TDCPAR_FUENTE not null, 
        "DCPAR_FRECA_NORM" 	DATE				    constraint NN_CP_TDCPAR_FRECA_NORM not null, 
        "DCPAR_FRECA_ORIG" 	VARCHAR2(100 BYTE)		constraint NN_CP_TDCPAR_FRECA_ORIG not null, 
        "DCPAR_TRECA_NORM" 	VARCHAR2(1000 BYTE)		constraint NN_CP_TDCPAR_TRECA_NORM not null, 
        "DCPAR_TRECA_ORIG" 	VARCHAR2(1000 BYTE)		constraint NN_CP_TDCPAR_TRECA_ORIG not null, 
        "DCPAR_OFIC_NORM"   VARCHAR2(1000 BYTE)		constraint NN_CP_TDCPAR_TOFIC_NORM not null,
        "DCPAR_OFIC_ORIG"   VARCHAR2(1000 BYTE)		constraint NN_CP_TDCPAR_TOFIC_ORIG not null,
        "DCPAR_REFERENCIA" 	VARCHAR2(1000 BYTE)		constraint NN_CP_TDCPAR_REFERENCIA not null,
        "DCPAR_OBSERV" 	    VARCHAR2(1000 BYTE)		constraint NN_CP_TDCPAR_APORTANTE not null, 
		"DCPAR_VALOR" 		NUMBER(21, 2) 		    constraint NN_CP_TDCPAR_VALOR not null,
        "DCPAR_FCREA" 	    DATE				    constraint NN_CP_TDCPAR_FCREA not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
    
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_CPAR" 		IS 'Identificador unico del comparacion de archivos';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_ID_REG_ORIG" 	IS 'Consecutivo de registro de archivo fuente ';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_FUENTE" 		IS 'Fecha de recaudo: PLANO / INTERNET';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_FRECA_NORM"   IS 'Fecha normalizada de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_FRECA_ORIG"   IS 'Fecha orignal de recaudo especificada en el archivo fuente ';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_TRECA_NORM"   IS 'Tipo de recuado normalizado';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_TRECA_ORIG"   IS 'Tipo de recaudo original espeficiado en el archivo fuente';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_OFIC_NORM"    IS 'Oficina normalizada';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_OFIC_ORIG"    IS 'Oficina original especificada en el archivo fuente';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_REFERENCIA"   IS 'Referencia de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_OBSERV" 	    IS 'Observacion';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_VALOR" 		IS 'Valor de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CP_TDCPAR"."DCPAR_FCREA" 	    IS 'Fecha de creacion del registro';
    
    
    
    COMMENT ON TABLE "FS_RECAUDOS_US"."CP_TDCPAR"  IS 'Tabla que almacena el detalle comparacion archivo de recaudo';
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CP_TDCPAR"
  add  constraint "PK_CP_TDCPAR"
       primary key ("DCPAR_CPAR", "DCPAR_ID_REG_ORIG", "DCPAR_FUENTE", "DCPAR_TRECA_NORM" )
       using index 
       tablespace &Indices;