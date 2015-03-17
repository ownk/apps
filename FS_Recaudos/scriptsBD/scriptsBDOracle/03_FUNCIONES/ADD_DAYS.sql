CREATE OR REPLACE FUNCTION "FS_RECAUDOS_US"."ADD_DAYS" (fecha in date , dias in number) RETURN DATE   is
begin
  return (fecha+dias);
end ADD_DAYS;
/