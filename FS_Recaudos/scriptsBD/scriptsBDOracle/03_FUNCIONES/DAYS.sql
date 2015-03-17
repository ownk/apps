CREATE FUNCTION "FS_RECAUDOS_US"."DIAS" (fechaMenor in date, fechaMayor in date) RETURN number is
begin
	
	RETURN trunc(fechaMayor-fechaMenor);
	
end DIAS;
/