ALTER TABLE MOVIES 
ADD COLUMN CREATED_DATE	TIMESTAMP		NOT NULL,
ADD COLUMN CREATED_BY		VARCHAR(100)	NOT NULL,
ADD COLUMN UPDATED_DATE	TIMESTAMP		NOT NULL,
ADD COLUMN UPDATED_BY		VARCHAR(100)	NOT NULL;