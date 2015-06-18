alter table FS_RECAUDOS_US.UN_TTPAR add tpar_nomb_arun varchar2(2000);

-- Add comments to the columns 
comment on column FS_RECAUDOS_US.UN_TTPAR.tpar_nomb_arun
  is 'Prefijo de nombre de archivo unificado. Eje: Cta_NNNN_ddmm NNNN:Ultimos Digitos Cuenta, ddmm: Dia mes de la fecha fin de proceso';
