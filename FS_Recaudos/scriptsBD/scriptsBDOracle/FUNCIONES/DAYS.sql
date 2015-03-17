CREATE FUNCTION "UDTESIS"."DIAS" (fechaMenor in date, fechaMayor in date) RETURN number is
begin
	
	RETURN trunc(fechaMayor-fechaMenor);
	
end DIAS;
/