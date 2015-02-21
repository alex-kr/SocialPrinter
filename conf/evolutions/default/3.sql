# Fix and add models to db
# --- !Ups

ALTER TABLE user ADD COLUMN password VARCHAR(255);

# --- !Downs

ALTER TABLE user drop COLUMN password;
