/******************************************************************************
VERSION:		
FECHA:		  
AUTOR:		  
DERECHOS:		
TABLA:	    
FUNCION:	Retornar el valor de la secuencia en oracle
		
OBSERVACIONES:	
******************************************************************************/

-- 
create or replace FUNCTION "FS_RECAUDOS_US"."NEXTVALUE" (sequencename in varchar) return number is
  result number;
begin
  EXECUTE IMMEDIATE ('select ' || sequencename || '.nextval from dual') into result;
  return(result);
  
end NEXTVALUE;
/