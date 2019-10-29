ALTER TABLE actors ALTER COLUMN movdb_id TYPE varchar USING movdb_id::varchar;
ALTER TABLE actors ALTER COLUMN movdb_id DROP NOT NULL;
ALTER TABLE actors ALTER COLUMN imdb_id TYPE varchar USING imdb_id::varchar;
ALTER TABLE actors ALTER COLUMN imdb_id DROP NOT NULL;
ALTER TABLE actors ALTER COLUMN height TYPE varchar USING height::varchar;