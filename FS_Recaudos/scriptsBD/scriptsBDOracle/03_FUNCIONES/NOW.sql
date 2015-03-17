/******************************************************************************
VERSION:		
FECHA:		  
AUTOR:		  
DERECHOS:		
TABLA:	    
FUNCION:	Retornar el sysdate en oracle
		
OBSERVACIONES:	
******************************************************************************/

--
create or replace FUNCTION "FS_RECAUDOS_US"."NOW" return DATE is
begin
  return(sysdate);
end now;
/