REM USUARIO DEL APLICATIVO FS_RECAUDOS_US

drop user FS_RECAUDOS_US cascade;

Create user FS_RECAUDOS_US
 Identified by fs_recaudos_us
 Default tablespace TS_DRECAUDOS
 Temporary tablespace TS_TRECAUDOS	
 account unlock;


Grant 
  ALTER USER, CREATE CLUSTER, CREATE DATABASE LINK, CREATE PROFILE , ALTER PROFILE, 
  CREATE PROCEDURE, CREATE PUBLIC DATABASE LINK, 
  CREATE PUBLIC SYNONYM, CREATE ROLE, CREATE SEQUENCE, 
  CREATE SESSION, CREATE SYNONYM, CREATE TABLE, 
  CREATE TRIGGER, CREATE USER , CREATE VIEW, 
  DROP PUBLIC SYNONYM, EXECUTE ANY PROCEDURE, 
  SELECT ANY SEQUENCE, SELECT ANY TABLE, 
  UNLIMITED TABLESPACE to FS_RECAUDOS_US;
