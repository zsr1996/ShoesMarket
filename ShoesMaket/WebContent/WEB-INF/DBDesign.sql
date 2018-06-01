SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS shoes;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE shoes
(
	shoeid int NOT NULL AUTO_INCREMENT,
	pinpaiming varchar(20) NOT NULL,
	shoujia float NOT NULL,
	goumaishijian varchar(20) NOT NULL,
	xiezitupian varchar(20),
	miaoshu text,
	PRIMARY KEY (shoeid)
);


CREATE TABLE users
(
	userid int NOT NULL AUTO_INCREMENT,
	firstname varchar(20) NOT NULL,
	lastname varchar(20) NOT NULL,
	sex varchar(6) NOT NULL,
	email varchar(50) NOT NULL,
	password varchar(60) DEFAULT '' NOT NULL,
	age int,
	phone int,
	image varchar(200),
	PRIMARY KEY (userid),
	UNIQUE (email)
);

drop table users;

