# --- !Ups

ALTER TABLE printer ADD COLUMN name VARCHAR(255);

# --- !Downs

ALTER TABLE printer DROP COLUMN name;
