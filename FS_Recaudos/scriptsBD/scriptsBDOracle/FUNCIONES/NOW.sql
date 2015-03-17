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
create or replace FUNCTION "UDTESIS"."NOW" return DATE is
begin
  return(sysdate);
end now;
/