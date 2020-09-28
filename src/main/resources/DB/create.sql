SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 about VARCHAR,
 website VARCHAR,
 email VARCHAR
);

CREATE TABLE IF NOT EXISTS users (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 position VARCHAR,
 roles VARCHAR
);

CREATE TABLE IF NOT EXISTS news (
 id int PRIMARY KEY auto_increment,
 headline VARCHAR,
 content VARCHAR,
 author VARCHAR,
 departmentid INTEGER,
 createdat LONG
);

CREATE TABLE IF NOT EXISTS departments_users (
 id int PRIMARY KEY auto_increment,
 Userid INTEGER,
 departmentid INTEGER
);