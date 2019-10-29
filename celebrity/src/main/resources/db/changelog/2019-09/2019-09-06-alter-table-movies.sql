ALTER TABLE movies ALTER COLUMN movdb_id TYPE varchar USING movdb_id::varchar;
ALTER TABLE movies ALTER COLUMN movdb_id DROP NOT NULL;
ALTER TABLE movies ALTER COLUMN imdb_id TYPE varchar USING imdb_id::varchar;
ALTER TABLE movies ALTER COLUMN imdb_id DROP NOT NULL;