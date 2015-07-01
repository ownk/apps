PROMPT INSERTANDO DATOS EN FS_RECAUDOS_US.CO_TPARA

Insert into FS_RECAUDOS_US.CO_TPARA (PARA_PARA,PARA_DESCRI,PARA_FCREA,PARA_VALOR) values ('co.tam_referencia_recaudo','cantidad de caracteres valida que corresponden a un numero refencia valida en el archivo de recaudo.', sysdate, '13' );
Insert into FS_RECAUDOS_US.CO_TPARA (PARA_PARA,PARA_DESCRI,PARA_FCREA,PARA_VALOR) values ('co.pefijo_volante','Numero que permmite validar si el prefijo de una referencia corresponde a un volante', sysdate, '90');

commit;
