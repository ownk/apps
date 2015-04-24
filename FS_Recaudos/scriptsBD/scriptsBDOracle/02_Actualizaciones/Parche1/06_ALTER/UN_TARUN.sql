alter table FS_RECAUDOS_US.UN_TARUN add arun_archivos_repetidos number;

-- Add comments to the columns 
comment on column FS_RECAUDOS_US.UN_TARUN.arun_archivos_repetidos
  is 'Cantidad de archivos repetidos';
