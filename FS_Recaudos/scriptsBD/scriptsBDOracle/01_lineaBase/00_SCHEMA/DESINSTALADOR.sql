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

REM USUARIO  DUENHO DEL APLICATIVO FS_RECAUDOS_US
drop user FS_RECAUDOS_US cascade;
/


REM TABLESPACE DE DATOS
DROP TABLESPACE TS_DRECAUDOS INCLUDING CONTENTS
/

REM TABLESPACE DE INDICES 
DROP TABLESPACE TS_IRECAUDOS INCLUDING CONTENTS
/

REM TABLESPACE TEMPORAL
DROP tablespace TS_TRECAUDOS
/






