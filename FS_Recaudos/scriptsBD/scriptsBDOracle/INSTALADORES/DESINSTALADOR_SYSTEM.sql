REM ******************************************************************
REM Fecha         : 10/09/2011
REM Realizado por : UDTESIS PROJECT
REM Base de Datos : UDTESIS
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

REM USUARIO  DUE�O DEL APLICATIVO UDTESIS
drop user UDTESIS cascade;
/

REM TABLESPACE DE DATOS
REM TABLESPACE DE DATOS

DROP TABLESPACE TS_DUDTESIS INCLUDING CONTENTS
/

REM TABLESPACE DE INDICES 
                                                            
DROP TABLESPACE TS_IUDTESIS INCLUDING CONTENTS
/

REM TABLESPACE UDTESISTEMP
DROP tablespace UDTESISTEMP
/






