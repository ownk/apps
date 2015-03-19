/******************************************************************************
	VERSION: 1.0		
	FECHA:	 19/03/2015	  
	AUTOR:	 ownk	
	FUNCION:	Retornar el sysdate en oracle
		
******************************************************************************/

--
create or replace FUNCTION "FS_RECAUDOS_US"."NOW" return DATE is
begin
  return(sysdate);
end now;
/