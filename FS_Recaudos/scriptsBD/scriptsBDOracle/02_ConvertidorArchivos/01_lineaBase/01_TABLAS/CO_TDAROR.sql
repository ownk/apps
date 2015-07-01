/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Detalle Archivo plano de recaudo original por convertir
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TDAROR
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TDAROR" 
   (	"DAROR_AROR" 		NUMBER					constraint NN_CO_TDAROR_AROR not null,
		"DAROR_ID_REG" 	    NUMBER		            constraint NN_CO_TDAROR_ID_REG not null,
		"DAROR_FRECA" 		VARCHAR2(10 BYTE)		constraint NN_CO_TDAROR_FRECA not null, 			
		"DAROR_REFERENCIA" 	VARCHAR2(100 BYTE)		constraint NN_CO_TDAROR_REFERENCIA not null,
        "DAROR_APORTANTE" 	VARCHAR2(100 BYTE)		constraint NN_CO_TDAROR_APORTANTE not null, 
		"DAROR_OFIC" 		VARCHAR2(10 BYTE)		constraint NN_CO_TDAROR_OFIC not null,
		"DAROR_VEFE" 		VARCHAR2(100)          	constraint NN_CO_TDAROR_VEFE not null, 
		"DAROR_VCHE" 		VARCHAR2(100) 		    constraint NN_CO_TDAROR_VCHE not null, 
		"DAROR_VTOT" 		VARCHAR2(100) 		    constraint NN_CO_TDAROR_VTOT not null, 
		"DAROR_CONS_BSC_1" 	VARCHAR2(10 BYTE)		constraint NN_CO_TDAROR_CONS_BSC_1 not null, 
        "DAROR_TIPO_RECA" 	VARCHAR2(10 BYTE)		constraint NN_CO_TDAROR_TIPO_RECA not null, 
        "DAROR_COMP" 		VARCHAR2(10 BYTE)		constraint NN_CO_TDAROR_COMP not null, 
        "DAROR_CONS_BSC_2"  VARCHAR2(10 BYTE)		constraint NN_CO_TDAROR_CONS_BSC_2 not null, 
        "DAROR_REGISTRO" 	VARCHAR2(4000 BYTE)		constraint NN_CO_TDAROR_REGISTRO   not null,
        "DAROR_FCREA" 		DATE					constraint NN_CO_TDAROR_FCREA not null,
        "DAROR_CTA_RECA"    VARCHAR2(100 BYTE)		constraint NN_CO_TDAROR_CTA_RECA not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
    
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_AROR" 		IS 'Identificador unico del archivo original de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_ID_REG" 	    IS 'Consecutivo de registro ';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_FRECA" 		IS 'Fecha de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_REFERENCIA" 	IS 'Referencia o plan donde se hara recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_APORTANTE" 	IS 'Identificacion del aportante';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_OFIC" 		IS 'Oficina desde la cual se hace el aporte';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_VEFE" 		IS 'Valor en efectivo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_VCHE" 		IS 'Valor en cheque';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_VTOT" 		IS 'Valor total del recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_CONS_BSC_1" 	IS 'Consecutivo 1 BSC';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_TIPO_RECA" 	IS 'Tipo de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_COMP" 		IS 'Comprobante de recaudo BSC';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_CONS_BSC_2"   IS 'Consecutivo 2 BSC';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_REGISTRO" 	IS 'Copia del registro original';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_FCREA" 		IS 'Fecha de creacion en el sistema';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDAROR"."DAROR_CTA_RECA" 	IS 'Cuenta recaudadora';
    
    
    COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TDAROR"  IS 'Tabla que almacena el detalle archivo original por convertir';
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TDAROR"
  add  constraint "PK_CO_TDAROR"
       primary key ("DAROR_AROR", "DAROR_ID_REG")
       using index 
       tablespace &Indices;