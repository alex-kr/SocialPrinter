# --- !Ups

ALTER TABLE document ADD COLUMN path VARCHAR(255);

# --- !Downs

ALTER TABLE document drop COLUMN path;
