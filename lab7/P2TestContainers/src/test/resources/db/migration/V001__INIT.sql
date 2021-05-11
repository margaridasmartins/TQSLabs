CREATE TABLE books (
  id BIGSERIAL PRIMARY KEY,
  title varchar(255) not null,
  author varchar(255) not null,
  published date
);

-- INSERT INTO books VALUES("A Game of Thrones", "George RR Martin","1996-08-08");
-- INSERT INTO books VALUES("A Feast for Crows", "George RR Martin","2005-09-08");
