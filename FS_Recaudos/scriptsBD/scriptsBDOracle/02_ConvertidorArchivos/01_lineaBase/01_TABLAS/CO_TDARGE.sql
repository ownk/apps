/******************************************************************************
	VERSION: 1.0		
	FECHA:	 10/06/2015	  
	AUTOR:	 ownk	      
	FUNCION: Detalle Archivo plano de recaudo generado 
******************************************************************************/

set define '&'
define Indices=TS_IRECAUDOS

Prompt 
Prompt Creando tabla CO_TDARGE
Prompt

  CREATE TABLE "FS_RECAUDOS_US"."CO_TDARGE" 
   (	"DARGE_ARGE" 		    NUMBER					constraint NN_CO_TDARGE_ARGE not null,
		"DARGE_DAROR_ID_REG"    NUMBER		            constraint NN_CO_TDARGE_DAROR_ID_REG not null,
        "DARGE_ID_REG"          NUMBER		            constraint NN_CO_TDARGE_ID_REG not null,
		"DARGE_FRECA" 		    VARCHAR(10 BYTE)		constraint NN_CO_TDARGE_FRECA not null, 			
		"DARGE_REFERENCIA" 	    VARCHAR2(100 BYTE)		constraint NN_CO_TDARGE_REFERENCIA not null,
        "DARGE_APORTANTE" 	    VARCHAR2(100 BYTE)		constraint NN_CO_TDARGE_APORTANTE not null, 
		"DARGE_OFIC" 		    VARCHAR2(10 BYTE)		constraint NN_CO_TDARGE_OFIC not null,
		"DARGE_VEFE" 		    VARCHAR2(100)          	constraint NN_CO_TDARGE_VEFE not null, 
		"DARGE_VCHE" 		    VARCHAR2(100) 		    constraint NN_CO_TDARGE_VCHE not null, 
		"DARGE_VTOT" 		    VARCHAR2(100) 		    constraint NN_CO_TDARGE_VTOT not null, 
		"DARGE_CONS_BSC_1" 	    VARCHAR2(10 BYTE)		constraint NN_CO_TDARGE_CONS_BSC_1 not null, 
        "DARGE_TIPO_RECA" 	    VARCHAR2(10 BYTE)		constraint NN_CO_TDARGE_TIPO_RECA not null, 
        "DARGE_COMP" 		    VARCHAR2(10 BYTE)		constraint NN_CO_TDARGE_COMP not null, 
        "DARGE_CONS_BSC_2"       VARCHAR2(10 BYTE)		constraint NN_CO_TDARGE_CONS_BSC_2 not null, 
        "DARGE_FCREA" 		    DATE					constraint NN_CO_TDARGE_FCREA not null
   ) 
   storage( initial 10k  next 10k  pctincrease 0 )
/  
    
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_ARGE" 		IS 'Identificador unico del archivo original de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_DAROR_ID_REG" IS 'Consecutivo de registro del archivo de recaudo original';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_ID_REG" 	    IS 'Consecutivo de registro ';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_FRECA" 		IS 'Fecha de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_REFERENCIA" 	IS 'Referencia o plan donde se hara recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_APORTANTE" 	IS 'Identificacion del aportante';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_OFIC" 		IS 'Oficina desde la cual se hace el aporte';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_VEFE" 		IS 'Valor en efectivo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_VCHE" 		IS 'Valor en cheque';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_VTOT" 		IS 'Valor total del recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_CONS_BSC_1" 	IS 'Consecutivo 1 BSC';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_TIPO_RECA" 	IS 'Tipo de recaudo';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_COMP" 		IS 'Comprobante de recaudo BSC';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_CONS_BSC_2"    IS 'Consecutivo 2 BSC';
    COMMENT ON COLUMN "FS_RECAUDOS_US"."CO_TDARGE"."DARGE_FCREA" 		IS 'Fecha de creacion en el sistema';
    
    COMMENT ON TABLE "FS_RECAUDOS_US"."CO_TDARGE"  IS 'Tabla que almacena el detalle archivo original por convertir';
   
   prompt Llave Primaria

alter table "FS_RECAUDOS_US"."CO_TDARGE"
  add  constraint "PK_CO_TDARGE"
       primary key ("DARGE_ARGE", "DARGE_ID_REG")
       using index 
       tablespace &Indices;