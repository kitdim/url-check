CREATE TABLE checks (
  id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  statusCode varchar(255),
  title varchar(255),
  h1 varchar(255),
  description varchar(255),
  created_date TIMESTAMP,
);

CREATE TABLE urls (
  id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name varchar(255),
  status varchar(255),
  created_date TIMESTAMP
);


INSERT INTO urls (name)
VALUES ('TEST.com');