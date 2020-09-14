DROP TABLE IF EXISTS participant;
DROP TABLE IF EXISTS  race;
DROP TABLE IF EXISTS race_event;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS post_comment;

CREATE TABLE participant
(
 id serial PRIMARY KEY,
 name varchar(100),
 Email varchar(100) DEFAULT NULL,
 Phone varchar(100) DEFAULT NULL,
 Club varchar(100) DEFAULT NULL,
 Age varchar(100) DEFAULT NULL,
 Sex varchar(100) DEFAULT NULL
);

CREATE TABLE race
(
 id serial PRIMARY KEY,
 name varchar(100),
 description varchar (100),
 distance varchar (100)
);

CREATE TABLE race_event
(
 id serial PRIMARY KEY,
 name varchar (100),
 description varchar (100),
 fee varchar (100),
 date date,
 event_location varchar (100)
);
CREATE  TABLE post
(
 id serial PRIMARY KEY ,
 title varchar (100)
);
CREATE table post_comment
(
  id serial primary key,
  review varchar (100)
);