DROP TABLE IF EXISTS participant;

CREATE TABLE participant
(
 id serial PRIMARY KEY,
 FirstName varchar(100),
 LastName varchar(100),
 Email varchar(100) DEFAULT NULL,
 Phone varchar(100) DEFAULT NULL,
 Club varchar(100) DEFAULT NULL,
 Age varchar(100) DEFAULT NULL,
 Sex varchar(100) DEFAULT NULL
)