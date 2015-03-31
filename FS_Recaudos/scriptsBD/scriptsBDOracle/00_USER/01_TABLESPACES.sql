REM ******************************************************************
REM Fecha         : 17/03/2015
REM Realizado por : Ownk
REM Base de Datos : FS_RECAUDOS_US
REM ******************************************************************


REM TABLESPACE Y SEGMENTOS DE ROLLBACK
set feedback off
spool TMP.lst
select distinct 'pause DEBE estar conectado con Usuario SYSTEM' || chr(10) || 'ROLL' || chr(10) || 'EXIT'
from dual
where user <> 'SYSTEM'
/
spool off
start TMP.lst
set feedback on

REM TABLESPACE TEMPORAL

Create Temporary tablespace TS_TRECAUDOS TEMPFILE
'C:\oraclexe\oradata\XE\TRECAUDOS.dbf' size 300m
         Extent management local uniform size 2m
;


REM TABLESPACE DE DATOS

CREATE TABLESPACE TS_DRECAUDOS DATAFILE 
  'C:\oraclexe\oradata\XE\DRECAUDOS.DBF' SIZE 300M AUTOEXTEND ON NEXT 1024M MAXSIZE UNLIMITED
LOGGING
ONLINE
PERMANENT
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;


REM TABLESPACE DE INDICES 
                                                            
CREATE TABLESPACE TS_IRECAUDOS DATAFILE 
  'C:\oraclexe\oradata\XE\IRECAUDOS.DBF' SIZE 300M AUTOEXTEND ON NEXT 1024M MAXSIZE UNLIMITED
LOGGING
ONLINE
PERMANENT
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;   

