ALTER session SET "_ORACLE_SCRIPT"=true;

CREATE USER daddysouvenir
  IDENTIFIED BY devekis01
  quota 100M on SYSTEM;

GRANT create session TO daddysouvenir;
GRANT create table TO daddysouvenir;
GRANT create view TO daddysouvenir;
GRANT create any trigger TO daddysouvenir;
GRANT create any procedure TO daddysouvenir;
GRANT create sequence TO daddysouvenir;
GRANT create synonym TO daddysouvenir;

--end
