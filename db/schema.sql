CREATE TABLE accident (
  id serial primary key,
  name varchar(2000),
  text varchar(2000),
  address varchar(2000),
  typeId int REFERENCES accidentType(id)
)

CREATE TABLE accidentType (
  id serial primary key,
  name varchar(2000),
);

CREATE TABLE rules (
  id serial primary key,
  name varchar(2000)
);

CREATE TABLE accident_rules (
  id serial primary key,
  accidentId int REFERENCES accident(id),
	rulesId int REFERENCES rules(id)
);

