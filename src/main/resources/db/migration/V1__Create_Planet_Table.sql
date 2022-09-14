CREATE TABLE IF NOT EXISTS planet (
   id smallint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   name varchar(80) UNIQUE NOT NULL,
   climate varchar(30) NOT NULL,
   terrain varchar(30) NOT NULL,
   number_of_apparitions integer NOT NULL
);