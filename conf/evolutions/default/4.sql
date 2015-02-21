# Fix and add models to db
# --- !Ups

DROP TABLE login;

# --- !Downs

CREATE TABLE login (user_id bigint(20) NOT NULL, hash varchar(255) NOT NULL);
ALTER TABLE login ADD INDEX FKlogin380874 (user_id), ADD CONSTRAINT FKlogin380874 FOREIGN KEY (user_id) REFERENCES `user` (id);

