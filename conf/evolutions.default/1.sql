# Initial db model generation
# --- !Ups

CREATE TABLE `user` (id bigint(20) NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, balance decimal(19, 0) NOT NULL, rating int(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE printer (id bigint(20) NOT NULL AUTO_INCREMENT, location varchar(255) NOT NULL, pages_available int(10), user_id bigint(20) NOT NULL, printer_specification_id bigint(20), PRIMARY KEY (id));
CREATE TABLE document (id bigint(20) NOT NULL AUTO_INCREMENT, name varchar(255), public tinyint(1) NOT NULL, date_loaded timestamp NOT NULL, data blob, user_id bigint(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE printer_specification (id bigint(20) NOT NULL AUTO_INCREMENT, color tinyint(1), photo tinyint(1), min_length int(10), max_length int(10), min_width int(10), max_width int(10), ppm_color int(10), ppm_black int(10), min_length_photo int(10), max_length_photo int(10), min_width_photo int(10), max_width_photo int(10), PRIMARY KEY (id));
CREATE TABLE history (id bigint(20) NOT NULL, useri_d bigint(20) NOT NULL, printerid bigint(20) NOT NULL, document_id bigint(20) NOT NULL, date_print timestamp NOT NULL, PRIMARY KEY (id));
ALTER TABLE printer ADD INDEX FKprinter248891 (user_id), ADD CONSTRAINT FKprinter248891 FOREIGN KEY (user_id) REFERENCES `user` (id);
ALTER TABLE printer ADD INDEX FKprinter855256 (printer_specification_id), ADD CONSTRAINT FKprinter855256 FOREIGN KEY (printer_specification_id) REFERENCES printer_specification (id);
ALTER TABLE document ADD INDEX FKdocument191326 (user_id), ADD CONSTRAINT FKdocument191326 FOREIGN KEY (user_id) REFERENCES `user` (id);
ALTER TABLE history ADD INDEX FKhistory633990 (document_id), ADD CONSTRAINT FKhistory633990 FOREIGN KEY (document_id) REFERENCES document (id);
ALTER TABLE history ADD INDEX FKhistory413996 (useri_d), ADD CONSTRAINT FKhistory413996 FOREIGN KEY (useri_d) REFERENCES `user` (id);
ALTER TABLE history ADD INDEX FKhistory240674 (printerid), ADD CONSTRAINT FKhistory240674 FOREIGN KEY (printerid) REFERENCES printer (id);

# --- !Downs

ALTER TABLE printer DROP FOREIGN KEY FKprinter248891;
ALTER TABLE printer DROP FOREIGN KEY FKprinter855256;
ALTER TABLE document DROP FOREIGN KEY FKdocument191326;
ALTER TABLE history DROP FOREIGN KEY FKhistory633990;
ALTER TABLE history DROP FOREIGN KEY FKhistory413996;
ALTER TABLE history DROP FOREIGN KEY FKhistory240674;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS printer;
DROP TABLE IF EXISTS document;
DROP TABLE IF EXISTS printer_specification;
DROP TABLE IF EXISTS history;
