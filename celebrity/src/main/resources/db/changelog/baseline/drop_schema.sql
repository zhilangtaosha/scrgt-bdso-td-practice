UPDATE pg_database SET datallowconn = 'true' WHERE datname = :databaseNameQuotes;
DROP SCHEMA IF EXISTS :databaseSchema CASCADE;