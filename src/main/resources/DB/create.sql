SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments (
id int PRIMARY KEY auto_increment,
name VARCHAR,
about VARCHAR,
website VARCHAR
);

CREATE TABLE IF NOT EXISTS users (
id int PRIMARY KEY auto_increment,
name VARCHAR,
position VARCHAR,
role VARCHAR,
userid INTEGER
);

CREATE TABLE IF NOT EXISTS news (
id int PRIMARY KEY auto_increment,
headline VARCHAR,
content VARCHAR,
author VARCHAR,
newsid INTEGER
);

CREATE TABLE IF NOT EXISTS departments_users (
id int PRIMARY KEY auto_increment,
userid INTEGER,
departmentid INTEGER
);