CREATE OR REPLACE FUNCTION "UDTESIS"."ADD_DAYS" (fecha in date , dias in number) RETURN DATE   is
begin
  return (fecha+dias);
end ADD_DAYS;
/