PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.CP_THTPR


Insert into FS_RECAUDOS_US.CP_THTPR (HTPR_FRECA_ORIG,HTPR_FRECA_NORM,HTPR_DESCRI, HTPR_COLOR) values('REFE', 'Efectivo',  'Recaudo efectivo plano', 'ninguno');
Insert into FS_RECAUDOS_US.CP_THTPR (HTPR_FRECA_ORIG,HTPR_FRECA_NORM,HTPR_DESCRI, HTPR_COLOR) values('RCHE', 'Cheque',  'Recaudo cheque plano', 'ninguno');
Insert into FS_RECAUDOS_US.CP_THTPR (HTPR_FRECA_ORIG,HTPR_FRECA_NORM,HTPR_DESCRI, HTPR_COLOR) values('Deposito por recaudo efec', 'Efectivo',  'Recaudo efectivo Internet', 'ninguno');
Insert into FS_RECAUDOS_US.CP_THTPR (HTPR_FRECA_ORIG,HTPR_FRECA_NORM,HTPR_DESCRI, HTPR_COLOR) values('Deposito por recaudo cheq', 'Cheque',  'Recaudo cheque Internet', 'ninguno');
Insert into FS_RECAUDOS_US.CP_THTPR (HTPR_FRECA_ORIG,HTPR_FRECA_NORM,HTPR_DESCRI, HTPR_COLOR) values('Db transferen otra cuenta', 'Transferencia',  'Recaudo transferencia Internet', 'ninguno');


commit;