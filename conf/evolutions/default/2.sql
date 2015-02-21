# Fix and add models to db
# --- !Ups

ALTER TABLE history CHANGE printerid printer_id bigint(20);
ALTER TABLE history CHANGE useri_d user_id bigint(20);
ALTER TABLE document CHANGE public visibility TINYINT(1);

ALTER TABLE user ADD COLUMN email VARCHAR(255);

CREATE TABLE login (user_id bigint(20) NOT NULL, hash varchar(255) NOT NULL);
ALTER TABLE login ADD INDEX FKlogin380874 (user_id), ADD CONSTRAINT FKlogin380874 FOREIGN KEY (user_id) REFERENCES `user` (id);

# --- !Downs

ALTER TABLE history CHANGE printer_id printerid bigint(20);
ALTER TABLE history CHANGE user_id useri_d bigint(20);
ALTER TABLE document CHANGE visibility public TINYINT(1);

ALTER TABLE user drop COLUMN email;

ALTER TABLE login DROP FOREIGN KEY FKlogin380874;
DROP TABLE IF EXISTS login;